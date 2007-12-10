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

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.buckminster.cmdline.AbstractCommand;
import org.eclipse.buckminster.cmdline.SimpleErrorExitException;
import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.update.configuration.IConfiguredSite;
import org.eclipse.update.core.IFeature;
import org.eclipse.update.core.IFeatureReference;
import org.eclipse.update.core.ISite;
import org.eclipse.update.core.SiteManager;
import org.eclipse.update.core.VersionedIdentifier;
import org.eclipse.update.operations.OperationsManager;

/**
 * @author kolwing
 */
public class Install extends AbstractCommand
{
	private URL m_site;

	private String m_version;

	private String m_feature;

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		int len = unparsed.length;
		if(len > 3)
			throw new UsageException("Too many arguments");
		if(len > 0)
			m_site = normalizeToURL(unparsed[0]);
		if(len > 1)
			m_feature = unparsed[1];
		if(len > 2)
			m_version = unparsed[2];
	}

	@Override
	protected int run(IProgressMonitor monitor) throws Exception
	{
		if (m_site == null)
			throw new UsageException("No site provided");
		if (m_feature == null)
			throw new UsageException("No feature id provided");

		IFeature featureToInstall = null;
		
		VersionedIdentifier vidToFind = new VersionedIdentifier(m_feature, m_version == null ? "0.0.0" : m_version);

		monitor.beginTask(null, IProgressMonitor.UNKNOWN);
		monitor.subTask("Searching for " + vidToFind + " in " + m_site + "...");

		ISite site = SiteManager.getSite(m_site, MonitorUtils.subMonitor(monitor, 1000));

		// search the features
		//
		IFeatureReference match = null;
		IFeatureReference[] references = site.getFeatureReferences();
		int idx = references.length;
		while(--idx >= 0)
		{
			IFeatureReference featureRef = references[idx];
			VersionedIdentifier vid = featureRef.getVersionedIdentifier();
			if (vid.getIdentifier().equals(m_feature))
			{
				if (m_version == null)
				{
					if (match == null || VersionedIdentifierComparator.compareStatic(vid, match.getVersionedIdentifier()) > 0)
						match = featureRef;
				}
				else if (vid.equals(vidToFind))
				{
					match = featureRef;
					break;
				}
			}
			MonitorUtils.worked(monitor, 1);
		}

		if (match == null)
			throw new SimpleErrorExitException("No suitable feature/version found");
		featureToInstall = match.getFeature(MonitorUtils.subMonitor(monitor, 1000));

		if (Platform.inDevelopmentMode())
			throw new SimpleErrorExitException("Will not install in development mode");

		monitor.subTask("Installing " + featureToInstall.getVersionedIdentifier() + "...");

		IConfiguredSite installSite = null;		
		IConfiguredSite[] configuredSites = SiteManager.getLocalSite().getCurrentConfiguration().getConfiguredSites();
		for(idx = 0; idx < configuredSites.length; ++idx)
		{
			IConfiguredSite configuredSite = configuredSites[idx];
			if (configuredSite.isProductSite() && configuredSite.isUpdatable())
			{
				installSite = configuredSite;
				break;
			}
			MonitorUtils.worked(monitor, 1);
		}

		if (installSite == null)
			throw new SimpleErrorExitException("Could not find a site to install to");

		IStatus brokenStatus = OperationsManager.getValidator().validatePendingInstall(null, featureToInstall);
		if(brokenStatus != null)
			throw new CoreException(brokenStatus);

		installSite.install(featureToInstall, null, MonitorUtils.subMonitor(monitor, 1000));
		return 0;
	}

	static URL normalizeToURL(String surl)
	{
		URL url;
		try
		{
			url = new URL(surl);
		}
		catch(MalformedURLException e)
		{
			try
			{
				url = new File(surl).toURI().toURL();
			}
			catch(MalformedURLException e2)
			{
				throw new IllegalArgumentException(surl + " cannot be converted to a URL");
			}
		}
		return url;
	}
}
