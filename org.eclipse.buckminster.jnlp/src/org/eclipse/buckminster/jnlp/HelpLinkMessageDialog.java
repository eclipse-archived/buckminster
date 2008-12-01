/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Karel Brezina
 *
 */
public class HelpLinkMessageDialog extends MessageDialog
{
	private String m_helpLinkTitle;
	private String m_helpLinkURL;
	
	public HelpLinkMessageDialog(
			Shell parentShell, String dialogTitle, Image dialogTitleImage, String dialogMessage, int dialogImageType, String[] dialogButtonLabels, int defaultIndex,
			String helpLinkTitle, String helpLinkURL)
	{
		super(parentShell, dialogTitle, dialogTitleImage, dialogMessage, dialogImageType, dialogButtonLabels, defaultIndex);

		m_helpLinkTitle = helpLinkTitle;
		m_helpLinkURL = helpLinkURL;
		
		if(m_helpLinkTitle == null)
		{
			m_helpLinkTitle = m_helpLinkURL;
		}
	}

    public static void openInformation(Shell parent, String dialogTitle, Image dialogTitleImage, String dialogMessage, String helpLinkTitle, String helpLinkURL) {
        MessageDialog dialog = new HelpLinkMessageDialog(parent, dialogTitle, dialogTitleImage, dialogMessage, INFORMATION, new String[] { IDialogConstants.OK_LABEL }, 0, helpLinkTitle, helpLinkURL);
        dialog.open();
        return;
    }

    @Override
	protected Control createDialogArea(Composite parent) {
        // create message area
        createMessageArea(parent);

        // allow subclasses to add custom controls
		new Label(parent, SWT.NULL);
        Control customArea = createCustomArea(parent);
        //If it is null create a dummy label for spacing purposes
        if (customArea == null) {
			customArea = new Label(parent, SWT.NULL);
		}
        return customArea;
    }

    @Override
	protected Control createCustomArea(Composite parent) {
    	
    	if(m_helpLinkURL == null)
    	{
    		return null;
    	}
    	
		Composite linkComposite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 0;
		linkComposite.setLayout(layout);

		new Label(linkComposite, SWT.NONE).setText(Messages.read_more_about_with_colon);

		Link helpLink = new Link(linkComposite, SWT.NONE);
		helpLink.setText("<a>" + m_helpLinkTitle + "</a>"); //$NON-NLS-1$ //$NON-NLS-2$
		helpLink.addSelectionListener(new SelectionAdapter()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				if(m_helpLinkURL != null)
				{
					Program.launch(m_helpLinkURL);
				}
			}
		});

		// after start set focus to OK button not to help link
		getShell().addFocusListener(new FocusAdapter()
		{

			private boolean m_passFocusToOk = true;

			@Override
			public void focusGained(FocusEvent e)
			{
				if(m_passFocusToOk)
				{
					getButton(IDialogConstants.OK_ID).setFocus();
					m_passFocusToOk = false;
				}
			}
		});

    	return null;
    }   
}
