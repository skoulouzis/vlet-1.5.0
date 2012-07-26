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
 * $Id: VJS.java,v 1.5 2011-04-18 12:00:26 ptdeboer Exp $  
 * $Date: 2011-04-18 12:00:26 $
 */ 
// source: 

package nl.uva.vlet.vjs;


import nl.uva.vlet.vrs.VRS;

/** 
 * Static class for the Virtual Job System (VJS) 
 * 
 * @author Piter T. de Boer 
 */
public class VJS extends VRS
{
	public final static String TYPE_VJOB="Job";
		
	public final static String TYPE_VJOBGROUP="JobGroup"; 
	
	public final static String TYPE_VQUEUE="Queue";
	
	public final static String TYPE_VJOBMANAGER="JobManager";
	
	public final static String TYPE_INPUT_SANDBOX="InputSandbox";

	public final static String TYPE_OUTPUT_SANDBOX="OutputSandbox";


}

