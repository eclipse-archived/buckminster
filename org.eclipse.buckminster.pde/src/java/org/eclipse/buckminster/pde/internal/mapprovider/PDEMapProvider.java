/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.internal.mapprovider;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.ProviderScore;
import org.eclipse.buckminster.core.rmap.model.VersionConverterDesc;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionConverter;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.pde.PDEPlugin;
import org.eclipse.buckminster.pde.internal.EclipseImportReaderType;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.xml.sax.helpers.AttributesImpl;

public class PDEMapProvider extends Provider
{
	public static final String BM_PDEMAP_PROVIDER_NS = XMLConstants.BM_PREFIX + "PDEMapProvider-1.0";

	public static final String BM_PDEMAP_PROVIDER_PREFIX = "pmp";

	public PDEMapProvider(String remoteReaderType, String[] componentTypes, VersionConverterDesc vcDesc,
		Format uri, String space, boolean mutable, boolean source, Documentation documentation)
	{
		super(remoteReaderType, componentTypes, vcDesc, uri, space, mutable, source, documentation);
	}

	@Override
	public ProviderMatch findMatch(NodeQuery query, MultiStatus problemCollector, IProgressMonitor monitor)
	throws CoreException
	{
		monitor.beginTask("", 100);
		try
		{
			TypedValue tv = getTypedValue(query, problemCollector, 
				getMap(query, problemCollector, MonitorUtils.subMonitor(monitor, 50)));

			if(tv == null)
				//
				// Map was not materialized
				//
				return null;

			IVersion v = null;
			VersionSelector vs = VersionSelector.tag(tv.getTag());
			ComponentRequest rq = query.getComponentRequest();
			IVersionConverter vc = getVersionConverter();
			if(vc != null)
			{
				// Let's check that the given tag matches what we are asking
				// for.
				//
				v = vc.createVersion(vs);
				IVersionDesignator vd = query.getVersionDesignator();
				if(!(vd == null || vd.designates(v)))
					return null;
			}

			VersionMatch vm = new VersionMatch(v, vs, getSpace(), -1, null, null);
			CorePlugin plugin = CorePlugin.getDefault();
			IReaderType rt = plugin.getReaderType(tv.getType().toLowerCase());

			String repoLocator = rt.convertFetchFactoryLocator(tv.getValue(), rq.getName());
			URL repoURL = rt.convertToURL(repoLocator, vm);
			if(repoURL != null)
			{
				String path = repoURL.getPath();
				if(path.endsWith(".jar") || path.endsWith(".zip"))
				{
					repoLocator = copyToLocalSite(repoURL, query, monitor);
					rt = plugin.getReaderType(IReaderType.ECLIPSE_IMPORT);
				}
			}

			Format uri = new Format(repoLocator);
			Provider delegated = new Provider(rt.getId(), getComponentTypeIDs(), getVersionConverterDesc(), uri, getSpace(), isMutable(),
				hasSource(), null)
			{
				@Override
				public Provider getMain()
				{
					return PDEMapProvider.this;
				}
			};

			String ctypeID = rq.getComponentTypeID();
			if(ctypeID == null)
				return delegated.findMatch(query, problemCollector, monitor);
			return new ProviderMatch(delegated, plugin.getComponentType(ctypeID), vm, ProviderScore.GOOD, query);
		}
		finally
		{
			monitor.done();
		}
	}

	private String copyToLocalSite(URL repoURL, NodeQuery query, IProgressMonitor monitor) throws CoreException
	{
		// Use the import reader for this.
		//
		InputStream input = null;
		try
		{
			ComponentRequest rq = query.getComponentRequest();
			File tempSite = EclipseImportReaderType.getTempSite(query.getContext().getUserCache());	
			File destDir = new File(tempSite, rq.getComponentTypeID() + 's');

			input = URLUtils.openStream(repoURL, MonitorUtils.subMonitor(monitor, 45));
			FileUtils.copyFile(input, destDir, new Path(repoURL.toURI().getPath()).lastSegment(), MonitorUtils.subMonitor(monitor, 5));
			return tempSite.toURI().toURL().toExternalForm();
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		catch(URISyntaxException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(input);
		}
	}

	private TypedValue getTypedValue(NodeQuery query, MultiStatus problemCollector, Map<ComponentName, TypedValue> map)
	{
		if(map == null)
			return null;

		ComponentName wanted = query.getComponentRequest();
		String name = wanted.getName();
		String ctype = wanted.getComponentTypeID();

		for(Map.Entry<ComponentName, TypedValue> entry : map.entrySet())
		{
			ComponentName cn = entry.getKey();
			if(cn.getName().equals(name) && Trivial.equalsAllowNull(ctype, cn.getComponentTypeID()))
				return entry.getValue();
		}

		String msg = String.format("PDEMapProvider %s(%s): Unable to find %s in map",
			getReaderTypeId(),
			getURI(query.getProperties()),
			wanted);

		problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, msg, null));
		PDEPlugin.getLogger().debug(msg);
		return null;
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
	public Map<ComponentName, TypedValue> getMap(NodeQuery query, MultiStatus problemCollector,
		IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask("", 1000);
		Map<UUID, Object> userCache = query.getContext().getUserCache();
		synchronized(userCache)
		{
			Map<ComponentName, TypedValue> map = getCachedMap(userCache);
			if(map != null)
				return map;

			try
			{
				ProviderMatch match = new ProviderMatch(this, CorePlugin.getDefault().getComponentType(IComponentType.UNKNOWN),
					new VersionMatch(null, null, getSpace(), -1, new Date(),null), 
					ProviderScore.GOOD, query);

				File tempFolder = FileUtils.createTempFolder("bucky", ".tmp");
				IComponentReader reader = match.getReader(MonitorUtils.subMonitor(monitor, 100));
				try
				{
					((ICatalogReader)reader).innerMaterialize(new Path(tempFolder.toString()),
						MonitorUtils.subMonitor(monitor, 400));
				}
				finally
				{
					reader.close();
				}

				map = new HashMap<ComponentName, TypedValue>();
				String[] mapFiles = tempFolder.list();
				MonitorUtils.worked(monitor, 100);

				int amountPerFile = 400 / mapFiles.length;
				for(String file : mapFiles)
				{
					if(file.endsWith(".map"))
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
				monitor.done();
			}
		}
	}

	@Override
	public void addPrefixMappings(HashMap<String, String> prefixMappings)
	{
		super.addPrefixMappings(prefixMappings);
		prefixMappings.put(BM_PDEMAP_PROVIDER_PREFIX, BM_PDEMAP_PROVIDER_NS);
	}

	@Override
	protected void addAttributes(AttributesImpl attrs)
	{
		super.addAttributes(attrs);
		attrs.addAttribute(javax.xml.XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI, "type", "xsi:type",
			"CDATA", BM_PDEMAP_PROVIDER_PREFIX + ":PDEMapProvider");
	}

	static class TypedValue
	{
		private final String m_type;

		private final String m_tag;

		private final String m_value;

		TypedValue(String type, String tag, String value)
		{
			m_type = type;
			m_tag = tag;
			m_value = value;
		}

		String getType()
		{
			return m_type;
		}

		String getTag()
		{
			return m_tag;
		}

		String getValue()
		{
			return m_value;
		}
	}

	private static final Pattern s_mapEntryPattern = Pattern.compile("^"
		+ "\\s*([a-zA-Z_][a-zA-Z0-9_.-]*)\\s*@\\s*([a-zA-Z_][a-zA-Z0-9_.-]*)\\s*="
		+ "\\s*([a-zA-Z_][a-zA-Z0-9_.-]*)\\s*,\\s*(.*?)\\s*$");

	private void collectEntries(File mapFile, Map<ComponentName, TypedValue> map) throws CoreException
	{
		LineNumberReader input = null;
		try
		{
			input = new LineNumberReader(new FileReader(mapFile));
			String line;
			while((line = input.readLine()) != null)
			{
				Matcher matcher = s_mapEntryPattern.matcher(line);
				if(matcher.matches())
				{
					// The first entry might be either a repository type id or the tag. If
					// we find a repository using that id, we assume it indeed is a repository.
					// If not, we have to assume it's a tag.
					//
					String readerTypeId;
					String tag;
					String theRest = matcher.group(4);
					int cPos = theRest.indexOf(',');
					if(cPos < 0)
						continue;

					try
					{
						String testReaderTypeId = matcher.group(3).toLowerCase();
						CorePlugin.getDefault().getReaderType(testReaderTypeId);
						readerTypeId = testReaderTypeId;
						tag = theRest.substring(0, cPos);
						theRest = theRest.substring(cPos + 1);
					}
					catch(CoreException e)
					{
						readerTypeId = "cvs";
						tag = matcher.group(3);
					}
					map.put(new ComponentName(matcher.group(2), matcher.group(1)), new TypedValue(
						readerTypeId, tag, theRest));
				}
			}
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

	private void cacheMap(Map<UUID, Object> userCache, Map<ComponentName, TypedValue> map)
	{
		userCache.put(getId(), map);
	}

	@SuppressWarnings("unchecked")
	private Map<ComponentName, TypedValue> getCachedMap(Map<UUID, Object> userCache)
	{
		return (Map<ComponentName, TypedValue>)userCache.get(getId());
	}
}
