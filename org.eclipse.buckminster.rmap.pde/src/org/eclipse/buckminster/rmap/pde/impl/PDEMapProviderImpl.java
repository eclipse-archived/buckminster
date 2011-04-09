/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.pde.impl;

import org.eclipse.buckminster.model.common.Replace;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.model.common.CommonConstants;
import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.ComponentIdentifier;
import org.eclipse.buckminster.model.common.Format;
import org.eclipse.buckminster.model.common.PropertyRef;
import org.eclipse.buckminster.model.common.Value;
import org.eclipse.buckminster.rmap.Locator;
import org.eclipse.buckminster.rmap.Matcher;
import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.buckminster.rmap.ResourceMap;
import org.eclipse.buckminster.rmap.RmapFactory;
import org.eclipse.buckminster.rmap.RmapPackage;
import org.eclipse.buckminster.rmap.SearchPath;
import org.eclipse.buckminster.rmap.VersionConverter;
import org.eclipse.buckminster.rmap.impl.ProviderImpl;
import org.eclipse.buckminster.rmap.pde.PDEMapProvider;
import org.eclipse.buckminster.rmap.pde.PdePackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.buckminster.rmap.pde.util.MapFileEntry;
import org.eclipse.buckminster.rmap.util.ICatalogReader;
import org.eclipse.buckminster.rmap.util.IComponentReader;
import org.eclipse.buckminster.rmap.util.RmapResourceFactoryImpl;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.equinox.p2.metadata.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>PDE Map Provider</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.rmap.pde.impl.PDEMapProviderImpl#getReplace
 * <em>Replace</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PDEMapProviderImpl extends ProviderImpl implements PDEMapProvider {
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

	private static Format convertFetchFactoryLocator(String readerType, Map<String, String> fetchFactoryLocator, ComponentIdentifier componentName)
			throws CoreException {
		Format uri;
		if ("cvs".equals(readerType))
			uri = convertFetchFactoryLocator_cvs(fetchFactoryLocator, componentName);
		else if ("url".equals(readerType))
			uri = convertFetchFactoryLocator_url(fetchFactoryLocator, componentName);
		else if ("p2".equals(readerType))
			uri = convertFetchFactoryLocator_p2(fetchFactoryLocator, componentName);
		else
			throw BuckminsterException.fromMessage("Reader %s cannot handle fetchFactory data", readerType);

		if (uri == null)
			throw BuckminsterException.fromMessage("Illegal fetch factory locator for reader %s", readerType);
		return uri;
	}

	private static Format convertFetchFactoryLocator_cvs(Map<String, String> fetchFactoryLocator, ComponentIdentifier componentName)
			throws CoreException {
		String cvsRoot = fetchFactoryLocator.get("cvsRoot"); //$NON-NLS-1$
		if (cvsRoot == null)
			return null;

		PropertyRef cnameRef = null;
		StringBuilder locator = new StringBuilder(cvsRoot);
		String pathStr = fetchFactoryLocator.get("path"); //$NON-NLS-1$
		locator.append(',');
		if (pathStr != null) {
			IPath path = Path.fromPortableString(pathStr);
			String last = path.lastSegment();
			String id = componentName.getId();
			if (last.startsWith(id)) {
				if (path.segmentCount() > 1) {
					locator.append(path.removeLastSegments(1).toPortableString());
					locator.append('/');
				}
				locator.append("{0}");
				if (last.length() > id.length())
					locator.append(last.substring(id.length()));
				cnameRef = CommonFactory.eINSTANCE.createPropertyRef();
			} else
				locator.append(pathStr);
		} else {
			locator.append("{0}");
			cnameRef = CommonFactory.eINSTANCE.createPropertyRef();
		}
		Format fmt = CommonFactory.eINSTANCE.createFormat();
		fmt.setFormat(locator.toString());
		if (cnameRef != null) {
			cnameRef.setKey(CommonConstants.COMPONENT_NAME);
			fmt.getValues().add(cnameRef);
		}
		return fmt;
	}

	private static Format convertFetchFactoryLocator_p2(Map<String, String> fetchFactoryLocator, ComponentIdentifier componentName)
			throws CoreException {
		String repoURI = fetchFactoryLocator.get("repository"); //$NON-NLS-1$
		if (repoURI == null)
			return null;

		Format fmt = CommonFactory.eINSTANCE.createFormat();
		fmt.setFormat(repoURI);
		return fmt;
	}

	private static Format convertFetchFactoryLocator_url(Map<String, String> fetchFactoryLocator, ComponentIdentifier componentName)
			throws CoreException {
		String repoURI = fetchFactoryLocator.get("src"); //$NON-NLS-1$
		if (repoURI == null)
			return null;

		Format fmt = CommonFactory.eINSTANCE.createFormat();
		fmt.setFormat(repoURI);
		return fmt;
	}

	private static Pattern createUniquePattern(String id, Map<String, Integer> idRefCounts) {
		StringBuilder bld = new StringBuilder();
		bld.append('^');
		int top = id.length();
		for (int idx = 0; idx < top; ++idx) {
			char c = id.charAt(idx);
			switch (c) {
				case '.':
					bld.append('\\');
					break;
			}
			bld.append(c);
		}
		bld.append('$');
		String patternString = bld.toString();
		Integer refCount = idRefCounts.get(patternString);
		if (refCount == null)
			refCount = Integer.valueOf(1);
		else
			refCount = Integer.valueOf(refCount.intValue() + 1);
		idRefCounts.put(patternString, refCount);

		return Pattern.compile(patternString);
	}

	/**
	 * The cached value of the '{@link #getReplace() <em>Replace</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getReplace()
	 * @generated
	 * @ordered
	 */
	protected Replace replace;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PDEMapProviderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetReplace(Replace newReplace, NotificationChain msgs) {
		Replace oldReplace = replace;
		replace = newReplace;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PdePackage.PDE_MAP_PROVIDER__REPLACE, oldReplace,
					newReplace);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PdePackage.PDE_MAP_PROVIDER__REPLACE:
				return getReplace();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PdePackage.PDE_MAP_PROVIDER__REPLACE:
				return basicSetReplace(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case PdePackage.PDE_MAP_PROVIDER__REPLACE:
				return replace != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PdePackage.PDE_MAP_PROVIDER__REPLACE:
				setReplace((Replace) newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case PdePackage.PDE_MAP_PROVIDER__REPLACE:
				setReplace((Replace) null);
				return;
		}
		super.eUnset(featureID);
	}

	@Override
	public ResourceMap getDelegationMap(IComponentReader reader, IStatus problemCollector, Map<ComponentIdentifier, Map<String, String>> queryHints,
			IProgressMonitor monitor) throws CoreException {
		Map<ComponentIdentifier, MapFileEntry> map = getMap(reader, problemCollector, monitor);
		if (map.isEmpty())
			return null;

		int spIndex = 0;
		ResourceMap rmap = RmapFactory.eINSTANCE.createResourceMap();
		Map<String, List<Provider>> providersPerReader = new HashMap<String, List<Provider>>();
		Map<String, List<ComponentIdentifier>> idsPerSearchPath = new HashMap<String, List<ComponentIdentifier>>();
		for (MapFileEntry entry : map.values()) {
			ComponentIdentifier ci = entry.getComponentIdentifier();
			Map<String, String> properties = entry.getProperties();
			boolean source = true;
			Version v = null;
			String vs = null;
			String rt = entry.getReaderType();
			boolean isP2 = "p2".equals(rt);
			if (isP2) {
				String vstr = properties.get("version"); //$NON-NLS-1$
				if (vstr != null) {
					v = Version.parseVersion(vstr);
					addQueryHint(queryHints, ci, "version", v.toString());
				}
				source = false;
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
				}
			}

			List<Provider> providers = providersPerReader.get(rt);
			if (providers == null) {
				providers = new ArrayList<Provider>();
				providersPerReader.put(rt, providers);
			}

			Provider provider = null;
			Format providerURI = convertFetchFactoryLocator(rt, properties, ci);
			for (Provider candidate : providers) {
				if (candidate.getComponentTypes().get(0).equals(ci.getType()) && EcoreUtil.equals(candidate.getURI(), providerURI)) {
					provider = candidate;
					break;
				}
			}

			SearchPath searchPath;
			if (provider == null) {
				provider = RmapFactory.eINSTANCE.createProvider();
				provider.setURI(providerURI);
				provider.setReaderType(rt);
				provider.getComponentTypes().add(ci.getType());
				provider.setSource(source);
				providers.add(provider);
				searchPath = RmapFactory.eINSTANCE.createSearchPath();
				searchPath.setName("sp" + spIndex++);
				searchPath.getProviders().add(provider);
				rmap.getSearchPaths().add(searchPath);
			} else
				searchPath = (SearchPath) provider.eContainer();

			List<ComponentIdentifier> ids = idsPerSearchPath.get(searchPath.getName());
			if (ids == null) {
				ids = new ArrayList<ComponentIdentifier>();
				idsPerSearchPath.put(searchPath.getName(), ids);
			}
			ids.add(ci);
		}

		// Add locators for each search path, or if the search path is appointed
		// by only
		// one component id, add the provider directory to the rmap.

		Map<String, Integer> idRefCounts = new HashMap<String, Integer>();
		List<Provider> singletonProviders = new ArrayList<Provider>();
		Iterator<SearchPath> spIterator = rmap.getSearchPaths().iterator();
		while (spIterator.hasNext()) {
			SearchPath sp = spIterator.next();
			List<ComponentIdentifier> ids = idsPerSearchPath.get(sp.getName());
			if (ids.size() == 1) {
				ComponentIdentifier ci = ids.get(0);
				Provider p = sp.getProviders().get(0);
				p.setPattern(createUniquePattern(ci.getId(), idRefCounts));
				sp.getProviders().clear();
				spIterator.remove();
				Format fmt = p.getURI();
				if (fmt.getValues().size() == 1) {
					// We might just as well resolve the component name here.
					// The provider will never be subject to other names
					Value v = fmt.getValues().get(0);
					if (v instanceof PropertyRef && CommonConstants.COMPONENT_NAME.equals(((PropertyRef) v).getKey())) {
						fmt.setFormat(fmt.getValue(Collections.singletonMap(CommonConstants.COMPONENT_NAME, ci.getId())));
						fmt.getValues().clear();
					}
				}
				singletonProviders.add(p);
			} else {
				for (ComponentIdentifier ci : ids) {
					Locator locator = RmapFactory.eINSTANCE.createLocator();
					locator.setPattern(createUniquePattern(ci.getId(), idRefCounts));
					locator.getComponentTypes().add(ci.getType());
					locator.setSearchPath(sp);
					rmap.getMatchers().add(locator);
				}
			}
		}
		for (Provider p : singletonProviders)
			rmap.getMatchers().add(p);

		// Clear component type of Locators that are only referenced once. The
		// provider will
		// discriminate anyway.
		for (Matcher m : rmap.getMatchers()) {
			if (m instanceof Locator && idRefCounts.get(m.getPattern().toString()).intValue() == 1)
				m.getComponentTypes().clear();
		}

		// Clean up the names
		spIndex = 0;
		spIterator = rmap.getSearchPaths().iterator();
		while (spIterator.hasNext()) {
			SearchPath sp = spIterator.next();
			sp.setName("sp" + spIndex++);
		}

		try {
			ResourceSet rs = new ResourceSetImpl();
			rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("rmap", new RmapResourceFactoryImpl());
			rs.getPackageRegistry().put(RmapPackage.eNS_URI, RmapPackage.eINSTANCE);
			Resource resource = rs.createResource(URI.createURI("http:///My.rmap"));
			resource.getContents().add(rmap);
			resource.save(System.out, null);
		} catch (IOException e) {

		}

		return rmap;
	}

	public Map<ComponentIdentifier, MapFileEntry> getMap(IComponentReader reader, IStatus problemCollector, IProgressMonitor monitor)
			throws CoreException {
		monitor.beginTask(null, 700);
		File tempFolder = null;
		try {
			Map<String, String> properties = reader.getProperties();
			tempFolder = IOUtils.createTempFolder("bucky", ".tmp", IOUtils.getTempRoot(properties)); //$NON-NLS-1$
			materializeMaps(tempFolder, reader, MonitorUtils.subMonitor(monitor, 500));

			String[] mapFiles = tempFolder.list();
			if (mapFiles == null || mapFiles.length == 0)
				return Collections.emptyMap();

			MonitorUtils.worked(monitor, 50);
			Map<ComponentIdentifier, MapFileEntry> map = new HashMap<ComponentIdentifier, MapFileEntry>();
			int amountPerFile = 100 / mapFiles.length;
			for (String file : mapFiles) {
				if (file.endsWith(".map")) //$NON-NLS-1$
					collectEntries(new File(tempFolder, file), properties, map);
				MonitorUtils.worked(monitor, amountPerFile);
			}
			map = Collections.unmodifiableMap(map);
			return map;
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
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

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Replace getReplace() {
		return replace;
	}

	@Override
	public boolean hasDelegationMap() {
		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setReplace(Replace newReplace) {
		if (newReplace != replace) {
			NotificationChain msgs = null;
			if (replace != null)
				msgs = ((InternalEObject) replace).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PdePackage.PDE_MAP_PROVIDER__REPLACE, null, msgs);
			if (newReplace != null)
				msgs = ((InternalEObject) newReplace).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PdePackage.PDE_MAP_PROVIDER__REPLACE, null, msgs);
			msgs = basicSetReplace(newReplace, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PdePackage.PDE_MAP_PROVIDER__REPLACE, newReplace, newReplace));
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

	private void addQueryHint(Map<ComponentIdentifier, Map<String, String>> queryHints, ComponentIdentifier ci, String key, String value) {
		Map<String, String> hints = queryHints.get(ci);
		if (hints == null) {
			hints = new HashMap<String, String>();
			queryHints.put(ci, hints);
		}
		hints.put(key, value);
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

} // PDEMapProviderImpl
