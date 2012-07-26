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
 * $Id: testExceptionForm.java,v 1.3 2011-04-18 12:27:12 ptdeboer Exp $  
 * $Date: 2011-04-18 12:27:12 $
 */ 
// source: 

package tests;

import nl.uva.vlet.exception.ResourceReadAccessDeniedException;
import nl.uva.vlet.exception.ResourceAlreadyExistsException;
import nl.uva.vlet.exception.ResourceToBigException;
import nl.uva.vlet.exception.VlException;
import nl.uva.vlet.gui.dialog.ExceptionForm;


public class testExceptionForm
{

    public static void main(String[] args)
    {
        // tests asynchronous text view 
        
        ExceptionForm.show(new ResourceReadAccessDeniedException("Acces denied"));
     
           
        // tests asynchronous mainText view 
        VlException e1=new ResourceReadAccessDeniedException(" Test 1");
        VlException e2=new ResourceAlreadyExistsException(" Test 2");
            
        ExceptionForm.show(e1);
        ExceptionForm.show(e2);

            try 
            {
                int i=0/0; 
            }
            catch (Exception e)
            {
                ExceptionForm.show(new VlException("Oooops",e));
            }
            
            String str=""; 
            
            for (int i=0;i<100;i++)
                str=str+"A lot of debug information. \n";
            
            ExceptionForm.show(new ResourceToBigException(str)); 
        
    }

}