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
import org.eclipse.pde.core.plugin.TargetPlatform;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * @author Karel Brezina
 * 
 */
public class TPIntroPage extends TPWizardPage
{
	private static final String ICON_BULB = "quick_fix.png";

	private static final int HORIZONTAL_INDENT = 20;

	private Composite m_pageComposite;

	private Label m_headingIcon;

	private Label m_heading;

	private Button m_enableWizard;

	protected TPIntroPage()
	{
		super(MaterializationConstants.STEP_TP_INTRO, "Setup Eclipse",
				"Get the best experience with the materialized material in Eclipse.");
	}

	public void createControl(Composite parent)
	{
		m_pageComposite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		m_pageComposite.setLayout(layout);
		GridData layoutData = new GridData();
		m_pageComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		Composite headingComposite = new Composite(m_pageComposite, SWT.NONE);
		headingComposite.setLayout(new GridLayout(2, false));

		m_headingIcon = new Label(headingComposite, SWT.NONE);
		m_headingIcon.setImage(MaterializationUtils.getImage(ICON_BULB));
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		layoutData.verticalAlignment = GridData.CENTER;
		m_headingIcon.setLayoutData(layoutData);

		m_heading = new Label(headingComposite, SWT.WRAP);
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		m_heading.setLayoutData(layoutData);

		Composite fillComposite = new Composite(m_pageComposite, SWT.NONE);
		fillComposite.setLayout(new FillLayout());
		fillComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		m_enableWizard = new Button(m_pageComposite, SWT.CHECK);
		m_enableWizard.setText("Do not start this wizard next time");
		m_enableWizard.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				getTPWizard().enableWizardNextTime(!m_enableWizard.getSelection());
			}
		});

		setControl(m_pageComposite);
	}

	@Override
	public boolean performPageCommit()
	{
		setErrorMessage(null);

		if(getTPWizard().isStartedFromIDE())
			try
			{
				String location = TargetPlatform.getLocation();
				if(location == null)
					throw new JNLPException("Internal error - eclipse location is empty", null);

				File locationFile = new File(location);
				if(!locationFile.exists())
					throw new JNLPException("Internal error - eclipse location does not exist", null);

				if(!locationFile.exists())
					throw new JNLPException("Internal error - eclipse location is not a directory", null);

				getTPWizard().analyzeTP(location);
			}
			catch(JNLPException e)
			{
				setErrorMessage(e.getMessage());
				return false;
			}

		return true;
	}

	@Override
	protected void beforeDisplaySetup()
	{
		// Text of the label is set here to be able to WRAP it - no idea how to do it nicer
		m_heading.setText("This wizard will help you to setup your Eclipse installation to get the best experience"
				+ " with the materialized material in Eclipse.");
		GridData layoutData = (GridData)m_heading.getLayoutData();
		layoutData.widthHint = m_heading.getShell().getSize().x - m_headingIcon.getSize().x - 80;
		m_pageComposite.layout();
	}
}
