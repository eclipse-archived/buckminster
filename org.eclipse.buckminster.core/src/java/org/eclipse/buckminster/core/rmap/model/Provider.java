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
import org.eclipse.buckminster.core.version.VersionSelectorFactory;
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
	public static final String ATTR_COMPONENT_TYPE = "componentType";

	public static final String ATTR_MANAGED_CATEGORIES = "managedCategories";

	public static final String ATTR_MUTABLE = "mutable";

	public static final String ATTR_READER_TYPE = "readerType";

	public static final String ATTR_SOURCE = "source";

	public static final String ATTR_VERSION_CONVERTER = "versionConverter";

	public static final String TAG = "provider";

	public static final String TAG_URI = "uri";

	public static final int SEQUENCE_NUMBER = 1;

	private final Documentation m_documentation;

	private final String m_componentTypeId;

	private final String[] m_managedCategories;

	private final boolean m_mutable;

	private final String m_readerTypeId;

	private final boolean m_source;

	private final ValueHolder m_uri;

	private final VersionConverterDesc m_versionConverter;

	/**
	 * @param remoteReaderType The id of a remote reader type.
	 * @param componentType The id of a component type.
	 * @param managedCategories Categories managed by the provider.
	 * @param versionConverterType The id of a version converter type.
	 * @param uri The holder that will produce the type specific string.
	 * @param mutable Set to <code>true</code> if this provider should provide content that can be
	 *            modified and commited back.
	 * @param source Set to <code>true</code> if this provider will provide source.
	 */
	public Provider(String remoteReaderType, String componentType, String[] managedCategories,
		VersionConverterDesc versionConverterDesc, Format uri, boolean mutable, boolean source,
		Documentation documentation)
	{
		m_readerTypeId = remoteReaderType;
		m_componentTypeId = componentType;
		m_managedCategories = managedCategories == null ? Trivial.EMPTY_STRING_ARRAY : managedCategories;
		m_versionConverter = versionConverterDesc;
		m_uri = uri;
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
		Logger logger = CorePlugin.getLogger();
		ComponentRequest request = query.getComponentRequest();
		String componentName = request.getName();
		String category = request.getCategory();
		String providerURI = getURI(query.getProperties());
		IVersionDesignator desiredVersion = query.getVersionDesignator();
		String readerType = getReaderTypeId();

		// The component request is equipped with a category. If the provider
		// is limited to certain categories, one of them must match.
		//
		String[] managedCategories = getManagedCategories();
		if(managedCategories.length > 0)
		{
			boolean managed = false;
			if(category != null)
			{
				for(String c : managedCategories)
				{
					if(category.equals(c))
					{
						managed = true;
						break;
					}
				}
			}
			if(!managed)
			{
				if(!getReaderTypeId().equals(IReaderType.ECLIPSE_PLATFORM))
				{
					String msg = String.format("Provider %s(%s): Unable to manage %s", readerType, providerURI,
						category == null ? "requests without category" : "category " + category);
					problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, msg, null));
					logger.debug(msg);
				}
				return null;
			}
		}

		ProviderScore score = query.getProviderScore(isMutable(), hasSource());
		if(score == ProviderScore.REJECTED)
		{
			String msg = String.format("Provider %s(%s): Score is below threshold", readerType, providerURI);
			problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, msg, null));
			logger.debug(msg);
			return null;
		}

		VersionMatch candidate;
		CoreException problem;
		IVersionFinder versionFinder = null;
		monitor.beginTask(null, 100);
		try
		{
			versionFinder = getReaderType().getVersionFinder(this, query, MonitorUtils.subMonitor(monitor, 20));
			IVersionConverter versionConverter = getVersionConverter();

			IProgressMonitor subMon = MonitorUtils.subMonitor(monitor, 80);
			candidate = (desiredVersion == null || versionConverter == null)
				? versionFinder.getDefaultVersion(subMon) : versionFinder.getBestVersion(
					VersionSelectorFactory.createQuery(versionConverter, desiredVersion), subMon);
			problem = null;
		}
		catch(CoreException e)
		{
			candidate = null;
			problem = e;
		}
		finally
		{
			if(versionFinder != null)
				versionFinder.close();
			monitor.done();
		}

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
			logger.debug(msg);
			problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, msg, problem));
			return null;
		}

		if(logger.isDebugEnabled())
		{
			if(desiredVersion == null)
				logger.debug(String.format("Provider %s(%s): Found a match for component %s fixed on %s",
					readerType, providerURI, componentName, candidate.getFixedVersionSelector()));
			else
				logger.debug(String.format(
					"Provider %s(%s): Found a match for %s using version designator %s, found version is %s fixed on %s",
					readerType, providerURI, componentName, desiredVersion, candidate.getVersion(),
					candidate.getFixedVersionSelector()));
		}
		return new ProviderMatch(this, candidate, score, query);
	}

	public final IComponentType getComponentType() throws CoreException
	{
		return CorePlugin.getDefault().getComponentType(m_componentTypeId);
	}

	public final String getComponentTypeId()
	{
		return m_componentTypeId;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public Documentation getDocumentation()
	{
		return m_documentation;
	}

	/**
	 * When ruling out bad providers during resolve, it is important that we can find the provider
	 * as it was entered into a search path and not a provider that it in turn delegated to.
	 * Delegees will typically override this method and instead return the delegatee.
	 * @return
	 */
	public Provider getMain()
	{
		return this;
	}

	public String[] getManagedCategories()
	{
		return m_managedCategories;
	}

	public final IReaderType getReaderType() throws CoreException
	{
		return CorePlugin.getDefault().getReaderType(m_readerTypeId);
	}

	public final String getReaderTypeId()
	{
		return m_readerTypeId;
	}

	/**
	 * @return Returns the typeSpecificURI.
	 */
	public final String getURI(Map<String, String> properties)
	{
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
		Utils.addAttribute(attrs, ATTR_COMPONENT_TYPE, m_componentTypeId);
		int numCategories = m_managedCategories.length;
		if(numCategories > 0)
		{
			StringBuilder bld = new StringBuilder(m_managedCategories[0]);
			for(int idx = 1; idx < numCategories; ++idx)
			{
				bld.append(',');
				bld.append(m_managedCategories[1]);
			}
			Utils.addAttribute(attrs, ATTR_MANAGED_CATEGORIES, bld.toString());
		}
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
