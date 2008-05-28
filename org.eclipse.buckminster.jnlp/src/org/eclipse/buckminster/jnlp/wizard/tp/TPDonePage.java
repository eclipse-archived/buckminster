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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Karel Brezina
 *
 */
public class TPDonePage extends TPWizardPage
{
	private Composite m_pageComposite;

	private Label m_heading;

	protected TPDonePage()
	{
		super(MaterializationConstants.STEP_TP_DONE, "Materialization Completed", "Close the materialization dialog.");
		setPreviousPage(this);
	}

	public void createControl(Composite parent)
	{
		m_pageComposite = new Composite(parent, SWT.NONE);
		m_pageComposite.setLayout(new GridLayout(1, false));
		m_pageComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		m_heading = new Label(m_pageComposite, SWT.WRAP);
		m_heading.setText("Eclipse installation was successfully set.");

		setControl(m_pageComposite);
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
