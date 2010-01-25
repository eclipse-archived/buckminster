/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.mapprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.cspec.IComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.ProviderScore;
import org.eclipse.buckminster.core.rmap.model.SearchPath;
import org.eclipse.buckminster.core.rmap.model.VersionConverterDesc;
import org.eclipse.buckminster.core.version.IVersionConverter;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.pde.PDEPlugin;
import org.eclipse.buckminster.pde.mapfile.MapFile;
import org.eclipse.buckminster.pde.mapfile.MapFileEntry;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.osgi.util.NLS;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class PDEMapProvider extends Provider
{
	public static final String BM_PDEMAP_PROVIDER_NS = XMLConstants.BM_PREFIX + "PDEMapProvider-1.0"; //$NON-NLS-1$

	public static final String BM_PDEMAP_PROVIDER_PREFIX = "pmp"; //$NON-NLS-1$

	private static void collectEntries(File mapFile, Map<ComponentIdentifier, MapFileEntry> map) throws CoreException
	{
		InputStream input = null;
		try
		{
			input = new FileInputStream(mapFile);
			ArrayList<MapFileEntry> list = new ArrayList<MapFileEntry>();
			MapFile.parse(input, mapFile.getCanonicalPath(), list);
			for(MapFileEntry entry : list)
				map.put(entry.getComponentIdentifier(), entry);
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

	public PDEMapProvider(SearchPath searchPath, String remoteReaderType, String[] componentTypes,
			VersionConverterDesc vcDesc, Format uri, Filter resolutionFilter, Map<String, String> properties,
			Documentation documentation)
	{
		super(searchPath, remoteReaderType, componentTypes, vcDesc, uri, null, null, resolutionFilter, properties,
				null, documentation);
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		super.addAttributes(attrs);
		attrs.addAttribute(javax.xml.XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI, "type", "xsi:type", "CDATA", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				BM_PDEMAP_PROVIDER_PREFIX + ":PDEMapProvider"); //$NON-NLS-1$
	}

	@Override
	public void addPrefixMappings(HashMap<String, String> prefixMappings)
	{
		super.addPrefixMappings(prefixMappings);
		prefixMappings.put(BM_PDEMAP_PROVIDER_PREFIX, BM_PDEMAP_PROVIDER_NS);
	}

	private void cacheMap(Map<UUID, Object> userCache, Map<ComponentIdentifier, MapFileEntry> map)
	{
		userCache.put(getId(), map);
	}

	@Override
	public ProviderMatch findMatch(NodeQuery query, MultiStatus problemCollector, IProgressMonitor monitor)
			throws CoreException
	{
		monitor.beginTask("", 100); //$NON-NLS-1$
		try
		{
			String providerURI = getURI(query.getProperties());
			String readerType = getReaderTypeId();
			ProviderScore score = query.getProviderScore(isMutable(), hasSource());
			if(score == ProviderScore.REJECTED)
			{
				String msg = NLS.bind(Messages.provider_0_for_1_score_below_treshold, readerType, providerURI);
				problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, msg, null));
				return null;
			}

			MapFileEntry tv = getMapFileEntry(query, problemCollector, getMap(query, problemCollector,
					MonitorUtils.subMonitor(monitor, 50)));

			if(tv == null)
				//
				// Map was not materialized
				//
				return null;

			Map<String, String> properties = tv.getProperties();
			Version v = null;
			String tag = properties.get("tag"); //$NON-NLS-1$
			VersionSelector vs = (tag == null)
					? null
					: VersionSelector.tag(tag);
			ComponentRequest rq = query.getComponentRequest();
			IVersionConverter vc = getVersionConverter();
			if(vc != null)
			{
				// Let's check that the given tag matches what we are asking
				// for.
				//
				v = vc.createVersion(vs);
				VersionRange vd = query.getVersionRange();
				if(!(vd == null || vd.isIncluded(v)))
					return null;
			}

			VersionMatch vm = new VersionMatch(v, vs, -1, null, null);
			IReaderType rt = tv.getReaderType();
			String repoLocator = rt.convertFetchFactoryLocator(properties, rq.getName());
			Format uri = new Format(repoLocator);
			Provider delegated = new Provider(getSearchPath(), rt.getId(), getComponentTypeIDs(),
					getVersionConverterDesc(), uri, null, null, getResolutionFilter(), getProviderProperties(), null,
					null);

			String ctypeID = rq.getComponentTypeID();
			if(ctypeID == null)
				return delegated.findMatch(query, problemCollector, monitor);

			IComponentType ctype = tv.getComponentIdentifier().getComponentType();
			ProviderMatch pm = new ProviderMatch(PDEMapProvider.this, ctype, vm, score, query);
			pm.setProvider(delegated);
			pm.setComponentType(ctype);
			return pm;
		}
		finally
		{
			monitor.done();
		}
	}

	@SuppressWarnings("unchecked")
	private Map<ComponentIdentifier, MapFileEntry> getCachedMap(Map<UUID, Object> userCache)
	{
		return (Map<ComponentIdentifier, MapFileEntry>)userCache.get(getId());
	}

	/**
	 * Returns a map that contains entries in the following form:
	 * 
	 * <pre>
	 *      &lt;elementType&gt;@&lt;elementID&gt; = &lt;REPOSITORYgt;, &lt;TAG&gt;, [...]
	 * </pre>
	 * 
	 * @return
	 */
	public Map<ComponentIdentifier, MapFileEntry> getMap(NodeQuery query, MultiStatus problemCollector,
			IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 700);
		Map<UUID, Object> userCache = query.getContext().getUserCache();
		synchronized(userCache)
		{
			Map<ComponentIdentifier, MapFileEntry> map = getCachedMap(userCache);
			if(map != null)
				return map;

			File tempFolder = null;
			try
			{
				tempFolder = FileUtils.createTempFolder("bucky", ".tmp"); //$NON-NLS-1$ //$NON-NLS-2$
				VersionSelector[] btPath = query.getBranchTagPath();
				if(btPath.length == 0)
					materializeMaps(tempFolder, null, query, MonitorUtils.subMonitor(monitor, 500));
				else
				{
					CoreException lastException = null;
					for(VersionSelector bt : btPath)
					{
						try
						{
							materializeMaps(tempFolder, bt, query, MonitorUtils.subMonitor(monitor, 500));
							lastException = null;
							break;
						}
						catch(CoreException e)
						{
							lastException = e;
						}
					}
					if(lastException != null)
						throw lastException;
				}

				map = new HashMap<ComponentIdentifier, MapFileEntry>();
				String[] mapFiles = tempFolder.list();
				if(mapFiles == null || mapFiles.length == 0)
					return null;

				MonitorUtils.worked(monitor, 50);

				int amountPerFile = 100 / mapFiles.length;
				for(String file : mapFiles)
				{
					if(file.endsWith(".map")) //$NON-NLS-1$
						collectEntries(new File(tempFolder, file), map);
					MonitorUtils.worked(monitor, amountPerFile);
				}
				map = Collections.unmodifiableMap(map);
				cacheMap(userCache, map);
				return map;
			}
			catch(CoreException e)
			{
				problemCollector.add(e.getStatus());
				PDEPlugin.getLogger().debug(e.getMessage());
				return null;
			}
			finally
			{
				if(tempFolder != null)
					FileUtils.deleteRecursive(tempFolder, MonitorUtils.subMonitor(monitor, 50));
				monitor.done();
			}
		}
	}

	private MapFileEntry getMapFileEntry(NodeQuery query, MultiStatus problemCollector,
			Map<ComponentIdentifier, MapFileEntry> map)
	{
		if(map == null)
			return null;

		ComponentRequest wanted = query.getComponentRequest();
		String name = wanted.getName();
		String ctype = wanted.getComponentTypeID();
		VersionRange vd = wanted.getVersionRange();

		IComponentIdentifier candidate = null;
		MapFileEntry candidateEntry = null;
		for(Map.Entry<ComponentIdentifier, MapFileEntry> entry : map.entrySet())
		{
			ComponentIdentifier cn = entry.getKey();
			if(cn.getName().equals(name) && Trivial.equalsAllowNull(ctype, cn.getComponentTypeID()))
			{
				Version v = cn.getVersion();
				if(vd != null)
				{
					if(!(v == null || vd.isIncluded(v)))
						continue;
				}

				if(candidate != null)
				{
					if(v == null)
						continue;

					if(candidate.getVersion() != null && candidate.getVersion().compareTo(v) > 0)
						continue;
				}
				candidate = cn;
				candidateEntry = entry.getValue();
			}
		}

		if(candidateEntry == null)
		{
			String msg = NLS.bind(Messages.PDEMapProvider_0_for_1_unable_to_find_2_in_map, new Object[] {
					getReaderTypeId(), getURI(query.getProperties()), wanted });

			problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, msg, null));
			PDEPlugin.getLogger().debug(msg);
		}
		return candidateEntry;
	}

	private void materializeMaps(File tempFolder, VersionSelector vs, NodeQuery query, IProgressMonitor monitor)
			throws CoreException
	{
		MonitorUtils.begin(monitor, 500);

		ProviderMatch match = new ProviderMatch(this, CorePlugin.getDefault().getComponentType(IComponentType.UNKNOWN),
				new VersionMatch(null, vs, -1, new Date(), null), ProviderScore.GOOD, query);
		IComponentReader reader = match.getReader(MonitorUtils.subMonitor(monitor, 100));
		try
		{
			((ICatalogReader)reader).innerMaterialize(new Path(tempFolder.toString()), MonitorUtils.subMonitor(monitor,
					400));
		}
		finally
		{
			IOUtils.close(reader);
			MonitorUtils.done(monitor);
		}
	}
}
