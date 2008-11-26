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
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.pde.internal.imports.PluginImportOperation;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.update.core.IPluginEntry;
import org.eclipse.update.core.ISiteFeatureReference;

@SuppressWarnings("restriction")
final class EclipseImportBase
{
	static class Key
	{
		private final String m_repositoryURI;

		private final ComponentRequest m_request;

		Key(String repositoryURI, ComponentRequest request)
		{
			m_repositoryURI = repositoryURI;
			m_request = request;
		}

		@Override
		public boolean equals(Object o)
		{
			return this == o || (o instanceof Key) && m_repositoryURI.equals(((Key)o).m_repositoryURI)
					&& m_request.equals(((Key)o).m_request);
		}

		public String getRepositoryURI()
		{
			return m_repositoryURI;
		}

		public ComponentRequest getRequest()
		{
			return m_request;
		}

		@Override
		public int hashCode()
		{
			return m_repositoryURI.hashCode() * 37 + m_request.hashCode();
		}
	}

	/**
	 * Parameter in the resource URI that determines the search order when resolving the component. Valid values are
	 * &quot;binary&quot; and &quot;linked&quot; or &quot;source&quot;. Default is &quot;binary&quot;
	 */
	static final String PARAM_IMPORT_TYPE = "importType";

	static final String IMPORT_TYPE_BINARY = "binary";

	static final String IMPORT_TYPE_LINKED = "linked";

	static final String IMPORT_TYPE_SOURCE = "source";

	private final int m_type;

	private final boolean m_platform;

	private final boolean m_feature;

	private final Key m_key;

	private final String m_query;

	private final File m_location;

	private final URL m_remoteLocation;

	private static final UUID CACHE_IMPORT_BASE_CACHE = UUID.randomUUID();

	public static EclipseImportBase obtain(NodeQuery query, String repositoryURI) throws CoreException
	{
		Key key = new Key(repositoryURI, query.getComponentRequest());
		Map<Key, EclipseImportBase> cache = getImportBaseCacheCache(query.getContext().getUserCache());
		synchronized(cache)
		{
			EclipseImportBase importBase = cache.get(key);
			if(importBase == null)
			{
				importBase = new EclipseImportBase(key);
				cache.put(key, importBase);
			}
			return importBase;
		}
	}

	@SuppressWarnings("unchecked")
	static Map<Key, EclipseImportBase> getImportBaseCacheCache(Map<UUID, Object> ctxUserCache)
	{
		synchronized(ctxUserCache)
		{
			Map<Key, EclipseImportBase> listCache = (Map<Key, EclipseImportBase>)ctxUserCache
					.get(CACHE_IMPORT_BASE_CACHE);
			if(listCache == null)
			{
				listCache = Collections.synchronizedMap(new HashMap<Key, EclipseImportBase>());
				ctxUserCache.put(CACHE_IMPORT_BASE_CACHE, listCache);
			}
			return listCache;
		}
	}

	private EclipseImportBase(Key key) throws CoreException
	{
		URI uri;
		try
		{
			uri = new URI(key.getRepositoryURI());
		}
		catch(URISyntaxException e)
		{
			throw BuckminsterException.fromMessage(e.getMessage());
		}

		String scheme = uri.getScheme();
		String path = uri.getPath();
		URL remoteLocation = null;
		File location = null;
		boolean platform = false;

		if(scheme == null)
		{
			if(path == null || path.length() == 0)
				platform = true;
			else
				location = new File(path);
		}
		else if("file".equalsIgnoreCase(scheme))
			location = new File(path);
		else
		{
			try
			{
				if(!(path.endsWith("/") || path.endsWith(".map") || path.endsWith(".xml") || path.endsWith(".jar")))
					path += '/';
				remoteLocation = new URL(scheme, uri.getHost(), path);
			}
			catch(MalformedURLException e)
			{
				throw BuckminsterException.fromMessage(e.getMessage());
			}
		}

		m_location = location;
		m_remoteLocation = remoteLocation;
		m_platform = platform;

		m_query = uri.getQuery();
		m_key = key;
		m_feature = IComponentType.ECLIPSE_FEATURE.equals(key.getRequest().getComponentTypeID());

		Map<String, String> params = TextUtils.queryAsParameters(uri.getQuery());
		String importType = params.get(PARAM_IMPORT_TYPE);
		if(importType == null || IMPORT_TYPE_BINARY.equalsIgnoreCase(importType))
			m_type = PluginImportOperation.IMPORT_BINARY;
		else if(IMPORT_TYPE_LINKED.equalsIgnoreCase(importType))
			m_type = PluginImportOperation.IMPORT_BINARY_WITH_LINKS;
		else if(IMPORT_TYPE_SOURCE.equalsIgnoreCase(importType))
			m_type = PluginImportOperation.IMPORT_WITH_SOURCE;
		else
			throw BuckminsterException.fromMessage("Invalid import type: %s", importType);

	}

	@Override
	public boolean equals(Object o)
	{
		return o == this || (o instanceof EclipseImportBase && ((EclipseImportBase)o).m_key.equals(m_key));
	}

	@Override
	public int hashCode()
	{
		return m_key.hashCode();
	}

	String getComponentName()
	{
		return m_key.getRequest().getName();
	}

	List<IFeatureModel> getFeatureModels(EclipseImportReaderType readerType, IProgressMonitor monitor)
			throws CoreException
	{
		return readerType.getFeatureModels(getLocation(), getComponentName(), monitor);
	}

	List<ISiteFeatureReference> getFeatureReferences(EclipseImportReaderType readerType, IProgressMonitor monitor)
			throws CoreException
	{
		return readerType.getFeatureReferences(getRemoteLocation(), getComponentName(), monitor);
	}

	Key getKey()
	{
		return m_key;
	}

	final File getLocation() throws CoreException
	{
		if(m_location == null && !m_platform)
			throw BuckminsterException.fromMessage("site is not local");
		return m_location;
	}

	List<IPluginEntry> getPluginEntries(EclipseImportReaderType readerType, IConnectContext cctx, NodeQuery query,
			IProgressMonitor monitor) throws CoreException
	{
		return readerType.getPluginEntries(getRemoteLocation(), cctx, query, getComponentName(), monitor);
	}

	List<IPluginModelBase> getPluginModels(EclipseImportReaderType readerType, IProgressMonitor monitor)
			throws CoreException
	{
		return readerType.getPluginModels(getLocation(), getComponentName(), monitor);
	}

	String getQuery()
	{
		return m_query;
	}

	final URL getRemoteLocation() throws CoreException
	{
		if(m_remoteLocation == null)
		{
			try
			{
				return m_location.toURI().toURL();
			}
			catch(MalformedURLException e)
			{
				throw BuckminsterException.wrap(e);
			}
		}
		return m_remoteLocation;
	}

	int getType()
	{
		return m_type;
	}

	boolean isFeature()
	{
		return m_feature;
	}

	boolean isLocal()
	{
		return m_remoteLocation == null;
	}
}
