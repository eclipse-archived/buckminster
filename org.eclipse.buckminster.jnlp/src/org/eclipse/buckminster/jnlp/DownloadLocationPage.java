/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import java.io.File;

import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.mspec.model.ConflictResolution;
import org.eclipse.buckminster.jnlp.ui.UiUtils;
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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 *
 */
public class DownloadLocationPage extends InstallWizardPage
{
	private Text m_locationText;

	private Combo m_materializerCombo;

	private Combo m_conflictCombo;
	
	private Button m_advancedSettingsButton;

	protected DownloadLocationPage()
	{
		super("SelectDownloadLocationStep", "Location", "Select materialization location.", null);
	}

	public void createControl(Composite parent)
	{
		Composite pageComposite = new Composite(parent, SWT.NONE);
		pageComposite.setLayout(new GridLayout(2, false));
		pageComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		setPageComplete(false);

		new Label(pageComposite, SWT.NONE).setText("Download Location:");

		Composite locationComposite = new Composite(pageComposite, SWT.NONE);

		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginHeight = gridLayout.marginWidth = 0;
		locationComposite.setLayout(gridLayout);
		locationComposite.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));

		MaterializationSpecBuilder builder = getMaterializationSpecBuilder();
		m_locationText = new Text(locationComposite, SWT.BORDER);
		m_locationText.setText(builder.getInstallLocation() == null
				? ""
				: builder.getInstallLocation().toOSString());
		m_locationText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_locationText.addModifyListener(new ModifyListener()
		{

			public void modifyText(ModifyEvent e)
			{
				getMaterializationSpecBuilder().setInstallLocation(Path.fromOSString(m_locationText.getText()));
				setErrorMessage(getCheckDirErrorMsg(m_locationText.getText()));
				getContainer().updateButtons();
			}
		});

		Button browseButton = new Button(locationComposite, SWT.PUSH);
		browseButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		browseButton.setText("Browse");
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

		new Label(pageComposite, SWT.NONE).setText("Materializer:");

		m_materializerCombo = UiUtils.createGridCombo(pageComposite, 0, 0, null, null, SWT.READ_ONLY);

		// TODO prepare materializers a better way
		// m_materializerCombo.setItems(AbstractMaterializer.getMaterializerIDs(false));
		m_materializerCombo.setItems(getInstallWizard().getMaterializers());

		int idx = 0;
		if(builder.getMaterializer() != null)
		{
			idx = m_materializerCombo.indexOf(builder.getMaterializer());

			if(idx < 0)
			{
				idx = 0;
			}
		}

		m_materializerCombo.select(idx);
		builder.setMaterializer(m_materializerCombo.getText());
		m_materializerCombo.addModifyListener(new ModifyListener()
		{

			public void modifyText(ModifyEvent e)
			{
				getMaterializationSpecBuilder().setMaterializer(m_materializerCombo.getText());
			}
		});

		new Label(pageComposite, SWT.NONE).setText("Conflict Resolution:");

		m_conflictCombo = UiUtils.createGridEnumCombo(pageComposite, 0, 0, ConflictResolution.values(), null, null,
				SWT.READ_ONLY);

		m_conflictCombo.select(builder.getConflictResolution() == null
				? ConflictResolution.getDefault().ordinal()
				: builder.getConflictResolution().ordinal());

		for(ConflictResolution cr : ConflictResolution.values())
		{
			m_conflictCombo.setData(String.valueOf(cr.ordinal()), cr);
		}

		builder.setConflictResolution((ConflictResolution)m_conflictCombo.getData(String.valueOf(m_conflictCombo
				.getSelectionIndex())));
		m_conflictCombo.addModifyListener(new ModifyListener()
		{

			public void modifyText(ModifyEvent e)
			{
				getMaterializationSpecBuilder().setConflictResolution(
						(ConflictResolution)m_conflictCombo
								.getData(String.valueOf(m_conflictCombo.getSelectionIndex())));
			}
		});

		m_advancedSettingsButton = new Button(pageComposite, SWT.CHECK);
		GridData gridData = new GridData();
		gridData.horizontalSpan = 2;
		gridData.verticalIndent = 10;
		m_advancedSettingsButton.setLayoutData(gridData);
		m_advancedSettingsButton.setText("Advanced Settings");		
		m_advancedSettingsButton.setSelection(builder.getNodes().size() > 0); 
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
	public IWizardPage getNextPage()
	{
		if(m_advancedSettingsButton.getSelection())
		{
			return getWizard().getPage("AdvancedSettingsStep");
		}
		return null;
	}
	
	@Override
	public boolean isPageComplete()
	{
		IPath location = getMaterializationSpecBuilder().getInstallLocation();
		if(location == null)
			return false;
		
		if(getCheckDirErrorMsg(location.toOSString()) != null)
		{
			return false;
		}

		if(getMaterializationSpecBuilder().getConflictResolution() == null)
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * Checks directory - if it's OK, then returns null otherwise it returns error text
	 * @param dir
	 * @return
	 */
	private String getCheckDirErrorMsg(String dir)
	{
		
		if(dir != null && dir.length() > 0)
		{
			File file = new File(dir);

			if(!file.exists() || !file.isDirectory())
			{
				return "Directory '" + m_locationText.getText() + "' does not exist";
			}
		}
		else
		{
			return "Directory location cannot be empty";
		}
		
		return null;
	}
	

}
