/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import java.io.File;
import java.util.Arrays;

import org.eclipse.buckminster.core.materializer.IMaterializer;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.jnlp.ui.UiUtils;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 *
 */
public class DestinationForm
{
	private final static String[] DESTINATION_TYPES = {IMaterializer.FILE_SYSTEM, IMaterializer.WORKSPACE, IMaterializer.TARGET_PLATFORM};

	private final static String[] DESTINATION_TYPES_TO_SHOW = {"Filesystem", "Workspace", "Eclipse Installation"};

	private static final String TOOL_TIP_TYPE = "Default destination type for materialization: Filesystem, Workspace, Eclipse Installation";
	 
	private static final String TOOL_TIP_DIRECTORY = "Default destination directory for materialization";
	 
	private static final String TOOL_TIP_BROWSE_DIRECTORY = "Browse default destination directory for materialization";
	 
	private InstallWizard m_installWizard;
	
	private MaterializationSpecBuilder m_mspec;
	
	private Combo m_destTypeCombo;
	
	private Text m_locationText;

	public DestinationForm(InstallWizard installWizard, MaterializationSpecBuilder builder)
	{
		m_installWizard = installWizard;
		m_mspec = builder;
	}

	public void createControl(Composite parent)
	{
		Label label = new Label(parent, SWT.NONE);
		label.setText("Destination Type:");
		label.setToolTipText(TOOL_TIP_TYPE);

		m_destTypeCombo = UiUtils.createGridArrayCombo(parent, 0, 0, DESTINATION_TYPES_TO_SHOW, null, null,
				SWT.READ_ONLY);

		new Label(parent, SWT.NONE);
		
		m_destTypeCombo.setToolTipText(TOOL_TIP_TYPE);

		for(int i = 0; i < DESTINATION_TYPES.length; i++)
			m_destTypeCombo.setData(String.valueOf(i), DESTINATION_TYPES[i]);

		m_destTypeCombo.addModifyListener(new ModifyListener()
		{

			public void modifyText(ModifyEvent e)
			{
				m_mspec.setMaterializer(
						(String)m_destTypeCombo.getData(String.valueOf(m_destTypeCombo.getSelectionIndex())));
			}
		});


		label = new Label(parent, SWT.NONE);
		label.setText("Destination Address:");
		label.setToolTipText(TOOL_TIP_DIRECTORY);

		m_locationText = new Text(parent, SWT.BORDER);
		m_locationText.setText(m_mspec.getInstallLocation() == null
				? ""
				: m_mspec.getInstallLocation().removeTrailingSeparator().toOSString());
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

				m_mspec.setInstallLocation(path);
			}
		});

		final Button browseButton = new Button(parent, SWT.PUSH);
		browseButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		browseButton.setText("Browse");
		browseButton.setToolTipText(TOOL_TIP_BROWSE_DIRECTORY);
		browseButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				DirectoryDialog dlg = new DirectoryDialog(browseButton.getShell());
				dlg.setFilterPath(m_locationText.getText());
				String dir = dlg.open();

				if(dir != null)
				{
					m_locationText.setText(dir);
				}
			}
		});
	}
	
	public void setup()
	{
		int defaultIdx = 0;
		if(m_mspec.getMaterializer() != null)
			defaultIdx = Arrays.asList(DESTINATION_TYPES).indexOf(m_mspec.getMaterializer());

		m_destTypeCombo.select(defaultIdx);
		m_mspec.setMaterializer(DESTINATION_TYPES[defaultIdx]);
		
		if(m_mspec.getInstallLocation() == null)
		{
			String defaultDestination = getDefaultDestination();
			
			if(defaultDestination != null)
			{
				m_mspec.setInstallLocation(
							Path.fromOSString(defaultDestination).addTrailingSeparator());
				m_locationText.setText(defaultDestination);
			}	
		}
		else
		{
			m_locationText.setText(m_mspec.getInstallLocation().removeTrailingSeparator().toOSString());
		}

	}
	
	private String getDefaultDestination() throws JNLPException
	{
		String destination = null;
		
		String userHome = System.getProperty("user.home");

		if(userHome != null)
		{
			destination = userHome + File.separatorChar + "materializations";
		
			if(m_installWizard.getArtifactName() != null)
				destination += File.separatorChar + m_installWizard.getArtifactName();
		}
		return destination;
	}
}
