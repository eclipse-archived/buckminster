package org.eclipse.buckminster.jnlp;

import org.eclipse.buckminster.core.mspec.builder.MaterializationNodeBuilder;
import org.eclipse.buckminster.jnlp.ui.general.editor.TableEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class AdvancedSettingsPage extends InstallWizardPage
{

	protected AdvancedSettingsPage()
	{
		super("AdvancedSettingsStep", "Advanced", "Set detailed properties for different parts of the materialization.", null);
	}

	public void createControl(Composite parent)
	{
		Composite pageComposite = new Composite(parent, SWT.NONE);
		pageComposite.setLayout(new GridLayout(1, false));
		pageComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		new TableEditor<MaterializationNodeBuilder>(
				pageComposite,
				new MaterializationNodeTable(getMaterializationSpecBuilder().getNodes()),
				getInstallWizard().getWindowImage(),
				getInstallWizard().getWindowTitle(),
				getInstallWizard().getWizardImage(),
				SWT.NONE);

		setControl(pageComposite);		
	}
}
