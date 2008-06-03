/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.wizard.tp;

import java.io.File;

import org.eclipse.buckminster.jnlp.MaterializationConstants;
import org.eclipse.buckminster.jnlp.MaterializationUtils;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Karel Brezina
 * 
 */
public class TPBackupFolderPage extends TPWizardPage
{
	private Composite m_pageComposite;

	private Label m_heading;

	private Button m_agreeButton;

	private Button m_disagreeButton;

	protected TPBackupFolderPage()
	{
		super(MaterializationConstants.STEP_TP_BACKUP_FOLDER, "Backup Folder",
				"The current eclipse folder will be backed up.");
	}

	public void createControl(Composite parent)
	{
		m_pageComposite = new Composite(parent, SWT.NONE);
		m_pageComposite.setLayout(new GridLayout(1, false));
		m_pageComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		m_heading = new Label(m_pageComposite, SWT.WRAP);

		new Label(m_pageComposite, SWT.NONE);

		m_agreeButton = new Button(m_pageComposite, SWT.RADIO);
		m_agreeButton.setText("Agree");

		m_disagreeButton = new Button(m_pageComposite, SWT.RADIO);
		m_disagreeButton.setText("Disagree");

		SelectionListener radioListener = new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				firePageChanged();
			}
		};

		m_agreeButton.addSelectionListener(radioListener);
		m_disagreeButton.addSelectionListener(radioListener);

		m_agreeButton.setSelection(true);

		setControl(m_pageComposite);
	}

	@Override
	public IWizardPage getNextPage()
	{
		return null;
	}

	@Override
	public boolean isPageComplete()
	{
		return m_agreeButton.getSelection();
	}

	@Override
	protected void beforeDisplaySetup()
	{
		File eclipseFolder = new File(getTPWizard().getEclipseFolder());
		File backupFolder = MaterializationUtils.getBackupFolder(eclipseFolder);
		String backupName = new Path(backupFolder.getPath()).lastSegment();

		// Text of the label is set here to be able to WRAP it - no idea how to do it nicer
		m_heading.setText("The selected destination folder already contains an Eclipse folder. "
				+ "This folder will be backed up to " + backupName + ".");
		GridData layoutData = (GridData)m_heading.getLayoutData();
		layoutData.widthHint = m_heading.getShell().getSize().x - 30;
		m_pageComposite.layout();
	}

	private void firePageChanged()
	{
		getContainer().updateButtons();
	}
}
