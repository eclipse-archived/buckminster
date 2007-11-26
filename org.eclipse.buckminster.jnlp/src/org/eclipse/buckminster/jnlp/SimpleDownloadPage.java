/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_ARTIFACT_SAX_EXCEPTION;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 * 
 */
public class SimpleDownloadPage extends InstallWizardPage
{
	private static final String TOOL_TIP_DIRECTORY = "Default destination directory for materialization";
	 
	private static final String TOOL_TIP_BROWSE_DIRECTORY = "Browse default destination directory for materialization";
	 
	private static final String TOOL_TIP_ADVANCED = "Customize your materialization";
	 	 
	private Text m_locationText;

	private Button m_advancedSettingsButton;

	protected SimpleDownloadPage()
	{
		super("SimpleDownloadLocationStep", "Select a Destination", "Select a target location for materialization.",
				null);
	}

	public void createControl(Composite parent)
	{
		// parent.getParent().getChildren()[2].dispose();

		Composite pageComposite = new Composite(parent, SWT.NONE);
		pageComposite.setLayout(new GridLayout(2, false));
		pageComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label label = new Label(pageComposite, SWT.NONE);
		label.setText("Destination Address:");
		label.setToolTipText(TOOL_TIP_DIRECTORY);

		Composite locationComposite = new Composite(pageComposite, SWT.NONE);

		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginHeight = gridLayout.marginWidth = 0;
		locationComposite.setLayout(gridLayout);
		locationComposite.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));

		MaterializationSpecBuilder builder = getMaterializationSpecBuilder();
		m_locationText = new Text(locationComposite, SWT.BORDER);
		m_locationText.setText(builder.getInstallLocation() == null
				? ""
				: builder.getInstallLocation().removeTrailingSeparator().toOSString());
		m_locationText.setToolTipText(TOOL_TIP_DIRECTORY);
		m_locationText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_locationText.addModifyListener(new ModifyListener()
		{

			public void modifyText(ModifyEvent e)
			{
				String pathStr = m_locationText.getText();
				IPath path = (pathStr == null || pathStr.length() == 0)
						? null
						: Path.fromOSString(pathStr).addTrailingSeparator();

				getMaterializationSpecBuilder().setInstallLocation(path);
			}
		});

		Button browseButton = new Button(locationComposite, SWT.PUSH);
		browseButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		browseButton.setText("Browse");
		browseButton.setToolTipText(TOOL_TIP_BROWSE_DIRECTORY);
		browseButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				DirectoryDialog dlg = new DirectoryDialog(getShell());
				dlg.setFilterPath(m_locationText.getText());
				String dir = dlg.open();

				if(dir != null)
				{
					m_locationText.setText(dir);
				}
			}
		});

		m_advancedSettingsButton = new Button(pageComposite, SWT.CHECK);
		GridData gridData = new GridData();
		gridData.horizontalSpan = 2;
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
		
		getInstallWizard().addMSpecChangeListener(new MSpecChangeListener()
		{
			public void handleMSpecChangeEvent(MSpecChangeEvent event)
			{
				m_locationText.setText(event.getMSpec().getInstallLocation() == null
						? ""
						: event.getMSpec().getInstallLocation().removeTrailingSeparator().toOSString());
			}
		});
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
						monitor.done();
					}
				});
				
				((SimpleAdvancedPage)getInstallWizard().getPage("SimpleAdvancedSettingsStep")).initializeMSpecTree();
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
					throw ((JNLPException)e.getCause());
				}
				
				throw new JNLPException(
						"Error while reading artifact specification", ERROR_CODE_ARTIFACT_SAX_EXCEPTION, e);

			}
			finally
			{
				getContainer().updateButtons();
			}			
		}
		
		if(getMaterializationSpecBuilder().getInstallLocation() == null)
		{
			String defaultDestination = getDefaultDestination();
			
			if(defaultDestination != null)
			{
				getMaterializationSpecBuilder().setInstallLocation(
							Path.fromOSString(defaultDestination).addTrailingSeparator());
				m_locationText.setText(defaultDestination);
			}	
		}
	}
	
	@Override
	public boolean isPageComplete()
	{
		return getInstallWizard().isMaterializerInitialized();
	}
	
	@Override
	public IWizardPage getNextPage()
	{
		if(m_advancedSettingsButton.getSelection())
		{
			return getWizard().getPage("SimpleAdvancedSettingsStep");
		}
		return null;
	}
	
	private String getDefaultDestination() throws JNLPException
	{
		String destination = null;
		
		String userHome = System.getProperty("user.home");

		if(userHome != null)
		{
			destination = userHome + File.separatorChar + "materializations";
		
			if(getInstallWizard().getArtifactName() != null)
				destination += File.separatorChar + getInstallWizard().getArtifactName();
		}
		return destination;
	}
}
