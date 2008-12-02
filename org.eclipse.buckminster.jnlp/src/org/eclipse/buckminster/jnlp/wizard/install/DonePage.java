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
import org.eclipse.buckminster.jnlp.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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

	private static final String ICON_ARROW = "incom_stat.gif"; //$NON-NLS-1$

	private static final String ICON_EXCLAMATION = "hprio_tsk.gif"; //$NON-NLS-1$

	private ComponentListPanel m_componentListPanel;

	private Label m_heading1;

	private Label m_heading2;

	private Label m_heading3;

	private Label m_heading4;

	private Label m_heading5;

	protected DonePage()
	{
		super(MaterializationConstants.STEP_DONE, Messages.view_results, Messages.materialization_of_distro_completed,
				null);
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
		m_heading1.setText(Messages.the_components_that_were_downloaded_and_their_destination_folders_are_listed_below);
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
		learnMore.setText(NLS.bind(Messages.click_link_here_to_learn_more_about_0, getInstallWizard()
				.getServiceProvider()));
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
		listGroup.setText(Messages.materialized_components);
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginHeight = gridLayout.marginWidth = 5;
		listGroup.setLayout(gridLayout);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.horizontalSpan = 2;
		listGroup.setLayoutData(gridData);

		m_componentListPanel = new ComponentListPanel();
		m_componentListPanel.createControl(listGroup);

		setControl(pageComposite);
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
		return Messages.done;
	}

	@Override
	public int getOverrideDefaultButtonId()
	{
		return IDialogConstants.CANCEL_ID;
	}

	// Previous is disabled
	@Override
	public IWizardPage getPreviousPage()
	{
		return null;
	}

	public void update(MaterializationContext context)
	{
		MaterializationStatistics ms = context.getMaterializationStatistics();
		showFailed(ms.getFailed().size());
		m_componentListPanel.update(context);
	}

	@Override
	protected void beforeDisplaySetup()
	{

		// Text of the label is set here to be able to WRAP it - no idea how to do it nicer
		m_heading2
				.setText(Messages.if_you_materialized_to_an_eclipse_workspace_you_should_be_able_to_select_the_new_workspace_directly_from_within_Eclipse);
		GridData layoutData = (GridData)m_heading2.getLayoutData();
		layoutData.widthHint = m_heading2.getShell().getSize().x - 35 - 2 * HORIZONTAL_INDENT - 11;

		m_heading3
				.setText(Messages.if_you_materialized_to_an_eclipse_target_platform_the_new_functionality_should_be_available_next_time_you_start_Eclipse);
		layoutData = (GridData)m_heading3.getLayoutData();
		layoutData.widthHint = m_heading3.getShell().getSize().x - 35 - 2 * HORIZONTAL_INDENT - 11;

		m_heading4
				.setText(Messages.if_you_materialized_to_a_file_system_the_materialized_content_should_be_present_in_the_designated_location);
		layoutData = (GridData)m_heading4.getLayoutData();
		layoutData.widthHint = m_heading4.getShell().getSize().x - 35 - 2 * HORIZONTAL_INDENT - 11;

		m_heading5.setText(Messages.make_sure_to_note_this_location_before_exiting_the_wizard);
		layoutData = (GridData)m_heading5.getLayoutData();
		layoutData.widthHint = m_heading5.getShell().getSize().x - 50 - 2 * HORIZONTAL_INDENT - 11;

		m_heading1.getParent().layout();
	}

	private void showFailed(int failed)
	{
		if(failed > 0)
			if(failed > 1)
				setErrorMessage(NLS.bind(Messages.materialization_of_0_components_was_cancelled_check_the_list_below,
						Integer.valueOf(failed)));
			else
				setErrorMessage(Messages.materialization_of_1_component_was_cancelled_check_the_list_below);
		else
			setErrorMessage(null);
	}
}
