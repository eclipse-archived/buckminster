/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import static org.eclipse.buckminster.jnlp.MaterializationConstants.*;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.mspec.builder.MaterializationNodeBuilder;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.jnlp.accountservice.IPublisher;
import org.eclipse.buckminster.jnlp.ui.general.wizard.AdvancedWizard;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.graphics.Image;
import org.xml.sax.SAXException;

/**
 * @author Karel Brezina
 *
 */
public class PublishWizard extends AdvancedWizard
{
	private static final String PUBLICATION_EXTPOINT = "org.eclipse.buckminster.jnlp.publication";
	
	private static final String ATTRIBUTE_CLASS = "class";

	private static final String PUBLISH_WINDOW_TITLE = "Publish Distro";
	
	private final InstallWizard m_installWizard;
	
	private IPublisher m_publisher;
	
	private MaterializationSpecBuilder m_mspec;
	
	private BillOfMaterials m_bom;
	
	private String m_originalSpaceName;
	
	public PublishWizard(InstallWizard installWizard)
	{
		setNeedsProgressMonitor(true);

		m_installWizard = installWizard;
		
		try
		{
			IPublisher originalPublisher = (IPublisher)installWizard.getAuthenticator();
			
			if(originalPublisher == null)
				throw new JNLPException("Cannot create publisher - missing plugin", ERROR_CODE_AUTHENTICATOR_EXCEPTION);
			
			m_publisher = originalPublisher.createDuplicatePublisher(true);
		}
		catch(Throwable e)
		{
			if(e instanceof JNLPException)
				throw (JNLPException)e;
			
			throw new JNLPException("Cannot create publisher", ERROR_CODE_AUTHENTICATOR_EXCEPTION, e);
		}
		
		m_mspec = new MaterializationSpecBuilder();
		m_mspec.initFrom(installWizard.getMaterializationSpecBuilder().createMaterializationSpec());
		m_mspec.setInstallLocation(MaterializationUtils.generalizePath(m_mspec, m_mspec.getInstallLocation()));
		
		for(MaterializationNodeBuilder builder : m_mspec.getNodes())
		{
			builder.setInstallLocation(MaterializationUtils.generalizePath(m_mspec, builder.getInstallLocation()));
		}
		
		m_bom = installWizard.getBOM();
		m_originalSpaceName = installWizard.getSpaceName();
	}
	
	protected IPublisher createPublisher(String basePathURL)
	{
		IExtensionRegistry er = Platform.getExtensionRegistry();
		IConfigurationElement[] elems = er.getConfigurationElementsFor(PUBLICATION_EXTPOINT);
		IPublisher publisher = null;

		if(elems.length != 1)
		{
			throw new JNLPException("Publisher is not available", ERROR_CODE_NO_PUBLISHER_EXCEPTION);
		}

		try
		{
			publisher = (IPublisher)elems[0].createExecutableExtension(ATTRIBUTE_CLASS);
			publisher.initialize(basePathURL);
		}
		catch(Throwable e)
		{
			throw new JNLPException("Cannot create publisher", ERROR_CODE_PUBLISHER_EXCEPTION, e);
		}

		return publisher;
	}

	@Override
	protected void addAdvancedPages()
	{
		if(!isLoggedIn())
			addAdvancedPage(new PublishLoginPage(getServiceProvider()));
		addAdvancedPage(new PublishSpacePage());
	}

	@Override
	public boolean performFinish()
	{
		PublishSpacePage spacePage = (PublishSpacePage)getPage(STEP_PUBLISH);
		final String selectedSpace = spacePage.getSelectedSpace();
		final String artifactName = m_mspec.getName();
		
		try
		{
			getContainer().run(true, false, new IRunnableWithProgress()
			{
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
				{
					monitor.beginTask(null, IProgressMonitor.UNKNOWN);
					monitor.subTask("Publishing materialization specification");
					publishMSpec(selectedSpace, artifactName);
					monitor.done();
				}
			});
		}
		catch(Exception e)
		{
			if(e instanceof JNLPException)
			{
				throw (JNLPException) e;
			}
			
			if(e.getCause() != null && e.getCause() instanceof JNLPException)
			{
				throw ((JNLPException)e.getCause());
			}
			
			throw new JNLPException(
					"Error while publishing materialization specification", ERROR_CODE_PUBLISHING_EXCEPTION, e);
		}
		
		return true;
	}

	private void publishMSpec(String selectedSpace, String artifactName)
	{
		MaterializationSpec mspec = getMSpecBuilder().createMaterializationSpec();

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try
		{
			Utils.serialize(mspec, out);
		}
		catch(SAXException e)
		{
			throw new JNLPException("Unable to read materialization specification", MaterializationConstants.ERROR_CODE_ARTIFACT_EXCEPTION, e);
		}

		int result;
		try
		{
			String xmlData = out.toString("UTF-8");
			result = getPublisher().publish(m_originalSpaceName, selectedSpace, artifactName, xmlData, true);
		}
		catch(Exception e)
		{
			throw new JNLPException("Unable to publish materialization specification", MaterializationConstants.ERROR_CODE_PUBLISHING_EXCEPTION, e);
		}
		
		if(result != IPublisher.STATUS_OK)
		{
			switch(result)
			{
			case IPublisher.ERROR_NOT_LOGGED_IN:
				throw new JNLPException("Publishing was not successful - login again and repeat publishing", MaterializationConstants.ERROR_CODE_PUBLISHING_EXCEPTION);
			case IPublisher.ERROR_SPACE_ACCESS_DENIED:
				throw new JNLPException("Publishing was not successful - the selected space has restricted access", MaterializationConstants.ERROR_CODE_PUBLISHING_EXCEPTION);
			case IPublisher.ERROR_ARTIFACT_ALREADY_EXISTS:
				throw new JNLPException("Publishing was not successful - the distro already exists in the selected space", MaterializationConstants.ERROR_CODE_PUBLISHING_EXCEPTION);
			default:
				throw new JNLPException("Publishing was not successful", MaterializationConstants.ERROR_CODE_PUBLISHING_EXCEPTION);
			}
		}
	}

	@Override
	public String getWindowTitle()
	{
		return PUBLISH_WINDOW_TITLE;
	}

	@Override
	public String getHelpURL()
	{
		return m_installWizard.getHelpURL();
	}
	
	@Override
	public String getMoreInfoURL()
	{
		return m_installWizard.getMoreInfoURL();
	}

	@Override
	protected Image getWindowImage()
	{
		return m_installWizard.getWindowImage();
	}
	
	@Override
	protected Image getWizardImage()
	{
		return m_installWizard.getWizardImage();
	}
	
	MaterializationSpecBuilder getMSpecBuilder()
	{
		return m_mspec;
	}
	
	BillOfMaterials getBOM()
	{
		return m_bom;
	}
	
	String getServiceProvider()
	{
		return m_installWizard.getServiceProvider();
	}
	
	IPublisher getPublisher()
	{
		return m_publisher;
	}
	
	void setPublisher(IPublisher publisher)
	{
		m_publisher = publisher;
	}
	
	boolean isLoggedIn()
	{
		boolean isLoggedIn = false;
		
		try
		{
			isLoggedIn = m_publisher.isLoggedIn();
		}
		catch(Exception e1)
		{
			// nothing isLoggedIn == false
		}
		
		return isLoggedIn;
	}
		
	String getLoginKey()
	{
		return m_installWizard.getAuthenticatorLoginKey();
	}
	
	void removeLoginKey()
	{
		m_installWizard.removeAuthenticatorLoginKey();
	}
	
	String getLoginKeyUserName()
	{
		return m_installWizard.getAuthenticatorLoginKeyUserName();
	}
	
	String getCurrentUserName()
	{
		return getPublisher().getCurrenlyLoggedUserName();
	}
	
	String getPreferredUserName()
	{
		return m_installWizard.getAuthenticatorUserName();
	}
	
	void setPreferredUserName(String userName)
	{
		m_installWizard.setAuthenticatorUserName(userName);
	}
	
	String getPreferredPassword()
	{
		return m_installWizard.getAuthenticatorPassword();
	}

	void setPreferredPassword(String password)
	{
		m_installWizard.setAuthenticatorPassword(password);
	}
}
