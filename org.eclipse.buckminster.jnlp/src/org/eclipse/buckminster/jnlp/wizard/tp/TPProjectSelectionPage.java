/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.wizard.tp;

import org.eclipse.buckminster.jnlp.MaterializationConstants;
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
public class TPProjectSelectionPage extends TPWizardPage
{
	private Composite m_pageComposite;

	private Button m_buckminsterButton;

	private Button m_spacesButton;

	private Button m_rssowlButton;

	private boolean m_buckminsterInstalled = false;
	
	private boolean m_spacesInstalled = false;
	
	private boolean m_rssowlInstalled = false;

	protected TPProjectSelectionPage()
	{
		super(MaterializationConstants.STEP_TP_PROJECT_SELECTION, "Project Selection",
				"Select projects to be installed into your Eclipse installation.");
	}

	public void createControl(Composite parent)
	{
		m_pageComposite = new Composite(parent, SWT.NONE);
		m_pageComposite.setLayout(new GridLayout(1, false));
		m_pageComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		m_buckminsterButton = new Button(m_pageComposite, SWT.CHECK);
		m_buckminsterButton.setText("Buckminster");

		m_spacesButton = new Button(m_pageComposite, SWT.CHECK);
		m_spacesButton.setText("Spaces");

		m_rssowlButton = new Button(m_pageComposite, SWT.CHECK);
		m_rssowlButton.setText("RSS Owl");

		m_buckminsterButton.setSelection(true);
		m_spacesButton.setSelection(true);
		m_rssowlButton.setSelection(true);

		m_buckminsterButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				m_spacesButton.setEnabled(!m_spacesInstalled && m_buckminsterButton.getSelection());
				m_rssowlButton.setEnabled(!m_rssowlInstalled && m_buckminsterButton.getSelection());
			}
		});

		setControl(m_pageComposite);
	}

	@Override
	protected void beforeDisplaySetup()
	{
		m_buckminsterInstalled = getTPWizard().isBuckminsterInstalled();
		m_spacesInstalled = getTPWizard().isSpacesInstalled();
		m_rssowlInstalled = getTPWizard().isRSSOwlInstalled();
		
		m_buckminsterButton.setEnabled(!m_buckminsterInstalled);
		m_spacesButton.setEnabled(!m_spacesInstalled);
		m_rssowlButton.setEnabled(!m_rssowlInstalled);
	}
	
	boolean isBuckminsterSelected()
	{
		return m_buckminsterButton.getSelection();
	}
	
	boolean isSpacesSelected()
	{
		return m_spacesButton.getSelection();
	}
	
	boolean isRSSOwlSelected()
	{
		return m_rssowlButton.getSelection();
	}
}
