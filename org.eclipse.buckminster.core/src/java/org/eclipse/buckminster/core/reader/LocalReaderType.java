/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.reader;

import java.io.File;
import java.net.URI;

import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.model.ConflictResolution;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.IVersionQuery;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

/**
 * @author Thomas Hallgren
 */
public class LocalReaderType extends URLCatalogReaderType
{
	private static final IVersionFinder s_blindFinder = new IVersionFinder()
	{
		public void close()
		{
		}

		public VersionMatch getBestVersion(IVersionQuery query, IProgressMonitor monitor) throws CoreException
		{
			MonitorUtils.complete(monitor);
			return null;
		}

		public VersionMatch getDefaultVersion(IProgressMonitor monitor) throws CoreException
		{
			MonitorUtils.complete(monitor);
			return null;
		}		
	};

	@Override
	public URI getURI(String repository) throws CoreException
	{
		return new File(repository).toURI();
	}

	@Override
	public IPath getMaterializationLocation(Resolution rc, MaterializationContext context, boolean[] optional) throws CoreException
	{
		return null;
	}

	@Override
	public IPath getFixedLocation(Resolution rc) throws CoreException
	{
		// Should be OK unless we encounter a workspace with old metadata
		//
		return new Path(rc.getRepository());
	}

	@Override
	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new LocalReader(this, providerMatch);
	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider, NodeQuery nodeQuery, IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return nodeQuery.useExistingArtifacts() == ConflictResolution.KEEP
			? new DefaultVersionFinder()
			: s_blindFinder;
	}
}
