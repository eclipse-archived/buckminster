/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.jnlp.p2;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.jnlp.p2.installer.IInstallOperation;
import org.eclipse.buckminster.jnlp.p2.installer.InstallDescription;
import org.eclipse.buckminster.jnlp.p2.installer.InstallUpdateProductOperation;
import org.eclipse.buckminster.jnlp.p2.installer.Messages;
import org.eclipse.buckminster.jnlp.p2.installer.VersionedName;
import org.eclipse.core.net.proxy.IProxyService;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;

public class P2MaterializerRunnable implements IRunnableWithProgress
{
	private final IInstallOperation m_installOperation;
	
	public P2MaterializerRunnable(IPath installLocation) throws Exception
	{
		InstallDescription installDescription = new InstallDescription();

		installDescription.setMetadataRepositories(new URL[]{new URL("http://download.eclipse.org/eclipse/updates/3.4milestones/")});
		installDescription.setArtifactRepositories(new URL[]{new URL("http://download.eclipse.org/eclipse/updates/3.4milestones/")});
		installDescription.setLauncherName("eclipse");
		installDescription.setRoots(new VersionedName[] {new VersionedName("org.eclipse.sdk.ide", (String)null)});
		installDescription.setAutoStart(true);
		installDescription.setInstallLocation(installLocation);

		installDescription.setAgentLocation(installLocation.append("p2")); //$NON-NLS-1$
		installDescription.setBundleLocation(installLocation);
		
		Map<String, String> profileProperties = new HashMap<String, String>();
		profileProperties.put("eclipse.p2.flavor", "tooling");
		profileProperties.put("eclipse.p2.profileName", "EclipseSDK01");
		installDescription.setProfileProperties(profileProperties);
			
		initializeProxySupport();
		startRequiredBundles(installDescription);
		
		m_installOperation = new InstallUpdateProductOperation(JNLPPlugin.getDefault().getContext(), installDescription);
	}

	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
	{
		try
		{
			IStatus status = m_installOperation.install(monitor);
			switch(status.getSeverity())
			{
			case IStatus.ERROR:
				throw new CoreException(status);
			case IStatus.CANCEL:
				throw new InterruptedException();
			}
		}
		catch(InterruptedException e)
		{
			throw e;
		}
		catch(Throwable t)
		{
			throw new InvocationTargetException(t);
		}
	}
	
	private void initializeProxySupport()
	{
		IProxyService proxies = (IProxyService)getService(JNLPPlugin.getDefault().getContext(), IProxyService.class
				.getName());
		if(proxies == null)
			return;
		proxies.setProxiesEnabled(true);
		proxies.setSystemProxiesEnabled(true);
	}

	private static Object getService(BundleContext context, String name)
	{
		if(context == null)
			return null;
		ServiceReference reference = context.getServiceReference(name);
		if(reference == null)
			return null;
		Object result = context.getService(reference);
		context.ungetService(reference);
		return result;
	}
	
	/**
	 * Starts the p2 bundles needed to continue with the install.
	 */
	private void startRequiredBundles(InstallDescription description) throws CoreException {
		IPath installLocation = description.getInstallLocation();
		if (installLocation == null)
			throw new CoreException(new Status(IStatus.ERROR, JNLPPlugin.JNLP_P2, Messages.App_NoInstallLocation, null));
		//set agent location if specified
		IPath agentLocation = description.getAgentLocation();
		if (agentLocation != null) {
			String agentArea = System.getProperty("eclipse.p2.data.area"); //$NON-NLS-1$
			// TODO a bit of a hack here.  If the value is already set and it is set to @config/p2 then 
			// it may well be the default value put in by PDE.  Overwrite it.
			// Its kind of unclear why we would NOT overwrite.  At this point the user set their choice
			// of shared or standalone and those dicate where the agent should put its info...
			if (agentArea == null || agentArea.length() == 0 || agentArea.startsWith("@config")) //$NON-NLS-1$
				System.setProperty("eclipse.p2.data.area", agentLocation.toOSString()); //$NON-NLS-1$ 
		}
		//start up p2
		try {
			JNLPPlugin.getDefault().getBundle("org.eclipse.equinox.p2.exemplarysetup").start(Bundle.START_TRANSIENT); //$NON-NLS-1$
		} catch (BundleException e) {
			throw new CoreException(new Status(IStatus.ERROR, JNLPPlugin.JNLP_P2, Messages.App_FailedStart, e));
		}
	}
}
