/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui.wizards;

import org.eclipse.jface.viewers.ISelection;

/**
 * The "New" wizard page allows setting the container for the new file as well as the file name. 
 * The page will only
 * accept file name without the extension OR with the extension that matches the expected one (distro).
 */

public class NewDistroWizardPage extends NewBMFileWizardPage
{
	/**
	 * Constructor for NewDistroWizardPage.
	 * 
	 * @param selection what is currently selected
	 */
	public NewDistroWizardPage(ISelection selection)
	{
		super(selection, "new_distro.distro", "distro");
		setTitle("New Buckminster Distro Editor File");
		setDescription("This wizard creates a new *.distro file that can be opened by a Distro editor. "+
				"A Distro is used to create a Distribution of components that can be " +
				"shared and materialized).");

	}
}
