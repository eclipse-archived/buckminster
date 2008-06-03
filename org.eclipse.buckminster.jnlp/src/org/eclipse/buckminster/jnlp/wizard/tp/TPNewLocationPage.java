/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.wizard.tp;

import java.io.File;

import org.eclipse.buckminster.jnlp.JNLPException;
import org.eclipse.buckminster.jnlp.MaterializationConstants;
import org.eclipse.buckminster.jnlp.MaterializationUtils;
import org.eclipse.buckminster.jnlp.ui.UiUtils;
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
public class TPNewLocationPage extends TPWizardPage
{
	private static final String TOOL_TIP_DESTINATION_ADDRESS = "Destination directory for materialization of the new Eclipse";

	private static final String TOOL_TIP_BROWSE_DESTINATION_ADDRESS = "Browse destination directory for materialization of the new Eclipse";

	private Composite m_pageComposite;

	private Text m_locationText;

	private Button m_browseButton;

	protected TPNewLocationPage()
	{
		super(MaterializationConstants.STEP_TP_NEW_LOCATION, "Destination Address",
				"Select a destination address for the new Eclipse installation.");
	}

	public void createControl(Composite parent)
	{
		m_pageComposite = new Composite(parent, SWT.NONE);
		m_pageComposite.setLayout(new GridLayout(3, false));
		m_pageComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label label = new Label(m_pageComposite, SWT.NONE);
		label.setText("Destination Address:");
		label.setToolTipText(TOOL_TIP_DESTINATION_ADDRESS);

		m_locationText = new Text(m_pageComposite, SWT.BORDER);
		m_locationText.setToolTipText(TOOL_TIP_DESTINATION_ADDRESS);
		m_locationText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_locationText.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				firePageChanged();
			}
		});

		m_browseButton = new Button(m_pageComposite, SWT.PUSH);
		m_browseButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		m_browseButton.setText("Browse");
		m_browseButton.setToolTipText(TOOL_TIP_BROWSE_DESTINATION_ADDRESS);
		m_browseButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				DirectoryDialog dlg = new DirectoryDialog(m_browseButton.getShell());
				dlg.setFilterPath(MaterializationUtils.getKnownPath(m_locationText.getText()));
				String dir = dlg.open();

				if(dir != null)
				{
					m_locationText.setText(dir);
				}
			}
		});

		setControl(m_pageComposite);
	}

	@Override
	public IWizardPage getNextPage()
	{
		if(getTPWizard().getEclipseFolder() != null && new File(getTPWizard().getEclipseFolder()).exists())
			return getTPWizard().getBackupFolderPage();

		return null;
	}

	@Override
	public boolean isPageComplete()
	{
		return getTPWizard().getNewOrCurrentPage().isPageCommitted()
				&& (!getTPWizard().isNewEclipse() || UiUtils.trimmedValue(m_locationText) != null);
	}

	@Override
	public boolean performPageCommit()
	{
		setErrorMessage(null);

		if(getContainer().getCurrentPage() == this)
			try
			{
				File destinationFolder = new File(UiUtils.trimmedValue(m_locationText));

				if(!destinationFolder.exists() || !destinationFolder.isDirectory())
					throw new JNLPException("Selected destination directory does not exist", null);
			}
			catch(JNLPException e)
			{
				setErrorMessage(e.getMessage());
				return false;
			}

		return true;
	}

	String getDestinationFolder()
	{
		return UiUtils.trimmedValue(m_locationText);
	}

	private void firePageChanged()
	{
		uncommitPage();
		getContainer().updateButtons();
	}
}
