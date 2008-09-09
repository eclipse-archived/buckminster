/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.wizard;

import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_NO_PUBLISHER_EXCEPTION;

import org.eclipse.buckminster.jnlp.JNLPException;
import org.eclipse.buckminster.jnlp.MaterializationUtils;
import org.eclipse.buckminster.jnlp.distroprovider.IRemoteDistroProvider;
import org.eclipse.buckminster.jnlp.ui.general.wizard.AdvancedTitleAreaDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * @author kaja
 *
 */
public class LoginDialog extends AdvancedTitleAreaDialog
{
	private ILoginHandler m_loginHandler;
	
	private LoginPanel m_login;
	
	public LoginDialog(
			Shell parentShell, ILoginHandler loginHandler,
			Image windowImage, String windowTitle, Image wizardImage, String serviceProvider, String helpURL)
	{
		super(
				parentShell, windowImage, windowTitle,
				wizardImage, "Login", "Publishing requires login to " + serviceProvider + ".", helpURL);
		m_loginHandler = loginHandler;
	}

	@Override
	protected Control createDialogArea(Composite parent)
	{
		m_login = new LoginPanel(m_loginHandler.getAuthenticatorLoginKeyUserName(), m_loginHandler.getAuthenticatorUserName(), m_loginHandler.getAuthenticatorPassword());

		ModifyListener fieldsListener = new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				enableDisableOkButton();
			}
		};

		SelectionListener fieldsSwitchListener = new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				enableDisableOkButton();
			}
		};

		Composite composite = new Composite((Composite)super.createDialogArea(parent), SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Control control = m_login.createControl(composite, fieldsListener, fieldsSwitchListener);
		
		m_login.setCurrentUserVisible(m_loginHandler.getAuthenticatorLoginKey() != null);

		return control;
	}

	private boolean getCompleteLoginFields()
	{
		String errorMsg = m_login.checkCompleteLoginFields();
		
		setErrorMessage(errorMsg);
		return errorMsg == null;
	}

	@Override
	protected void enableDisableOkButton()
	{
		boolean enable = getCompleteLoginFields();
		getButton(IDialogConstants.OK_ID).setEnabled(enable);
	}
	
	@Override
	protected void buttonPressed(int buttonId)
	{
		if(buttonId == IDialogConstants.OK_ID)
		{
			IRemoteDistroProvider authenticator;
		
			try
			{
				authenticator = m_loginHandler.getAuthenticator().createDuplicate(false);
		
				if(authenticator == null)
				{
					throw new JNLPException("Publisher is not available", ERROR_CODE_NO_PUBLISHER_EXCEPTION);
				}
		
				String userName = null;
				String password = null;
				
				if(m_login.isCurrentUser())
				{
					int result = authenticator.relogin(m_loginHandler.getAuthenticatorLoginKey());
		
					if(result == IRemoteDistroProvider.LOGIN_UNKNOW_KEY)
						m_loginHandler.removeAuthenticatorLoginKey();
		
					if(result != IRemoteDistroProvider.LOGIN_OK)
					{
						throw new JNLPException("Cannot login - try to login using USERNAME and PASSWORD", null);
					}					
				} else
				{					
					userName = m_login.getLogin();
					password = m_login.getPassword();
		
					if(!m_login.isAlreadyUser())
					{
						int result = authenticator.register(userName, password, m_login.getEmail());
		
						MaterializationUtils.checkRegistrationResponse(result);
					}
		
					if(authenticator.relogin(userName, password) != IRemoteDistroProvider.LOGIN_OK)
					{
						throw new JNLPException("Cannot login - check username and password and try again", null);
					}
				}
		
				if(!authenticator.isLoggedIn())
				{
					throw new JNLPException("Problem with the remote server - try to login later", null);
				}
		
				m_loginHandler.getAuthenticator().releaseConnection();
				m_loginHandler.setAuthenticator(authenticator);
				m_loginHandler.setAuthenticatorUserName(userName);
				m_loginHandler.setAuthenticatorPassword(password);
			}
			catch(Throwable e)
			{
				setErrorMessage(e.getMessage());
				return;
			}
		}
		
		setReturnCode(buttonId);
		close();
	}
}
