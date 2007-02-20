/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.materializer;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.reader.SiteFeatureReaderType;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
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

	public List<Materialization> materialize(BillOfMaterials bom, Set<Resolution> excludes, RMContext context,
			IProgressMonitor monitor) throws CoreException
	{
		// Obtain the mirror site URL.
		//
		String mirrorSiteLocation = context.get(MIRROR_SITE_LOCATION_PROPERTY);
		if(mirrorSiteLocation == null)
			throw BuckminsterException.fromMessage("Site mirror materializer is missing the required property "
					+ MIRROR_SITE_LOCATION_PROPERTY);

		URL url;
		try
		{
			url = URLUtils.normalizeToURL(mirrorSiteLocation);
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.fromMessage("Property " + MIRROR_SITE_LOCATION_PROPERTY
					+ " cannot be coerced to a URL", e);
		}
		File mirrorSiteFile = FileUtils.getFile(url);
		if(mirrorSiteFile == null)
			throw BuckminsterException.fromMessage("Property " + MIRROR_SITE_LOCATION_PROPERTY
					+ " does not denote a local resource");

		monitor.beginTask(null, 100);
		try
		{
			Set<Resolution> notThese = new HashSet<Resolution>();
			if(excludes != null)
				notThese.addAll(excludes);
			Map<String, FeaturesPerSite> sites = new HashMap<String, FeaturesPerSite>();

			IProgressMonitor siteCollectorMon = MonitorUtils.subMonitor(monitor, 50);
			siteCollectorMon.beginTask(null, bom.uniqueNodeCount() * 100);
			try
			{
				collectSites(bom, notThese, context, sites, siteCollectorMon);
			}
			finally
			{
				siteCollectorMon.done();
			}
			mirrorAndExpose(context, mirrorSiteFile, context.get(MIRROR_SITE_URL_PROPERTY), sites.values(),
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

	private static void mirrorAndExpose(RMContext context, File mirrorSiteFile, String mirrorSiteURL,
			Collection<FeaturesPerSite> sitesAndFeatures, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 100 + sitesAndFeatures.size() * 100);
		try
		{
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
					context.addResolveException(e.getStatus());
				}
			}
		}
		finally
		{
			monitor.done();
		}
	}

	private static void collectSites(DepNode node, Set<Resolution> notThese, RMContext context,
			Map<String, FeaturesPerSite> sites, IProgressMonitor monitor) throws CoreException
	{
		Resolution resolution = node.getResolution();
		if(resolution == null || notThese.contains(resolution))
			return;

		notThese.add(resolution);
		for(DepNode child : node.getChildren())
			collectSites(child, notThese, context, sites, monitor);

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
			return;
		}

		String siteURL = resolution.getRepository(context);
		FeaturesPerSite fps = sites.get(siteURL);
		if(fps == null)
		{
			fps = new FeaturesPerSite(SiteFeatureReaderType.getSite(siteURL, MonitorUtils.subMonitor(monitor, 100)));
			sites.put(siteURL, fps);
		}
		fps.add(resolution.getComponentIdentifier());
	}
}
