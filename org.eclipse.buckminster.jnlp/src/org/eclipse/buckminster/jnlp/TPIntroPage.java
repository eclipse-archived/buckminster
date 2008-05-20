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
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Karel Brezina
 *
 */
public class TPIntroPage extends TPWizardPage
{
	private Label m_heading;
	
	private Button m_enableWizard;
	
	protected TPIntroPage()
	{
		super(MaterializationConstants.STEP_TP_INTRO, "Setup Eclipse", "Get the best experience with the materialized material in Eclipse.",
				null);
	}

	public void createControl(Composite parent)
	{
		Composite pageComposite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		pageComposite.setLayout(layout);
		GridData layoutData = new GridData();
		pageComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		m_heading = new Label(pageComposite, SWT.WRAP);
		layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		m_heading.setLayoutData(layoutData);
		
		Composite fillComposite = new Composite(pageComposite, SWT.NONE);
		fillComposite.setLayout(new FillLayout());
		
		m_enableWizard = new Button(pageComposite, SWT.CHECK);
		m_enableWizard.setText("Do not start this wizard next time");
		m_enableWizard.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				getTPWizard().enableWizardNextTime(m_enableWizard.getSelection());
			}
		});

		setControl(pageComposite);
	}

	@Override
	protected void beforeDisplaySetup()
	{
		// Text of the label is set here to be able to WRAP it - no idea how to do it nicer 
		m_heading.setText(
				"This wizard will help you to setup your Eclipse installation to get the best experience" +
				" with the materialized material in Eclipse.");
		GridData layoutData = (GridData)m_heading.getLayoutData();
		layoutData.widthHint = m_heading.getShell().getSize().x - 25;
		m_heading.getParent().layout();
	}
}
