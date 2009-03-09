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
import java.util.Properties;

import org.eclipse.buckminster.cmdline.AbstractCommand;
import org.eclipse.buckminster.cmdline.Headless;
import org.eclipse.buckminster.cmdline.SimpleErrorExitException;
import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.internal.p2.console.ProvisioningHelper;
import org.eclipse.equinox.internal.p2.engine.Profile;
import org.eclipse.equinox.internal.p2.engine.SimpleProfileRegistry;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;
import org.eclipse.equinox.internal.provisional.p2.core.eventbus.IProvisioningEventBus;
import org.eclipse.equinox.internal.provisional.p2.director.IPlanner;
import org.eclipse.equinox.internal.provisional.p2.director.ProfileChangeRequest;
import org.eclipse.equinox.internal.provisional.p2.director.ProvisioningPlan;
import org.eclipse.equinox.internal.provisional.p2.engine.CommitOperationEvent;
import org.eclipse.equinox.internal.provisional.p2.engine.DefaultPhaseSet;
import org.eclipse.equinox.internal.provisional.p2.engine.IEngine;
import org.eclipse.equinox.internal.provisional.p2.engine.IProfile;
import org.eclipse.equinox.internal.provisional.p2.engine.IProfileRegistry;
import org.eclipse.equinox.internal.provisional.p2.engine.InstallableUnitEvent;
import org.eclipse.equinox.internal.provisional.p2.engine.InstallableUnitOperand;
import org.eclipse.equinox.internal.provisional.p2.engine.Operand;
import org.eclipse.equinox.internal.provisional.p2.engine.ProvisioningContext;
import org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.InstallableUnitQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.LatestIUVersionQuery;
import org.eclipse.equinox.internal.provisional.p2.query.Collector;
import org.eclipse.equinox.internal.provisional.p2.query.CompositeQuery;
import org.eclipse.equinox.internal.provisional.p2.query.Query;
import org.eclipse.equinox.p2.publisher.PublisherInfo;
import org.eclipse.equinox.p2.publisher.eclipse.BundlesAction;
import org.eclipse.osgi.service.datalocation.Location;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.osgi.util.NLS;

@SuppressWarnings("restriction")
public class Install extends AbstractCommand
{
	static ProvisioningContext createContext(URI site)
	{
		URI[] repoLocations = new URI[] { site };
		ProvisioningContext context = new ProvisioningContext(repoLocations);
		context.setArtifactRepositories(repoLocations);
		return context;
	}

	static IInstallableUnit[] getRootIUs(URI site, IProfile profile, String iuName, Version version,
			IProgressMonitor monitor) throws SimpleErrorExitException
	{
		if(!iuName.endsWith(".feature.group"))
			iuName = iuName + ".feature.group";

		Query query = new InstallableUnitQuery(iuName, version == null
				? VersionRange.emptyRange
				: new VersionRange(version, true, version, true));

		Collector roots = ProvisioningHelper.getInstallableUnits(site, new CompositeQuery(new Query[] { query,
				new LatestIUVersionQuery() }), new Collector(), monitor);

		if(roots.size() <= 0)
			roots = profile.query(query, roots, new NullProgressMonitor());

		if(roots.size() <= 0)
			throw new SimpleErrorExitException(NLS.bind(Messages.no_suitable_feature_version_found_matching_0, iuName));

		return (IInstallableUnit[])roots.toArray(IInstallableUnit.class);
	}

	static URI normalizeToURI(String surl)
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
				throw new IllegalArgumentException(NLS.bind(Messages.URL_0_malformed, surl));
			}
		}
		return URI.create(url.toString());
	}

	static int planAndExecute(IProfile profile, ProfileChangeRequest request, ProvisioningContext context,
			IProgressMonitor monitor) throws CoreException
	{
		Buckminster bucky = Buckminster.getDefault();
		IPlanner planner = bucky.getService(IPlanner.class);
		ProvisioningPlan plan;
		try
		{
			plan = planner.getProvisioningPlan(request, context, monitor);
			IStatus status = plan.getStatus();
			if(status.getSeverity() == IStatus.CANCEL)
				return Headless.EXIT_FORCED;
			if(status.getSeverity() == IStatus.ERROR)
				throw new CoreException(status);
		}
		finally
		{
			bucky.ungetService(planner);
		}

		IEngine engine = bucky.getService(IEngine.class);
		try
		{
			IStatus status = engine.perform(profile, new DefaultPhaseSet(), plan.getOperands(), context, monitor);
			if(status.getSeverity() == IStatus.CANCEL)
				return Headless.EXIT_FORCED;
			if(status.getSeverity() == IStatus.ERROR)
				throw new CoreException(status);
		}
		finally
		{
			bucky.ungetService(engine);
		}
		return Headless.EXIT_OK;
	}

	private URI m_site;

	private Version m_version;

	private String m_feature;

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		int len = unparsed.length;
		if(len > 3)
			throw new UsageException(Messages.too_many_arguments);
		if(len > 0)
		{
			String p2Repos = unparsed[0];
			if(p2Repos.endsWith("/headless-site.xml"))
				p2Repos = p2Repos.substring(0, p2Repos.length() - 18);
			else if(p2Repos.endsWith("/site.xml"))
				p2Repos = p2Repos.substring(0, p2Repos.length() - 9);
			m_site = normalizeToURI(p2Repos);
		}
		if(len > 1)
			m_feature = unparsed[1];
		if(len > 2)
			m_version = Version.parseVersion(unparsed[2]);
	}

	@Override
	protected int run(IProgressMonitor monitor) throws Exception
	{
		if(m_site == null)
			throw new UsageException(Messages.no_site_provided);
		if(m_feature == null)
			throw new UsageException(Messages.no_feature_id_provided);

		Buckminster bucky = Buckminster.getDefault();
		String profileId = bucky.getBundle().getBundleContext().getProperty("eclipse.p2.profile");
		if(profileId == null)
		{
			profileId = "BuckminsterHeadless";
			System.setProperty("eclipse.p2.profile", profileId);
		}

		SimpleProfileRegistry profileRegistry = (SimpleProfileRegistry)bucky.getService(IProfileRegistry.class);
		try
		{
			IProfile profile = profileRegistry.getProfile(profileId);
			if(profile == null)
			{
				Location location = Platform.getInstallLocation();
				File installDir = new File(location.getURL().toURI());
				String destination = installDir.getAbsolutePath();
				Properties props = new Properties();
				props.setProperty(IProfile.PROP_INSTALL_FOLDER, destination);
				props.setProperty(IProfile.PROP_CACHE, destination);
				props.setProperty(IProfile.PROP_DESCRIPTION, "Buckminster Headless");
				props.setProperty(IProfile.PROP_INSTALL_FEATURES, "true");
				props.setProperty(IProfile.PROP_FLAVOR, "tooling");
				props.setProperty(IProfile.PROP_ROAMING, "true");
				Profile profImpl = (Profile)ProvisioningHelper.addProfile(profileId, props);

				IProvisioningEventBus bus = bucky.getService(IProvisioningEventBus.class);
				IEngine engine = bucky.getService(IEngine.class);
				profileRegistry.lockProfile(profImpl);
				try
				{
					// Create metadata for 'self'
					//
					PublisherInfo info = new PublisherInfo();
					for(File bundleFile : new File(installDir, "plugins").listFiles())
					{
						BundleDescription bd = BundlesAction.createBundleDescription(bundleFile);
						IArtifactKey key = BundlesAction.createBundleArtifactKey(bd.getSymbolicName(), bd.getVersion()
								.toString());
						IInstallableUnit iu = BundlesAction.createBundleIU(bd, key, info);
						profImpl.addInstallableUnit(iu);
						bus.publishEvent(new InstallableUnitEvent("collect", false, profImpl,
								new InstallableUnitOperand(iu, null), InstallableUnitEvent.INSTALL, null));
					}
					bus.publishEvent(new CommitOperationEvent(profImpl, new DefaultPhaseSet(), new Operand[0], engine));
					profileRegistry.updateProfile(profImpl);
				}
				finally
				{
					profileRegistry.unlockProfile(profImpl);
				}
				profile = profileRegistry.getProfile(profileId);
			}
			IInstallableUnit[] rootArr = getRootIUs(m_site, profile, m_feature, m_version, monitor);

			// Add as root IU's to a request
			ProfileChangeRequest request = new ProfileChangeRequest(profile);
			for(IInstallableUnit rootIU : rootArr)
				request.setInstallableUnitProfileProperty(rootIU, IInstallableUnit.PROP_PROFILE_ROOT_IU, Boolean.TRUE
						.toString());
			request.addInstallableUnits(rootArr);
			return planAndExecute(profile, request, createContext(m_site), monitor);
		}
		finally
		{
			bucky.ungetService(profileRegistry);
		}
	}
}
