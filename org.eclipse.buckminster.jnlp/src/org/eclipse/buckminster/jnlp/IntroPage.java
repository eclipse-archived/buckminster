/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Thomas Hallgren
 *
 */
public class IntroPage extends WizardPage
{
	protected IntroPage()
	{
		super("Intro page");
	}

	public void createControl(Composite parent)
	{
		Label label = new Label(parent, SWT.HORIZONTAL);
		label.setText("Hello IntroPage");
		setControl(label);
	}
}
