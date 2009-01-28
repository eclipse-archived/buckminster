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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.UUID;

import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ResolverDecisionType;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.AbstractVersionFinder;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.pde.PDEPlugin;
import org.eclipse.buckminster.runtime.BuckminsterPreferences;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.update.core.IFeature;
import org.eclipse.update.core.IFeatureReference;
import org.eclipse.update.core.IIncludedFeatureReference;
import org.eclipse.update.core.IPluginEntry;
import org.eclipse.update.core.ISite;
import org.eclipse.update.core.ISiteFeatureReference;
import org.eclipse.update.core.SiteManager;
import org.eclipse.update.core.VersionedIdentifier;
import org.eclipse.update.internal.core.FeatureDownloadException;

@SuppressWarnings("restriction")
public class EclipseImportFinder extends AbstractVersionFinder
{
	private static ISiteFeatureReference[] getSiteFeatureReferences(URL location, IProgressMonitor monitor)
			throws CoreException
	{
		ISite site;
		synchronized(SiteManager.class)
		{
			site = SiteManager.getSite(location, true, monitor);
			if(site == null)
				throw new OperationCanceledException();

			return site.getFeatureReferences();
		}
	}

	private final EclipseImportReaderType m_readerType;

	private final EclipseImportBase m_base;

	private final NodeQuery m_query;

	private static final UUID CACHE_KEY_PLUGIN_ENTRIES_CACHE = UUID.randomUUID();

	@SuppressWarnings("unchecked")
	private static Map<URL, IPluginEntry[]> getPluginEntriesCache(Map<UUID, Object> ctxUserCache)
	{
		synchronized(ctxUserCache)
		{
			Map<URL, IPluginEntry[]> cache = (Map<URL, IPluginEntry[]>)ctxUserCache.get(CACHE_KEY_PLUGIN_ENTRIES_CACHE);
			if(cache == null)
			{
				cache = Collections.synchronizedMap(new HashMap<URL, IPluginEntry[]>());
				ctxUserCache.put(CACHE_KEY_PLUGIN_ENTRIES_CACHE, cache);
			}
			return cache;
		}
	}

	private final int m_connectionRetryCount;

	private final long m_connectionRetryDelay;

	public EclipseImportFinder(EclipseImportReaderType readerType, Provider provider, IComponentType ctype,
			NodeQuery query) throws CoreException
	{
		super(provider, ctype, query);

		m_connectionRetryCount = BuckminsterPreferences.getConnectionRetryCount();
		m_connectionRetryDelay = BuckminsterPreferences.getConnectionRetryDelay() * 1000L;

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

	private void addFeaturePluginEntries(HashMap<VersionedIdentifier, IPluginEntry> entries,
			HashSet<VersionedIdentifier> seenFeatures, IFeature feature, IProgressMonitor monitor) throws CoreException
	{
		for(IPluginEntry entry : feature.getRawPluginEntries())
			entries.put(entry.getVersionedIdentifier(), entry);

		IIncludedFeatureReference[] includedFeatures = feature.getIncludedFeatureReferences();
		if(includedFeatures.length == 0)
		{
			MonitorUtils.complete(monitor);
			return;
		}

		monitor.beginTask(null, includedFeatures.length * 100);
		for(IIncludedFeatureReference ref : includedFeatures)
		{
			VersionedIdentifier vid = ref.getVersionedIdentifier();
			if(seenFeatures.contains(vid))
				MonitorUtils.worked(monitor, 100);
			else
			{
				seenFeatures.add(vid);
				IFeature includedFeature = obtainFeature(ref, MonitorUtils.subMonitor(monitor, 50));
				if(feature != null)
					addFeaturePluginEntries(entries, seenFeatures, includedFeature, MonitorUtils
							.subMonitor(monitor, 50));
			}
		}
		monitor.done();
	}

	private VersionMatch getBestFeatureVersion(IProgressMonitor monitor) throws CoreException
	{
		return m_base.isLocal()
				? getBestLocalFeatureVersion(monitor)
				: getBestRemoteFeatureVersion(monitor);
	}

	private VersionMatch getBestLocalFeatureVersion(IProgressMonitor monitor) throws CoreException
	{
		IVersion bestFit = null;
		for(IFeatureModel model : m_base.getFeatureModels(m_readerType, monitor))
		{
			IVersion version = VersionFactory.OSGiType.fromString(model.getFeature().getVersion());
			if(getQuery().isMatch(version, null) && (bestFit == null || version.compareTo(bestFit) > 0))
				bestFit = version;
		}
		if(bestFit == null)
			return null;
		return new VersionMatch(bestFit, null, -1, null, null);
	}

	private VersionMatch getBestLocalPluginVersion(IProgressMonitor monitor) throws CoreException
	{
		IVersion bestFit = null;
		for(IPluginModelBase model : m_base.getPluginModels(m_readerType, monitor))
		{
			IVersion version = VersionFactory.OSGiType.fromString(model.getBundleDescription().getVersion().toString());
			if(getQuery().isMatch(version, null))
			{
				if(bestFit == null)
					bestFit = version;
				else if(version.compareTo(bestFit) > 0)
				{
					logDecision(ResolverDecisionType.VERSION_REJECTED, bestFit, NLS
							.bind(Messages._0_is_higher, version));
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

	@SuppressWarnings("deprecation")
	private VersionMatch getBestRemoteFeatureVersion(IProgressMonitor monitor) throws CoreException
	{
		IVersion bestFit = null;
		monitor.beginTask(null, 100);
		monitor.subTask(Messages.fetching_remote_feature_references);
		try
		{
			for(ISiteFeatureReference model : getSiteFeatureReferences(m_base.getRemoteLocation(), monitor))
			{
				if(!model.getVersionedIdentifier().getIdentifier().equals(m_base.getComponentName()))
					continue;

				IFeature feature = model.getFeature(MonitorUtils.subMonitor(monitor, 5));
				IVersion version = VersionFactory.OSGiType.fromString(feature.getVersionedIdentifier().getVersion()
						.toString());
				if(getQuery().isMatch(version, null))
				{
					if(bestFit == null)
						bestFit = version;
					else if(version.compareTo(bestFit) > 0)
					{
						logDecision(ResolverDecisionType.VERSION_REJECTED, bestFit, NLS.bind(Messages._0_is_higher,
								version));
						bestFit = version;
					}
				}
			}
			if(bestFit == null)
				return null;
			return new VersionMatch(bestFit, null, -1, null, null);
		}
		finally
		{
			monitor.done();
		}
	}

	@SuppressWarnings("deprecation")
	private VersionMatch getBestRemotePluginVersion(IProgressMonitor monitor) throws CoreException
	{
		IVersion bestFit = null;

		for(IPluginEntry model : getSitePluginEntries(m_base.getRemoteLocation(), getConnectContext(), m_query, monitor))
		{
			if(!model.getVersionedIdentifier().getIdentifier().equals(m_base.getComponentName()))
				continue;

			IVersion version = VersionFactory.OSGiType.fromString(model.getVersionedIdentifier().getVersion()
					.toString());
			if(getQuery().isMatch(version, null))
			{
				if(bestFit == null)
					bestFit = version;
				else if(version.compareTo(bestFit) > 0)
				{
					logDecision(ResolverDecisionType.VERSION_REJECTED, bestFit, NLS
							.bind(Messages._0_is_higher, version));
					bestFit = version;
				}
			}
		}
		if(bestFit == null)
			return null;
		return new VersionMatch(bestFit, null, -1, null, null);
	}

	private IPluginEntry[] getSitePluginEntries(URL location, IConnectContext cctx, NodeQuery query,
			IProgressMonitor monitor) throws CoreException
	{
		synchronized(location.toString().intern())
		{
			Map<URL, IPluginEntry[]> cache = getPluginEntriesCache(query.getContext().getUserCache());
			IPluginEntry[] entries = cache.get(location);
			if(entries != null)
				return entries;

			if(location.getPath().endsWith(".map")) //$NON-NLS-1$
			{
				MonitorUtils.complete(monitor);
				entries = EclipseImportReaderType.getMapPluginEntries(location, cctx);
				cache.put(location, entries);
				return entries;
			}

			ISite site;
			MonitorUtils.begin(monitor, 100);
			synchronized(SiteManager.class)
			{
				site = SiteManager.getSite(location, true, MonitorUtils.subMonitor(monitor, 50));
				if(site == null)
					throw new OperationCanceledException();

				try
				{
					entries = site.getPluginEntries();
					cache.put(location, entries);
					MonitorUtils.worked(monitor, 50);
					return entries;
				}
				catch(UnsupportedOperationException uoe)
				{
					// Damn it! We need to use the slow version.
					//
					HashMap<VersionedIdentifier, IPluginEntry> entryCache = new HashMap<VersionedIdentifier, IPluginEntry>();
					HashSet<VersionedIdentifier> seenFeatures = new HashSet<VersionedIdentifier>();
					IFeatureReference[] refs = getSiteFeatureReferences(location, MonitorUtils.subMonitor(monitor, 10));
					IProgressMonitor itemsMonitor = MonitorUtils.subMonitor(monitor, 40);
					itemsMonitor.beginTask(null, refs.length * 100);

					for(IFeatureReference ref : refs)
					{
						// The getFeature() call is not thread-safe. It uses static variables without
						// synchronization
						//
						VersionedIdentifier vid = ref.getVersionedIdentifier();
						if(seenFeatures.add(vid))
						{
							IFeature feature = obtainFeature(ref, MonitorUtils.subMonitor(itemsMonitor, 50));
							if(feature != null)
								addFeaturePluginEntries(entryCache, seenFeatures, feature, MonitorUtils.subMonitor(
										itemsMonitor, 50));
						}
					}
					entries = entryCache.values().toArray(new IPluginEntry[entryCache.size()]);
					cache.put(location, entries);
					return entries;
				}
				finally
				{
					MonitorUtils.done(monitor);
				}
			}
		}
	}

	private IFeature obtainFeature(IFeatureReference ref, IProgressMonitor monitor)
	{
		Exception e;
		Logger logger = PDEPlugin.getLogger();
		for(int retryCount = 0;;)
		{
			try
			{
				logger.debug("Downloading %s", ref.getURL()); //$NON-NLS-1$
				return ref.getFeature(monitor);
			}
			catch(FeatureDownloadException ex)
			{
				if(retryCount < m_connectionRetryCount)
				{
					Throwable t = ex.getStatus().getException();
					if(t instanceof IOException)
					{
						if(!(t instanceof FileNotFoundException))
						{
							++retryCount;
							try
							{
								Thread.sleep(m_connectionRetryDelay);
								logger.warning(NLS.bind(Messages.connection_to_0_failed_on_1_retry_attempt_2_started,
										new Object[] { ref.getURL(), t.getMessage(), new Integer(retryCount) }));
								continue;
							}
							catch(InterruptedException e1)
							{
							}
						}
					}
				}
				e = ex;
			}
			catch(Exception ex)
			{
				e = ex;
			}
			break;
		}
		logger.warning(e, e.getMessage());
		return null;
	}
}
