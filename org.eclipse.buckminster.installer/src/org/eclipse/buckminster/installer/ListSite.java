/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.installer;

import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.eclipse.buckminster.cmdline.AbstractCommand;
import org.eclipse.buckminster.cmdline.SimpleErrorExitException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.osgi.util.NLS;
import org.eclipse.update.configuration.IConfiguredSite;
import org.eclipse.update.configuration.ILocalSite;
import org.eclipse.update.core.IFeature;
import org.eclipse.update.core.IFeatureReference;
import org.eclipse.update.core.ISite;
import org.eclipse.update.core.SiteManager;
import org.eclipse.update.core.VersionedIdentifier;

/**
 * @author kolwing
 * 
 */
public class ListSite extends AbstractCommand
{
	private URL m_site;

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		int len = unparsed.length;
		if(len > 1)
			throw new SimpleErrorExitException(Messages.too_many_arguments);
		if(len == 1)
			m_site = Install.normalizeToURL(unparsed[0]);
	}

	@Override
	protected int run(IProgressMonitor monitor) throws Exception
	{
		VersionedIdentifierComparator vidc = VersionedIdentifierComparator.ASCENDING;
		SortedMap<VersionedIdentifier, IAdaptable> features = new TreeMap<VersionedIdentifier, IAdaptable>(vidc);

		ILocalSite localSite = null;
		try
		{
			monitor.beginTask(null, IProgressMonitor.UNKNOWN);
			monitor.subTask(NLS.bind(Messages.searching_0_, m_site == null
					? Messages.local_site
					: m_site.toString()));

			ISite[] sites;
			if(m_site == null)
			{
				localSite = SiteManager.getLocalSite();
				IConfiguredSite[] cSites = localSite.getCurrentConfiguration().getConfiguredSites();
				int idx = cSites.length;
				sites = new ISite[idx];
				while(--idx >= 0)
					sites[idx] = cSites[idx].getSite();
				MonitorUtils.worked(monitor, 1000);
			}
			else
			{
				sites = new ISite[] { SiteManager.getSite(m_site, MonitorUtils.subMonitor(monitor, 1000)) };
				MonitorUtils.testCancelStatus(monitor);
			}

			// collect all features
			//
			for(int idx = 0; idx < sites.length; ++idx)
			{
				ISite site = sites[idx];
				IFeatureReference[] featureRefs = site.getFeatureReferences();
				for(int refIdx = 0; refIdx < featureRefs.length; ++refIdx)
				{
					IFeatureReference featureRef = featureRefs[refIdx];
					VersionedIdentifier vi = featureRef.getVersionedIdentifier();
					try
					{
						features.put(vi, featureRef.getFeature(MonitorUtils.subMonitor(monitor, 1000)));
					}
					catch(CoreException e)
					{
						features.put(vi, featureRef);
					}
				}
			}
		}
		finally
		{
			monitor.done();
		}

		System.out.println(Messages.feature_listing_heading);
		Iterator<Map.Entry<VersionedIdentifier, IAdaptable>> itor = features.entrySet().iterator();
		while(itor.hasNext())
		{
			Map.Entry<VersionedIdentifier, IAdaptable> entry = itor.next();
			IAdaptable f = entry.getValue();
			System.out.print("  "); //$NON-NLS-1$
			System.out.print(entry.getKey());
			System.out.print(" ("); //$NON-NLS-1$
			System.out.print((f instanceof IFeature)
					? ((IFeature)f).getLabel()
					: ((IFeatureReference)f).getName());
			System.out.println(")"); //$NON-NLS-1$

			if(f instanceof IFeatureReference)
			{
				System.out.println("    " + Messages.only_reference_found); //$NON-NLS-1$
				continue;
			}

			if(localSite == null)
				continue;

			IStatus brokenStatus = localSite.getFeatureStatus((IFeature)f);
			if(brokenStatus.getCode() == IFeature.STATUS_HAPPY)
				continue;

			if(brokenStatus.isMultiStatus())
			{
				IStatus[] children = ((MultiStatus)brokenStatus).getChildren();
				for(int idx = 0; idx < children.length; ++idx)
					System.out.println("    " + children[idx].getMessage()); //$NON-NLS-1$
			}
			else
				System.out.println("    " + brokenStatus.getMessage()); //$NON-NLS-1$
		}
		return 0;
	}
}
