/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.wizard.tp;

import org.eclipse.buckminster.jnlp.MaterializationConstants;
import org.eclipse.buckminster.jnlp.progress.MaterializationProgressProvider;
import org.eclipse.core.runtime.jobs.ProgressProvider;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Karel Brezina
 * 
 */
public class TPOperationPage extends TPWizardPage
{
	private ProgressProvider m_progressProvider;

	protected TPOperationPage()
	{
		super(MaterializationConstants.STEP_TP_OPERATION, "Materialization in Progress",
				"Please wait until materialization finishes.");
	}

	public void createControl(Composite parent)
	{
		Composite pageComposite = new Composite(parent, SWT.NONE);
		pageComposite.setLayout(new GridLayout(1, false));
		pageComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		m_progressProvider = new MaterializationProgressProvider(pageComposite);

		setControl(pageComposite);
	}

	@Override
	public IWizardPage getNextPage()
	{
		return getWizard().getPage(MaterializationConstants.STEP_TP_DONE);
	}

	// Disable previous
	@Override
	public IWizardPage getPreviousPage()
	{
		return null;
	}

	public ProgressProvider getProgressProvider()
	{
		return m_progressProvider;
	}

	// Disable finish
	@Override
	public boolean isPageComplete()
	{
		if(getContainer().getCurrentPage() == this)
			return false;

		return true;
	}
}
