package org.eclipse.buckminster.core.materializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.ITargetPlatform;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.TargetPlatform;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.IComponentIdentifier;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.IMaterializationNode;
import org.eclipse.buckminster.core.mspec.IMaterializationSpec;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.P2ReaderType;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.equinox.internal.p2.artifact.repository.simple.SimpleArtifactDescriptor;
import org.eclipse.equinox.internal.p2.engine.InstallableUnitOperand;
import org.eclipse.equinox.internal.p2.engine.Phase;
import org.eclipse.equinox.internal.p2.engine.PhaseSet;
import org.eclipse.equinox.internal.p2.engine.ProvisioningPlan;
import org.eclipse.equinox.internal.p2.engine.phases.Collect;
import org.eclipse.equinox.internal.p2.metadata.ArtifactKey;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.engine.IEngine;
import org.eclipse.equinox.p2.engine.IProfile;
import org.eclipse.equinox.p2.engine.IProfileRegistry;
import org.eclipse.equinox.p2.engine.IProvisioningPlan;
import org.eclipse.equinox.p2.engine.ProvisioningContext;
import org.eclipse.equinox.p2.metadata.IArtifactKey;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepository;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepositoryManager;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.Constants;

@SuppressWarnings("restriction")
public class P2Materializer extends AbstractMaterializer {
	private static final String CLASSIFIER_OSGI_BUNDLE = "osgi.bundle"; //$NON-NLS-1$

	private static final String CLASSIFIER_ORG_ECLIPSE_UPDATE_FEATURE = "org.eclipse.update.feature"; //$NON-NLS-1$

	private static final String PROP_ARTIFACT_FOLDER = "artifact.folder"; //$NON-NLS-1$

	public static URI cleanURIFromImportType(URI repoLocation) {
		Map<String, String> props = URLUtils.queryAsParameters(repoLocation.getQuery());
		if (props.remove("importType") != null) //$NON-NLS-1$
			try {
				repoLocation = new URI(repoLocation.getScheme(), repoLocation.getAuthority(), repoLocation.getPath(),
						URLUtils.encodeFromQueryPairs(props), repoLocation.getFragment());
			} catch (URISyntaxException e) {
				throw new IllegalArgumentException(e);
			}
		return repoLocation;
	}

	static IArtifactRepository getArtifactRepository(IArtifactRepositoryManager manager, URI repoLocation, IProgressMonitor monitor)
			throws CoreException {
		SubMonitor subMon = SubMonitor.convert(monitor, 200);
		try {
			return manager.loadRepository(repoLocation, subMon.newChild(100));
		} catch (ProvisionException e) {
			return manager.refreshRepository(repoLocation, subMon.newChild(100));
		}
	}

	static IMetadataRepository getMetadataRepository(IMetadataRepositoryManager manager, URI repoLocation, IProgressMonitor monitor)
			throws CoreException {
		SubMonitor subMon = SubMonitor.convert(monitor, 200);
		try {
			return manager.loadRepository(repoLocation, subMon.newChild(100));
		} catch (ProvisionException e) {
			return manager.refreshRepository(repoLocation, subMon.newChild(100));
		}
	}

	@Override
	public boolean canWorkInParallel() {
		// Since we start and stop services
		//
		return false;
	}

	@Override
	public String getMaterializerRootDir() throws CoreException {
		ITargetPlatform tp = TargetPlatform.getInstance();
		File location = tp.getLocation();
		if (location == null) {
			// Create a default target platform under the buckminster folder
			//
			location = tp.getDefaultPlatformLocation(true);
			// bug 285449: throw exception if we cannot determine the target
			// location
			if (location == null)
				throw BuckminsterException.fromMessage(Messages.Unable_to_determine_platform_install_location);
		}
		return location.getAbsolutePath();
	}

	@Override
	public List<Materialization> materialize(List<Resolution> resolutions, MaterializationContext context, IProgressMonitor monitor)
			throws CoreException {
		Map<File, List<Resolution>> resPerLocation = new HashMap<File, List<Resolution>>();
		IMaterializationSpec mspec = context.getMaterializationSpec();

		IPath installRoot = mspec.getInstallLocation();
		if (installRoot == null)
			installRoot = Path.fromOSString(getMaterializerRootDir());
		else
			installRoot = Path.fromOSString(ExpandingProperties.expand(context, installRoot.toOSString(), 0));

		for (Resolution res : resolutions) {
			IMaterializationNode node = mspec.getMatchingNode(res);
			IPath installLocation = null;
			Map<String, ? extends Object> props = context.getProperties(res);
			if (node != null) {
				installLocation = node.getInstallLocation();
				if (installLocation != null) {
					installLocation = Path.fromOSString(ExpandingProperties.expand(props, installLocation.toOSString(), 0));
					if (!installLocation.isAbsolute())
						installLocation = installRoot.append(installLocation);
				}
			}

			if (installLocation == null)
				installLocation = installRoot;

			File locationKey = installLocation.toFile();
			List<Resolution> rss = resPerLocation.get(locationKey);
			if (rss == null) {
				rss = new ArrayList<Resolution>();
				resPerLocation.put(locationKey, rss);
			}
			rss.add(res);
		}

		SubMonitor subMon = SubMonitor.convert(monitor, 100 + resPerLocation.size() * 1000);

		IProvisioningAgent p2Agent = CorePlugin.getDefault().getResolverAgent();
		IMetadataRepositoryManager mdrManager = (IMetadataRepositoryManager) p2Agent.getService(IMetadataRepositoryManager.SERVICE_NAME);
		IArtifactRepositoryManager arManager = (IArtifactRepositoryManager) p2Agent.getService(IArtifactRepositoryManager.SERVICE_NAME);
		IEngine engine = (IEngine) p2Agent.getService(IEngine.SERVICE_NAME);
		IProfileRegistry registry = (IProfileRegistry) p2Agent.getService(IProfileRegistry.SERVICE_NAME);
		Map<URI, IMetadataRepository> knownMDRs = new HashMap<URI, IMetadataRepository>();
		Map<URI, IArtifactRepository> knownARs = new HashMap<URI, IArtifactRepository>();

		try {
			File file = FileUtils.getFile(Platform.getInstallLocation().getURL());
			URI runtimeAR = file.toURI();
			knownARs.put(runtimeAR, arManager.loadRepository(runtimeAR, subMon.newChild(100)));
		} catch (Exception e) {
			// Ignore
			CorePlugin.getLogger().warning(e, "Unable to load runtime repository: " + e.getMessage()); //$NON-NLS-1$
		}

		for (Map.Entry<File, List<Resolution>> entry : resPerLocation.entrySet()) {
			// ensure the user-specified artifact repos will be consulted by
			// loading them

			final File destDir = entry.getKey();

			// do a create here to ensure that we don't default to a #load later
			// and grab a repo which is the wrong
			// type
			// e.g. extension location type because a plugins/ directory exists.
			IArtifactRepository destAR;
			try {
				destAR = arManager.createRepository(destDir.toURI(), "Runnable repository.", //$NON-NLS-1$
						IArtifactRepositoryManager.TYPE_SIMPLE_REPOSITORY, null);
			} catch (ProvisionException e) {
				// ignore... perhaps one already exists and we will just load it
				// later
				destAR = arManager.loadRepository(destDir.toURI(), null);
			}

			List<Resolution> ress = entry.getValue();
			List<IInstallableUnit> ius = new ArrayList<IInstallableUnit>(ress.size());
			for (Resolution res : ress) {
				SubMonitor subSubMon = subMon.newChild(800 / ress.size());
				subSubMon.setWorkRemaining(1000);

				IComponentIdentifier cid = res.getComponentIdentifier();
				Version version = Version.create(cid.getVersion().toString());
				URI repoURI = cleanURIFromImportType(URI.create(res.getRepository()));
				String path = repoURI.getPath();
				if (path.endsWith(".jar")) //$NON-NLS-1$
				{
					// This is a direct pointer to an artifact, not a repository
					//
					fetchP2object(context, destDir, destAR, res, subSubMon, cid, version);
					continue;
				}

				// Try URI as a P2 repository
				IMetadataRepository mdr = knownMDRs.get(repoURI);
				if (mdr == null) {
					try {
						mdr = getMetadataRepository(mdrManager, repoURI, subSubMon.newChild(500));
						knownMDRs.put(repoURI, mdr);
					} catch (ProvisionException pe) {
						if (ProvisionException.REPOSITORY_NOT_FOUND != pe.getStatus().getCode())
							throw pe;

						// URI is not a p2 repository
						fetchP2object(context, destDir, destAR, res, subSubMon, cid, version);
						continue;
					}
				}

				VersionRange range = new VersionRange(version, true, version, true);
				String name = cid.getName();
				boolean isFeature = IComponentType.ECLIPSE_FEATURE.equals(cid.getComponentTypeID());

				if (isFeature)
					// Since this is what we want in the target platform
					name = name + ".feature.jar"; //$NON-NLS-1$

				IQueryResult<IInstallableUnit> result = mdr.query(QueryUtil.createIUQuery(name, range), subSubMon.newChild(250));
				Iterator<IInstallableUnit> itor = result.iterator();
				if (!itor.hasNext())
					throw new ProvisionException(NLS.bind(Messages.Unable_to_resolve_0_1_in_MDR_2,
							new Object[] { cid.getName(), version, res.getRepository() }));

				IInstallableUnit iu = itor.next();
				ius.add(iu);

				// Check if this IU has artifacts and if so, load the artifact
				// repository
				//
				if (iu.getArtifacts().size() > 0) {
					IArtifactRepository ar = knownARs.get(repoURI);
					if (ar == null) {
						ar = getArtifactRepository(arManager, repoURI, subSubMon.newChild(250));
						knownARs.put(repoURI, ar);
					}
				} else
					subSubMon.worked(250);
			}

			IArtifactRepository tempAr = P2ReaderType.getTempAR(subMon.newChild(1));
			knownARs.put(tempAr.getLocation(), tempAr);

			// create the operands from the list of IUs
			InstallableUnitOperand[] operands = new InstallableUnitOperand[ius.size()];
			int i = 0;
			for (IInstallableUnit iu : ius)
				operands[i++] = new InstallableUnitOperand(null, iu);

			// call the engine with only the "collect" phase so all we do is
			// download

			String destDirStr = destDir.toString();
			if (!registry.containsProfile(destDirStr)) {
				Map<String, String> properties = new HashMap<String, String>();
				properties.put(IProfile.PROP_SHARED_CACHE, Boolean.toString(false));
				properties.put(IProfile.PROP_INSTALL_FEATURES, Boolean.toString(true));
				properties.put(IProfile.PROP_CACHE, destDirStr);
				properties.put(IProfile.PROP_INSTALL_FOLDER, destDirStr);
				registry.addProfile(destDirStr, properties);
			}

			IProfile profile = registry.getProfile(destDirStr);
			try {
				PhaseSet phaseSet = new PhaseSet(new Phase[] { new Collect(100) }) { /*
																					 * nothing
																					 * to
																					 * override
																					 */
				};
				Set<URI> mdrURIs = knownMDRs.keySet();
				Set<URI> arURIs = knownARs.keySet();
				ProvisioningContext pctx = new ProvisioningContext(p2Agent);
				pctx.setMetadataRepositories(mdrURIs.toArray(new URI[mdrURIs.size()]));
				pctx.setArtifactRepositories(arURIs.toArray(new URI[arURIs.size()]));
				IProvisioningPlan plan = new ProvisioningPlan(profile, operands, pctx);
				IStatus status = engine.perform(plan, phaseSet, subMon.newChild(200));
				if (status.getSeverity() == IStatus.ERROR)
					throw BuckminsterException.wrap(status);
			} finally {
				registry.removeProfile(profile.getProfileId());
			}

			// The resource holding the target archive must be refreshed (if
			// indeed, it is a resource at all)
			IContainer[] destConts = ResourcesPlugin.getWorkspace().getRoot().findContainersForLocationURI(destDir.toURI());
			if (destConts != null && destConts.length > 0) {
				for (IContainer destCont : destConts) {
					IProject project = destCont.getProject();
					if (project.isOpen())
						project.refreshLocal(IResource.DEPTH_INFINITE, subMon.newChild(1));
				}
			}
		}
		TargetPlatform.getInstance().locationsChanged(resPerLocation.keySet());
		return Collections.emptyList();
	}

	private void convertSourceJar(IComponentIdentifier cid, File bundleJar, Manifest mf) throws IOException {
		File tempRoot = bundleJar.getParentFile();
		File outFile = null;
		ZipOutputStream out = null;
		InputStream in = new FileInputStream(bundleJar);
		try {
			String name = cid.getName();
			String binName = name.substring(0, name.length() - 7);
			String strVer = cid.getVersion().toString();
			if (mf == null)
				mf = new Manifest();
			Attributes attrs = mf.getMainAttributes();
			attrs.putValue(Constants.BUNDLE_SYMBOLICNAME, name);
			attrs.putValue(Constants.BUNDLE_NAME, "Source for " + binName); //$NON-NLS-1$
			attrs.putValue(Constants.BUNDLE_VERSION, strVer);
			attrs.putValue(Constants.BUNDLE_MANIFESTVERSION, "2"); //$NON-NLS-1$
			attrs.putValue("Eclipse-SourceBundle", binName + ";version=" + strVer); //$NON-NLS-1$//$NON-NLS-2$

			outFile = File.createTempFile("newbundle-", ".jar", tempRoot); //$NON-NLS-1$//$NON-NLS-2$
			out = new ZipOutputStream(new FileOutputStream(outFile));
			ZipEntry ze = new ZipEntry("META-INF/"); //$NON-NLS-1$
			out.putNextEntry(ze);
			ze = new ZipEntry("META-INF/MANIFEST.MF"); //$NON-NLS-1$
			out.putNextEntry(ze);
			mf.write(out);

			ZipInputStream zin = new ZipInputStream(in);
			while ((ze = zin.getNextEntry()) != null) {
				if ("META-INF/".equals(ze.getName()) || "META-INF/MANIFEST.MF".equals(ze.getName())) //$NON-NLS-1$//$NON-NLS-2$
					continue;
				out.putNextEntry(ze);
				if (!ze.isDirectory())
					IOUtils.copy(zin, out, null);
			}
		} finally {
			IOUtils.close(in);
			IOUtils.close(out);
		}
		File tmpRename = File.createTempFile("oldbundle-", ".jar", tempRoot); //$NON-NLS-1$//$NON-NLS-2$;
		if (bundleJar.renameTo(tmpRename)) {
			if (outFile.renameTo(bundleJar))
				tmpRename.delete();
			else {
				// Attempt to roll back
				tmpRename.renameTo(bundleJar);
				throw new IOException("Unable to rename " + outFile.getAbsolutePath() + " to " + bundleJar.getAbsolutePath()); //$NON-NLS-1$ //$NON-NLS-2$
			}
		} else
			throw new IOException("Unable to rename " + bundleJar.getAbsolutePath() + " to " + tmpRename.getAbsolutePath()); //$NON-NLS-1$//$NON-NLS-2$
	}

	private void fetchP2object(MaterializationContext context, File destDir, IArtifactRepository destAR, Resolution res, SubMonitor subSubMon,
			IComponentIdentifier cid, Version version) throws CoreException {

		IArtifactKey aKey;
		if (IComponentType.ECLIPSE_FEATURE.equals(cid.getComponentTypeID()))
			aKey = new ArtifactKey(CLASSIFIER_ORG_ECLIPSE_UPDATE_FEATURE, cid.getName(), version);
		else
			aKey = new ArtifactKey(CLASSIFIER_OSGI_BUNDLE, cid.getName(), version);

		if (destAR.contains(aKey))
			return;

		IComponentType ctype = CorePlugin.getDefault().getComponentType(cid.getComponentTypeID());
		IPath location = Path.fromOSString(destDir.getAbsolutePath());
		IPath ctypeRelative = ctype.getRelativeLocation();
		if (ctypeRelative != null)
			location = location.append(ctypeRelative);
		location.toFile().mkdirs();

		String leafName = cid.getName() + '_' + cid.getVersion();
		if (res.isUnpack()) {
			location = location.append(leafName);
			location = location.addTrailingSeparator();
		} else
			location = location.append(leafName + ".jar"); //$NON-NLS-1$

		IReaderType readerType = CorePlugin.getDefault().getReaderType(res.getReaderTypeId());
		IComponentReader reader = readerType.getReader(res, context, subSubMon.newChild(10));
		try {
			reader.materialize(location, res, context, subSubMon.newChild(500));
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				throw BuckminsterException.wrap(e);
			}
		}

		if (cid.getName().endsWith(".source") && IComponentType.OSGI_BUNDLE.equals(cid.getComponentTypeID()) && !res.isUnpack()) { //$NON-NLS-1$
			// It is not unlikely that we have downloaded a source bundle from a
			// maven repository at this point. They often lack the some settings
			// required by Eclipse in their manifest. If that's the case, then
			// we can fix it here so that source lookups will work in the IDE.
			Object convertSource = context.get("buckminster.convert.source"); //$NON-NLS-1$
			if (convertSource != null && "true".equalsIgnoreCase(convertSource.toString())) { //$NON-NLS-1$
				try {
					File bundleJar = location.toFile();
					Manifest mf = null;
					JarFile jar = null;
					try {
						jar = new JarFile(bundleJar);
						mf = jar.getManifest();
					} finally {
						if (jar != null) {
							try {
								jar.close();
							} catch (IOException e) {
							}
						}
					}
					if (mf == null || mf.getMainAttributes().getValue(Constants.BUNDLE_SYMBOLICNAME) == null) {
						convertSourceJar(cid, bundleJar, mf);
					}
				} catch (Exception e) {
					// Not fatal but a warning is appropriate
					CorePlugin.getLogger().warning(e.getMessage(), e);
				}
			}
		}

		SimpleArtifactDescriptor desc = new SimpleArtifactDescriptor(aKey);
		if (IComponentType.ECLIPSE_FEATURE.equals(cid.getComponentTypeID()) || res.isUnpack())
			desc.addRepositoryProperties(Collections.singletonMap(PROP_ARTIFACT_FOLDER, Boolean.toString(true)));
		destAR.addDescriptor(desc);
	}
}
