/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.wizard.install;

import org.eclipse.buckminster.jnlp.MaterializationConstants;
import org.eclipse.buckminster.jnlp.MaterializationUtils;
import org.eclipse.buckminster.jnlp.Messages;
import org.eclipse.buckminster.jnlp.wizard.DestinationForm;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Karel Brezina
 * 
 */
public class SimpleDownloadPage extends InstallWizardPage
{
	private static final String TOOL_TIP_ADVANCED = Messages.customize_your_materialization;
	 	 
	private DestinationForm m_destinationForm;
	
	private Button m_advancedSettingsButton;
	
	protected SimpleDownloadPage()
	{
		super(MaterializationConstants.STEP_DOWNLOAD_LOCATION, Messages.select_a_destination, Messages.select_a_target_location_for_materialization,
				null);
	}

	public void createControl(Composite parent)
	{
		Composite pageComposite = new Composite(parent, SWT.NONE);
		pageComposite.setLayout(new GridLayout(3, false));
		pageComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		m_destinationForm = new DestinationForm(
				getMaterializationSpecBuilder(),
				MaterializationUtils.getDefaultDestination(getInstallWizard().getArtifactName()),
				false,
				false,
				false,
				false,
				true);
		
		m_destinationForm.createControl(pageComposite);

		m_advancedSettingsButton = new Button(pageComposite, SWT.CHECK);
		GridData gridData = new GridData();
		gridData.horizontalSpan = 3;
		gridData.verticalIndent = 10;
		m_advancedSettingsButton.setLayoutData(gridData);
		m_advancedSettingsButton.setText(Messages.advanced_settings);
		m_advancedSettingsButton.setToolTipText(TOOL_TIP_ADVANCED);
		m_advancedSettingsButton.setSelection(false);
		m_advancedSettingsButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				getContainer().updateButtons();
			}
		});
		setControl(pageComposite);
	}

	@Override
	protected void beforeDisplaySetup()
	{
		m_destinationForm.update();
	}
	
	@Override
	public boolean isPageComplete()
	{
		// disable FINISH button on the two first pages
		IWizardPage currentPage = getContainer().getCurrentPage();
		
		if(currentPage.equals(getInstallWizard().getStartingPage())
				|| currentPage.equals(getInstallWizard().getLoginPage())
				|| currentPage.equals(getInstallWizard().getSelectDistroPage())
				|| currentPage.equals(getInstallWizard().getFolderRestrictionPage()))
			return false;

		return getInstallWizard().isMaterializerInitialized();
	}
	
	@Override
	public IWizardPage getNextPage()
	{
		if(m_advancedSettingsButton.getSelection())
		{
			return getInstallWizard().getAdvancedPage();
		}
		return null;
	}
}
