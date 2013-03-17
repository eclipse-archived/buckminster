/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui.wizards;

import org.eclipse.buckminster.ui.Messages;
import org.eclipse.jface.viewers.ISelection;

/**
 * The "New" wizard page allows setting the container for the new file as well
 * as the file name. The page will only accept file name without the extension
 * OR with the extension that matches the expected one (cspex).
 */

public class NewCSPEXWizardPage extends NewBMFileWizardPage {
	/**
	 * Constructor for NewCSPEXWizardPage.
	 * 
	 * @param pageName
	 */
	public NewCSPEXWizardPage(ISelection selection) {
		super(selection, "buckminster.cspex", "cspex"); //$NON-NLS-1$ //$NON-NLS-2$
		setTitle(Messages.new_buckminster_component_specification_extension_file);
		setDescription(Messages.new_buckminster_cspex_wizard_explanation_article);
	}

}
