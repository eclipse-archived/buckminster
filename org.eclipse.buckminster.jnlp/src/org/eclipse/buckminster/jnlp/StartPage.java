/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import org.eclipse.jface.wizard.IWizardPage;
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
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 */
public class StartPage extends InstallWizardPage
{
	protected StartPage()
	{
		super("StartStep", "Materialization", "Please verify that what is described below is what you want to materialize.", null);
	}

	public void createControl(Composite parent)
	{				
		Composite pageComposite = new Composite(parent, SWT.NONE);
		pageComposite.setLayout(new GridLayout(1, false));

		new Label(pageComposite, SWT.NONE).setText("Publisher Information:");

		Composite brandingComposite = new Composite(pageComposite, SWT.NONE);
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginHeight = gridLayout.marginWidth = 0;
		brandingComposite.setLayout(gridLayout);
		brandingComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		int hSpan = 2;
		if(getInstallWizard().getBrandingImage() != null)
		{
			Label brandingIcon = new Label(brandingComposite, SWT.NONE);
			brandingIcon.setImage(getInstallWizard().getBrandingImage());
			brandingIcon.setBackground(getShell().getDisplay().getSystemColor(SWT.COLOR_WHITE));
			hSpan = 1;
		}
		
		Text text = new Text(brandingComposite, SWT.BORDER | SWT.NO_FOCUS | SWT.MULTI | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		text.setText(getInstallWizard().getBrandingString());
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 30;
		gridData.horizontalSpan = hSpan;
		gridData.verticalAlignment = SWT.TOP;
		text.setLayoutData(gridData);
		text.setBackground(getShell().getDisplay().getSystemColor(SWT.COLOR_WHITE));

		new Label(pageComposite, SWT.NONE);
		
		new Label(pageComposite, SWT.NONE).setText("Product Summary:");

		text = new Text(pageComposite, SWT.BORDER | SWT.NO_FOCUS | SWT.MULTI | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		text.setText(getInstallWizard().getArtifactName());
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.heightHint = 30;
		text.setLayoutData(gridData);
		text.setBackground(getShell().getDisplay().getSystemColor(SWT.COLOR_WHITE));
		
		if(getInstallWizard().isLoginRequired())
		{
			Composite infoComposite = new Composite(pageComposite, SWT.NONE);
			GridData data = new	GridData(GridData.FILL_BOTH);
			data.horizontalSpan = 2;
			infoComposite.setLayoutData(data);
			infoComposite.setLayout(new GridLayout());
	
			Group infoGroup = new Group(pageComposite, SWT.BOTTOM);
			infoGroup.setText("Info");
			infoGroup.setLayout(new GridLayout());
			data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
			data.horizontalSpan = 2; infoGroup.setLayoutData(data);

			final String message = "Note that, on request of the publisher of this material, you will be asked to log in to ";
			final String providerURL = getInstallWizard().getAuthenticator().getProviderURL();
			
			if(providerURL == null)
			{
				new Label(infoGroup, SWT.WRAP).setText(message + getInstallWizard().getAuthenticator().getProvider());
			}
			else
			{
				Link link = new Link(infoGroup, SWT.WRAP);
				link.setText(message + "<a>" + getInstallWizard().getAuthenticator().getProvider() + "</a>");
				link.addSelectionListener(new SelectionAdapter()
				{
					@Override
					public void widgetSelected(SelectionEvent e)
					{
						if(providerURL != null)
						{
							Program.launch(providerURL);
						}
					}
				});
			}
		}
		
		setControl(pageComposite);
	}
	
	@Override
	public IWizardPage getNextPage()
	{
		if(getInstallWizard().isLoginRequired())
		{
			return getWizard().getPage("LoginStep");
		} 
		return getWizard().getPage("SimpleDownloadLocationStep");	
	}
	
	@Override
	public boolean isPageComplete()
	{
		return ! getInstallWizard().isProblemInProperties();
	}
}
