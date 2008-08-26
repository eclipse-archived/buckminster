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
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ResolverDecisionType;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.AbstractVersionFinder;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.update.core.IFeature;
import org.eclipse.update.core.IPluginEntry;
import org.eclipse.update.core.ISiteFeatureReference;

@SuppressWarnings("restriction")
public class EclipseImportFinder extends AbstractVersionFinder
{
	private final EclipseImportReaderType m_readerType;
	private final EclipseImportBase m_base;
	private final NodeQuery m_query;

	public EclipseImportFinder(EclipseImportReaderType readerType, Provider provider, IComponentType ctype, NodeQuery query)
	throws CoreException
	{
		super(provider, ctype, query);
		m_base = EclipseImportBase.obtain(query, provider.getURI(query.getProperties()));
		m_readerType = readerType;
		m_query = query;
	}

	public VersionMatch getBestVersion(IProgressMonitor monitor)
	throws CoreException
	{
		return m_base.isFeature()
			? getBestFeatureVersion(monitor)
			: getBestPluginVersion(monitor);
	}

	private VersionMatch getBestFeatureVersion(IProgressMonitor monitor)
	throws CoreException
	{
		return m_base.isLocal()
			? getBestLocalFeatureVersion(monitor)
			: getBestRemoteFeatureVersion(monitor);
	}

	private VersionMatch getBestLocalFeatureVersion(IProgressMonitor monitor)
	throws CoreException
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

	private VersionMatch getBestLocalPluginVersion(IProgressMonitor monitor)
	throws CoreException
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
					logDecision(ResolverDecisionType.VERSION_REJECTED, bestFit, String.format("%s is higher", version));
					bestFit = version;					
				}
			}
		}
		if(bestFit == null)
			return null;
		return new VersionMatch(bestFit, null, -1, null, null);
	}

	private VersionMatch getBestPluginVersion(IProgressMonitor monitor)
	throws CoreException
	{
		return m_base.isLocal()
			? getBestLocalPluginVersion(monitor)
			: getBestRemotePluginVersion(monitor);
	}

	@SuppressWarnings("deprecation")
	private VersionMatch getBestRemoteFeatureVersion(IProgressMonitor monitor)
	throws CoreException
	{
		IVersion bestFit = null;
		monitor.beginTask(null, 100);
		monitor.subTask("Fetching remote feature references");
		try
		{
			for(ISiteFeatureReference model : m_base.getFeatureReferences(m_readerType, MonitorUtils.subMonitor(monitor, 80)))
			{
				IFeature feature = model.getFeature(MonitorUtils.subMonitor(monitor, 5));
				IVersion version = VersionFactory.OSGiType.fromString(feature.getVersionedIdentifier().getVersion().toString());
				if(getQuery().isMatch(version, null))
				{
					if(bestFit == null)
						bestFit = version;
					else if(version.compareTo(bestFit) > 0)
					{
						logDecision(ResolverDecisionType.VERSION_REJECTED, bestFit, String.format("%s is higher", version));
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
		for(IPluginEntry model : m_base.getPluginEntries(m_readerType, m_query, monitor))
		{
			IVersion version = VersionFactory.OSGiType.fromString(model.getVersionedIdentifier().getVersion().toString());
			if(getQuery().isMatch(version, null))
			{
				if(bestFit == null)
					bestFit = version;
				else if(version.compareTo(bestFit) > 0)
				{
					logDecision(ResolverDecisionType.VERSION_REJECTED, bestFit, String.format("%s is higher", version));
					bestFit = version;					
				}
			}
		}
		if(bestFit == null)
			return null;
		return new VersionMatch(bestFit, null, -1, null, null);
	}
}
