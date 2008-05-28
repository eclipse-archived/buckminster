/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.wizard.tp;

import org.eclipse.buckminster.jnlp.MaterializationConstants;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Karel Brezina
 *
 */
public class TPNewRecommendedPage extends TPWizardPage
{
	private Composite m_pageComposite;
	
	private Label m_heading;
	
	private Button m_newEclipseButton;

	private Button m_currentEclipseButton;

	protected TPNewRecommendedPage()
	{
		super(MaterializationConstants.STEP_TP_NEW_RECOMMENDED, "New Eclipse is recommended",
				"It is recommended to install the new version of Eclipse.");
	}

	public void createControl(Composite parent)
	{
		m_pageComposite = new Composite(parent, SWT.NONE);
		m_pageComposite.setLayout(new GridLayout(1, false));
		m_pageComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		m_heading = new Label(m_pageComposite, SWT.WRAP);

		new Label(m_pageComposite, SWT.NONE);

		m_newEclipseButton = new Button(m_pageComposite, SWT.RADIO);
		m_newEclipseButton.setText("Install New Eclipse");

		m_currentEclipseButton = new Button(m_pageComposite, SWT.RADIO);
		m_currentEclipseButton.setText("Use Current Eclipse");

		SelectionListener radioListener = new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				firePageChanged();
			}
		};

		m_newEclipseButton.addSelectionListener(radioListener);
		m_currentEclipseButton.addSelectionListener(radioListener);

		m_newEclipseButton.setSelection(true);
		
		setControl(m_pageComposite);
	}

	@Override
	protected void beforeDisplaySetup()
	{
		// Text of the label is set here to be able to WRAP it - no idea how to do it nicer 
		m_heading.setText(
				"You are currently using Eclipse " + getTPWizard().getCurrentEclipseVersion() + ". It is recommended to install Eclipse " + getTPWizard().getProvidedEclipseVersion() + " to get the latest features.");
		GridData layoutData = (GridData)m_heading.getLayoutData();
		layoutData.widthHint = m_heading.getShell().getSize().x - 30;
		m_pageComposite.layout();
	}

	private void firePageChanged()
	{
		uncommitPage();
	}
	
	@Override
	public boolean performPageCommit()
	{
		if(getContainer().getCurrentPage() == this)
			getTPWizard().setNewEclipse(m_newEclipseButton.getSelection());
		
		return true;
	}
	
	@Override
	public IWizardPage getNextPage()
	{
		if(m_newEclipseButton.getSelection())
			return getTPWizard().getNewLocationPage();
		
		return getTPWizard().getToolsSelectionPage();
	}
}
