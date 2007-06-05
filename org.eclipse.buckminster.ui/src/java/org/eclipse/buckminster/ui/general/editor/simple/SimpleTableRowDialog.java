/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.general.editor.simple;

import java.util.Arrays;

import org.eclipse.buckminster.ui.general.editor.TableRowDialog;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
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
public class SimpleTableRowDialog<T> extends TableRowDialog
{
	private final ISimpleTable<T> m_table;
	
	private final int m_row;
	
	private final boolean m_newRow;
	
	private IWidgetin[] m_widgetins;

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
		super(parent, windowImage, windowTitle, wizardImage, helpURL, (row == -1));
		
		m_table = table;
		m_row = row;
		m_newRow = (row == -1);
		m_widgetins = new IWidgetin[table.getColumns()];
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
	protected void enableDisableOkButton()
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
		
		getButton(IDialogConstants.OK_ID).setEnabled(valid);
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
				getButton(IDialogConstants.OK_ID).setEnabled(false);
				
				return;
			}
		}
		
		setReturnCode(buttonId);
		close();		
	}
}
