/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import static org.eclipse.buckminster.jnlp.MaterializationConstants.*;

import java.util.List;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.jnlp.ui.UiUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 *
 */
public class PublishSpacePage extends PublishWizardPage
{
	private Combo m_spaceCombo;
	
	private Text m_shortDesc;
	
	private Text m_documentation;
	
	private MSpecDetailsPanel m_mspecDetails;
	
	private Label m_userNameLabel;
	
	protected PublishSpacePage()
	{
		super(MaterializationConstants.STEP_PUBLISH, "Publish", "Publish distro to a selected space.", null);
	}

	public void createControl(Composite parent)
	{
		setPageComplete(false); // set to true when the page is shown

		Composite topComposite = new Composite(parent, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginHeight = gridLayout.marginWidth = 0;
		topComposite.setLayout(gridLayout);
		topComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		CTabFolder tabFolder = new CTabFolder(topComposite, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		CTabItem mainTab = new CTabItem(tabFolder, SWT.NONE);
		mainTab.setText("Main");

		CTabItem detailsTab = new CTabItem(tabFolder, SWT.NONE);
		detailsTab.setText("Details");
		
		Composite pageComposite = new Composite(tabFolder, SWT.NONE);
		pageComposite.setLayout(new GridLayout(3, false));

		new Label(pageComposite, SWT.NONE).setText("Target Space:");

		m_spaceCombo = new Combo(pageComposite, SWT.READ_ONLY);
		m_spaceCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		new Label(pageComposite, SWT.NONE);

		new Label(pageComposite, SWT.NONE);
		new Label(pageComposite, SWT.NONE);
		new Label(pageComposite, SWT.NONE);

		Label label = new Label(pageComposite, SWT.NONE);
		label.setText("Short Description:");
		label.setEnabled(getPublishWizard().isCSsiteTopComponent());
		
		m_shortDesc = new Text(pageComposite, SWT.BORDER);
		m_shortDesc.setText(getPublishWizard().getMSpecBuilder().getShortDesc() == null ? "" : getPublishWizard().getMSpecBuilder().getShortDesc());
		m_shortDesc.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_shortDesc.setEnabled(getPublishWizard().isCSsiteTopComponent());
		m_shortDesc.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				getPublishWizard().getMSpecBuilder().setShortDesc(UiUtils.trimmedValue(m_shortDesc));
			}
		});
		new Label(pageComposite, SWT.NONE);

		label = new Label(pageComposite, SWT.NONE);
		label.setText("Documentation:");
		label.setEnabled(getPublishWizard().isCSsiteTopComponent());
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.TOP, false, false));
		
		m_documentation = new Text(pageComposite, SWT.MULTI | SWT.V_SCROLL | SWT.BORDER);
		m_documentation.setText(getPublishWizard().getMSpecBuilder().getDocumentation() == null ? "" : getPublishWizard().getMSpecBuilder().getDocumentation().toString());
		m_documentation.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		m_documentation.setEnabled(getPublishWizard().isCSsiteTopComponent());
		m_documentation.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				setErrorMessage(null);
				String doc = UiUtils.trimmedValue(m_documentation);
				try
				{
					getPublishWizard().getMSpecBuilder().setDocumentation(doc == null ? null : Documentation.parse(doc));
				}
				catch(CoreException ex)
				{
					setErrorMessage("Cannot save documentation: " + ex.getMessage());
				}
			}
		});
		new Label(pageComposite, SWT.NONE);

		mainTab.setControl(pageComposite);
		tabFolder.setSelection(mainTab);

		m_mspecDetails = new MSpecDetailsPanel(getPublishWizard().getMSpecBuilder(), "", false);
		detailsTab.setControl(m_mspecDetails.createControl(tabFolder));
		m_mspecDetails.initializeMSpecTree(getPublishWizard().getBOM());
		m_mspecDetails.update();
		
		// Bottom part
		Composite infoComposite = new Composite(topComposite, SWT.NONE);
		GridData data = new GridData(GridData.FILL_BOTH);
		infoComposite.setLayoutData(data);
		infoComposite.setLayout(new GridLayout());

		Group infoGroup = new Group(topComposite, SWT.BOTTOM);
		infoGroup.setText("Login Info");
		RowLayout rowLayout = new RowLayout();
		rowLayout.marginHeight = rowLayout.marginWidth = 5;
		rowLayout.spacing = 0;
		infoGroup.setLayout(rowLayout);
		data = new GridData(GridData.FILL_HORIZONTAL);
		infoGroup.setLayoutData(data);
		
		new Label(infoGroup, SWT.WRAP).setText("You are currently logged in as ");
		
		m_userNameLabel = new Label(infoGroup, SWT.NONE);
		// prepare bold font
		FontData[] fontDatas = m_userNameLabel.getFont().getFontData();
		for(FontData fontData : fontDatas)
			fontData.setStyle(SWT.ITALIC);
		m_userNameLabel.setFont(new Font(getShell().getDisplay(), fontDatas));
		
		new Label(infoGroup, SWT.NONE).setText(", ");
		Link link = new Link(infoGroup, SWT.WRAP);
		link.setText("click <a>here</a> to change your identity.");
		link.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				LoginDialog loginDialog = new LoginDialog(getShell(), getPublishWizard());
				if(loginDialog.open() == IDialogConstants.OK_ID)
				{
					beforeDisplaySetup();
				}
			}
		});
		
		setControl(topComposite);
	}

	private void updateUserName(String userName)
	{
		m_userNameLabel.setText(userName + " "); // needs an extra space otherwise italic text misses the last part
		m_userNameLabel.getParent().pack();
	}

	@Override
	protected void beforeDisplaySetup()
	{
		setPageComplete(true);
		
		List<String> availableSpaces;
		
		try
		{
			availableSpaces = getPublishWizard().getPublisher().getSpaceNames();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new JNLPException(
					"Cannot read available spaces", ERROR_CODE_PUBLISHING_EXCEPTION, e);
		}
		
		if(availableSpaces.size() == 0)
			throw new JNLPException(
					"Cannot read available spaces", ERROR_CODE_PUBLISHING_EXCEPTION);
		
		m_spaceCombo.setItems(availableSpaces.toArray(new String[0]));
		m_spaceCombo.select(0);
		
		updateUserName(getPublishWizard().getCurrentUserName());
		
		((Composite)getControl()).layout();
	}

	String getSelectedSpace()
	{
		return m_spaceCombo.getText();
	}
}
