/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
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
public class HelpLinkErrorDialog extends ErrorDialog
{
	private Image m_dialogImage;

	private String m_helpLinkTitle;

	private String m_helpLinkURL;

	private String m_errorCode;

	private static String s_syncString = null;

	public static int openError(Shell parentShell, Image dialogImage, String dialogTitle, String message,
			IStatus status, String helpLinkTitle, String helpLinkURL, String errorCode, int displayMask)
	{
		ErrorDialog dialog;

		if(s_syncString != null)
			System.out.println(s_syncString);

		if(helpLinkURL == null)
		{
			dialog = new ErrorDialog(parentShell, dialogTitle, message, status, displayMask);
		}
		else
		{
			if(helpLinkTitle == null)
			{
				helpLinkTitle = helpLinkURL.toString();
			}
			dialog = new HelpLinkErrorDialog(parentShell, dialogImage, dialogTitle, message, helpLinkTitle,
					helpLinkURL, errorCode, status, displayMask);
		}

		return dialog.open();
	}

	public static int openError(Shell parent, Image dialogImage, String dialogTitle, String message,
			String helpLinkTitle, String helpLinkURL, String errorCode, IStatus status)
	{

		return openError(parent, dialogImage, dialogTitle, message, status, helpLinkTitle, helpLinkURL, errorCode,
				IStatus.OK | IStatus.INFO | IStatus.WARNING | IStatus.ERROR);
	}

	public static void setSyncString(String syncString)
	{
		s_syncString = syncString;
	}

	protected HelpLinkErrorDialog(Shell parentShell, Image dialogImage, String dialogTitle, String msg,
			String helpLinkString, String helpLinkURL, String errorCode, IStatus status, int displayMask)
	{
		super(parentShell, dialogTitle, msg, status, displayMask);

		m_dialogImage = dialogImage;
		m_helpLinkTitle = helpLinkString;
		m_helpLinkURL = helpLinkURL;
		m_errorCode = errorCode;
	}

	@Override
	protected void configureShell(Shell shell)
	{
		super.configureShell(shell);
		if(m_dialogImage != null)
		{
			shell.setImage(m_dialogImage);
		}
	}

	@Override
	protected Control createMessageArea(Composite composite)
	{

		// create image
		Image image = getImage();
		if(image != null)
		{
			imageLabel = new Label(composite, SWT.NULL);
			image.setBackground(imageLabel.getBackground());
			imageLabel.setImage(image);
			imageLabel
					.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_CENTER | GridData.VERTICAL_ALIGN_BEGINNING));
		}

		Composite msgComposite = new Composite(composite, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = layout.marginWidth = 0;
		msgComposite.setLayout(layout);

		GridData data;

		// create message
		if(message != null)
		{
			Label msgLabel = new Label(msgComposite, getMessageLabelStyle());
			msgLabel.setText(message);
			data = new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL
					| GridData.VERTICAL_ALIGN_BEGINNING);
			data.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH);
			msgLabel.setLayoutData(data);
		}

		new Label(msgComposite, SWT.NONE);

		Composite linkComposite = new Composite(msgComposite, SWT.NONE);
		layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 0;
		linkComposite.setLayout(layout);

		new Label(linkComposite, SWT.NONE).setText(Messages.HelpLinkErrorDialog_read_more_at);

		Link helpLink = new Link(linkComposite, SWT.NONE);
		helpLink.setText("<a>" + m_helpLinkTitle + "</a>"); //$NON-NLS-1$ //$NON-NLS-2$
		helpLink.addSelectionListener(new SelectionAdapter()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				if(m_helpLinkURL != null)
				{
					if(m_errorCode == null)
					{
						Program.launch(m_helpLinkURL);
					}
					else
					{
						Program.launch(m_helpLinkURL + "?errorCode=" + m_errorCode); //$NON-NLS-1$
					}
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

		return composite;
	}
}
