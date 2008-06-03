/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.wizard.tp;

import org.eclipse.buckminster.jnlp.MaterializationConstants;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Karel Brezina
 * 
 */
public class TPToolSelectionPage extends TPWizardPage
{
	private Composite m_pageComposite;

	private Label m_heading;

	private Button m_distroToolsButton;

	protected TPToolSelectionPage()
	{
		super(MaterializationConstants.STEP_TP_TOOL_SELECTION, "Tool Selection",
				"Select tools to be installed into your Eclipse installation.");
	}

	public void createControl(Composite parent)
	{
		m_pageComposite = new Composite(parent, SWT.NONE);
		m_pageComposite.setLayout(new GridLayout(1, false));
		m_pageComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		m_heading = new Label(m_pageComposite, SWT.WRAP);

		new Label(m_pageComposite, SWT.NONE);

		m_distroToolsButton = new Button(m_pageComposite, SWT.CHECK);
		m_distroToolsButton.setText("Install Distro Tools");

		m_distroToolsButton.setSelection(true);

		setControl(m_pageComposite);
	}

	// disable NEXT
	@Override
	public IWizardPage getNextPage()
	{
		return null;
	}

	@Override
	protected void beforeDisplaySetup()
	{
		// Text of the label is set here to be able to WRAP it - no idea how to do it nicer
		m_heading.setText("Distro Tools make it easy to work with your distros within Eclipse.");
		GridData layoutData = (GridData)m_heading.getLayoutData();
		layoutData.widthHint = m_heading.getShell().getSize().x - 30;
		m_pageComposite.layout();
	}

	boolean isDistroToolsSelected()
	{
		return m_distroToolsButton.getSelection();
	}
}
