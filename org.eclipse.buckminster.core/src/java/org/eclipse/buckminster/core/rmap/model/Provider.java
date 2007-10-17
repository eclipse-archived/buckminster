/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.rmap.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.common.model.ValueHolder;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.ctype.MissingCSpecSourceException;
import org.eclipse.buckminster.core.helpers.MapUnion;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.metadata.ISaxableStorage;
import org.eclipse.buckminster.core.metadata.ReferentialIntegrityException;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.model.UUIDKeyed;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.version.IVersionConverter;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class Provider extends UUIDKeyed implements ISaxableElement
{
	public static final String ATTR_COMPONENT_TYPES = "componentTypes";

	public static final String ATTR_MUTABLE = "mutable";

	public static final String ATTR_READER_TYPE = "readerType";

	public static final String ATTR_SOURCE = "source";

	public static final String ATTR_SPACE = "space";

	public static final String ATTR_VERSION_CONVERTER = "versionConverter";

	public static final String TAG = "provider";

	public static final String TAG_URI = "uri";

	public static final int SEQUENCE_NUMBER = 2;

	private final Documentation m_documentation;

	private final String[] m_componentTypeIDs;

	private final boolean m_mutable;

	private final String m_readerTypeId;

	private final String m_space;

	private final boolean m_source;

	private final ValueHolder m_uri;

	private final VersionConverterDesc m_versionConverter;
	
	private final SearchPath m_searchPath;

	public Provider(String remoteReaderType, String[] componentTypeIDs, String uri)
	{
		this(null, remoteReaderType, componentTypeIDs, null, new Format(uri), null, false, false, null);
	}

	/**
	 * @param searchPath The owner searchPath
	 * @param remoteReaderType The id of a remote reader type.
	 * @param componentType The id of a component type.
	 * @param managedCategories Categories managed by the provider.
	 * @param versionConverterType The id of a version converter type.
	 * @param uri The holder that will produce the type specific string.
	 * @param space The space that provides the components
	 * @param mutable Set to <code>true</code> if this provider should provide content that can be
	 *            modified and commited back.
	 * @param source Set to <code>true</code> if this provider will provide source.
	 */
	public Provider(SearchPath searchPath, String remoteReaderType, String[] componentTypeIDs,
		VersionConverterDesc versionConverterDesc, Format uri, String space, boolean mutable, boolean source,
		Documentation documentation)
	{
		m_searchPath = searchPath;
		m_readerTypeId = remoteReaderType;
		m_componentTypeIDs = componentTypeIDs == null ? Trivial.EMPTY_STRING_ARRAY : componentTypeIDs;
		m_versionConverter = versionConverterDesc;
		m_uri = uri;
		m_space = space;
		m_mutable = mutable;
		m_source = source;
		m_documentation = documentation;
	}

	public void addPrefixMappings(HashMap<String, String> prefixMappings)
	{
		prefixMappings.put("xsi", javax.xml.XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);
	}

	public ProviderMatch findMatch(NodeQuery query, MultiStatus problemCollector, IProgressMonitor monitor)
	throws CoreException
	{
		IVersionFinder versionFinder = null;
		monitor.beginTask(null, 100);
		try
		{
			Logger logger = CorePlugin.getLogger();
			ComponentRequest request = query.getComponentRequest();
			String componentTypeID = request.getComponentTypeID();
			String providerURI = getURI(query.getProperties());
			String readerType = getReaderTypeId();
	
			// The component request is equipped with a component type. It must
			// match the types that this provider provides.
			//
			IComponentType[] componentTypes = getComponentTypes();
			if(componentTypeID != null)
			{
				boolean found = false;
				int idx = componentTypes.length;
				while(--idx >= 0)
				{
					IComponentType ctype = componentTypes[idx];
					if(ctype.getId().equals(componentTypeID))
					{
						// Limit the component types to this one type
						//
						componentTypes = new IComponentType[] { ctype };
						found = true;
						break;
					}
				}

				if(!found)
				{
					// The ECLIPSE_PLATFORM reader is silent here since it is always consulted
					//
					if(!getReaderTypeId().equals(IReaderType.ECLIPSE_PLATFORM))
					{
						String msg = String.format("Provider %s(%s): does not provide components of type %s",
								readerType, providerURI, componentTypeID);
						problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, msg, null));
					}
					return null;
				}
			}

			ProviderScore score = query.getProviderScore(isMutable(), hasSource());
			if(score == ProviderScore.REJECTED)
			{
				String msg = String.format("Provider %s(%s): Score is below threshold", readerType, providerURI);
				problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, msg, null));
				return null;
			}

			VersionMatch candidate = null;
			IComponentType ctypeUsed = null;
			CoreException problem = null;
			try
			{
				for(IComponentType ctype : componentTypes)
				{
					try
					{
						versionFinder = getReaderType().getVersionFinder(this, ctype, query, MonitorUtils.subMonitor(monitor, 20));
						candidate = versionFinder.getBestVersion(MonitorUtils.subMonitor(monitor, 80));
						if(candidate == null)
							continue;
						ctypeUsed = ctype;
					}
					catch(MissingCSpecSourceException e)
					{
						continue;
					}
					break;
				}
			}
			catch(CoreException e)
			{
				problem = e;
			}
	
			String componentName = request.getName();
			IVersionDesignator desiredVersion = query.getVersionDesignator();
			if(candidate == null)
			{
				String msg;
				if(desiredVersion == null)
					msg = String.format("Provider %s(%s): No match found for component %s", readerType,
						providerURI, componentName);
				else
					msg = String.format(
						"Provider %s(%s): No match found for component %s using version designator %s",
						readerType, providerURI, componentName, desiredVersion);
				problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, msg, problem));
				return null;
			}

			if(logger.isDebugEnabled())
			{
				if(desiredVersion == null)
					logger.debug(String.format("Provider %s(%s): Found a match for component %s, %s",
						readerType, providerURI, componentName, candidate));
				else
					logger.debug(String.format(
						"Provider %s(%s): Found a match for %s using version designator %s, %s",
						readerType, providerURI, componentName, desiredVersion, candidate));
			}
			return versionFinder.getProviderMatch(candidate, ctypeUsed, score);
		}
		finally
		{
			if(versionFinder != null)
				versionFinder.close();
			monitor.done();
		}			
	}

	public final IComponentType[] getComponentTypes() throws CoreException
	{
		CorePlugin plugin = CorePlugin.getDefault();
		int idx = m_componentTypeIDs.length;
		IComponentType[] ctypes = new IComponentType[idx];
		while(--idx >= 0)
			ctypes[idx] = plugin.getComponentType(m_componentTypeIDs[idx]);
		return ctypes;
	}

	public final String[] getComponentTypeIDs()
	{
		return m_componentTypeIDs;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public Documentation getDocumentation()
	{
		return m_documentation;
	}

	public final IReaderType getReaderType() throws CoreException
	{
		return CorePlugin.getDefault().getReaderType(m_readerTypeId);
	}

	public final String getReaderTypeId()
	{
		return m_readerTypeId;
	}

	public final SearchPath getSearchPath()
	{
		return m_searchPath;
	}

	public final String getSpace()
	{
		return m_space;
	}

	/**
	 * @return Returns the typeSpecificURI.
	 */
	public final String getURI(Map<String, String> properties)
	{
		if(m_searchPath != null)
		{
			ResourceMap rmap = m_searchPath.getResourceMap();
			Map<String,String> dfltProps = rmap.getProperties();
			if(!dfltProps.isEmpty())
				properties = new MapUnion<String, String>(properties, dfltProps);
		}
		return m_uri.getValue(properties);
	}

	public IVersionConverter getVersionConverter() throws CoreException
	{
		VersionConverterDesc vcd = getVersionConverterDesc();
		return vcd == null ? null : vcd.getVersionConverter();
	}

	public final VersionConverterDesc getVersionConverterDesc()
	{
		return m_versionConverter;
	}

	/**
	 * @return Returns the hasSource.
	 */
	public final boolean hasSource()
	{
		return m_source;
	}

	/**
	 * @return Returns the readOnly.
	 */
	public final boolean isMutable()
	{
		return m_mutable;
	}

	public boolean isPersisted() throws CoreException
	{
		return getStorage().contains(this);
	}

	public void remove() throws CoreException
	{
		UUID thisId = getId();
		StorageManager sm = StorageManager.getDefault();
		if(!sm.getResolutions().getReferencingKeys(thisId, "providerId").isEmpty())
			throw new ReferentialIntegrityException(this, "remove", "Referenced from Resolution");

		getStorage().removeElement(thisId);
	}

	public void store() throws CoreException
	{
		getStorage().putElement(this);
	}

	public void toSax(ContentHandler handler) throws SAXException
	{
		handler.startDocument();

		HashMap<String, String> prefixMappings = new HashMap<String, String>();
		addPrefixMappings(prefixMappings);
		for(Map.Entry<String, String> pfxMapping : prefixMappings.entrySet())
			handler.startPrefixMapping(pfxMapping.getKey(), pfxMapping.getValue());

		toSax(handler, XMLConstants.BM_RMAP_NS, XMLConstants.BM_RMAP_PREFIX, getDefaultTag());
		for(String pfx : prefixMappings.keySet())
			handler.endPrefixMapping(pfx);

		handler.endDocument();
	}

	public void toSax(ContentHandler handler, String namespace, String prefix, String localName)
	throws SAXException
	{
		String qName = Utils.makeQualifiedName(prefix, localName);
		AttributesImpl attrs = new AttributesImpl();
		addAttributes(attrs);
		handler.startElement(namespace, localName, qName, attrs);
		emitElements(handler, XMLConstants.BM_RMAP_NS, XMLConstants.BM_RMAP_PREFIX);
		handler.endElement(namespace, localName, qName);
	}

	protected void addAttributes(AttributesImpl attrs)
	{
		Utils.addAttribute(attrs, ATTR_READER_TYPE, m_readerTypeId);
		if(m_componentTypeIDs.length > 0)
			Utils.addAttribute(attrs, ATTR_COMPONENT_TYPES, TextUtils.concat(m_componentTypeIDs, ","));
		if(m_space != null)
			Utils.addAttribute(attrs, ATTR_SPACE, m_space);
		Utils.addAttribute(attrs, ATTR_MUTABLE, Boolean.toString(m_mutable));
		Utils.addAttribute(attrs, ATTR_SOURCE, Boolean.toString(m_source));
	}

	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException
	{
		if(m_documentation != null)
			m_documentation.toSax(handler, namespace, prefix, m_documentation.getDefaultTag());
		m_uri.toSax(handler, namespace, prefix, TAG_URI);
		if(m_versionConverter != null)
			m_versionConverter.toSax(handler, namespace, prefix, m_versionConverter.getDefaultTag());
	}

	private ISaxableStorage<Provider> getStorage() throws CoreException
	{
		return StorageManager.getDefault().getProviders();
	}
}
