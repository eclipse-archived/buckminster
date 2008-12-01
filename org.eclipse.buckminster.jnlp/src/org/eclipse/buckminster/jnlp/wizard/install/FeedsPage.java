/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.wizard.install;

import org.eclipse.buckminster.jnlp.MaterializationConstants;
import org.eclipse.buckminster.jnlp.MaterializationUtils;
import org.eclipse.buckminster.jnlp.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 * 
 */
public class FeedsPage extends InstallWizardPage
{
	private static final int VERTICAL_SPACING = 10;

	private static final int HORIZONTAL_INDENT = 20;

	private static final String ICON_ARROW = "incom_stat.gif"; //$NON-NLS-1$

	private Label m_heading;

	private Link m_heading2;

	private Link m_openHTML;

	private String m_infoPageURL;

	protected FeedsPage()
	{
		super(MaterializationConstants.STEP_FEEDS, "Cloudfeeds", Messages.use_cloudfeeds_to_stay_current_with_this_distro, //$NON-NLS-1$
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

		m_heading = new Label(pageComposite, SWT.WRAP);
		GridData layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		m_heading.setLayoutData(layoutData);

		m_heading2 = new Link(pageComposite, SWT.WRAP);
		layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		m_heading2.setLayoutData(layoutData);
		m_heading2.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				if(getInstallWizard().getLearnMoreCloudreaderURL() != null)
				{
					Program.launch(getInstallWizard().getLearnMoreCloudreaderURL());
				}
			}
		});

		new Label(pageComposite, SWT.NONE);
		new Label(pageComposite, SWT.NONE);

		Label label = new Label(pageComposite, SWT.NONE);
		label.setImage(MaterializationUtils.getImage(ICON_ARROW));
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		layoutData.verticalAlignment = GridData.CENTER;
		label.setLayoutData(layoutData);

		m_openHTML = new Link(pageComposite, SWT.NONE);
		m_openHTML
				.setText(Messages.click_link_here_to_see_what_feeds_are_available_for_this_distro_and_add_them_to_your_reader);
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		m_openHTML.setLayoutData(layoutData);
		m_openHTML.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				if(m_infoPageURL != null)
				{
					Program.launch(m_infoPageURL);
				}
			}
		});

		new Label(pageComposite, SWT.NONE);
		new Label(pageComposite, SWT.NONE);

		label = new Label(pageComposite, SWT.NONE);
		label.setImage(MaterializationUtils.getImage(ICON_ARROW));
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		layoutData.verticalAlignment = GridData.CENTER;
		label.setLayoutData(layoutData);

		Label cloudreaderHeading = new Label(pageComposite, SWT.NONE);
		cloudreaderHeading
				.setText(Messages.to_get_the_cloudreader_use_the_following_update_site_URLs_Eclipse_3_3_or_later_required_with_colon);
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		cloudreaderHeading.setLayoutData(layoutData);

		Composite sitesComposite = new Composite(pageComposite, SWT.NONE);
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginWidth = gridLayout.marginHeight = 0;
		gridLayout.marginLeft = 100;
		sitesComposite.setLayout(gridLayout);
		layoutData = new GridData();
		layoutData.horizontalSpan = 2;
		sitesComposite.setLayoutData(layoutData);

		Text updateSite33Text = getSelectableItalicText(sitesComposite);
		String u = getInstallWizard().getEclipseDistroTools33UpdateSiteURL();
		updateSite33Text.setText(u == null ? Messages.missing_in_input : u);			
		new Label(sitesComposite, SWT.NONE).setText(Messages.for_eclipse_3_3);

		u = getInstallWizard().getEclipseDistroTools34UpdateSiteURL();
		Text updateSite34Text = getSelectableItalicText(sitesComposite);
		updateSite34Text.setText(u == null ? Messages.missing_in_input : u);			
		new Label(sitesComposite, SWT.NONE).setText(Messages.for_eclipse_3_4);

		new Label(pageComposite, SWT.NONE);
		new Label(pageComposite, SWT.NONE);

		label = new Label(pageComposite, SWT.NONE);
		label.setImage(MaterializationUtils.getImage(ICON_ARROW));
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		layoutData.verticalAlignment = GridData.CENTER;
		label.setLayoutData(layoutData);

		Link learnMore = new Link(pageComposite, SWT.NONE);
		learnMore.setText(Messages.learn_link_more_about_cloudfeeds);
		layoutData = new GridData();
		layoutData.horizontalIndent = HORIZONTAL_INDENT;
		learnMore.setLayoutData(layoutData);
		learnMore.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				if(getInstallWizard().getLearnMoreCloudfeedsURL() != null)
				{
					Program.launch(getInstallWizard().getLearnMoreCloudfeedsURL());
				}
			}
		});

		setControl(pageComposite);
	}

	private Text getSelectableItalicText(Composite composite)
	{
		Text text = new Text(composite, SWT.NO_FOCUS | SWT.READ_ONLY);
		FontData[] fontDadas = text.getFont().getFontData();
		if(fontDadas.length > 0)
			fontDadas[0].setStyle(SWT.ITALIC);
		text.setFont(new Font(Display.getCurrent(), fontDadas));

		return text;
	}

	@Override
	protected void beforeDisplaySetup()
	{

		// Text of the label is set here to be able to WRAP it - no idea how to do it nicer
		m_heading
				.setText(Messages.cloudfeeds_are_web_feeds_providing_updates_new_dependents_and_other_content_a_distros_publisher_wants_to_share);
		GridData layoutData = (GridData)m_heading.getLayoutData();
		layoutData.widthHint = m_heading.getShell().getSize().x - 25;

		m_heading2
				.setText(Messages.view_cloudfeeds_in_any_reader_supporting_RSS_Atom_including_the_link_cloudreader_plugin_for_eclipse_feeds_contain_materialization_links_cloudlinks_so_you_can_get_new_distros_directly_from_your_reader);
		layoutData = (GridData)m_heading2.getLayoutData();
		layoutData.widthHint = m_heading2.getShell().getSize().x - 25;

		m_heading.getParent().layout();

		m_infoPageURL = getInstallWizard().getComponentInfoPageURL();
		m_openHTML.setEnabled(m_infoPageURL != null);
	}

	// Finish is disabled
	@Override
	public boolean isPageComplete()
	{
		return !getInstallWizard().isMaterializationFinished();
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
}
