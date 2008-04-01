/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import static org.eclipse.buckminster.jnlp.MaterializationConstants.*;

import org.eclipse.buckminster.jnlp.accountservice.IAuthenticator;
import org.eclipse.buckminster.jnlp.accountservice.IPublisher;
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
public class PublishLoginPage extends PublishWizardPage
{
	private LoginPanel m_login;

	private String m_lastRegisteredUserName;

	private String m_lastRegisteredPassword;

	protected PublishLoginPage(String provider)
	{
		super(MaterializationConstants.STEP_PUBLISH_LOGIN, "Login", "Publishing requires login to " + provider + ".", null);
	}

	@Override
	protected void beforeDisplaySetup()
	{
		m_login.setCurrentUserVisible(getPublishWizard().getAuthenticatorLoginKey() != null);
		setPageComplete(getCompleteLoginFields());
	}
	
	public void createControl(Composite parent)
	{		
		m_login = new LoginPanel(getPublishWizard().getAuthenticatorLoginKeyUserName(), getPublishWizard().getAuthenticatorUserName(), getPublishWizard().getAuthenticatorPassword());

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

	@Override
    public boolean isPageComplete()
	{
        if(isCurrentPage())
        {
        	return super.isPageComplete();
		}

        return getPublishWizard().isLoggedIn();
    }

	private boolean getCompleteLoginFields()
	{
		String errorMsg = m_login.checkCompleteLoginFields();
		
		setErrorMessage(errorMsg);
		return errorMsg == null;
	}

	@Override
	public boolean performPageCommit()
	{
			IPublisher publisher;

			try
			{
				publisher = getPublishWizard().getPublisher();

				if(publisher == null)
				{
					throw new JNLPException("Publisher is not available", ERROR_CODE_NO_PUBLISHER_EXCEPTION);
				}

				String userName = null;
				String password = null;
				
				if(m_login.isCurrentUser())
				{
					int result = publisher.relogin(getPublishWizard().getAuthenticatorLoginKey());

					if(result == IAuthenticator.LOGIN_UNKNOW_KEY)
						getPublishWizard().removeAuthenticatorLoginKey();

					if(result != IAuthenticator.LOGIN_OK)
					{
						throw new JNLPException("Cannot login - try to login using USERNAME and PASSWORD", null);
					}					
				} else
				{					
					userName = m_login.getLogin();
					password = m_login.getPassword();
	
					if(!m_login.isAlreadyUser())
					{
						int result = publisher.register(userName, password, m_login.getEmail());
	
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
	
					if(publisher.relogin(userName, password) != IAuthenticator.LOGIN_OK)
					{
						throw new JNLPException("Cannot login - check username and password and try again", null);
					}
				}

				if(!publisher.isLoggedIn())
				{
					throw new JNLPException("Problem with the remote server - try to login later", null);
				}

				getPublishWizard().setAuthenticatorUserName(userName);
				getPublishWizard().setAuthenticatorPassword(password);
			}
			catch(Throwable e)
			{
				setErrorMessage(e.getMessage());
				return false;
			}
			
			setErrorMessage(null);
		
		return true;
	}
}
