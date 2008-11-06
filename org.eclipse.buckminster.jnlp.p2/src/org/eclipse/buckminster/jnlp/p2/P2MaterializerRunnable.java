/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.jnlp.p2;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.equinox.internal.p2.installer.InstallUpdateProductOperation;
import org.eclipse.equinox.internal.p2.installer.InstallerActivator;
import org.eclipse.equinox.internal.p2.installer.VersionedName;
import org.eclipse.equinox.internal.provisional.p2.installer.IInstallOperation;
import org.eclipse.equinox.internal.provisional.p2.installer.InstallDescription;
import org.eclipse.jface.operation.IRunnableWithProgress;

@SuppressWarnings("restriction")
public class P2MaterializerRunnable implements IRunnableWithProgress
{
	private final IInstallOperation m_installOperation;
	
	public P2MaterializerRunnable(IPath installLocation)
	{
		//org.eclipse.equinox.internal.app.Activator.getContainer();
		//org.eclipse.equinox.internal.frameworkadmin.equinox.Activator.getDefault();
		//org.eclipse.equinox.internal.simpleconfigurator.manipulator.Activator.getDefault();
		//InstallerActivator.getDefault();
		
		InstallDescription installDescription = new InstallDescription();

		try
		{
			installDescription.setMetadataRepositories(new URL[]{new URL("http://download.eclipse.org/eclipse/updates/3.4milestones/")});
			installDescription.setArtifactRepositories(new URL[]{new URL("http://download.eclipse.org/eclipse/updates/3.4milestones/")});
			installDescription.setLauncherName("eclipse");
			installDescription.setRoots(new VersionedName[] {new VersionedName("org.eclipse.sdk.ide", (String)null)});
			installDescription.setAutoStart(true);
			installDescription.setInstallLocation(installLocation);
			
			Map<String, String> profileProperties = new HashMap<String, String>();
			profileProperties.put("eclipse.p2.flavor", "tooling");
			profileProperties.put("eclipse.p2.profileName", "EclipseSDK01");
			installDescription.setProfileProperties(profileProperties);
		}
		catch(MalformedURLException e)
		{
			throw new RuntimeException(e);
		}
			
		m_installOperation = new InstallUpdateProductOperation(InstallerActivator.getDefault().getContext(), installDescription);
	}

	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
	{
		try
		{
			IStatus status = m_installOperation.install(monitor);
			if(status.getSeverity() == IStatus.ERROR)
				throw new CoreException(status);
		}
		catch(OperationCanceledException e)
		{
			throw new InterruptedException();
		}
		catch(Throwable t)
		{
			throw new InvocationTargetException(t);
		}
	}
}
