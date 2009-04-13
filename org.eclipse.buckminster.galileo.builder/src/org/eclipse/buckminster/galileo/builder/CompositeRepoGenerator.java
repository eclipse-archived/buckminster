package org.eclipse.buckminster.galileo.builder;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.amalgam.releng.build.Build;
import org.eclipse.amalgam.releng.build.Bundle;
import org.eclipse.amalgam.releng.build.Contribution;
import org.eclipse.amalgam.releng.build.Feature;
import org.eclipse.amalgam.releng.build.Repository;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.equinox.internal.p2.artifact.repository.CompositeArtifactRepository;
import org.eclipse.equinox.internal.p2.core.helpers.FileUtils;
import org.eclipse.equinox.internal.p2.metadata.repository.CompositeMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.InstallableUnitQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.query.Collector;
import org.eclipse.equinox.internal.provisional.p2.repository.IRepository;

@SuppressWarnings("restriction")
public class CompositeRepoGenerator extends BuilderPhase
{
	private static URI mangleLocation(String location)
	{
		if(location.endsWith("/site.xml")) //$NON-NLS-1$
			location = location.substring(0, location.length() - 8);
		return URI.create(location);
	}

	private static void verifyIUExistence(IMetadataRepositoryManager mdrMgr, Repository repo, String id,
			String version, List<String> errors) throws CoreException
	{
		if(repo == null)
			return;

		URI location = mangleLocation(repo.getLocation());
		InstallableUnitQuery query = version == null
				? new InstallableUnitQuery(id)
				: new InstallableUnitQuery(id, new Version(version));

		if(mdrMgr.loadRepository(location, null).query(query, new Collector(), null).isEmpty())
		{
			String msg = String.format("Unable to find %s/%s in repository %s", id, version, repo.getLocation());
			errors.add(msg);
			Buckminster.getLogger().error(msg);
		}
	}

	private final File m_location;

	private final String m_name;

	public CompositeRepoGenerator(Builder builder, File location, String name)
	{
		super(builder);
		m_location = location;
		m_name = name;
	}

	@Override
	public void run(IProgressMonitor monitor) throws CoreException
	{
		Logger log = Buckminster.getLogger();
		log.info("Starting generation of composite repository");
		long now = System.currentTimeMillis();

		FileUtils.deleteAll(m_location);

		Map<String, String> properties = new HashMap<String, String>();
		properties.put(IRepository.PROP_COMPRESSED, Boolean.toString(true));
		URI locationURI = Builder.createURI(m_location);
		Build buildModel = getBuilder().getBuild();
		EList<Contribution> contribs = buildModel.getContributions();
		MonitorUtils.begin(monitor, contribs.size() * 100);
		boolean errorsFound = false;

		Buckminster bucky = Buckminster.getDefault();
		IMetadataRepositoryManager mdrMgr = bucky.getService(IMetadataRepositoryManager.class);
		mdrMgr.removeRepository(locationURI);
		CompositeMetadataRepository mdr = (CompositeMetadataRepository)mdrMgr.createRepository(locationURI, m_name,
				Activator.COMPOSITE_METADATA_TYPE, properties);

		IArtifactRepositoryManager arMgr = bucky.getService(IArtifactRepositoryManager.class);
		arMgr.removeRepository(locationURI);
		CompositeArtifactRepository ar = (CompositeArtifactRepository)arMgr.createRepository(locationURI, m_name
				+ " artifacts", Activator.COMPOSITE_ARTIFACTS_TYPE, properties); //$NON-NLS-1$

		for(Contribution contrib : contribs)
		{
			IProgressMonitor contribMonitor = MonitorUtils.subMonitor(monitor, 100);
			EList<Repository> repos = contrib.getRepositories();
			MonitorUtils.begin(contribMonitor, repos.size() * 200);
			List<String> errors = new ArrayList<String>();
			for(Repository repo : repos)
			{
				try
				{
					URI location = mangleLocation(repo.getLocation());

					log.info("Adding child meta-data repository %s", location);
					mdrMgr.loadRepository(location, MonitorUtils.subMonitor(contribMonitor, 100));
					mdr.addChild(location);

					log.info("Adding child artifact repository %s", location);
					arMgr.loadRepository(location, MonitorUtils.subMonitor(contribMonitor, 100));
					ar.addChild(location);
				}
				catch(Exception e)
				{
					String msg = String.format("Failed to load repository at: %s: %s", repo.getLocation(),
							Builder.getExceptionMessages(e));
					errors.add(msg);
					log.error(e, msg);
				}
			}

			if(errors.size() == 0)
			{
				// Verify that all contributed features and bundles can be found in their respective
				// repository
				for(Feature feature : contrib.getFeatures())
					verifyIUExistence(mdrMgr, feature.getRepo(), feature.getId() + ".feature.group", //$NON-NLS-1$
							feature.getVersion(), errors);
				for(Bundle bundle : contrib.getBundles())
					verifyIUExistence(mdrMgr, bundle.getRepo(), bundle.getId(), bundle.getVersion(), errors);
			}
			MonitorUtils.done(contribMonitor);
			if(!errors.isEmpty())
			{
				getBuilder().sendEmail(contrib, errors);
				errorsFound = true;
			}
		}
		bucky.ungetService(mdrMgr);
		bucky.ungetService(arMgr);

		MonitorUtils.done(monitor);
		if(errorsFound)
			throw BuckminsterException.fromMessage("CompositeRepository generation was not succesful");

		log.info("Done. Took %d ms", Long.valueOf(System.currentTimeMillis() - now));
	}
}
