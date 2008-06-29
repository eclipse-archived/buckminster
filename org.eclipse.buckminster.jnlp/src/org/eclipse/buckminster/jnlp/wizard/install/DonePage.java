/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.wizard.install;

import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.materializer.MaterializationStatistics;
import org.eclipse.buckminster.jnlp.MaterializationConstants;
import org.eclipse.buckminster.jnlp.MaterializationUtils;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;

/**
 * @author Karel Brezina
 * 
 */
public class DonePage extends InstallWizardPage
{
	private static final int VERTICAL_SPACING = 10;

	private static final int HORIZONTAL_INDENT = 20;

	private static final String ICON_ARROW = "incom_stat.gif";

	private static final String ICON_EXCLAMATION = "hprio_tsk.gif";

	private ComponentListPanel m_componentListPanel;

	private Label m_heading1;

	private Label m_heading2;

	private Label m_heading3;

	private Label m_heading4;

	private Label m_heading5;

	protected DonePage()
	{
		super(MaterializationConstants.STEP_DONE, "View Results", "Materialization of distro completed.", null);
		setPreviousPage(this);
	}

	public void createControl(Composite parent)
	{
		Composite pageComposite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.verticalSpacing = VERTICAL_SPACING;
		pageComposite.setLayout(layout);
		pageComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		m_heading1 = new Label(pageComposite, SWT.WRAP);
		m_heading1.setText("The components that were downloaded, and their destination folders, are listed below.");
		GridData layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		m_heading1.setLayoutData(layoutData);

		Label space = new Label(pageComposite, SWT.NONE);
		layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		layoutData.heightHint = 5;
		space.setLayoutData(layoutData);

		Label label = new Label(pageComposite, SWT.NONE);
		label.setImage(MaterializationUtils.getImage(ICON_ARROW));
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		layoutData.verticalAlignment = GridData.BEGINNING;
		label.setLayoutData(layoutData);

		m_heading2 = new Label(pageComposite, SWT.WRAP);
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		m_heading2.setLayoutData(layoutData);

		label = new Label(pageComposite, SWT.NONE);
		label.setImage(MaterializationUtils.getImage(ICON_ARROW));
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		layoutData.verticalAlignment = GridData.BEGINNING;
		label.setLayoutData(layoutData);

		m_heading3 = new Label(pageComposite, SWT.WRAP);
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		m_heading3.setLayoutData(layoutData);

		label = new Label(pageComposite, SWT.NONE);
		label.setImage(MaterializationUtils.getImage(ICON_ARROW));
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		layoutData.verticalAlignment = GridData.BEGINNING;
		label.setLayoutData(layoutData);

		m_heading4 = new Label(pageComposite, SWT.WRAP);
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		m_heading4.setLayoutData(layoutData);

		new Label(pageComposite, SWT.NONE);

		Composite exclamationComposite = new Composite(pageComposite, SWT.NONE);
		layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 0;
		exclamationComposite.setLayout(layout);
		
		label = new Label(exclamationComposite, SWT.NONE);
		label.setImage(MaterializationUtils.getImage(ICON_EXCLAMATION));
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		label.setLayoutData(layoutData);

		m_heading5 = new Label(exclamationComposite, SWT.WRAP);

		space = new Label(pageComposite, SWT.NONE);
		layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		layoutData.heightHint = 5;
		space.setLayoutData(layoutData);
		
		Link learnMore = new Link(pageComposite, SWT.NONE);
		learnMore.setText("Click <a>here</a> to learn more about " + getInstallWizard().getServiceProvider() + ".");
		layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		learnMore.setLayoutData(layoutData);
		learnMore.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				if(getInstallWizard().getServiceProviderHomePageURL() != null)
				{
					Program.launch(getInstallWizard().getServiceProviderHomePageURL());
				}
			}
		});

		space = new Label(pageComposite, SWT.NONE);
		layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		layoutData.heightHint = 5;
		space.setLayoutData(layoutData);

		Group listGroup = new Group(pageComposite, SWT.NONE);
		listGroup.setText("Materialized Components");
		FillLayout fillLayout = new FillLayout();
		fillLayout.marginHeight = fillLayout.marginWidth = 5;
		listGroup.setLayout(fillLayout);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.horizontalSpan = 2;
		listGroup.setLayoutData(gridData);

		m_componentListPanel = new ComponentListPanel();
		m_componentListPanel.createControl(listGroup);

		setControl(pageComposite);
	}

	public void update(MaterializationContext context)
	{
		MaterializationStatistics ms = context.getMaterializationStatistics();
		showFailed(ms.getFailed().size());
		m_componentListPanel.update(context);
	}

	private void showFailed(int failed)
	{
		if(failed > 0)
			if(failed > 1)
				setErrorMessage("Materialization of " + failed + " components was cancelled");
			else
				setErrorMessage("Materialization of 1 component was cancelled");
		else
			setErrorMessage(null);
	}

	@Override
	protected void beforeDisplaySetup()
	{

		// Text of the label is set here to be able to WRAP it - no idea how to do it nicer
		m_heading2
				.setText("If you materialized  to an Eclipse workspace, you should be able to select the new workspace directly from within Eclipse.");
		GridData layoutData = (GridData)m_heading2.getLayoutData();
		layoutData.widthHint = m_heading2.getShell().getSize().x - 35 - 2 * HORIZONTAL_INDENT - 11;

		m_heading3
				.setText("If you materialized to an Eclipse target platform, the new functionality should be available next time you start Eclipse.");
		layoutData = (GridData)m_heading3.getLayoutData();
		layoutData.widthHint = m_heading3.getShell().getSize().x - 35 - 2 * HORIZONTAL_INDENT - 11;

		m_heading4
				.setText("If you materialized to a file system, the materialized content should be present in the designated location.");
		layoutData = (GridData)m_heading4.getLayoutData();
		layoutData.widthHint = m_heading4.getShell().getSize().x - 35 - 2 * HORIZONTAL_INDENT - 11;

		m_heading5
				.setText("Make sure to note this location before exiting the wizard.");
		layoutData = (GridData)m_heading5.getLayoutData();
		layoutData.widthHint = m_heading5.getShell().getSize().x - 50 - 2 * HORIZONTAL_INDENT - 11;

		m_heading1.getParent().layout();
	}

	// Previous is disabled
	@Override
	public IWizardPage getPreviousPage()
	{
		return null;
	}

	@Override
	public IWizardPage getNextPage()
	{
		return getInstallWizard().getBOM().getResolution().getOPML() == null
				? null
				: getInstallWizard().getInfoPage();
	}

	@Override
	public String getOverrideCancelButtonText()
	{
		return "Done";
	}

	@Override
	public int getOverrideDefaultButtonId()
	{
		return IDialogConstants.CANCEL_ID;
	}
}
