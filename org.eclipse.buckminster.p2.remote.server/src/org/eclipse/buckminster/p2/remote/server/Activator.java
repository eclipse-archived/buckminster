/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.p2.remote.server;

import java.io.File;

import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.internal.p2.core.helpers.ServiceHelper;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator
{
	public static final String ID = "org.eclipse.buckminster.p2.remote.server"; //$NON-NLS-1$

	private static BundleContext s_context;

	public static IArtifactRepositoryManager getArtifactRepositoryManager()
	{
		return (IArtifactRepositoryManager)ServiceHelper.getService(s_context,
			IArtifactRepositoryManager.class.getName());
	}

	public static BundleContext getContext()
	{
		return s_context;
	}

	public static IMetadataRepositoryManager getMetadataRepositoryManager()
	{
		return (IMetadataRepositoryManager)ServiceHelper.getService(s_context,
			IMetadataRepositoryManager.class.getName());
	}

	public static File getStateLocation()
	{
		return Platform.getStateLocation(s_context.getBundle()).toFile();
	}

	public void start(BundleContext context) throws Exception
	{
		s_context = context;
	}

	public void stop(BundleContext context) throws Exception
	{
		s_context = null;
	}
}
