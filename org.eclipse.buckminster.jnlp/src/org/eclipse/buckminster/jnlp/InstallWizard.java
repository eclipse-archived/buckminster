/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp;

import static org.eclipse.buckminster.jnlp.MaterializationConstants.ARTIFACT_TYPE_MSPEC;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.ARTIFACT_TYPE_UNKNOWN;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.ARTIFACT_UNKNOWN_TEXT;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_404_EXCEPTION;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.ERROR_CODE_ARTIFACT_EXCEPTION;
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
import static org.eclipse.buckminster.jnlp.MaterializationConstants.MATERIALIZERS;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_ARTIFACT_NAME;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_ARTIFACT_TYPE;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_ARTIFACT_VERSION;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_ARTIFACT_DESCRIPTION;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_ARTIFACT_DOCUMENTATION;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_ARTIFACT_URL;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_BASE_PATH_URL;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_ERROR_URL;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_HELP_URL;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_HOME_PAGE_URL;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_LEARN_MORE_URL;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_LOGIN_KEY;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_LOGIN_REQUIRED;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_MATERIALIZATION_IMAGE;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_PROFILE_TEXT;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_SERVICE_PROVIDER;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_SPACE_NAME;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_WINDOW_ICON;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_WINDOW_TITLE;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_WIZARD_ICON;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_CSPEC_NAME;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_CSPEC_TYPE;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_CSPEC_VERSION_STRING;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.PROP_CSPEC_VERSION_TYPE;
import static org.eclipse.buckminster.jnlp.MaterializationConstants.WINDOW_TITLE_UNKNOWN;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.builder.MaterializationNodeBuilder;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.jnlp.accountservice.IAuthenticator;
import org.eclipse.buckminster.jnlp.componentinfo.IComponentInfoProvider;
import org.eclipse.buckminster.jnlp.progress.MaterializationProgressProvider;
import org.eclipse.buckminster.jnlp.ui.general.wizard.AdvancedWizard;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.xml.sax.SAXException;

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
	
	private Image m_brandingImage;
	
	private String m_brandingString;
	
	private URL m_mspecURL = null;
	
	private BillOfMaterials m_cachedBOM;
	
	private URL m_cachedBOMURL;
	
	private String m_artifactName;
	
	private String m_artifactVersion;
	
	private String m_artifactDescription;
	
	private String m_artifactDocumentation;
	
	private String m_windowTitle;
	
	private Image m_windowImage;
	
	private Image m_wizardImage;
	
	private Image m_materializationImage;
	
	private String m_helpURL;
	
	private String m_moreInfoURL;
	
	private String m_errorURL = ERROR_HELP_URL;
	
	private boolean m_loginRequired;
	
	private String m_learnMoreURL;
	
	private String m_basePathURL;
	
	private String m_homePageURL;
	
	private String m_serviceProvider;
	
	private String m_spaceName;
	
	private String m_loginKey;
	
	private String m_loginKeyUserName;
	
	private String m_cspecName;
	
	private String m_cspecType;
	
	private String m_cspecVersionString;
	
	private String m_cspecVersionType;
	
	private boolean m_loginPageRequested = false;
	
	private LoginPage m_loginPage;
	
	private SpaceRestrictionPage m_spaceRestrictionPage;

	private SimpleDownloadPage m_downloadPage;
	
	private SimpleAdvancedPage m_advancedPage;
	
	private OperationPage m_operationPage;
	
	private DonePage m_donePage;
	
	private IUnresolvedNodeHandler m_unresolvedNodeHandler;
	
	private final MaterializationSpecBuilder m_builder = new MaterializationSpecBuilder();
	
	private final List<MSpecChangeListener> m_mspecListeners = new ArrayList<MSpecChangeListener>();
	
	private final Map<String,String> m_properties;
	
	private IAuthenticator m_authenticator;
	
	private IComponentInfoProvider m_infoProvider;
	
	private String m_infoPageURL;
	
	private String m_authenticatorUserName;
	
	private String m_authenticatorPassword;
	
	private final List<LearnMoreItem> m_learnMores;
	
	private boolean m_problemInProperties = false;

	public InstallWizard(Map<String, String> properties)
	{
		setNeedsProgressMonitor(true);

		m_properties = properties;
		
		readProperties(properties);
		
		m_authenticator = createAuthenticator(m_loginRequired);
		
		m_infoProvider = createComponentInfoProvider();
		
		m_learnMores = createLearnMores();
	}
	
	private IAuthenticator createAuthenticator(boolean needed)
	{
		IExtensionRegistry er = Platform.getExtensionRegistry();
		IConfigurationElement[] elems = er.getConfigurationElementsFor(AUTHENTICATION_EXTPOINT);
		IAuthenticator authenticator = null;

		try
		{
			if(elems.length != 1)
			{
				throw new JNLPException("Authenticator is not available", ERROR_CODE_NO_AUTHENTICATOR_EXCEPTION);
			}
			
			try
			{
				authenticator = (IAuthenticator) elems[0].createExecutableExtension(ATTRIBUTE_CLASS);
				authenticator.initialize(m_basePathURL);
				
				if(m_loginKey != null)
				{
					int result = authenticator.login(m_loginKey);
					
					if(result == IAuthenticator.LOGIN_UNKNOW_KEY)
						m_loginKey = null;
					
					if(result == IAuthenticator.LOGIN_OK)
						m_loginKeyUserName = authenticator.getCurrenlyLoggedUserName();
					else
						m_loginKeyUserName = null;
				}
			}
			catch(Throwable e)
			{
				throw new JNLPException("Cannot create authenticator", ERROR_CODE_AUTHENTICATOR_EXCEPTION, e);
			}
		}
		catch(JNLPException e)
		{
			if(needed)
			{
				m_problemInProperties = true;
				
				IStatus status = BuckminsterException.wrap(e.getCause() != null ? e.getCause() : e).getStatus();
				CorePlugin.logWarningsAndErrors(status);
				HelpLinkErrorDialog.openError(
						null, null, ERROR_WINDOW_TITLE,	e.getMessage(), ERROR_HELP_TITLE, m_errorURL, e.getErrorCode(), status);
			}
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
				infoProvider = (IComponentInfoProvider) elems[0].createExecutableExtension(ATTRIBUTE_CLASS);
			}
			catch(Throwable e)
			{
				throw new JNLPException("Cannot create component info provider", ERROR_CODE_RUNTIME_EXCEPTION, e);
			}
		}
		catch(JNLPException e)
		{
			m_problemInProperties = true;
			
			IStatus status = BuckminsterException.wrap(e.getCause() != null ? e.getCause() : e).getStatus();
			CorePlugin.logWarningsAndErrors(status);
			HelpLinkErrorDialog.openError(
					null, null, ERROR_WINDOW_TITLE,	e.getMessage(), ERROR_HELP_TITLE, m_errorURL, e.getErrorCode(), status);
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

	@Override
	protected void addAdvancedPages()
	{
		addAdvancedPage(new StartPage());

		if(!m_problemInProperties)
		{
			m_loginPage = new LoginPage(
						m_authenticator == null ? "Virtual Distro Provider" : getServiceProvider());
			addAdvancedPage(m_loginPage);

			m_downloadPage = new SimpleDownloadPage();
			addAdvancedPage(m_downloadPage);
			
			m_advancedPage = new SimpleAdvancedPage();
			addAdvancedPage(m_advancedPage);
			
			m_spaceRestrictionPage = new SpaceRestrictionPage();
			addAdvancedPage(m_spaceRestrictionPage);
			
			m_operationPage = new OperationPage();
			addAdvancedPage(m_operationPage);
			
			m_donePage = new DonePage();
			addAdvancedPage(m_donePage);
		}
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

			final IStatus status =
				e.getCause() == null ?
					BuckminsterException.wrap(e).getStatus() :
						BuckminsterException.wrap(e.getCause()).getStatus();

			CorePlugin.logWarningsAndErrors(status);
			Display.getDefault().syncExec(new Runnable()
			{
				public void run()
				{
					HelpLinkErrorDialog.openError(null, null, MaterializationConstants.ERROR_WINDOW_TITLE, e.getMessage(), ERROR_HELP_TITLE,
							m_errorURL, e.getErrorCode(), status);
				}
			});
		}
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

			if(m_authenticator != null)
			{
				try
				{
					m_authenticator.releaseConnection();
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
    	
    	return true;
    }
    
    @Override
	public boolean performFinish()
	{
    	if(m_unresolvedNodeHandler != null && m_unresolvedNodeHandler.isUnresolvedNodeIncluded())
    	{
    		MessageBox messageBox = new MessageBox(getContainer().getShell(), SWT.ICON_QUESTION | SWT.YES | SWT.NO);
    		messageBox.setMessage(
    				"Some distro dependencies cannot be resolved. " +
    				"You may decide to exclude the unresolved artifacts.\n" +
    				"However, excluding an artifact may result in a configuration that will no longer build.\n\n" +
    				"Do you want to exclude the unresolved artifacts?");
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
				m_builder.setInstallLocation(Path.fromOSString(MaterializationUtils.getDefaultDestination(getArtifactName())).addTrailingSeparator());
			
			MaterializationSpecBuilder builderToPerform = new MaterializationSpecBuilder();
			builderToPerform.initFrom(m_builder.createMaterializationSpec());
			
			if(m_cachedBOMURL != null)
				builderToPerform.setURL(m_cachedBOMURL);

			excludeCSsiteComponents(builderToPerform, getBOM());
			
			getContainer().showPage(m_operationPage);
			IJobManager jobManager = Job.getJobManager();
			((MaterializationProgressProvider)m_operationPage.getProgressProvider()).setEnabled(true);
			jobManager.setProgressProvider(m_operationPage.getProgressProvider());
			MaterializerRunnable mr = new MaterializerRunnable(builderToPerform.createMaterializationSpec());
			getContainer().run(true, true, mr);
			jobManager.setProgressProvider(null);
			((MaterializationProgressProvider)m_operationPage.getProgressProvider()).setEnabled(false);
			
			if(getComponentInfoProvider() != null)
				m_infoPageURL = getComponentInfoProvider().prepareHTML(
						getProperties(),
						getBOM().getResolution().getOPML(),
						MaterializationUtils.getDefaultDestination(null));
			
			getContainer().showPage(m_donePage);
			m_donePage.update(mr.getContext());
		}
		catch(InterruptedException e)
		{
			showOriginalPage(originalPage);
			originalPage.setErrorMessage("Operation cancelled");
		}
		catch(Exception e)
		{
			showOriginalPage(originalPage);
			
			final IStatus status = BuckminsterException.wrap(e).getStatus();
			CorePlugin.logWarningsAndErrors(status);
			HelpLinkErrorDialog.openError(
					null,
					m_windowImage,
					MaterializationConstants.ERROR_WINDOW_TITLE,
					"Materialization failed",
					MaterializationConstants.ERROR_HELP_TITLE,
					m_errorURL,
					ERROR_CODE_MATERIALIZATION_EXCEPTION,
					status);
		}
		
		return false;
	}
	
    // CSSITE components don't need to be materialized, so they are excluded
	private void excludeCSsiteComponents(MaterializationSpecBuilder mspec, DepNode depNode) throws CoreException
	{
		if(hasCSsiteReader(depNode))
			excludeComponent(mspec, depNode);
		
		for(DepNode childDepNode : depNode.getChildren())
			excludeCSsiteComponents(mspec, childDepNode);
	}

	private boolean hasCSsiteReader(DepNode depNode) throws CoreException
	{
		Resolution resolution = depNode.getResolution();
		
		if(resolution != null)
			if(MaterializationConstants.READER_TYPE_CSSITE.equals(resolution.getProvider().getReaderTypeId()))
				return true;
		
		return false;
	}
	
	private void excludeComponent(MaterializationSpecBuilder mspec, DepNode depNode) throws CoreException
	{
		Resolution resolution = depNode.getResolution();

		if(resolution != null)
		{
			CSpec cspec = resolution.getCSpec();

			if(cspec != null)
			{
				String componentName = cspec.getName();
				String componentType = cspec.getComponentTypeID();

				MaterializationNodeBuilder nodeBuilder = new MaterializationNodeBuilder();
				nodeBuilder.setNamePattern(Pattern.compile("^\\Q" + componentName + "\\E$"));
				nodeBuilder.setComponentTypeID(componentType);
				nodeBuilder.setExclude(true);
				
				mspec.getNodes().add(0, nodeBuilder);
			}
		}
	}

	private void showOriginalPage(IWizardPage originalPage)
	{
		WizardPage originalPreviousPage = (WizardPage)originalPage.getPreviousPage();
		getContainer().showPage(originalPage);
		originalPage.setPreviousPage(originalPreviousPage);
	}

	MaterializationSpecBuilder getMaterializationSpecBuilder()
	{
		return m_builder;
	}

	Map<String, String> getProperties()
	{
		return m_properties;
	}

	Image getBrandingImage()
	{
		return m_brandingImage;
	}

	String getBrandingString()
	{
		return m_brandingString;
	}
	
	String getArtifactName()
	{
		return m_artifactName;
	}
	
	String getArtifactVersion()
	{
		return m_artifactVersion;
	}
	
	String getArtifactDescription()
	{
		return m_artifactDescription;
	}
	
	String getArtifactDocumentation()
	{
		return m_artifactDocumentation;
	}
	
	@Override
	public String getWindowTitle()
	{
		return m_windowTitle;
	}
	
	@Override
	protected Image getWindowImage()
	{
		return m_windowImage;
	}
	
	@Override
	protected Image getWizardImage()
	{
		return m_wizardImage;
	}
	
	Image getMaterializationImage()
	{
		return m_materializationImage;
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
	
	boolean isLoginRequired()
	{
		return m_loginRequired;
	}
	
	boolean isLoggedIn()
	{
		boolean isLoggedIn = false;
		
		try
		{
			isLoggedIn = m_authenticator.isLoggedIn();
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
	
	void setLoginPageRequested(boolean loginPageRequested)
	{
		m_loginPageRequested = loginPageRequested;
	}
	
	String getLearnMoreURL()
	{
		return m_learnMoreURL;
	}
	
	String getErrorURL()
	{
		return m_errorURL;
	}
	
	String getServiceProviderHomePageURL()
	{
		return m_homePageURL;
	}
	
	String getServiceProvider()
	{
		return m_serviceProvider;
	}
	
	String getSpaceName()
	{
		return m_spaceName;
	}
	
	String getCSpecName()
	{
		return m_cspecName;
	}
	
	String getCSpecType()
	{
		return m_cspecType;
	}
	
	String getCSpecVersionString()
	{
		return m_cspecVersionString;
	}
	
	String getCSpecVersionType()
	{
		return m_cspecVersionType;
	}
	
	String[] getMaterializers()
	{
		return MATERIALIZERS;
	}
	
	public IAuthenticator getAuthenticator()
	{
		return m_authenticator;
	}
	
	public void setAuthenticator(IAuthenticator authenticator)
	{
		m_authenticator = authenticator;
	}
	
	public IComponentInfoProvider getComponentInfoProvider()
	{
		return m_infoProvider;
	}
	
	public String getComponentInfoPageURL()
	{
		return m_infoPageURL;
	}
	
	public String getAuthenticatorLoginKey()
	{
		return m_loginKey;
	}
	
	public void removeAuthenticatorLoginKey()
	{
		m_loginKey = null;
		m_loginKeyUserName = null;
	}
	
	public String getAuthenticatorLoginKeyUserName()
	{
		return m_loginKeyUserName;
	}
	
	public String getAuthenticatorCurrentUserName()
	{
		return getAuthenticator().getCurrenlyLoggedUserName();
	}
	
	public String getAuthenticatorUserName()
	{
		return m_authenticatorUserName;
	}
	
	public void setAuthenticatorUserName(String userName)
	{
		m_authenticatorUserName = userName;
	}
	
	public String getAuthenticatorPassword()
	{
		return m_authenticatorPassword;
	}

	public void setAuthenticatorPassword(String password)
	{
		m_authenticatorPassword = password;
	}
	
	List<LearnMoreItem> getLearnMores()
	{
		return m_learnMores;
	}
	
	boolean isProblemInProperties()
	{
		return m_problemInProperties;
	}
	

	int checkSpaceReadAccess() throws Exception
	{
		if(m_authenticator == null || m_spaceName == null)
			return IAuthenticator.SPACE_ACCESS_FORBIDDEN;
		
		return m_authenticator.checkSpaceReadAccess(m_spaceName);
	}	

	IWizardPage getDownloadPage()
	{
		try
		{
			int result = checkSpaceReadAccess();

			if(result == IAuthenticator.SPACE_ACCESS_FORBIDDEN ||
					result == IAuthenticator.SPACE_ACCESS_INVITATION_EXISTS ||
					result == IAuthenticator.SPACE_ACCESS_INVITATION_EXISTS_EMAIL_NOT_VERIFIED)
			{	
				m_spaceRestrictionPage.setStatus(result);
				
				return m_spaceRestrictionPage;
			}
		}
		catch(Exception e1)
		{
			// no information - try to get the artifact
		}

		return m_downloadPage;
	}
	
	boolean isMaterializerInitialized()
	{
		return m_cachedBOMURL != null;
	}
	
	void resetMaterializerInitialization()
	{
		m_cachedBOMURL = null;
	}
	
	void initializeMaterializer()
	{
		initMSPEC();
		initBOM();
	}
	
	private void initMSPEC()
	{
		try
		{
			HttpClient client;
			
			if(m_authenticator != null)
				client = m_authenticator.getHttpClient();
			else
				client = new HttpClient();
			
			HttpMethod method = null;
			InputStream stream = null;
			
			try
			{
				method = new GetMethod(m_mspecURL.toURI().toString());

				int status = client.executeMethod(method);
				MaterializationUtils.checkConnection(status, m_mspecURL.toString());

				stream = method.getResponseBodyAsStream();

				IParser<MaterializationSpec> parser = CorePlugin.getDefault().getParserFactory()
						.getMaterializationSpecParser(true);

				m_builder.initFrom(parser.parse(ARTIFACT_TYPE_MSPEC, stream));
				m_builder.setInstallLocation(MaterializationUtils.expandPath(m_builder, m_builder.getInstallLocation()));
			}
			catch(URISyntaxException e)
			{
				throw new JNLPException("Cannot read materialization specification",
						ERROR_CODE_MALFORMED_PROPERTY_EXCEPTION, e);
			}
			finally
			{
				IOUtils.close(stream);
				
				if(method != null)
					method.releaseConnection();
			}
			
			Display.getDefault().asyncExec(new Runnable(){

				public void run()
				{
					MSpecChangeEvent event = new MSpecChangeEvent(m_builder);
					for(MSpecChangeListener listener : m_mspecListeners)
					{
						listener.handleMSpecChangeEvent(event);
					}
				}});
			
		} catch(FileNotFoundException e)
		{
			throw new JNLPException(
					"Cannot read materialization specification",
					ERROR_CODE_404_EXCEPTION,
					BuckminsterException.fromMessage("%s cannot be found", m_mspecURL));
		}
		catch(IOException e)
		{
			throw new JNLPException("Cannot read materialization specification", ERROR_CODE_REMOTE_IO_EXCEPTION, e);
		}
		catch(CoreException e)
		{
			throw new JNLPException("Cannot read materialization specification", ERROR_CODE_ARTIFACT_EXCEPTION, e);
		}
	}
	
	private void initBOM()
	{
		m_cachedBOM = null;
		
		try
		{
			HttpClient client;
			
			if(m_authenticator != null)
				client = m_authenticator.getHttpClient();
			else
				client = new HttpClient();
			
			URL bomURL = getMaterializationSpecBuilder().getURL();

			HttpMethod method = null;
			InputStream stream = null;
			BillOfMaterials bom = null;
			
			try
			{
				method = new GetMethod(bomURL.toURI().toString());

				int status = client.executeMethod(method);
				MaterializationUtils.checkConnection(status, bomURL.toString());

				stream = method.getResponseBodyAsStream();

				IParser<BillOfMaterials> parser = CorePlugin.getDefault().getParserFactory()
						.getBillOfMaterialsParser(true);

				bom = parser.parse(bomURL.toString(), stream);
			}
			catch(URISyntaxException e)
			{
				throw new JNLPException("Cannot read materialization specification",
						ERROR_CODE_MALFORMED_PROPERTY_EXCEPTION, e);
			}
			finally
			{
				IOUtils.close(stream);
				
				if(method != null)
					method.releaseConnection();
			}

			m_cachedBOM = bom;
		}
		catch(CoreException e)
		{
			throw new JNLPException(
					"Cannot read artifact specification -\n\tmaterialization is supported only from BOM",
					ERROR_CODE_ARTIFACT_EXCEPTION, e);
		}
		catch(FileNotFoundException e)
		{
			throw new JNLPException("Cannot read artifact specification", ERROR_CODE_404_EXCEPTION,
					BuckminsterException.fromMessage("%s cannot be found", getMaterializationSpecBuilder().getURL()));
		}
		catch(IOException e)
		{
			throw new JNLPException("Cannot read artifact specification", ERROR_CODE_REMOTE_IO_EXCEPTION, e);
		}
		
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
		
		saveBOM(m_cachedBOM, cachedBOMFile);
		
		try
		{
			m_cachedBOMURL = cachedBOMFile.toURI().toURL();
		}
		catch(MalformedURLException e)
		{
			throw new JNLPException("Cannot create URL link to a temp file", ERROR_CODE_MALFORMED_PROPERTY_EXCEPTION, e);
		}
	}
		
	BillOfMaterials getBOM()
	{
		if(m_cachedBOM == null)
		{
			initBOM();
		}
	
		return m_cachedBOM;
	}
	
	void initMSpecTree()
	{
		m_advancedPage.initializeMSpecTree(getBOM());
	}
	
	void setUnresolvedNodeHandler(IUnresolvedNodeHandler unresolvedNodeHandler)
	{
		m_unresolvedNodeHandler = unresolvedNodeHandler;
	}

	void saveBOM(BillOfMaterials bom, File file)
	{
		try
		{
			FileOutputStream os = new FileOutputStream(file);
			Utils.serialize(bom, os);
			os.close();
		}
		catch(FileNotFoundException e1)
		{
			throw new JNLPException("File cannot be opened or created", ERROR_CODE_FILE_IO_EXCEPTION, e1);
		}
		catch(SAXException e1)
		{
			throw new JNLPException("Unable to read BOM specification", ERROR_CODE_ARTIFACT_EXCEPTION, e1);
		}
		catch(IOException e1)
		{
			throw new JNLPException("Cannot write to file", ERROR_CODE_FILE_IO_EXCEPTION, e1);
		}
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
			errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(), ERROR_CODE_MISSING_PROPERTY_EXCEPTION));
		}

		m_basePathURL = tmp;

		tmp = properties.get(PROP_ARTIFACT_TYPE);
		
		if(tmp == null)
		{
			Throwable e = new MissingPropertyException(PROP_ARTIFACT_TYPE);
			errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(), ERROR_CODE_MISSING_PROPERTY_EXCEPTION));
			tmp = ARTIFACT_TYPE_UNKNOWN;
		}

		String artifactType = tmp;
		
		tmp = properties.get(PROP_ARTIFACT_URL);
		if(tmp == null)
		{
			Throwable e = new MissingPropertyException(PROP_ARTIFACT_URL);
			errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(), ERROR_CODE_MISSING_PROPERTY_EXCEPTION));
		} else
		{
			try
			{
				if(ARTIFACT_TYPE_MSPEC.equals(artifactType))
				{
					m_mspecURL = new URL(tmp);
					// initURL() is called in LoginPage
				} else
				{
					m_builder.setURL(new URL(tmp));
				}
			}
			catch(MalformedURLException e)
			{
				errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(), ERROR_CODE_MALFORMED_PROPERTY_EXCEPTION));
			}
		}
		
		tmp = properties.get(PROP_ARTIFACT_NAME);
		if(tmp == null)
		{
			Throwable e = new MissingPropertyException(PROP_ARTIFACT_NAME);
			errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(), ERROR_CODE_MISSING_PROPERTY_EXCEPTION));
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
			errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(), ERROR_CODE_MISSING_PROPERTY_EXCEPTION));
			tmp = ARTIFACT_UNKNOWN_TEXT;
		}
		m_brandingString = tmp;

		tmp = properties.get(PROP_WINDOW_TITLE);
		if(tmp == null)
		{
			Throwable e = new MissingPropertyException(PROP_WINDOW_TITLE);
			errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(), ERROR_CODE_MISSING_PROPERTY_EXCEPTION));
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
				errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(), ERROR_CODE_MALFORMED_PROPERTY_EXCEPTION));
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
				errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(), ERROR_CODE_MALFORMED_PROPERTY_EXCEPTION));
			}
		}
		
		tmp = properties.get(PROP_MATERIALIZATION_IMAGE);
		m_materializationImage = null;
		if(tmp != null)
		{
			try
			{
				m_materializationImage = ImageDescriptor.createFromURL(new URL(tmp)).createImage();
			}
			catch(MalformedURLException e)
			{
				errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(), ERROR_CODE_MALFORMED_PROPERTY_EXCEPTION));
			}
		}
		
		m_helpURL = properties.get(PROP_HELP_URL);
		// TODO use different helpURL and moreInfoURL, now there is just helpURL
		m_moreInfoURL = m_helpURL;
		//m_moreInfoURL = properties.get(PROP_MORE_INFO_URL);
		
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

		m_spaceName = properties.get(PROP_SPACE_NAME);

		m_cspecName = properties.get(PROP_CSPEC_NAME);
		if(m_cspecName == null)
		{
			Throwable e = new MissingPropertyException(PROP_CSPEC_NAME);
			errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(), ERROR_CODE_MISSING_PROPERTY_EXCEPTION));
		}
		
		m_cspecType = properties.get(PROP_CSPEC_TYPE);
		if(m_cspecType == null)
		{
			Throwable e = new MissingPropertyException(PROP_CSPEC_TYPE);
			errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(), ERROR_CODE_MISSING_PROPERTY_EXCEPTION));
		}
		
		m_cspecVersionString = properties.get(PROP_CSPEC_VERSION_STRING);
		if(m_cspecVersionString == null)
		{
			Throwable e = new MissingPropertyException(PROP_CSPEC_VERSION_STRING);
			errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(), ERROR_CODE_MISSING_PROPERTY_EXCEPTION));
		}
		
		m_cspecVersionType = properties.get(PROP_CSPEC_VERSION_TYPE);
		if(m_cspecVersionType == null)
		{
			Throwable e = new MissingPropertyException(PROP_CSPEC_VERSION_TYPE);
			errorList.add(new ErrorEntry(BuckminsterException.wrap(e).getStatus(), ERROR_CODE_MISSING_PROPERTY_EXCEPTION));
		}

		if(errorList.size() > 0)
		{
			m_problemInProperties = true;
			
			final IStatus topStatus;
			
			if(errorList.size() == 1)
			{
				topStatus = errorList.get(0).getStatus();
			} else
			{
				topStatus = createMultiStatusFromStatus(errorList.get(0).getStatus());
				for(int i = 1; i < errorList.size(); i++)
				{
					((MultiStatus) topStatus).add(errorList.get(i).getStatus());
				}
			}		
			
			final String topErrorCode = errorList.get(0).getErrorCode();
			
			CorePlugin.logWarningsAndErrors(topStatus);
			Display.getDefault().syncExec(new Runnable()
			{
				public void run()
				{
					HelpLinkErrorDialog.openError(
							null,
							m_windowImage,
							ERROR_WINDOW_TITLE,
							"Error while reading materialization information",
							ERROR_HELP_TITLE,
							m_errorURL,
							topErrorCode,
							topStatus);
				}
			});
		}
		
		m_loginKey = properties.get(PROP_LOGIN_KEY);
	}		

	/**
	 * Wizard page doesn't display message text (the second line in title area) if the wizard image is too small
	 * This function creates a new image that is 64 pixels high - adds to the original image transparent stripe
	 * 
	 * @param origImage original image
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
		
		ImageData newImageData = new ImageData(origImageData.width, WIZARD_IMAGE_HEIGHT, origImageData.depth, origImageData.palette);
		
		newImageData.alpha = origImageData.alpha;
		
		ImageData transparencyMask = origImageData.getTransparencyMask();
		boolean testTransparency = origImageData.getTransparencyType() == SWT.TRANSPARENCY_MASK || origImageData.getTransparencyType() == SWT.TRANSPARENCY_PIXEL;

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
	
	private static MultiStatus createMultiStatusFromStatus(IStatus status)
	{
		return new MultiStatus(
				status.getPlugin(), status.getCode(), status.getMessage(), status.getException());
	}
	
	public void addMSpecChangeListener(MSpecChangeListener listener)
	{
		m_mspecListeners.add(listener);
	}
	
	public void removeMSpecChangeListener(MSpecChangeListener listener)
	{
		m_mspecListeners.remove(listener);
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
