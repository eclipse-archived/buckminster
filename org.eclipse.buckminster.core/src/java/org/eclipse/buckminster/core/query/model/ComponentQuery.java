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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
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
import org.eclipse.buckminster.core.query.builder.AdvisorNodeBuilder;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.rmap.model.ProviderScore;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.buckminster.sax.UUIDKeyed;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;


/**
 * @author Thomas Hallgren
 */
public class ComponentQuery extends UUIDKeyed implements IUUIDPersisted
{
	public static final String ATTR_PROPERTIES = "properties";

	public static final String ATTR_RESOURCE_MAP = "resourceMap";

	public static final String ATTR_SHORT_DESC = "shortDesc";

	public static final String ELEM_ROOT_REQUEST = "rootRequest";

	public static final int SEQUENCE_NUMBER = 4;

	public static final String TAG = "componentQuery";

	public static ComponentQuery fromStream(URL url, InputStream stream, boolean validating) throws CoreException
	{
		try
		{
			IParserFactory pf = CorePlugin.getDefault().getParserFactory();
			IParser<ComponentQuery> parser = pf.getComponentQueryParser(false);
			return parser.parse(url.toString(), stream);
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public static ComponentQuery fromURL(URL url, boolean validating) throws CoreException
	{
		InputStream stream = null;
		try
		{
			stream = DownloadManager.read(url);
			return fromStream(url, stream, validating);
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

	public ComponentQuery(ComponentQueryBuilder bld)
	{
		m_documentation = bld.getDocumentation();
		m_shortDesc = bld.getShortDesc();
		m_propertiesURL = bld.getPropertiesURL();
		m_resourceMapURL = bld.getResourceMapURL();
		m_rootRequest = bld.getRootRequest();

		List<AdvisorNodeBuilder> advisorNodeBuilders = bld.getAdvisoryNodeList();
		if(advisorNodeBuilders.size() == 0)
			m_advisorNodes = Collections.emptyList();
		else
		{
			ArrayList<AdvisorNode> advisorNodes = new ArrayList<AdvisorNode>(advisorNodeBuilders.size());
			for(AdvisorNodeBuilder nodeBld : advisorNodeBuilders)
				advisorNodes.add(nodeBld.create());
			m_advisorNodes = Collections.unmodifiableList(advisorNodes);
		}

		Map<String,String> properties = bld.getProperties();
		if(properties == null || properties.size() == 0)
			m_properties = Collections.emptyMap();
		else
			m_properties = Collections.unmodifiableMap(new ExpandingProperties(properties));
		
		m_contextURL = bld.getContextURL();
	}

	public boolean allowCircularDependency(ComponentName cName)
	{
		AdvisorNode node = getMatchingNode(cName);
		return node == null ? false : node.allowCircularDependency();
	}

	public List<AdvisorNode> getAdvisoryNodes()
	{
		return m_advisorNodes;
	}
	
	public List<String> getAttributes(ComponentName cName)
	{
		AdvisorNode node = getMatchingNode(cName);
		return node == null ? Collections.<String>emptyList() : node.getAttributes();
	}

	public VersionSelector[] getBranchTagPath(ComponentName cName)
	{
		AdvisorNode node = getMatchingNode(cName);
		return node == null ? VersionSelector.EMPTY_PATH : node.getBranchTagPath();
	}

	public Map<String,String> getDeclaredProperties()
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

	public synchronized Map<String, String> getGlobalProperties()
	{
		if(m_allProperties != null)
			return m_allProperties;

		m_allProperties = new ExpandingProperties();
		m_allProperties.putAll(m_properties);

		if(m_propertiesURL != null)
		{
			URL propsURL = getResolvedPropertiesURL();
			InputStream input = null;
			try
			{
				input = DownloadManager.read(propsURL);
				Map<String,String> urlProps = new BMProperties(input);
				if(urlProps.size() > 0)
				{
					m_allProperties = new ExpandingProperties(m_allProperties);
					m_allProperties.putAll(urlProps);
				}
			}
			catch(Exception e)
			{
				// We allow missing properties but we log it nevertheless
				//
				CorePlugin.getLogger().info("Unable to read property file '%s' : %s", propsURL, e.toString());
			}
			finally
			{
				IOUtils.close(input);
			}
		}
		return m_allProperties;
	}

	public AdvisorNode getMatchingNode(ComponentName cName)
	{
		String name = cName.getName();
		for(AdvisorNode aNode : m_advisorNodes)
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

	/**
	 * Primarily intended for the ResolverAdviceEditor.
	 * 
	 * @param pattern
	 * @return
	 */
	public AdvisorNode getNodeByPattern(String pattern, String componentTypeID)
	{
		for(AdvisorNode node : m_advisorNodes)
			if(node.getNamePattern().toString().equals(pattern)
			&& Trivial.equalsAllowNull(node.getComponentTypeID(), componentTypeID))
				return node;
		return null;
	}

	public URL getOverlayFolder(ComponentName cName)
	{
		AdvisorNode node = getMatchingNode(cName);
		return node == null ? null : node.getOverlayFolder();
	}

	public URL getContextURL()
	{
		return m_contextURL;
	}

	public String getPropertiesURL()
	{
		return m_propertiesURL;
	}

	public String getResourceMapURL()
	{
		return m_resourceMapURL;
	}

	public URL getResolvedPropertiesURL()
	{
		return resolveURL(m_propertiesURL);
	}

	public URL getResolvedResourceMapURL()
	{
		return resolveURL(m_resourceMapURL);
	}

	public ProviderScore getProviderScore(ComponentName cName, boolean mutable, boolean source)
	{
		AdvisorNode node = getMatchingNode(cName);
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
			mutableScore = mutable ? ProviderScore.GOOD : ProviderScore.BAD;
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
			sourceScore = source ? ProviderScore.GOOD : ProviderScore.BAD;
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

	public int[] getResolutionPrio(ComponentName cName)
	{
		AdvisorNode node = getMatchingNode(cName);
		return node == null ? AdvisorNode.DEFAULT_RESOLUTION_PRIO : node.getResolutionPrio();
	}

	public long getRevision(ComponentName cName)
	{
		AdvisorNode node = getMatchingNode(cName);
		return node == null ? -1 : node.getRevision();
	}

	public ComponentRequest getRootRequest()
	{
		return m_rootRequest;
	}

	public String getShortDesc()
	{
		return m_shortDesc;
	}

	public String[] getSpacePath(ComponentName cName)
	{
		AdvisorNode node = getMatchingNode(cName);
		return node == null ? Trivial.EMPTY_STRING_ARRAY : node.getSpacePath();
	}

	public String getTagInfo()
	{
		return "Query for " + m_rootRequest;
	}

	public Date getTimestamp(ComponentName cName)
	{
		AdvisorNode node = getMatchingNode(cName);
		return node == null ? null : node.getTimestamp();
	}

	public IVersionDesignator getVersionOverride(ComponentName cName)
	{
		AdvisorNode node = getMatchingNode(cName);
		return node == null ? null : node.getVersionOverride();
	}

	public boolean isPersisted(StorageManager sm) throws CoreException
	{
		return false;
	}

	public boolean isPrune(ComponentName cName)
	{
		AdvisorNode node = getMatchingNode(cName);
		return node == null ? false : node.isPrune();
	}

	public void remove(StorageManager sm) throws CoreException
	{
		throw new UnsupportedOperationException();
	}

	public void removeAdvisorNode(AdvisorNode node)
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

	public boolean skipComponent(ComponentName cName)
	{
		AdvisorNode node = getMatchingNode(cName);
		return node == null ? false : node.skipComponent();
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
	
	public boolean useMaterialization(ComponentName cName)
	{
		AdvisorNode node = getMatchingNode(cName);
		return node == null ? true : node.isUseMaterialization();
	}

	public boolean useResolutionService(ComponentName cName)
	{
		AdvisorNode node = getMatchingNode(cName);
		return node == null ? true : node.isUseRemoteResolution();
	}

	public boolean useTargetPlatform(ComponentName cName)
	{
		AdvisorNode node = getMatchingNode(cName);
		return node == null ? true : node.isUseTargetPlatform();
	}

	public boolean useWorkspace(ComponentName cName)
	{
		AdvisorNode node = getMatchingNode(cName);
		return node == null ? true : node.isUseWorkspace();
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

	private URL resolveURL(String url)
	{
		if(url == null)
			return null;

		try
		{
			if(m_contextURL == null)
				return URLUtils.normalizeToURL(url);
			return new URL(m_contextURL, url);
		}
		catch(MalformedURLException e)
		{
			return null;
		}
	}
}
