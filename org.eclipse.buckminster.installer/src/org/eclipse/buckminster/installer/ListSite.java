/*****************************************************************************
 * Copyright (c) 2007-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.installer;

import java.net.URI;
import java.util.Arrays;

import org.eclipse.buckminster.cmdline.AbstractCommand;
import org.eclipse.buckminster.cmdline.Headless;
import org.eclipse.buckminster.cmdline.SimpleErrorExitException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.equinox.internal.p2.console.ProvisioningHelper;
import org.eclipse.equinox.internal.provisional.p2.engine.IProfile;
import org.eclipse.equinox.internal.provisional.p2.engine.IProfileRegistry;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.LatestIUVersionQuery;
import org.eclipse.equinox.internal.provisional.p2.query.Collector;
import org.eclipse.equinox.internal.provisional.p2.query.CompositeQuery;
import org.eclipse.equinox.internal.provisional.p2.query.Query;

@SuppressWarnings("restriction")
public class ListSite extends AbstractCommand
{
	static IInstallableUnit[] getRootIUs(URI site, IProgressMonitor monitor) throws SimpleErrorExitException
	{
		Collector roots = new Collector();

		if(site == null)
		{
			IProfile runningInstanceProfile = ProvisioningHelper.getProfile(IProfileRegistry.SELF);

			if(runningInstanceProfile != null)
				roots = runningInstanceProfile.query(new CompositeQuery(new Query[] { new FeatureQuery(),
						new LatestIUVersionQuery() }), roots, monitor);
		}
		else
			roots = ProvisioningHelper.getInstallableUnits(site, new CompositeQuery(new Query[] { new FeatureQuery(),
					new LatestIUVersionQuery() }), roots, monitor);

		return (IInstallableUnit[])roots.toArray(IInstallableUnit.class);
	}

	private URI m_site;

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		int len = unparsed.length;
		if(len > 1)
			throw new SimpleErrorExitException(Messages.too_many_arguments);
		if(len == 1)
			m_site = Install.normalizeToURI(unparsed[0]);
	}

	@Override
	protected int run(IProgressMonitor monitor) throws Exception
	{
		monitor.beginTask(null, IProgressMonitor.UNKNOWN);
		System.out.println(Messages.feature_listing_heading);

		IInstallableUnit[] roots = getRootIUs(m_site, monitor);
		Arrays.sort(roots);
		for(IInstallableUnit iu : roots)
		{
			System.out.print("  "); //$NON-NLS-1$

			String id = iu.getId();
			if(id.endsWith(FeatureQuery.FEATURE_GROUP))
				id = id.substring(0, id.length() - 14);
			System.out.print(id);
			String label = iu.getProperty(IInstallableUnit.PROP_NAME);
			if(label != null)
			{
				System.out.print(" ("); //$NON-NLS-1$
				System.out.print(label);
				System.out.println(")"); //$NON-NLS-1$
			}
		}
		return Headless.EXIT_OK;
	}
}
