/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_NO_PUBLISHER_EXCEPTION;

import org.eclipse.buckminster.jnlp.accountservice.IAuthenticator;
import org.eclipse.buckminster.jnlp.accountservice.IPublisher;
import org.eclipse.buckminster.jnlp.ui.general.wizard.AdvancedTitleAreaDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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
	private PublishWizard m_publishWizard;
	
	private LoginPanel m_login;
	
	public LoginDialog(Shell parentShell, PublishWizard publishWizard)
	{
		super(
				parentShell, publishWizard.getWindowImage(), publishWizard.getWindowTitle() + " - Login Dialog",
				publishWizard.getWizardImage(), "Login", "Publishing requires login to " + publishWizard.getServiceProvider() + ".",
				publishWizard.getHelpURL());
		m_publishWizard = publishWizard;
	}

	@Override
	protected Control createDialogArea(Composite parent)
	{
		m_login = new LoginPanel(m_publishWizard.getLoginKeyUserName(), m_publishWizard.getPreferredUserName(), m_publishWizard.getPreferredPassword());

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
		
		m_login.setCurrentUserVisible(m_publishWizard.getLoginKey() != null);

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
			IPublisher publisher;
		
			try
			{
				publisher = m_publishWizard.getPublisher().createDuplicatePublisher(false);
		
				if(publisher == null)
				{
					throw new JNLPException("Publisher is not available", ERROR_CODE_NO_PUBLISHER_EXCEPTION);
				}
		
				String userName = null;
				String password = null;
				
				if(m_login.isCurrentUser())
				{
					int result = publisher.relogin(m_publishWizard.getLoginKey());
		
					if(result == IAuthenticator.LOGIN_UNKNOW_KEY)
						m_publishWizard.removeLoginKey();
		
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
		
						MaterializationUtils.checkRegistrationResponse(result);
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
		
				m_publishWizard.getPublisher().releaseConnection();
				m_publishWizard.setPublisher(publisher);
				m_publishWizard.setPreferredUserName(userName);
				m_publishWizard.setPreferredPassword(password);
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
