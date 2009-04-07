package org.eclipse.buckminster.galileo.builder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.galileo.builder.BuildModel.Configuration;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.equinox.internal.p2.console.ProvisioningHelper;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;
import org.eclipse.equinox.internal.provisional.p2.director.IPlanner;
import org.eclipse.equinox.internal.provisional.p2.director.ProfileChangeRequest;
import org.eclipse.equinox.internal.provisional.p2.director.ProvisioningPlan;
import org.eclipse.equinox.internal.provisional.p2.engine.IProfile;
import org.eclipse.equinox.internal.provisional.p2.engine.IProfileRegistry;
import org.eclipse.equinox.internal.provisional.p2.engine.ProvisioningContext;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.InstallableUnitQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.LatestIUVersionQuery;
import org.eclipse.equinox.internal.provisional.p2.query.Collector;
import org.eclipse.equinox.internal.provisional.p2.query.CompositeQuery;
import org.eclipse.equinox.internal.provisional.p2.query.Query;

@SuppressWarnings("restriction")
public class RepositoryVerifier
{
	static ProvisioningContext createContext(URI site)
	{
		URI[] repoLocations = new URI[] { site };
		ProvisioningContext context = new ProvisioningContext(repoLocations);
		context.setArtifactRepositories(repoLocations);
		return context;
	}

	static IInstallableUnit[] getRootIUs(URI site, IProfile profile, String iuName, Version version,
			IProgressMonitor monitor) throws CoreException
	{
		if(!iuName.endsWith(Activator.FEATURE_GROUP_SUFFIX))
			iuName += Activator.FEATURE_GROUP_SUFFIX;

		Query query = new InstallableUnitQuery(iuName, version == null
				? VersionRange.emptyRange
				: new VersionRange(version, true, version, true));

		Collector roots = ProvisioningHelper.getInstallableUnits(site, new CompositeQuery(new Query[] { query,
				new LatestIUVersionQuery() }), new Collector(), monitor);

		if(roots.size() <= 0)
			roots = profile.query(query, roots, new NullProgressMonitor());

		if(roots.size() <= 0)
			throw BuckminsterException.fromMessage("Feature %s not found", iuName);

		return (IInstallableUnit[])roots.toArray(IInstallableUnit.class);
	}

	private final String m_id;

	private final Version m_version;

	private final URI m_repoLocation;

	public RepositoryVerifier(URI repoLocation, String id, Version version)
	{
		m_id = id;
		m_repoLocation = repoLocation;
		m_version = version;
	}

	public void run(List<Configuration> configs, IProgressMonitor monitor) throws CoreException
	{
		System.out.println("Starting planner verification");
		long now = System.currentTimeMillis();

		Buckminster bucky = Buckminster.getDefault();
		String profileId = "GalileoTest";
		IProfileRegistry profileRegistry = bucky.getService(IProfileRegistry.class);
		IPlanner planner = bucky.getService(IPlanner.class);
		MonitorUtils.begin(monitor, 10);
		try
		{
			Map<String, String> props = new HashMap<String, String>();
			props.put(IProfile.PROP_FLAVOR, "tooling");
			props.put(IProfile.PROP_ENVIRONMENTS, "osgi.ws=gtk,osgi.os=linux,osgi.arch=x86_64");

			profileRegistry.addProfile(profileId, props);
			IProfile profile = profileRegistry.getProfile(profileId);
			IInstallableUnit[] rootArr = getRootIUs(m_repoLocation, profile, m_id, m_version, MonitorUtils.subMonitor(
					monitor, 1));

			// Add as root IU's to a request
			ProfileChangeRequest request = new ProfileChangeRequest(profile);
			for(IInstallableUnit rootIU : rootArr)
				request.setInstallableUnitProfileProperty(rootIU, IInstallableUnit.PROP_PROFILE_ROOT_IU,
						Boolean.TRUE.toString());
			request.addInstallableUnits(rootArr);
			ProvisioningPlan plan = planner.getProvisioningPlan(request, createContext(m_repoLocation),
					MonitorUtils.subMonitor(monitor, 9));
			IStatus status = plan.getStatus();
			if(status.getSeverity() == IStatus.ERROR)
				throw new CoreException(status);
		}
		finally
		{
			MonitorUtils.done(monitor);
			bucky.ungetService(profileRegistry);
			bucky.ungetService(planner);
		}
		System.out.println("Done. Took " + (System.currentTimeMillis() - now) + " ms");
	}
}
