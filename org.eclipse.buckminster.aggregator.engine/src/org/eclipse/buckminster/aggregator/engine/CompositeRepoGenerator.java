package org.eclipse.buckminster.aggregator.engine;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.aggregator.Aggregator;
import org.eclipse.buckminster.aggregator.Contribution;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.MappedUnit;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.equinox.internal.p2.artifact.repository.CompositeArtifactRepository;
import org.eclipse.equinox.internal.p2.core.helpers.FileUtils;
import org.eclipse.equinox.internal.p2.metadata.repository.CompositeMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.core.VersionedName;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.repository.IRepository;

public class CompositeRepoGenerator extends BuilderPhase
{
	public CompositeRepoGenerator(Builder builder)
	{
		super(builder);
	}

	@Override
	public void run(IProgressMonitor monitor) throws CoreException
	{
		Logger log = Buckminster.getLogger();
		log.info("Starting generation of composite repository");
		long now = System.currentTimeMillis();

		String name = getBuilder().getAggregator().getLabel() + " Composite";
		File location = new File(getBuilder().getBuildRoot(), Builder.REPO_FOLDER_INTERIM);
		FileUtils.deleteAll(location);

		Map<String, String> properties = new HashMap<String, String>();
		properties.put(IRepository.PROP_COMPRESSED, Boolean.toString(true));
		URI locationURI = Builder.createURI(location);
		Aggregator buildModel = getBuilder().getAggregator();
		List<Contribution> contribs = buildModel.getContributions();
		MonitorUtils.begin(monitor, contribs.size() * 100);
		boolean errorsFound = false;

		Buckminster bucky = Buckminster.getDefault();
		IMetadataRepositoryManager mdrMgr = bucky.getService(IMetadataRepositoryManager.class);
		mdrMgr.removeRepository(locationURI);
		CompositeMetadataRepository compositeMdr = (CompositeMetadataRepository)mdrMgr.createRepository(locationURI,
				name, Builder.COMPOSITE_METADATA_TYPE, properties);

		IArtifactRepositoryManager arMgr = bucky.getService(IArtifactRepositoryManager.class);
		arMgr.removeRepository(locationURI);
		CompositeArtifactRepository compositeAr = (CompositeArtifactRepository)arMgr.createRepository(locationURI, name
				+ " artifacts", Builder.COMPOSITE_ARTIFACTS_TYPE, properties); //$NON-NLS-1$

		for(Contribution contrib : contribs)
		{
			IProgressMonitor contribMonitor = MonitorUtils.subMonitor(monitor, 100);
			List<MappedRepository> repos = contrib.getRepositories();
			MonitorUtils.begin(contribMonitor, repos.size() * 200);
			List<String> errors = new ArrayList<String>();
			for(MappedRepository repo : repos)
			{
				MetadataRepository mdr = repo.getMetadataRepository();
				if(mdr.eIsProxy())
				{
					String msg = String.format("Repository location %s is unresolved", repo.getLocation());
					errors.add(msg);
					log.error(msg);
					continue;
				}

				if(!repo.isMapVerbatim())
				{
					for(MappedUnit mu : repo.getEnabledUnits())
					{
						InstallableUnit muIU = mu.getInstallableUnit();
						if(muIU.eIsProxy())
						{
							VersionedName vn = muIU.getVersionedNameFromProxy();
							String msg = String.format("Unable to find %s/%s in repository %s", vn.getId(),
									vn.getVersion(), repo.getLocation());
							errors.add(msg);
							log.error(msg);
						}
					}
				}

				try
				{
					URI childLocation = mdr.getLocation();
					log.info("Adding child meta-data repository %s", childLocation);
					compositeMdr.addChild(childLocation);
					log.info("Adding child artifact repository %s", childLocation);
					compositeAr.addChild(childLocation);
				}
				catch(Exception e)
				{
					String msg = Builder.getExceptionMessages(e);
					errors.add(msg);
					log.error(e, msg);
				}
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
		log.info("Done. Took %d ms", Long.valueOf(System.currentTimeMillis() - now));
		if(errorsFound)
			throw BuckminsterException.fromMessage("CompositeRepository generation was not succesful");
	}
}
