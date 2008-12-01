/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.wizard.install;

import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_NO_AUTHENTICATOR_EXCEPTION;

import org.eclipse.buckminster.jnlp.JNLPException;
import org.eclipse.buckminster.jnlp.MaterializationConstants;
import org.eclipse.buckminster.jnlp.MaterializationUtils;
import org.eclipse.buckminster.jnlp.Messages;
import org.eclipse.buckminster.jnlp.distroprovider.IRemoteDistroProvider;
import org.eclipse.buckminster.jnlp.wizard.LoginPanel;
import org.eclipse.buckminster.jnlp.wizard.install.InstallWizardPage;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.osgi.util.NLS;
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
	
	private IWizardPage m_nextPage;
	
	protected LoginPage(String provider)
	{
		super(MaterializationConstants.STEP_LOGIN, Messages.login, NLS.bind(Messages.materialization_requires_login_to_0, provider), null); 
	}

	@Override
	protected void beforeDisplaySetup()
	{
		getInstallWizard().setLoginPageRequested(true);
		m_login.setCurrentUserVisible(getInstallWizard().getAuthenticatorLoginKey() != null);
		
		setPageComplete(getCompleteLoginFields());
	}
	
	public void createControl(Composite parent)
	{		
		m_nextPage = getInstallWizard().getSelectDistroPage();
		
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

			IRemoteDistroProvider authenticator;

			String userName = null;
			String password = null;

			try
			{
				authenticator = getInstallWizard().getDistroProvider();

				if(authenticator == null)
				{
					throw new JNLPException(Messages.authenticator_is_not_available, ERROR_CODE_NO_AUTHENTICATOR_EXCEPTION);
				}

				if(m_login.isCurrentUser())
				{
					int result = authenticator.relogin(getInstallWizard().getAuthenticatorLoginKey());

					if(result == IRemoteDistroProvider.LOGIN_UNKNOW_KEY)
						getInstallWizard().removeAuthenticatorLoginKey();

					if(result != IRemoteDistroProvider.LOGIN_OK)
						throw new JNLPException(Messages.cannot_login_try_to_login_using_USERNAME_and_PASSWORD, null);
				}
				else
				{					
					userName = m_login.getLogin();
					password = m_login.getPassword();
	
					if(!m_login.isAlreadyUser())
					{
						int result = authenticator.register(userName, password, m_login.getEmail());
	
						if(result == IRemoteDistroProvider.REGISTER_LOGIN_EXISTS &&
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
	
					if(authenticator.relogin(userName, password) != IRemoteDistroProvider.LOGIN_OK)
					{
						throw new JNLPException(Messages.cannot_login_check_username_and_password_and_try_again, null);
					}
				}
				
				if(!authenticator.isLoggedIn())
				{
					throw new JNLPException(Messages.problem_with_the_remote_server_try_to_login_later, null);
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
			
			
			if(getInstallWizard().isFolderRestrictionPageNeeded())
				m_nextPage = getInstallWizard().getFolderRestrictionPage();
			else
			{
				if(!getInstallWizard().isStackInfoRetrieved())
					getInstallWizard().retrieveStackInfo();
				
				if(getInstallWizard().getDistro() != null)
					m_nextPage = getInstallWizard().getDownloadPage();
				else
					m_nextPage = getInstallWizard().getSelectDistroPage();
			}
		}
		
		return true;
	}
    
	@Override
	public IWizardPage getNextPage()
	{
		return m_nextPage;
	}
}
