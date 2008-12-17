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
import java.net.MalformedURLException;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.AbstractVersionFinder;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

/**
 * @author Thomas Hallgren
 */
public class LocalReaderType extends URLCatalogReaderType
{
	private static final IVersionFinder s_blindFinder = new AbstractVersionFinder(null, null, null)
	{
		public VersionMatch getBestVersion(IProgressMonitor monitor) throws CoreException
		{
			MonitorUtils.complete(monitor);
			return null;
		}
	};

	@Override
	public IPath getFixedLocation(Resolution rc) throws CoreException
	{
		try
		{
			File file = FileUtils.getFile(URLUtils.normalizeToURL(rc.getRepository()));
			if(file == null)
				throw new IllegalArgumentException(Messages.Resolution_not_created_using_LocalReader);
			IPath path = Path.fromOSString(file.toString());
			if(path.toFile().isDirectory())
				path = path.addTrailingSeparator();
			return path;
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	@Override
	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new LocalReader(this, providerMatch);
	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider, IComponentType ctype, NodeQuery nodeQuery,
			IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return nodeQuery.useWorkspace() || nodeQuery.useMaterialization()
				? new DefaultVersionFinder(provider, ctype, nodeQuery)
				: s_blindFinder;
	}
}
