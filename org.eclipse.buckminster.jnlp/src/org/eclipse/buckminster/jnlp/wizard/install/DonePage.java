/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.wizard.install;

import java.util.List;

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

	private static final int HORIZONTAL_INDENT = 50;

	private static final String ICON_LEARN = "library_wiz.png";

	private static final String ICON_PUBLISH = "xhtml_wiz.png";

	private ComponentListPanel m_componentListPanel;

	protected DonePage()
	{
		super(MaterializationConstants.STEP_DONE, "Materialization Completed", "Close the materialization dialog.",
				null);
		setPreviousPage(this);
	}

	public void createControl(Composite parent)
	{
		Composite pageComposite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.verticalSpacing = VERTICAL_SPACING;
		pageComposite.setLayout(layout);

		Label label = new Label(pageComposite, SWT.NONE);
		label.setText("Learn More:");
		GridData layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		label.setLayoutData(layoutData);

		List<LearnMoreItem> learnMores = getInstallWizard().getLearnMores();

		label = new Label(pageComposite, SWT.NONE);
		label.setImage(MaterializationUtils.getImage(ICON_LEARN));
		GridData learnMoreIconlayoutData = new GridData();
		learnMoreIconlayoutData.horizontalIndent = HORIZONTAL_INDENT;

		learnMoreIconlayoutData.verticalSpan = learnMores.size();
		if(learnMoreIconlayoutData.verticalSpan == 0)
		{
			learnMoreIconlayoutData.verticalSpan = 1;
			new Label(pageComposite, SWT.NONE);
		}

		learnMoreIconlayoutData.verticalAlignment = GridData.CENTER;
		label.setLayoutData(learnMoreIconlayoutData);

		for(LearnMoreItem item : learnMores)
		{
			createLink(pageComposite, item.getString(), item.getUrl());
		}

		new Label(pageComposite, SWT.NONE);
		new Label(pageComposite, SWT.NONE);

		label = new Label(pageComposite, SWT.NONE);
		label.setText("Distro Actions:");
		layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		label.setLayoutData(layoutData);

		label = new Label(pageComposite, SWT.NONE);
		label.setImage(MaterializationUtils.getImage(ICON_PUBLISH));
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		layoutData.verticalAlignment = GridData.CENTER;
		label.setLayoutData(layoutData);

		Link publishMSPECLink = new Link(pageComposite, SWT.NONE);
		publishMSPECLink.setText("<a>Publish changed settings as a distro</a>");
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		//layoutData.widthHint = BUTTON_WIDTH;
		publishMSPECLink.setLayoutData(layoutData);
		publishMSPECLink.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				MaterializationUtils.startPublishingWizard(getInstallWizard(), getShell());
			}
		});

		new Label(pageComposite, SWT.NONE);
		new Label(pageComposite, SWT.NONE);

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

	private Link createLink(Composite parent, String string, final String url)
	{
		Link learnMoreLink = new Link(parent, SWT.NONE);
		learnMoreLink.setText("<a>" + string + "</a>");
		GridData layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		learnMoreLink.setLayoutData(layoutData);
		learnMoreLink.addSelectionListener(new SelectionAdapter()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String linkURL = url;

				if(linkURL != null)
				{
					Program.launch(linkURL);
				}
			}
		});

		return learnMoreLink;
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
