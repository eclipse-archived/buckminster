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

import org.eclipse.buckminster.cmdline.AbstractCommand;
import org.eclipse.buckminster.cmdline.Headless;
import org.eclipse.buckminster.cmdline.SimpleErrorExitException;
import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.equinox.internal.p2.console.ProvisioningHelper;
import org.eclipse.equinox.internal.p2.engine.SimpleProfileRegistry;
import org.eclipse.equinox.internal.p2.metadata.query.LatestIUVersionQuery;
import org.eclipse.equinox.internal.provisional.p2.director.IPlanner;
import org.eclipse.equinox.internal.provisional.p2.director.ProfileChangeRequest;
import org.eclipse.equinox.p2.engine.DefaultPhaseSet;
import org.eclipse.equinox.p2.engine.IEngine;
import org.eclipse.equinox.p2.engine.IProfile;
import org.eclipse.equinox.p2.engine.IProfileRegistry;
import org.eclipse.equinox.p2.engine.IProvisioningPlan;
import org.eclipse.equinox.p2.engine.ProvisioningContext;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.equinox.p2.metadata.query.InstallableUnitQuery;
import org.eclipse.equinox.p2.query.IQuery;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.PipedQuery;
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

		IQuery<IInstallableUnit> query = new InstallableUnitQuery(iuName, version == null
				? VersionRange.emptyRange
				: new VersionRange(version, true, version, true));
 
		IQueryResult<IInstallableUnit> roots = ProvisioningHelper.getInstallableUnits(site,
				new PipedQuery<IInstallableUnit>(query, new LatestIUVersionQuery<IInstallableUnit>()), monitor);

		if(roots.isEmpty())
			roots = profile.query(query, new NullProgressMonitor());

		if(roots.isEmpty())
			throw new SimpleErrorExitException(NLS.bind(Messages.no_suitable_feature_version_found_matching_0, iuName));

		return roots.toArray(IInstallableUnit.class);
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
		IProvisioningPlan plan;
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
			IStatus status = engine.perform(plan, new DefaultPhaseSet(), monitor);
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
			profileId = "Buckminster";
			System.setProperty("eclipse.p2.profile", profileId);
		}

		SimpleProfileRegistry profileRegistry = (SimpleProfileRegistry)bucky.getService(IProfileRegistry.class);
		try
		{
			IProfile profile = profileRegistry.getProfile(profileId);
			IInstallableUnit[] rootArr = getRootIUs(m_site, profile, m_feature, m_version, monitor);

			// Add as root IU's to a request
			ProfileChangeRequest request = new ProfileChangeRequest(profile);
			for(IInstallableUnit rootIU : rootArr)
				request.setInstallableUnitProfileProperty(rootIU, IProfile.PROP_PROFILE_ROOT_IU,
						Boolean.TRUE.toString());
			request.addInstallableUnits(rootArr);
			return planAndExecute(profile, request, createContext(m_site), monitor);
		}
		finally
		{
			bucky.ungetService(profileRegistry);
		}
	}
}
