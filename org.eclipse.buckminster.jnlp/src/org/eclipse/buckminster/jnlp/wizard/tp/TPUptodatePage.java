/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.wizard.tp;

import org.eclipse.buckminster.jnlp.MaterializationConstants;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;

/**
 * @author Karel Brezina
 *
 */
public class TPUptodatePage extends TPWizardPage
{
	private Composite m_pageComposite;

	private Label m_heading;

	private Label m_linkHeading;
	
	private String m_updateSiteURL;
	
	private Link m_updateSiteLink;

	protected TPUptodatePage()
	{
		super(MaterializationConstants.STEP_TP_UPTODATE, "Up-To-Date", "Eclipse installation is up-to-date.");
		setPreviousPage(this);
	}

	public void createControl(Composite parent)
	{
		m_pageComposite = new Composite(parent, SWT.NONE);
		m_pageComposite.setLayout(new GridLayout(1, false));
		m_pageComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		m_heading = new Label(m_pageComposite, SWT.WRAP);
		m_heading.setText("Your Eclipse installation is up-to-date.");

		new Label(m_pageComposite, SWT.NONE);

		m_linkHeading = new Label(m_pageComposite, SWT.WRAP);
		m_updateSiteLink = new Link(m_pageComposite, SWT.NONE);
		GridData layoutData = new GridData();
		layoutData.horizontalIndent = 20;
		m_updateSiteLink.setLayoutData(layoutData);
		m_updateSiteLink.addSelectionListener(new SelectionAdapter()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String linkURL = m_updateSiteURL;

				if(linkURL != null)
				{
					Program.launch(linkURL);
				}
			}
		});

		setControl(m_pageComposite);
	}

	@Override
	protected void beforeDisplaySetup()
	{
		// Text of the label is set here to be able to WRAP it - no idea how to do it nicer
		m_linkHeading
				.setText("To get the best experience with the materialized material in Eclipse install EclipseDistroTools from the following update site:");
		GridData layoutData = (GridData)m_linkHeading.getLayoutData();
		layoutData.widthHint = m_linkHeading.getShell().getSize().x - 30;	
		
		if(getTPWizard().getCurrentEclipseVersion().compareTo(TPWizard.getOSGi340Version()) >= 0)
			m_updateSiteURL = getTPWizard().getEclipseDistroTools34UpdateSiteURL();
		else
			m_updateSiteURL = getTPWizard().getEclipseDistroTools33UpdateSiteURL();

		m_updateSiteLink.setText("<a>" + m_updateSiteURL + "</a>");
		
		m_pageComposite.layout();
	}

	// Disable finish
	@Override
	public boolean isPageComplete()
	{
		if(getContainer().getCurrentPage() == this)
			return false;

		return true;
	}

	// Previous is disabled
	@Override
	public IWizardPage getPreviousPage()
	{
		return null;
	}

	// Next is disabled
	@Override
	public IWizardPage getNextPage()
	{
		return null;
	}

	@Override
	public String getOverrideCancelButtonText()
	{
		return "Done";
	}

	@Override
	public int getOverrideDefaultButtonId()
	{
		return IDialogConstants.CANCEL_ID;
	}
}
