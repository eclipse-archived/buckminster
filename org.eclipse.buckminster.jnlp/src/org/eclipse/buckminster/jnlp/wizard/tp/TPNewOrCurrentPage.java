/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.wizard.tp;

import java.io.File;

import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.jnlp.JNLPException;
import org.eclipse.buckminster.jnlp.MaterializationConstants;
import org.eclipse.buckminster.jnlp.MaterializationUtils;
import org.eclipse.buckminster.jnlp.ui.UiUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.pde.core.plugin.TargetPlatform;
import org.eclipse.pde.internal.core.ICoreConstants;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 * 
 */
@SuppressWarnings("restriction")
public class TPNewOrCurrentPage extends TPWizardPage
{
	private static final String TOOL_TIP_ECLIPSE_LOCATION = "Location of the current Eclipse";

	private static final String TOOL_TIP_BROWSE_ECLIPSE_LOCATION = "Browse location of the current Eclipse";

	private static final String ECLIPSE_FEATURE = "org.eclipse.platform";

	private static final String BUCKMINSTER_FEATURE = "org.eclipse.buckminster.core.feature";

	private static final String SPACES_FEATURE = "org.eclipse.spaces.core.feature";

	private static final String MIN_ECLIPSE_VERSION = "3.3.0";

	private static final String MIN_OK_ECLIPSE_VERSION = "3.4.0";

	private static final IVersion s_minEclipseVersion;

	private static final IVersion s_minOkEclipseVersion;

	private Composite m_pageComposite;
	
	private Label m_heading;
	
	private Button m_newEclipseButton;

	private Button m_currentEclipseButton;

	private Text m_locationText;

	private Button m_browseButton;

	private IVersion m_currentEclipseVersion;

	private boolean m_buckminsterInstalled = false;

	private boolean m_spacesInstalled = false;

	static
	{
		try
		{
			s_minEclipseVersion = VersionFactory.createVersion(IVersionType.OSGI, MIN_ECLIPSE_VERSION);
			s_minOkEclipseVersion = VersionFactory.createVersion(IVersionType.OSGI, MIN_OK_ECLIPSE_VERSION);
		}
		catch(CoreException e)
		{
			throw new ExceptionInInitializerError(e);
		}
	}

	protected TPNewOrCurrentPage()
	{
		super(MaterializationConstants.STEP_TP_INTRO, "New or Current Eclipse",
				"Do you want to install a new Elipse or use a current one?");
	}

	public void createControl(Composite parent)
	{
		m_pageComposite = new Composite(parent, SWT.NONE);
		m_pageComposite.setLayout(new GridLayout(1, false));
		m_pageComposite.setLayoutData(new GridData(GridData.FILL_BOTH));

		m_heading = new Label(m_pageComposite, SWT.WRAP);

		new Label(m_pageComposite, SWT.NONE);
		m_newEclipseButton = new Button(m_pageComposite, SWT.RADIO);
		m_newEclipseButton.setText("New Eclipse");
		GridData gridData = new GridData();
		gridData.horizontalSpan = 1;
		m_newEclipseButton.setLayoutData(gridData);

		m_currentEclipseButton = new Button(m_pageComposite, SWT.RADIO);
		m_currentEclipseButton.setText("Current Eclipse");
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		m_currentEclipseButton.setLayoutData(gridData);

		Group currentEclipseGroup = new Group(m_pageComposite, SWT.NONE);
		currentEclipseGroup.setLayout(new GridLayout(3, false));
		currentEclipseGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label label = new Label(currentEclipseGroup, SWT.NONE);
		label.setText("Eclipse Location:");
		label.setToolTipText(TOOL_TIP_ECLIPSE_LOCATION);

		m_locationText = new Text(currentEclipseGroup, SWT.BORDER);
		m_locationText.setToolTipText(TOOL_TIP_ECLIPSE_LOCATION);
		m_locationText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		m_locationText.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				firePageChanged();
			}
		});

		m_browseButton = new Button(currentEclipseGroup, SWT.PUSH);
		m_browseButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		m_browseButton.setText("Browse");
		m_browseButton.setToolTipText(TOOL_TIP_BROWSE_ECLIPSE_LOCATION);
		m_browseButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				DirectoryDialog dlg = new DirectoryDialog(m_browseButton.getShell());
				dlg.setFilterPath(MaterializationUtils.getKnownPath(m_locationText.getText()));
				String dir = dlg.open();

				if(dir != null)
				{
					m_locationText.setText(dir);
				}
			}
		});
		
		SelectionListener radioListener = new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				enableEclipseLocation(m_currentEclipseButton.getSelection());
				firePageChanged();
			}
		};

		m_newEclipseButton.addSelectionListener(radioListener);
		m_currentEclipseButton.addSelectionListener(radioListener);

		m_newEclipseButton.setSelection(true);
		enableEclipseLocation(m_currentEclipseButton.getSelection());
		
		setControl(m_pageComposite);
	}

	@Override
	protected void beforeDisplaySetup()
	{
		// Text of the label is set here to be able to WRAP it - no idea how to do it nicer 
		m_heading.setText("Do you want to install a new Elipse or use a current one?");
		GridData layoutData = (GridData)m_heading.getLayoutData();
		layoutData.widthHint = m_heading.getShell().getSize().x - 30;
		m_pageComposite.layout();
	}

	private void firePageChanged()
	{
		uncommitPage();
		getContainer().updateButtons();
	}
	
	@Override
	public boolean isPageComplete()
	{
		return m_newEclipseButton.getSelection() || UiUtils.trimmedValue(m_locationText) != null;
	}

	@Override
	public boolean performPageCommit()
	{
		setErrorMessage(null);

		m_buckminsterInstalled = false;
		m_spacesInstalled = false;

		getTPWizard().setNewEclipse(m_newEclipseButton.getSelection());
		
		if(m_newEclipseButton.getSelection())
			return true;

		try
		{
			String location = UiUtils.trimmedValue(m_locationText);
			if(location == null)
				throw new JNLPException("Eclipse location cannot be empty", null);

			File locationFile = new File(location);
			if(!locationFile.exists())
				throw new JNLPException("Selected Eclipse location does not exist", null);

			if(!locationFile.exists())
				throw new JNLPException("Selected Eclipse location is not a directory", null);

			try
			{
				setTP(location);
				IFeatureModel featureModel = PDECore.getDefault().getFeatureModelManager().findFeatureModel(
						ECLIPSE_FEATURE);
				if(featureModel == null)
					throw new JNLPException("The selected location is not an Eclipse folder", null);

				m_currentEclipseVersion = VersionFactory.createVersion(IVersionType.OSGI, featureModel.getFeature()
						.getVersion()).replaceQualifier(null);

				if(s_minEclipseVersion.compareTo(m_currentEclipseVersion) > 0)
					throw new JNLPException("Your Eclipse is too old, you need the new version", null);

				m_buckminsterInstalled = PDECore.getDefault().getFeatureModelManager().findFeatureModel(
						BUCKMINSTER_FEATURE) != null;
				m_spacesInstalled = PDECore.getDefault().getFeatureModelManager().findFeatureModel(SPACES_FEATURE) != null;
			}
			catch(JNLPException e)
			{
				throw e;
			}
			catch(Exception e)
			{
				throw new JNLPException("Error in analysing Eclipse installation", null);
			}
			finally
			{
				unsetTP();
			}
		}
		catch(JNLPException e)
		{
			setErrorMessage(e.getMessage());
			return false;
		}

		return true;
	}

	String getEclipseFolder()
	{
		return UiUtils.trimmedValue(m_locationText);
	}

	boolean isNewEclipse()
	{
		return m_newEclipseButton.getSelection();
	}

	boolean isEclipseUpToDate()
	{
		return s_minOkEclipseVersion.compareTo(m_currentEclipseVersion) <= 0;
	}

	IVersion getCurrentEclipseVersion()
	{
		return m_currentEclipseVersion;
	}

	boolean isBuckminsterInstalled()
	{
		return m_buckminsterInstalled;
	}

	boolean isSpacesInstalled()
	{
		return m_spacesInstalled;
	}

	private void enableEclipseLocation(boolean enabled)
	{
		m_locationText.setEnabled(enabled);
		m_browseButton.setEnabled(enabled);
	}

	private void setTP(String targetPlatform)
	{
		PDECore pdePlugin = PDECore.getDefault();
		Preferences preferences = pdePlugin.getPluginPreferences();
		IPath newPath = new Path(targetPlatform);
		Platform.getInstallLocation();
		IPath defaultPath = new Path(TargetPlatform.getDefaultLocation());
		String mode = defaultPath.equals(newPath)
				? ICoreConstants.VALUE_USE_THIS
				: ICoreConstants.VALUE_USE_OTHER;
		preferences.setValue(ICoreConstants.TARGET_MODE, mode);
		preferences.setValue(ICoreConstants.PLATFORM_PATH, targetPlatform);
		pdePlugin.savePluginPreferences();
	}

	private void unsetTP()
	{
		this.setTP(TargetPlatform.getDefaultLocation());
	}
}
