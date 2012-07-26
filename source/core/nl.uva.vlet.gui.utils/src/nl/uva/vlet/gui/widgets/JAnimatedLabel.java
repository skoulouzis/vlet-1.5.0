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
 * $Id: JAnimatedLabel.java,v 1.4 2011-04-18 12:27:13 ptdeboer Exp $  
 * $Date: 2011-04-18 12:27:13 $
 */ 
// source: 

package nl.uva.vlet.gui.widgets;

import javax.swing.Icon;
import javax.swing.JLabel;

import nl.uva.vlet.gui.icons.AnimatedIcon;
import nl.uva.vlet.gui.icons.IconAnimator;

/**
 * JLabel subclass with some convenience methods which supports AnimatedIcon as the Icon.
 * This way animated gifs, etc can be stopped, restarted or the animation speed can be changed. 
 *
 */
public class JAnimatedLabel extends JLabel
{
    private static final long serialVersionUID = -1199581037837264677L;
    
    public JAnimatedLabel(AnimatedIcon icon)
    {
        super(icon); // will call setIcon() ! 
    }
    
    public void setIcon(Icon icon)
    {
        super.setIcon(icon); 
    }
    
    /** Explicitly set AnimatedIcon */ 
    public void setAnimatedIcon(AnimatedIcon icon) 
    {
        setIcon(icon); 
    }
    
    public void start()
    {
        if (isAnimated())
            getAnimatedIcon().start(); 
    }
    
    public void stop()
    {
        if (isAnimated())
            getAnimatedIcon().stop(); 
    }
    
    public void reset()
    {
        if (isAnimated())
            getAnimatedIcon().reset(); 
    }
    
    public void setAnimationSpeed(double speed)
    {
        if (isAnimated())
            getAnimatedIcon().setAnimationSpeed(speed);  
    }
    
    public AnimatedIcon getAnimatedIcon()
    {
       Icon icon=this.getIcon(); 
       if (icon instanceof AnimatedIcon)
           return (AnimatedIcon)icon;
       
       return null;  
    }
    
    /** Returns true is the Icon is of AnimatedIcon class */ 
    public boolean isAnimated()
    {
        return (getAnimatedIcon()!=null); 
    }
    
    public void dispose()
    {
        AnimatedIcon icon=this.getAnimatedIcon();
 
        // unregister and dispose; 
        if (icon!=null)
        {
            IconAnimator.getDefault().unregister(icon);
            icon.dispose();
        }
    } 
    
}
