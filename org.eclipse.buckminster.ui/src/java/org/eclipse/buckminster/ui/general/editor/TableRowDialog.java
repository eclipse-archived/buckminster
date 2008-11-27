/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.general.editor;

import org.eclipse.buckminster.ui.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.events.HelpEvent;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Karel Brezina
 * 
 */
public abstract class TableRowDialog extends TitleAreaDialog
{
	private final Image m_windowImage;

	private final String m_windowTitle;

	private final Image m_wizardImage;

	private final String m_helpURL;

	private final boolean m_newRow;

	public TableRowDialog(Shell parentShell, Image windowImage, String windowTitle, Image wizardImage, String helpURL,
			boolean newRow)
	{
		super(parentShell);
		m_windowImage = windowImage;
		m_windowTitle = windowTitle;
		m_wizardImage = wizardImage;
		m_helpURL = helpURL;
		m_newRow = newRow;
	}

	@Override
	public boolean isHelpAvailable()
	{
		return m_helpURL != null;
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
	protected void createButtonsForButtonBar(Composite parent)
	{
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
		enableDisableOkButton();
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
			setTitle(Messages.new_row);
			setMessage(Messages.enter_new_row_fields);
		}
		else
		{
			setTitle(Messages.edit_row);
			setMessage(Messages.edit_row_fields);
		}

		return contents;
	}

	@Override
	protected Control createHelpControl(Composite parent)
	{
		Control helpControl = super.createHelpControl(parent);
		helpControl.addHelpListener(new HelpListener()
		{

			public void helpRequested(HelpEvent e)
			{
				if(m_helpURL != null)
				{
					Program.launch(m_helpURL);
				}
			}
		});

		return helpControl;
	}

	protected abstract void enableDisableOkButton();
}
