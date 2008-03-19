/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import org.eclipse.buckminster.jnlp.accountservice.IAuthenticator;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
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
				
		Composite fillerComposite = new Composite(pageComposite, SWT.NONE);
		fillerComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		setControl(pageComposite);
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

			if(result == IAuthenticator.SPACE_ACCESS_FORBIDDEN ||
					result == IAuthenticator.SPACE_ACCESS_INVITATION_EXISTS ||
					result == IAuthenticator.SPACE_ACCESS_INVITATION_EXISTS_EMAIL_NOT_VERIFIED)
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
		case IAuthenticator.SPACE_ACCESS_FORBIDDEN:
			m_stackLayout.topControl = m_solutionForbiddenComposite;
			break;
		case IAuthenticator.SPACE_ACCESS_INVITATION_EXISTS:
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
