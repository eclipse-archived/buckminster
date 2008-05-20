/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import org.eclipse.buckminster.jnlp.ui.general.wizard.AdvancedWizardPage;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * @author Karel Brezina
 *
 */
abstract public class TPWizardPage extends AdvancedWizardPage
{
	protected TPWizardPage(String pageName)
	{
		super(pageName);
	}

	protected TPWizardPage(String pageName, String title, String message, ImageDescriptor titleImage)
	{
		super(pageName, title, message, titleImage);
	}

	TPWizard getTPWizard()
	{
		return (TPWizard)getAdvancedWizard();
	}
}
