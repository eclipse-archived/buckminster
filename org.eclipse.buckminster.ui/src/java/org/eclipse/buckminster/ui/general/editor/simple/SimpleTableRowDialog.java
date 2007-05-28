/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.general.editor.simple;

import java.util.Arrays;

import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.HelpEvent;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * Row editor of general table editor TableEditor
 * 
 * @author Karel Brezina
 */
public class SimpleTableRowDialog<T> extends TitleAreaDialog
{
	private final ISimpleTable<T> m_table;
	
	private final Image m_windowImage;
	
	private final String m_windowTitle;
	
	private final Image m_wizardImage;

	private final String m_helpURL;
	
	private final int m_row;
	
	private final boolean m_newRow;
	
	private IWidgetin[] m_widgetins;

	private Button m_okButton;

	/**
	 * Creates row editor
	 * 
	 * @param parent parent shell
	 * @param windowImage window icon
	 * @param windowTitle window title
	 * @param wizardImage wizard image
	 * @param helpURL URL to help info. If not null - help link or icon is displayed for accessing help
	 * @param table wrapped editor data
	 * @param row table row number that will be edited or -1 for new row
	 */
	public SimpleTableRowDialog(Shell parent, Image windowImage, String windowTitle, Image wizardImage, String helpURL, ISimpleTable<T> table, int row)
	{
		super(parent);
		
		m_table = table;
		m_windowImage = windowImage;
		m_windowTitle = windowTitle;
		m_wizardImage = wizardImage;
		m_helpURL = helpURL;
		m_row = row;
		m_newRow = (row == -1);
		m_widgetins = new IWidgetin[table.getColumns()];
	}

	@Override
	public boolean isHelpAvailable()
    {
    	return m_helpURL != null;	    	
    }
	
    @Override
	protected Control createHelpControl(Composite parent)
	{
		Control helpControl = super.createHelpControl(parent);
		helpControl.addHelpListener(new HelpListener(){

			public void helpRequested(HelpEvent e)
			{
				if(m_helpURL != null)
				{
					Program.launch(m_helpURL);
				}
			}});
		
		return helpControl;
	}
	
	@Override
	protected void configureShell(Shell newShell)
	{
		super.configureShell(newShell);
		newShell.setText(m_windowTitle);
		
		if(m_windowImage != null)
		{
			newShell.setImage(m_windowImage);
		}
	}

	@Override
	protected Control createContents(Composite parent)
	{
		Control contents = super.createContents(parent);

		if(m_wizardImage != null)
		{
			setTitleImage(m_wizardImage);
		}
		
		if(m_newRow)
		{
			setTitle("New Row");
			setMessage("Enter new row fields.");
		}
		else
		{
			setTitle("Edit Row");
			setMessage("Edit row fields.");
		}

		return contents;
	}

	@Override
	protected Control createDialogArea(Composite parent)
	{
		Composite composite = (Composite)super.createDialogArea(parent);

		Listener rowModifyListener = new Listener()
		{
			public void handleEvent(Event event)
			{
				setErrorMessage(event.text);
				enableDisableOkButton();
			}
		};
		
		Composite textComposite = new Composite(composite, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginTop = 7;
		layout.marginWidth = 10;
		textComposite.setLayout(layout);
		textComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Object[] fields = null;
		
		if(! m_newRow)
		{
			fields = m_table.toRowArray(m_table.getRow(m_row));
		} else
		{
			fields = new Object[m_table.getColumns()];
			Arrays.fill(fields, null);
		}
		
		m_widgetins = m_table.fillGrid(textComposite, fields);
		
		for(int i = 0; i < m_table.getColumns(); i++)
		{
			m_widgetins[i].addListener(ISimpleTable.ERROR_MESSAGE_EVENT_TYPE, rowModifyListener);
		}
		
		return textComposite;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent)
	{
		m_okButton = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
		enableDisableOkButton();
	}

	private void enableDisableOkButton()
	{
		boolean valid = true;

		try
		{
			for(int i = 0; i < m_table.getColumns(); i++)
			{
				m_table.getFieldValidator(i).validate(m_widgetins[i].getData());
			}
		}
		catch(ValidatorException e1)
		{
			valid = false;
		}
		
		m_okButton.setEnabled(valid);
	}
	
	@Override
	protected void buttonPressed(int buttonId)
	{
		if(buttonId == IDialogConstants.OK_ID)
		{
			Object[] fields = new Object[m_table.getColumns()];
			
			for(int i = 0; i < m_table.getColumns(); i++)
			{
				fields[i] = m_widgetins[i].getData();
			}
			
			try
			{
				if(m_newRow)
				{
					m_table.addRow(fields);
				} else
				{
					m_table.setRow(m_row, fields);
				}
			}
			catch(ValidatorException e)
			{
				setErrorMessage(e.getMessage());
				m_okButton.setEnabled(false);
				
				return;
			}
		}
		
		setReturnCode(buttonId);
		close();		
	}
}
