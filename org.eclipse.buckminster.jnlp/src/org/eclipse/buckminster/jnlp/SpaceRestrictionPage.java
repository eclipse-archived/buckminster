/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Karel Brezina
 *
 */
public class SpaceRestrictionPage extends InstallWizardPage
{
	private static final int HORIZONTAL_SPACING = 15;

	private static final String ICON_WARNING = "error.png";

	private static final String ICON_DOT = "brkp_obj.gif";

	private Button m_acceptedButton;

	protected SpaceRestrictionPage()
	{
		super(MaterializationConstants.STEP_RESTRICTION, "Space Restriction", "Materialization space has a restrictive access.",
				null);
	}

	public void createControl(Composite parent)
	{
		Composite pageComposite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		pageComposite.setLayout(layout);

		Composite warningComposite = new Composite(pageComposite, SWT.NONE);
		layout = new GridLayout(2, false);
		layout.horizontalSpacing = HORIZONTAL_SPACING;
		warningComposite.setLayout(layout);
		
		Label label = new Label(warningComposite, SWT.NONE);
		label.setImage(getInstallWizard().getImage(ICON_WARNING));
		GridData layoutData = new GridData();

		label = new Label(warningComposite, SWT.WRAP);
		label.setText("Access to the materialization space is forbidden");
		
		new Label(pageComposite, SWT.NONE);

		new Label(pageComposite, SWT.NONE).setText("Solution:");

		Composite solutionComposite = new Composite(pageComposite, SWT.NONE);
		layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 0;
		solutionComposite.setLayout(layout);
		solutionComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		label = new Label(solutionComposite, SWT.NONE);
		label.setImage(getInstallWizard().getImage(ICON_DOT));
		layoutData = new GridData();
		layoutData.verticalAlignment = GridData.CENTER;
		label.setLayoutData(layoutData);

		new Label(solutionComposite, SWT.WRAP).setText("Confirm email Verifycation");
		
		label = new Label(solutionComposite, SWT.NONE);
		label.setImage(getInstallWizard().getImage(ICON_DOT));
		layoutData = new GridData();
		layoutData.verticalAlignment = GridData.CENTER;
		label.setLayoutData(layoutData);

		new Label(solutionComposite, SWT.WRAP).setText("Login to " + getInstallWizard().getServiceProvider() + " and accept the invitation");
		
		Composite fillerComposite = new Composite(pageComposite, SWT.NONE);
		fillerComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		m_acceptedButton = new Button(pageComposite, SWT.CHECK);
		m_acceptedButton.setText("I have accepted the invitation at " + getInstallWizard().getServiceProvider());
		m_acceptedButton.addSelectionListener(new SelectionAdapter(){

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				getContainer().updateButtons();
			}
		});

		setControl(pageComposite);
	}

	@Override
	public boolean isPageComplete()
	{
		return m_acceptedButton.getSelection();
	}

}
