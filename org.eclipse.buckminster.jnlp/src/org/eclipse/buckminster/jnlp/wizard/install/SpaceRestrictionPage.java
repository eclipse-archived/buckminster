/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.wizard.install;

import org.eclipse.buckminster.jnlp.distroprovider.IRemoteDistroProvider;
import org.eclipse.buckminster.jnlp.wizard.LoginDialog;
import org.eclipse.buckminster.jnlp.MaterializationConstants;
import org.eclipse.buckminster.jnlp.MaterializationUtils;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;

/**
 * @author Karel Brezina
 *
 */
public class SpaceRestrictionPage extends InstallWizardPage
{
	private static final int HORIZONTAL_SPACING = 15;

	private static final String ICON_WARNING = "error.png";

	private static final String ICON_DOT = "brkp_obj.gif";

	private Composite m_stackComposite;
	
	private StackLayout m_stackLayout;
	
	private Composite m_solutionEmailAndInvitationComposite;
	
	private Composite m_solutionInvitationComposite;
	
	private Composite m_solutionForbiddenComposite;
	
	private Label m_userNameLabel;
	
	protected SpaceRestrictionPage()
	{
		super(MaterializationConstants.STEP_RESTRICTION, "Space Restriction", "Materialization space has a restrictive access.",
				null);
	}

	public void createControl(Composite parent)
	{
		Composite pageComposite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		pageComposite.setLayout(layout);

		Composite warningComposite = new Composite(pageComposite, SWT.NONE);
		layout = new GridLayout(2, false);
		layout.horizontalSpacing = HORIZONTAL_SPACING;
		warningComposite.setLayout(layout);
		
		Label label = new Label(warningComposite, SWT.NONE);
		label.setImage(MaterializationUtils.getImage(ICON_WARNING));
		GridData layoutData = new GridData();

		label = new Label(warningComposite, SWT.WRAP);
		label.setText("Access to the materialization space is forbidden");
		
		new Label(pageComposite, SWT.NONE);

		new Label(pageComposite, SWT.NONE).setText("Solution:");

		m_stackComposite = new Composite(pageComposite, SWT.NONE);
		m_stackLayout = new StackLayout();
		m_stackComposite.setLayout(m_stackLayout);
		
		m_solutionEmailAndInvitationComposite = new Composite(m_stackComposite, SWT.NONE);
		layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 0;
		m_solutionEmailAndInvitationComposite.setLayout(layout);
		m_solutionEmailAndInvitationComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		label = new Label(m_solutionEmailAndInvitationComposite, SWT.NONE);
		label.setImage(MaterializationUtils.getImage(ICON_DOT));
		layoutData = new GridData();
		layoutData.verticalAlignment = GridData.CENTER;
		label.setLayoutData(layoutData);

		new Label(m_solutionEmailAndInvitationComposite, SWT.WRAP).setText("Confirm email Validation");
		
		label = new Label(m_solutionEmailAndInvitationComposite, SWT.NONE);
		label.setImage(MaterializationUtils.getImage(ICON_DOT));
		layoutData = new GridData();
		layoutData.verticalAlignment = GridData.CENTER;
		label.setLayoutData(layoutData);

		Link link = new Link(m_solutionEmailAndInvitationComposite, SWT.WRAP);
		link.setText("Login to <a>" + getInstallWizard().getServiceProvider() + "</a> and accept the invitation");
		link.addSelectionListener(new SelectionAdapter()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				Program.launch(getInstallWizard().getServiceProviderHomePageURL());
			}
		});
		
		m_solutionInvitationComposite = new Composite(m_stackComposite, SWT.NONE);
		layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 0;
		m_solutionInvitationComposite.setLayout(layout);
		m_solutionInvitationComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		label = new Label(m_solutionInvitationComposite, SWT.NONE);
		label.setImage(MaterializationUtils.getImage(ICON_DOT));
		layoutData = new GridData();
		layoutData.verticalAlignment = GridData.CENTER;
		label.setLayoutData(layoutData);

		link = new Link(m_solutionInvitationComposite, SWT.WRAP);
		link.setText("Login to <a>" + getInstallWizard().getServiceProvider() + "</a> and accept the invitation");
		link.addSelectionListener(new SelectionAdapter()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				Program.launch(getInstallWizard().getServiceProviderHomePageURL());
			}
		});
		
		m_solutionForbiddenComposite = new Composite(m_stackComposite, SWT.NONE);
		layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 0;
		m_solutionForbiddenComposite.setLayout(layout);
		m_solutionForbiddenComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		label = new Label(m_solutionForbiddenComposite, SWT.NONE);
		label.setImage(MaterializationUtils.getImage(ICON_DOT));
		layoutData = new GridData();
		layoutData.verticalAlignment = GridData.CENTER;
		label.setLayoutData(layoutData);

		new Label(m_solutionForbiddenComposite, SWT.WRAP).setText("Ask the space owner to invite you to the materialization space");
		
		label = new Label(m_solutionForbiddenComposite, SWT.NONE);
		label.setImage(MaterializationUtils.getImage(ICON_DOT));
		layoutData = new GridData();
		layoutData.verticalAlignment = GridData.CENTER;
		label.setLayoutData(layoutData);

		link = new Link(m_solutionForbiddenComposite, SWT.WRAP);
		link.setText("Login to <a>" + getInstallWizard().getServiceProvider() + "</a> and accept the invitation");
		link.addSelectionListener(new SelectionAdapter()
		{

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				Program.launch(getInstallWizard().getServiceProviderHomePageURL());
			}
		});
				
		// Bottom part
		Composite infoComposite = new Composite(pageComposite, SWT.NONE);
		GridData data = new GridData(GridData.FILL_BOTH);
		infoComposite.setLayoutData(data);
		infoComposite.setLayout(new GridLayout());

		Group infoGroup = new Group(pageComposite, SWT.BOTTOM);
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
		Link loginLink = new Link(infoGroup, SWT.WRAP);
		loginLink.setText("click <a>here</a> to change your identity.");
		loginLink.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				LoginDialog loginDialog =
					new LoginDialog(
							getShell(), getInstallWizard(),
							getInstallWizard().getWindowImage(), getInstallWizard().getWindowTitle() + " - Login Dialog",
							getInstallWizard().getWizardImage(), getInstallWizard().getServiceProvider(),
							getInstallWizard().getHelpURL());
				if(loginDialog.open() == IDialogConstants.OK_ID)
				{
					updateUserName();
				}
			}
		});		
		
		setControl(pageComposite);
	}

	private void updateUserName()
	{
		m_userNameLabel.setText(getInstallWizard().getAuthenticatorCurrentUserName() + " "); // needs an extra space otherwise italic text misses the last part
		m_userNameLabel.getParent().pack();
		((Composite)getControl()).layout();
	}

	@Override
	protected void beforeDisplaySetup()
	{
		updateUserName();
	}
	
	@Override
	public boolean isPageComplete()
	{
		return true;
	}

    @Override
	public boolean performPageCommit()
	{
		try
		{
			int result = getInstallWizard().checkSpaceReadAccess();

			if(result == IRemoteDistroProvider.SPACE_ACCESS_FORBIDDEN ||
					result == IRemoteDistroProvider.SPACE_ACCESS_INVITATION_EXISTS ||
					result == IRemoteDistroProvider.SPACE_ACCESS_INVITATION_EXISTS_EMAIL_NOT_VERIFIED)
			{
				setErrorMessage("Aceess forbidden - the invitation is not accepted");
				return false;
			}
		}
		catch(Exception e1)
		{
			// no information - try to get the artifact
		}

		return true;
	}

	public void setStatus(int result)
	{
		switch(result)
		{
		case IRemoteDistroProvider.SPACE_ACCESS_FORBIDDEN:
			m_stackLayout.topControl = m_solutionForbiddenComposite;
			break;
		case IRemoteDistroProvider.SPACE_ACCESS_INVITATION_EXISTS:
			m_stackLayout.topControl = m_solutionInvitationComposite;
			break;
		default:
			m_stackLayout.topControl = m_solutionEmailAndInvitationComposite;
		}
		
		m_stackComposite.layout();
		
		setErrorMessage(null);
	}
    
	@Override
	public IWizardPage getNextPage()
	{
		return getWizard().getPage(MaterializationConstants.STEP_DOWNLOAD_LOCATION);
	}
}
