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
 * $Id: ImageUtil.java,v 1.4 2011-04-18 12:27:15 ptdeboer Exp $  
 * $Date: 2011-04-18 12:27:15 $
 */ 
// source: 

package nl.uva.vlet.gui.image;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import nl.uva.vlet.Global;
import nl.uva.vlet.gui.util.gif.NS2GifDecoder;
import nl.uva.vlet.util.ResourceLoader;

public class ImageUtil
{
    public static class ImageError extends Error
    {
        public ImageError(String message)
        {
            super(message); 
        }

        private static final long serialVersionUID = -554788643749026422L;
    }

//    /**
//     * Loads gif icon and decodes it using Jai classed.
//     * Decodes the GIF image sequence but doesn't retain frame wait times! 
//     * <p>  
//     * This method does NOT do any caching.  
//     */  
//    public static ImageSequence jaiLoadAnimatedGif(URL url) throws Exception
//    {
//        // Use Rasters. 
//        // 
//        
//        byte bytes[]=ResourceLoader.getDefault().getBytes(url.toString()); 
//        infoPrintf("Read #%d bytes\n",bytes.length);
//        
//        SeekableStream sStream=new ByteArraySeekableStream(bytes,0,bytes.length); 
//        
//        //GifImageDecoder gifDec=null; 
//        
//        GIFImageDecoder gifDec = new GIFImageDecoder(sStream, null);
//        
//        // Get First:
//        //Raster raster = gifDec.decodeAsRaster();
//        // is null 
//        //ImageDecodeParam param = gifDec.getParam();
//        //infoPrintf("param=%s\n",param); 
//        
//        // ===============================
//        // Image 0: Decode first image: Must Exist!
//        // ===============================
//        int numPages=0; // gifDec.getNumPages();
//        List<BufferedImage> images=new ArrayList<BufferedImage>(); 
//        RenderedImage renImg = gifDec.decodeAsRenderedImage();
//        ImageDecodeParam param = gifDec.getParam(); 
//        
//        infoPrintf(" - param= %s\n",param); 
//        
//        // Can't determine nr of images, so just try to decode as much as possible 
//        try
//        {
//            while(true)
//            {
//                // ================
//                // Store Picture   
//                // ================
//                Raster data=renImg.getData(); 
//                Rectangle b = data.getBounds();
//                infoPrintf("--- raster #%d ---\n",numPages); 
//                infoPrintf("raster bounds    = %d,%d,%d,%d \n",b.x,b.y,b.width,b.height);
//                
//                String[] names = renImg.getPropertyNames();
//                
//                if ((names==null) || (names.length<=0)) 
//                    infoPrintf(" - NULL properties \n"); 
//                else
//                    for (String name:names)
//                    {
//                        infoPrintf(" prop: '%10s'=%s\n",name,""); 
//                    }
//                infoPrintf(" - renImg = %s\n",data); 
//                infoPrintf(" - data   = %s\n",data); 
//                 infoPrintf(" - parent = %s\n",data.getParent()); 
//                
//                ColorModel cm = renImg.getColorModel(); 
//               
//                BufferedImage bufImg=new BufferedImage(cm,(WritableRaster)data,false,null);
//                images.add(bufImg);
//                // ==============
//                // Next -> throws exception is no more images 
//                // ==============
//                numPages++;
//                renImg=gifDec.decodeAsRenderedImage(numPages);
//                
//                // ??? Where is the header information ? 
//            }
//        }
//        catch (Exception e)
//        {
//            ;// exception -> no more left 
//        }
//        
//        return new ImageSequence(images); 
//    }

	public static ImageSequence loadAnimatedGif(ClassLoader optClassLoader,String urlstr) throws Exception
	{
		return loadAnimatedGif(ResourceLoader.getDefault().resolveUrl(optClassLoader, urlstr)); 
	}

    /** 
     * Loads legacy Netscape 2.0 animated Gificon and decodes it which seems to be 
     * the animated gif standard nowdays! 
     * This method does NOT do any caching.  
     */  
    public static ImageSequence loadAnimatedGif(URL url) throws Exception
    {
        NS2GifDecoder gifDec = new NS2GifDecoder(); 
        
        gifDec.read(url);
        Dimension size = gifDec.getFrameSize(); 
        int num=gifDec.getFrameCount(); 
        
        int loopc=gifDec.getLoopCount(); 
        
        infoPrintf(" --- header ---\n");
        infoPrintf("  - num frames = %d\n",num);  
        infoPrintf("  - loop count = %dn",loopc);  
        infoPrintf("  - frame size = %dx%d\n",size.width,size.height);  
    
        List<BufferedImage> images=new ArrayList<BufferedImage>(); 
        List<ImageSequence.FrameInfo> infos=new ArrayList<ImageSequence.FrameInfo>();
        // get frames; 
        for (int i=0;i<num;i++)
        {
            infoPrintf(" --- frame #%d ---\n",i);
            
            BufferedImage frame = gifDec.getFrame(i);
            int delay=gifDec.getDelay(i); 
            infoPrintf("  - delay = %d\n",delay);
            images.add(frame);
            // 1st = 1st image,etc. 
            //inf.imageNr=i;
            //inf.waitTimeMs=delay; 
    
            ImageSequence.FrameInfo inf=new ImageSequence.FrameInfo(i,delay);
            infos.add(inf); 
        }   
        
        ImageSequence anim=new ImageSequence(images,infos,loopc);
        return anim; 
    }

    private static void infoPrintf(String format,Object... args)
    {
        Global.errorPrintf(ImageUtil.class, format, args); 
    }

    public static BufferedImage convertToBufferedImage(Image image)
    {
        if (image instanceof BufferedImage)
            return (BufferedImage)image; 
        
        int width=image.getWidth(null); 
        int height=image.getHeight(null); 
        
        BufferedImage newImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        Graphics2D imageGraphics = newImage.createGraphics();
        
        imageGraphics.drawImage(image, 0,0,null);
        
        return newImage; 
    }
    
}
