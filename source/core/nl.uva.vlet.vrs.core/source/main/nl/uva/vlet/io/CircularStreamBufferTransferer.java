/*
 * Copyright 2006-2011 The Virtual Laboratory for e-Science (VL-e) 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").  
 * You may not use this file except in compliance with the License. 
 * For details, see the LICENCE.txt file location in the root directory of this 
 * distribution or obtain the Apache Licence at the following location: 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 * 
 * See: http://www.vl-e.nl/ 
 * See: LICENCE.txt (located in the root folder of this distribution). 
 * ---
 * $Id: CircularStreamBufferTransferer.java,v 1.7 2011-04-18 12:00:40 ptdeboer Exp $  
 * $Date: 2011-04-18 12:00:40 $
 */ 
// source: 

package nl.uva.vlet.io;

import java.io.InputStream;
import java.io.OutputStream;

import nl.uva.vlet.Global;
import nl.uva.vlet.exception.VlException;
import nl.uva.vlet.exception.VlIOException;
import nl.uva.vlet.presentation.Presentation;
import nl.uva.vlet.tasks.ActionTask;
import nl.uva.vlet.tasks.ITaskMonitor;
import nl.uva.vlet.vfs.VFS;

/**
 * CircularStreamBufferTransferer copies data from in InputStream to 
 * an OutputStream.  
 * It uses a circular buffer to transfer bytes from the InputStream
 * to the OutputStream. 
 * It starts the reader in a background thread while waiting for the reader
 * to fill the buffer and starting writing the data in current thread.
 * This parallel read/write will better use the available bandwidth by 
 * both reading and writing at the same time.  
 * <p>
 * @author P.T. de Boer
 */

public class CircularStreamBufferTransferer
{
    // data buffer: acces is synchronized. 
    // This object is also used as general mutex.
    private byte buffer[]=null;

    private long totalRead = 0;

    private long nrWritten = 0;
    
    /** either unkownSize==true or nrToTransfer>0) */
    private boolean unknownSize=true; 
    
    /** either unkownSize==true or nrToTransfer>0) */
    private long nrToTransfer = -1; // keep unknown for now
    
    /** time in milliseconds */ 
    private long readTime=0;
    
    /** time in milliseconds */ 
    private long writeTime=0;

    /** 
     * Optional Transfer info. 
     * Class will update current transfer by updating 
     * the nr of bytes currently transferred by
     * this buffer. 
     */
    ITaskMonitor transferInfo=null;
    
    // *** 
    // optimization/limitation options 
    // *** 
    
    /** nr of bytes thats get read per read iteration:32k buffers */  
    private int readChunkSize=32*1024;
    
    /** nr of bytes thats get written each write iteration: 32k buffer. */  
    private int writeChunkSize=32*1024;

    /** buffer size to use */ 
    private int bufferSize=0;
    
    /**
     *Creates new StreamBuffer using as internal buffer with size "size"
     *
     */ 
    public CircularStreamBufferTransferer(int size)
    {
        init(size); 
    }
    
    /**
     *Creates new StreamBuffer using as internal buffer with size "size"
     *
     */ 
    public CircularStreamBufferTransferer(int size,
            InputStream input,OutputStream output)
    {
        init(size); 
        this.setInputStream(input);
        this.setOutputstream(output); 
    }
    
    private void init(int size)
    {
        this.buffer = new byte[size];
        this.bufferSize=size; 
    }

    Boolean readerWait = new Boolean(true);

    Boolean writerWait = new Boolean(true);

    private OutputStream outputStream=null; 
    
    private InputStream inputStream=null; 

    private boolean cancelTransfer=false;

    private ActionTask readerTask=null; 
 
    /** Sets the OutputStream to write to. Can be called only once */  
    public void setOutputstream(OutputStream outp)
    {
        if (this.outputStream!=null)
            throw new Error("Cannot set OutputStream twice!"); 
        
        this.outputStream=outp; 
    }
 
    /** Sets the InputStream to read from. Can be called only once */ 
    public void setInputStream(InputStream inp)
    {
        if (this.inputStream!=null)
            throw new Error("Cannot set InputStream twice!"); 

        this.inputStream=inp; 
    }
    
    /** 
     * Limits the nr of bytes thats get written each write iteration. 
     * If the OutputStream can not efficiently (or not at all!) 
     * handle big writes, limit the maximum with this method. 
     */ 
    public void setMaxWriteChunkSize(int size)
    {
        this.writeChunkSize=size; 
    }
    
    /** 
     * Limits the nr of bytes thats get read each read iteration. 
     * If the InputStream can not efficiently (or not at all!) 
     * handle big reads, limit the maximum with this method. 
     * Usually the 'read()' method already reads the nr. bytes
     * it can handle per read (which it returns).   
     */ 
    public void setMaxReadChunkSize(int size)
    {
        this.readChunkSize=size; 
    }
   
    /** Starts read loop. Is started in the background by startTransfer() . */  
    protected void readLoop()  throws VlException
    {
        int buflen = buffer.length;
       
        try
        {
            // do loop while there is data left 
            while ( (unknownSize==true) || (totalRead < nrToTransfer))
            {
                if (mustStop())
                    throw new nl.uva.vlet.exception.VlInterruptedException("Transfer interrupted!"); 
                
                int start=0; 
                int delta=0; 
                
                // Do buffer calculations in MuTex'd time: 
                synchronized(buffer)
                {
                    delta = buflen; // ? istr.available();
                    int free = buflen - (int) (totalRead - nrWritten); // free space in
                                                                // buffer
                    // do not read past free space in buffer
                    if (delta > free)
                        delta = free;
                
                    // do not read to much at once:  
                    if (delta>readChunkSize)
                        delta=readChunkSize; 
                
                    // do not read past end of file (if size is known) 
                    if (nrToTransfer>=0)
                        if (totalRead + delta > nrToTransfer)
                            delta = (int) (nrToTransfer - totalRead);
                
                    start = (int) (totalRead % buflen);// start in cicular buffer

                    // do not read past buffer end (wrap around) 
                    if (start + delta > buflen)
                        delta = buflen - start; 
                }
                
                debugPrintf("reader: nrRead    =%d\n",totalRead);
                debugPrintf("reader: nrWritten =%d\n",nrWritten);
                debugPrintf("reader: nrToRead  =%d\n",nrToTransfer);
                debugPrintf("reader: start     =%d\n",start);
                debugPrintf("reader: delta     =%d\n",delta);
                
                // new data to tranfer ?
                if (delta > 0)
                {
                    long startTime=System.currentTimeMillis();
            
                    int n= inputStream.read(buffer, start, delta);
                    
                    if (n<0)  
                    {
                        if  (unknownSize==true)
                        {
                            // EOF when reading from unknown InputStream: 
                            // We know the size now, update nrToTransfer to current 
                            // number bytes read. This will stop the read and the write. 
                            // Set unknownSize to false to trigger updating the stats. 
                            
                            unknownSize=false;
                            nrToTransfer=totalRead;
                        }
                        else
                        {
                            Global.errorPrintf(this,"Got EOF while reading %d bytes from inputstream\n",nrToTransfer);
                            throw new VlIOException("Failed to read expected number of bytes: read="+totalRead+" while expected="+nrToTransfer);
                        }
                    }
                    else if (n>0)
                    {
                        // MUTEX save: field is only updated by reader:  
                        totalRead+=n; //update totalRead;
                    }
                    else if (n==0) 
                    {
                       Global.debugPrintf(this,"read(): Got 0 bytes ...\n"); 
                        // ok, try again could be time out.
                    }
                    
                    readTime+=System.currentTimeMillis()-startTime; 
                    debugPrintf("reader: after read, nrRead=%s\n",totalRead);
                    
                    // notify writer there is data (if writer is waiting) 
                    synchronized(writerWait)
                    {
                       writerWait.notify();
                    }
                }
                else if ((unknownSize==true) || (totalRead<nrToTransfer))
                {
                    // Wait for writer to free space. 
                    // Wait for .1 second max. or until writer notifies reader. 
                    synchronized (readerWait)
                    {
                        readerWait.wait(100);
                    }
                }
            }
            
            debugPrintf("--- Reader done ---\n"); 
            debugPrintf("reader total nrRead    =%d\n",totalRead);
            debugPrintf("reader total nrWritten =%d\n",nrWritten); 
            debugPrintf("reaeer nrToTransfer    =%d\n",nrToTransfer);
            
        }
        catch (Throwable err)
        {
            Global.errorPrintf(this, "***Error: Exception:%s\n",err);
              // Signal Strop: 
            this.cancelTransfer=true;
            // notify writer since there is a read error ! 
             
            synchronized(writerWait)
            {
               writerWait.notify();
            }
            
            throw new VlIOException("Exception while reading",err); 
        }
        
    }

    private void debugPrintf(String format,Object... args)
    {
        Global.debugPrintf(this, format,args); 
    }
    
    public void setStop(boolean val) 
    {
        cancelTransfer=val; 
    }
    
    protected boolean mustStop()
    {
        // ActionTask.mustStop() is called  
        if (cancelTransfer==true)
            return true;
        
        // User interaction through the dialog: Cancel! 
        if ((this.transferInfo!=null) && (transferInfo.isCancelled()))
            return true;
        
        return false; 
    }
    
    void writeLoop() throws VlIOException
    {
        int buflen = buffer.length;

        try
        {
            while ( (unknownSize==true) || (nrWritten < nrToTransfer) )
            {
                if (mustStop())
                    throw new nl.uva.vlet.exception.VlInterruptedException("Transfer interrupted!"); 
                
                int delta=0;
                int start=0;
                
                // Do buffer calculations in MuTex'd time: 
                synchronized(buffer)
                {
                    // nr bytes to be written
                    delta = (int) (totalRead - nrWritten); 
                    // start in cicular buffer
                    start = (int) (nrWritten % buflen);
                
                    if (start + delta > buflen)
                        delta = buflen - start; // wrap around buffer;

                    if (delta > writeChunkSize)
                        delta=writeChunkSize;
                }
                
                debugPrintf("writer nrRead    =%d\n",totalRead);
                debugPrintf("writer nrWritten =%d\n",nrWritten);
                debugPrintf("writer nrToRead  =%d\n",nrToTransfer);
                debugPrintf("writer start     =%d\n",start);
                debugPrintf("writer delta     =%d\n",delta);
                
                // data to write ? 
                if (delta > 0)
                {
                    long startTime=System.currentTimeMillis();
                     
                    outputStream.write(buffer, start, delta);
                    
                    writeTime+=System.currentTimeMillis()-startTime;
                    
                    // MUTEX save: field is only updated by writer: 
                    nrWritten += delta;
                    
                    if (transferInfo!=null) 
                    {
                        // update current transfer:
                        transferInfo.updateSubTaskDone(nrWritten);
                    }
                    //notify reader that buffer is empty
                    synchronized(readerWait)
                    {
                         readerWait.notify();
                    }
                }
                else if ((unknownSize==true) || (nrWritten<nrToTransfer))
                {
                       // Wait for reader to fill buffer. 
                    // wait for .1 second or until reader notifies writer.  
                    synchronized (writerWait)
                    {
                        writerWait.wait(100);
                    }
                }
            }
            
            debugPrintf("--- Writer done ---\n"); 
            debugPrintf("writer total nrRead    =%d\n",totalRead);
            debugPrintf("writer total nrWritten =%d\n",nrWritten); 
            debugPrintf("writer nrToTransfer    =%d\n",nrToTransfer);
            
            // in the case the reader still is waiting for the writer
            // to finish :
            
            synchronized(readerWait)
            {
                 readerWait.notify();
            }
        }
        catch (Throwable err)
        {
            Global.errorPrintf(this, "***Error: Exception:%s\n", err);
              // Signal Strop: 
            this.cancelTransfer=true;
            // notify reader since there is a read error ! 
            synchronized(readerWait)
            {
                 readerWait.notify();
            }
            // rethrow
            throw new VlIOException("Exception while writing",err); 
        }

    }

    /** Transfer upto numTranfer bytes, or -1 for all */ 
    public void startTransfer(long numTransfer) throws VlException
    {
        // =============================================================
        // Pre Transfer
        // =============================================================

        long start=System.currentTimeMillis();
        
        // fixed streamcopy or unknown.
        
        if (numTransfer>=0)
        {
            this.nrToTransfer=numTransfer;
            this.unknownSize=false;
        }
        else
        {
            // copy all (Stream Copy) 
            this.nrToTransfer=-1;
            this.unknownSize=true;
        }
        
        // update transferinfo
        if (this.transferInfo!=null) 
        {
            transferInfo.startSubTask("Performing StreamCopy",numTransfer);
            // set to 0 if unknown; !
            if (numTransfer<0)
                transferInfo.updateSubTaskDone(0); 
        }
        
        // start reader in background: 
        readerTask = new ActionTask(VFS.getTaskWatcher(),
                "CircularStreamBuffer.readerTask")
        {
           
            public void doTask() throws VlException
            {
                readLoop(); 
            }

            @Override
            public void stopTask()
            {
                setStop(true); 
            }
        };
        
        // =============================================================
        // Transfer Loop 
        // =============================================================
        
        readerTask.startTask();
        
        // writer will be last to finish so start in CURRENT thread. 
        writeLoop();
        
        // =============================================================
        // Post Transfer
        // =============================================================
        
        // join tasks: 
        try
        {
            readerTask.join();
        }
        catch (InterruptedException e)
        {
            throw new VlException("InterruptedException",e.getMessage(),e); 
        } 
        // after transfer make sure all streams are flushes and closed ! 
        
        long totalTime=System.currentTimeMillis()-start;
        
        // do not divide by zero:
        
        if (readTime<=0) 
            readTime=1; 
        if (writeTime<=0) 
            writeTime=1; 
        if (totalTime<=0) 
            totalTime=1; 
        
        // unit B/ms= kB/s
        debugPrintf("read speed=%d kB/s\n",(nrToTransfer/readTime)); 
        debugPrintf("write speed=%d kB/s\n",(nrToTransfer/writeTime));
        
        // bytes per second: 
        long totalSpeed=(long)(1000L*nrToTransfer/totalTime);
        
        String totalSpeedStr=Presentation.getDefault().speedString(totalSpeed); 
                
        debugPrintf("total speed=%s\n",totalSpeedStr);
        
        // Ugly:
        //if (this.transferInfo!=null)
        //    transferInfo.addLogText("Total stream transfer speed="+totalSpeedStr+"\n"); 
        
        if (readerTask.hasException())
        {
            Throwable e=readerTask.getException();
            
            if (e instanceof VlException) 
                throw ((VlException)e);
            else
                throw new VlIOException(e.getMessage(),e);
        }
    }
    
    public void setTaskMonitor(ITaskMonitor transfer)
    {
        this.transferInfo=transfer;
    }

    public int getReadChunkSize()
    {
        return this.readChunkSize; 
    }

    public int getWriteChunkSize() 
    {
        return this.writeChunkSize; 
    }

    public int getCopyBufferSize()
    {
        return this.bufferSize; 
    }

    public long getTotalWritten()
    {
        return this.nrWritten; 
    }

}
