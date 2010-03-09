/*****************************************************************************
 * Copyright (c) 2007-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.installer;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Iterator;

import org.eclipse.buckminster.cmdline.AbstractCommand;
import org.eclipse.buckminster.cmdline.Headless;
import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.equinox.internal.provisional.p2.director.ProfileChangeRequest;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.core.IProvisioningAgentProvider;
import org.eclipse.equinox.p2.engine.IEngine;
import org.eclipse.equinox.p2.engine.IProfile;
import org.eclipse.equinox.p2.engine.IProfileRegistry;
import org.eclipse.equinox.p2.engine.IProvisioningPlan;
import org.eclipse.equinox.p2.engine.ProvisioningContext;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.planner.IPlanner;
import org.eclipse.equinox.p2.query.IQuery;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.IQueryable;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;
import org.eclipse.osgi.util.NLS;

@SuppressWarnings("restriction")
public class Install extends AbstractCommand {
	static ProvisioningContext createContext(IProvisioningAgent agent, URI site) {
		URI[] repoLocations = new URI[] { site };
		ProvisioningContext context = new ProvisioningContext(agent);
		context.setArtifactRepositories(repoLocations);
		return context;
	}

	static IQueryResult<IInstallableUnit> getRootIUs(IProvisioningAgent agent, URI site, IProfile profile, String iuName, Version version,
			IProgressMonitor monitor) throws CoreException {
		if (!iuName.endsWith(".feature.group")) //$NON-NLS-1$
			iuName = iuName + ".feature.group"; //$NON-NLS-1$

		IQuery<IInstallableUnit> query = QueryUtil.createIUQuery(iuName, version);

		SubMonitor subMon = SubMonitor.convert(monitor, 100);
		IQueryable<IInstallableUnit> queryable;
		IMetadataRepositoryManager repoManager = (IMetadataRepositoryManager) agent.getService(IMetadataRepositoryManager.SERVICE_NAME);
		if (site == null) {
			queryable = repoManager;
			subMon.worked(80);
		} else
			queryable = repoManager.loadRepository(site, subMon.newChild(80));

		IQueryResult<IInstallableUnit> roots = queryable.query(QueryUtil.createLatestQuery(query), subMon.newChild(10));

		if (roots.isEmpty())
			roots = profile.query(query, subMon.newChild(10));

		if (roots.isEmpty())
			throw BuckminsterException.fromMessage(NLS.bind(Messages.no_suitable_feature_version_found_matching_0, iuName));

		return roots;
	}

	static URI normalizeToURI(String surl) {
		URL url;
		try {
			url = new URL(surl);
		} catch (MalformedURLException e) {
			try {
				url = new File(surl).toURI().toURL();
			} catch (MalformedURLException e2) {
				throw new IllegalArgumentException(NLS.bind(Messages.URL_0_malformed, surl));
			}
		}
		return URI.create(url.toString());
	}

	static int planAndExecute(IProvisioningAgent agent, IProfile profile, ProfileChangeRequest request, ProvisioningContext context,
			IProgressMonitor monitor) throws CoreException {
		IPlanner planner = (IPlanner) agent.getService(IPlanner.SERVICE_NAME);
		IProvisioningPlan plan = planner.getProvisioningPlan(request, context, monitor);
		IStatus status = plan.getStatus();
		if (status.getSeverity() == IStatus.CANCEL)
			return Headless.EXIT_FORCED;
		if (status.getSeverity() == IStatus.ERROR)
			throw new CoreException(status);

		IEngine engine = (IEngine) agent.getService(IEngine.SERVICE_NAME);
		status = engine.perform(plan, null, monitor);
		if (status.getSeverity() == IStatus.CANCEL)
			return Headless.EXIT_FORCED;
		if (status.getSeverity() == IStatus.ERROR)
			throw new CoreException(status);
		return Headless.EXIT_OK;
	}

	private URI site;

	private Version version;

	private String feature;

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception {
		int len = unparsed.length;
		if (len > 3)
			throw new UsageException(Messages.too_many_arguments);
		if (len > 0) {
			String p2Repos = unparsed[0];
			if (p2Repos.endsWith("/headless-site.xml")) //$NON-NLS-1$
				p2Repos = p2Repos.substring(0, p2Repos.length() - 18);
			else if (p2Repos.endsWith("/site.xml")) //$NON-NLS-1$
				p2Repos = p2Repos.substring(0, p2Repos.length() - 9);
			site = normalizeToURI(p2Repos);
		}
		if (len > 1)
			feature = unparsed[1];
		if (len > 2)
			version = Version.parseVersion(unparsed[2]);
	}

	@Override
	protected int run(IProgressMonitor monitor) throws Exception {
		if (site == null)
			throw new UsageException(Messages.no_site_provided);
		if (feature == null)
			throw new UsageException(Messages.no_feature_id_provided);

		Buckminster bucky = Buckminster.getDefault();
		IProvisioningAgentProvider agentProvider = bucky.getService(IProvisioningAgentProvider.class);
		IProvisioningAgent agent = agentProvider.createAgent(null);
		String profileId = bucky.getBundle().getBundleContext().getProperty("eclipse.p2.profile"); //$NON-NLS-1$
		if (profileId == null) {
			profileId = "Buckminster"; //$NON-NLS-1$
			System.setProperty("eclipse.p2.profile", profileId); //$NON-NLS-1$
		}

		IProfileRegistry profileRegistry = (IProfileRegistry) agent.getService(IProfileRegistry.SERVICE_NAME);
		try {
			IProfile profile = profileRegistry.getProfile(profileId);
			IQueryResult<IInstallableUnit> rootArr = getRootIUs(agent, site, profile, feature, version, monitor);

			// Add as root IU's to a request
			ProfileChangeRequest request = new ProfileChangeRequest(profile);
			for (Iterator<IInstallableUnit> iter = rootArr.iterator(); iter.hasNext();)
				request.setInstallableUnitProfileProperty(iter.next(), IProfile.PROP_PROFILE_ROOT_IU, Boolean.TRUE.toString());
			request.addAll(rootArr.toUnmodifiableSet());
			return planAndExecute(agent, profile, request, createContext(agent, site), monitor);
		} finally {
			agent.stop();
			bucky.ungetService(agentProvider);
		}
	}
}
