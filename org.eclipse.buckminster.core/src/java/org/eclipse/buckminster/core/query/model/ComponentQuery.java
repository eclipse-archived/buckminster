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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.common.model.IProperties;
import org.eclipse.buckminster.core.common.model.SAXEmitter;
import org.eclipse.buckminster.core.cspec.model.ComponentCategory;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.metadata.ISaxableStorage;
import org.eclipse.buckminster.core.metadata.ReferentialIntegrityException;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.model.UUIDKeyed;
import org.eclipse.buckminster.core.mspec.model.ConflictResolution;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.parser.IParserFactory;
import org.eclipse.buckminster.core.rmap.model.ProviderScore;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;


/**
 * @author Thomas Hallgren
 */
public class ComponentQuery extends UUIDKeyed implements ISaxable, ISaxableElement
{
	private static final Map<String, String> s_globalAdditions;

	static
	{
		s_globalAdditions = new HashMap<String, String>();
		s_globalAdditions.putAll(BMProperties.getSystemProperties());

		URL eclipseHome = Platform.getInstallLocation().getURL();
		assert ("file".equals(eclipseHome.getProtocol()));
		s_globalAdditions.put("eclipse.home", FileUtils.getFile(eclipseHome).toString());
		s_globalAdditions.put("workspace.root", ResourcesPlugin.getWorkspace().getRoot().getLocation()
				.toPortableString());
		try
		{
			s_globalAdditions.put("localhost", InetAddress.getLocalHost().getHostName());
		}
		catch(UnknownHostException e1)
		{
			// We'll just have to do without it.
		}
	}

	public static Map<String, String> getGlobalPropertyAdditions()
	{
		return s_globalAdditions;
	}

	public static final String ATTR_PROPERTIES = "properties";

	public static final String ATTR_RESOURCE_MAP = "resourceMap";

	public static final String ATTR_SHORT_DESC = "shortDesc";

	public static final String ELEM_ROOT_REQUEST = "rootRequest";

	public static final String TAG = "componentQuery";

	public static final int SEQUENCE_NUMBER = 2;

	public static ComponentQuery fromStream(String systemId, InputStream stream) throws CoreException
	{
		try
		{
			IParserFactory pf = CorePlugin.getDefault().getParserFactory();
			IParser<ComponentQuery> parser = pf.getComponentQueryParser(false);
			return parser.parse(systemId, stream);
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public static ComponentQuery fromURL(URL url, IProgressMonitor monitor) throws CoreException
	{
		InputStream stream = null;
		try
		{
			stream = URLUtils.openStream(url, monitor);
			return fromStream(url.toString(), stream);
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

	private final Documentation m_documentation;

	private final String m_shortDesc;

	private final Map<String, String> m_properties;

	private final URL m_propertiesURL;

	private final URL m_resourceMapURL;

	private final ComponentRequest m_rootRequest;

	private transient Map<String, String> m_allProperties;

	public ComponentQuery(Documentation documentation, String shortDesc, List<AdvisorNode> advisorNodes, Map<String,String> properties, URL propertiesURL, URL resourceMapURL, ComponentRequest rootRequest)
	{
		m_documentation = documentation;
		m_shortDesc = shortDesc;
		m_propertiesURL = propertiesURL;
		m_resourceMapURL = resourceMapURL;
		m_rootRequest = rootRequest;

		if(advisorNodes == null || advisorNodes.size() == 0)
			m_advisorNodes = Collections.emptyList();
		else
			m_advisorNodes = Collections.unmodifiableList(new ArrayList<AdvisorNode>(advisorNodes));
		
		if(properties == null || properties.size() == 0)
			m_properties = Collections.emptyMap();
		else
			m_properties = Collections.unmodifiableMap(new ExpandingProperties(properties));
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

		m_allProperties = getGlobalPropertyAdditions();
		if(m_properties.size() > 0)
		{
			m_allProperties = new ExpandingProperties(m_allProperties);
			m_allProperties.putAll(m_properties);
		}

		if(m_propertiesURL != null)
		{
			InputStream input = null;
			try
			{
				input = new BufferedInputStream(URLUtils.openStream(m_propertiesURL, null));
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
				Logger.getLogger(getClass().getName()).info("Unable to read property file '"
					+ m_propertiesURL + "' : " + e.toString());
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
				String matchingCategory = aNode.getCategory();
				if(matchingCategory == null || matchingCategory.equals(cName.getCategory()))
					return aNode;
			}
		}
		return null;
	}

	public IPath getMaterializationLocation(ComponentName cName) throws CoreException
	{
		IPath location = null;
		AdvisorNode node = getMatchingNode(cName);
		String projectName = getProjectName(cName);
		if(node != null)
			location = node.getMaterializationLocation(projectName);

		if(location == null)
		{
			IPath relativeLocation = null;
			IPath cRoot = ResourcesPlugin.getWorkspace().getRoot().getLocation();
			ComponentCategory cc = ComponentCategory.getCategory(cName.getCategory());
			if(cc != null)
				relativeLocation = cc.getRelativeLocation();

			if(relativeLocation != null)
				location = cRoot.append(relativeLocation).append(cName.getName());
			else
				location = cRoot.append(projectName);
		}
		return location;
	}

	/**
	 * Primarily intended for the ResolverAdviceEditor.
	 * 
	 * @param pattern
	 * @return
	 */
	public AdvisorNode getNodeByPattern(String pattern, String category)
	{
		for(AdvisorNode node : m_advisorNodes)
			if(node.getNamePattern().toString().equals(pattern)
			&& Trivial.equalsAllowNull(node.getCategory(), category))
				return node;
		return null;
	}

	public URL getOverlayFolder(ComponentName cName)
	{
		AdvisorNode node = getMatchingNode(cName);
		return node == null ? null : node.getOverlayFolder();
	}

	public String getProjectName(ComponentName cName) throws CoreException
	{
		String name = cName.getName();
		AdvisorNode node = getMatchingNode(cName);
		Pattern repFrom = null;
		String  repTo = null;

		// Any replacement found in the advisor node?
		//
		if(node != null)
		{
			repFrom = node.getReplaceFrom();
			repTo   = node.getReplaceTo();
		}

		// If the advicor node did not have a complete replace
		// pattern, perhaps we have a category that has?
		//
		if(repFrom == null || repTo == null)
		{
			String categoryName = cName.getCategory();
			ComponentCategory cc = ComponentCategory.getCategory(categoryName);
			if(cc == null)
				//
				// No category.
				//
				return name;

			Pattern desiredMatch = cc.getDesiredNamePattern();
			if(desiredMatch == null || desiredMatch.matcher(name).find())
				//
				// We have a category but no desire to change the name
				//
				return name;

			repFrom = cc.getSubstituteNamePattern();
			repTo = cc.getNameSubstitution();

			if(repFrom == null || repTo == null)
				throw new BuckminsterException("Category: " + categoryName + " defines desiredNamePattern but no substitution");
		}

		Matcher matcher = repFrom.matcher(name);
		if(matcher.matches())
		{
			String repl = matcher.replaceAll(repTo).trim();
			if(repl.length() > 0)
				name = repl;
		}
		return name;
	}

	public Map<String, String> getProperties(ComponentName cName)
	{
		// fill this up as you go..
		//
		IProperties p = new ExpandingProperties(getGlobalProperties());
		p.putAll(cName.getProperties());

		AdvisorNode node = getMatchingNode(cName);
		if(node != null)
			p.putAll(node.getProperties());
		return p;
	}

	public URL getPropertiesURL()
	{
		return m_propertiesURL;
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

	public URL getResourceMapURL()
	{
		return m_resourceMapURL;
	}

	public ComponentRequest getRootRequest()
	{
		return m_rootRequest;
	}

	public String getShortDesc()
	{
		return m_shortDesc;
	}

	public IVersionDesignator getVersionOverride(ComponentName cName)
	{
		AdvisorNode node = getMatchingNode(cName);
		return node == null ? null : node.getVersionOverride();
	}

	public boolean isPersisted() throws CoreException
	{
		return getStorage().contains(this);
	}

	public boolean isPrune(ComponentName cName)
	{
		AdvisorNode node = getMatchingNode(cName);
		return node == null ? false : node.isPrune();
	}

	public void remove() throws CoreException
	{
		UUID thisId = getId();
		StorageManager sm = StorageManager.getDefault();
		if(!sm.getDepNodes().getReferencingKeys(thisId, "queryId").isEmpty())
			throw new ReferentialIntegrityException(this, "remove", "Referenced from BillOfMaterials");

		getStorage().removeElement(thisId);
	}

	public void removeAdvisorNode(AdvisorNode node)
	{
		m_advisorNodes.remove(node);
	}

	public boolean skipComponent(ComponentName cName)
	{
		AdvisorNode node = getMatchingNode(cName);
		return node == null ? false : node.skipComponent();
	}

	public void store() throws CoreException
	{
		getStorage().putElement(this);
	}

	public void toSax(ContentHandler handler) throws SAXException
	{
		handler.startDocument();
		toSax(handler, BM_CQUERY_NS, BM_CQUERY_PREFIX, getDefaultTag());
		handler.endDocument();
	}

	public void toSax(ContentHandler handler, String namespace, String prefix, String localName) throws SAXException
	{
		handler.startPrefixMapping(BM_CQUERY_PREFIX, BM_CQUERY_NS);

		String qName = Utils.makeQualifiedName(prefix, localName);
		AttributesImpl attrs = new AttributesImpl();
		if(m_resourceMapURL != null)
			Utils.addAttribute(attrs, ATTR_RESOURCE_MAP, m_resourceMapURL.toString());
		if(m_propertiesURL != null)
			Utils.addAttribute(attrs, ATTR_PROPERTIES, m_propertiesURL.toString());
		if(m_shortDesc != null)
			Utils.addAttribute(attrs, ATTR_SHORT_DESC, m_shortDesc);

		handler.startElement(namespace, localName, qName, attrs);
		if(m_documentation != null)
			m_documentation.toSax(handler, BM_CQUERY_NS, BM_CQUERY_PREFIX, m_documentation.getDefaultTag());

		m_rootRequest.toSax(handler, BM_CQUERY_NS, BM_CQUERY_PREFIX, ELEM_ROOT_REQUEST);
		SAXEmitter.emitProperties(handler, m_properties, namespace, prefix, true, false);

		for(AdvisorNode node : m_advisorNodes)
			node.toSax(handler, BM_CQUERY_NS, BM_CQUERY_PREFIX, node.getDefaultTag());

		handler.endElement(namespace, localName, qName);
		handler.endPrefixMapping(BM_CQUERY_PREFIX);
	}

	public ConflictResolution useExistingArtifacts(ComponentName cName)
	{
		AdvisorNode node = getMatchingNode(cName);
		return node == null ? ConflictResolution.FAIL : node.whenNotEmpty();
	}

	public boolean useExistingProject(ComponentName cName)
	{
		AdvisorNode node = getMatchingNode(cName);
		return node == null ? true : node.useProject();
	}
	
	public boolean useInstalledComponent(ComponentName cName)
	{
		AdvisorNode node = getMatchingNode(cName);
		return node == null ? true : node.useInstalled();
	}

	public boolean useMaterialization(ComponentName cName)
	{
		AdvisorNode node = getMatchingNode(cName);
		return node == null ? true : node.useMaterialization();
	}

	private ISaxableStorage<ComponentQuery> getStorage() throws CoreException
	{
		return StorageManager.getDefault().getQueries();
	}
}
