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
import org.eclipse.equinox.internal.p2.artifact.repository.CompositeArtifactRepository;
import org.eclipse.equinox.internal.p2.core.helpers.FileUtils;
import org.eclipse.equinox.internal.p2.metadata.repository.CompositeMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.repository.IRepository;

@SuppressWarnings("restriction")
public class CompositeRepoGenerator extends BuilderPhase {
	private static void verifyIUExistence(IMetadataRepositoryManager mdrMgr, Repository repo, String id, String version, List<String> errors)
			throws CoreException {
		if (repo == null)
			return;

		URI location = URI.create(repo.getLocation());
		if (Builder.getIU(mdrMgr.loadRepository(location, null), id, version) == null) {
			String msg = String.format("Unable to find %s/%s in repository %s", id, version, repo.getLocation());
			errors.add(msg);
			Buckminster.getLogger().error(msg);
		}
	}

	public CompositeRepoGenerator(Builder builder) {
		super(builder);
	}

	@Override
	public void run(IProgressMonitor monitor) throws CoreException {
		Logger log = Buckminster.getLogger();
		log.info("Starting generation of composite repository");
		long now = System.currentTimeMillis();

		String name = getBuilder().getBuild().getLabel() + " Composite";
		File location = new File(getBuilder().getBuildRoot(), Builder.REPO_FOLDER_INTERIM);
		FileUtils.deleteAll(location);

		Map<String, String> properties = new HashMap<String, String>();
		properties.put(IRepository.PROP_COMPRESSED, Boolean.toString(true));
		URI locationURI = Builder.createURI(location);
		Build buildModel = getBuilder().getBuild();
		List<Contribution> contribs = buildModel.getContributions();
		MonitorUtils.begin(monitor, contribs.size() * 100);
		boolean errorsFound = false;

		Buckminster bucky = Buckminster.getDefault();
		IMetadataRepositoryManager mdrMgr = bucky.getService(IMetadataRepositoryManager.class);
		mdrMgr.removeRepository(locationURI);
		CompositeMetadataRepository mdr = (CompositeMetadataRepository) mdrMgr.createRepository(locationURI, name, Builder.COMPOSITE_METADATA_TYPE,
				properties);

		IArtifactRepositoryManager arMgr = bucky.getService(IArtifactRepositoryManager.class);
		arMgr.removeRepository(locationURI);
		CompositeArtifactRepository ar = (CompositeArtifactRepository) arMgr.createRepository(locationURI,
				name + " artifacts", Builder.COMPOSITE_ARTIFACTS_TYPE, properties); //$NON-NLS-1$

		for (Contribution contrib : contribs) {
			IProgressMonitor contribMonitor = MonitorUtils.subMonitor(monitor, 100);
			List<Repository> repos = contrib.getRepositories();
			MonitorUtils.begin(contribMonitor, repos.size() * 200);
			List<String> errors = new ArrayList<String>();
			for (Repository repo : repos) {
				try {
					URI childLocation = URI.create(repo.getLocation());

					log.info("Adding child meta-data repository %s", childLocation);
					mdrMgr.loadRepository(childLocation, MonitorUtils.subMonitor(contribMonitor, 100));
					mdr.addChild(childLocation);

					log.info("Adding child artifact repository %s", childLocation);
					arMgr.loadRepository(childLocation, MonitorUtils.subMonitor(contribMonitor, 100));
					ar.addChild(childLocation);
				} catch (Exception e) {
					String msg = Builder.getExceptionMessages(e);
					errors.add(msg);
					log.error(e, msg);
				}
			}

			if (errors.size() == 0) {
				// Verify that all contributed features and bundles can be found
				// in their respective
				// repository
				for (Feature feature : contrib.getFeatures())
					verifyIUExistence(mdrMgr, feature.getRepo(), feature.getId() + ".feature.group", //$NON-NLS-1$
							feature.getVersion(), errors);
				for (Bundle bundle : contrib.getBundles())
					verifyIUExistence(mdrMgr, bundle.getRepo(), bundle.getId(), bundle.getVersion(), errors);
			}
			MonitorUtils.done(contribMonitor);
			if (!errors.isEmpty()) {
				getBuilder().sendEmail(contrib, errors);
				errorsFound = true;
			}
		}
		bucky.ungetService(mdrMgr);
		bucky.ungetService(arMgr);

		MonitorUtils.done(monitor);
		log.info("Done. Took %d ms", Long.valueOf(System.currentTimeMillis() - now));
		if (errorsFound)
			throw BuckminsterException.fromMessage("CompositeRepository generation was not succesful");
	}
}
