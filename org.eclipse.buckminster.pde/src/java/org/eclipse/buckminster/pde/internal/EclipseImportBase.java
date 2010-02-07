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
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.pde.internal.imports.PluginImportOperation;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;

@SuppressWarnings("restriction")
final class EclipseImportBase {
	static class Key {
		private final String repositoryURI;

		private final ComponentRequest request;

		Key(String repositoryURI, ComponentRequest request) {
			this.repositoryURI = repositoryURI;
			this.request = request;
		}

		@Override
		public boolean equals(Object o) {
			return this == o || (o instanceof Key) && repositoryURI.equals(((Key) o).repositoryURI) && request.equals(((Key) o).request);
		}

		public String getRepositoryURI() {
			return repositoryURI;
		}

		public ComponentRequest getRequest() {
			return request;
		}

		@Override
		public int hashCode() {
			return repositoryURI.hashCode() * 37 + request.hashCode();
		}
	}

	/**
	 * Parameter in the resource URI that determines the search order when
	 * resolving the component. Valid values are &quot;binary&quot; and
	 * &quot;linked&quot; or &quot;source&quot;. Default is &quot;binary&quot;
	 */
	static final String PARAM_IMPORT_TYPE = "importType"; //$NON-NLS-1$

	static final String IMPORT_TYPE_BINARY = "binary"; //$NON-NLS-1$

	static final String IMPORT_TYPE_LINKED = "linked"; //$NON-NLS-1$

	static final String IMPORT_TYPE_SOURCE = "source"; //$NON-NLS-1$

	private final int type;

	private final boolean platform;

	private final boolean feature;

	private final Key key;

	private final String query;

	private final File location;

	private final URL remoteLocation;

	private boolean unpack = false;

	private static final UUID CACHE_IMPORT_BASE_CACHE = UUID.randomUUID();

	public static EclipseImportBase obtain(NodeQuery query, String repositoryURI) throws CoreException {
		Key key = new Key(repositoryURI, query.getComponentRequest());
		Map<Key, EclipseImportBase> cache = getImportBaseCacheCache(query.getContext().getUserCache());
		synchronized (cache) {
			EclipseImportBase importBase = cache.get(key);
			if (importBase == null) {
				importBase = new EclipseImportBase(key);
				cache.put(key, importBase);
			}
			return importBase;
		}
	}

	@SuppressWarnings("unchecked")
	static Map<Key, EclipseImportBase> getImportBaseCacheCache(Map<UUID, Object> ctxUserCache) {
		synchronized (ctxUserCache) {
			Map<Key, EclipseImportBase> listCache = (Map<Key, EclipseImportBase>) ctxUserCache.get(CACHE_IMPORT_BASE_CACHE);
			if (listCache == null) {
				listCache = Collections.synchronizedMap(new HashMap<Key, EclipseImportBase>());
				ctxUserCache.put(CACHE_IMPORT_BASE_CACHE, listCache);
			}
			return listCache;
		}
	}

	private EclipseImportBase(Key key) throws CoreException {
		URI uri;
		try {
			uri = new URI(key.getRepositoryURI());
		} catch (URISyntaxException e) {
			throw BuckminsterException.fromMessage(e.getMessage());
		}

		String scheme = uri.getScheme();
		String path = uri.getPath();
		URL remoteLoc = null;
		File loc = null;
		boolean platf = false;

		if (scheme == null) {
			if (path == null || path.length() == 0)
				platf = true;
			else
				loc = new File(path);
		} else if ("file".equalsIgnoreCase(scheme)) //$NON-NLS-1$
			loc = new File(path);
		else {
			try {
				if (!(path.endsWith("/") || path.endsWith(".map") || path.endsWith(".xml") || path.endsWith(".jar"))) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					path += '/';
				remoteLoc = new URL(scheme, uri.getHost(), uri.getPort(), path);
			} catch (MalformedURLException e) {
				throw BuckminsterException.fromMessage(e.getMessage());
			}
		}

		this.location = loc;
		this.remoteLocation = remoteLoc;
		this.platform = platf;

		this.query = uri.getQuery();
		this.key = key;
		this.feature = IComponentType.ECLIPSE_FEATURE.equals(key.getRequest().getComponentTypeID());

		Map<String, String> params = URLUtils.queryAsParameters(uri.getQuery());
		String importType = params.get(PARAM_IMPORT_TYPE);
		if (importType == null)
			type = PluginImportOperation.IMPORT_UNKNOWN;
		else if (IMPORT_TYPE_BINARY.equalsIgnoreCase(importType))
			type = PluginImportOperation.IMPORT_BINARY;
		else if (IMPORT_TYPE_LINKED.equalsIgnoreCase(importType))
			type = PluginImportOperation.IMPORT_BINARY_WITH_LINKS;
		else if (IMPORT_TYPE_SOURCE.equalsIgnoreCase(importType))
			type = PluginImportOperation.IMPORT_WITH_SOURCE;
		else
			throw BuckminsterException.fromMessage(NLS.bind(Messages.invalid_import_type_0, importType));

	}

	@Override
	public boolean equals(Object o) {
		return o == this || (o instanceof EclipseImportBase && ((EclipseImportBase) o).key.equals(key));
	}

	@Override
	public int hashCode() {
		return key.hashCode();
	}

	String getComponentName() {
		return key.getRequest().getName();
	}

	List<IFeatureModel> getFeatureModels(EclipseImportReaderType readerType, IProgressMonitor monitor) throws CoreException {
		return readerType.getFeatureModels(getLocation(), getComponentName(), monitor);
	}

	Key getKey() {
		return key;
	}

	final File getLocation() throws CoreException {
		if (location == null && !platform)
			throw BuckminsterException.fromMessage(Messages.site_is_not_local);
		return location;
	}

	List<IPluginModelBase> getPluginModels(EclipseImportReaderType readerType, IProgressMonitor monitor) throws CoreException {
		return readerType.getPluginModels(getLocation(), getComponentName(), monitor);
	}

	String getQuery() {
		return query;
	}

	final URL getRemoteLocation() throws CoreException {
		if (remoteLocation == null) {
			try {
				return location.toURI().toURL();
			} catch (MalformedURLException e) {
				throw BuckminsterException.wrap(e);
			}
		}
		return remoteLocation;
	}

	int getType() {
		return type;
	}

	boolean isFeature() {
		return feature;
	}

	boolean isLocal() {
		return remoteLocation == null;
	}

	/**
	 * Should this bundle be unpacked (set in the enclosing feature.xml)
	 * 
	 * @return true if supposed to be unpacked
	 */
	boolean isUnpack() {
		return unpack;
	}

	/**
	 * Should this bundle be unpacked (set in the enclosing feature.xml)
	 * 
	 * @param unpack
	 */
	void setUnpack(boolean unpack) {
		this.unpack = unpack;
	}
}
