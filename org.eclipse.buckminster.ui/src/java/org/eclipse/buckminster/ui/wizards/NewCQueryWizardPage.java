/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ui.wizards;

import org.eclipse.buckminster.ui.Messages;
import org.eclipse.jface.viewers.ISelection;

/**
 * The "New" wizard page allows setting the container for the new file as well as the file name. The page will only
 * accept file name without the extension OR with the extension that matches the expected one (cquery).
 */

public class NewCQueryWizardPage extends NewBMFileWizardPage
{
	/**
	 * Constructor for NewCQueryWizardPage.
	 * 
	 * @param pageName
	 */
	public NewCQueryWizardPage(ISelection selection)
	{
		super(selection, "new_query.cquery", "cquery"); //$NON-NLS-1$ //$NON-NLS-2$
		setTitle(Messages.new_buckminster_component_query_editor_file);
		setDescription(Messages.new_buckminster_cquery_wizard_explanation_article);

	}
}
