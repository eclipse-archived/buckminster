/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.program.Program;

/**
 * @author Thomas Hallgren
 */
abstract class InstallWizardPage extends WizardPage
{
	protected InstallWizardPage(String pageName)
	{
		super(pageName);
	}

	protected InstallWizardPage(String pageName, String title, String message, ImageDescriptor titleImage)
	{
		super(pageName, title, titleImage);
		setMessage(message);
	}

	InstallWizard getInstallWizard()
	{
		return (InstallWizard)getWizard();
	}

	MaterializationSpecBuilder getMaterializationSpecBuilder()
	{
		return getInstallWizard().getMaterializationSpecBuilder();
	}
	
	void displayException(Throwable e)
	{
		CorePlugin.getLogger().warning(e.getMessage(), e);
		setErrorMessage(e.getMessage());
	}
		
	@Override
	public void performHelp()
	{
		String helpURL = getInstallWizard().getHelpURL();
		
		if(helpURL != null)
		{
			Program.launch(getInstallWizard().getHelpURL());
		}
	}

}
