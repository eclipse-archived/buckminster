/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
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

	// TODO remove
	// private Label m_infoLabel;

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

		// TODO remove
		/*
		 * m_locationText.addFocusListener(new FocusAdapter(){
		 * 
		 * @Override public void focusGained(FocusEvent e) { m_infoLabel.setText("Location"); m_infoLabel.pack(); }});
		 */

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

		// TODO remove
		/*
		 * m_advancedSettingsButton.addFocusListener(new FocusAdapter(){
		 * 
		 * @Override public void focusGained(FocusEvent e) { m_infoLabel.setText("Advanced settings");
		 * m_infoLabel.pack(); }});
		 * 
		 * Composite infoComposite = new Composite(pageComposite, SWT.NONE); GridData data = new
		 * GridData(GridData.FILL_BOTH); data.horizontalSpan = 2; infoComposite.setLayoutData(data);
		 * infoComposite.setLayout(new GridLayout());
		 * 
		 * Group infoGroup = new Group(pageComposite, SWT.BOTTOM); infoGroup.setText("Field Info");
		 * infoGroup.setLayout(new GridLayout()); data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		 * data.horizontalSpan = 2; infoGroup.setLayoutData(data);
		 * 
		 * 
		 * m_infoLabel = new Label(infoGroup, SWT.WRAP); //m_infoLabel.setText("This is a very important piece of
		 * information!! This is a very important piece of information!! This is a very important piece of
		 * information!!");
		 */
		setControl(pageComposite);
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
}
