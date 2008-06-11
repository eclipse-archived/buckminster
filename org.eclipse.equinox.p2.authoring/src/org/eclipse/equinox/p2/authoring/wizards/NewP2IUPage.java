/*******************************************************************************
 * Copyright (c) 2008
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed below, as Initial Contributors under such license.
 * The text of such license is available at 
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 * 		Henrik Lindberg
 *******************************************************************************/
package org.eclipse.equinox.p2.authoring.wizards;

import org.eclipse.buckminster.ui.wizards.NewBMFileWizardPage;
import org.eclipse.jface.viewers.ISelection;


public class NewP2IUPage extends NewBMFileWizardPage
{
	/**
	 * Constructor for NewDistroWizardPage.
	 * 
	 * @param selection what is currently selected
	 */
	public NewP2IUPage(ISelection selection)
	{
		super(selection, "new_installable", "iu"); 
		setTitle("New p2 Installable Unit File");
		setDescription("Creates an Installable Unit File (.iu) that can be edited, published to " +
				"a meta data repository, and be installed.");

	}
}
