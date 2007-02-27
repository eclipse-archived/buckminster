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

import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.IVersionQuery;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelectorFactory;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.update.core.IFeature;
import org.eclipse.update.core.IPluginEntry;
import org.eclipse.update.core.ISiteFeatureReference;

@SuppressWarnings("restriction")
public class EclipseImportFinder implements IVersionFinder
{
	private final EclipseImportReaderType m_readerType;
	private final EclipseImportBase m_base;

	public EclipseImportFinder(EclipseImportReaderType readerType, String repositoryURI, ComponentRequest request)
	throws CoreException
	{
		m_base = EclipseImportBase.obtain(repositoryURI, request);
		m_readerType = readerType;
	}

	public void close()
	{
	}

	public VersionMatch getBestVersion(IVersionQuery query, IProgressMonitor monitor)
	throws CoreException
	{
		return m_base.isFeature()
			? getBestFeatureVersion(query, monitor)
			: getBestPluginVersion(query, monitor);
	}

	public VersionMatch getDefaultVersion(IProgressMonitor monitor)
	throws CoreException
	{
		IVersionDesignator designator = VersionFactory.createDesignator(VersionFactory.OSGiType, "0.0.0");
		IVersionQuery query = VersionSelectorFactory.createQuery(null, designator);
		return getBestVersion(query, monitor);
	}

	private VersionMatch getBestFeatureVersion(IVersionQuery query, IProgressMonitor monitor)
	throws CoreException
	{
		return m_base.isLocal()
			? getBestLocalFeatureVersion(query, monitor)
			: getBestRemoteFeatureVersion(query, monitor);
	}

	private VersionMatch getBestLocalFeatureVersion(IVersionQuery query, IProgressMonitor monitor)
	throws CoreException
	{
		IVersion bestFit = null;
		for(IFeatureModel model : m_base.getFeatureModels(m_readerType, monitor))
		{
			IVersion version = VersionFactory.OSGiType.fromString(model.getFeature().getVersion());
			if(query.matches(version) && (bestFit == null || version.compareTo(bestFit) > 0))
				bestFit = version;
		}
		if(bestFit == null)
			return null;
		return new VersionMatch(bestFit, VersionSelectorFactory.tag(bestFit.toString()));
	}

	private VersionMatch getBestLocalPluginVersion(IVersionQuery query, IProgressMonitor monitor)
	throws CoreException
	{
		IVersion bestFit = null;
		for(IPluginModelBase model : m_base.getPluginModels(m_readerType, monitor))
		{
			IVersion version = VersionFactory.OSGiType.fromString(model.getBundleDescription().getVersion().toString());
			if(query.matches(version) && (bestFit == null || version.compareTo(bestFit) > 0))
				bestFit = version;
		}
		if(bestFit == null)
			return null;
		return new VersionMatch(bestFit, VersionSelectorFactory.tag(bestFit.toString()));
	}

	private VersionMatch getBestPluginVersion(IVersionQuery query, IProgressMonitor monitor)
	throws CoreException
	{
		return m_base.isLocal()
			? getBestLocalPluginVersion(query, monitor)
			: getBestRemotePluginVersion(query, monitor);
	}

	@SuppressWarnings("deprecation")
	private VersionMatch getBestRemoteFeatureVersion(IVersionQuery query, IProgressMonitor monitor)
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
				if(query.matches(version) && (bestFit == null || version.compareTo(bestFit) > 0))
					bestFit = version;
			}
			if(bestFit == null)
				return null;
			return new VersionMatch(bestFit, VersionSelectorFactory.tag(bestFit.toString()));
		}
		finally
		{
			monitor.done();
		}
	}

	@SuppressWarnings("deprecation")
	private VersionMatch getBestRemotePluginVersion(IVersionQuery query, IProgressMonitor monitor) throws CoreException
	{
		IVersion bestFit = null;
		for(IPluginEntry model : m_base.getPluginEntries(m_readerType, monitor))
		{
			IVersion version = VersionFactory.OSGiType.fromString(model.getVersionedIdentifier().getVersion().toString());
			if(query.matches(version) && (bestFit == null || version.compareTo(bestFit) > 0))
				bestFit = version;
		}
		if(bestFit == null)
			return null;
		return new VersionMatch(bestFit, VersionSelectorFactory.tag(bestFit.toString()));
	}
}
