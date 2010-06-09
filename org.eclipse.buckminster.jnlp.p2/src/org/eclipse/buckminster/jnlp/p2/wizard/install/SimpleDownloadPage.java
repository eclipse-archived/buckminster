/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.p2.wizard.install;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.jnlp.p2.MaterializationConstants;
import org.eclipse.buckminster.jnlp.p2.MaterializationUtils;
import org.eclipse.buckminster.jnlp.p2.installer.IInstallOperation;
import org.eclipse.buckminster.jnlp.p2.installer.P2PropertyKeys;
import org.eclipse.buckminster.jnlp.p2.ui.UiUtils;
import org.eclipse.buckminster.jnlp.p2.wizard.ProfileDialog;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.internal.p2.engine.SimpleProfileRegistry;
import org.eclipse.equinox.internal.provisional.p2.engine.IProfile;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 * 
 */
@SuppressWarnings("restriction")
public class SimpleDownloadPage extends InstallWizardPage
{
	private static final String TOOL_TIP_DIRECTORY = "Destination directory for materialization";

	private static final String TOOL_TIP_BROWSE_DIRECTORY = "Browse destination directory for materialization";

	private static final String TOOL_TIP_PROFILE = "Materialization profile";
	
	private static final String TOOL_TIP_CREATE_PROFILE = "Create a new materialization profile";
	
	private String m_defaultInstallLocation;

	private Text m_locationText;

	private Button m_browseButton;
	
	private Combo m_profileCombo;
	
	private List<String> m_currentProfileNames = new ArrayList<String>();

	private List<String> m_newProfileNames = new ArrayList<String>();
	
	private Button m_profileButton;
	
	private Map<IPath, List<IProfile>> m_profileMap = new HashMap<IPath, List<IProfile>>();
	
	protected SimpleDownloadPage()
	{
		super(MaterializationConstants.STEP_DOWNLOAD_LOCATION, "Select a Destination", "Select a target location for materialization.",
				null);
		
		createProfileMap();
	}

	private void createProfileMap()
	{
		IProfile[] profiles = new SimpleProfileRegistry().getProfiles();
		
		for(IProfile profile : profiles)
		{
			String location = profile.getProperty(IProfile.PROP_INSTALL_FOLDER);
			
			IPath path = createPath(location);
			
			if(path == null)
				continue;
			
			List<IProfile> pathProfiles = m_profileMap.get(path);
			
			if(pathProfiles == null)
			{
				pathProfiles = new ArrayList<IProfile>();
				m_profileMap.put(path, pathProfiles);				
			}
			
			pathProfiles.add(profile);
		}
	}
	
	private static IPath createPath(String pathString)
	{
		return (pathString == null || pathString.length() == 0)
		? null
		: Path.fromOSString(pathString).addTrailingSeparator();
	}
	
	public void createControl(Composite parent)
	{
		m_defaultInstallLocation = MaterializationUtils.getDefaultDestination(getInstallWizard().getArtifactName());

		Composite pageComposite = new Composite(parent, SWT.NONE);
		pageComposite.setLayout(new GridLayout(3, false));
		pageComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Label label = new Label(pageComposite, SWT.NONE);
		label.setText("Location:");
		label.setToolTipText(TOOL_TIP_DIRECTORY);

		m_locationText = new Text(pageComposite, SWT.BORDER);
		m_locationText.setText(getInstallWizard().getInstallLocation() == null
				? ""
				: getInstallWizard().getInstallLocation().removeTrailingSeparator().toOSString());
		m_locationText.setToolTipText(TOOL_TIP_DIRECTORY);
		m_locationText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_locationText.addModifyListener(new ModifyListener()
		{

			public void modifyText(ModifyEvent e)
			{
				String pathStr = m_locationText.getText();
				IPath path = createPath(pathStr);
				getInstallWizard().setInstallLocation(path);
				
				refreshProfileCombo(path, m_profileCombo.getText());
			}
		});

		m_browseButton = new Button(pageComposite, SWT.PUSH);
		m_browseButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		m_browseButton.setText("browse");
		m_browseButton.setToolTipText(TOOL_TIP_BROWSE_DIRECTORY);
		m_browseButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				DirectoryDialog dlg = new DirectoryDialog(m_browseButton.getShell());
				dlg.setFilterPath(getKnownPath());
				String dir = dlg.open();

				if(dir != null)
				{
					m_locationText.setText(dir);
				}
			}

			private String getKnownPath()
			{
				IPath path = getInstallWizard().getInstallLocation();

				if(path == null)
					return null;

				File file = null;
				String pathString = null;
				do
				{
					// second and other runs - remove last segment
					if(file != null)
						path = path.removeLastSegments(1);

					pathString = path.removeTrailingSeparator().toOSString();
					file = new File(pathString);
				} while(!file.exists());

				if(!file.isDirectory())
					return null;

				return pathString;
			}
		});

		label = new Label(pageComposite, SWT.NONE);
		label.setText("Profile:");
		label.setToolTipText(TOOL_TIP_PROFILE);

		m_profileCombo = new Combo(pageComposite, SWT.BORDER | SWT.READ_ONLY);
		m_profileCombo.setToolTipText(TOOL_TIP_PROFILE);
		m_profileCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_profileCombo.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				String profileName = UiUtils.trimmedValue(m_profileCombo.getText());
				getInstallWizard().setProfileName(profileName);
			}
		});

		m_profileButton = new Button(pageComposite, SWT.PUSH);
		m_profileButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		m_profileButton.setText("create new profile");
		m_profileButton.setToolTipText(TOOL_TIP_CREATE_PROFILE);
		m_profileButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				ProfileDialog profileDialog =
					new ProfileDialog(
							getShell(),
							getInstallWizard().getWindowImage(), getInstallWizard().getWindowTitle() + " - New Profile Dialog",
							getInstallWizard().getWizardImage(), getInstallWizard().getHelpURL());
				if(profileDialog.open() == IDialogConstants.OK_ID)
				{
					m_profileCombo.add(profileDialog.getProfileName());
					m_profileCombo.select(m_profileCombo.getItemCount() - 1);
					m_newProfileNames.add(profileDialog.getProfileName());
				}
			}
		});

		setControl(pageComposite);
	}

	@Override
	protected void beforeDisplaySetup()
	{
		IPath path = null;
		
		if(getInstallWizard().getInstallLocation() == null)
		{
			if(m_defaultInstallLocation != null)
			{
				path = createPath(m_defaultInstallLocation);
				getInstallWizard().setInstallLocation(path);
				m_locationText.setText(m_defaultInstallLocation);
			}
		}
		else
		{
			path = getInstallWizard().getInstallLocation();
			m_locationText.setText(path.removeTrailingSeparator().toOSString());
		}
		
		if(m_currentProfileNames.size() == 0)
			refreshProfileCombo(path, null);
	}
	
	private void refreshProfileCombo(IPath path, String select)
	{
		m_currentProfileNames.clear();
		
		List<IProfile> profiles = m_profileMap.get(path);
		
		if(profiles != null)
		{
			for(IProfile profile : profiles)
			{
				String profileName = profile.getProperty(P2PropertyKeys.PROP_PROFILE_NAME);
				
				if(profileName == null)
					continue;
				
				m_currentProfileNames.add(profileName);
			}
		}
		
		m_currentProfileNames.addAll(m_newProfileNames);
		
		if(m_currentProfileNames.size() == 0)
			m_currentProfileNames.add(IInstallOperation.DEFAULT_PROFILE_NAME);
		
		m_profileCombo.setItems(m_currentProfileNames.toArray(new String[0]));
		
		int idx = m_currentProfileNames.indexOf(select);
		
		if(idx > -1)
			m_profileCombo.select(idx);
		else
			if(m_currentProfileNames.size() > 0)
				m_profileCombo.select(m_currentProfileNames.size() - 1);
			else
				m_profileCombo.select(0);
	}
	
	@Override
	public boolean isPageComplete()
	{
		// disable FINISH button on the two first pages
		IWizardPage currentPage = getContainer().getCurrentPage();
		
		if(currentPage.equals(getInstallWizard().getStartingPage())
				|| currentPage.equals(getInstallWizard().getLoginPage())
				|| currentPage.equals(getInstallWizard().getFolderRestrictionPage()))
			return false;

		return getInstallWizard().isMaterializerInitialized();
	}
	
	@Override
	public IWizardPage getNextPage()
	{
		return null;
	}
}
