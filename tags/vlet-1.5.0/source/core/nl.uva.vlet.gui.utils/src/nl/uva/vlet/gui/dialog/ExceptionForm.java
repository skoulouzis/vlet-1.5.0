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
 * $Id: ExceptionForm.java,v 1.5 2011-04-18 12:27:15 ptdeboer Exp $  
 * $Date: 2011-04-18 12:27:15 $
 */ 
// source: 

package nl.uva.vlet.gui.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import nl.uva.vlet.ClassLogger;
import nl.uva.vlet.Global;
import nl.uva.vlet.GlobalConfig;
import nl.uva.vlet.exception.VlException;
import nl.uva.vlet.gui.GuiSettings;
import nl.uva.vlet.gui.UIGlobal;
import nl.uva.vlet.gui.UIPlatform;

/**
 * Simple Exception Form 
 * Author P.T. de Boer 
 */
public class ExceptionForm extends javax.swing.JDialog implements ActionListener 
{
	private static final long serialVersionUID = -4959845172597559289L;

	public static void show(Exception e) 
	{
		show(null,e,false); 
	}
	
	public static void show(final Dialog parent,final Exception e,final boolean modal) 
	{
		// allow for asynchronous call, but dialog MUST be shown during GUI thread ! 
		// invoke show dialog during GUI thread: 
		
		if (UIGlobal.isGuiThread()==false)
		{
			////System.err.println("Calling swing invoke later"); 
			Runnable showtask=new Runnable()
			{
				public void run()
				{
					//System.err.println("after swing invoke later"); 
					
					show(parent,e,modal);
				}
			};
			
			SwingUtilities.invokeLater(showtask);
			return; 
		}
		
		ExceptionForm inst;
		
		if (modal==true) 
			 inst = new ExceptionForm(parent,e,modal);
		else
			 inst = new ExceptionForm(e);
		
        GuiSettings.setToOptimalWindowSize(inst);
        
        if (GlobalConfig.isApplet()==false)
        	inst.setAlwaysOnTop(true);
        
        inst.setModal(modal); // will halt after 'setVisible()'
 		inst.setVisible(true);
 		// next works only when not modal: 
        inst.requestFocus(); // first set focus to Dialog 
        inst.okButton.requestFocus(); // then set focus to OK 
	}

	public static void main(String args[])
	{
	    VlException e1=new VlException("Original Exception","This is the original Exception");
	    VlException e2=new VlException("Nested Exception","This is the longer Nested Exception were the message text, if to big to fit into one line"+
	            "will be wrapped around. The dailog should also be big enough to show all text. Newline:\n This is a new line.",e1);
	    show(e2);
	}
	
	// ========================================================================
	// 
	// ========================================================================

	private JTextArea errorText;
	private JPanel leftPane;
	private JTextArea debugTextArea;
	private JScrollPane debugScrollPane;
	private JTabbedPane mainTabPanel;
	private JTextField mainHeaderTextField;
	private JPanel errorPanel;
	private JLabel errorTextLabel;
	private JScrollPane errorScrollPane;
	private JPanel rightPane;
	private JPanel topPane;
	private JButton okButton;
	private JPanel buttonPanel;
	private String message="No message"; 

	private ExceptionForm(Exception e)
	{
	    super(); 
	    init(e); 
    }
		
	public void setDebugText(String txt)
	{
	    debugTextArea.setText(txt);
	    debugTextArea.setSize(debugTextArea.getPreferredSize());
	    // update viewport information:
	}
		
	public ExceptionForm(Dialog parent, Exception e,boolean modal) 
	{        
		// for chained modal dailogs, parent can NOT be NULL 
		super(parent,null,modal);  
		init(e);
	}
	
	private void init(Exception e)
	{
	    UIPlatform.getPlatform().getWindowRegistry().register(this);
		initGUI();
		String name=e.getClass().getSimpleName();
		
		// Depricated: VlException have names, should use class names
		if (e instanceof VlException)
		{
			name=((VlException)e).getName(); 
			// other VlExceptions ? 
		}
		
		this.mainHeaderTextField.setText(name);
		String txt=e.getMessage(); 
		if (txt==null)
		    txt="[No Text]";
		
		// Turn on AUTO WRAPPING ! 
		this.errorText.setLineWrap(true);
        this.errorText.setWrapStyleWord(true);     
        // Set of columns vs rows 

        this.errorText.setText(txt);
        //this.errorText.setColumns((int)(Math.round(Math.sqrt(txt.length())))); 

        // === Debug Text: 
        // get the whole bubs:
        setDebugText(VlException.getChainedStackTraceText(e));  
        
        // update to the max!
        // Dimension pref=this.errorText.getPreferredSize();
        
        // can happen when an error occures at init time, be robuust:
        
//        if (UIGlobal.getGuiSettings()!=null) 
//        {
//           if (pref.width>UIGlobal.getGuiSettings().max_dialog_text_width)
//              pref.width=UIGlobal.getGuiSettings().max_dialog_text_width;
//        }
//        
//        this.errorText.setSize(pref);
        
        // update panels: 
       // this.errorPanel.setSize(this.errorPanel.getPreferredSize());
        
        // center in frame: 
        //this.setLocation(frame.getLocation().x+frame.getSize().width/2-this.getSize().width/2,
        //        frame.getLocation().y+frame.getSize().height/2-this.getSize().height/2);
        
       // GuiSettings.setToOptimalWindowSize(this); 
           
        //  show me 
		//this.setVisible(true);
	}
	
	private void initGUI() 
    {
		try 
        {
			BorderLayout thisLayout = new BorderLayout();
			this.getContentPane().setLayout(thisLayout);
            {
                topPane = new JPanel();
                this.getContentPane().add(topPane, BorderLayout.NORTH);
                //topPane.setMaximumSize(new java.awt.Dimension(8, 8));
               // topPane.setMinimumSize(new java.awt.Dimension(8, 8));
               //topPane.setPreferredSize(new java.awt.Dimension(282, 22));
                {
                    errorTextLabel = new JLabel();
                    topPane.add(errorTextLabel);
                    errorTextLabel.setText("VlException");
                }

            }
			{
				leftPane = new JPanel();
				this.getContentPane().add(leftPane, BorderLayout.WEST);
				//leftPane.setPreferredSize(new java.awt.Dimension(9, 64));
				//leftPane.setMaximumSize(new java.awt.Dimension(8, 8));
			}
            {
                rightPane = new JPanel();
                this.getContentPane().add(rightPane, BorderLayout.EAST);
                //rightPane.setMaximumSize(new java.awt.Dimension(8, 8));
                //rightPane.setMinimumSize(new java.awt.Dimension(8, 8));
                // rightPane.setPreferredSize(new java.awt.Dimension(10, 64));
            }
            {
                buttonPanel = new JPanel();
                this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
                //buttonPanel.setPreferredSize(new java.awt.Dimension(282, 26));
                {
                    okButton = new JButton();
                    buttonPanel.add(okButton);
                    okButton.setText("Close");
                    //okButton.setPreferredSize(new java.awt.Dimension(92, 18));
                    okButton.addActionListener(this);
                }
            }
            {
                mainTabPanel = new JTabbedPane();
                this.getContentPane().add(mainTabPanel);
                //mainTabPanel.setPreferredSize(new java.awt.Dimension(327, 131));
                {
                    errorPanel = new JPanel();
                    mainTabPanel.addTab("Error", null, errorPanel, null);
                    BorderLayout mainPanelLayout = new BorderLayout();
                    errorPanel.setLayout(mainPanelLayout);
                    //errorPanel.setPreferredSize(new java.awt.Dimension(303, 89));
                    errorPanel.setToolTipText("The error which occured");
                    {
                        mainHeaderTextField = new JTextField();
                        errorPanel.add(mainHeaderTextField, BorderLayout.NORTH);
                        mainHeaderTextField.setText("Text");
                        mainHeaderTextField.setEditable(false);
                        mainHeaderTextField.setFocusable(false);
                        mainHeaderTextField.setAutoscrolls(false);
                        mainHeaderTextField
                            .setToolTipText("The Error which occured");
                        //mainHeaderTextField.setPreferredSize(new java.awt.Dimension(303, 24));
                        mainHeaderTextField.setFont(new java.awt.Font("Dialog",1,14));
                    }
                    {
                        errorScrollPane = new JScrollPane();
                        errorPanel.add(errorScrollPane, BorderLayout.CENTER);
                        errorScrollPane.setAutoscrolls(true);
                        errorScrollPane.getVerticalScrollBar().setAutoscrolls(true);
                        errorScrollPane.getHorizontalScrollBar().setAutoscrolls(true);
                        {
                            errorText = new JTextArea();
                            errorScrollPane.setViewportView(errorText);
                            errorText.setText(message);
                            //errorText.setSize(getPreferredSize()); 
                            errorText.setEditable(false);
                            errorText.setToolTipText("Extra information provided by the implementation.");
                            errorText.setWrapStyleWord(true);
                             
                        }
                        {
                        }
                    }
                }
                {
                    debugScrollPane = new JScrollPane();
                    mainTabPanel.addTab("debug", null, debugScrollPane, null);
                    debugScrollPane.setToolTipText("Provides debug information");
                    {
                        debugTextArea = new JTextArea();
                        debugScrollPane.setViewportView(debugTextArea);
                        debugTextArea.setText("(No debug info)");
                        debugTextArea.setToolTipText("Debug information provided by the exception.");
                        //debugTextArea.setPreferredSize(new java.awt.Dimension(296,42));
                    }
                }
            }
			//this.setSize(337, 194);
		} 
		catch (Exception e) 
		{
			Global.logException(ClassLogger.ERROR,this,e,"Exception during initGUI!\n"); 
		}
	}

	public void actionPerformed(ActionEvent arg0)
	{
		this.dispose(); 
	}

	public static void show(Throwable e)
	{
		if (e instanceof Exception)
		{
			show((Exception)e); 
		}
	}
}
