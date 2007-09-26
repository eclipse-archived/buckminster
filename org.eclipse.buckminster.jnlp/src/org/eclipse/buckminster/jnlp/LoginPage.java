/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_NO_AUTHENTICATOR_EXCEPTION;

import org.eclipse.buckminster.jnlp.accountservice.IAuthenticator;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Karel Brezina
 * 
 */
public class LoginPage extends InstallWizardPage
{
	private LoginPanel m_login;

	protected LoginPage(String provider)
	{
		super("LoginStep", "Login", "Materialization requires login to " + provider + ".", null);
		
		m_login = new LoginPanel();
	}

	public void createControl(Composite parent)
	{
		setPageComplete(false);

		ModifyListener fieldsListener = new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				setPageComplete(getCompleteLoginFields());
			}
		};

		SelectionListener fieldsSwitchListener = new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				setPageComplete(getCompleteLoginFields());
			}
		};
				
		setControl(m_login.createControl(parent, fieldsListener, fieldsSwitchListener));
	}

	private boolean getCompleteLoginFields()
	{
		String errorMsg = m_login.checkCompleteLoginFields();
		
		setErrorMessage(errorMsg);
		return errorMsg == null;
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

			String userName;
			String password;

			try
			{
				authenticator = getInstallWizard().getAuthenticator();

				if(authenticator == null)
				{
					throw new JNLPException("Authenticator is not available", ERROR_CODE_NO_AUTHENTICATOR_EXCEPTION);
				}

				userName = m_login.getLogin();
				password = m_login.getPassword();

				if(!m_login.isAlreadyUser())
				{
					int result = authenticator.register(userName, password, m_login.getEmail());

					if(result == IAuthenticator.REGISTER_LOGIN_EXISTS)
					{
						// try to login using this login and password
						if(authenticator.login(userName, password) == IAuthenticator.LOGIN_OK)
						{
							return true;
						}
					}

					MaterializationUtils.checkRegistrationResponse(result);
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
			
			getInstallWizard().setAuthenticatorUserName(userName);
			getInstallWizard().setAuthenticatorPassword(password);
		}
		
		return true;
	}
}
