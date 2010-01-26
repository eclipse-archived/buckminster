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

import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ResolverDecisionType;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.AbstractVersionFinder;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.pde.mapfile.MapFileEntry;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;

@SuppressWarnings({ "restriction" })
public class EclipseImportFinder extends AbstractVersionFinder
{
	private static List<MapFileEntry> getSiteEntries(URL location, IConnectContext cctx, NodeQuery query,
			IProgressMonitor monitor) throws CoreException
	{
		String cacheKey = location.toString().intern();
		synchronized(cacheKey)
		{
			Map<String, List<MapFileEntry>> cache = getMapEntriesCache(query.getContext().getUserCache());
			List<MapFileEntry> entries = cache.get(cacheKey);
			if(entries != null)
				return entries;

			if(location.getPath().endsWith(".map")) //$NON-NLS-1$
			{
				MonitorUtils.complete(monitor);
				entries = EclipseImportReaderType.getMapEntries(location, cctx);
			}
			else
				entries = Collections.emptyList();
			cache.put(cacheKey, entries);
			return entries;

		}
	}

	private final EclipseImportReaderType m_readerType;

	private final EclipseImportBase m_base;

	private final NodeQuery m_query;

	private static final UUID CACHE_KEY_PLUGIN_ENTRIES_CACHE = UUID.randomUUID();

	private static Map<String, List<MapFileEntry>> getMapEntriesCache(Map<UUID, Object> ctxUserCache)
	{
		synchronized(ctxUserCache)
		{
			@SuppressWarnings("unchecked")
			Map<String, List<MapFileEntry>> cache = (Map<String, List<MapFileEntry>>)ctxUserCache.get(CACHE_KEY_PLUGIN_ENTRIES_CACHE);
			if(cache == null)
			{
				cache = Collections.synchronizedMap(new HashMap<String, List<MapFileEntry>>());
				ctxUserCache.put(CACHE_KEY_PLUGIN_ENTRIES_CACHE, cache);
			}
			return cache;
		}
	}

	public EclipseImportFinder(EclipseImportReaderType readerType, Provider provider, IComponentType ctype,
			NodeQuery query) throws CoreException
	{
		super(provider, ctype, query);
		m_base = EclipseImportBase.obtain(query, provider.getURI(query.getProperties()));
		m_readerType = readerType;
		m_query = query;
	}

	public VersionMatch getBestVersion(IProgressMonitor monitor) throws CoreException
	{
		return m_base.isFeature()
				? getBestFeatureVersion(monitor)
				: getBestPluginVersion(monitor);
	}

	private VersionMatch getBestFeatureVersion(IProgressMonitor monitor) throws CoreException
	{
		return m_base.isLocal()
				? getBestLocalFeatureVersion(monitor)
				: getBestRemoteFeatureVersion(monitor);
	}

	private VersionMatch getBestLocalFeatureVersion(IProgressMonitor monitor) throws CoreException
	{
		Version bestFit = null;
		for(IFeatureModel model : m_base.getFeatureModels(m_readerType, monitor))
		{
			Version version = VersionHelper.parseVersion(model.getFeature().getVersion());
			if(getQuery().isMatch(version, null) && (bestFit == null || version.compareTo(bestFit) > 0))
				bestFit = version;
		}
		if(bestFit == null)
			return null;
		return new VersionMatch(bestFit, null, -1, null, null);
	}

	private VersionMatch getBestLocalPluginVersion(IProgressMonitor monitor) throws CoreException
	{
		Version bestFit = null;
		for(IPluginModelBase model : m_base.getPluginModels(m_readerType, monitor))
		{
			Version version = Version.fromOSGiVersion(model.getBundleDescription().getVersion());
			if(getQuery().isMatch(version, null))
			{
				if(bestFit == null)
					bestFit = version;
				else if(version.compareTo(bestFit) > 0)
				{
					logDecision(ResolverDecisionType.VERSION_REJECTED, bestFit,
							NLS.bind(Messages._0_is_higher, version));
					bestFit = version;
				}
			}
		}
		if(bestFit == null)
			return null;
		return new VersionMatch(bestFit, null, -1, null, null);
	}

	private VersionMatch getBestPluginVersion(IProgressMonitor monitor) throws CoreException
	{
		return m_base.isLocal()
				? getBestLocalPluginVersion(monitor)
				: getBestRemotePluginVersion(monitor);
	}

	private VersionMatch getBestRemoteFeatureVersion(IProgressMonitor monitor) throws CoreException
	{
		return getBestRemoteVersion(IComponentType.ECLIPSE_FEATURE, monitor);
	}

	private VersionMatch getBestRemotePluginVersion(IProgressMonitor monitor) throws CoreException
	{
		return getBestRemoteVersion(IComponentType.OSGI_BUNDLE, monitor);
	}

	private VersionMatch getBestRemoteVersion(String ctypeId, IProgressMonitor monitor) throws CoreException
	{
		Version bestFit = null;

		String cname = m_base.getComponentName();
		for(MapFileEntry entry : getSiteEntries(m_base.getRemoteLocation(), getConnectContext(), m_query, monitor))
		{
			ComponentIdentifier cid = entry.getComponentIdentifier();
			if(!(ctypeId.equals(cid.getComponentTypeID()) && cid.getName().equals(cname)))
				continue;

			Version version = cid.getVersion();
			if(getQuery().isMatch(version, null))
			{
				if(bestFit == null)
					bestFit = version;
				else if(version.compareTo(bestFit) > 0)
				{
					logDecision(ResolverDecisionType.VERSION_REJECTED, bestFit,
							NLS.bind(Messages._0_is_higher, version));
					bestFit = version;
				}
			}
		}
		if(bestFit == null)
			return null;
		return new VersionMatch(bestFit, null, -1, null, null);
	}
}
