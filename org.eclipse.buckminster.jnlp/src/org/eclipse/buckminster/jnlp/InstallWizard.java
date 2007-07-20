/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp;

import static org.eclipse.buckminster.jnlp.MaterializationConstants.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.mspec.builder.MaterializationNodeBuilder;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.jnlp.accountservice.IAuthenticator;
import org.eclipse.buckminster.jnlp.progress.MaterializationProgressProvider;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 * 
 */
public class InstallWizard extends Wizard
{
	static private final String AUTHENTICATION_EXTPOINT = "org.eclipse.buckminster.jnlp.authentication";
	
	static private final String ATTRIBUTE_CLASS = "class";
	
	static private final String LEARNMORE_EXTPOINT = "org.eclipse.buckminster.jnlp.learnmore";
	
	static private final String ATTRIBUTE_STRING = "string";
	
	static private final String ATTRIBUTE_URL = "url";
	
	private Image m_brandingImage;
	
	private String m_brandingString;
	
	private URL m_mspecURL = null;
	
	private String m_artifactName;
	
	private String m_windowTitle;
	
	private Image m_windowImage;
	
	private Image m_wizardImage;
	
	private Image m_materializationImage;
	
	private String m_helpURL;
	
	private String m_moreInfoURL;
	
	private String m_errorURL;
	
	private boolean m_loginRequired;
	
	private String m_learnMoreURL;
	
	private String m_basePathURL;
	
	private final MaterializationSpecBuilder m_builder = new MaterializationSpecBuilder();
	
	private final List<MaterializationNodeBuilder> m_originalNodeBuilders = new ArrayList<MaterializationNodeBuilder>();
	
	private final List<MaterializationNodeBuilder> m_newNodeBuilders = new ArrayList<MaterializationNodeBuilder>();
	
	private final List<MSpecChangeListener> m_mspecListeners = new ArrayList<MSpecChangeListener>();
	
	private final Map<String,String> m_properties;
	
	private final IAuthenticator m_authenticator;
	
	private final List<LearnMoreItem> m_learnMores;
	
	private boolean m_problemInProperties = false;

	public InstallWizard(Map<String, String> properties)
	{
		setNeedsProgressMonitor(true);

		m_properties = properties;
		
		readProperties(properties);
		
		m_authenticator = createAuthenticator(m_loginRequired);
		
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

	private List<LearnMoreItem> createLearnMores()
	{
		List<LearnMoreItem> learnMores = new ArrayList<LearnMoreItem>();

		IExtensionRegistry er = Platform.getExtensionRegistry();
		IConfigurationElement[] elems = er.getConfigurationElementsFor(LEARNMORE_EXTPOINT);
		
		for(IConfigurationElement elem : elems)
		{
			learnMores.add(new LearnMoreItem(elem.getAttribute(ATTRIBUTE_STRING), elem.getAttribute(ATTRIBUTE_URL)));			
		}
		
		return learnMores;
	}

	@Override
	public void addPages()
	{
		addPage(new StartPage());

		if(!m_problemInProperties)
		{
			addPage(new LoginPage(m_authenticator == null ? "Virtual Distro Provider" : m_authenticator.getProvider()));
			addPage(new SimpleDownloadPage());
			addPage(new SimpleAdvancedPage());
			// TODO connection between wizard and server is not implemented yet
			//addPage(new RememberSettingsPage());
			// TODO replace with real spaces
			//addPage(new SpacePage(new String[] {"Space 1", "Space 2", "Space 3"}));
			addPage(new OperationPage());
			addPage(new DonePage());
		}
		
		setWindowTitle(m_windowTitle);

		if(m_windowImage != null)
		{
			getShell().setImage(m_windowImage);
		}
				
		if(m_wizardImage != null)
		{
			setDefaultPageImageDescriptor(ImageDescriptor.createFromImage(m_wizardImage));
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
							ERROR_HELP_URL, e.getErrorCode(), status);
				}
			});
		}
	}
    
    @Override
	public boolean performCancel()
    {
    	if(m_authenticator != null)
    	{
    		try
			{
				m_authenticator.logout();
			}
			catch(Throwable e)
			{
				// do nothing - session might be invalidated
			}
    	}
    	
    	return true;
    }
    
    @Override
	public boolean performFinish()
	{
		if(m_newNodeBuilders.size() > 0)
		{
			useNewNodes();
		}
		
		WizardPage originalPage = (WizardPage)getContainer().getCurrentPage();
		
		originalPage.setErrorMessage(null);
		try
		{
			OperationPage operationPage = (OperationPage)getPage("OperationStep");
			getContainer().showPage(operationPage);
			IJobManager jobManager = Job.getJobManager();
			((MaterializationProgressProvider)operationPage.getProgressProvider()).setEnabled(true);
			jobManager.setProgressProvider(operationPage.getProgressProvider());
			getContainer().run(true, true, new MaterializerRunnable(m_builder.createMaterializationSpec()));
			jobManager.setProgressProvider(null);
			((MaterializationProgressProvider)operationPage.getProgressProvider()).setEnabled(false);
			getContainer().showPage(getPage("DoneStep"));
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
	
	Image getImage(String imageName)
	{
		Class<?> myClass = this.getClass();
		String imageResource = "/icons/" + imageName;
		URL imageUrl = myClass.getResource(imageResource);
		return ImageDescriptor.createFromURL(imageUrl).createImage();
	}
	
	private void showOriginalPage(IWizardPage originalPage)
	{
		WizardPage originalPreviousPage = (WizardPage)originalPage.getPreviousPage();
		getContainer().showPage(originalPage);
		originalPage.setPreviousPage(originalPreviousPage);
	}

	private void useNewNodes()
	{
		List<MaterializationNodeBuilder> nodes = m_builder.getNodes();
		nodes.clear();

		for(MaterializationNodeBuilder nb : m_newNodeBuilders)
		{
			if(nb.getConflictResolution() != null ||
				nb.getDocumentation() != null ||
				nb.getInstallLocation() != null ||
				nb.getMaterializer() != null ||
				nb.getResourcePath() != null ||
				nb.isExclude())
			{
				nodes.add(nb);
			}
		}
	}

	MaterializationSpecBuilder getMaterializationSpecBuilder()
	{
		return m_builder;
	}

	List<MaterializationNodeBuilder> getOriginalMaterializationNodeBuilders()
	{
		return m_originalNodeBuilders;
	}
	
	List<MaterializationNodeBuilder> getNewMaterializationNodeBuilders()
	{
		return m_newNodeBuilders;
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

	@Override
	public String getWindowTitle()
	{
		return m_windowTitle;
	}
	
	Image getWindowImage()
	{
		return m_windowImage;
	}
	
	Image getWizardImage()
	{
		return m_wizardImage;
	}
	
	Image getMaterializationImage()
	{
		return m_materializationImage;
	}
	
	String getHelpURL()
	{
		return m_helpURL;
	}
	
	String getMoreInfoURL()
	{
		return m_moreInfoURL;
	}
	
	boolean isLoginRequired()
	{
		return m_loginRequired;
	}
	
	String getLearnMoreURL()
	{
		return m_learnMoreURL;
	}
	
	String getErrorURL()
	{
		return m_errorURL;
	}
	
	String[] getMaterializers()
	{
		return MATERIALIZERS;
	}
	
	IAuthenticator getAuthenticator()
	{
		return m_authenticator;
	}
	
	List<LearnMoreItem> getLearnMores()
	{
		return m_learnMores;
	}
	
	boolean isProblemInProperties()
	{
		return m_problemInProperties;
	}
	
	void initMSPEC() throws JNLPException
	{
		if(m_mspecURL == null)
			return;
		
		try
		{
// TODO http idea
/*			
			HttpClient client = new HttpClient();
			HttpMethod method = null;
			try
			{
				method = new GetMethod(m_mspecURL.toURI().toString());
			}
			catch(URISyntaxException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int status = client.executeMethod(method);

			InputStream stream = method.getResponseBodyAsStream();
*/
			
			InputStream stream = m_mspecURL.openStream();
			IParser<MaterializationSpec> parser =
				CorePlugin.getDefault().getParserFactory().getMaterializationSpecParser(true);
			
			m_builder.initFrom(parser.parse(ARTIFACT_TYPE_MSPEC, stream));
			m_originalNodeBuilders.addAll(m_builder.getNodes());

			MSpecChangeEvent event = new MSpecChangeEvent(m_builder);
			for(MSpecChangeListener listener : m_mspecListeners)
			{
				listener.handleMSpecChangeEvent(event);
			}
		} catch(FileNotFoundException e)
		{
			throw new JNLPException(
					"Cannot read materialization specification",
					ERROR_CODE_404_EXCEPTION,
					new BuckminsterException(m_mspecURL + " cannot be found"));
		}
		catch(IOException e)
		{
			// TODO how to match 403 error code better?
			if(e.getMessage() != null && e.getMessage().startsWith("Server returned HTTP response code: 403"))
			{
				throw new JNLPException(
						"Cannot read materialization specification",
						ERROR_CODE_403_EXCEPTION,
						new BuckminsterException(m_mspecURL + " - access denied"));
			}
			throw new JNLPException("Cannot read materialization specification", ERROR_CODE_REMOTE_IO_EXCEPTION, e);
		}
		catch(SAXException e)
		{
			throw new JNLPException("Cannot read materialization specification", ERROR_CODE_ARTIFACT_SAX_EXCEPTION, e);
		}
		
		m_mspecURL = null;
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
		m_moreInfoURL = properties.get(PROP_MORE_INFO_URL);
		
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
		
		m_learnMoreURL = properties.get(PROP_LEARN_MORE_URL);
		
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
