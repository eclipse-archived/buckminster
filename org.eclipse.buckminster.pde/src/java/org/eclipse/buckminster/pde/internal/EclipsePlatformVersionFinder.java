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

import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.MalformedProviderURIException;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.AbstractVersionFinder;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionDesignator;
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

	private final String m_componentName;
	private final InstalledType m_type;

	public EclipsePlatformVersionFinder(IReaderType readerType, Provider provider, IComponentType ctype, NodeQuery query) throws CoreException
	{
		super(provider, ctype, query);
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
		IVersion v = null;
		NodeQuery query = getQuery();
		IVersionDesignator dsg = query.getVersionDesignator();
		if(m_type == InstalledType.PLUGIN)
		{
			IPluginModelBase plugin = EclipsePlatformReaderType.getBestPlugin(m_componentName, dsg, query);
			if(plugin != null)
				v = VersionFactory.OSGiType.coerce(plugin.getBundleDescription().getVersion());
		}
		else
		{
			IFeatureModel feature = EclipsePlatformReaderType.getBestFeature(m_componentName, dsg, query);
			if(feature != null)
				v = VersionFactory.OSGiType.fromString(feature.getFeature().getVersion());
		}
		return (v == null) ? null : new VersionMatch(v, null, getProvider().getSpace(), -1L, null, null);
	}
}
