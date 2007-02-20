/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui.wizards;

import org.eclipse.ui.INewWizard;

/**
 * This is a sample new wizard. Its role is to create a new file resource in the provided container. If the container
 * resource (a folder or a project) is selected in the workspace when the wizard is opened, it will accept it as the
 * target container. The wizard creates one file with the extension "cquery". If a sample multi-page editor (also
 * available as a template) is registered for the same extension, it will be able to open it.
 */

public class NewCQueryWizard extends NewBMFileWizard implements INewWizard
{
	/**
	 * Constructor for NewCQueryWizard.
	 */
	public NewCQueryWizard()
	{
		super();
	}

	/**
	 * Adding the page to the wizard.
	 */

	@Override
	public void addPages()
	{
		setPage(new NewCQueryWizardPage(getSelection()));
	}
}
