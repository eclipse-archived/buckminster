/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.p2.wizard;

import org.eclipse.buckminster.jnlp.p2.ui.UiUtils;
import org.eclipse.buckminster.jnlp.p2.ui.general.wizard.AdvancedTitleAreaDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 *
 */
public class ProfileDialog extends AdvancedTitleAreaDialog
{
	private String m_profileName;
	
	private Text m_profileNameText;
	
	public ProfileDialog(
			Shell parentShell, Image windowImage, String windowTitle, Image wizardImage, String helpURL)
	{
		super(parentShell, windowImage, windowTitle, wizardImage, "New Profile", "Create a new materialization profile.", helpURL);
	}
	
	@Override
	protected Control createDialogArea(Composite parent)
	{
		Composite composite = new Composite((Composite)super.createDialogArea(parent), SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Composite pageComposite = new Composite(composite, SWT.NONE);
		pageComposite.setLayout(new GridLayout(2, false));
		pageComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Label label = new Label(pageComposite, SWT.NONE);
		label.setText("Profile Name:");

		m_profileNameText = new Text(pageComposite, SWT.BORDER);
		m_profileNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_profileNameText.addModifyListener(new ModifyListener()
		{

			public void modifyText(ModifyEvent e)
			{
				enableDisableOkButton();
			}
		});

		return m_profileNameText;
	}

	private boolean isFormComplete()
	{
		String errorMsg = null;
		
		if(UiUtils.trimmedValue(m_profileNameText) == null)
		{
			errorMsg = "Profile name cannot be empty";
		}
		
		setErrorMessage(errorMsg);
		return errorMsg == null;
	}

	@Override
	protected void enableDisableOkButton()
	{
		boolean enable = isFormComplete();
		getButton(IDialogConstants.OK_ID).setEnabled(enable);
	}
	
	public String getProfileName()
	{
		return m_profileName;
	}
	
	@Override
	protected void buttonPressed(int buttonId)
	{
		m_profileName = null;
		
		if(buttonId == IDialogConstants.OK_ID)
		{
			m_profileName = UiUtils.trimmedValue(m_profileNameText);
		}
		
		setReturnCode(buttonId);
		close();
	}
}
