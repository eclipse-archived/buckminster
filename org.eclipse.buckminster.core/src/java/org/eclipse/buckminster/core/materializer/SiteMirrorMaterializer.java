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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.reader.SiteFeatureReaderType;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.update.core.ISite;
import org.eclipse.update.core.ISiteFeatureReference;
import org.eclipse.update.core.model.InvalidSiteTypeException;
import org.eclipse.update.internal.mirror.MirrorSite;
import org.eclipse.update.internal.mirror.MirrorSiteFactory;

/**
 * Materializes each component to the local filesystem.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class SiteMirrorMaterializer extends AbstractMaterializer
{
	@Override
	public String getMaterializerRootDir()
	{
		return "siteMirrors";
	}

	@SuppressWarnings("serial")
	private static class FeaturesPerSite extends ArrayList<ISiteFeatureReference>
	{
		private final HashSet<ComponentIdentifier> m_includedIDs = new HashSet<ComponentIdentifier>();

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

		ISiteFeatureReference[] getOptionalFeatureRefs()
		{
			return toArray(new ISiteFeatureReference[size()]);
		}

		void add(ComponentIdentifier cid) throws CoreException
		{
			if(!m_includedIDs.contains(cid))
			{
				ISiteFeatureReference v = SiteFeatureReaderType.getSiteFeatureReference(m_site, cid);

				// Get the site reference from the site. It might not exist since we only see
				// the features that are listed in the site.xml
				// TODO: Improve this to really check that the feature is there.
				//
				if(v != null)
					super.add(v);
				m_includedIDs.add(cid);
			}
		}
	}

	public static final Object MIRROR_SITE_LOCATION_PROPERTY = "mirror.site.location";

	public static final Object MIRROR_SITE_URL_PROPERTY = "mirror.site.url";

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
			mirrorAndExpose(context, context.get(MIRROR_SITE_URL_PROPERTY), sites,
					MonitorUtils.subMonitor(monitor, 50));

			// Not supposed to be further perused
			//
			return Collections.emptyList();
		}
		finally
		{
			monitor.done();
		}
	}

	private static void mirrorAndExpose(RMContext context, String mirrorSiteURL,
			Map<IPath, Map<String, FeaturesPerSite>> sites, IProgressMonitor monitor) throws CoreException
	{
		int count = 0;
		for (IPath path : sites.keySet())
			count += sites.get(path).size();

		monitor.beginTask(null, 100 + count * 100);
		try
		{
			for (IPath path : sites.keySet())
			{
				Collection<FeaturesPerSite> sitesAndFeatures = sites.get(path).values();
				File mirrorSiteFile = path.toFile();

				MirrorSiteFactory factory = new MirrorSiteFactory();
				MirrorSite mirrorSite;
				try
				{
					mirrorSite = (MirrorSite)factory.createSite(mirrorSiteFile);
					MonitorUtils.worked(monitor, 100);
				}
				catch(InvalidSiteTypeException e)
				{
					throw BuckminsterException.wrap(e);
				}
				mirrorSite.setIgnoreNonPresentPlugins(true);
				for(FeaturesPerSite fps : sitesAndFeatures)
				{
					try
					{
						mirrorSite.mirrorAndExpose(fps.getSite(), fps.getFeatureRefs(), null, mirrorSiteURL);
						MonitorUtils.worked(monitor, 100);
					}
					catch(CoreException e)
					{
						if(!context.isContinueOnError())
							throw e;
						context.addException(e.getStatus());
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
		for(Resolution resolution : resolutions)
		{
			try
			{
				SiteFeatureReaderType.checkComponentType(resolution.getProvider());
			}
			catch(CoreException e)
			{
				// Ignore this node, i.e. don't consider it to be a site.feature
				// We are interested of its children anyway.
				//
				MonitorUtils.worked(monitor, 100);
				continue;
			}

			IPath installLocation = context.getArtifactLocation(resolution);
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
			fps.add(resolution.getComponentIdentifier());
		}
	}
}
