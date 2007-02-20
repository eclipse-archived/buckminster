/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.pde.internal;

import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.MalformedProviderURIException;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionQuery;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelectorFactory;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;

/**
 * A Reader that knows about features and plugins that are part of an Eclipse
 * installation.
 * @author thhal
 */
@SuppressWarnings("restriction")
public class EclipsePlatformVersionFinder implements IVersionFinder
{
	enum InstalledType { FEATURE, PLUGIN }

	private final EclipsePlatformReaderType m_readerType;
	private final String m_componentName;
	private final InstalledType m_type;

	public EclipsePlatformVersionFinder(EclipsePlatformReaderType readerType, Provider provider, NodeQuery query) throws CoreException
	{
		m_readerType = readerType;
		String uri = provider.getURI(query.getProperties());
		IPath path = new Path(uri);
		if(path.segmentCount() == 2)
		{
			m_type = InstalledType.valueOf(path.segment(0).toUpperCase());
			if(m_type != null)
			{
				m_componentName = path.segment(1);
				return;
			}
		}
		throw new MalformedProviderURIException(readerType, uri);
	}

	public void close()
	{
	}

	public VersionMatch getBestVersion(IVersionQuery query, IProgressMonitor monitor) throws CoreException
	{
		VersionMatch dflt = this.getDefaultVersion(monitor);
		return dflt != null && query.matches(dflt.getVersion()) ? dflt : null;
	}

	public VersionMatch getDefaultVersion(IProgressMonitor monitor) throws CoreException
	{
		if(m_type == InstalledType.PLUGIN)
		{
			IPluginModelBase plugin = m_readerType.getBestPlugin(m_componentName, null);
			if(plugin != null)
			{
				String version = plugin.getBundleDescription().getVersion().toString();
				IVersion v = VersionFactory.OSGiType.fromString(version);
				return new VersionMatch(v, VersionSelectorFactory.tag(version));
			}
		}
		else
		{
			IFeatureModel feature = m_readerType.getBestFeature(m_componentName, null);
			if(feature != null)
			{
				String version = feature.getFeature().getVersion();
				IVersion v = VersionFactory.OSGiType.fromString(version);
				return new VersionMatch(v, VersionSelectorFactory.tag(version));
			}
		}
		return null;
	}
}
