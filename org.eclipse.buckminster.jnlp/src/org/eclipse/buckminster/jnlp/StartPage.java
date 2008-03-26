/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import org.eclipse.buckminster.jnlp.ui.UiUtils;
import org.eclipse.buckminster.jnlp.ui.general.wizard.AdvancedWizardDialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
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
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 */
public class StartPage extends InstallWizardPage
{
	protected StartPage()
	{
		super(MaterializationConstants.STEP_START, "Materialization", "Please verify that what is described below is what you want to materialize.", null);
	}

	public void createControl(Composite parent)
	{				
		Composite pageComposite = new Composite(parent, SWT.NONE);
		pageComposite.setLayout(new GridLayout(1, false));

		FocusListener focusNextButtonListener = new FocusAdapter()
		{
			@Override
			public void focusGained(FocusEvent e)
			{
				focusNextButton();
			}
		};
		
		Group productGroup = new Group(pageComposite, SWT.NONE);
		productGroup.setText("Product Summary");
		productGroup.setLayout(new GridLayout(2, false));
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		productGroup.setLayoutData(gridData);
		
		new Label(productGroup, SWT.NONE).setText("Name:");
		Text text = new Text(productGroup, SWT.BORDER | SWT.NO_FOCUS | SWT.READ_ONLY | SWT.WRAP);
		text.setText(getInstallWizard().getArtifactName());
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		text.setBackground(getShell().getDisplay().getSystemColor(SWT.COLOR_WHITE));
		text.addFocusListener(focusNextButtonListener);
		
		new Label(productGroup, SWT.NONE).setText("Version:");
		text = new Text(productGroup, SWT.BORDER | SWT.NO_FOCUS | SWT.READ_ONLY | SWT.WRAP);
		text.setText(getInstallWizard().getCSpecVersionString() + " - " + getInstallWizard().getCSpecVersionType());//ArtifactVersion());
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		text.setBackground(getShell().getDisplay().getSystemColor(SWT.COLOR_WHITE));
		text.addFocusListener(focusNextButtonListener);
		
		new Label(productGroup, SWT.NONE).setText("Description:");
		text = new Text(productGroup, SWT.BORDER | SWT.NO_FOCUS | SWT.READ_ONLY | SWT.WRAP);
		text.setText(UiUtils.getNotNullString(getInstallWizard().getArtifactDescription()));
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		text.setBackground(getShell().getDisplay().getSystemColor(SWT.COLOR_WHITE));
		text.addFocusListener(focusNextButtonListener);
		
		Label label = new Label(productGroup, SWT.NONE);
		gridData = new GridData();
		gridData.verticalAlignment = SWT.TOP;
		gridData.verticalIndent = 2;
		label.setLayoutData(gridData);
		label.setText("Documentation:");
		text = new Text(productGroup, SWT.BORDER | SWT.NO_FOCUS | SWT.MULTI | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		text.setText(UiUtils.getNotNullString(getInstallWizard().getArtifactDocumentation()));
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 60;
		text.setLayoutData(gridData);
		text.setBackground(getShell().getDisplay().getSystemColor(SWT.COLOR_WHITE));
		text.addFocusListener(focusNextButtonListener);

		new Label(pageComposite, SWT.NONE);
		
		Group publisherGroup = new Group(pageComposite, SWT.NONE);
		publisherGroup.setText("Publisher Information");
		publisherGroup.setLayout(new GridLayout());
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		publisherGroup.setLayoutData(gridData);
		
		text = new Text(publisherGroup, SWT.BORDER | SWT.NO_FOCUS | SWT.MULTI | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		text.setText(getInstallWizard().getBrandingString());
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 30;
		text.setLayoutData(gridData);
		text.setBackground(getShell().getDisplay().getSystemColor(SWT.COLOR_WHITE));
		text.addFocusListener(focusNextButtonListener);
		
		if(getInstallWizard().isLoginRequired() && (!getInstallWizard().isLoggedIn() || getInstallWizard().isLoginPageRequested()))
		{
			Composite infoComposite = new Composite(pageComposite, SWT.NONE);
			GridData data = new GridData(GridData.FILL_BOTH);
			data.horizontalSpan = 2;
			infoComposite.setLayoutData(data);
			infoComposite.setLayout(new GridLayout());

			Group infoGroup = new Group(pageComposite, SWT.BOTTOM);
			infoGroup.setText("Info");
			FillLayout fillLayout = new FillLayout();
			fillLayout.marginHeight = fillLayout.marginWidth = 5;
			infoGroup.setLayout(fillLayout);
			data = new GridData(GridData.FILL_HORIZONTAL);
			data.horizontalSpan = 2;
			infoGroup.setLayoutData(data);

			final String message = "Note that, on request of the publisher of this material, you will be asked to log in to ";
			final String providerURL = null; //getInstallWizard().getServiceProviderHomePageURL();

			if(providerURL == null)
			{
				new Label(infoGroup, SWT.WRAP).setText(message + getInstallWizard().getServiceProvider());
			}
			else
			{
				Link link = new Link(infoGroup, SWT.WRAP);
				link.setText(message + "<a>" + getInstallWizard().getServiceProvider() + "</a>");
				link.addSelectionListener(new SelectionAdapter()
				{
					@Override
					public void widgetSelected(SelectionEvent e)
					{
						Program.launch(providerURL);
					}
				});
			}
		}
		
		setControl(pageComposite);
	}
	
	@Override
	protected void beforeDisplaySetup()
	{
		focusNextButton();
	}
	
	private void focusNextButton()
	{
		((AdvancedWizardDialog)getContainer()).getButtonFromButtonArea(IDialogConstants.NEXT_ID).setFocus();
	}
	
	@Override
	public IWizardPage getNextPage()
	{
		if(getInstallWizard().isLoginRequired() && (!getInstallWizard().isLoggedIn() || getInstallWizard().isLoginPageRequested()))
		{
			return getWizard().getPage(MaterializationConstants.STEP_LOGIN);
		} 
		return getInstallWizard().getDownloadPage();	
	}
	
	@Override
	public boolean isPageComplete()
	{
		return ! getInstallWizard().isProblemInProperties();
	}
}
