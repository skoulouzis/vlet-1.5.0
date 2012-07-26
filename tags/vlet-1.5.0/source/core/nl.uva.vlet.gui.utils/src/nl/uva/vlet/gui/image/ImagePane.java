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
 * $Id: ImagePane.java,v 1.2 2011-04-18 12:27:15 ptdeboer Exp $  
 * $Date: 2011-04-18 12:27:15 $
 */ 
// source: 

package nl.uva.vlet.gui.image;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import javax.swing.JComponent;

import nl.uva.vlet.Global;
import nl.uva.vlet.exception.ResourceException;
import nl.uva.vlet.exception.VlException;
import nl.uva.vlet.gui.UIGlobal;
import nl.uva.vlet.vrl.VRL;


/** 
 * ImagePane which handles asynchronous imageUpdates. 
 * After an image is loaded the component size is updated to
 * match the new size. 
 * This because when updating an image from a remote resource, 
 * the size might not be known yet as long as the AWT toolkit is 
 * 'decoding;  the image bytes.  
 */ 
public class ImagePane extends JComponent
{
	// wait for an image to be updated 
	public static class ImageWaiter implements ImageObserver
	{
		Image image=null; 

		int newWidth=0; 
		int newHeight=0;

		boolean allBits=false; 
		boolean error=false; 
		
		public ImageWaiter(Image _image)
		{
			image=_image;
		}

		public void setImage(Image _image)
		{
			this.image=_image; 
			allBits=false; 
		}

		public synchronized boolean imageUpdate(Image img, int infoFlags, int x, int y,
				int width, int height)
		{
			debug(">>> ImageWaiter: flags="+infoFlags); 

			
			if ((infoFlags & ImageObserver.ERROR)>0) 
			{
				this.error=true; 
				
				synchronized(this)
				{
					// wakeup ! 
					this.notifyAll();
				}

				return false; 
			}
			
			boolean done=false; 

			if (width>0) 
				newWidth=width; 

			if (height>0) 
				newHeight=height; 

			debug(">>> ImageWaiter: update"+width+","+height+" => "+newWidth+","+newHeight);

			if (allBits==false)
				this.allBits=((infoFlags & ImageObserver.ALLBITS)>0); 

			if (allBits) 
			{
				if ((newWidth>0) && (newHeight>0)) 
				{
					synchronized(this)
					{
						// wakeup ! 
						this.notifyAll();
					}

					done=true; 
				}
			}

			// new update needed ? 
			return (done==false); 
		}

		/** waits for size to be known 
		 * @throws ResourceException */
		public void waitForCompletion(boolean waitForAllBits) throws ResourceException
		{
			debug(">>> ImageWaiter: waitForCompletion()");

			boolean wait=true; 

			while(wait==true)
			{
				// get size and trigger imageUpdate ! 

				this.newHeight=image.getHeight(this); 
				this.newWidth=image.getWidth(this);
			
				if (error==true)
				{
					throw new ResourceException("Image Loading failed"); 
				}
				
				if ((newHeight<=0) || (newWidth<=0))
				{
					wait=true; 
				}
				else 
				{
					// size is known now, wait for all bits ? 
					if (waitForAllBits==false)
					{
						// don't wait 
						wait=false;
					}
					else
					{
						if (allBits==true)
							wait=false;
						else
							wait=true; 
					}
				}

				// image not done yet:
				if (wait)
				{
					try
					{
						synchronized(this)
						{
							// wait 1s. check again:
							this.wait(1000);
						}
					}
					catch (InterruptedException e)
					{
						Global.errorPrintln(this,"Interrupted:"+e); 
						Global.errorPrintStacktrace(e);
					}
				}
			}
			debug("<<< ImageWaiter: waitForCompletion():DONE!");
		}
		
		private void debug(String msg)
		{
			Global.debugPrintln(this,msg); 
		}
	} // imageWaiters
	
	// needed by swing 
	private static final long serialVersionUID = 4745630397251453021L;

	// ===============================================================
	// Instance :
	// ================================================================
	
	/** The Image */ 
	private Image image=null;
	
	/** Default Background Color */ 
	Color bgcolor=Color.GRAY;
	
	/** Width of Image, might not be same as this component's width */ 
	private int imageWidth=-1;
	
	/** Height of Image, might not be same as this component's height*/ 
	private int imageHeight=-1;

	public ImagePane(Image source) throws ResourceException
	{
		init(); 
		this.setImage(source,false); 
	}

	public ImagePane()
	{
		init(); 
	}
	
	private void init()
	{
		
	}

	/**
	 * Set new image, if waitForCompletion==true, this method
	 * will only return when the whole image loaded and ready. 
	 * 
	 * @param bytes
	 * @param waitForCompletion Set to true to block and wait for the image 
	 *                          to be complete.
	 * @throws ResourceException 
	 */
	public void setImage(byte bytes[],boolean waitForCompletion) throws ResourceException
	{
		Toolkit tk = Toolkit.getDefaultToolkit();
		setImage(tk.createImage(bytes),waitForCompletion);
	}

	/**
	 * Set new image, if waitForCompletion==true, this method
	 * will only return when the whole image is ready. 
	 * 
	 * @param image Image to be drawn. 
	 * @param waitForCompletion Set to true to block and wait for the image 
	 *                          to be complete.
	 * @throws ResourceException 
	 */
	public void setImage(Image newImage, boolean waitForCompletion) throws ResourceException
	{
		if ((newImage!=null) && (waitForCompletion)) 
		{
				// Since createImage is asynchronous, the image size
				// might not be know. Wait until updateImage provides
				// the right image size. 
				// Specify waitForallBits==true if the complete image needs to be available. 
				// Set to false if only the correct size is needed. 
				
				Global.debugPrintln(this,"Waiting for image completion:");
				
				ImageWaiter waiter=new ImageWaiter(newImage); 

				waiter.waitForCompletion(false); 
				
				debug("Done: Waiting for image completion"); 
		}

		// image complete: swap: 
		// swap 
		// swap 		
		if (this.image!=null)
		{
			image.flush(); 
			image=null; 
		}
		
		this.image=newImage; 
		
		if (this.image==null) 
			return; 
		
		int tmpHeight=image.getHeight(this); // might return -1!
		int tmpWidth=image.getWidth(this);  // might return -1!

		// be carefull due to ascynchronous nature of updateImage
		// height events COULD happen during this code:

		Global.debugPrintln(this,"this height="+imageHeight); 
		Global.debugPrintln(this,"this width="+imageWidth);
		Global.debugPrintln(this,"new height="+tmpHeight); 
		Global.debugPrintln(this,"new width="+tmpWidth);

		//
		// if size is not know yet, imageUpdate will do the trick 
		// 
		if ((tmpHeight>0) && (tmpWidth>0))
		{
			this.imageHeight=tmpHeight; 
			this.imageWidth=tmpWidth; 
			updateSize(imageWidth,imageHeight);
		}
	}

	@Override
	public void setSize(int w, int h)
	{
		// setSize is callend by parent container to set 
		// actual size of component. This is NOT the image size !  
		// do optional checking here: 
		debug("setSize:"+w+","+h);
		// call component setSize
		// AutoResize => Update Image Size ! 
		super.setSize(w, h);
	}
	
	// return actual size of image 
	@Override
	public Dimension getPreferredSize()
	{
		if ((this.imageHeight<=0) || (this.imageWidth<=0))
			return super.getSize(); 
		
		return new Dimension(this.imageWidth,this.imageHeight);
	}
	
	/**
	 * Return size of actual image. This can differ from 
	 * current components size.
	 */
	public Dimension getImageSize()
	{
		return new Dimension(this.imageWidth,this.imageHeight); 
	}
	
	// returns Component size:  
	public Dimension getSize()
	{
	    return super.getSize(); 
	}
	public Dimension getMaximumSize()
	{
		if ((this.imageHeight<=0) || (this.imageWidth<=0))
			return super.getSize(); 

		return getPreferredSize(); 
	}
	
	/**
	 * Since this is a lightweight component we should handle 
	 * ImageObserver events ourselfs. 
	 * After an (background) update: perform the update during swing event thread
	 */ 
	protected void updateSize(final int newWidth,final int newHeight)
	{
		this.imageWidth=newWidth; 
		this.imageHeight=newHeight; 

		Global.debugPrintln(this,"async update size="+newWidth+","+newHeight);
		// setSize(this.width,this.height);
		
		// methods go background already if not event thread !  
		revalidate(); 
		repaint(); // request repaint! 

		/* Not Needed for revalidate&repaint
		Runnable runT=new Runnable()
		{
			public void run()
			{
				//setSize(newWidth,newHeight);
				revalidate(); 
				repaint(); // request repaint! 
			}
		};

		SwingUtilities.invokeLater(runT);
		*/
	}


	// Interface: ImageObserver
	// 
	// Must handle imageUpdate events as image is 'processed'
	// in another thread. ! 

	public boolean imageUpdate(Image img,
			int infoFlags,
			int x,
			int y,
			int newWidth,
			int newHeight)
	{
		//Let super method to the work (usually a (re)paint):
		boolean val=super.imageUpdate(img,infoFlags,x,y,newWidth,newHeight); 
		// check 
		boolean updateSize=false;

		if ((infoFlags&ImageObserver.HEIGHT)>0)
		{
			if (newWidth>0)
			{
				this.imageWidth=newWidth; 
				Global.debugPrintln("ImagePane","NEW height="+imageHeight);
				updateSize=true;
			}
		}

		if ((infoFlags&ImageObserver.WIDTH)>0)
		{
			if (newHeight>0)
			{
				this.imageHeight=newHeight; 

				Global.debugPrintln("ImagePane","NEW width="+imageWidth);
				updateSize=true;
			}
		}

		//
		// don't check for ALL_BITS: drawing will be done in background
		// 
		if ((updateSize) && (this.imageWidth>0) && (this.imageHeight>0))
		{
			updateSize(this.imageWidth,this.imageHeight); 
		}

		return val;
	}

	/* Override update for speed ! 
    public void update(Graphics g)
    {
        paint(g); 
    }*/

	public void paint(Graphics g)
	{
		Image targetImage=this.image;

		// viewed imaged not ready ? 
		//if ( (targetImage==null) || (targetImage.getHeight(null)<=0) || (targetImage.getWidth(null)<=0))
		//	targetImage=this.orgImage;

		//if (targetImage==null)
		//	targetImage=this.orgImage;
		
		// Do NOT add X,Y Offset, parent container does this already: 
		{
		  int x=this.getLocation().x;
		  int y=this.getLocation().y;
		  debug("paint() offset x,y="+x+","+y);
		}
		
		g.drawImage(targetImage,0,0,bgcolor,this);
		//Dimension dim = this.getPreferredSize(); 

		//g.drawImage(image,x,y,dim.width,dim.height,bgcolor,this);
	}


	public void dispose()
	{
		if (this.image!=null)
		{
			this.image.flush(); 
			this.image=null;
		} 
	}

	public void loadImage(VRL location, boolean wait) throws VlException
	{
	 	Image img = UIGlobal.getResourceLoader().getImage(location);
		
		if (img==null)
			throw new ResourceException("Image loader returned NULL for:"+location); 
		
		setImage(img,wait);
	}
	
	private void debug(String msg)
	{
		Global.debugPrintln(this,msg); 
	}

	public Image getImage()
	{
		return this.image; 
	}	
}
