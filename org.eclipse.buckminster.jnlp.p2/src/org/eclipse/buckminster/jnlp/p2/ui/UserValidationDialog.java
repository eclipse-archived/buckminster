/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.jnlp.p2.ui;

import org.eclipse.buckminster.jnlp.p2.ui.general.wizard.AdvancedTitleAreaDialog;
import org.eclipse.equinox.internal.provisional.p2.core.IServiceUI.AuthenticationInfo;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 * 
 */
@SuppressWarnings("restriction")
public class UserValidationDialog extends AdvancedTitleAreaDialog
{
	private AuthenticationInfo m_result;

	private Text m_username;

	private Text m_password;

	private Button m_saveButton;

	public UserValidationDialog(Shell parentShell, Image windowImage, String windowTitle, Image wizardImage,
			String wizardTitle, String wizardMessage, AuthenticationInfo previousInfo)
	{
		super(parentShell, windowImage, windowTitle, wizardImage, wizardTitle, wizardMessage, null);
		m_result = previousInfo;
	}

	public AuthenticationInfo getResult()
	{
		return m_result;
	}

	@Override
	protected void buttonPressed(int buttonId)
	{
		if(buttonId == IDialogConstants.OK_ID)
			m_result = new AuthenticationInfo(m_username.getText(), m_password.getText(), m_saveButton.getSelection());
		else
			m_result = null;

		setReturnCode(buttonId);
		close();
	}

	@Override
	protected Control createDialogArea(Composite parent)
	{
		Composite composite = (Composite)super.createDialogArea(parent);

		Composite fieldContainer = new Composite(composite, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginHeight = layout.marginWidth = 10;
		fieldContainer.setLayout(layout);
		GridData layoutData = new GridData();
		fieldContainer.setLayoutData(layoutData);

		Label label = new Label(fieldContainer, SWT.NONE);
		label.setText("Username:");
		m_username = new Text(fieldContainer, SWT.BORDER);
		layoutData = new GridData(GridData.FILL_HORIZONTAL);
		layoutData.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.ENTRY_FIELD_WIDTH);
		layoutData.horizontalAlignment = SWT.END;
		m_username.setLayoutData(layoutData);
		m_username.setText(getUserName());

		label = new Label(fieldContainer, SWT.NONE);
		label.setText("Password:");
		m_password = new Text(fieldContainer, SWT.PASSWORD | SWT.BORDER);
		layoutData = new GridData(GridData.FILL_HORIZONTAL);
		layoutData.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.ENTRY_FIELD_WIDTH);
		layoutData.horizontalAlignment = SWT.END;
		m_password.setLayoutData(layoutData);
		m_password.setText(getPassword());

		Composite checkboxContainer = new Composite(composite, SWT.NONE);
		layout = new GridLayout();
		layout.marginHeight = layout.marginWidth = 10;
		checkboxContainer.setLayout(layout);
		layoutData = new GridData();
		layoutData.widthHint = convertHorizontalDLUsToPixels(IDialogConstants.ENTRY_FIELD_WIDTH);
		checkboxContainer.setLayoutData(layoutData);
		m_saveButton = new Button(checkboxContainer, SWT.CHECK);
		m_saveButton.setText("Save password");
		m_saveButton.setSelection(saveResult());

		m_username.setFocus();

		return composite;
	}

	@Override
	protected void enableDisableOkButton()
	{
		// OK is always enabled
	}

	private String getPassword()
	{
		return m_result != null
				? m_result.getPassword()
				: ""; //$NON-NLS-1$
	}

	private String getUserName()
	{
		return m_result != null
				? m_result.getUserName()
				: ""; //$NON-NLS-1$
	}

	private boolean saveResult()
	{
		return m_result != null
				? m_result.saveResult()
				: false;
	}

}
