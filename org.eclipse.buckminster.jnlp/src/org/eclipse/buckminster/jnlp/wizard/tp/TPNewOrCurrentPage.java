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
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 * 
 */
public class TPNewOrCurrentPage extends TPWizardPage
{
	private static final String TOOL_TIP_ECLIPSE_LOCATION = "Location of the current Eclipse";

	private static final String TOOL_TIP_BROWSE_ECLIPSE_LOCATION = "Browse location of the current Eclipse";

	private Composite m_pageComposite;
	
	private Button m_newEclipseButton;

	private Button m_currentEclipseButton;

	private Text m_locationText;

	private Button m_browseButton;

	protected TPNewOrCurrentPage()
	{
		super(MaterializationConstants.STEP_TP_INTRO, "New or Current Eclipse",
				"Do you want to install a new Elipse or use the current one?");
	}

	public void createControl(Composite parent)
	{
		m_pageComposite = new Composite(parent, SWT.NONE);
		m_pageComposite.setLayout(new GridLayout(1, false));
		m_pageComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		m_newEclipseButton = new Button(m_pageComposite, SWT.RADIO);
		m_newEclipseButton.setText("Install New Eclipse");
		GridData gridData = new GridData();
		gridData.horizontalSpan = 1;
		m_newEclipseButton.setLayoutData(gridData);

		new Label(m_pageComposite, SWT.NONE);

		m_currentEclipseButton = new Button(m_pageComposite, SWT.RADIO);
		m_currentEclipseButton.setText("Use Current Eclipse");
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		m_currentEclipseButton.setLayoutData(gridData);

		Group currentEclipseGroup = new Group(m_pageComposite, SWT.NONE);
		currentEclipseGroup.setLayout(new GridLayout(3, false));
		currentEclipseGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label label = new Label(currentEclipseGroup, SWT.NONE);
		label.setText("Eclipse Location:");
		label.setToolTipText(TOOL_TIP_ECLIPSE_LOCATION);

		m_locationText = new Text(currentEclipseGroup, SWT.BORDER);
		m_locationText.setToolTipText(TOOL_TIP_ECLIPSE_LOCATION);
		m_locationText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_locationText.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				firePageChanged();
			}
		});

		m_browseButton = new Button(currentEclipseGroup, SWT.PUSH);
		m_browseButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		m_browseButton.setText("Browse");
		m_browseButton.setToolTipText(TOOL_TIP_BROWSE_ECLIPSE_LOCATION);
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
		
		SelectionListener radioListener = new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				enableEclipseLocation(m_currentEclipseButton.getSelection());
				firePageChanged();
			}
		};

		m_newEclipseButton.addSelectionListener(radioListener);
		m_currentEclipseButton.addSelectionListener(radioListener);

		m_newEclipseButton.setSelection(true);
		enableEclipseLocation(m_currentEclipseButton.getSelection());
		
		setControl(m_pageComposite);
	}

	private void firePageChanged()
	{
		uncommitPage();
		getContainer().updateButtons();
	}
	
	@Override
	public boolean isPageComplete()
	{
		return m_newEclipseButton.getSelection() || UiUtils.trimmedValue(m_locationText) != null;
	}

	@Override
	public boolean performPageCommit()
	{
		setErrorMessage(null);

		getTPWizard().setNewEclipse(m_newEclipseButton.getSelection());
		
		if(m_newEclipseButton.getSelection())
			return true;

		try
		{
			String location = UiUtils.trimmedValue(m_locationText);
			if(location == null)
				throw new JNLPException("Eclipse location cannot be empty", null);

			File locationFile = new File(location);
			if(!locationFile.exists())
				throw new JNLPException("Selected Eclipse location does not exist", null);

			if(!locationFile.exists())
				throw new JNLPException("Selected Eclipse location is not a directory", null);

			getTPWizard().analyzeTP(location);
		}
		catch(JNLPException e)
		{
			setErrorMessage(e.getMessage());
			return false;
		}

		return true;
	}

	String getEclipseFolder()
	{
		return UiUtils.trimmedValue(m_locationText);
	}

	private void enableEclipseLocation(boolean enabled)
	{
		m_locationText.setEnabled(enabled);
		m_browseButton.setEnabled(enabled);
	}

	@Override
	public IWizardPage getNextPage()
	{
		if(m_newEclipseButton.getSelection())
			return getTPWizard().getNewLocationPage();
		
		if(isPageCommitted() && !getTPWizard().isEclipseUpToDate())
			return getTPWizard().getNewRecommendedPage();
		
		return getTPWizard().getToolsSelectionPage();
	}
}
