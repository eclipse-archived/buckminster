/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.p4.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.eclipse.buckminster.p4.Messages;
import org.eclipse.buckminster.p4.P4Plugin;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Bundle;

/**
 * The Buckminster p4 plugin is not dependent on the Perforce P4WSAD plugin but if it exists it will be notified of new
 * p4 projects that Buckminster binds into the workspace through this class. This class will use the OSGi bundle
 * framework and reflective API's to do its job.
 * 
 * @author thhal
 */
public abstract class P4WSADBridge
{
	private static boolean s_stateKnown = false;

	private static Constructor<?> s_connectionInfoCtor;

	private static Method s_manageProjectMethod;

	public static synchronized boolean isPresent()
	{
		if(!s_stateKnown)
		{
			Logger logger = P4Plugin.getLogger();
			Bundle bundle = Platform.getBundle("com.perforce.team.core"); //$NON-NLS-1$
			if(bundle == null)
			{
				logger.debug(Messages.p4wsad_plugin_not_present);
				s_stateKnown = true;
				return false;
			}

			try
			{
				Class<?> perforceProviderPluginClass = bundle
						.loadClass("com.perforce.team.core.PerforceProviderPlugin"); //$NON-NLS-1$
				Class<?> connectionInfoClass = bundle.loadClass("com.perforce.p4api.ConnectionParameters"); //$NON-NLS-1$
				s_connectionInfoCtor = connectionInfoClass.getConstructor(new Class[] { String.class });
				s_manageProjectMethod = perforceProviderPluginClass.getMethod("manageProject", //$NON-NLS-1$
						new Class[] { IProject.class, connectionInfoClass });
				logger.debug(Messages.p4wsad_plugin_is_present);
			}
			catch(Exception e)
			{
				logger.debug(NLS.bind(Messages.p4wsad_plugin_cannot_be_loaded_0, e.toString()));
			}
			s_stateKnown = true;
		}
		return s_manageProjectMethod != null;
	}

	public static void shareProject(IProject project, DepotURI depotURI) throws CoreException
	{
		if(!isPresent())
			throw BuckminsterException.fromMessage(Messages.p4wsad_plugin_is_not_present);

		StringBuilder bld = new StringBuilder();
		bld.append("P4PORT "); //$NON-NLS-1$
		bld.append(depotURI.getAddress());
		bld.append(" P4CLIENT "); //$NON-NLS-1$
		bld.append(depotURI.getClientName());

		String tmp = depotURI.getUser();
		if(tmp != null)
		{
			bld.append(" P4USER "); //$NON-NLS-1$
			bld.append(tmp);
			bld.append(" USELOGIN true"); //$NON-NLS-1$
		}

		tmp = depotURI.getPassword();
		if(tmp != null)
		{
			bld.append(" P4PASSWD "); //$NON-NLS-1$
			bld.append(tmp);
			bld.append(" SAVEPASS true"); //$NON-NLS-1$
		}

		Logger logger = P4Plugin.getLogger();
		try
		{
			logger.debug(NLS.bind(Messages.sharing_project_0_to_p4, project.getName()));
			s_manageProjectMethod.invoke(null, new Object[] { project,
					s_connectionInfoCtor.newInstance(new Object[] { bld.toString() }) });
		}
		catch(Exception e)
		{
			// We don't consider this an error but it's worth a warning.
			//
			logger.warning(NLS.bind(Messages.problems_when_sharing_project_to_P4WSAD_0, e.getMessage()));
		}
	}
}
