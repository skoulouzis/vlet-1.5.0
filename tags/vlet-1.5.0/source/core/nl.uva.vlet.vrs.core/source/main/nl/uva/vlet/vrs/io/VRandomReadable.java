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
 * $Id: VRandomReadable.java,v 1.4 2011-04-18 12:00:33 ptdeboer Exp $  
 * $Date: 2011-04-18 12:00:33 $
 */ 
// source: 

package nl.uva.vlet.vrs.io;

import nl.uva.vlet.exception.VlException;

/**
 * Random Readable interface. 
 *  
 * @author P.T. de Boer 
 */
public interface VRandomReadable
{
	 /**
     * Reads <code>nrBytes</code> from file starting to read from 
     * <code>fileOffset</code>. Data is stored into the byte array
     * buffer[] starting at bufferOffset.
     *   
     * @throws VlException
     * @see java.io.RandomAccessFile#readBytes
     */
    public int readBytes(long fileOffset, byte buffer[], int bufferOffset,
            int nrBytes) throws VlException;
  
    /**
     * Return (maximum) length of random readable resource. 
     * fileOffset+nrBytes &lt; length of resource. 
     */ 
    long getLength() throws VlException;
}
