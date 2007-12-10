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

import java.util.ArrayList;

import org.eclipse.buckminster.cmdline.AbstractCommand;
import org.eclipse.buckminster.cmdline.SimpleErrorExitException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.update.configuration.IConfiguredSite;
import org.eclipse.update.core.IFeature;
import org.eclipse.update.core.IFeatureReference;
import org.eclipse.update.core.SiteManager;
import org.eclipse.update.core.VersionedIdentifier;

/**
 * @author kolwing
 * 
 */
public class Uninstall extends AbstractCommand
{
	private String m_version;

	private String m_feature;

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		int len = unparsed.length;
		if(len > 2)
			throw new SimpleErrorExitException("Too many arguments");
		if(len > 0)
			m_feature = unparsed[0];
		if(len > 1)
			m_version = unparsed[1];
	}

	@Override
	protected int run(IProgressMonitor monitor) throws Exception
	{
		if (m_feature == null)
			throw new SimpleErrorExitException("No feature id provided");

		monitor.beginTask(null, IProgressMonitor.UNKNOWN);

		try
		{
			if (Platform.inDevelopmentMode())
				throw new SimpleErrorExitException("Will not uninstall in development mode");

			VersionedIdentifier vidToFind = new VersionedIdentifier(m_feature, m_version == null ? "0.0.0" : m_version);

			monitor.subTask("Searching for " + vidToFind + "...");

			IConfiguredSite uninstallSite = null;

			IConfiguredSite[] configuredSites = SiteManager.getLocalSite().getCurrentConfiguration().getConfiguredSites();
			for(int idx = 0; idx < configuredSites.length; ++idx)
			{
				IConfiguredSite configuredSite = configuredSites[idx];
				if (configuredSite.isProductSite() && configuredSite.isUpdatable())
				{
					uninstallSite = configuredSite;
					break;
				}
				MonitorUtils.worked(monitor, 1);
			}

			if (uninstallSite == null)
				throw new SimpleErrorExitException("Could not find a site to uninstall from");

			// search the features
			//
			ArrayList<IFeatureReference> matches = new ArrayList<IFeatureReference>();
			IFeatureReference[] featureRefs = uninstallSite.getFeatureReferences();
			for(int idx = 0; idx < featureRefs.length; ++idx)
			{
				IFeatureReference featureRef = featureRefs[idx];
				VersionedIdentifier vid = featureRef.getVersionedIdentifier();
				if (vid.getIdentifier().equals(m_feature))
				{
					if (m_version == null)
						matches.add(featureRef);
					else
					{
						if (vid.equals(vidToFind))
						{
							matches.add(featureRef);
							break;
						}
					}
				}
				MonitorUtils.worked(monitor, 1);
			}

			if (matches.isEmpty())
				throw new SimpleErrorExitException("No suitable feature/version found");

			if (matches.size() > 1)
			{
				StringBuffer sb = new StringBuffer("More than one version found:");
				for(int idx = 0; idx < matches.size(); ++idx)
					sb.append(' ').append(matches.get(idx).getVersionedIdentifier().getVersion());
				throw new SimpleErrorExitException(sb.toString());
			}
			IFeature featureToUninstall = matches.get(0).getFeature(MonitorUtils.subMonitor(monitor, 1000));
			monitor.subTask("Uninstalling " + featureToUninstall.getVersionedIdentifier() + "...");

			if (uninstallSite.isConfigured(featureToUninstall))
				uninstallSite.unconfigure(featureToUninstall);
			MonitorUtils.worked(monitor, 1);
			uninstallSite.remove(featureToUninstall, MonitorUtils.subMonitor(monitor, 1000));
		}
		finally
		{
			monitor.done();
		}

		return 0;
	}
}
