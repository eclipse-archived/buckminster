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
import org.eclipse.osgi.util.NLS;
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
			throw new SimpleErrorExitException(Messages.too_many_arguments);
		if(len > 0)
			m_feature = unparsed[0];
		if(len > 1)
			m_version = unparsed[1];
	}

	@Override
	protected int run(IProgressMonitor monitor) throws Exception
	{
		if(m_feature == null)
			throw new SimpleErrorExitException(Messages.no_feature_id_provided);

		monitor.beginTask(null, IProgressMonitor.UNKNOWN);

		try
		{
			if(Platform.inDevelopmentMode())
				throw new SimpleErrorExitException(Messages.no_uninstall_in_development_mode);

			VersionedIdentifier vidToFind = new VersionedIdentifier(m_feature, m_version == null
					? "0.0.0" : m_version); //$NON-NLS-1$

			monitor.subTask(NLS.bind(Messages.searching_for_0_, vidToFind));

			IConfiguredSite uninstallSite = null;

			IConfiguredSite[] configuredSites = SiteManager.getLocalSite().getCurrentConfiguration()
					.getConfiguredSites();
			for(int idx = 0; idx < configuredSites.length; ++idx)
			{
				IConfiguredSite configuredSite = configuredSites[idx];
				if(configuredSite.isProductSite() && configuredSite.isUpdatable())
				{
					uninstallSite = configuredSite;
					break;
				}
				MonitorUtils.worked(monitor, 1);
			}

			if(uninstallSite == null)
				throw new SimpleErrorExitException(Messages.site_to_uninstall_from_not_found);

			// search the features
			//
			ArrayList<IFeatureReference> matches = new ArrayList<IFeatureReference>();
			IFeatureReference[] featureRefs = uninstallSite.getFeatureReferences();
			for(int idx = 0; idx < featureRefs.length; ++idx)
			{
				IFeatureReference featureRef = featureRefs[idx];
				VersionedIdentifier vid = featureRef.getVersionedIdentifier();
				if(vid.getIdentifier().equals(m_feature))
				{
					if(m_version == null)
						matches.add(featureRef);
					else
					{
						if(vid.equals(vidToFind))
						{
							matches.add(featureRef);
							break;
						}
					}
				}
				MonitorUtils.worked(monitor, 1);
			}

			if(matches.isEmpty())
				throw new SimpleErrorExitException(Messages.no_suitable_feature_version_found);

			if(matches.size() > 1)
			{
				StringBuffer sb = new StringBuffer();
				for(int idx = 0; idx < matches.size(); ++idx)
					sb.append(' ').append(matches.get(idx).getVersionedIdentifier().getVersion());
				throw new SimpleErrorExitException(NLS.bind(Messages.multiple_versions_found_0, sb.toString()));
			}
			IFeature featureToUninstall = matches.get(0).getFeature(MonitorUtils.subMonitor(monitor, 1000));
			monitor.subTask(NLS.bind(Messages.uninstalling_0_, featureToUninstall.getVersionedIdentifier()));

			if(uninstallSite.isConfigured(featureToUninstall))
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
