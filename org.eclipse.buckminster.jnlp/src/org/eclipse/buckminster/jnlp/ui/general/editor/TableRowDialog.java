/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.ui.general.editor;

import org.eclipse.buckminster.jnlp.ui.UiUtils;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Karel Brezina
 *
 */
public class TableRowDialog<T> extends TitleAreaDialog
{
	private final ITable<T> m_table;
	
	private final Image m_windowImage;
	
	private final String m_windowTitle;
	
	private final Image m_wizardImage;

	private final int m_row;
	
	private final boolean m_newRow;
	
	private Control[] m_controls;

	private Button m_okButton;

	/**
	 * PropertyDialog constructor
	 *
	 * @param parent the parent shell
	 */
	public TableRowDialog(Shell parent, Image windowImage, String windowTitle, Image wizardImage, ITable<T> table, int row)
	{
		super(parent);
		
		m_table = table;
		m_windowImage = windowImage;
		m_windowTitle = windowTitle;
		m_wizardImage = wizardImage;
		m_row = row;
		m_newRow = (row == -1);
		m_controls = new Control[table.getColumns()];
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

	/**
	 * Creates the dialog area
	 *
	 * @param parent the parent composite
	 * @return Control
	 */
	@Override
	protected Control createDialogArea(Composite parent)
	{
		Composite composite = (Composite)super.createDialogArea(parent);

		Composite textComposite = new Composite(composite, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginTop = 7;
		layout.marginWidth = 10;
		textComposite.setLayout(layout);
		textComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Object[] fields = null;
		
		if(! m_newRow)
		{
			fields = m_table.toRowArray(m_table.getRow(m_row));
		}
		
		Listener rowModifyListener = new Listener()
		{
			public void handleEvent(Event event)
			{
				setErrorMessage(event.text);
				enableDisableOkButton();
			}
		};
		
		for(int i = 0; i < m_table.getColumns(); i++)
		{
			UiUtils.createGridLabel(textComposite, m_table.getColumnHeaders()[i] + ":", 1, 0, SWT.NONE);
			m_controls[i] = m_table.getControl(textComposite, i, m_newRow ? null : fields[i]);
			m_controls[i].addListener(ITable.ERROR_MESSAGE_EVENT_TYPE, rowModifyListener);
		}
		
		return textComposite;
	}

	/**
	 * Creates the buttons
	 *
	 * @param parent the parent composite
	 */
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
				m_table.getFieldValidator(i).validate(m_controls[i].getData());
			}
		}
		catch(ValidatorException e1)
		{
			valid = false;
		}
		
		m_okButton.setEnabled(valid);
	}
	
	/**
	 * Handles a button press
	 *
	 * @param buttonId the ID of the pressed button
	 */
	@Override
	protected void buttonPressed(int buttonId)
	{
		if(buttonId == IDialogConstants.OK_ID)
		{
			Object[] fields = new Object[m_table.getColumns()];
			
			for(int i = 0; i < m_table.getColumns(); i++)
			{
				fields[i] = m_controls[i].getData();
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
				m_controls[0].setFocus();
				return;
			}
		}
		
		setReturnCode(buttonId);
		close();		
	}
}
