/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 * 
 * Contributors:
 * Lorenzo Bettini - https://bugs.eclipse.org/bugs/show_bug.cgi?id=428301
 *******************************************************************************/
package org.eclipse.buckminster.pde.internal;

import static org.eclipse.buckminster.core.helpers.MapUtils.getString;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.IComponentRequest;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.materializer.IMaterializer;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.ConflictResolution;
import org.eclipse.buckminster.core.reader.CatalogReaderType;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.reader.P2ReaderType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.pde.PDEPlugin;
import org.eclipse.buckminster.pde.internal.EclipseImportBase.Key;
import org.eclipse.buckminster.pde.internal.imports.PluginImportOperation;
import org.eclipse.buckminster.pde.mapfile.MapFile;
import org.eclipse.buckminster.pde.mapfile.MapFileEntry;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.equinox.internal.p2.artifact.repository.ArtifactRequest;
import org.eclipse.equinox.internal.p2.repository.Transport;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.processing.ProcessingStepHandler;
import org.eclipse.equinox.p2.metadata.IArtifactKey;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.repository.artifact.IArtifactDescriptor;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepository;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRequest;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.PDEState;
import org.eclipse.pde.internal.core.feature.ExternalFeatureModel;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.osgi.framework.Constants;

@SuppressWarnings({ "restriction" })
public class EclipseImportReaderType extends CatalogReaderType implements IPDEConstants {
	/**
	 * A request to mirror (copy) an artifact into a given destination artifact
	 * repository.
	 */
	public static class CopyRequest extends ArtifactRequest {
		private final File destination;

		public CopyRequest(IArtifactKey key, Transport transport, File destination) {
			super(key, transport);
			this.destination = destination;
		}

		@Override
		public void perform(IArtifactRepository sourceRepository, IProgressMonitor monitor) {
			monitor.subTask(NLS.bind(Messages.downloading_0, getArtifactKey().getId()));
			setSourceRepository(sourceRepository);

			// if the request does not have a descriptor then try to fill one in
			// by getting
			// the list of all and randomly picking one that appears to be
			// optimized.
			IArtifactDescriptor optimized = null;
			IArtifactDescriptor canonical = null;
			IArtifactDescriptor descriptor = null;

			IArtifactDescriptor[] descriptors = source.getArtifactDescriptors(getArtifactKey());
			if (descriptors.length > 0) {
				for (int i = 0; i < descriptors.length; i++) {
					if (descriptors[i].getProperty(IArtifactDescriptor.FORMAT) == null)
						canonical = descriptors[i];
					else if (ProcessingStepHandler.canProcess(descriptors[i]))
						optimized = descriptors[i];
				}

				boolean chooseCanonical = source.getLocation().equals("file"); //$NON-NLS-1$
				// If the source repo is local then look for a canonical
				// descriptor so we don't waste processing
				// time.
				descriptor = chooseCanonical ? canonical : optimized;

				// if the descriptor is still null then we could not find our
				// first choice of format so switch the
				// logic.
				if (descriptor == null)
					descriptor = !chooseCanonical ? canonical : optimized;
			}

			// if the descriptor is not set now then the repo does not have the
			// requested artifact
			if (descriptor == null) {
				setResult(new Status(IStatus.ERROR, PDEPlugin.getPluginId(), NLS.bind(Messages.artifact_not_found_0, getArtifactKey())));
				return;
			}

			IStatus status = transfer(descriptor, monitor);
			if (monitor.isCanceled()) {
				setResult(Status.CANCEL_STATUS);
				return;
			}

			if (status.isOK() || status.getSeverity() == IStatus.CANCEL) {
				setResult(status);
				return;
			}

			if (descriptor == canonical || canonical == null) {
				setResult(status);
				return;
			}

			// try with canonical
			setResult(transfer(canonical, monitor));
		}

		private IStatus transfer(IArtifactDescriptor descriptor, IProgressMonitor monitor) {
			IStatus status;
			do {
				status = transferSingle(descriptor, monitor);
			} while (status.getSeverity() == IStatus.ERROR && status.getCode() == IArtifactRepository.CODE_RETRY);
			return status;
		}

		private IStatus transferSingle(IArtifactDescriptor descriptor, IProgressMonitor monitor) {
			OutputStream output = null;
			IStatus status = null;
			try {
				output = new FileOutputStream(destination);
				status = source.getArtifact(descriptor, output, monitor);
			} catch (IOException e) {
				status = BuckminsterException.createStatus(e);
			} finally {
				IOUtils.close(output);
			}
			return status;
		}
	}

	private static final UUID CACHE_KEY_SITE_CACHE = UUID.randomUUID();

	public static File getTempSite(Map<UUID, Object> ucache) throws CoreException {
		Map<String, File> siteCache = getSiteCache(ucache);
		synchronized (siteCache) {
			String key = EclipseImportReaderType.class.getSimpleName() + ":tempSite"; //$NON-NLS-1$
			File tempSite = siteCache.get(key);
			if (tempSite != null)
				return tempSite;

			tempSite = FileUtils.createTempFolder("bmsite", ".tmp"); //$NON-NLS-1$ //$NON-NLS-2$
			new File(tempSite, PLUGINS_FOLDER).mkdir();
			new File(tempSite, FEATURES_FOLDER).mkdir();
			siteCache.put(key, tempSite);
			return tempSite;
		}
	}

	static URL createRemoteComponentURL(URL remoteLocation, IConnectContext cctx, ComponentIdentifier cid, String subDir) throws CoreException {
		if (remoteLocation.getPath().endsWith(".jar")) //$NON-NLS-1$
			return remoteLocation;

		if (remoteLocation.getPath().endsWith(".map")) //$NON-NLS-1$
		{
			for (MapFileEntry entry : getMapEntries(remoteLocation, cctx)) {
				ComponentIdentifier entryCid = entry.getComponentIdentifier();
				if (!(entryCid.getName().equals(cid.getName()) && entryCid.getComponentTypeID().equals(cid.getComponentTypeID())))
					continue;

				if (VersionHelper.equalsUnqualified(cid.getVersion(), entryCid.getVersion()))
					try {
						return new URL(getString(entry.getProperties(), "src")); //$NON-NLS-1$
					} catch (MalformedURLException e) {
						// Just skip
					}
			}
			throw BuckminsterException.fromMessage(NLS.bind(Messages.unable_to_find_0_in_map_1, cid.getName(), remoteLocation));
		}
		try {
			return new URL(remoteLocation, subDir + '/' + cid.getName() + '_' + cid.getVersion() + ".jar"); //$NON-NLS-1$
		} catch (MalformedURLException e) {
			throw BuckminsterException.wrap(e);
		}
	}

	static List<MapFileEntry> getMapEntries(URL location, IConnectContext cctx) throws CoreException {
		InputStream input = null;
		try {
			ArrayList<MapFileEntry> mapEntries = new ArrayList<MapFileEntry>();
			input = DownloadManager.read(location, cctx);
			MapFile.parse(input, location.toString(), null, null, mapEntries);
			ArrayList<MapFileEntry> binaryEntries = new ArrayList<MapFileEntry>();
			for (MapFileEntry entry : mapEntries) {
				if (!IReaderType.URL.equals(entry.getReaderType().getId()))
					continue;

				Map<String, Object> props = entry.getProperties();
				String src = getString(props, "src"); //$NON-NLS-1$
				if (src == null || !(src.endsWith(".jar") || src.endsWith(".zip"))) //$NON-NLS-1$ //$NON-NLS-2$
					continue;

				binaryEntries.add(entry);
			}
			return binaryEntries;
		} catch (FileNotFoundException e) {
			return Collections.emptyList();
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			IOUtils.close(input);
		}
	}

	@SuppressWarnings("unchecked")
	static Map<String, File> getSiteCache(Map<UUID, Object> ctxUserCache) {
		synchronized (ctxUserCache) {
			Map<String, File> siteCache = (Map<String, File>) ctxUserCache.get(CACHE_KEY_SITE_CACHE);
			if (siteCache == null) {
				siteCache = Collections.synchronizedMap(new HashMap<String, File>());
				ctxUserCache.put(CACHE_KEY_SITE_CACHE, siteCache);
			}
			return siteCache;
		}
	}

	private final Map<IProject, IClasspathEntry[]> classpaths = new HashMap<IProject, IClasspathEntry[]>();

	private final HashMap<File, IFeatureModel[]> featureCache = new HashMap<File, IFeatureModel[]>();

	public EclipseImportReaderType() {
	}

	public synchronized void addProjectClasspath(IProject project, IClasspathEntry[] classPath) {
		classpaths.put(project, classPath);
	}

	@Override
	public URI getArtifactURL(Resolution resolution, RMContext context) throws CoreException {
		try {
			URL siteURL = new URL(resolution.getRepository());
			String sitePath = siteURL.getPath();
			if (!(sitePath.endsWith(".map") || sitePath.endsWith(".xml") || sitePath.endsWith(".jar"))) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				siteURL = URLUtils.appendTrailingSlash(siteURL);

			boolean isFeature = IComponentType.ECLIPSE_FEATURE.equals(resolution.getComponentTypeId());
			String subDir = isFeature ? FEATURES_FOLDER : PLUGINS_FOLDER;

			return createRemoteComponentURL(siteURL, null, resolution.getComponentIdentifier(), subDir).toURI();
		} catch (MalformedURLException e) {
			throw BuckminsterException.wrap(e);
		} catch (URISyntaxException e) {
			throw BuckminsterException.wrap(e);
		}
	}

	@Override
	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException {
		MonitorUtils.complete(monitor);
		return new EclipseImportReader(this, providerMatch);
	}

	@Override
	public String getRecommendedMaterializer() {
		return IMaterializer.P2;
	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider, IComponentType ctype, NodeQuery nodeQuery, IProgressMonitor monitor)
			throws CoreException {
		throw new UnsupportedOperationException("import reader can not be used for resolution"); //$NON-NLS-1$
	}

	@Override
	public synchronized void postMaterialization(MaterializationContext context, IProgressMonitor monitor) throws CoreException {
		// Create needed classpath entries in each project
		//
		PluginImportOperation.setClasspaths(monitor, classpaths);

		// Clear cached entries
		//
		featureCache.clear();
		classpaths.clear();
	}

	IInstallableUnit getCachedInstallableUnit(IMetadataRepository mdr, ProviderMatch providerMatch) throws CoreException {
		NodeQuery query = providerMatch.getNodeQuery();
		Version bv = providerMatch.getVersionMatch().getVersion();
		if (bv == null)
			return null;
		VersionRange vr = new VersionRange(bv, true, bv, true);

		IComponentRequest cr = query.getComponentRequest();
		String name = cr.getName();
		if (IComponentType.ECLIPSE_FEATURE.equals(cr.getComponentTypeID()) && !name.endsWith(IPDEConstants.FEATURE_GROUP))
			name += IPDEConstants.FEATURE_GROUP;
		IQueryResult<IInstallableUnit> c = mdr.query(QueryUtil.createIUQuery(name, vr), null);
		if (c.isEmpty())
			return null;
		return c.iterator().next();
	}

	IFeatureModel getFeatureModel(ProviderMatch rInfo, IProgressMonitor monitor) throws CoreException {
		IFeatureModel model = null;
		EclipseImportBase localBase = localizeContents(rInfo, false, MonitorUtils.subMonitor(monitor, 90));
		String version = rInfo.getVersionMatch().getVersion().toString();
		for (IFeatureModel candidate : localBase.getFeatureModels(this, monitor)) {
			if (version.equals(candidate.getFeature().getVersion())) {
				model = candidate;
				break;
			}
		}
		return model;
	}

	List<IFeatureModel> getFeatureModels(File location, String featureName, IProgressMonitor monitor) throws CoreException {
		ArrayList<IFeatureModel> candidates = new ArrayList<IFeatureModel>();
		for (IFeatureModel model : getSiteFeatures(location, monitor)) {
			if (model.getFeature().getId().equals(featureName))
				candidates.add(model);
		}
		return candidates;
	}

	IPluginModelBase getPluginModel(ProviderMatch rInfo, IProgressMonitor monitor) throws CoreException {
		IPluginModelBase model = null;
		EclipseImportBase localBase = localizeContents(rInfo, true, MonitorUtils.subMonitor(monitor, 90));
		String version = rInfo.getVersionMatch().getVersion().toString();
		for (IPluginModelBase candidate : localBase.getPluginModels(this, MonitorUtils.subMonitor(monitor, 10))) {
			if (version.equals(candidate.getBundleDescription().getVersion().toString())) {
				model = candidate;
				break;
			}
		}
		return model;
	}

	List<IPluginModelBase> getPluginModels(File location, String pluginName, IProgressMonitor monitor) throws CoreException {
		ArrayList<IPluginModelBase> candidates = new ArrayList<IPluginModelBase>();
		for (IPluginModelBase model : getSitePlugins(location, monitor)) {
			if (model.getPluginBase().getId().equals(pluginName))
				candidates.add(model);
		}
		return candidates;
	}

	// Must be synchronized since we materialize to a common state
	synchronized EclipseImportBase localizeContents(ProviderMatch rInfo, boolean isPlugin, IProgressMonitor monitor) throws CoreException {
		NodeQuery query = rInfo.getNodeQuery();
		EclipseImportBase base = EclipseImportBase.obtain(query, rInfo.getRepositoryURI());
		if (base.isLocal() && rInfo.getVersionMatch().getArtifactInfo() == null)
			return base;

		Map<UUID, Object> userCache = query.getContext().getUserCache();
		String name = base.getComponentName();
		monitor.beginTask(null, 1000);
		monitor.subTask(NLS.bind(Messages.localizing_0, name));

		try {
			IConnectContext cctx = rInfo.getConnectContext();
			String typeDir = isPlugin ? PLUGINS_FOLDER : FEATURES_FOLDER;

			File tempSite = getTempSite(userCache);
			File subDir = new File(tempSite, typeDir);
			String jarName = null;
			File jarFile = null;

			VersionMatch vm = rInfo.getVersionMatch();
			if (vm.getArtifactInfo() != null) {
				// This is a P2 artifact. Copy it from the artifact repository
				//
				IInstallableUnit iu = P2ReaderType.getIU(rInfo, monitor);
				IArtifactRepository ar = P2ReaderType.getArtifactRepository(rInfo, monitor);
				Transport transport = (Transport) ar.getProvisioningAgent().getService(Transport.SERVICE_NAME);
				for (IArtifactKey ak : iu.getArtifacts()) {
					jarName = ak.getId() + '_' + ak.getVersion() + ".jar"; //$NON-NLS-1$
					jarFile = new File(subDir, jarName);
					IStatus status = ar.getArtifacts(new IArtifactRequest[] { new CopyRequest(ak, transport, jarFile) }, monitor);
					if (!status.isOK())
						throw new CoreException(status);
				}
				if (jarFile == null)
					throw BuckminsterException.fromMessage(NLS.bind(Messages.IU_0_1_has_no_artifacts, iu.getId(), iu.getVersion()));
			} else {
				URL pluginURL = createRemoteComponentURL(base.getRemoteLocation(), cctx, new ComponentIdentifier(rInfo.getComponentName(), rInfo
						.getComponentType().getId(), vm.getVersion()), typeDir);

				// Use a temporary local site
				//
				IPath path = Path.fromPortableString(pluginURL.getPath());
				jarName = path.lastSegment();
				if (!(jarName.endsWith(".jar") || jarName.endsWith(".zip"))) //$NON-NLS-1$ //$NON-NLS-2$
					throw BuckminsterException.fromMessage(NLS.bind(Messages.invalid_url_fore_remote_import_0, pluginURL));

				jarFile = new File(subDir, jarName);
				InputStream input = null;
				try {
					input = DownloadManager.getCache().open(pluginURL, cctx, null, null, MonitorUtils.subMonitor(monitor, 900));
					FileUtils.copyFile(input, subDir, jarName, MonitorUtils.subMonitor(monitor, 900));
				} finally {
					IOUtils.close(input);
				}
			}
			Key remoteKey = base.getKey();

			base = EclipseImportBase.obtain(query, new URI("file", null, tempSite.toURI().getPath(), base //$NON-NLS-1$
					.getQuery(), name).toString());

			File destDir = null;
			boolean unpack = true;
			ConflictResolution cres = ConflictResolution.REPLACE;

			if (jarName.endsWith(".zip")) //$NON-NLS-1$
			{
				// Special orbit packaging. Just unzip into the plug-ins folder
				//
				destDir = subDir;
				cres = ConflictResolution.UPDATE;
			} else {
				if (!base.isFeature()) {
					// Guess unpack based on classpath
					//
					JarFile jf = new JarFile(jarFile);
					Manifest mf = jf.getManifest();
					if (mf != null) {
						String[] classPath = TextUtils.split(mf.getMainAttributes().getValue(Constants.BUNDLE_CLASSPATH), ","); //$NON-NLS-1$

						int top = classPath.length;
						unpack = (top > 0);
						for (int idx = 0; idx < top; ++idx) {
							if (classPath[idx].trim().equals(".")) //$NON-NLS-1$
							{
								unpack = false;
								break;
							}
						}
					}
					jf.close();
				}
				if (unpack) {
					String vcName = jarName.substring(0, jarName.length() - 4);
					destDir = new File(subDir, vcName);
				}
			}

			if (unpack) {
				InputStream input = new FileInputStream(jarFile);
				try {
					FileUtils.unzip(input, null, destDir, cres, MonitorUtils.subMonitor(monitor, 100));
				} finally {
					IOUtils.close(input);
				}
				jarFile.delete();
			}
			base.setUnpack(unpack);

			// Cache this using the remote key also so that the next time
			// someone asks for it, the local
			// version is returned
			//
			EclipseImportBase.getImportBaseCacheCache(userCache).put(remoteKey, base);
			return base;
		} catch (URISyntaxException e) {
			throw BuckminsterException.wrap(e);
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			monitor.done();
		}
	}

	private CoreException createException(ArrayList<IStatus> resultStatus) {
		int errCount = resultStatus.size();
		if (errCount == 1)
			return new CoreException(resultStatus.get(0));

		IStatus[] children = resultStatus.toArray(new IStatus[errCount]);
		MultiStatus multiStatus = new MultiStatus(PDEPlugin.getPluginId(), IStatus.OK, children, Messages.problems_loading_feature, null);
		return new CoreException(multiStatus);
	}

	private IFeatureModel[] getPlatformFeatures() {
		return PDECore.getDefault().getFeatureModelManager().getModels();
	}

	private IPluginModelBase[] getPlatformPlugins() {
		return PDECore.getDefault().getModelManager().getExternalModels();
	}

	private synchronized IFeatureModel[] getSiteFeatures(File location, IProgressMonitor monitor) throws CoreException {
		if (location == null)
			return getPlatformFeatures();

		IFeatureModel[] result = featureCache.get(location);
		if (result != null)
			return result;

		location = new File(location, FEATURES_FOLDER);
		File[] dirs = location.listFiles();
		if (dirs == null || dirs.length == 0)
			return new IFeatureModel[0];

		monitor.beginTask(null, dirs.length);
		monitor.subTask(NLS.bind(Messages.building_feature_list_for_site_0, location));
		ArrayList<IFeatureModel> models = new ArrayList<IFeatureModel>(dirs.length);
		ArrayList<IStatus> resultStatus = null;
		for (File dir : dirs) {
			File manifest = new File(dir, FEATURE_MANIFEST);
			InputStream manifestInput = null;
			try {
				manifestInput = new FileInputStream(manifest);

				ExternalFeatureModel model = new ExternalFeatureModel();
				model.setInstallLocation(dir.getAbsolutePath());
				model.load(manifestInput, false);
				if (!model.isValid())
					throw new CoreException(new Status(IStatus.WARNING, PDEPlugin.getPluginId(), IStatus.OK, NLS.bind(
							Messages.import_location_0_contains_invalid_feature, dir), null));

				models.add(model);
			} catch (FileNotFoundException e) {
				// This is expected. No feature.xml means not a feature.
			} catch (CoreException e) {
				if (resultStatus == null)
					resultStatus = new ArrayList<IStatus>();
				resultStatus.add(e.getStatus());
			} finally {
				IOUtils.close(manifestInput);
				MonitorUtils.worked(monitor, 1);
			}
		}

		if (resultStatus != null)
			throw createException(resultStatus);

		result = models.toArray(new IFeatureModel[models.size()]);
		featureCache.put(location, result);
		return result;
	}

	private synchronized IPluginModelBase[] getSitePlugins(File location, IProgressMonitor monitor) throws CoreException {
		if (location == null)
			return getPlatformPlugins();

		monitor.beginTask(null, 2);
		monitor.subTask(NLS.bind(Messages.building_plugin_list_for_site_0, location));
		try {
			File pluginsRoot = new File(location, PLUGINS_FOLDER);
			if (!pluginsRoot.isDirectory())
				return new IPluginModelBase[0];

			File[] files = pluginsRoot.listFiles();
			int idx = files.length;
			URL[] pluginURLs = new URL[idx];
			while (--idx >= 0)
				pluginURLs[idx] = files[idx].toURI().toURL();

			MonitorUtils.worked(monitor, 1);
			PDEState state = new PDEState(pluginURLs, false, false, MonitorUtils.subMonitor(monitor, 1));
			return state.getTargetModels();
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			monitor.done();
		}
	}
}
