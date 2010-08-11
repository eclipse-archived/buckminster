/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.pde.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.model.common.CommonConstants;
import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.ComponentIdentifier;
import org.eclipse.buckminster.model.common.Format;
import org.eclipse.buckminster.model.common.PropertyRef;
import org.eclipse.buckminster.rmap.Locator;
import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.buckminster.rmap.ResourceMap;
import org.eclipse.buckminster.rmap.RmapFactory;
import org.eclipse.buckminster.rmap.SearchPath;
import org.eclipse.buckminster.rmap.VersionConverter;
import org.eclipse.buckminster.rmap.impl.ProviderImpl;
import org.eclipse.buckminster.rmap.pde.PDEMapProvider;
import org.eclipse.buckminster.rmap.pde.PdePackage;
import org.eclipse.buckminster.rmap.pde.util.MapFileEntry;
import org.eclipse.buckminster.rmap.util.ICatalogReader;
import org.eclipse.buckminster.rmap.util.IComponentReader;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.equinox.p2.metadata.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>PDE Map Provider</b></em>'. <!-- end-user-doc -->
 * <p>
 * </p>
 * 
 * @generated
 */
public class PDEMapProviderImpl extends ProviderImpl implements PDEMapProvider {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PDEMapProviderImpl() {
		super();
	}

	@Override
	public ResourceMap getDelegationMap(IComponentReader reader, IStatus problemCollector, Map<ComponentIdentifier,Map<String,String>> queryHints, IProgressMonitor monitor) throws CoreException {
		Map<ComponentIdentifier, MapFileEntry> map = getMap(reader, problemCollector, monitor);
		if(map.isEmpty())
			return null;

		int spIndex = 0;
		ResourceMap rmap = RmapFactory.eINSTANCE.createResourceMap();
		Map<String,List<Provider>> providersPerReader = new HashMap<String, List<Provider>>();
		for(MapFileEntry entry : map.values()) {
			ComponentIdentifier ci = entry.getComponentIdentifier();
			Map<String,String> properties = entry.getProperties();
			boolean source = true;
			boolean mutable = true;

			Version v = null;
			String vs = null;
			String rt = entry.getReaderType();
			if ("p2".equals(rt)) { //$NON-NLS-1$
				String vstr = properties.get("version"); //$NON-NLS-1$
				if (vstr != null) {
					v = Version.parseVersion(vstr);
					addQueryHint(queryHints, ci, "version", v.toString());
				}
				source = false;
				mutable = false;
			} else {
				String tag = properties.get("tag"); //$NON-NLS-1$
				if (tag != null)
					vs = tag;
				VersionConverter vc = getVersionConverter();
				if (vc != null) {
					// Let's check that the given tag matches what we are asking
					// for.
					//
					v = vc.createVersion(vs);
					addQueryHint(queryHints, ci, "version", v.toString());
				} else {
					addQueryHint(queryHints, ci, "tag", tag);					
				}
				if ("url".equals(rt)) { //$NON-NLS-1$
					source = false;
					mutable = false;
				}
			}

			List<Provider> providers = providersPerReader.get(rt);
			if(providers == null) {
				providers = new ArrayList<Provider>();
				providersPerReader.put(rt, providers);
			}
			
			Provider found = null;
			Format providerURI = convertFetchFactoryLocator(rt, properties, ci);
			for(Provider provider : providers) {
				if(provider.getComponentTypes().get(0).equals(ci.getType())
				&& EcoreUtil.equals(provider.getURI(), providerURI)) {
					found = provider;
					break;
				}
			}
			
			SearchPath searchPath;
			if(found == null) {
				found = RmapFactory.eINSTANCE.createProvider();
				found.setURI(providerURI);
				found.setReaderType(rt);
				found.getComponentTypes().add(ci.getType());
				found.setSource(source);
				found.setMutable(mutable);
				providers.add(found);
				searchPath = RmapFactory.eINSTANCE.createSearchPath();
				searchPath.setName("sp" + spIndex++);
				searchPath.getProviders().add(found);
				rmap.getSearchPaths().add(searchPath);
			} else
				searchPath = (SearchPath)found.eContainer();
			
			Locator locator = RmapFactory.eINSTANCE.createLocator();
			locator.setPattern(Pattern.compile('^' + Pattern.quote(ci.getId()) + '$'));
			locator.setSearchPath(searchPath);
			rmap.getMatchers().add(locator);
		}
		return rmap;
	}

	private void addQueryHint(Map<ComponentIdentifier, Map<String, String>> queryHints, ComponentIdentifier ci, String key, String value) {
		Map<String,String> hints = queryHints.get(ci);
		if(hints == null) {
			hints = new HashMap<String, String>();
			queryHints.put(ci, hints);
		}
		hints.put(key, value);
	}

	private static Format convertFetchFactoryLocator(String readerType, Map<String, String> fetchFactoryLocator, ComponentIdentifier componentName) throws CoreException {
		Format uri;
		if("cvs".equals(readerType))
			uri = convertFetchFactoryLocator_cvs(fetchFactoryLocator, componentName);
		else if("url".equals(readerType))
			uri = convertFetchFactoryLocator_url(fetchFactoryLocator, componentName);
		else if("p2".equals(readerType))
			uri = convertFetchFactoryLocator_p2(fetchFactoryLocator, componentName);
		else
			throw BuckminsterException.fromMessage("Reader %s cannot handle fetchFactory data", readerType);

		if(uri == null)
			throw BuckminsterException.fromMessage("Illegal fetch factory locator for reader %s", readerType);			
		return uri;
	}

	private static Format convertFetchFactoryLocator_cvs(Map<String, String> fetchFactoryLocator, ComponentIdentifier componentName) throws CoreException {
		String cvsRoot = fetchFactoryLocator.get("cvsRoot"); //$NON-NLS-1$
		if (cvsRoot == null)
			return null;

		PropertyRef cnameRef = null;
		StringBuilder locator = new StringBuilder(cvsRoot);
		String pathStr = fetchFactoryLocator.get("path"); //$NON-NLS-1$
		locator.append(',');
		if (pathStr != null) {
			IPath path = Path.fromPortableString(pathStr);
			if(componentName.equals(path.lastSegment())) {
				if(path.segmentCount() > 1) {
					locator.append(path.removeLastSegments(1).toPortableString());
					locator.append('/');
				}
				locator.append("{0}");
				cnameRef = CommonFactory.eINSTANCE.createPropertyRef();
			}
			locator.append(pathStr);
		}
		else {
			locator.append("{0}");
			cnameRef = CommonFactory.eINSTANCE.createPropertyRef();
		}
		Format fmt = CommonFactory.eINSTANCE.createFormat();
		fmt.setFormat(locator.toString());
		if(cnameRef != null) {
			cnameRef.setKey(CommonConstants.COMPONENT_NAME);
			fmt.getValues().add(cnameRef);
		}
		return fmt;
	}

	private static Format convertFetchFactoryLocator_url(Map<String, String> fetchFactoryLocator, ComponentIdentifier componentName) throws CoreException {
		String repoURI = fetchFactoryLocator.get("src"); //$NON-NLS-1$
		if(repoURI == null)
			return null;

		Format fmt = CommonFactory.eINSTANCE.createFormat();
		fmt.setFormat(repoURI);
		return fmt;
	}

	private static Format convertFetchFactoryLocator_p2(Map<String, String> fetchFactoryLocator, ComponentIdentifier componentName) throws CoreException {
		String repoURI = fetchFactoryLocator.get("repository"); //$NON-NLS-1$
		if(repoURI == null)
			return null;
		
		Format fmt = CommonFactory.eINSTANCE.createFormat();
		fmt.setFormat(repoURI);
		return fmt;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PdePackage.Literals.PDE_MAP_PROVIDER;
	}

	private void materializeMaps(File tempFolder, IComponentReader reader, IProgressMonitor monitor) throws CoreException {
		MonitorUtils.begin(monitor, 500);
		try {
			((ICatalogReader) reader).materialize(new Path(tempFolder.toString()), MonitorUtils.subMonitor(monitor, 400));
		} finally {
			IOUtils.close(reader);
			MonitorUtils.done(monitor);
		}
	}

	public Map<ComponentIdentifier, MapFileEntry> getMap(IComponentReader reader, IStatus problemCollector, IProgressMonitor monitor)
			throws CoreException {
		monitor.beginTask(null, 700);
		File tempFolder = null;
		try {
			Map<String, String> properties = reader.getProperties();
			tempFolder = IOUtils.createTempFolder("bucky", ".tmp", IOUtils.getTempRoot(properties)); //$NON-NLS-1$
			materializeMaps(tempFolder, reader, MonitorUtils.subMonitor(monitor, 500));

			Map<ComponentIdentifier, MapFileEntry> map = new HashMap<ComponentIdentifier, MapFileEntry>();
			String[] mapFiles = tempFolder.list();
			if (mapFiles == null || mapFiles.length == 0)
				return null;

			MonitorUtils.worked(monitor, 50);

			int amountPerFile = 100 / mapFiles.length;
			for (String file : mapFiles) {
				if (file.endsWith(".map")) //$NON-NLS-1$
					collectEntries(new File(tempFolder, file), properties, map);
				MonitorUtils.worked(monitor, amountPerFile);
			}
			map = Collections.unmodifiableMap(map);
			return map;
		} catch (CoreException e) {
			((MultiStatus)problemCollector).add(e.getStatus());
			Buckminster.getLogger().debug(e, e.getMessage());
			return null;
		} catch (IOException e) {
			((MultiStatus)problemCollector).add(BuckminsterException.createStatus(e));
			Buckminster.getLogger().debug(e, e.getMessage());
			return null;
		} finally {
			if (tempFolder != null)
				try {
					IOUtils.deleteRecursive(tempFolder, MonitorUtils.subMonitor(monitor, 50));
				} catch (IOException e) {
					Buckminster.getLogger().warning(e, e.getMessage());
				}
			monitor.done();
		}
	}

	private static void collectEntries(File mapFile, Map<String, String> properties, Map<ComponentIdentifier, MapFileEntry> map) throws CoreException {
		InputStream input = null;
		try {
			input = new FileInputStream(mapFile);
			ArrayList<MapFileEntry> list = new ArrayList<MapFileEntry>();
			org.eclipse.buckminster.rmap.pde.util.MapFile.parse(input, mapFile.getCanonicalPath(), properties, list);
			for (MapFileEntry entry : list)
				map.put(entry.getComponentIdentifier(), entry);
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			IOUtils.close(input);
		}
	}

} // PDEMapProviderImpl
