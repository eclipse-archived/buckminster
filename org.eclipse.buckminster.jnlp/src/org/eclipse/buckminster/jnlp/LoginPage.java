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
import org.eclipse.jface.wizard.IWizardPage;
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

	private String m_lastRegisteredUserName;

	private String m_lastRegisteredPassword;
	
	private boolean m_pageCommitted;
	
	protected LoginPage(String provider)
	{
		super(MaterializationConstants.STEP_LOGIN, "Login", "Materialization requires login to " + provider + ".", null);
	}

	@Override
	protected void beforeDisplaySetup()
	{
		getInstallWizard().setLoginPageRequested(true);
		m_login.setCurrentUserVisible(getInstallWizard().getAuthenticatorLoginKey() != null);
		
		setPageComplete(getCompleteLoginFields());
		
		m_pageCommitted = false;
	}
	
	public void createControl(Composite parent)
	{		
		m_login = new LoginPanel(getInstallWizard().getAuthenticatorLoginKeyUserName());

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
    public boolean isPageComplete()
	{
        if(isCurrentPage())
        {
        	return super.isPageComplete();
		}

        return !getInstallWizard().isLoginRequired() || getInstallWizard().isLoggedIn();
    }

	@Override
	public void setPageComplete(boolean complete)
	{
		super.setPageComplete(!getInstallWizard().isLoginRequired() || complete);
	}
	
    @Override
	public boolean performPageCommit()
	{
		if(isCurrentPage())
		{

			IAuthenticator authenticator;

			String userName = null;
			String password = null;

			try
			{
				authenticator = getInstallWizard().getAuthenticator();

				if(authenticator == null)
				{
					throw new JNLPException("Authenticator is not available", ERROR_CODE_NO_AUTHENTICATOR_EXCEPTION);
				}

				if(m_login.isCurrentUser())
				{
					int result = authenticator.relogin(getInstallWizard().getAuthenticatorLoginKey());

					if(result == IAuthenticator.LOGIN_UNKNOW_KEY)
						getInstallWizard().removeAuthenticatorLoginKey();

					if(result != IAuthenticator.LOGIN_OK)
						throw new JNLPException("Cannot login - try to login using USERNAME and PASSWORD", null);
				}
				else
				{					
					userName = m_login.getLogin();
					password = m_login.getPassword();
	
					if(!m_login.isAlreadyUser())
					{
						int result = authenticator.register(userName, password, m_login.getEmail());
	
						if(result == IAuthenticator.REGISTER_LOGIN_EXISTS &&
								m_lastRegisteredUserName != null && m_lastRegisteredUserName.equals(userName) &&
								m_lastRegisteredPassword != null && m_lastRegisteredPassword.equals(password))
							
							// OK - user has already registered this login, don't force him to rewrite login & password to "Already User" section
							;
						else
						{					
							MaterializationUtils.checkRegistrationResponse(result);
							
							m_lastRegisteredUserName = userName;
							m_lastRegisteredPassword = password;
						}
					}
	
					if(authenticator.relogin(userName, password) != IAuthenticator.LOGIN_OK)
					{
						throw new JNLPException("Cannot login - check username and password and try again", null);
					}
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
			
			if(
				((userName == null) != (getInstallWizard().getAuthenticatorUserName() == null)) ||
				userName != null && password != null && !(userName.equals(getInstallWizard().getAuthenticatorUserName()) && password.equals(getInstallWizard().getAuthenticatorPassword())))
			{
				getInstallWizard().resetMaterializerInitialization();
			}
			
			getInstallWizard().setAuthenticatorUserName(userName);
			getInstallWizard().setAuthenticatorPassword(password);
			
			m_pageCommitted = true;
		}
		
		return true;
	}
    
	@Override
	public IWizardPage getNextPage()
	{
		if(m_pageCommitted)
			return getInstallWizard().getDownloadPage();
		
		return getWizard().getPage(MaterializationConstants.STEP_DOWNLOAD_LOCATION);
	}
}
