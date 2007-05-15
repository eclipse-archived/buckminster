/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 */
public class StartPage extends InstallWizardPage
{
	protected StartPage()
	{
		super("StartStep", "Materialize", "Do you want to materialize the following?", null);
	}

	public void createControl(Composite parent)
	{				
		Composite pageComposite = new Composite(parent, SWT.NONE);
		pageComposite.setLayout(new GridLayout(1, false));

		new Label(pageComposite, SWT.NONE).setText("Information from the producer:");

		Composite brandingComposite = new Composite(pageComposite, SWT.NONE);
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginHeight = gridLayout.marginWidth = 0;
		brandingComposite.setLayout(gridLayout);
		brandingComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		int hSpan = 2;
		if(getInstallWizard().getBrandingImage() != null)
		{
			Label brandingIcon = new Label(brandingComposite, SWT.NONE);
			brandingIcon.setImage(getInstallWizard().getBrandingImage());
			brandingIcon.setBackground(getShell().getDisplay().getSystemColor(SWT.COLOR_WHITE));
			hSpan = 1;
		}
		
		Text text = new Text(brandingComposite, SWT.BORDER | SWT.NO_FOCUS | SWT.MULTI | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		text.setText(getInstallWizard().getBrandingString());
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 30;
		gridData.horizontalSpan = hSpan;
		gridData.verticalAlignment = SWT.TOP;
		text.setLayoutData(gridData);
		text.setBackground(getShell().getDisplay().getSystemColor(SWT.COLOR_WHITE));

		new Label(pageComposite, SWT.NONE);
		
		new Label(pageComposite, SWT.NONE).setText("Information about the materialization:");

		text = new Text(pageComposite, SWT.BORDER | SWT.NO_FOCUS | SWT.MULTI | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		text.setText(getInstallWizard().getArtifactName());
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 30;
		text.setLayoutData(gridData);
		text.setBackground(getShell().getDisplay().getSystemColor(SWT.COLOR_WHITE));
		
		setControl(pageComposite);
	}
}
