/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.query.model;

import static org.eclipse.buckminster.core.XMLConstants.BM_CQUERY_NS;
import static org.eclipse.buckminster.core.XMLConstants.BM_CQUERY_PREFIX;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.common.model.SAXEmitter;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.model.IUUIDPersisted;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.parser.IParserFactory;
import org.eclipse.buckminster.core.query.IAdvisorNode;
import org.eclipse.buckminster.core.query.IComponentQuery;
import org.eclipse.buckminster.core.query.builder.AdvisorNodeBuilder;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.rmap.model.ProviderScore;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.buckminster.sax.UUIDKeyed;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.equinox.internal.provisional.p2.metadata.VersionRange;
import org.eclipse.osgi.util.NLS;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class ComponentQuery extends UUIDKeyed implements IUUIDPersisted, IComponentQuery
{
	public static final String ATTR_PROPERTIES = "properties"; //$NON-NLS-1$

	public static final String ATTR_RESOURCE_MAP = "resourceMap"; //$NON-NLS-1$

	public static final String ATTR_SHORT_DESC = "shortDesc"; //$NON-NLS-1$

	public static final String ELEM_ROOT_REQUEST = "rootRequest"; //$NON-NLS-1$

	public static final int SEQUENCE_NUMBER = 4;

	public static final String TAG = "componentQuery"; //$NON-NLS-1$

	public static ComponentQuery fromStream(URL url, IConnectContext cctx, InputStream stream, boolean validating)
			throws CoreException
	{
		try
		{
			IParserFactory pf = CorePlugin.getDefault().getParserFactory();
			IParser<ComponentQuery> parser = pf.getComponentQueryParser(false);
			ComponentQuery cquery = parser.parse(url.toString(), stream);
			cquery.setConnectContext(cctx);
			return cquery;
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public static ComponentQuery fromURL(URL url, IConnectContext cctx, boolean validating) throws CoreException
	{
		InputStream stream = null;
		try
		{
			stream = DownloadManager.read(url, cctx);
			return fromStream(url, cctx, stream, validating);
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(stream);
		}
	}

	private final List<AdvisorNode> m_advisorNodes;

	private transient Map<String, String> m_allProperties;

	private final Documentation m_documentation;

	private final Map<String, String> m_properties;

	private final URL m_contextURL;

	private final String m_propertiesURL;

	private final String m_resourceMapURL;

	private final ComponentRequest m_rootRequest;

	private final String m_shortDesc;

	private transient IConnectContext m_connectContext;

	public ComponentQuery(ComponentQueryBuilder bld)
	{
		m_documentation = bld.getDocumentation();
		m_shortDesc = bld.getShortDesc();
		m_propertiesURL = bld.getPropertiesURL();
		m_resourceMapURL = bld.getResourceMapURL();
		m_rootRequest = bld.getRootRequest();

		List<AdvisorNodeBuilder> advisorNodeBuilders = bld.getAdvisoryNodes();
		if(advisorNodeBuilders.size() == 0)
			m_advisorNodes = Collections.emptyList();
		else
		{
			ArrayList<AdvisorNode> advisorNodes = new ArrayList<AdvisorNode>(advisorNodeBuilders.size());
			for(AdvisorNodeBuilder nodeBld : advisorNodeBuilders)
				advisorNodes.add(nodeBld.create());
			m_advisorNodes = Collections.unmodifiableList(advisorNodes);
		}

		Map<String, String> properties = bld.getDeclaredProperties();
		if(properties == null || properties.size() == 0)
			m_properties = Collections.emptyMap();
		else
			m_properties = Collections.unmodifiableMap(new ExpandingProperties<String>(properties));

		m_contextURL = bld.getContextURL();
	}

	public boolean allowCircularDependency(ComponentName cName)
	{
		IAdvisorNode node = getMatchingNode(cName);
		return node == null
				? false
				: node.allowCircularDependency();
	}

	public List<? extends IAdvisorNode> getAdvisoryNodes()
	{
		return m_advisorNodes;
	}

	/**
	 * @deprecated Use {@link #getAttributes(ComponentName, Map)}
	 */
	@Deprecated
	public List<String> getAttributes(ComponentName cName)
	{
		IAdvisorNode node = getMatchingNode(cName);
		return node == null
				? Collections.<String> emptyList()
				: node.getAttributes();
	}

	public List<String> getAttributes(ComponentName cName, Map<String, ? extends Object> properties)
	{
		IAdvisorNode node = getMatchingNode(cName, properties);
		return node == null
				? Collections.<String> emptyList()
				: node.getAttributes();
	}

	/**
	 * @deprecated Use {@link #getBranchTagPath(ComponentName, Map)}
	 */
	@Deprecated
	public VersionSelector[] getBranchTagPath(ComponentName cName)
	{
		IAdvisorNode node = getMatchingNode(cName);
		return node == null
				? VersionSelector.EMPTY_PATH
				: node.getBranchTagPath();
	}

	public VersionSelector[] getBranchTagPath(ComponentName cName, Map<String, ? extends Object> properties)
	{
		IAdvisorNode node = getMatchingNode(cName, properties);
		return node == null
				? VersionSelector.EMPTY_PATH
				: node.getBranchTagPath();
	}

	public IConnectContext getConnectContext()
	{
		return m_connectContext;
	}

	public URL getContextURL()
	{
		return m_contextURL;
	}

	public Map<String, String> getDeclaredProperties()
	{
		return m_properties;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public Documentation getDocumentation()
	{
		return m_documentation;
	}

	public ComponentRequest getExpandedRootRequest(Map<String, ? extends Object> properties)
	{
		String name = m_rootRequest.getName();
		String expName = ExpandingProperties.expand(properties, name, 0);
		return name.equals(expName)
				? m_rootRequest
				: new ComponentRequest(expName, m_rootRequest.getComponentTypeID(), m_rootRequest.getVersionRange());
	}

	public synchronized Map<String, String> getGlobalProperties()
	{
		if(m_allProperties != null)
			return m_allProperties;

		m_allProperties = new ExpandingProperties<String>();
		m_allProperties.putAll(m_properties);

		if(m_propertiesURL != null)
		{
			URL propsURL = getResolvedPropertiesURL();
			InputStream input = null;
			try
			{
				input = DownloadManager.read(propsURL, getConnectContext());
				Map<String, String> urlProps = new BMProperties(input);
				if(urlProps.size() > 0)
				{
					m_allProperties = new ExpandingProperties<String>(m_allProperties);
					m_allProperties.putAll(urlProps);
				}
			}
			catch(Exception e)
			{
				// We allow missing properties but we log it nevertheless
				//
				CorePlugin.getLogger().info(NLS.bind(Messages.Unable_to_read_property_file_0_1, propsURL, e.toString()));
			}
			finally
			{
				IOUtils.close(input);
			}
		}
		return m_allProperties;
	}

	/**
	 * @deprecated Use {@link #getMatchingNode(ComponentName, Map)}
	 */
	@Deprecated
	public IAdvisorNode getMatchingNode(ComponentName cName)
	{
		String name = cName.getName();
		for(IAdvisorNode aNode : m_advisorNodes)
		{
			Pattern pattern = aNode.getNamePattern();
			if(pattern.matcher(name).find())
			{
				String matchingType = aNode.getComponentTypeID();
				if(matchingType == null || matchingType.equals(cName.getComponentTypeID()))
					return aNode;
			}
		}
		return null;
	}

	public IAdvisorNode getMatchingNode(ComponentName cName, Map<String, ? extends Object> properties)
	{
		String name = cName.getName();
		for(IAdvisorNode aNode : m_advisorNodes)
		{
			Pattern pattern = aNode.getNamePattern();
			if(!(pattern == null || pattern.matcher(name).find()))
				continue;

			String matchingType = aNode.getComponentTypeID();
			if(!(matchingType == null || matchingType.equals(cName.getComponentTypeID())))
				continue;

			Filter filter = aNode.getFilter();
			if(filter == null || filter.match(properties))
				return aNode;
		}
		return null;
	}

	public AdvisorNode getNodeByCriteria(Pattern pattern, String componentType, Filter filter)
	{
		for(AdvisorNode node : m_advisorNodes)
			if(Trivial.equalsAllowNull(node.getNamePattern(), pattern)
					&& Trivial.equalsAllowNull(node.getComponentTypeID(), componentType)
					&& Trivial.equalsAllowNull(node.getFilter(), filter))
				return node;
		return null;
	}

	/**
	 * @deprecated Use {@link #getNodeByCriteria(Pattern, String, Filter)}
	 */
	@Deprecated
	public IAdvisorNode getNodeByPattern(String pattern, String componentTypeID)
	{
		for(IAdvisorNode node : m_advisorNodes)
			if(node.getNamePattern().toString().equals(pattern)
					&& Trivial.equalsAllowNull(node.getComponentTypeID(), componentTypeID))
				return node;
		return null;
	}

	/**
	 * @deprecated Use {@link #getOverlayFolder(ComponentName, Map)}
	 */
	@Deprecated
	public URL getOverlayFolder(ComponentName cName)
	{
		IAdvisorNode node = getMatchingNode(cName);
		return node == null
				? null
				: node.getOverlayFolder();
	}

	public URL getOverlayFolder(ComponentName cName, Map<String, ? extends Object> properties)
	{
		IAdvisorNode node = getMatchingNode(cName, properties);
		return node == null
				? null
				: node.getOverlayFolder();
	}

	public String getPropertiesURL()
	{
		return m_propertiesURL;
	}

	/**
	 * @deprecated Use {@link #getProviderScore(ComponentName, boolean, boolean, Map)}
	 */
	@Deprecated
	public ProviderScore getProviderScore(ComponentName cName, boolean mutable, boolean source)
	{
		return getProviderScore(cName, mutable, source, getGlobalProperties());
	}

	public ProviderScore getProviderScore(ComponentName cName, boolean mutable, boolean source,
			Map<String, ? extends Object> properties)
	{
		IAdvisorNode node = getMatchingNode(cName, properties);
		if(node == null)
			return ProviderScore.GOOD;

		ProviderScore mutableScore = ProviderScore.FAIR;
		switch(node.getMutableLevel())
		{
		case REQUIRE:
			if(!mutable)
				return ProviderScore.REJECTED;
			mutableScore = ProviderScore.PREFERRED;
			break;
		case DESIRE:
			mutableScore = mutable
					? ProviderScore.GOOD
					: ProviderScore.BAD;
			break;
		case AVOID:
			mutableScore = mutable
					? ProviderScore.BAD
					: ProviderScore.GOOD;
			break;
		case REJECT:
			if(mutable)
				return ProviderScore.REJECTED;
			mutableScore = ProviderScore.PREFERRED;
			break;
		default:
		}

		ProviderScore sourceScore = ProviderScore.FAIR;
		switch(node.getSourceLevel())
		{
		case REQUIRE:
			if(!source)
				return ProviderScore.REJECTED;
			sourceScore = ProviderScore.PREFERRED;
			break;
		case DESIRE:
			sourceScore = source
					? ProviderScore.GOOD
					: ProviderScore.BAD;
			break;
		case AVOID:
			sourceScore = source
					? ProviderScore.BAD
					: ProviderScore.GOOD;
			break;
		case REJECT:
			if(source)
				return ProviderScore.REJECTED;
			sourceScore = ProviderScore.PREFERRED;
			break;
		default:
		}
		return ProviderScore.values()[(sourceScore.ordinal() + mutableScore.ordinal()) / 2];
	}

	/**
	 * @deprecated Use {@link #getResolutionPrio(ComponentName, Map)}
	 */
	@Deprecated
	public int[] getResolutionPrio(ComponentName cName)
	{
		IAdvisorNode node = getMatchingNode(cName);
		return node == null
				? IAdvisorNode.DEFAULT_RESOLUTION_PRIO
				: node.getResolutionPrio();
	}

	public int[] getResolutionPrio(ComponentName cName, Map<String, ? extends Object> properties)
	{
		IAdvisorNode node = getMatchingNode(cName, properties);
		return node == null
				? IAdvisorNode.DEFAULT_RESOLUTION_PRIO
				: node.getResolutionPrio();
	}

	public URL getResolvedPropertiesURL()
	{
		return m_propertiesURL == null
				? null
				: URLUtils.resolveURL(m_contextURL, ExpandingProperties.expand(BMProperties.getSystemProperties(),
						m_propertiesURL, 0));
	}

	public URL getResolvedResourceMapURL()
	{
		return m_resourceMapURL == null
				? null
				: URLUtils.resolveURL(m_contextURL, ExpandingProperties.expand(BMProperties.getSystemProperties(),
						m_resourceMapURL, 0));
	}

	public String getResourceMapURL()
	{
		return m_resourceMapURL;
	}

	public String getRevision(ComponentName cName, Map<String, ? extends Object> properties)
	{
		IAdvisorNode node = getMatchingNode(cName, properties);
		return node == null
				? null
				: node.getRevision();
	}

	public ComponentRequest getRootRequest()
	{
		return m_rootRequest;
	}

	public String getShortDesc()
	{
		return m_shortDesc;
	}

	public String getTagInfo()
	{
		return NLS.bind(Messages.Query_for_0, m_rootRequest);
	}

	/**
	 * @deprecated Use {@link #getTimestamp(ComponentName, Map)}
	 */
	@Deprecated
	public Date getTimestamp(ComponentName cName)
	{
		IAdvisorNode node = getMatchingNode(cName);
		return node == null
				? null
				: node.getTimestamp();
	}

	public Date getTimestamp(ComponentName cName, Map<String, ? extends Object> properties)
	{
		IAdvisorNode node = getMatchingNode(cName, properties);
		return node == null
				? null
				: node.getTimestamp();
	}

	/**
	 * @deprecated Use {@link #getVersionOverride(ComponentName, Map)}
	 */
	@Deprecated
	public VersionRange getVersionOverride(ComponentName cName)
	{
		IAdvisorNode node = getMatchingNode(cName);
		return node == null
				? null
				: node.getVersionOverride();
	}

	public VersionRange getVersionOverride(ComponentName cName, Map<String, ? extends Object> properties)
	{
		IAdvisorNode node = getMatchingNode(cName, properties);
		return node == null
				? null
				: node.getVersionOverride();
	}

	public boolean isPersisted(StorageManager sm) throws CoreException
	{
		return false;
	}

	/**
	 * @deprecated Use {@link #isPrune(ComponentName, Map)}
	 */
	@Deprecated
	public boolean isPrune(ComponentName cName)
	{
		IAdvisorNode node = getMatchingNode(cName);
		return node == null
				? false
				: node.isPrune();
	}

	public boolean isPrune(ComponentName cName, Map<String, ? extends Object> properties)
	{
		IAdvisorNode node = getMatchingNode(cName, properties);
		return node == null
				? false
				: node.isPrune();
	}

	public void remove(StorageManager sm) throws CoreException
	{
		throw new UnsupportedOperationException();
	}

	public void removeAdvisorNode(IAdvisorNode node)
	{
		m_advisorNodes.remove(node);
	}

	public ComponentQuery resolve()
	{
		ComponentQueryBuilder bld = new ComponentQueryBuilder();
		bld.initFrom(this);
		URL tmp = getResolvedPropertiesURL();
		if(tmp != null)
			bld.setPropertiesURL(tmp.toString());
		tmp = getResolvedResourceMapURL();
		if(tmp != null)
			bld.setResourceMapURL(tmp.toString());
		bld.setContextURL(null);
		return bld.createComponentQuery();
	}

	/**
	 * @deprecated Use {@link #skipComponent(ComponentName, Map)}
	 */
	@Deprecated
	public boolean skipComponent(ComponentName cName)
	{
		IAdvisorNode node = getMatchingNode(cName);
		return node == null
				? false
				: node.skipComponent();
	}

	public boolean skipComponent(ComponentName cName, Map<String, ? extends Object> properties)
	{
		IAdvisorNode node = getMatchingNode(cName, properties);
		return node == null
				? false
				: node.skipComponent();
	}

	public void store(StorageManager sm) throws CoreException
	{
		throw new UnsupportedOperationException();
	}

	public void toSax(ContentHandler handler) throws SAXException
	{
		handler.startDocument();
		toSax(handler, BM_CQUERY_NS, BM_CQUERY_PREFIX, getDefaultTag());
		handler.endDocument();
	}

	@Override
	public void toSax(ContentHandler handler, String namespace, String prefix, String localName) throws SAXException
	{
		handler.startPrefixMapping(BM_CQUERY_PREFIX, BM_CQUERY_NS);
		super.toSax(handler, namespace, prefix, localName);
		handler.endPrefixMapping(BM_CQUERY_PREFIX);
	}

	/**
	 * @deprecated Use {@link #useMaterialization(ComponentName, Map)}
	 */
	@Deprecated
	public boolean useMaterialization(ComponentName cName)
	{
		IAdvisorNode node = getMatchingNode(cName);
		return node == null
				? true
				: node.isUseMaterialization();
	}

	public boolean useMaterialization(ComponentName cName, Map<String, ? extends Object> properties)
	{
		IAdvisorNode node = getMatchingNode(cName, properties);
		return node == null
				? true
				: node.isUseMaterialization();
	}

	/**
	 * @deprecated Use {@link #useResolutionService(ComponentName, Map)}
	 */
	@Deprecated
	public boolean useResolutionService(ComponentName cName)
	{
		IAdvisorNode node = getMatchingNode(cName);
		return node == null
				? true
				: node.isUseRemoteResolution();
	}

	public boolean useResolutionService(ComponentName cName, Map<String, ? extends Object> properties)
	{
		IAdvisorNode node = getMatchingNode(cName, properties);
		return node == null
				? true
				: node.isUseRemoteResolution();
	}

	/**
	 * @deprecated Use {@link #useTargetPlatform(ComponentName, Map)}
	 */
	@Deprecated
	public boolean useTargetPlatform(ComponentName cName)
	{
		IAdvisorNode node = getMatchingNode(cName);
		return node == null
				? true
				: node.isUseTargetPlatform();
	}

	public boolean useTargetPlatform(ComponentName cName, Map<String, ? extends Object> properties)
	{
		IAdvisorNode node = getMatchingNode(cName, properties);
		return node == null
				? true
				: node.isUseTargetPlatform();
	}

	/**
	 * @deprecated Use {@link #useWorkspace(ComponentName, Map)}
	 */
	@Deprecated
	public boolean useWorkspace(ComponentName cName)
	{
		IAdvisorNode node = getMatchingNode(cName);
		return node == null
				? true
				: node.isUseWorkspace();
	}

	public boolean useWorkspace(ComponentName cName, Map<String, ? extends Object> properties)
	{
		IAdvisorNode node = getMatchingNode(cName, properties);
		return node == null
				? true
				: node.isUseWorkspace();
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		if(m_resourceMapURL != null)
			Utils.addAttribute(attrs, ATTR_RESOURCE_MAP, m_resourceMapURL.toString());
		if(m_propertiesURL != null)
			Utils.addAttribute(attrs, ATTR_PROPERTIES, m_propertiesURL.toString());
		if(m_shortDesc != null)
			Utils.addAttribute(attrs, ATTR_SHORT_DESC, m_shortDesc);
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException
	{
		if(m_documentation != null)
			m_documentation.toSax(handler, namespace, prefix, m_documentation.getDefaultTag());

		m_rootRequest.toSax(handler, namespace, prefix, ELEM_ROOT_REQUEST);
		SAXEmitter.emitProperties(handler, m_properties, namespace, prefix, true, false);

		for(AdvisorNode node : m_advisorNodes)
			node.toSax(handler, namespace, prefix, node.getDefaultTag());
	}

	@Override
	protected String getElementNamespace(String namespace)
	{
		return BM_CQUERY_NS;
	}

	@Override
	protected String getElementPrefix(String prefix)
	{
		return BM_CQUERY_PREFIX;
	}

	private void setConnectContext(IConnectContext cctx)
	{
		m_connectContext = cctx;
	}
}
