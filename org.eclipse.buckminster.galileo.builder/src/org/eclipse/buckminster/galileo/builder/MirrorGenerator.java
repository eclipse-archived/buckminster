package org.eclipse.buckminster.galileo.builder;

import java.io.File;
import java.lang.reflect.Method;
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
import org.eclipse.buckminster.galileo.builder.Builder.PackedStrategy;
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
import org.eclipse.equinox.internal.p2.director.PermissiveSlicer;
import org.eclipse.equinox.internal.p2.metadata.repository.CompositeMetadataRepository;
import org.eclipse.equinox.internal.p2.metadata.repository.LocalMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.ArtifactDescriptor;
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
import org.eclipse.equinox.internal.provisional.spi.p2.metadata.repository.RepositoryReference;
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

	private static final Method LocalMetadataRepository_createRepositoriesSnapshot;

	static {
		try {
			LocalMetadataRepository_createRepositoriesSnapshot = LocalMetadataRepository.class.getDeclaredMethod("createRepositoriesSnapshot");
			LocalMetadataRepository_createRepositoriesSnapshot.setAccessible(true);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	private static IStatus extractDeeperRootCause(IStatus status) {
		if (status == null)
			return null;

		if (status.isMultiStatus()) {
			IStatus[] children = ((MultiStatus) status).getChildren();
			for (int i = 0; i < children.length; i++) {
				IStatus deeper = extractDeeperRootCause(children[i]);
				if (deeper != null)
					return deeper;
			}
		}

		Throwable t = status.getException();
		if (t instanceof CoreException) {
			IStatus deeper = extractDeeperRootCause(((CoreException) t).getStatus());
			if (deeper != null)
				return deeper;
		}
		return status.getSeverity() == IStatus.ERROR ? status : null;
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

	@SuppressWarnings("unchecked")
	private static List<RepositoryReference> getRepositoryReferences(LocalMetadataRepository localMdr) throws CoreException {
		try {
			return (List<RepositoryReference>) LocalMetadataRepository_createRepositoriesSnapshot.invoke(localMdr);
		} catch (Exception e) {
			throw BuckminsterException.wrap(e);
		}
	}

	private static void mirror(IArtifactRepository source, IArtifactRepository dest, IArtifactDescriptor sourceDesc, IArtifactDescriptor targetDesc,
			IProgressMonitor monitor) throws CoreException {
		if (dest.contains(sourceDesc))
			return;

		RawMirrorRequest request = new RawMirrorRequest(sourceDesc, targetDesc, dest);
		request.setSourceRepository(source);
		request.perform(monitor);
		IStatus result = request.getResult();
		if (result.getSeverity() == IStatus.ERROR) {
			if (result.getCode() != org.eclipse.equinox.internal.provisional.p2.core.ProvisionException.ARTIFACT_EXISTS) {
				dest.removeDescriptor(sourceDesc);
				result = extractRootCause(result);
				throw BuckminsterException.fromMessage(result.getException(), "Unable to mirror artifact %s from repository %s: %s", sourceDesc
						.getArtifactKey(), source.getLocation(), result.getMessage());
			}
		}
	}

	private static void unpackToSibling(IArtifactRepository target, IArtifactDescriptor optimized, IArtifactDescriptor canonical, boolean verifyOnly,
			IProgressMonitor monitor) throws CoreException {
		CanonicalizeRequest request = new CanonicalizeRequest(optimized, canonical, target);
		request.perform(monitor);
		IStatus result = request.getResult();
		if (result.getSeverity() != IStatus.ERROR
				|| result.getCode() == org.eclipse.equinox.internal.provisional.p2.core.ProvisionException.ARTIFACT_EXISTS) {
			if (verifyOnly)
				target.removeDescriptor(canonical);
			return;
		}

		result = extractRootCause(result);
		target.removeDescriptor(canonical);
		throw BuckminsterException.fromMessage(result.getException(), "Unable to unpack artifact %s in repository %s: %s",
				optimized.getArtifactKey(), target.getLocation(), result.getMessage());
	}

	private IMetadataRepositoryManager mdrMgr = null;

	private IArtifactRepositoryManager arMgr = null;

	public MirrorGenerator(Builder builder) {
		super(builder);
	}

	private List<RepositoryReference> getRepositoryReferences(IMetadataRepository mdr) throws CoreException {
		if (mdr instanceof LocalMetadataRepository)
			return getRepositoryReferences((LocalMetadataRepository) mdr);

		if (mdr instanceof CompositeMetadataRepository) {
			HashSet<RepositoryReference> compositeRefs = new HashSet<RepositoryReference>();
			for (URI child : getCompositeChildren(mdr))
				compositeRefs.addAll(getRepositoryReferences(mdrMgr.loadRepository(child, null)));
			if (compositeRefs.size() > 0)
				return new ArrayList<RepositoryReference>(compositeRefs);
		}

		return Collections.emptyList();
	}

	private void mirror(IArtifactRepository source, IArtifactRepository dest, Set<IArtifactKey> keysToInstall, List<String> errors,
			IProgressMonitor monitor) {
		Logger log = Buckminster.getLogger();
		IArtifactKey[] keys = source.getArtifactKeys();
		MonitorUtils.begin(monitor, keys.length * 100);

		for (IArtifactKey key : keys) {
			if (!keysToInstall.contains(key))
				continue;

			log.info("- mirroring artifact %s", key);

			PackedStrategy strategy = getBuilder().getPackedStrategy();
			if (!"osgi.bundle".equals(key.getClassifier()))
				strategy = PackedStrategy.SKIP;

			try {
				IArtifactDescriptor[] aDescs = source.getArtifactDescriptors(key);
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

				if (optimized == null && canonical == null)
					throw BuckminsterException.fromMessage("Found no usable descriptor for artifact %s in repository %s", key, dest.getLocation());

				if (optimized == null) {
					log.debug("    doing copy of canonical artifact");
					mirror(source, dest, canonical, canonical, MonitorUtils.subMonitor(monitor, 90));
					continue;
				}

				switch (strategy) {
					case SKIP:
						if (canonical == null)
							// Canonical is required
							throw BuckminsterException.fromMessage("No canonical artifact %s found in repository %s", key, dest.getLocation());
						log.debug("    doing copy of canonical artifact");
						mirror(source, dest, canonical, canonical, MonitorUtils.subMonitor(monitor, 90));
					case COPY:
						log.debug("    doing copy of optimized artifact");
						mirror(source, dest, optimized, optimized, MonitorUtils.subMonitor(monitor, 90));
						break;
					default:
						// We need a canonical descriptor to complete this.
						if (canonical == null) {
							ArtifactDescriptor ad = new ArtifactDescriptor(key);
							ad.setRepository(dest);
							canonical = ad;
						}
						if (strategy == PackedStrategy.UNPACK) {
							log.debug("    doing copy of optimized artifact into canonical target");
							mirror(source, dest, optimized, canonical, MonitorUtils.subMonitor(monitor, 90));
						} else {
							log.debug("    doing copy of optimized artifact");
							mirror(source, dest, optimized, optimized, MonitorUtils.subMonitor(monitor, 70));
							boolean isVerify = strategy == PackedStrategy.VERIFY;
							log.debug("    unpacking optimized artifact%s", isVerify ? " for verification" : "");
							unpackToSibling(dest, optimized, canonical, isVerify, MonitorUtils.subMonitor(monitor, 20));
						}
				}
			} catch (CoreException e) {
				errors.add(Builder.getExceptionMessages(e));
				Buckminster.getLogger().error(e, e.getMessage());
			}
		}
		MonitorUtils.done(monitor);
	}

	private void mirror(Query filter, IMetadataRepository source, final IMetadataRepository dest, IProgressMonitor monitor) throws CoreException {
		Collector allIUs = source.query(filter, new Collector(), monitor);
		dest.addInstallableUnits((IInstallableUnit[]) allIUs.toArray(IInstallableUnit.class));

		Builder builder = getBuilder();
		if (builder.isMirrorReferences()) {
			Logger log = Buckminster.getLogger();
			for (RepositoryReference ref : getRepositoryReferences(source)) {
				URI location = ref.Location;
				String refKey = location.toString();
				String refType = ref.Type == IRepository.TYPE_METADATA ? "meta-data" : "artifacts";
				if (!builder.isMatchedReference(refKey)) {
					log.debug("- %s reference %s was ruled out by inclusion/exclusion patterns", refType, refKey);
					continue;
				}

				if (refKey.endsWith("/site.xml"))
					location = URI.create(refKey.substring(0, refKey.length() - 8));

				log.debug("- mirroring %s reference %s", refType, refKey);
				dest.addReference(location, ref.Nickname, ref.Type, 0);
			}
		}
	}

	@Override
	public void run(IProgressMonitor monitor) throws CoreException {
		Logger log = Buckminster.getLogger();
		log.info("Starting mirror generation");
		long now = System.currentTimeMillis();

		Builder builder = getBuilder();
		File destination = new File(builder.getBuildRoot(), Builder.REPO_FOLDER_FINAL);
		URI finalURI = Builder.createURI(destination);

		File aggregateDestination = destination;
		URI aggregateURI = finalURI;

		Buckminster bucky = Buckminster.getDefault();

		mdrMgr = bucky.getService(IMetadataRepositoryManager.class);
		arMgr = bucky.getService(IArtifactRepositoryManager.class);
		URI source = builder.getGlobalRepoURI();
		MonitorUtils.begin(monitor, 100);
		boolean artifactErrors = false;
		try {
			boolean isUpdate = builder.isUpdate();
			URI[] trustedRepos = builder.getTrustedContributionRepos();
			if (trustedRepos.length > 0) {
				aggregateDestination = new File(destination, Builder.REPO_FOLDER_AGGREGATE);
				aggregateURI = Builder.createURI(aggregateDestination);
			}

			IArtifactRepository aggregateAr = null;
			IMetadataRepository finalMdr = null;
			if (!isUpdate) {
				FileUtils.deleteAll(destination);
				mdrMgr.removeRepository(finalURI);
				arMgr.removeRepository(aggregateURI);
				MonitorUtils.worked(monitor, 2);
			} else {
				try {
					aggregateAr = arMgr.loadRepository(aggregateURI, MonitorUtils.subMonitor(monitor, 1));
				} catch (ProvisionException e) {
				}

				try {
					finalMdr = mdrMgr.loadRepository(finalURI, MonitorUtils.subMonitor(monitor, 1));
				} catch (ProvisionException e) {
				}
			}

			URI mirrors = builder.getMirrorsURI(false);
			if (aggregateAr == null) {
				Map<String, String> properties = new HashMap<String, String>();
				properties.put(IRepository.PROP_COMPRESSED, Boolean.toString(true));
				properties.put(Publisher.PUBLISH_PACK_FILES_AS_SIBLINGS, Boolean.toString(true));
				if (mirrors != null) {
					URI artifactMirrors = trustedRepos.length > 0 ? builder.getMirrorsURI(true) : mirrors;
					properties.put(IRepository.PROP_MIRRORS_URL, artifactMirrors.toString());
				}
				String label = builder.getBuild().getLabel();
				aggregateAr = arMgr.createRepository(aggregateURI, label + " artifacts", Builder.SIMPLE_ARTIFACTS_TYPE, properties); //$NON-NLS-1$
			}

			if (finalMdr == null) {
				Map<String, String> properties = new HashMap<String, String>();
				properties.put(IRepository.PROP_COMPRESSED, Boolean.toString(true));
				if (mirrors != null)
					properties.put(IRepository.PROP_MIRRORS_URL, mirrors.toString());
				String label = builder.getBuild().getLabel();
				finalMdr = mdrMgr.createRepository(finalURI, label, Builder.SIMPLE_METADATA_TYPE, properties);
			}

			IMetadataRepository sourceMdr = mdrMgr.loadRepository(source, MonitorUtils.subMonitor(monitor, 1));
			IArtifactRepository sourceAr = arMgr.loadRepository(source, MonitorUtils.subMonitor(monitor, 1));

			final Set<IInstallableUnit> unitsToInstall = builder.getUnitsToAggregate();

			// Add the transitive closure of all unverified roots
			//
			Set<IInstallableUnit> unverifiedRoots = builder.getUnverifiedUnits();
			if (!unverifiedRoots.isEmpty()) {
				PermissiveSlicer slicer = new PermissiveSlicer(sourceMdr, null, true, false, true, false, false);
				IQueryable slice = slicer.slice(unverifiedRoots.toArray(new IInstallableUnit[unverifiedRoots.size()]), MonitorUtils.subMonitor(
						monitor, 1));

				Query adder = new MatchQuery() {
					@Override
					public boolean isMatch(Object candidate) {
						unitsToInstall.add((IInstallableUnit) candidate);
						return false;
					}
				};
				slice.query(adder, new Collector(), MonitorUtils.subMonitor(monitor, 1));
			}

			URI categoryRepo = builder.getCategoriesRepo();
			List<URI> children = getCompositeChildren(sourceMdr);
			IProgressMonitor childMonitor = MonitorUtils.subMonitor(monitor, 7);
			MonitorUtils.begin(childMonitor, (trustedRepos.length + children.size()) * 100);
			for (URI childURI : children) {
				if (childURI.equals(categoryRepo))
					continue;

				log.info("Mirroring meta-data from from %s", childURI);
				IMetadataRepository child = mdrMgr.loadRepository(childURI, MonitorUtils.subMonitor(childMonitor, 1));
				mirror(new IncludesQuery(unitsToInstall), child, finalMdr, MonitorUtils.subMonitor(childMonitor, 99));
			}

			Set<IInstallableUnit> trustedUnits = builder.getTrustedUnits();
			if (trustedUnits.size() > 0) {
				for (URI trustedRepo : trustedRepos) {
					log.info("Mirroring meta-data from from %s", trustedRepo);
					IMetadataRepository child = mdrMgr.loadRepository(trustedRepo, MonitorUtils.subMonitor(childMonitor, 1));
					mirror(new IncludesQuery(trustedUnits), child, finalMdr, MonitorUtils.subMonitor(childMonitor, 99));
				}
			}
			childMonitor.done();

			IMetadataRepository categoryRepository = mdrMgr.loadRepository(categoryRepo, MonitorUtils.subMonitor(monitor, 1));
			mirror(new AllButAllContributedFeature(), categoryRepository, finalMdr, MonitorUtils.subMonitor(monitor, 1));
			log.info("Done mirroring meta-data");

			HashSet<IArtifactKey> keysToInstall = new HashSet<IArtifactKey>(unitsToInstall.size());
			for (IInstallableUnit iu : unitsToInstall)
				for (IArtifactKey key : iu.getArtifacts())
					keysToInstall.add(key);

			children = getCompositeChildren(sourceAr);
			childMonitor = MonitorUtils.subMonitor(monitor, 88);
			MonitorUtils.begin(childMonitor, (children.size()) * 100);
			allRepos: for (URI childURI : children) {
				if (childURI.equals(categoryRepo))
					continue;

				for (URI trustedRepo : trustedRepos)
					if (childURI.equals(trustedRepo))
						continue allRepos;

				log.info("Mirroring artifacts from from %s", childURI);
				IArtifactRepository child = arMgr.loadRepository(childURI, MonitorUtils.subMonitor(childMonitor, 1));
				ArrayList<String> errors = new ArrayList<String>();
				mirror(child, aggregateAr, keysToInstall, errors, MonitorUtils.subMonitor(childMonitor, 99));
				if (errors.size() > 0) {
					artifactErrors = true;
					String childStr = childURI.toString();
					if (!childStr.endsWith("/"))
						childStr += "/";

					Contribution repoContributor = null;
					outer: for (Contribution contrib : builder.getBuild().getContributions()) {
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
						builder.sendEmail(repoContributor, errors);

				}
			}
			log.info("Done mirroring artifacts");
			childMonitor.done();

			FileUtils.deleteAll(new File(destination, "compositeArtifacts.jar"));
			if (trustedRepos.length > 0) {
				// Set up the final composite repositories
				log.info("Building final artifact composite at %s", finalURI);
				Map<String, String> properties = new HashMap<String, String>();
				properties.put(IRepository.PROP_COMPRESSED, Boolean.toString(true));

				String name = builder.getBuild().getLabel();
				arMgr.removeRepository(finalURI);
				CompositeArtifactRepository compositeAr = (CompositeArtifactRepository) arMgr.createRepository(finalURI,
						name + " artifacts", Builder.COMPOSITE_ARTIFACTS_TYPE, properties); //$NON-NLS-1$

				for (URI trustedURI : trustedRepos)
					compositeAr.addChild(trustedURI);
				compositeAr.addChild(finalURI.relativize(aggregateURI));

				log.info("Done building final artifact composite");
			}
		} finally {
			bucky.ungetService(mdrMgr);
			mdrMgr = null;
			bucky.ungetService(arMgr);
			arMgr = null;
			MonitorUtils.done(monitor);
		}
		log.info("Done. Took %d ms", Long.valueOf(System.currentTimeMillis() - now));
		if (artifactErrors)
			throw BuckminsterException.fromMessage("Not all artifacts could be mirrored");
	}
}
