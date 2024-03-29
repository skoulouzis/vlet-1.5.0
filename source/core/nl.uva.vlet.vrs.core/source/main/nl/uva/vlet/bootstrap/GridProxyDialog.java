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
 * $Id: GridProxyDialog.java,v 1.1 2011-11-25 16:15:48 ptdeboer Exp $  
 * $Date: 2011-11-25 16:15:48 $
 */ 
// source: 

package nl.uva.vlet.bootstrap;


/** 
 * Wrapper class to bootstrap the VBrowser. 
 * The main method calls the BootStrap class 
 * with the configuration needed to start the VBrowser. 
 * Use this class as the main class in the manifest to create a 
 * self executing jar to start the VBrowser. 
 * Only the Bootstrapper class has to be present in the jar file. 
 * The rest is added automagically. 
 * 
 * @author Piter T. de Boer/Piter.NL
 */
public class GridProxyDialog 
{
	public static void main(String[] args) 
    {
		Bootstrapper boot=new Bootstrapper();
		
		try 
		{
           // boot.launch(nl.uva.vlet.gui.startVBrowser,args); 
            boot.launch("nl.uva.vlet.gui.util.proxy.GridProxyDialog",args);
		}
		catch (Exception e) 
		{
			  System.out.println("***Error: Exception:" + e);
	          e.printStackTrace();
		} 
		
    }
	
}
