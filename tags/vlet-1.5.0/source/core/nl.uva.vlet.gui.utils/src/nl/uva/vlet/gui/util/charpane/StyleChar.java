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
 * $Id: StyleChar.java,v 1.3 2011-04-18 12:27:16 ptdeboer Exp $  
 * $Date: 2011-04-18 12:27:16 $
 */ 
// source: 

package nl.uva.vlet.gui.util.charpane;

import java.awt.Color;

/** 
 * Styled Character. Holder for the character buffer. 
 * Keeps char, style color charset (name!). 
 * 
 * @author P.T. de Boer
 */
public class StyleChar
{
    public static final int STYLE_NONE=0x0000; 
    
    public static final int STYLE_BOLD=0x0001<<1; 
    
    public static final int STYLE_ITALIC=0x0001<<2; 
    
    public static final int STYLE_INVERSE=0x0001<<3; 
    
    public static final int STYLE_UNDERSCORE=0x0001<<4;
    
    public static final int STYLE_UBERBOLD=0x0001<<05;

    public static final int STYLE_BLINK=0x0001<<06;

    public static final int STYLE_HIDDEN=0x0001<<07;

    // =====================================================
    // 
    // =====================================================
    
	int MAX_BYTES=8; 
	
    // utf-8 bytes pre allocate byte array for speed: 
    byte charBytes[]=new byte[MAX_BYTES]; 
    int numBytes=0; 
    int style=0;
    
    // color index -1 -> use default fore/back ground
    int foregroundColor=-1; 
    int backgroundColor=-1;;
    String charSet=null;  // NAMED charSet ! (if null inheret)

    boolean hasChanged=true; // when a redraw is needed

	int alpha=255; // 0=transparent,255=opaque 
    
    public void copyFrom(StyleChar schar)
    {
        setBytes(schar.charBytes,schar.numBytes); 
     
        this.style=schar.style; 
        this.backgroundColor=schar.backgroundColor;
        this.foregroundColor=schar.foregroundColor;
        this.charSet=schar.charSet;
        this.hasChanged=true; //
        this.alpha=schar.alpha; 
    }

    public void setBytes(byte[] bytes)
    {
    	System.arraycopy(bytes,0,this.charBytes,0,bytes.length); 
        numBytes=bytes.length;
    }

    public void setBytes(byte[] bytes,int len) 
    {
    	System.arraycopy(bytes,0,this.charBytes,0,len); 
        numBytes=len;
    }

    public void setChar(byte c) 
    {
        charBytes[0]=c; 
        numBytes=1; 
    }

    public void clear()
    {
        setChar((byte)' '); 
        style=0;
        foregroundColor=-1; 
        backgroundColor=-1;
        hasChanged=true; // needs redraw
        charSet=null;
        alpha=-1;
    }
    
    /** Alpha Blending ! 0-255 */ 
    public void setAlpha(int alpha)
    {
    	this.alpha=alpha; 
    }

	public boolean hasStyle(int styleFlag)
	{
		return(this.style&styleFlag)>0;
	}

	public void setDrawStyle(int drawStyle)
	{
		this.style=drawStyle; 
	}

	public boolean isItalic()
	{
		return hasStyle(STYLE_ITALIC); 
	}
	
	public boolean isBold()
	{
		return hasStyle(STYLE_BOLD); 
	}
	
	public boolean isInverse()
	{
		return hasStyle(STYLE_INVERSE); 
	}
	
	public boolean hasUnderscore()
	{
		return hasStyle(STYLE_UNDERSCORE); 
	}

	public boolean isUberBold()
	{
		return hasStyle(STYLE_UBERBOLD); 
	}
	
	
	// Calculate Unique key derived from Character attributes 
	
	private byte charUnique[]=new byte[20];  
	
	public byte[] characterUnique()
	{
		int index=0;
		for (;index<numBytes;index++)
			charUnique[index]=charBytes[index];
		
		for (;index<MAX_BYTES;index++)
			charUnique[index]=0;
		
		charUnique[index++]=(byte)(style%256); 
		charUnique[index++]=(byte)(1+alpha%256); 
		charUnique[index++]=(byte)((1+backgroundColor)%256); 
		charUnique[index++]=(byte)((1+foregroundColor)%256);
		
		long charsetHash=0;
		if (charSet!=null)
			charsetHash=this.charSet.hashCode();
	
		charUnique[index++]=(byte)(charsetHash%256); 
		charUnique[index++]=(byte)((charsetHash>>8)%256); 
		charUnique[index++]=(byte)((charsetHash>>16)%256); 
		charUnique[index++]=(byte)((charsetHash>>24)%256); 
		charUnique[index++]=(byte)((charsetHash>>32)%256); 
		charUnique[index++]=(byte)((charsetHash>>40)%256); 
		charUnique[index++]=(byte)((charsetHash>>48)%256); 
		charUnique[index++]=(byte)((charsetHash>>56)%256); 
		
		return charUnique;
	}

	/** Check against single byte char */ 
	public boolean isChar(char c) 
	{
		if (this.numBytes==1 && (charBytes[0]==c))
			return true; 
		return false; 
	}

}