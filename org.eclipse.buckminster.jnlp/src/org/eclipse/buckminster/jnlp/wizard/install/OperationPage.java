/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.wizard.install;

import org.eclipse.buckminster.jnlp.MaterializationConstants;
import org.eclipse.buckminster.jnlp.Messages;
import org.eclipse.buckminster.jnlp.progress.MaterializationProgressProvider;
import org.eclipse.core.runtime.jobs.ProgressProvider;
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
public class OperationPage extends InstallWizardPage
{
	private ProgressProvider m_progressProvider;
	
	protected OperationPage()
	{
		super(MaterializationConstants.STEP_OPERATION, Messages.materialization_in_progress, Messages.please_wait_until_materialization_finishes, null);
	}

	public void createControl(Composite parent)
	{
		Composite pageComposite = new Composite(parent, SWT.NONE);
		pageComposite.setLayout(new GridLayout(1, false));
		pageComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		m_progressProvider = new MaterializationProgressProvider(pageComposite);
/*
		Label imageLabel = new AlwaysEnabledLabel(pageComposite, SWT.NONE);
		
		Image image = getInstallWizard().getMaterializationImage();
		
		if(image != null)
		{
			imageLabel.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, true));
			imageLabel.setImage(image);
			imageLabel.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		}
*/
		setControl(pageComposite);
	}

	// Disable finish
	@Override
	public boolean isPageComplete()
	{
		if(getContainer().getCurrentPage() == this)
		{
			return false;
		}
		
		return true;
	}
	
	// Disable previous
    @Override
	public IWizardPage getPreviousPage()
    {
    	return null;
    }

	@Override
	public IWizardPage getNextPage()
	{
		return getWizard().getPage(MaterializationConstants.STEP_DONE);
	}
	
	public ProgressProvider getProgressProvider()
	{
		return m_progressProvider;
	}
}

// Label that cannot be disabled - disabled image has just two colors - it's awful
class AlwaysEnabledLabel extends Label
{

	public AlwaysEnabledLabel(Composite parent, int style)
	{
		super(parent, style);
	}
	
	@Override
	protected void checkSubclass ()
	{
		// do nothing - it should be quite safe to do this subclassing
	}
	
	@Override
	public void setEnabled(boolean enabled)
	{
		// do nothing - don't want to make it gray
	}
}
