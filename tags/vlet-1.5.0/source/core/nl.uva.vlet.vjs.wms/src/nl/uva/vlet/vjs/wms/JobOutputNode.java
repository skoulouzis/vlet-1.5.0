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
 * $Id: JobOutput.java,v 1.2 2011-04-18 12:28:50 ptdeboer Exp $  
 * $Date: 2011-04-18 12:28:50 $
 */ 
// source: 

package nl.uva.vlet.vjs.wms;

import java.net.URISyntaxException;
import java.util.ArrayList;

import nl.uva.vlet.Global;
import nl.uva.vlet.exception.VlException;
import nl.uva.vlet.glite.OutputInfo;
import nl.uva.vlet.vrl.VRL;
import nl.uva.vlet.vrs.VCompositeNode;
import nl.uva.vlet.vrs.VNode;
import nl.uva.vlet.vrs.VRSContext;

public class JobOutputNode extends VCompositeNode
{
    private ArrayList<OutputInfo> outputInfo;

    public JobOutputNode(VRSContext context, VRL vrl, ArrayList<OutputInfo> outputInfo)
    {
        super(context, vrl);
        this.outputInfo = outputInfo;
    }

    @Override
    public String getType()
    {
        return "JobOutput";
    }

    @Override
    public VNode[] getNodes() throws VlException
    {
        VRSContext ctx = this.getVRSContext();
        VNode[] nodes = new VNode[outputInfo.size()];
        for (int i = 0; i < outputInfo.size(); i++)
        {

            try
            {
                nodes[i] = ctx.openLocation(new VRL(outputInfo.get(i).getFileURI()));
                debug("Getting files :" + outputInfo.get(i).getFileURI());
            }
            catch (URISyntaxException e)
            {
                e.printStackTrace();
            }
        }

        return nodes;
    }

    private void debug(String msg)
    {
        Global.errorPrintln(this, msg);

    }

    @Override
    public String[] getResourceTypes()
    {
        return null;
    }

}
