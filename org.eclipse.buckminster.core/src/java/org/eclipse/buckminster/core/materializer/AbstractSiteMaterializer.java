/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.materializer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.SiteFeatureReaderType;
import org.eclipse.buckminster.core.site.ISiteFeatureConverter;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.update.core.ISite;
import org.eclipse.update.core.ISiteFeatureReference;

/**
 * Materializes each component to the local filesystem.
 * 
 * @author Thomas Hallgren
 */
abstract class AbstractSiteMaterializer extends AbstractMaterializer
{
	@SuppressWarnings("serial")
	private static class FeaturesPerSite extends ArrayList<ISiteFeatureReference>
	{
		private final HashSet<Resolution> m_includedRes = new HashSet<Resolution>();

		private final ISite m_site;

		FeaturesPerSite(ISite site)
		{
			m_site = site;
		}

		ISite getSite()
		{
			return m_site;
		}

		ISiteFeatureReference[] getFeatureRefs()
		{
			return toArray(new ISiteFeatureReference[size()]);
		}

		Resolution[] getResolutions()
		{
			return m_includedRes.toArray(new Resolution[m_includedRes.size()]);
		}

		void add(Resolution res) throws CoreException
		{
			if(!m_includedRes.contains(res))
			{
				ISiteFeatureReference v = SiteFeatureReaderType.getSiteFeatureReference(m_site, res.getComponentIdentifier());

				// Get the site reference from the site. It might not exist since we only see
				// the features that are listed in the site.xml
				// TODO: Improve this to really check that the feature is there.
				//
				if(v != null)
					super.add(v);
				m_includedRes.add(res);
			}
		}
	}

	@Override
	public boolean canWorkInParallel()
	{
		return false;
	}

	public List<Materialization> materialize(List<Resolution> resolutions, MaterializationContext context,
			IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 100);
		try
		{
			Map<IPath, Map<String, FeaturesPerSite>> sites = new HashMap<IPath, Map<String, FeaturesPerSite>>();

			IProgressMonitor siteCollectorMon = MonitorUtils.subMonitor(monitor, 50);
			siteCollectorMon.beginTask(null, resolutions.size() * 100);
			try
			{
				collectSites(context, resolutions, sites, siteCollectorMon);
			}
			finally
			{
				siteCollectorMon.done();
			}
			installFeatures(context, sites, MonitorUtils.subMonitor(monitor, 50));

			// Not supposed to be further perused
			//
			return Collections.emptyList();
		}
		finally
		{
			monitor.done();
		}
	}

	protected abstract ISite getDestinationSite(MaterializationContext context, IPath destination, IProgressMonitor monitor) throws CoreException;

	protected abstract void installFeatures(MaterializationContext context, ISite destinationSite, ISite fromSite, ISiteFeatureReference[] features, IProgressMonitor monitor) throws CoreException;

	private void installFeatures(final MaterializationContext context, Map<IPath, Map<String, FeaturesPerSite>> sites, IProgressMonitor monitor) throws CoreException
	{
		int count = 0;
		Set<IPath> destinations = sites.keySet();
		for (IPath path : destinations)
			count += sites.get(path).size();

		monitor.beginTask(null, destinations.size() * 100 + count * 100);
		try
		{
			for (IPath path : destinations)
			{
				ISite mirrorSite = getDestinationSite(context, path, MonitorUtils.subMonitor(monitor, 100));
				for(FeaturesPerSite fps : sites.get(path).values())
				{
					final Resolution first = fps.getResolutions()[0];
					ILogListener listener = new ILogListener()
					{
						public void logging(IStatus status, String plugin)
						{
							switch(status.getSeverity())
							{
							case IStatus.WARNING:
							case IStatus.ERROR:
								Platform.removeLogListener(this);
								context.addException(first.getRequest(), status);
								Platform.addLogListener(this);
							}
						}	
					};
					Platform.addLogListener(listener);

					try
					{
						context.addException(first.getRequest(), new Status(IStatus.INFO, CorePlugin.getID(), "Start mirroring"));
						installFeatures(context, mirrorSite, fps.getSite(), fps.getFeatureRefs(), MonitorUtils.subMonitor(monitor, 100));
					}
					catch(CoreException e)
					{
						if(!context.isContinueOnError())
							throw e;
						context.addException(first.getRequest(), e.getStatus());
					}
					finally
					{
						context.addException(first.getRequest(), new Status(IStatus.INFO, CorePlugin.getID(), "End mirroring"));
						Platform.removeLogListener(listener);
					}
				}
			}
		}
		finally
		{
			monitor.done();
		}
	}

	private static void collectSites(MaterializationContext context, List<Resolution> resolutions,
			Map<IPath, Map<String, FeaturesPerSite>> sites, IProgressMonitor monitor) throws CoreException
	{
		ArrayList<Resolution> siteFeatures = new ArrayList<Resolution>(resolutions.size());
		ArrayList<Resolution> plugins = new ArrayList<Resolution>();
		ArrayList<Resolution> features = new ArrayList<Resolution>();

		for(Resolution resolution : resolutions)
		{
			if(IComponentType.ECLIPSE_SITE_FEATURE.equals(resolution.getComponentTypeId()))
				siteFeatures.add(resolution);

			// This component is not part of the normal update site manager protocol. We
			// can still install it but we need to get it in another form.
			//
			if(IComponentType.OSGI_BUNDLE.equals(resolution.getComponentTypeId()))
				plugins.add(resolution);
			else if(IComponentType.ECLIPSE_FEATURE.equals(resolution.getComponentTypeId()))
				features.add(resolution);
		}

		if(!(plugins.isEmpty() && features.isEmpty()))
		{
			IReaderType pdeReaderType;
			try
			{
				pdeReaderType = CorePlugin.getDefault().getReaderType(IReaderType.ECLIPSE_PLATFORM);
			}
			catch(CoreException e)
			{
				throw BuckminsterException.fromMessage("Unable to install plugins and features that do not stem from an update site since PDE is missing");
			}
			File tempSite = FileUtils.createTempFolder("bmsite", "tmp");
			siteFeatures.addAll(((ISiteFeatureConverter)pdeReaderType).convertToSiteFeatures(context, tempSite, features, plugins));
		}

		for(Resolution resolution : siteFeatures)
		{
			IPath installLocation = context.getInstallLocation(resolution);
			String siteURL = resolution.getRepository();
			Map<String, FeaturesPerSite> sitesInLocation = sites.get(installLocation);
			if(sitesInLocation == null)
			{
				sitesInLocation = new HashMap<String, FeaturesPerSite>();
				sites.put(installLocation, sitesInLocation);
			}
			FeaturesPerSite fps = sitesInLocation.get(siteURL);
			if(fps == null)
			{
				fps = new FeaturesPerSite(SiteFeatureReaderType.getSite(siteURL, MonitorUtils.subMonitor(monitor, 100)));
				sitesInLocation.put(siteURL, fps);
			}
			fps.add(resolution);
		}
	}
}
