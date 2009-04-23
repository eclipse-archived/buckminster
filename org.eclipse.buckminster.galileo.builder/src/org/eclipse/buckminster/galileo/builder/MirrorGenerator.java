package org.eclipse.buckminster.galileo.builder;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.amalgam.releng.build.Contribution;
import org.eclipse.amalgam.releng.build.Repository;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.equinox.internal.p2.artifact.repository.CompositeArtifactRepository;
import org.eclipse.equinox.internal.p2.artifact.repository.MirrorRequest;
import org.eclipse.equinox.internal.p2.artifact.repository.RawMirrorRequest;
import org.eclipse.equinox.internal.p2.core.helpers.FileUtils;
import org.eclipse.equinox.internal.p2.metadata.repository.CompositeMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactDescriptor;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepository;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.processing.ProcessingStepHandler;
import org.eclipse.equinox.internal.provisional.p2.core.ProvisionException;
import org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.query.Collector;
import org.eclipse.equinox.internal.provisional.p2.query.IQueryable;
import org.eclipse.equinox.internal.provisional.p2.query.MatchQuery;
import org.eclipse.equinox.internal.provisional.p2.query.Query;
import org.eclipse.equinox.internal.provisional.p2.repository.ICompositeRepository;
import org.eclipse.equinox.internal.provisional.p2.repository.IRepository;
import org.eclipse.equinox.p2.publisher.Publisher;

@SuppressWarnings("restriction")
public class MirrorGenerator extends BuilderPhase {
	private static class AllButAllContributedFeature extends MatchQuery {
		@Override
		public boolean isMatch(Object candidate) {
			return !(candidate instanceof IInstallableUnit && Builder.ALL_CONTRIBUTED_CONTENT_FEATURE.equals(((IInstallableUnit) candidate).getId()));
		}
	}

	/**
	 * A request to restore the canonical form after a raw copy of the optimized
	 * form
	 */
	private static class CanonicalizeRequest extends MirrorRequest {
		private IArtifactDescriptor optimizedDescriptor;

		private IArtifactDescriptor canonicalDescriptor;

		public CanonicalizeRequest(IArtifactDescriptor optimizedDescriptor, IArtifactDescriptor canonicalDescriptor,
				IArtifactRepository targetRepository) {
			super(canonicalDescriptor.getArtifactKey(), targetRepository, null, null);
			this.optimizedDescriptor = optimizedDescriptor;
			this.canonicalDescriptor = canonicalDescriptor;
			setSourceRepository(targetRepository);
		}

		@Override
		public void perform(IProgressMonitor monitor) {
			setResult(transfer(canonicalDescriptor, optimizedDescriptor, monitor));
		}
	}

	private static class IncludesQuery extends MatchQuery {
		private final Set<IInstallableUnit> unitsToInclude;

		public IncludesQuery(Set<IInstallableUnit> unitsToInclude) {
			this.unitsToInclude = unitsToInclude;
		}

		@Override
		public boolean isMatch(Object candidate) {
			return unitsToInclude.contains(candidate);
		}
	}

	private static IStatus constraintStatus(IStatus status) {
		return status.getSeverity() == IStatus.ERROR && status.getException() != null ? status : null;
	}

	private static IStatus extractDeeperRootCause(IStatus status) {
		if (status == null)
			return null;
		if (!status.isMultiStatus())
			return constraintStatus(status);

		IStatus[] children = ((MultiStatus) status).getChildren();
		if (children == null)
			return constraintStatus(status);

		for (int i = 0; i < children.length; i++) {
			IStatus deeper = extractDeeperRootCause(children[i]);
			if (deeper != null)
				return deeper;
		}

		Throwable t = status.getException();
		if (t instanceof CoreException) {
			IStatus deeper = extractDeeperRootCause(((CoreException) t).getStatus());
			if (deeper != null)
				return deeper;
		}
		return constraintStatus(status);
	}

	/**
	 * Extract the root cause. The root cause is the first severe
	 * non-MultiStatus status containing an exception when searching depth first
	 * otherwise null.
	 * 
	 * @param status
	 * @return root cause
	 */
	private static IStatus extractRootCause(IStatus status) {
		IStatus rootCause = extractDeeperRootCause(status);
		return rootCause == null ? status : rootCause;
	}

	@SuppressWarnings("unchecked")
	private static List<URI> getCompositeChildren(IRepository repository) {
		return (repository instanceof ICompositeRepository) ? ((ICompositeRepository) repository).getChildren() : Collections.emptyList();
	}

	private static void mirror(IArtifactRepository source, IArtifactRepository dest, IArtifactDescriptor descriptor, IProgressMonitor monitor)
			throws CoreException {
		if (dest.contains(descriptor))
			return;

		RawMirrorRequest request = new RawMirrorRequest(descriptor, descriptor, dest);
		request.setSourceRepository(source);
		request.perform(monitor);
		IStatus result = request.getResult();
		if (result.getSeverity() == IStatus.ERROR) {
			if (result.getCode() != org.eclipse.equinox.internal.provisional.p2.core.ProvisionException.ARTIFACT_EXISTS) {
				dest.removeDescriptor(descriptor);
				result = extractRootCause(result);
				throw BuckminsterException.fromMessage(result.getException(), "Unable to mirror artifact %s from repository %s: %s", descriptor
						.getArtifactKey(), source.getLocation(), result.getMessage());
			}
		}
	}

	private static void mirror(IArtifactRepository source, IArtifactRepository dest, Set<IArtifactKey> keysToInstall, List<String> errors,
			IProgressMonitor monitor) {
		Logger log = Buckminster.getLogger();
		boolean localSource = "file".equals(source.getLocation().getScheme()); //$NON-NLS-1$
		IArtifactKey[] keys = source.getArtifactKeys();
		MonitorUtils.begin(monitor, keys.length * 100);
		for (IArtifactKey key : keys) {
			if (!keysToInstall.contains(key))
				continue;

			log.info("- mirroring artifact %s", key);
			try {
				IArtifactDescriptor[] aDescs = source.getArtifactDescriptors(key);
				if (!localSource && aDescs.length == 2) {
					// Typically one that has no format and one that is packed.
					// If so,
					// just copy the packed one.
					//
					IArtifactDescriptor optimized = null;
					IArtifactDescriptor canonical = null;
					for (IArtifactDescriptor desc : aDescs) {
						if (desc.getProperty(IArtifactDescriptor.FORMAT) == null)
							canonical = desc;
						else if (ProcessingStepHandler.canProcess(desc))
							optimized = desc;
					}
					if (optimized != null) {
						mirror(source, dest, optimized, MonitorUtils.subMonitor(monitor, 90));
						if (canonical != null) {
							// Restore the canonical form from the optimized
							// one.
							CanonicalizeRequest request = new CanonicalizeRequest(optimized, canonical, dest);
							request.perform(MonitorUtils.subMonitor(monitor, 90));
							IStatus result = request.getResult();
							if (result.getSeverity() == IStatus.ERROR) {
								if (result.getCode() != org.eclipse.equinox.internal.provisional.p2.core.ProvisionException.ARTIFACT_EXISTS) {
									dest.removeDescriptor(canonical);
									result = extractRootCause(result);
									throw BuckminsterException.fromMessage(result.getException(),
											"Unable to unpack artifact %s in repository %s: %s", key, dest.getLocation(), result.getMessage());
								}
							}
						}
						continue;
					}
				}
				for (IArtifactDescriptor desc : aDescs)
					mirror(source, dest, desc, MonitorUtils.subMonitor(monitor, 100 / aDescs.length));
			} catch (CoreException e) {
				errors.add(Builder.getExceptionMessages(e));
				Buckminster.getLogger().error(e, e.getMessage());
			}
		}
		MonitorUtils.done(monitor);
	}

	private static void mirror(Query filter, IQueryable source, IMetadataRepository dest, IProgressMonitor monitor) {
		Collector allIUs = source.query(filter, new Collector(), monitor);
		dest.addInstallableUnits((IInstallableUnit[]) allIUs.toArray(IInstallableUnit.class));
	}

	public MirrorGenerator(Builder builder) {
		super(builder);
	}

	@Override
	public void run(IProgressMonitor monitor) throws CoreException {
		Logger log = Buckminster.getLogger();
		log.info("Starting mirror generation");
		long now = System.currentTimeMillis();

		File destination = new File(getBuilder().getBuildRoot(), Builder.REPO_FOLDER_FINAL);
		URI finalURI = Builder.createURI(destination);

		File mirrorDestination = destination;
		URI mirrorURI = finalURI;

		Buckminster bucky = Buckminster.getDefault();

		IMetadataRepositoryManager mdrMgr = bucky.getService(IMetadataRepositoryManager.class);
		IArtifactRepositoryManager arMgr = bucky.getService(IArtifactRepositoryManager.class);
		URI source = getBuilder().getGlobalRepoURI();
		MonitorUtils.begin(monitor, 100);
		boolean artifactErrors = false;
		try {
			boolean isUpdate = getBuilder().isUpdate();
			URI[] trustedRepos = getBuilder().getTrustedContributionRepos();
			if (trustedRepos.length > 0) {
				mirrorDestination = new File(destination, Builder.REPO_FOLDER_MIRROR);
				mirrorURI = Builder.createURI(mirrorDestination);
			}

			IArtifactRepository mirrorAr = null;
			IMetadataRepository mirrorMdr = null;
			if (!isUpdate) {
				FileUtils.deleteAll(destination);
				mdrMgr.removeRepository(mirrorURI);
				arMgr.removeRepository(mirrorURI);
				MonitorUtils.worked(monitor, 2);
			} else {
				try {
					mirrorAr = arMgr.loadRepository(mirrorURI, MonitorUtils.subMonitor(monitor, 1));
				} catch (ProvisionException e) {
				}

				try {
					mirrorMdr = mdrMgr.loadRepository(mirrorURI, MonitorUtils.subMonitor(monitor, 1));
				} catch (ProvisionException e) {
				}
			}

			URI mirrors = getBuilder().getMirrorsURI();
			if (mirrorAr == null) {
				Map<String, String> properties = new HashMap<String, String>();
				properties.put(IRepository.PROP_COMPRESSED, Boolean.toString(true));
				properties.put(Publisher.PUBLISH_PACK_FILES_AS_SIBLINGS, Boolean.toString(true));
				if (mirrors != null)
					properties.put(IRepository.PROP_MIRRORS_URL, mirrors.toString());
				String label = getBuilder().getBuild().getLabel();
				mirrorAr = arMgr.createRepository(mirrorURI, label + " artifacts", Builder.SIMPLE_ARTIFACTS_TYPE, properties); //$NON-NLS-1$
			}

			if (mirrorMdr == null) {
				Map<String, String> properties = new HashMap<String, String>();
				properties.put(IRepository.PROP_COMPRESSED, Boolean.toString(true));
				if (mirrors != null)
					properties.put(IRepository.PROP_MIRRORS_URL, mirrors.toString());
				String label = getBuilder().getBuild().getLabel();
				mirrorMdr = mdrMgr.createRepository(mirrorURI, label, Builder.SIMPLE_METADATA_TYPE, properties);
			}

			// Step 1. Mirror all artifacts. This means copying a lot of data.
			// We mirror
			// one repository at a time to get a more informative error in case
			// of failure
			//
			IArtifactRepository sourceAr = arMgr.loadRepository(source, MonitorUtils.subMonitor(monitor, 1));
			List<URI> children = getCompositeChildren(sourceAr);
			IProgressMonitor childMonitor = MonitorUtils.subMonitor(monitor, 88);
			MonitorUtils.begin(childMonitor, children.size() * 100);

			Set<IInstallableUnit> unitsToInstall = getBuilder().getUnitsToInstall();
			HashSet<IArtifactKey> keysToInstall = new HashSet<IArtifactKey>(unitsToInstall.size());
			for (IInstallableUnit iu : unitsToInstall)
				for (IArtifactKey key : iu.getArtifacts())
					keysToInstall.add(key);

			URI categoryRepo = getBuilder().getCategoriesRepo();
			allRepos: for (URI childURI : children) {
				if (childURI.equals(categoryRepo))
					continue;

				for (URI trustedRepo : trustedRepos)
					if (childURI.equals(trustedRepo))
						continue allRepos;

				log.info("Mirroring artifacts from from %s", childURI);
				IArtifactRepository child = arMgr.loadRepository(childURI, MonitorUtils.subMonitor(childMonitor, 1));
				ArrayList<String> errors = new ArrayList<String>();
				mirror(child, mirrorAr, keysToInstall, errors, MonitorUtils.subMonitor(childMonitor, 99));
				if (errors.size() > 0) {
					artifactErrors = true;
					String childStr = childURI.toString();
					if (!childStr.endsWith("/"))
						childStr += "/";

					Contribution repoContributor = null;
					outer: for (Contribution contrib : getBuilder().getBuild().getContributions()) {
						for (Repository repo : contrib.getRepositories()) {
							String repoLoc = repo.getLocation();
							if (!repoLoc.endsWith("/"))
								repoLoc += "/";
							if (repoLoc.equals(childStr)) {
								repoContributor = contrib;
								break outer;
							}
						}
					}
					if (repoContributor != null)
						getBuilder().sendEmail(repoContributor, errors);

				}
			}
			log.info("Done mirroring artifacts");
			childMonitor.done();

			// Step 2. Mirror the composite but don't include platform nor
			// categories. We
			// mirror one repository at a time to get a more informative error
			// in case of
			// failure
			//
			IMetadataRepository sourceMdr = mdrMgr.loadRepository(source, MonitorUtils.subMonitor(monitor, 1));

			children = getCompositeChildren(sourceMdr);
			childMonitor = MonitorUtils.subMonitor(monitor, 7);
			MonitorUtils.begin(childMonitor, children.size() * 100);
			allRepos: for (URI childURI : children) {
				if (childURI.equals(categoryRepo))
					continue;

				for (URI trustedRepo : trustedRepos)
					if (childURI.equals(trustedRepo))
						continue allRepos;

				log.info("Mirroring meta-data from from %s", childURI);
				IMetadataRepository child = mdrMgr.loadRepository(childURI, MonitorUtils.subMonitor(childMonitor, 1));
				mirror(new IncludesQuery(unitsToInstall), child, mirrorMdr, MonitorUtils.subMonitor(childMonitor, 99));
			}
			childMonitor.done();

			// Step 3. Mirror generated content but don't include the
			// generated 'include all' feature
			log.info("Mirroring meta-data from from %s", categoryRepo);
			IMetadataRepository categoryRepository = mdrMgr.loadRepository(categoryRepo, MonitorUtils.subMonitor(monitor, 1));
			mirror(new AllButAllContributedFeature(), categoryRepository, mirrorMdr, MonitorUtils.subMonitor(monitor, 1));
			log.info("Done mirroring meta-data");

			FileUtils.deleteAll(new File(destination, "compositeContent.jar"));
			FileUtils.deleteAll(new File(destination, "compositeArtifacts.jar"));

			if (trustedRepos.length > 0) {
				// Set up the final composite repositories
				log.info("Building final composites at %s", finalURI);
				Map<String, String> properties = new HashMap<String, String>();
				properties.put(IRepository.PROP_COMPRESSED, Boolean.toString(true));

				String name = getBuilder().getBuild().getLabel();
				mdrMgr.removeRepository(finalURI);
				CompositeMetadataRepository compositeMdr = (CompositeMetadataRepository) mdrMgr.createRepository(finalURI, name,
						Builder.COMPOSITE_METADATA_TYPE, properties);

				arMgr.removeRepository(finalURI);
				CompositeArtifactRepository compositeAr = (CompositeArtifactRepository) arMgr.createRepository(finalURI,
						name + " artifacts", Builder.COMPOSITE_ARTIFACTS_TYPE, properties); //$NON-NLS-1$

				for (URI trustedURI : trustedRepos) {
					compositeMdr.addChild(trustedURI);
					compositeAr.addChild(trustedURI);
				}
				compositeMdr.addChild(finalURI.relativize(mirrorURI));
				compositeAr.addChild(finalURI.relativize(mirrorURI));
				log.info("Done building final composite");
			}
		} finally {
			bucky.ungetService(mdrMgr);
			bucky.ungetService(arMgr);
			MonitorUtils.done(monitor);
		}
		log.info("Done. Took %d ms", Long.valueOf(System.currentTimeMillis() - now));
		if (artifactErrors)
			throw BuckminsterException.fromMessage("Not all artifacts could be mirrored");
	}
}
