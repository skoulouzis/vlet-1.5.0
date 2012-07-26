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
 * $Id: EmulatorKeyMapper.java,v 1.3 2011-04-18 12:27:10 ptdeboer Exp $  
 * $Date: 2011-04-18 12:27:10 $
 */ 
// source: 

package nl.uva.vlet.util.vlterm;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import nl.uva.vlet.ClassLogger;
import nl.uva.vlet.Global;

/**
 * Emulator KeyMapper.
 *  
 * Transform AWT KeyEvents to Emulator Codes 
 * 
 * @author Piter T. de Boer
 *
 */
public class EmulatorKeyMapper implements TerminalKeyListener, KeyListener
{
	Emulator emulator=null;

	private char lastPressed;
	
	public  EmulatorKeyMapper(Emulator emulator)
	{
		this.emulator=emulator;
	}


	public void keyPressed(KeyEvent e)
	{
		int keycode = e.getKeyCode();
		char keychar=e.getKeyChar();
		
		lastPressed=keychar;
		// use customizable keystrings:
		String keystr=KeyEvent.getKeyText(keycode);

		int mods=e.getModifiersEx();

		// keystr matches Token String Reprentation: 
		// (OPtionally prefix with "VT52_"/"VT100"  for VT52/VT100 codes:

		debugPrintf("keyPressed():Input => mods=%d, keycode=%d, char=%s, keystr=%s\n",
				mods,keycode,""+keychar,keystr); 
	
		if (emulator==null)
		{
			debugPrintf("*** NO EMULATOR PRESENT ***\n");
			return; 
		}
		
		boolean ctrl=(mods&KeyEvent.CTRL_DOWN_MASK)>0; 
		boolean alt=(mods&KeyEvent.ALT_DOWN_MASK)>0;
		try
		{

			if (alt)
			{
				if (keycode==KeyEvent.VK_F9)
					emulator.signalHalt(true); 

				if (keycode==KeyEvent.VK_F10)
					emulator.step(); 

				if (keycode==KeyEvent.VK_F11)
					emulator.signalHalt(false);

				if (keycode==KeyEvent.VK_F12)
					emulator.signalTerminate(); 
			}
			
            if (ctrl)
			{
				// CTRL-A to CTRL-Z
				
				debugPrintf("CTRL-%s\n",""+(char)keycode);
				// special CTRL-SPACE = send NUL 
				
				if ((keycode>=KeyEvent.VK_A) && (keycode<=KeyEvent.VK_Z))
				{
					emulator.send((byte)(keycode-KeyEvent.VK_A+1));
					return;
				}
				
				// Special Characters! 
				switch (keycode)
				{
				    case '@': 
				    case ' ': 
				    	emulator.send((byte)0);
				    	break; 
					case '[': 
						emulator.send((byte)VT10xEmulatorDefs.CTRL_ESC);
						break;
					default: 
				}
				
				if (keycode==KeyEvent.VK_PAGE_UP)
				{
					
				}
				else if (keycode==KeyEvent.VK_PAGE_DOWN)
				{
					
				}
			}
			else  if (keycode==KeyEvent.VK_DELETE)
				emulator.send(emulator.getKeyCode("DELETE")); 
			else if (keycode==KeyEvent.VK_PAGE_UP)
				emulator.send(emulator.getKeyCode("PAGE_UP")); 
			else if (keycode==KeyEvent.VK_PAGE_DOWN)
				emulator.send(emulator.getKeyCode("PAGE_DOWN")); 
			else if (keycode==KeyEvent.VK_END)
				emulator.send(emulator.getKeyCode("END")); 
			else
			{
				byte[] code = this.emulator.getKeyCode(keystr); 

				if (code != null)
				{
					emulator.send(code); 
					return;
				}
				else  if ((keychar & 0xff00) == 0)
				{
					emulator.send((byte)keychar);
				}
			}

		}
		catch (Exception ee)
		{
			Global.logException(ClassLogger.ERROR,this,ee,"Emulator Exception\n"); 
		}
		e.consume(); 
	}



	public void keyReleased(KeyEvent e) 
	{

	}
	
    private void debugPrintf(String format,Object... args)
    {
        Global.debugPrintf(this,format,args);
    }
    

	public void keyTyped(KeyEvent e)
	{
		debugPrintf("Event:%s\n",e);
		
		int keycode = e.getKeyCode();
		char keychar=e.getKeyChar(); 
		// use customizable keystrings:
		String keystr=KeyEvent.getKeyText(keycode);

		int mods=e.getModifiersEx();

		debugPrintf("keyTyped():Input => mods=%d, keycode=%d, char=%s, keystr=%s\n",
				mods,keycode,""+keychar,keystr); 
		 
		// International Keymappings: 
		// if a key is "typed" but not pressed, this was a combo character, 
		// like "e =>ë, or 'u=>ú 
		
		if (lastPressed==keychar)
			return; 
		
		keystr=""+keychar;
		
		// try to send keychar as UTF string ! 
		try
		{
			try 
			{
				emulator.send(keystr.getBytes(emulator.getEncoding()));
			}
			catch (UnsupportedEncodingException e1)
			{
				emulator.send(keystr.getBytes());
			}
		}
		catch (IOException ex) 
		{
			Global.logException(ClassLogger.ERROR,this,ex,"emulator.send() exception\n"); 
		}
		 
	}
  
}
