/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import org.eclipse.buckminster.jnlp.accountservice.IAuthenticator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 * 
 */
public class LoginPage extends InstallWizardPage
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

	protected LoginPage(String provider)
	{
		super("LoginStep", "Login", "Materialization requires login to " + provider + ".", null);
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

	public void createControl(Composite parent)
	{
		Composite pageComposite = new Composite(parent, SWT.NONE);
		pageComposite.setLayout(new GridLayout(1, false));

		setPageComplete(false);

		m_userButton = new Button(pageComposite, SWT.RADIO);
		m_userButton.setText("Already user");
		GridData gridData = new GridData();
		gridData.horizontalSpan = 1;
		m_userButton.setLayoutData(gridData);
		m_userButton.setSelection(true);
		m_userButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				enableLogin1Fields(true);
				enableLogin2Fields(false);
				setPageComplete(getCompleteLogin1Fields());
			}
		});

		Group userGroup = new Group(pageComposite, SWT.NONE);
		userGroup.setLayout(new GridLayout(2, false));
		userGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		m_login1Label = new Label(userGroup, SWT.NONE);
		m_login1Label.setText("Login:");
		m_login1Text = new Text(userGroup, SWT.BORDER);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		m_login1Text.setLayoutData(gridData);

		m_password1Label = new Label(userGroup, SWT.NONE);
		m_password1Label.setText("Password:");
		m_password1Text = new Text(userGroup, SWT.BORDER | SWT.PASSWORD);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		m_password1Text.setLayoutData(gridData);

		new Label(pageComposite, SWT.NONE);

		m_registerButton = new Button(pageComposite, SWT.RADIO);
		m_registerButton.setText("New user");
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		m_registerButton.setLayoutData(gridData);
		m_registerButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				enableLogin1Fields(false);
				enableLogin2Fields(true);
				setPageComplete(getCompleteLogin2Fields());
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

		ModifyListener complete1Listener = new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				setPageComplete(getCompleteLogin1Fields());
			}
		};

		m_login1Text.addModifyListener(complete1Listener);
		m_password1Text.addModifyListener(complete1Listener);

		ModifyListener complete2Listener = new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				setPageComplete(getCompleteLogin2Fields());
			}
		};

		m_login2Text.addModifyListener(complete2Listener);
		m_password2Text.addModifyListener(complete2Listener);
		m_retypePasswordText.addModifyListener(complete2Listener);
		m_emailText.addModifyListener(complete2Listener);

		setControl(pageComposite);
	}

	@Override
	public void setPageComplete(boolean complete)
	{
		super.setPageComplete(!getInstallWizard().isLoginRequired() || complete);
	}

	@Override
	public boolean performPageCommit()
	{
		if(getInstallWizard().isLoginRequired())
		{

			IAuthenticator authenticator;

			try
			{
				authenticator = getInstallWizard().getAuthenticator();

				if(authenticator == null)
				{
					throw new JNLPException("Authenticator is not available", null);
				}

				String userName = m_login1Text.getText();
				String password = m_password1Text.getText();

				if(m_registerButton.getSelection())
				{
					userName = m_login2Text.getText();
					password = m_password2Text.getText();

					int result = authenticator.register(userName, password, m_emailText.getText());

					if(result == IAuthenticator.REGISTER_LOGIN_EXISTS)
					{
						// try to login using this login and password
						if(authenticator.login(userName, password) == IAuthenticator.LOGIN_OK)
						{
							return true;
						}
					}

					switch(result)
					{
					case IAuthenticator.REGISTER_FAIL:
						throw new JNLPException("Registration was not successful", null);
					case IAuthenticator.REGISTER_LOGIN_EXISTS:
						throw new JNLPException("Login name already exists - choose a different one", null);
					case IAuthenticator.REGISTER_LOGIN_TOO_SHORT:
						throw new JNLPException("Login is too short - length must be between 3 and 25", null);
					case IAuthenticator.REGISTER_PASSWORD_TOO_SHORT:
						throw new JNLPException("Password is too short - length must be between 4 and 25", null);
					case IAuthenticator.REGISTER_EMAIL_FORMAT_ERROR:
						throw new JNLPException("Email does not have standard format", null);
					}
				}

				if(authenticator.login(userName, password) != IAuthenticator.LOGIN_OK)
				{
					throw new JNLPException("Cannot login - check username and password and try again", null);
				}

				if(!authenticator.isLoggedIn())
				{
					throw new JNLPException("Problem with the remote server - try to login later", null);
				}
			}
			catch(Throwable e)
			{
				setErrorMessage(e.getMessage());
				return false;
			}
		}
		
		return true;
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

	private boolean getCompleteLogin1Fields()
	{
		if(m_login1Text.getText().length() == 0)
		{
			setErrorMessage("Login cannot be empty");
			return false;
		}
		if(m_password1Text.getText().length() == 0)
		{
			setErrorMessage("Password cannot be empty");
			return false;
		}

		setErrorMessage(null);
		return true;
	}

	private boolean getCompleteLogin2Fields()
	{
		if(m_login2Text.getText().length() == 0)
		{
			setErrorMessage("Login cannot be empty");
			return false;
		}
		if(m_password2Text.getText().length() == 0)
		{
			setErrorMessage("Password cannot be empty");
			return false;
		}
		if(m_retypePasswordText.getText().length() == 0)
		{
			setErrorMessage("Retyped Password cannot be empty");
			return false;
		}
		if(!m_password2Text.getText().equals(m_retypePasswordText.getText()))
		{
			setErrorMessage("Passwords are different");
			return false;
		}
		if(m_emailText.getText().length() == 0)
		{
			setErrorMessage("Email cannot be empty");
			return false;
		}

		setErrorMessage(null);
		return true;
	}
}
