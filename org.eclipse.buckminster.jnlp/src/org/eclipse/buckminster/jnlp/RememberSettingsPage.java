/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 * 
 */
public class RememberSettingsPage extends InstallWizardPage
{
	private Button m_remember;

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
	
	protected RememberSettingsPage()
	{
		super("RememberDownloadSettingsStep", "Remember", "Remember Download Settings.", null);
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
		pageComposite.setLayout(new GridLayout(2, false));

		setPageComplete(false);

		m_remember = new Button(pageComposite, SWT.CHECK);
		m_remember.setSelection(true);
		m_remember.setText("Remember Download Settings");
		GridData gridData = new GridData();
		gridData.horizontalSpan = 2;
		m_remember.setLayoutData(gridData);
		m_remember.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				m_userButton.setEnabled(m_remember.getSelection());
				enableLogin1Fields(m_remember.getSelection());
				m_registerButton.setEnabled(m_remember.getSelection());
				enableLogin2Fields(m_remember.getSelection());

				if(!m_remember.getSelection())
				{
					setErrorMessage(null);
					setPageComplete(true);
				}
				else
				{
					if(m_userButton.getSelection())
					{
						setPageComplete(getCompleteLogin1Fields());
					} else
					{
						setPageComplete(getCompleteLogin2Fields());
					}
				}
			}
		});

		new Label(pageComposite, SWT.NONE);
		new Label(pageComposite, SWT.NONE);
		
		m_userButton = new Button(pageComposite, SWT.RADIO);
		m_userButton.setText("Already user");
		gridData = new GridData();
		gridData.horizontalSpan = 2;
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
		
		m_login1Label = new Label(pageComposite, SWT.NONE);
		m_login1Label.setText("Login:");
		m_login1Text = new Text(pageComposite, SWT.BORDER);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		m_login1Text.setLayoutData(gridData);

		m_password1Label = new Label(pageComposite, SWT.NONE);
		m_password1Label.setText("Password:");
		m_password1Text = new Text(pageComposite, SWT.BORDER | SWT.PASSWORD);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		m_password1Text.setLayoutData(gridData);

		new Label(pageComposite, SWT.NONE);
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
		
		m_login2Label = new Label(pageComposite, SWT.NONE);
		m_login2Label.setText("Login:");
		m_login2Text = new Text(pageComposite, SWT.BORDER);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		m_login2Text.setLayoutData(gridData);

		m_password2Label = new Label(pageComposite, SWT.NONE);
		m_password2Label.setText("Password:");
		m_password2Text = new Text(pageComposite, SWT.BORDER | SWT.PASSWORD);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		m_password2Text.setLayoutData(gridData);

		m_retypePasswordLabel = new Label(pageComposite, SWT.NONE);
		m_retypePasswordLabel.setText("Retype Password:");
		m_retypePasswordText = new Text(pageComposite, SWT.BORDER | SWT.PASSWORD);
		gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		m_retypePasswordText.setLayoutData(gridData);

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

		setControl(pageComposite);
	}

	@Override
	public IWizardPage getNextPage()
	{
		if(m_remember.getSelection())
		{
			return getWizard().getPage("SelectSpaceStep");
		}
		return null;
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
		
		setErrorMessage(null);
		return true;
	}
}
