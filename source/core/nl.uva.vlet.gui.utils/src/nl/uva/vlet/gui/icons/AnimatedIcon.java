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
 * $Id: AnimatedIcon.java,v 1.6 2011-04-18 12:27:12 ptdeboer Exp $  
 * $Date: 2011-04-18 12:27:12 $
 */ 
// source: 

package nl.uva.vlet.gui.icons;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.swing.Icon;

import nl.uva.vlet.data.IntegerHolder;
import nl.uva.vlet.gui.image.ImageSequence;

/**
 * AnimatedIcon wraps around a AnimatedImage and holds the state information which 
 * image from the ImageSequence needs to be painted.
 * <br> 
 * For an AnimatedIcon to animate,the AnimatedIcon needs to be registered at an IconAnimator.
 * @see IconAnimator 
 */
public class AnimatedIcon implements Icon, Serializable
{
    // === //
    private static final long serialVersionUID = -5056027227860438690L;

    // === //
    
    protected String description;   
    protected String sequenceName;
    protected int frameNr=0;
    protected int loopNr=0; 
    protected ImageSequence animImage;
    protected double animationSpeed=1.0;
    protected IconAnimator iconAnimator;
    protected boolean painted=false;
    private boolean hasStopped; 
    
    public AnimatedIcon(ImageSequence animImage)
    {
        this.animImage=animImage; 
    }
    
    /**
     * Create Single Image Icon, using for testing purposes only since a single image
     * icon can't really be animated. 
     */
    public AnimatedIcon(BufferedImage image)
    {
        this.animImage=new ImageSequence(image); 
    }

    @Override
    public int getIconHeight()
    {
        return animImage.getHeight(); 
    }

    @Override
    public int getIconWidth()
    {
        return animImage.getWidth(); 
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        // delegate to ImageSeqeunce: 
        animImage.paintImage(sequenceName,frameNr,c, g, x, y);
        this.painted=true;
        
        // 
        // Auto Register and bind to the Component so that animation will be started automatically
        // when for example this Icon is used in a JLabel or other standaard JComponent !
        // To avoid this, use setIconAnimator() 
        
        if (this.iconAnimator==null)
        {
           IconAnimator.getDefault().register(c,this);
        }
//        String text="#"+frameNr; 
//        g.setColor(Color.black); 
//        g.drawString(text, 0,getIconHeight());
    } 
    
    /** Whether a paint() has been issued */
    public boolean isPainted()
    {
        return this.painted; 
    }
    
    /**
     * Specify animation speed. 1.0 = standard, 2.0 =2x faster,etc
     * A negative value results in a reverse animation. 
     * 
     * @param speed
     */
    public void setAnimationSpeed(double speed)
    {
        boolean inverse=false; 
        
        if ( (speed>0) && (animationSpeed<0) 
            || (speed<0) && (animationSpeed>0) ) 
            inverse=true; 
        
        this.animationSpeed=speed;
        
        if (inverse)
            loopNr=animImage.getLoopCount()-loopNr;
    }
    
    public double getAnimationSpeed()
    {
        return this.animationSpeed; 
    }
    
    public void next()
    {
        IntegerHolder loopH=new IntegerHolder(loopNr); 
        IntegerHolder frameH=new IntegerHolder(frameNr); 
        
        boolean result=false; 
        
        if (animationSpeed>0) 
           result=animImage.calculateNextFrame(sequenceName,loopH,frameH,false);
        else if (animationSpeed<0)
           result=animImage.calculateNextFrame(sequenceName,loopH,frameH,true);
        else
           ;

        // no more frames! 
        if (result==false)
            this.hasStopped=true; 
        
        // update values 
        loopNr=loopH.value;
        frameNr=frameH.value;  
    }

    public void stop()
    {
        this.animationSpeed=0; 
        this.hasStopped=true; 
    }
    
    public void start()
    {
        this.animationSpeed=1;
        this.hasStopped=false; 
    }    
    
    public void reverse()
    {
        this.animationSpeed=-1; 
    }
    
    /**
     * Return wait time how long the current image needs to be displayed in milliseconds
     */ 
    public int getCurrentWaitTime()
    {
        // lowest integer time in milliseconds  
        return (int)Math.floor(animImage.getFrameDelay(frameNr)*Math.abs(animationSpeed));   
    }
    
    /** Unregisters this icon. */ 
    public void dispose()
    {
        // unregister if registered !
        if (this.iconAnimator!=null)
            this.iconAnimator.unregister(this);
        
        // Do NOT dispose ImageSequence, it might be shared!
        this.animImage=null;
         
    }

    /** Specify the Icon Animator for this icon. It can only have one. */ 
    protected void setIconAnimator(IconAnimator iconAnimator)
    {
       this.iconAnimator=iconAnimator;
    }
    
    /** Reset Frame Sequence. Doesn't stop animation */ 
    public void reset()
    {
        this.sequenceName=null; 
        this.frameNr=0;
    }
    
    public boolean hasStopped()
    {
        return this.hasStopped; 
    }

    public Image getCurrentImage()
    {
        return this.animImage.getFrameImage(frameNr); 
    }
    
}
