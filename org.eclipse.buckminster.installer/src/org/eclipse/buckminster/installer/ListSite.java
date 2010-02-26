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
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.equinox.internal.p2.metadata.query.LatestIUVersionQuery;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.core.IProvisioningAgentProvider;
import org.eclipse.equinox.p2.engine.IProfile;
import org.eclipse.equinox.p2.engine.IProfileRegistry;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.query.Collector;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.PipedQuery;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;

@SuppressWarnings("restriction")
public class ListSite extends AbstractCommand {
	static IInstallableUnit[] getRootIUs(URI site, IProgressMonitor monitor) throws CoreException {
		Buckminster bucky = Buckminster.getDefault();
		IProvisioningAgentProvider agentProvider = bucky.getService(IProvisioningAgentProvider.class);
		IProvisioningAgent agent = agentProvider.createAgent(null);
		SubMonitor subMon = SubMonitor.convert(monitor, 10);
		try {
			IQueryResult<IInstallableUnit> roots;
			if (site == null) {
				IProfileRegistry registry = (IProfileRegistry) agent.getService(IProfileRegistry.SERVICE_NAME);
				IProfile runningInstanceProfile = registry.getProfile(IProfileRegistry.SELF);

				if (runningInstanceProfile != null)
					roots = runningInstanceProfile.query(PipedQuery.createPipe(new FeatureQuery(), new LatestIUVersionQuery<IInstallableUnit>()),
							subMon.newChild(10));
				else
					roots = Collector.emptyCollector();
			} else {
				IMetadataRepositoryManager repoManager = (IMetadataRepositoryManager) agent.getService(IMetadataRepositoryManager.SERVICE_NAME);
				roots = repoManager.loadRepository(site, subMon.newChild(8)).query(
						PipedQuery.createPipe(new FeatureQuery(), new LatestIUVersionQuery<IInstallableUnit>()), subMon.newChild(2));
			}
			return roots.toArray(IInstallableUnit.class);
		} finally {
			agent.stop();
			bucky.ungetService(agentProvider);
			subMon.done();
		}
	}

	private URI site;

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception {
		int len = unparsed.length;
		if (len > 1)
			throw new SimpleErrorExitException(Messages.too_many_arguments);
		if (len == 1)
			site = Install.normalizeToURI(unparsed[0]);
	}

	@Override
	protected int run(IProgressMonitor monitor) throws Exception {
		monitor.beginTask(null, IProgressMonitor.UNKNOWN);
		System.out.println(Messages.feature_listing_heading);

		IInstallableUnit[] roots = getRootIUs(site, monitor);
		Arrays.sort(roots);
		for (IInstallableUnit iu : roots) {
			System.out.print("  "); //$NON-NLS-1$

			String id = iu.getId();
			if (id.endsWith(FeatureQuery.FEATURE_GROUP))
				id = id.substring(0, id.length() - 14);
			System.out.print(id);
			String label = iu.getProperty(IInstallableUnit.PROP_NAME);
			if (label != null) {
				System.out.print(" ("); //$NON-NLS-1$
				System.out.print(label);
				System.out.println(")"); //$NON-NLS-1$
			}
		}
		return Headless.EXIT_OK;
	}
}
