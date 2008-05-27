/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.pde.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.ConflictResolution;
import org.eclipse.buckminster.core.reader.CatalogReaderType;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.ProviderScore;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.pde.IPDEConstants;
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
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.pde.core.plugin.IFragmentModel;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.PDEState;
import org.eclipse.pde.internal.core.feature.ExternalFeatureModel;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.update.core.IFeature;
import org.eclipse.update.core.IFeatureReference;
import org.eclipse.update.core.IIncludedFeatureReference;
import org.eclipse.update.core.IPluginEntry;
import org.eclipse.update.core.ISite;
import org.eclipse.update.core.ISiteFeatureReference;
import org.eclipse.update.core.PluginEntry;
import org.eclipse.update.core.SiteManager;
import org.eclipse.update.core.VersionedIdentifier;
import org.osgi.framework.Constants;

@SuppressWarnings("restriction")
public class EclipseImportReaderType extends CatalogReaderType implements IPDEConstants
{
	private static final UUID CACHE_KEY_SITE_CACHE = UUID.randomUUID();

	public static class RemotePluginEntry extends PluginEntry
	{
		private final URL m_remoteLocation;

		public RemotePluginEntry(URL remoteLocation)
		{
			m_remoteLocation = remoteLocation;
		}

		public URL getRemoteLocation()
		{
			return m_remoteLocation;
		}
	}

	public URI getArtifactURL(Resolution resolution, RMContext context) throws CoreException
	{
		try
		{
			URL siteURL = new URL(resolution.getRepository());
			String sitePath = siteURL.getPath();
			if(!(sitePath.endsWith(".map") || sitePath.endsWith(".xml") || sitePath.endsWith(".jar")));
				siteURL = URLUtils.appendTrailingSlash(siteURL);

			String subDir = IComponentType.ECLIPSE_FEATURE.equals(resolution.getComponentTypeId())
				? FEATURES_FOLDER
				: PLUGINS_FOLDER;

			return createRemoteComponentURL(siteURL, resolution.getName(), resolution.getVersion(), subDir).toURI();
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}
		catch(URISyntaxException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public static File getTempSite(Map<UUID,Object> ucache) throws CoreException
	{
		Map<String,File> siteCache = getSiteCache(ucache);
		synchronized(siteCache)
		{
			String key = EclipseImportReaderType.class.getSimpleName() + ":tempSite";
			File tempSite = siteCache.get(key);
			if(tempSite != null)
				return tempSite;

			tempSite = FileUtils.createTempFolder("bmsite", ".tmp");
			new File(tempSite, PLUGINS_FOLDER).mkdir();
			new File(tempSite, FEATURES_FOLDER).mkdir();
			siteCache.put(key, tempSite);
			return tempSite;
		}
	}

	@SuppressWarnings("unchecked")
	private static Map<String,File> getSiteCache(Map<UUID,Object> ctxUserCache)
	{
		synchronized(ctxUserCache)
		{
			Map<String, File> siteCache = (Map<String, File>)ctxUserCache.get(CACHE_KEY_SITE_CACHE);
			if(siteCache == null)
			{
				siteCache = Collections.synchronizedMap(new HashMap<String, File>());
				ctxUserCache.put(CACHE_KEY_SITE_CACHE, siteCache);
			}
			return siteCache;
		}
	}

	private final Map<IProject, IClasspathEntry[]> m_classpaths = new HashMap<IProject, IClasspathEntry[]>();

	private final HashMap<File, IFeatureModel[]> m_featureCache = new HashMap<File, IFeatureModel[]>();

	private final HashMap<File, IPluginModelBase[]> m_pluginCache = new HashMap<File, IPluginModelBase[]>();

	private transient ArrayList<IFragmentModel> m_siteFragments;

	@SuppressWarnings("deprecation")
	public synchronized IPluginModelBase getPluginModelBase(URL location, String id, String version,
		ProviderMatch templateInfo) throws CoreException
	{
		for(IPluginEntry candidate : getSitePluginEntries(location, new NullProgressMonitor()))
		{
			VersionedIdentifier vi = candidate.getVersionedIdentifier();
			String name = vi.getIdentifier();
			if(id.equals(name))
			{
				String versionStr = vi.getVersion().toString();
				if(version == null || version.equals(versionStr))
					return localize(vi.getIdentifier(), versionStr, templateInfo);
			}
		}
		return null;
	}

	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor)
	throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new EclipseImportReader(this, providerMatch);
	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider, IComponentType ctype, NodeQuery nodeQuery, IProgressMonitor monitor)
	throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new EclipseImportFinder(this, provider, ctype, nodeQuery);
	}

	@Override
	public synchronized void postMaterialization(MaterializationContext context, IProgressMonitor monitor) throws CoreException
	{
		// Create needed classpath entries in each project
		//
		PluginImportOperation.setClasspaths(monitor, m_classpaths);

		// Clear cached entries
		//
		m_pluginCache.clear();
		m_featureCache.clear();
		m_classpaths.clear();
	}

	public synchronized void addProjectClasspath(IProject project, IClasspathEntry[] classPath)
	{
		m_classpaths.put(project, classPath);
	}

	IFeatureModel getFeatureModel(ProviderMatch rInfo, IProgressMonitor monitor) throws CoreException
	{
		IFeatureModel model = null;
		EclipseImportBase localBase = localizeContents(rInfo, false, MonitorUtils.subMonitor(monitor, 90));
		String version = rInfo.getVersionMatch().getVersion().toString();
		for(IFeatureModel candidate : localBase.getFeatureModels(this, monitor))
		{
			if(version.equals(candidate.getFeature().getVersion()))
			{
				model = candidate;
				break;
			}
		}
		return model;
	}

	List<IFeatureModel> getFeatureModels(File location, String featureName, IProgressMonitor monitor)
	throws CoreException
	{
		ArrayList<IFeatureModel> candidates = new ArrayList<IFeatureModel>();
		for(IFeatureModel model : getSiteFeatures(location, monitor))
		{
			if(model.getFeature().getId().equals(featureName))
				candidates.add(model);
		}
		return candidates;
	}

	List<ISiteFeatureReference> getFeatureReferences(URL location, String componentName,
		IProgressMonitor monitor) throws CoreException
	{
		ArrayList<ISiteFeatureReference> result = new ArrayList<ISiteFeatureReference>();
		for(ISiteFeatureReference ref : getSiteFeatureReferences(location, monitor))
		{
			if(ref.getVersionedIdentifier().getIdentifier().equals(componentName))
				result.add(ref);
		}
		return result;
	}

	List<IFragmentModel> getFragmentsFor(URL location, ProviderMatch templateInfo, String pluginId)
	throws CoreException
	{
		ArrayList<IFragmentModel> frags = null;
		for(IFragmentModel frag : getAllFragments(location, templateInfo))
		{
			if(frag.getFragment().getPluginId().equals(pluginId))
			{
				if(frags == null)
					frags = new ArrayList<IFragmentModel>();
				frags.add(frag);
			}
		}
		return frags == null ? Collections.<IFragmentModel> emptyList() : frags;
	}

	List<IPluginEntry> getPluginEntries(URL location, String componentName, IProgressMonitor monitor)
	throws CoreException
	{
		ArrayList<IPluginEntry> result = new ArrayList<IPluginEntry>();
		for(IPluginEntry entry : getSitePluginEntries(location, monitor))
		{
			if(entry.getVersionedIdentifier().getIdentifier().equals(componentName))
				result.add(entry);
		}
		return result;
	}

	IPluginModelBase getPluginModel(ProviderMatch rInfo, IProgressMonitor monitor) throws CoreException
	{
		IPluginModelBase model = null;
		EclipseImportBase localBase = localizeContents(rInfo, true, MonitorUtils.subMonitor(monitor, 90));
		String version = rInfo.getVersionMatch().getVersion().toString();
		for(IPluginModelBase candidate : localBase.getPluginModels(this, MonitorUtils.subMonitor(monitor, 10)))
		{
			if(version.equals(candidate.getBundleDescription().getVersion().toString()))
			{
				model = candidate;
				break;
			}
		}
		return model;
	}

	List<IPluginModelBase> getPluginModels(File location, String pluginName, IProgressMonitor monitor)
	throws CoreException
	{
		ArrayList<IPluginModelBase> candidates = new ArrayList<IPluginModelBase>();
		for(IPluginModelBase model : getSitePlugins(location, monitor))
		{
			if(model.getPluginBase().getId().equals(pluginName))
				candidates.add(model);
		}
		return candidates;
	}

	EclipseImportBase localizeContents(ProviderMatch rInfo, boolean isPlugin, IProgressMonitor monitor)
	throws CoreException
	{
		NodeQuery query = rInfo.getNodeQuery();
		EclipseImportBase base = EclipseImportBase.obtain(query, rInfo.getRepositoryURI());
		if(base.isLocal())
			return base;

		// Synchronize on base, it uses a ligthweight pattern so only
		// one instance will exists for any given URI/request combination
		//
		synchronized(base)
		{
			Map<UUID,Object> userCache = query.getContext().getUserCache();
			String name = base.getComponentName();
			monitor.beginTask(null, 1000);
			monitor.subTask("Localizing " + name);

			try
			{
				String typeDir = isPlugin ? PLUGINS_FOLDER : FEATURES_FOLDER;
				URL pluginURL = createRemoteComponentURL(base.getRemoteLocation(), rInfo.getComponentName(), rInfo.getVersionMatch().getVersion(), typeDir);

				// Use a temporary local site
				//
				IPath path = Path.fromPortableString(pluginURL.getPath());
				String jarName = path.lastSegment();
				if(!(jarName.endsWith(".jar") || jarName.endsWith(".zip")))
					throw BuckminsterException.fromMessage("Invalid url for remote import: %s", pluginURL);

				String vcName = jarName.substring(0, jarName.length() - 4);
				File tempSite = getTempSite(userCache);
				File subDir = new File(tempSite, typeDir);
				File jarFile = new File(subDir, jarName);
				InputStream input = null;
				try
				{
					input = DownloadManager.getCache().open(pluginURL, null, null, MonitorUtils.subMonitor(monitor, 900));
					FileUtils.copyFile(input, subDir, jarName, MonitorUtils.subMonitor(monitor, 900));
				}
				finally
				{
					IOUtils.close(input);
				}
				Key remoteKey = base.getKey();

				base = EclipseImportBase.obtain(query, new URI("file", null, tempSite.toURI().getPath(),
					base.getQuery(), name).toString());

				File destDir = null;
				boolean unpack = true;
				ConflictResolution cres = ConflictResolution.REPLACE;

				if(jarName.endsWith(".zip"))
				{
					// Special orbit packaging. Just unzip into the plug-ins folder
					//
					destDir = subDir;
					cres = ConflictResolution.UPDATE;
				}
				else
				{
					if(!base.isFeature())
					{
						// Guess unpack based on classpath
						//
						JarFile jf = new JarFile(jarFile);
						Manifest mf = jf.getManifest();
						if(mf != null)
						{
							String[] classPath = TextUtils.split(mf.getMainAttributes().getValue(Constants.BUNDLE_CLASSPATH), ",");
		
							int top = classPath.length;
							unpack = (top > 0);
							for(int idx = 0; idx < top; ++idx)
							{
								if(classPath[idx].equals("."))
								{
									unpack = false;
									break;
								}
							}
						}
						jf.close();
					}
					if(unpack)
						destDir = new File(subDir, vcName);
				}

				if(unpack)
				{
					input = new FileInputStream(jarFile);
					try
					{
						FileUtils.unzip(input, null, destDir, cres, MonitorUtils.subMonitor(monitor, 100));
					}
					finally
					{
						IOUtils.close(input);
					}
					jarFile.delete();
				}

				// Cache this using the remote key also so that the next time someone asks for it, the local
				// version is returned
				//
				EclipseImportBase.getImportBaseCacheCache(userCache).put(remoteKey, base);
				return base;
			}
			catch(URISyntaxException e)
			{
				throw BuckminsterException.wrap(e);
			}
			catch(IOException e)
			{
				throw BuckminsterException.wrap(e);
			}
			finally
			{
				monitor.done();
			}
		}
	}

	private CoreException createException(ArrayList<IStatus> resultStatus)
	{
		int errCount = resultStatus.size();
		if(errCount == 1)
			return new CoreException(resultStatus.get(0));

		IStatus[] children = resultStatus.toArray(new IStatus[errCount]);
		MultiStatus multiStatus = new MultiStatus(PDEPlugin.getPluginId(), IStatus.OK, children,
			"Problems loading features", null);
		return new CoreException(multiStatus);
	}

	static URL createRemoteComponentURL(URL remoteLocation, String name, IVersion version, String subDir)
	throws MalformedURLException, CoreException
	{
		if(remoteLocation.getPath().endsWith(".jar"))
			return remoteLocation;

		if(remoteLocation.getPath().endsWith(".map"))
		{
			for(RemotePluginEntry entry : getMapPluginEntries(remoteLocation))
			{
				VersionedIdentifier vid = entry.getVersionedIdentifier();
				if(name.equals(vid.getIdentifier()) && version.equalsUnqualified(VersionFactory.OSGiType.coerce(vid.getVersion())))
					return entry.getRemoteLocation();
			}
			throw BuckminsterException.fromMessage("Unable to find %s in map %s", name, remoteLocation);
		}
		return new URL(remoteLocation, subDir + '/' + name + '_' + version + ".jar");
	}

	@SuppressWarnings("deprecation")
	private synchronized List<IFragmentModel> getAllFragments(URL location, ProviderMatch templateInfo)
	throws CoreException
	{
		if(m_siteFragments != null)
			return m_siteFragments;

		ArrayList<IFragmentModel> frags = new ArrayList<IFragmentModel>();
		for(IPluginEntry candidate : getSitePluginEntries(location, new NullProgressMonitor()))
		{
			if(candidate.isFragment())
			{
				// Only one way to know if this is a fragment for the pluginId
				// We must download it.
				//
				VersionedIdentifier vi = candidate.getVersionedIdentifier();
				frags.add((IFragmentModel)localize(vi.getIdentifier(), vi.getVersion().toString(),
					templateInfo));
			}
		}
		m_siteFragments = frags;
		return frags;
	}

	private IFeatureModel[] getPlatformFeatures()
	{
		return PDECore.getDefault().getFeatureModelManager().getModels();
	}

	private IPluginModelBase[] getPlatformPlugins()
	{
		return PDECore.getDefault().getModelManager().getExternalModels();
	}

	private ISiteFeatureReference[] getSiteFeatureReferences(URL location, IProgressMonitor monitor)
	throws CoreException
	{
		ISite site;
		synchronized(SiteManager.class)
		{
			site = SiteManager.getSite(location, true, monitor);
			if(site == null)
				throw new OperationCanceledException();
	
			return site.getFeatureReferences();
		}
	}

	private synchronized IFeatureModel[] getSiteFeatures(File location, IProgressMonitor monitor)
	throws CoreException
	{
		if(location == null)
			return getPlatformFeatures();

		IFeatureModel[] result = m_featureCache.get(location);
		if(result != null)
			return result;

		location = new File(location, FEATURES_FOLDER);
		File[] dirs = location.listFiles();
		if(dirs == null || dirs.length == 0)
			return new IFeatureModel[0];

		monitor.beginTask(null, dirs.length);
		monitor.subTask("Building feature list for site " + location);
		ArrayList<IFeatureModel> models = new ArrayList<IFeatureModel>(dirs.length);
		ArrayList<IStatus> resultStatus = null;
		for(File dir : dirs)
		{
			File manifest = new File(dir, FEATURE_FILE);
			InputStream manifestInput = null;
			try
			{
				manifestInput = new FileInputStream(manifest);

				ExternalFeatureModel model = new ExternalFeatureModel();
				model.setInstallLocation(dir.getAbsolutePath());
				model.load(manifestInput, false);
				if(!model.isValid())
					throw new CoreException(new Status(IStatus.WARNING, PDEPlugin.getPluginId(), IStatus.OK,
						"Import location " + dir + " contains invalid feature.", null));

				models.add(model);
			}
			catch(FileNotFoundException e)
			{
				// This is expected. No feature.xml means not a feature.
			}
			catch(CoreException e)
			{
				if(resultStatus == null)
					resultStatus = new ArrayList<IStatus>();
				resultStatus.add(e.getStatus());
			}
			finally
			{
				IOUtils.close(manifestInput);
				MonitorUtils.worked(monitor, 1);
			}
		}

		if(resultStatus != null)
			throw createException(resultStatus);

		result = models.toArray(new IFeatureModel[models.size()]);
		m_featureCache.put(location, result);
		return result;
	}

	private static RemotePluginEntry[] getMapPluginEntries(URL location) throws CoreException
	{
		InputStream input = null;
		try
		{
			ArrayList<MapFileEntry> mapEntries = new ArrayList<MapFileEntry>();
			input = DownloadManager.read(location);
			MapFile.parse(input, location.toString(), mapEntries);
			ArrayList<RemotePluginEntry> entries = new ArrayList<RemotePluginEntry>();
			for(MapFileEntry entry : mapEntries)
			{
				ComponentIdentifier cid = entry.getComponentIdentifier();
				if(!IComponentType.OSGI_BUNDLE.equals(cid.getComponentTypeID()))
					continue;

				if(!IReaderType.URL.equals(entry.getReaderType().getId()))
					continue;

				Map<String,String> props = entry.getProperties();
				String src = props.get("src");
				if(src == null || !(src.endsWith(".jar") || src.endsWith(".zip")))
					continue;

				RemotePluginEntry pluginEntry;
				try
				{
					pluginEntry = new RemotePluginEntry(new URL(src));
				}
				catch(MalformedURLException e)
				{
					continue;
				}

				pluginEntry.setPluginIdentifier(cid.getName());
				if(cid.getVersion() != null)
					pluginEntry.setPluginVersion(cid.getVersion().toString());

				pluginEntry.setUnpack(Boolean.parseBoolean(props.get("unpack")));
				entries.add(pluginEntry);
			}
			return entries.toArray(new RemotePluginEntry[entries.size()]);
		}
		catch(FileNotFoundException e)
		{
			return new RemotePluginEntry[0];
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(input);
		}
	}

	public IPluginEntry[] getSitePluginEntries(URL location, IProgressMonitor monitor) throws CoreException
	{
		if(location.getPath().endsWith(".map"))
		{
			MonitorUtils.complete(monitor);
			return getMapPluginEntries(location);
		}

		ISite site;
		MonitorUtils.begin(monitor, 100);
		synchronized(SiteManager.class)
		{
			site = SiteManager.getSite(location, true, MonitorUtils.subMonitor(monitor, 50));
			if(site == null)
				throw new OperationCanceledException();

			try
			{
				IPluginEntry[] entries = site.getPluginEntries();
				MonitorUtils.worked(monitor, 50);
				return entries;
			}
			catch(UnsupportedOperationException uoe)
			{
				// Damn it! We need to use the slow version.
				//
				HashMap<VersionedIdentifier, IPluginEntry> entries = new HashMap<VersionedIdentifier, IPluginEntry>();
				HashSet<VersionedIdentifier> seenFeatures = new HashSet<VersionedIdentifier>();
				IFeatureReference[] refs = getSiteFeatureReferences(location,
					MonitorUtils.subMonitor(monitor, 10));
				IProgressMonitor itemsMonitor = MonitorUtils.subMonitor(monitor, 40);
				itemsMonitor.beginTask(null, refs.length * 100);
				for(IFeatureReference ref : refs)
				{
					// The getFeature() call is not thread-safe. It uses static variables without
					// synchronization
					//
					VersionedIdentifier vid = ref.getVersionedIdentifier();
					if(seenFeatures.add(vid))
					{
						IFeature feature;
						try
						{
							feature = ref.getFeature(MonitorUtils.subMonitor(itemsMonitor, 50));
						}
						catch(Exception e)
						{
							PDEPlugin.getLogger().warning(e, e.getMessage());
							continue;
						}
						addFeaturePluginEntries(entries, seenFeatures, feature, MonitorUtils.subMonitor(itemsMonitor, 50));
					}
				}
				return entries.values().toArray(new IPluginEntry[entries.size()]);
			}
			finally
			{
				MonitorUtils.done(monitor);
			}
		}
	}

	private static void addFeaturePluginEntries(HashMap<VersionedIdentifier, IPluginEntry> entries, HashSet<VersionedIdentifier> seenFeatures, IFeature feature, IProgressMonitor monitor) throws CoreException
	{
		for(IPluginEntry entry : feature.getRawPluginEntries())
			entries.put(entry.getVersionedIdentifier(), entry);

		IIncludedFeatureReference[] includedFeatures = feature.getIncludedFeatureReferences();
		if(includedFeatures.length == 0)
		{
			MonitorUtils.complete(monitor);
			return;
		}

		monitor.beginTask(null, includedFeatures.length * 100);
		for(IIncludedFeatureReference ref : includedFeatures)
		{
			VersionedIdentifier vid = ref.getVersionedIdentifier();
			if(seenFeatures.contains(vid))
				MonitorUtils.worked(monitor, 100);
			else
			{
				seenFeatures.add(vid);
				IFeature includedFeature;
				try
				{
					includedFeature = ref.getFeature(MonitorUtils.subMonitor(monitor, 50));
				}
				catch(Exception e)
				{
					PDEPlugin.getLogger().warning(e, e.getMessage());
					continue;
				}
				addFeaturePluginEntries(entries, seenFeatures, includedFeature, MonitorUtils.subMonitor(monitor, 50));
			}
		}
		monitor.done();
	}

	private synchronized IPluginModelBase[] getSitePlugins(File location, IProgressMonitor monitor)
	throws CoreException
	{
		if(location == null)
			return getPlatformPlugins();

		monitor.beginTask(null, 2);
		monitor.subTask("Building plugin list for site " + location);
		try
		{
			File pluginsRoot = new File(location, PLUGINS_FOLDER);
			if(!pluginsRoot.isDirectory())
				return new IPluginModelBase[0];

			File[] files = pluginsRoot.listFiles();
			int idx = files.length;

			IPluginModelBase[] plugins = m_pluginCache.get(location);
			if(plugins != null && plugins.length == idx)
				return plugins;

			URL[] pluginURLs = new URL[idx];
			while(--idx >= 0)
				pluginURLs[idx] = files[idx].toURI().toURL();

			MonitorUtils.worked(monitor, 1);
			PDEState state = new PDEState(pluginURLs, false, MonitorUtils.subMonitor(monitor, 1));
			// was (for M5): plugins = state.getModels();
			IPluginModelBase[] workspace = state.getWorkspaceModels();
			IPluginModelBase[] target = state.getTargetModels();
			IPluginModelBase[] all = new IPluginModelBase[workspace.length + target.length];
			if(workspace.length > 0)
				System.arraycopy(workspace, 0, all, 0, workspace.length);
			if(target.length > 0)
				System.arraycopy(target, 0, all, workspace.length, target.length);
			plugins = all;
			m_pluginCache.put(location, plugins);
			return plugins;
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			monitor.done();
		}
	}

	private IPluginModelBase localize(String pluginID, String versionStr, ProviderMatch templateInfo)
	throws CoreException
	{
		IVersion version = VersionFactory.OSGiType.fromString(versionStr);
		NodeQuery tplNq = templateInfo.getNodeQuery();
		Provider provider = templateInfo.getProvider();
		VersionMatch vm = new VersionMatch(version, VersionSelector.tag(versionStr), provider.getSpace(), -1, null, null);

		NodeQuery nq = new NodeQuery(tplNq.getContext(), new ComponentRequest(pluginID,
			IComponentType.OSGI_BUNDLE,
			VersionFactory.createDesignator(VersionFactory.OSGiType, versionStr)), null);
		ProviderMatch pm = new ProviderMatch(provider, templateInfo.getComponentType(), vm, ProviderScore.GOOD, nq);
		return getPluginModel(pm, new NullProgressMonitor());
	}
}
