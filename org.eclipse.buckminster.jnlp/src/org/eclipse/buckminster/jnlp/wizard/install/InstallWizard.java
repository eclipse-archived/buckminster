/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp.wizard.install;

import static org.eclipse.buckminster.jnlp.MaterializationConstants.ARTIFACT_UNKNOWN_TEXT;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_AUTHENTICATOR_EXCEPTION;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_FILE_IO_EXCEPTION;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_MALFORMED_PROPERTY_EXCEPTION;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_MATERIALIZATION_EXCEPTION;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_MISSING_PROPERTY_EXCEPTION;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_NO_AUTHENTICATOR_EXCEPTION;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_REMOTE_IO_EXCEPTION;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_RUNTIME_EXCEPTION;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_HELP_TITLE;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_HELP_URL;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_WINDOW_TITLE;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.MATERIALIZATOR_PROPERTIES;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.MATERIALIZERS;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.META_AREA;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.LOCALPROP_ENABLE_TP_WIZARD;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_ARTIFACT_NAME;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_ARTIFACT_VERSION;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_ARTIFACT_DESCRIPTION;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_ARTIFACT_DOCUMENTATION;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_BASE_PATH_URL;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_ERROR_URL;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_HELP_URL;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_HOME_PAGE_URL;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_LEARN_MORE_URL;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_LEARN_MORE_CLOUDFEEDS_URL;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_LEARN_MORE_CLOUDREADER_URL;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_LOGIN_KEY;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_LOGIN_REQUIRED;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_PROFILE_TEXT;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_SERVICE_PROVIDER;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_FOLDER_PATH;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_WINDOW_ICON;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_WINDOW_TITLE;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_WIZARD_ICON;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_DISTRO_ID;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_CSPEC_ID;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_CSPEC_NAME;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_CSPEC_TYPE;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_CSPEC_VERSION_STRING;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_CSPEC_VERSION_TYPE;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_ECLIPSE_SDK_URL;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_ECLIPSE_SDK_VERSION;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_ECLIPSE_DISTRO_TOOLS_34_URL;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_ECLIPSE_DISTRO_TOOLS_33_URL;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_ECLIPSE_DISTRO_TOOLS_34_UPDATE_SITE_URL;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_ECLIPSE_DISTRO_TOOLS_33_UPDATE_SITE_URL;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.VALUE_FALSE;

import static org.eclipse.buckminster.jnlp.MaterializationConstants.WINDOW_TITLE_UNKNOWN;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.VALUE_TRUE;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.jnlp.HelpLinkErrorDialog;
import org.eclipse.buckminster.jnlp.wizard.ILoginHandler;
import org.eclipse.buckminster.jnlp.wizard.IUnresolvedNodeHandler;
import org.eclipse.buckminster.jnlp.JNLPException;
import org.eclipse.buckminster.jnlp.MaterializationConstants;
import org.eclipse.buckminster.jnlp.MaterializationUtils;
import org.eclipse.buckminster.jnlp.MaterializerRunnable;
import org.eclipse.buckminster.jnlp.MissingPropertyException;
import org.eclipse.buckminster.jnlp.componentinfo.IComponentInfoProvider;
import org.eclipse.buckminster.jnlp.distroprovider.Distro;
import org.eclipse.buckminster.jnlp.distroprovider.DistroVariant;
import org.eclipse.buckminster.jnlp.distroprovider.IRemoteDistroProvider;
import org.eclipse.buckminster.jnlp.progress.MaterializationProgressProvider;
import org.eclipse.buckminster.jnlp.ui.general.wizard.AdvancedWizard;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;

/**
 * @author Thomas Hallgren
 * 
 */
public class InstallWizard extends AdvancedWizard implements ILoginHandler
{
	static private final String AUTHENTICATION_EXTPOINT = "org.eclipse.buckminster.jnlp.authentication";

	static private final String COMPONENTINFO_EXTPOINT = "org.eclipse.buckminster.jnlp.componentInfo";

	static private final String ATTRIBUTE_CLASS = "class";

	static private final String LEARNMORE_EXTPOINT = "org.eclipse.buckminster.jnlp.learnmore";

	static private final String ATTRIBUTE_STRING = "string";

	static private final String ATTRIBUTE_URL = "url";

	static private final String UNIVERSAL_ERROR_MESSAGE =
		"Materialization failures typically occur because a distro's publisher failed to keep it current," +
		" or because you experienced a network interruption while downloading.";

	private static MultiStatus createMultiStatusFromStatus(IStatus status)
	{
		return new MultiStatus(status.getPlugin(), status.getCode(), status.getMessage(), status.getException());
	}

	private Image m_brandingImage;

	private String m_brandingString;

	private BillOfMaterials m_cachedBOM;

	private URL m_cachedBOMURL;

	private String m_artifactName;

	private String m_artifactVersion;

	private String m_artifactDescription;

	private String m_artifactDocumentation;

	private String m_windowTitle;

	private Image m_windowImage;

	private Image m_wizardImage;

	// private Image m_materializationImage; // unused

	private String m_helpURL;

	private String m_moreInfoURL;

	private String m_errorURL = ERROR_HELP_URL;

	private boolean m_loginRequired;

	private String m_learnMoreURL;

	private String m_learnMoreCloudfeedsURL;

	private String m_learnMoreCloudreaderURL;

	private String m_basePathURL;

	private String m_homePageURL;

	private String m_serviceProvider;

	private String m_folderPath;

	private String m_loginKey;

	private String m_loginKeyUserName;

	private Long m_distroId;

	private Distro m_distro;
	
	private Map<Long, Distro> m_retrievedDistroCache = new HashMap<Long, Distro>();

	private Long m_cspecId;

	private String m_cspecName;

	private String m_cspecType;

	private String m_cspecVersionString;

	private String m_cspecVersionType;

	private String m_eclipseSDKURL;

	private IVersion m_eclipseSDKVersion;

	private String m_eclipseDistroTools34URL;

	private String m_eclipseDistroTools33URL;

	private String m_eclipseDistroTools34UpdateSiteURL;

	private String m_eclipseDistroTools33UpdateSiteURL;

	private boolean m_loginPageRequested = false;

	private LoginPage m_loginPage;

	private SelectDistroPage m_selectDistroPage;

	private FolderRestrictionPage m_folderRestrictionPage;

	private SimpleDownloadPage m_downloadPage;

	private SimpleAdvancedPage m_advancedPage;

	private OperationPage m_operationPage;

	private DonePage m_donePage;

	private FeedsPage m_infoPage;

	private IUnresolvedNodeHandler m_unresolvedNodeHandler;

	private final MaterializationSpecBuilder m_builder = new MaterializationSpecBuilder();

	private final Map<String, String> m_properties;

	private final BMProperties m_localProperties;

	private final boolean m_startedFromIDE;

	private IRemoteDistroProvider m_distroProvider;

	private IComponentInfoProvider m_infoProvider;

	private String m_infoPageURL;

	private String m_authenticatorUserName;

	private String m_authenticatorPassword;

	private final List<LearnMoreItem> m_learnMores;

	private boolean m_materializationFinished = false;

	private boolean m_problemInProperties = false;
	
	private List<DistroVariant> m_distroVariants;

	public InstallWizard(Map<String, String> properties)
	{
		this(properties, false);
	}

	public InstallWizard(Map<String, String> properties, boolean startedFromIDE)
	{
		setNeedsProgressMonitor(true);

		m_properties = properties;

		m_startedFromIDE = startedFromIDE;

		readProperties(properties);

		m_localProperties = readLocalProperties();

		m_distroProvider = createDistroProvider();

		m_infoProvider = createComponentInfoProvider();

		m_learnMores = createLearnMores();
	}

	@Override
	public void createPageControls(Composite pageContainer)
	{
		try
		{
			super.createPageControls(pageContainer);
		}
		catch(final JNLPException e)
		{
			m_problemInProperties = true;

			final IStatus status = e.getCause() == null
					? BuckminsterException.wrap(e).getStatus()
					: BuckminsterException.wrap(e.getCause()).getStatus();

			CorePlugin.logWarningsAndErrors(status);
			Display.getDefault().syncExec(new Runnable()
			{
				public void run()
				{
					HelpLinkErrorDialog.openError(null, null, MaterializationConstants.ERROR_WINDOW_TITLE, e
							.getMessage(), ERROR_HELP_TITLE, m_errorURL, e.getErrorCode(), status);
				}
			});
		}
	}

	@Override
	public void dispose()
	{
		if(m_brandingImage != null)
		{
			m_brandingImage.dispose();
		}

		if(m_windowImage != null)
		{
			m_windowImage.dispose();
		}

		if(m_wizardImage != null)
		{
			m_wizardImage.dispose();
		}
	}

	public IRemoteDistroProvider getDistroProvider()
	{
		return m_distroProvider;
	}

	public String getAuthenticatorCurrentUserName()
	{
		IRemoteDistroProvider auth = getDistroProvider();
		return auth == null
				? ""
				: auth.getCurrenlyLoggedUserName();
	}

	public String getAuthenticatorLoginKey()
	{
		return m_loginKey;
	}

	public String getAuthenticatorLoginKeyUserName()
	{
		return m_loginKeyUserName;
	}

	public String getAuthenticatorPassword()
	{
		return m_authenticatorPassword;
	}

	public String getAuthenticatorUserName()
	{
		return m_authenticatorUserName;
	}

	public String getComponentInfoPageURL()
	{
		return m_infoPageURL;
	}

	public IComponentInfoProvider getComponentInfoProvider()
	{
		return m_infoProvider;
	}

	public String getErrorURL()
	{
		return m_errorURL;
	}

	@Override
	public String getHelpURL()
	{
		return m_helpURL;
	}

	@Override
	public String getMoreInfoURL()
	{
		return m_moreInfoURL;
	}

	@Override
	public Image getWindowImage()
	{
		return m_windowImage;
	}

	@Override
	public String getWindowTitle()
	{
		return m_windowTitle;
	}

	@Override
	public boolean performCancel()
	{
		try
		{
			// disable progress provider
			Job.getJobManager().setProgressProvider(null);
			OperationPage operationPage = (OperationPage)getPage(MaterializationConstants.STEP_OPERATION);
			if(operationPage != null)
				((MaterializationProgressProvider)operationPage.getProgressProvider()).setEnabled(false);

			if(m_distroProvider != null)
			{
				try
				{
					m_distroProvider.releaseConnection();
				}
				catch(Throwable e)
				{
					// do nothing - session might be invalidated
				}
			}
		}
		catch(Throwable e)
		{
			// it should always finish
			e.printStackTrace();
		}

		/*
		 * Starts Eclipse installation wizard - Eclipse SDK + Buckminster + Spaces + RSS Owl
		 * if(isMaterializationFinished()) { String materializerID =
		 * getMaterializationSpecBuilder().getMaterializerID();
		 * 
		 * if((materializerID == IMaterializer.TARGET_PLATFORM || materializerID == IMaterializer.WORKSPACE) &&
		 * VALUE_TRUE.equals(m_localProperties.get(LOCALPROP_ENABLE_TP_WIZARD)))
		 * MaterializationUtils.startTPWizard(this, getShell());
		 * 
		 * saveLocalProperties(); }
		 */

		return true;
	}

	@Override
	public boolean performFinish()
	{
		if(m_unresolvedNodeHandler != null && m_unresolvedNodeHandler.isUnresolvedNodeIncluded())
		{
			MessageBox messageBox = new MessageBox(getContainer().getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
			messageBox.setMessage("Some distro dependencies cannot be resolved. "
					+ "You may decide to exclude the unresolved artifacts.\n"
					+ "However, excluding an artifact may result in a configuration that will no longer build.\n\n"
					+ "Do you want to exclude the unresolved artifacts?");
			messageBox.setText("Warning");
			if(messageBox.open() == SWT.YES)
				m_unresolvedNodeHandler.excludeUnresolvedNodes();
			else
				return false;
		}

		WizardPage originalPage = (WizardPage)getContainer().getCurrentPage();

		originalPage.setErrorMessage(null);
		try
		{
			if(m_builder.getInstallLocation() == null)
				m_builder.setInstallLocation(Path.fromOSString(
						MaterializationUtils.getDefaultDestination(getArtifactName())).addTrailingSeparator());

			MaterializationSpecBuilder builderToPerform = new MaterializationSpecBuilder();
			builderToPerform.initFrom(m_builder.createMaterializationSpec());

			if(m_cachedBOMURL != null)
				builderToPerform.setURL(m_cachedBOMURL.toString());

			MaterializationUtils.excludeCSsiteComponents(builderToPerform, getBOM());

			getContainer().showPage(m_operationPage);

			if(!m_startedFromIDE)
			{
				((MaterializationProgressProvider)m_operationPage.getProgressProvider()).setEnabled(true);
				Job.getJobManager().setProgressProvider(m_operationPage.getProgressProvider());
			}

			MaterializerRunnable mr = new MaterializerRunnable(builderToPerform.createMaterializationSpec());
			getContainer().run(true, true, mr);

			if(!m_startedFromIDE)
			{
				Job.getJobManager().setProgressProvider(null);
				((MaterializationProgressProvider)m_operationPage.getProgressProvider()).setEnabled(false);
			}
			
			m_materializationFinished = true;

			if(getComponentInfoProvider() != null)
				try
				{
					m_infoPageURL = getComponentInfoProvider().prepareHTML(getProperties(),
							getBOM().getResolution().getOPML(), MaterializationUtils.getDefaultDestination(null));
				}
				catch(Exception e)
				{
					m_infoPageURL = null;
					final IStatus status = BuckminsterException.wrap(e).getStatus();
					CorePlugin.logWarningsAndErrors(status);
					HelpLinkErrorDialog
							.openError(null, m_windowImage, MaterializationConstants.ERROR_WINDOW_TITLE,
									"Cannot create an HTML page with additional distro infomation",
									MaterializationConstants.ERROR_HELP_TITLE, m_errorURL,
									ERROR_CODE_RUNTIME_EXCEPTION, status);
				}

			m_donePage.update(mr.getContext());
			getContainer().showPage(m_donePage);
		}
		catch(InterruptedException e)
		{
			showOriginalPage(originalPage);
			originalPage.setErrorMessage("Operation cancelled");
		}
		catch(Exception e)
		{
			showOriginalPage(originalPage);

			// final IStatus status = BuckminsterException.wrap(e).getStatus();
			final IStatus status = BuckminsterException.fromMessage(BuckminsterException.wrap(e),
					UNIVERSAL_ERROR_MESSAGE).getStatus();

			CorePlugin.logWarningsAndErrors(status);
			HelpLinkErrorDialog.openError(null, m_windowImage, MaterializationConstants.ERROR_WINDOW_TITLE,
					"This distro failed to materialize", MaterializationConstants.ERROR_HELP_TITLE, m_errorURL,
					ERROR_CODE_MATERIALIZATION_EXCEPTION, status);
		}
		finally
		{
			if(!m_startedFromIDE)
			{
				Job.getJobManager().setProgressProvider(null);
				((MaterializationProgressProvider)m_operationPage.getProgressProvider()).setEnabled(false);
			}
		}

		return false;
	}

	public void removeAuthenticatorLoginKey()
	{
		m_loginKey = null;
		m_loginKeyUserName = null;
	}

	public void setDistroProvider(IRemoteDistroProvider distroProvider)
	{
		m_distroProvider = distroProvider;
	}

	public void setAuthenticatorPassword(String password)
	{
		m_authenticatorPassword = password;
	}

	public void setAuthenticatorUserName(String userName)
	{
		m_authenticatorUserName = userName;
	}

	@Override
	protected void addAdvancedPages()
	{
		addAdvancedPage(new StartPage());

		if(!m_problemInProperties)
		{
			m_loginPage = new LoginPage(m_distroProvider == null
					? "Virtual Distro Provider"
					: getServiceProvider());
			addAdvancedPage(m_loginPage);

			m_selectDistroPage = new SelectDistroPage();
			addAdvancedPage(m_selectDistroPage);			
			
			m_downloadPage = new SimpleDownloadPage();
			addAdvancedPage(m_downloadPage);

			m_advancedPage = new SimpleAdvancedPage();
			addAdvancedPage(m_advancedPage);

			m_folderRestrictionPage = new FolderRestrictionPage();
			addAdvancedPage(m_folderRestrictionPage);

			m_operationPage = new OperationPage();
			addAdvancedPage(m_operationPage);

			m_donePage = new DonePage();
			addAdvancedPage(m_donePage);

			m_infoPage = new FeedsPage();
			addAdvancedPage(m_infoPage);
		}
	}

	public boolean isStartedFromIDE()
	{
		return m_startedFromIDE;
	}

	@Override
	public Image getWizardImage()
	{
		return m_wizardImage;
	}

	String getArtifactDescription()
	{
		return m_artifactDescription;
	}

	String getArtifactDocumentation()
	{
		return m_artifactDocumentation;
	}

	String getArtifactName()
	{
		return m_artifactName;
	}

	String getArtifactVersion()
	{
		return m_artifactVersion;
	}

	public BillOfMaterials getBOM()
	{
		return m_cachedBOM;
	}

	Image getBrandingImage()
	{
		return m_brandingImage;
	}

	String getBrandingString()
	{
		return m_brandingString;
	}

	public String getCSpecName()
	{
		return m_cspecName;
	}

	public String getCSpecType()
	{
		return m_cspecType;
	}

	public String getCSpecVersionString()
	{
		return m_cspecVersionString;
	}

	public String getCSpecVersionType()
	{
		return m_cspecVersionType;
	}

	public String getEclipseSDKURL()
	{
		return m_eclipseSDKURL;
	}

	public IVersion getEclipseSDKVersion()
	{
		return m_eclipseSDKVersion;
	}

	public String getEclipseDistroTools34URL()
	{
		return m_eclipseDistroTools34URL;
	}

	public String getEclipseDistroTools33URL()
	{
		return m_eclipseDistroTools33URL;
	}

	public String getEclipseDistroTools34UpdateSiteURL()
	{
		return m_eclipseDistroTools34UpdateSiteURL;
	}

	public String getEclipseDistroTools33UpdateSiteURL()
	{
		return m_eclipseDistroTools33UpdateSiteURL;
	}

	IWizardPage getLoginPage()
	{
		return m_loginPage;
	}
	
	boolean isFolderRestrictionPageNeeded()
	{
		try
		{
			int result = checkFolderReadAccess();

			if(result == IRemoteDistroProvider.FOLDER_ACCESS_FORBIDDEN
					|| result == IRemoteDistroProvider.FOLDER_ACCESS_INVITATION_EXISTS
					|| result == IRemoteDistroProvider.FOLDER_ACCESS_INVITATION_EXISTS_EMAIL_NOT_VERIFIED)
			{
				m_folderRestrictionPage.setStatus(result);

				return true;
			}
		}
		catch(Exception e1)
		{
			// no information - try to get the artifact
		}
		
		return false;
	}
	
	IWizardPage getFolderRestrictionPage()
	{
		return m_folderRestrictionPage;
	}
	
	IWizardPage getSelectDistroPage()
	{
		return m_selectDistroPage;
	}
	
	IWizardPage getDownloadPage()
	{
		return m_downloadPage;
	}
	
	IWizardPage getAdvancedPage()
	{
		return m_advancedPage;
	}

	IWizardPage getInfoPage()
	{
		return m_infoPage;
	}

	List<LearnMoreItem> getLearnMores()
	{
		return m_learnMores;
	}

	String getLearnMoreURL()
	{
		return m_learnMoreURL;
	}

	String getLearnMoreCloudfeedsURL()
	{
		return m_learnMoreCloudfeedsURL;
	}

	String getLearnMoreCloudreaderURL()
	{
		return m_learnMoreCloudreaderURL;
	}

	// // Seems to be never used
	// Image getMaterializationImage()
	// {
	// return m_materializationImage;
	// }
	boolean isMaterializationFinished()
	{
		return m_materializationFinished;
	}

	public MaterializationSpecBuilder getMaterializationSpecBuilder()
	{
		return m_builder;
	}

	Long getDistroId()
	{
		return m_distroId;
	}
	
	Distro getDistro()
	{
		return m_distro;
	}
	
	void setDistro(Distro distro)
	{
		m_distro = distro;
	}
	
	List<DistroVariant> getDistroVariants()
	{
		return m_distroVariants;
	}
	
	String[] getMaterializers()
	{
		return MATERIALIZERS;
	}

	Map<String, String> getProperties()
	{
		return m_properties;
	}

	public String getServiceProvider()
	{
		return m_serviceProvider;
	}

	String getServiceProviderHomePageURL()
	{
		return m_homePageURL;
	}

	public String getFolderPath()
	{
		return m_folderPath;
	}

	int checkFolderReadAccess() throws Exception
	{
		if(m_loginRequired && (m_distroProvider == null || m_folderPath == null))
			return IRemoteDistroProvider.FOLDER_ACCESS_FORBIDDEN;

		// if authenticator is null - get smacked later (can only end up here without an
		// authenticator if loginRequired is false anyway).
		return m_distroProvider.checkFolderReadAccess(m_folderPath);
	}

	void retrieveStackInfo()
	{
		m_distroVariants = null;
		DistroVariant distroVariant = null;
		m_distro = null;
		
		if(m_distroId == null)
		{
			retrieveDistroVariants();
			
			if(m_distroVariants.size() == 1)
			{
				distroVariant = m_distroVariants.get(0);
				
				if(distroVariant.isBroken())
					distroVariant = null;
			}
		}
		
		if(m_distroId != null || distroVariant != null)
		{
			Long distroId = m_distroId != null ? m_distroId : distroVariant.getDistroId(); 
			retrieveDistro(distroId);
		}
	}
	
	void retrieveDistroVariants()
	{
		m_distroVariants = null;
		try
		{
			getContainer().run(true, false, new IRunnableWithProgress()
			{

				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
				{
					monitor.beginTask(null, IProgressMonitor.UNKNOWN);
					monitor.subTask("Retrieving stack variations");
					
					try
					{
						m_distroVariants = m_distroProvider.getDistroVariants(m_cspecId);
					}
					catch(Exception e)
					{
						throw new InvocationTargetException(e);
					}
					
					monitor.done();
				}
			});
		}
		catch(Exception e)
		{
			Throwable originalException = e;
			
			if(e instanceof InvocationTargetException && e.getCause() != null)
				originalException = e.getCause();
				
			throw new JNLPException("Cannot read stack variations", ERROR_CODE_REMOTE_IO_EXCEPTION, originalException);
		}
	}
	
	void retrieveDistro(final Long distroId)
	{
		m_distro = null;
		m_cachedBOM = null;
		m_cachedBOMURL = null;

		if(distroId == null)
			return;

		m_distro = m_retrievedDistroCache.get(distroId);

		if(m_distro == null)
		{
			try
			{
				getContainer().run(true, false, new IRunnableWithProgress()
				{

					public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
					{
						monitor.beginTask(null, IProgressMonitor.UNKNOWN);
						monitor.subTask("Retrieving distro specification");
						
						try
						{
							m_distro = m_distroProvider.getDistro(distroId);
						}
						catch(Exception e)
						{
							throw new InvocationTargetException(e);
						}
						m_retrievedDistroCache.put(distroId, m_distro);
						
						m_builder.initFrom(m_distro.getMspec());
						
						IPath location = m_builder.getInstallLocation() == null ?
											Path.fromOSString(MaterializationUtils.getDefaultDestination(m_artifactName)) :
											MaterializationUtils.expandPath(m_builder, m_builder.getInstallLocation());
						m_builder.setInstallLocation(location);
						
						m_cachedBOM = m_distro.getBom();
						saveBOMLocally();
												
						initMSpecTree();
						
						monitor.done();
					}
				});
			}
			catch(Exception e)
			{
				if(e instanceof JNLPException)
					throw (JNLPException)e;
				
				Throwable originalException = e;
				
				if(e instanceof InvocationTargetException && e.getCause() != null)
					originalException = e.getCause();

				throw new JNLPException("Cannot read distro specification", ERROR_CODE_REMOTE_IO_EXCEPTION, originalException);
			}
		}
	}
	
	private void saveBOMLocally()
	{
		File cachedBOMFile;
		try
		{
			cachedBOMFile = File.createTempFile("jnlp", ".bom");
			cachedBOMFile.deleteOnExit();
		}
		catch(IOException e)
		{
			throw new JNLPException("Cannot create a temp file", ERROR_CODE_FILE_IO_EXCEPTION, e);
		}

		MaterializationUtils.saveBOM(m_cachedBOM, cachedBOMFile);

		try
		{
			m_cachedBOMURL = cachedBOMFile.toURI().toURL();
		}
		catch(MalformedURLException e)
		{
			throw new JNLPException("Cannot create URL link to a temp file", ERROR_CODE_MALFORMED_PROPERTY_EXCEPTION, e);
		}
	}
	
	void initMSpecTree()
	{
		m_advancedPage.initializeMSpecTree(getBOM());
	}

	boolean isLoggedIn()
	{
		boolean isLoggedIn = false;

		try
		{
			isLoggedIn = m_distroProvider.isLoggedIn();
		}
		catch(Exception e1)
		{
			// nothing isLoggedIn == false
		}

		return isLoggedIn;
	}

	boolean isLoginPageRequested()
	{
		return m_loginPageRequested;
	}

	boolean isLoginRequired()
	{
		return m_loginRequired;
	}

	boolean isStackInfoRetrieved()
	{
		return m_distroVariants != null;
	}

	boolean isMaterializerInitialized()
	{
		return m_cachedBOMURL != null;
	}

	boolean isProblemInProperties()
	{
		return m_problemInProperties;
	}

	void resetMaterializerInitialization()
	{
		m_distroVariants = null;
		m_cachedBOMURL = null;
	}

	void setLoginPageRequested(boolean loginPageRequested)
	{
		m_loginPageRequested = loginPageRequested;
	}

	void setUnresolvedNodeHandler(IUnresolvedNodeHandler unresolvedNodeHandler)
	{
		m_unresolvedNodeHandler = unresolvedNodeHandler;
	}

	public BMProperties getLocalProperties()
	{
		return m_localProperties;
	}

	public void enableWizardNextTime(boolean enable)
	{
		getLocalProperties().put(LOCALPROP_ENABLE_TP_WIZARD, enable
				? VALUE_TRUE
				: VALUE_FALSE);
	}

	private IRemoteDistroProvider createDistroProvider()
	{
		IExtensionRegistry er = Platform.getExtensionRegistry();
		IConfigurationElement[] elems = er.getConfigurationElementsFor(AUTHENTICATION_EXTPOINT);
		IRemoteDistroProvider authenticator = null;

		try
		{
			if(elems.length != 1)
			{
				throw new JNLPException("Distro provider is not available", ERROR_CODE_NO_AUTHENTICATOR_EXCEPTION);
			}

			try
			{
				authenticator = (IRemoteDistroProvider)elems[0].createExecutableExtension(ATTRIBUTE_CLASS);
				authenticator.initialize(m_basePathURL);

				if(m_loginKey != null)
				{
					int result = authenticator.login(m_loginKey);

					if(result == IRemoteDistroProvider.LOGIN_UNKNOW_KEY)
						m_loginKey = null;

					if(result == IRemoteDistroProvider.LOGIN_OK)
						m_loginKeyUserName = authenticator.getCurrenlyLoggedUserName();
					else
						m_loginKeyUserName = null;
				}
			}
			catch(Throwable e)
			{
				throw new JNLPException("Cannot connect to the remote server", ERROR_CODE_AUTHENTICATOR_EXCEPTION, e);
			}
		}
		catch(JNLPException e)
		{
			m_problemInProperties = true;

			IStatus status = BuckminsterException.wrap(e.getCause() != null
					? e.getCause()
					: e).getStatus();
			CorePlugin.logWarningsAndErrors(status);
			HelpLinkErrorDialog.openError(null, null, ERROR_WINDOW_TITLE, e.getMessage(), ERROR_HELP_TITLE, m_errorURL,
					e.getErrorCode(), status);
		}

		return authenticator;
	}

	private IComponentInfoProvider createComponentInfoProvider()
	{
		IExtensionRegistry er = Platform.getExtensionRegistry();
		IConfigurationElement[] elems = er.getConfigurationElementsFor(COMPONENTINFO_EXTPOINT);
		IComponentInfoProvider infoProvider = null;

		try
		{
			if(elems.length != 1)
				return null;

			try
			{
				infoProvider = (IComponentInfoProvider)elems[0].createExecutableExtension(ATTRIBUTE_CLASS);
			}
			catch(Throwable e)
			{
				throw new JNLPException("Cannot create component info provider", ERROR_CODE_RUNTIME_EXCEPTION, e);
			}
		}
		catch(JNLPException e)
		{
			m_problemInProperties = true;

			IStatus status = BuckminsterException.wrap(e.getCause() != null
					? e.getCause()
					: e).getStatus();
			CorePlugin.logWarningsAndErrors(status);
			HelpLinkErrorDialog.openError(null, null, ERROR_WINDOW_TITLE, e.getMessage(), ERROR_HELP_TITLE, m_errorURL,
					e.getErrorCode(), status);
		}

		return infoProvider;
	}

	private List<LearnMoreItem> createLearnMores()
	{
		List<LearnMoreItem> learnMores = new ArrayList<LearnMoreItem>();

		// Learn more items from properties
		if(m_learnMoreURL != null)
		{
			learnMores.add(new LearnMoreItem("Create your own virtual distribution", m_learnMoreURL));
		}

		if(m_homePageURL != null)
		{
			learnMores.add(new LearnMoreItem("Search your components at " + m_serviceProvider, m_homePageURL));
		}

		// Learn more items from extension
		IExtensionRegistry er = Platform.getExtensionRegistry();
		IConfigurationElement[] elems = er.getConfigurationElementsFor(LEARNMORE_EXTPOINT);

		for(IConfigurationElement elem : elems)
		{
			learnMores.add(new LearnMoreItem(elem.getAttribute(ATTRIBUTE_STRING), elem.getAttribute(ATTRIBUTE_URL)));
		}

		return learnMores;
	}

	/**
	 * Wizard page doesn't display message text (the second line in title area) if the wizard image is too small This
	 * function creates a new image that is 64 pixels high - adds to the original image transparent stripe
	 * 
	 * @param origImage
	 * 		original image
	 * @return new image
	 */
	private Image getNormalizedWizardImage(Image origImage)
	{
		final int WIZARD_IMAGE_HEIGHT = 64;

		ImageData origImageData = origImage.getImageData();

		if(origImageData.height >= WIZARD_IMAGE_HEIGHT)
		{
			return origImage;
		}

		ImageData newImageData = new ImageData(origImageData.width, WIZARD_IMAGE_HEIGHT, origImageData.depth,
				origImageData.palette);

		newImageData.alpha = origImageData.alpha;

		ImageData transparencyMask = origImageData.getTransparencyMask();
		boolean testTransparency = origImageData.getTransparencyType() == SWT.TRANSPARENCY_MASK
				|| origImageData.getTransparencyType() == SWT.TRANSPARENCY_PIXEL;

		for(int y = 0; y < origImageData.height; y++)
		{
			for(int x = 0; x < origImageData.width; x++)
			{
				if(testTransparency && transparencyMask.getPixel(x, y) == 0)
				{
					newImageData.setAlpha(x, y, 0);
				}
				else
				{
					newImageData.setPixel(x, y, origImageData.getPixel(x, y));
					newImageData.setAlpha(x, y, origImageData.getAlpha(x, y));
				}
			}
		}

		for(int y = origImageData.height; y < WIZARD_IMAGE_HEIGHT; y++)
		{
			for(int x = 0; x < origImageData.width; x++)
			{
				newImageData.setAlpha(x, y, 0);
			}
		}

		return new Image(Display.getDefault(), newImageData);
	}

	private void readProperties(Map<String, String> properties)
	{
		class ErrorEntry
		{
			private IStatus m_status;

			private String m_errorCode;

			public ErrorEntry(IStatus status, String errorCode)
			{
				super();
				m_status = status;
				m_errorCode = errorCode;
			}

			public String getErrorCode()
			{
				return m_errorCode;
			}

			public IStatus getStatus()
			{
				return m_status;
			}
		}

		List<ErrorEntry> errorList = new ArrayList<ErrorEntry>();

		String tmp = properties.get(PROP_BASE_PATH_URL);

		if(tmp == null)
		{
			Throwable e = new MissingPropertyException(PROP_BASE_PATH_URL);
			errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(),
					ERROR_CODE_MISSING_PROPERTY_EXCEPTION));
		}

		m_basePathURL = tmp;

		tmp = properties.get(PROP_ARTIFACT_NAME);
		if(tmp == null)
		{
			Throwable e = new MissingPropertyException(PROP_ARTIFACT_NAME);
			errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(),
					ERROR_CODE_MISSING_PROPERTY_EXCEPTION));
			tmp = ARTIFACT_UNKNOWN_TEXT;
		}

		m_builder.setName(tmp);
		m_artifactName = tmp;

		m_artifactVersion = properties.get(PROP_ARTIFACT_VERSION);
		m_artifactDescription = properties.get(PROP_ARTIFACT_DESCRIPTION);

		if(m_artifactDescription != null)
			try
			{
				m_artifactDescription = new String(Base64.decodeBase64(m_artifactDescription.getBytes()), "UTF-8");
			}
			catch(UnsupportedEncodingException e1)
			{
				m_artifactDescription = null;
			}

		m_artifactDocumentation = properties.get(PROP_ARTIFACT_DOCUMENTATION);

		if(m_artifactDocumentation != null)
			try
			{
				m_artifactDocumentation = new String(Base64.decodeBase64(m_artifactDocumentation.getBytes()), "UTF-8");
			}
			catch(UnsupportedEncodingException e1)
			{
				m_artifactDocumentation = null;
			}

		// Branding image is not wanted
		m_brandingImage = null;

		tmp = properties.get(PROP_PROFILE_TEXT);
		if(tmp == null)
		{
			Throwable e = new MissingPropertyException(PROP_PROFILE_TEXT);
			errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(),
					ERROR_CODE_MISSING_PROPERTY_EXCEPTION));
			tmp = ARTIFACT_UNKNOWN_TEXT;
		}
		m_brandingString = tmp;

		tmp = properties.get(PROP_WINDOW_TITLE);
		if(tmp == null)
		{
			Throwable e = new MissingPropertyException(PROP_WINDOW_TITLE);
			errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(),
					ERROR_CODE_MISSING_PROPERTY_EXCEPTION));
			tmp = WINDOW_TITLE_UNKNOWN;
		}
		m_windowTitle = tmp;

		tmp = properties.get(PROP_WINDOW_ICON);
		m_windowImage = null;
		if(tmp != null)
		{
			try
			{
				m_windowImage = ImageDescriptor.createFromURL(new URL(tmp)).createImage();
			}
			catch(MalformedURLException e)
			{
				errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(),
						ERROR_CODE_MALFORMED_PROPERTY_EXCEPTION));
			}
		}

		tmp = properties.get(PROP_WIZARD_ICON);
		m_wizardImage = null;
		if(tmp != null)
		{
			try
			{
				m_wizardImage = getNormalizedWizardImage(ImageDescriptor.createFromURL(new URL(tmp)).createImage());
			}
			catch(MalformedURLException e)
			{
				errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(),
						ERROR_CODE_MALFORMED_PROPERTY_EXCEPTION));
			}
		}

		// // Loads an image that is never used
		// tmp = properties.get(PROP_MATERIALIZATION_IMAGE);
		// m_materializationImage = null;
		// if(tmp != null)
		// {
		// try
		// {
		// m_materializationImage = ImageDescriptor.createFromURL(new URL(tmp)).createImage();
		// }
		// catch(MalformedURLException e)
		// {
		// errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(),
		// ERROR_CODE_MALFORMED_PROPERTY_EXCEPTION));
		// }
		// }

		m_helpURL = properties.get(PROP_HELP_URL);
		// TODO use different helpURL and moreInfoURL, now there is just helpURL
		m_moreInfoURL = m_helpURL;
		// m_moreInfoURL = properties.get(PROP_MORE_INFO_URL);

		m_errorURL = properties.get(PROP_ERROR_URL);
		if(m_errorURL == null)
		{
			m_errorURL = ERROR_HELP_URL;
		}

		m_loginRequired = false;
		tmp = properties.get(PROP_LOGIN_REQUIRED);
		if("true".equalsIgnoreCase(tmp))
		{
			m_loginRequired = true;
		}

		m_learnMoreURL = properties.get(PROP_LEARN_MORE_URL);

		m_homePageURL = properties.get(PROP_HOME_PAGE_URL);

		m_serviceProvider = properties.get(PROP_SERVICE_PROVIDER);

		m_folderPath = properties.get(PROP_FOLDER_PATH);

		tmp = properties.get(PROP_DISTRO_ID);
		if(tmp != null && tmp.length() > 0)
		{
			try
			{
				m_distroId = new Long(tmp);
			}
			catch(NumberFormatException e)
			{
				m_distroId = null;
			}
		}
		
		tmp = properties.get(PROP_CSPEC_ID);
		if(tmp == null)
		{
			Throwable e = new MissingPropertyException(PROP_CSPEC_ID);
			errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(),
					ERROR_CODE_MISSING_PROPERTY_EXCEPTION));
		}
		
		try
		{
			m_cspecId = new Long(tmp);
		}
		catch(NumberFormatException e)
		{
			errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(),
					ERROR_CODE_MISSING_PROPERTY_EXCEPTION));
		}

		m_cspecName = properties.get(PROP_CSPEC_NAME);
		if(m_cspecName == null)
		{
			Throwable e = new MissingPropertyException(PROP_CSPEC_NAME);
			errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(),
					ERROR_CODE_MISSING_PROPERTY_EXCEPTION));
		}

		m_cspecType = properties.get(PROP_CSPEC_TYPE);
		if(m_cspecType == null)
		{
			Throwable e = new MissingPropertyException(PROP_CSPEC_TYPE);
			errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(),
					ERROR_CODE_MISSING_PROPERTY_EXCEPTION));
		}

		m_cspecVersionString = properties.get(PROP_CSPEC_VERSION_STRING);
		if(m_cspecVersionString == null)
		{
			Throwable e = new MissingPropertyException(PROP_CSPEC_VERSION_STRING);
			errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(),
					ERROR_CODE_MISSING_PROPERTY_EXCEPTION));
		}

		m_cspecVersionType = properties.get(PROP_CSPEC_VERSION_TYPE);
		if(m_cspecVersionType == null)
		{
			Throwable e = new MissingPropertyException(PROP_CSPEC_VERSION_TYPE);
			errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(),
					ERROR_CODE_MISSING_PROPERTY_EXCEPTION));
		}

		m_eclipseSDKURL = properties.get(PROP_ECLIPSE_SDK_URL);
		tmp = properties.get(PROP_ECLIPSE_SDK_VERSION);
		try
		{
			m_eclipseSDKVersion = (tmp == null
					? null
					: VersionFactory.createVersion(IVersionType.OSGI, tmp));
		}
		catch(CoreException e)
		{
			m_eclipseSDKVersion = null;
		}

		m_eclipseDistroTools34URL = properties.get(PROP_ECLIPSE_DISTRO_TOOLS_34_URL);
		m_eclipseDistroTools33URL = properties.get(PROP_ECLIPSE_DISTRO_TOOLS_33_URL);

		m_eclipseDistroTools34UpdateSiteURL = properties.get(PROP_ECLIPSE_DISTRO_TOOLS_34_UPDATE_SITE_URL);
		m_eclipseDistroTools33UpdateSiteURL = properties.get(PROP_ECLIPSE_DISTRO_TOOLS_33_UPDATE_SITE_URL);

		m_learnMoreCloudfeedsURL = properties.get(PROP_LEARN_MORE_CLOUDFEEDS_URL);
		m_learnMoreCloudreaderURL = properties.get(PROP_LEARN_MORE_CLOUDREADER_URL);

		if(errorList.size() > 0)
		{
			m_problemInProperties = true;

			final IStatus topStatus;

			if(errorList.size() == 1)
			{
				topStatus = errorList.get(0).getStatus();
			}
			else
			{
				topStatus = createMultiStatusFromStatus(errorList.get(0).getStatus());
				for(int i = 1; i < errorList.size(); i++)
				{
					((MultiStatus)topStatus).add(errorList.get(i).getStatus());
				}
			}

			final String topErrorCode = errorList.get(0).getErrorCode();

			CorePlugin.logWarningsAndErrors(topStatus);
			Display.getDefault().syncExec(new Runnable()
			{
				public void run()
				{
					HelpLinkErrorDialog.openError(null, m_windowImage, ERROR_WINDOW_TITLE,
							"Error while reading materialization information", ERROR_HELP_TITLE, m_errorURL,
							topErrorCode, topStatus);
				}
			});
		}

		m_loginKey = properties.get(PROP_LOGIN_KEY);
	}

	private void showOriginalPage(IWizardPage originalPage)
	{
		WizardPage originalPreviousPage = (WizardPage)originalPage.getPreviousPage();
		getContainer().showPage(originalPage);
		originalPage.setPreviousPage(originalPreviousPage);
	}

	private static BMProperties readLocalProperties()
	{
		BMProperties localProperties = null;
		InputStream in = null;
		try
		{
			in = new FileInputStream(getLocalPropertiesLocation().toFile());
			localProperties = new BMProperties(in);
		}
		catch(FileNotFoundException e)
		{
			localProperties = new BMProperties(getDefaultLocalProperties());
		}
		catch(IOException e)
		{
			localProperties = new BMProperties(getDefaultLocalProperties());
			e.printStackTrace();
		}
		finally
		{
			IOUtils.close(in);
		}

		return localProperties;
	}

	private static Map<String, String> getDefaultLocalProperties()
	{
		Map<String, String> defaultLocalProperties = new HashMap<String, String>();
		defaultLocalProperties.put(LOCALPROP_ENABLE_TP_WIZARD, VALUE_TRUE);
		return defaultLocalProperties;
	}

	protected void saveLocalProperties()
	{
		OutputStream out = null;
		try
		{
			File propFile = getLocalPropertiesLocation().toFile();
			if(!propFile.exists())
				propFile.createNewFile();
			out = new FileOutputStream(propFile);
			m_localProperties.store(out, null);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			IOUtils.close(out);
		}
	}

	private static IPath getLocalPropertiesLocation()
	{
		return ResourcesPlugin.getWorkspace().getRoot().getLocation().append(META_AREA).append(
				MATERIALIZATOR_PROPERTIES);
	}
}

class LearnMoreItem
{
	private String m_string;

	private String m_url;

	public LearnMoreItem(String string, String url)
	{
		m_string = string;
		m_url = url;
	}

	public String getString()
	{
		return m_string;
	}

	public String getUrl()
	{
		return m_url;
	}
}
