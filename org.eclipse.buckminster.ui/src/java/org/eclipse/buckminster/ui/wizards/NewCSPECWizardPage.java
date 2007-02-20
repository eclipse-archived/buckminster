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
 * The "New" wizard page allows setting the container for the new file as well as the file name. The page will only
 * accept file name without the extension OR with the extension that matches the expected one (cspex).
 */

public class NewCSPECWizardPage extends NewBMFileWizardPage
{
	/**
	 * Constructor for NewCSPEXWizardPage.
	 * 
	 * @param pageName
	 */
	public NewCSPECWizardPage(ISelection selection)
	{
		super(selection, "new_cspec.cspec", "cspec");
		setTitle("New Buckminster Component Specification File");
		setDescription("This wizard creates a new file with *.cspec extension that can be opened by an XML or text editor. "
				+ "A CSPEC is used when a specification can not be generated.");
	}

}