package org.eclipse.buckminster.galileo.builder;

import java.net.URI;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.amalgam.releng.build.ARCH;
import org.eclipse.amalgam.releng.build.Config;
import org.eclipse.amalgam.releng.build.OS;
import org.eclipse.amalgam.releng.build.WS;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Logger;
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
import org.eclipse.equinox.internal.provisional.p2.engine.InstallableUnitOperand;
import org.eclipse.equinox.internal.provisional.p2.engine.Operand;
import org.eclipse.equinox.internal.provisional.p2.engine.ProvisioningContext;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.InstallableUnitQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.LatestIUVersionQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.query.Collector;
import org.eclipse.equinox.internal.provisional.p2.query.CompositeQuery;
import org.eclipse.equinox.internal.provisional.p2.query.MatchQuery;
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
			throw BuckminsterException.fromMessage("Feature %s not found", iuName); //$NON-NLS-1$

		return (IInstallableUnit[])roots.toArray(IInstallableUnit.class);
	}

	private static String configEnvString(Config config)
	{
		StringBuilder bld = new StringBuilder();
		WS ws = config.getWs();
		if(ws != null)
		{
			bld.append("osgi.ws="); //$NON-NLS-1$
			bld.append(ws.getLiteral());
		}
		OS os = config.getOs();
		if(os != null)
		{
			if(bld.length() > 0)
				bld.append(',');
			bld.append("osgi.os="); //$NON-NLS-1$
			bld.append(os.getLiteral());
		}
		ARCH arch = config.getArch();
		if(arch != null)
		{
			if(bld.length() > 0)
				bld.append(',');
			bld.append("osgi.arch="); //$NON-NLS-1$
			bld.append(arch.getLiteral());
		}
		return bld.toString();
	}

	private static String configNameString(Config config)
	{
		StringBuilder bld = new StringBuilder();
		WS ws = config.getWs();
		if(ws != null)
			bld.append(ws.getLiteral());
		OS os = config.getOs();
		if(os != null)
		{
			if(bld.length() > 0)
				bld.append('.');
			bld.append(os.getLiteral());
		}
		ARCH arch = config.getArch();
		if(arch != null)
		{
			if(bld.length() > 0)
				bld.append('.');
			bld.append(arch.getLiteral());
		}
		return bld.toString();
	}

	private final String m_id;

	private final Version m_version;

	private final URI m_repoLocation;

	private final URI m_tpRepoLocation;

	public RepositoryVerifier(URI repoLocation, URI tpRepoLocation, String id, Version version)
	{
		m_id = id;
		m_repoLocation = repoLocation;
		m_tpRepoLocation = tpRepoLocation;
		m_version = version;
	}

	public Set<IInstallableUnit> run(List<Config> configs, IProgressMonitor monitor) throws CoreException
	{
		Logger log = Buckminster.getLogger();
		log.info("Starting planner verification"); //$NON-NLS-1$
		long now = System.currentTimeMillis();

		Buckminster bucky = Buckminster.getDefault();
		String profilePrefix = "GalileoTest_"; //$NON-NLS-1$
		IProfileRegistry profileRegistry = bucky.getService(IProfileRegistry.class);
		IPlanner planner = bucky.getService(IPlanner.class);
		final HashSet<IInstallableUnit> unitsToInstall = new HashSet<IInstallableUnit>();

		MonitorUtils.begin(monitor, configs.size() * 100);
		try
		{
			for(Config config : configs)
			{
				String configName = configNameString(config);
				String profileId = profilePrefix + configName;
				log.info("Verifying config: %s", configName); //$NON-NLS-1$
				Map<String, String> props = new HashMap<String, String>();
				props.put(IProfile.PROP_FLAVOR, "tooling"); //$NON-NLS-1$
				props.put(IProfile.PROP_ENVIRONMENTS, configEnvString(config));

				profileRegistry.addProfile(profileId, props);
				IProfile profile = profileRegistry.getProfile(profileId);
				IInstallableUnit[] rootArr = getRootIUs(m_repoLocation, profile, m_id, m_version,
						MonitorUtils.subMonitor(monitor, 10));

				// Add as root IU's to a request
				ProfileChangeRequest request = new ProfileChangeRequest(profile);
				for(IInstallableUnit rootIU : rootArr)
					request.setInstallableUnitProfileProperty(rootIU, IInstallableUnit.PROP_PROFILE_ROOT_IU,
							Boolean.TRUE.toString());
				request.addInstallableUnits(rootArr);
				ProvisioningPlan plan = planner.getProvisioningPlan(request, createContext(m_repoLocation),
						MonitorUtils.subMonitor(monitor, 90));

				Operand[] ops = plan.getOperands();
				for(Operand op : ops)
				{
					if(!(op instanceof InstallableUnitOperand))
						continue;

					InstallableUnitOperand iuOp = (InstallableUnitOperand)op;
					IInstallableUnit iu = iuOp.second();
					if(iu != null)
						unitsToInstall.add(iu);
				}

				IStatus status = plan.getStatus();
				if(status.getSeverity() == IStatus.ERROR)
					throw new CoreException(status);
			}
		}
		finally
		{
			MonitorUtils.done(monitor);
			bucky.ungetService(profileRegistry);
			bucky.ungetService(planner);
		}
		log.info("Done. Took %d ms", Long.valueOf(System.currentTimeMillis() - now)); //$NON-NLS-1$

		if(unitsToInstall.size() > 0)
		{
			// Filter out everything that is included in the target platform
			//
			log.info("Found %d units to install. Now pruning using target platform", Integer.valueOf(unitsToInstall.size())); //$NON-NLS-1$
			IMetadataRepositoryManager mdrMgr = bucky.getService(IMetadataRepositoryManager.class);
			IMetadataRepository tpRepo = mdrMgr.loadRepository(m_tpRepoLocation, null);
			tpRepo.query(new MatchQuery()
			{
				@Override
				public boolean isMatch(Object candidate)
				{
					unitsToInstall.remove(candidate);
					return false;
				}
			}, new Collector(), null);
			log.info("%d units remain after pruning", Integer.valueOf(unitsToInstall.size())); //$NON-NLS-1$
		}
		return unitsToInstall;
	}
}
