/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_ARTIFACT_EXCEPTION;

import java.lang.reflect.InvocationTargetException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
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
	private static final String TOOL_TIP_ADVANCED = "Customize your materialization";
	 	 
	private DestinationForm m_destinationForm;
	
	private Button m_advancedSettingsButton;
	
	protected SimpleDownloadPage()
	{
		super(MaterializationConstants.STEP_DOWNLOAD_LOCATION, "Select a Destination", "Select a target location for materialization.",
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
		m_advancedSettingsButton.setText("Advanced Settings");
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
		if(!getInstallWizard().isMaterializerInitialized())
		{
			// read MSPEC and BOM after login 
			try
			{
				getContainer().run(true, false, new IRunnableWithProgress()
				{
	
					public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
					{
						monitor.beginTask(null, IProgressMonitor.UNKNOWN);
						monitor.subTask("Retrieving materialization specification");
						getInstallWizard().initializeMaterializer();
						getInstallWizard().initMSpecTree();
						monitor.done();
					}
				});
			}
			catch(Exception e)
			{
				setPageComplete(false);
	
				if(e instanceof JNLPException)
				{
					throw (JNLPException) e;
				}
				
				if(e.getCause() != null && e.getCause() instanceof JNLPException)
				{
					JNLPException jnlpException = (JNLPException)e.getCause();
					
					// Forbidden - show login page
					if(MaterializationConstants.ERROR_CODE_403_EXCEPTION.equals(jnlpException.getErrorCode()))
					{
						LoginPage loginPage = (LoginPage)getWizard().getPage(MaterializationConstants.STEP_LOGIN);
						getContainer().showPage(loginPage);
					}
					
					throw jnlpException;
				}
				
				throw new JNLPException(
						"Error while reading artifact specification", ERROR_CODE_ARTIFACT_EXCEPTION, e);
	
			}
			finally
			{
				getContainer().updateButtons();
			}			
		}

		m_destinationForm.update();
	}
	
	@Override
	public boolean isPageComplete()
	{
		// disable FINISH button on the two first pages
		if(getWizard().getContainer().getCurrentPage().equals(getWizard().getPage(MaterializationConstants.STEP_START)) ||
				getWizard().getContainer().getCurrentPage().equals(getWizard().getPage(MaterializationConstants.STEP_LOGIN)))
			return false;
		
		return getInstallWizard().isMaterializerInitialized();
	}
	
	@Override
	public IWizardPage getNextPage()
	{
		if(m_advancedSettingsButton.getSelection())
		{
			return getWizard().getPage(MaterializationConstants.STEP_ADVANCED_SETTINGS);
		}
		return null;
	}
}
