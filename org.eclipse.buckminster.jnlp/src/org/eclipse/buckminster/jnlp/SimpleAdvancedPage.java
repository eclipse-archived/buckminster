/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.jface.wizard.IWizardPage;
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
public class SimpleAdvancedPage extends InstallWizardPage
{
	private MSpecDetailsPanel m_detailsPanel;
	
	protected SimpleAdvancedPage()
	{
		super(MaterializationConstants.STEP_ADVANCED_SETTINGS, "Advanced", "Select components for materialization.", null);
	}

	@Override
	protected void beforeDisplaySetup()
	{
		m_detailsPanel.update();
	}
	
	public void createControl(Composite parent)
	{
		Composite pageComposite = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginHeight = gridLayout.marginWidth = 0;
		pageComposite.setLayout(gridLayout);
		pageComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		m_detailsPanel = new MSpecDetailsPanel(
				getMaterializationSpecBuilder(),
				MaterializationUtils.getDefaultDestination(getInstallWizard().getArtifactName()),
				true);
		
		m_detailsPanel.createControl(pageComposite);
		
		new Label(pageComposite, SWT.NONE);

		Composite publishComposite = new Composite(pageComposite, SWT.NONE);
		gridLayout = new GridLayout(1, false);
		gridLayout.marginHeight = gridLayout.marginWidth = 0;
		publishComposite.setLayout(gridLayout);
		GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		gridData.horizontalSpan = 3;
		publishComposite.setLayoutData(gridData);
		
		Button publishButton = new Button(publishComposite, SWT.PUSH);
		publishButton.setText("Publish as a Distro");
		gridData = new GridData(SWT.END, SWT.CENTER, true, false);
		publishButton.setLayoutData(gridData);
		publishButton.addSelectionListener(new SelectionAdapter(){

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				MaterializationUtils.startPublishingWizard(getInstallWizard(), getShell());
			}});
		
		setControl(pageComposite);
	}
	
	void initializeMSpecTree(BillOfMaterials bom)
	{
		m_detailsPanel.initializeMSpecTree(bom);
	}
	
	@Override
	public IWizardPage getNextPage()
	{
		return null;
	}
}
