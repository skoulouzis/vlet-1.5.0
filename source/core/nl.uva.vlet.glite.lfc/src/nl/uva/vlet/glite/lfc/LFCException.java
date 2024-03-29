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
 * $Id: LFCException.java,v 1.2 2011-04-18 12:30:40 ptdeboer Exp $  
 * $Date: 2011-04-18 12:30:40 $
 */ 
// source: 

package nl.uva.vlet.glite.lfc;



/**
 * LFCException.
 * 
 * @author Piter T. de Boer, Spiros Koulouzis  
 */

public class LFCException extends Exception
{
	/** generated id */ 
	private static final long serialVersionUID = 7875496601935511425L;
	
	private int errorCode=0; 
	
	public LFCException(String message)
	{
		super(message);  
	}

	public LFCException(String message, Exception ex) 
	{
		super(message,ex); 
	}
	
	public LFCException(String message, int errornr, Exception ex) 
	{
		super(message+"\n"+LFCError.getMessage(errornr),ex);
		this.errorCode=errornr; 
	}
	
	public LFCException(String message, int errornr) 
	{
		super(message+"\n"+LFCError.getMessage(errornr));
		this.errorCode=errornr; 
	}

	/** Returns LFC Error Code */ 
	public int getErrorCode()
	{
		return this.errorCode;
	}

	public void setErrorCode(int errorCode)
	{
		this.errorCode = errorCode;
	}
	
	/** Returns LFC Error Message */ 
	public String getErrorString()
	{
		return LFCError.getMessage(errorCode); 
	}
	
}