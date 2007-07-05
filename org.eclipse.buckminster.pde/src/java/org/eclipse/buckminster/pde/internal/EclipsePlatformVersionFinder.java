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

import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.MalformedProviderURIException;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.AbstractVersionFinder;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionMatch;
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
public class EclipsePlatformVersionFinder extends AbstractVersionFinder
{
	enum InstalledType { FEATURE, PLUGIN }

	private final EclipsePlatformReaderType m_readerType;
	private final String m_componentName;
	private final InstalledType m_type;

	public EclipsePlatformVersionFinder(EclipsePlatformReaderType readerType, Provider provider, NodeQuery query) throws CoreException
	{
		super(provider, query);
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

	public VersionMatch getBestVersion(IProgressMonitor monitor) throws CoreException
	{
		if(m_type == InstalledType.PLUGIN)
		{
			IPluginModelBase plugin = m_readerType.getBestPlugin(m_componentName, null);
			if(plugin != null)
			{
				IVersion v = VersionFactory.OSGiType.coerce(plugin.getBundleDescription().getVersion());
				return new VersionMatch(v, null, getProvider().getSpace(), -1L, null, null);
			}
		}
		else
		{
			IFeatureModel feature = m_readerType.getBestFeature(m_componentName, null);
			if(feature != null)
			{
				String version = feature.getFeature().getVersion();
				IVersion v = VersionFactory.OSGiType.fromString(version);
				return new VersionMatch(v, null, getProvider().getSpace(), -1L, null, null);
			}
		}
		return null;
	}
}
