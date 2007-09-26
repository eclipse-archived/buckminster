/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 *
 */
public class LoginPanel
{
	private Button m_userButton;

	private Label m_login1Label;

	private Text m_login1Text;

	private Label m_password1Label;

	private Text m_password1Text;

	private Button m_registerButton;

	private Label m_login2Label;

	private Text m_login2Text;

	private Label m_password2Label;

	private Text m_password2Text;

	private Label m_retypePasswordLabel;

	private Text m_retypePasswordText;

	private Label m_emailLabel;

	private Text m_emailText;

	private String m_initUserName;
	
	private String m_initPassword;
	
	public LoginPanel()
	{
		m_initUserName = "";
		m_initPassword = "";
	}
	
	public LoginPanel(String initUserName, String initPassword)
	{
		m_initUserName = initUserName == null ? "" : initUserName;
		m_initPassword = initPassword == null ? "" : initPassword;
	}
	
	public boolean isAlreadyUser()
	{
		if(m_userButton.getSelection())
		{
			return true;
		}
		return false;
	}
	
	public String getLogin()
	{
		if(m_userButton.getSelection())
		{
			return m_login1Text.getText();
		}
		return m_login2Text.getText();
	}

	public String getPassword()
	{
		if(m_userButton.getSelection())
		{
			return m_password1Text.getText();
		}
		return m_password2Text.getText();
	}

	public String getEmail()
	{
		if(m_userButton.getSelection())
		{
			return null;
		}
		return m_emailText.getText();
	}

	public Control createControl(
			Composite parent,
			ModifyListener fieldsListener,
			SelectionListener fieldsSwitchListener)
	{
		Composite pageComposite = new Composite(parent, SWT.NONE);
		pageComposite.setLayout(new GridLayout(1, false));

		m_userButton = new Button(pageComposite, SWT.RADIO);
		m_userButton.setText("Already user");
		GridData gridData = new GridData();
		gridData.horizontalSpan = 1;
		m_userButton.setLayoutData(gridData);
		m_userButton.setSelection(true);
		
		m_userButton.addSelectionListener(fieldsSwitchListener);
		m_userButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				enableLogin1Fields(true);
				enableLogin2Fields(false);
			}
		});

		Group userGroup = new Group(pageComposite, SWT.NONE);
		userGroup.setLayout(new GridLayout(2, false));
		userGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		m_login1Label = new Label(userGroup, SWT.NONE);
		m_login1Label.setText("Login:");
		m_login1Text = new Text(userGroup, SWT.BORDER);
		m_login1Text.setText(m_initUserName);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		m_login1Text.setLayoutData(gridData);

		m_password1Label = new Label(userGroup, SWT.NONE);
		m_password1Label.setText("Password:");
		m_password1Text = new Text(userGroup, SWT.BORDER | SWT.PASSWORD);
		m_password1Text.setText(m_initPassword);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		m_password1Text.setLayoutData(gridData);

		new Label(pageComposite, SWT.NONE);

		m_registerButton = new Button(pageComposite, SWT.RADIO);
		m_registerButton.setText("New user");
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		m_registerButton.setLayoutData(gridData);
		
		m_registerButton.addSelectionListener(fieldsSwitchListener);
		m_registerButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				enableLogin1Fields(false);
				enableLogin2Fields(true);
			}
		});

		Group registerGroup = new Group(pageComposite, SWT.NONE);
		registerGroup.setLayout(new GridLayout(2, false));
		registerGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		m_login2Label = new Label(registerGroup, SWT.NONE);
		m_login2Label.setText("Login:");
		m_login2Text = new Text(registerGroup, SWT.BORDER);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		m_login2Text.setLayoutData(gridData);

		m_password2Label = new Label(registerGroup, SWT.NONE);
		m_password2Label.setText("Password:");
		m_password2Text = new Text(registerGroup, SWT.BORDER | SWT.PASSWORD);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		m_password2Text.setLayoutData(gridData);

		m_retypePasswordLabel = new Label(registerGroup, SWT.NONE);
		m_retypePasswordLabel.setText("Retype Password:");
		m_retypePasswordText = new Text(registerGroup, SWT.BORDER | SWT.PASSWORD);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		m_retypePasswordText.setLayoutData(gridData);

		m_emailLabel = new Label(registerGroup, SWT.NONE);
		m_emailLabel.setText("Email Address:");
		m_emailText = new Text(registerGroup, SWT.BORDER);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		m_emailText.setLayoutData(gridData);

		// Align both groups
		gridData = new GridData();
		gridData.widthHint = m_retypePasswordLabel.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;
		m_login1Label.setLayoutData(gridData);

		enableLogin2Fields(false);

		m_login1Text.addModifyListener(fieldsListener);
		m_password1Text.addModifyListener(fieldsListener);

		m_login2Text.addModifyListener(fieldsListener);
		m_password2Text.addModifyListener(fieldsListener);
		m_retypePasswordText.addModifyListener(fieldsListener);
		m_emailText.addModifyListener(fieldsListener);
		
		return pageComposite;
	}

	private void enableLogin1Fields(boolean enabled)
	{
		m_login1Label.setEnabled(enabled);
		m_login1Text.setEnabled(enabled);
		m_password1Label.setEnabled(enabled);
		m_password1Text.setEnabled(enabled);
	}

	private void enableLogin2Fields(boolean enabled)
	{
		m_login2Label.setEnabled(enabled);
		m_login2Text.setEnabled(enabled);
		m_password2Label.setEnabled(enabled);
		m_password2Text.setEnabled(enabled);
		m_retypePasswordLabel.setEnabled(enabled);
		m_retypePasswordText.setEnabled(enabled);
		m_emailLabel.setEnabled(enabled);
		m_emailText.setEnabled(enabled);
	}

	public String checkCompleteLoginFields()
	{
		if(isAlreadyUser())
		{
			if(m_login1Text.getText().length() == 0)
			{
				return "Login cannot be empty";
			}
			if(m_password1Text.getText().length() == 0)
			{
				return "Password cannot be empty";
			}
		}
		else
		{
			if(m_login2Text.getText().length() == 0)
			{
				return "Login cannot be empty";
			}
			if(m_password2Text.getText().length() == 0)
			{
				return "Password cannot be empty";
			}
			if(m_retypePasswordText.getText().length() == 0)
			{
				return "Retyped Password cannot be empty";
			}
			if(!m_password2Text.getText().equals(m_retypePasswordText.getText()))
			{
				return "Passwords are different";
			}
			if(m_emailText.getText().length() == 0)
			{
				return "Email cannot be empty";
			}
		}
		
		return null;
	}
}
