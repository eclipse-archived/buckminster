/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.query.builder;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.query.model.AdvisorNode;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Platform;


/**
 * @author Thomas Hallgren
 */
public class ComponentQueryBuilder
{
	private static final Map<String,String> s_globalAdditions;

	static
	{
		s_globalAdditions = new HashMap<String,String>();
		URL eclipseHome = Platform.getInstallLocation().getURL();
		assert ("file".equals(eclipseHome.getProtocol()));
		s_globalAdditions.put("eclipse.home", FileUtils.getFile(eclipseHome).toString());
		s_globalAdditions.put("workspace.root", ResourcesPlugin.getWorkspace().getRoot().getLocation().toPortableString());
		try
		{
			s_globalAdditions.put("localhost", InetAddress.getLocalHost().getHostName());
		}
		catch(UnknownHostException e1)
		{
			// We'll just have to do without it.
		}
	}

	public static Map<String,String> getGlobalPropertyAdditions()
	{
		return s_globalAdditions;
	}

	private final ArrayList<AdvisorNodeBuilder> m_advisorNodes = new ArrayList<AdvisorNodeBuilder>();

	private Documentation m_documentation;

	private Map<String, String> m_properties;

	private URL m_propertiesURL;

	private URL m_resourceMapURL;

	private ComponentRequest m_rootRequest;

	private String m_shortDesc;

	public ComponentQueryBuilder()
	{
		this.clear();
	}

	public void addAdvisorNode(AdvisorNodeBuilder node)
	{
		m_advisorNodes.add(node);
	}

	public void clear()
	{
		m_advisorNodes.clear();
		m_properties = new ExpandingProperties(BMProperties.getSystemProperties());
		m_properties.putAll(s_globalAdditions);
		m_propertiesURL = null;
		m_resourceMapURL = null;
		m_rootRequest = null;
		m_documentation = null;
		m_shortDesc = null;
	}

	public ComponentQuery createComponentQuery()
	{
		ArrayList<AdvisorNode> nodes = new ArrayList<AdvisorNode>(m_advisorNodes.size());
		for(AdvisorNodeBuilder bld : m_advisorNodes)
			nodes.add(bld.create());
		return new ComponentQuery(m_documentation, m_shortDesc, nodes, m_properties, m_propertiesURL, m_resourceMapURL, m_rootRequest);
	}

	public List<AdvisorNodeBuilder> getAdvisoryNodeList()
	{
		return m_advisorNodes;
	}

	public Documentation getDocumentation()
	{
		return m_documentation;
	}

	public AdvisorNodeBuilder getNodeByPattern(String pattern, String category)
	{
		for(AdvisorNodeBuilder node : m_advisorNodes)
			if(node.getNamePattern().toString().equals(pattern)
			&& Trivial.equalsAllowNull(node.getCategory(), category))
				return node;
		return null;
	}

	public Map<String,String> getProperties()
	{
		return m_properties;
	}

	public URL getPropertiesURL()
	{
		return m_propertiesURL;
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

	public void initFrom(ComponentQuery query)
	{
		this.clear();
		for(AdvisorNode node : query.getAdvisoryNodes())
		{
			AdvisorNodeBuilder bld = new AdvisorNodeBuilder();
			bld.initFrom(node);
			m_advisorNodes.add(bld);
		}
		m_properties = new ExpandingProperties(query.getDeclaredProperties());
		m_propertiesURL = query.getPropertiesURL();
		m_resourceMapURL = query.getResourceMapURL();
		m_rootRequest = query.getRootRequest();
		m_documentation = query.getDocumentation();
		m_shortDesc = query.getShortDesc();
	}

	public void removeAdvisorNode(AdvisorNodeBuilder node)
	{
		m_advisorNodes.remove(node);
	}

	public void setDocumentation(Documentation documentation)
	{
		m_documentation = documentation;
	}

	public final void setPropertiesURL(URL propertiesURL)
	{
		m_propertiesURL = propertiesURL;
	}

	public final void setResourceMapURL(URL resourceMapURL)
	{
		m_resourceMapURL = resourceMapURL;
	}

	public final void setRootRequest(ComponentRequest rootRequest)
	{
		m_rootRequest = rootRequest;
	}

	public void setShortDesc(String shortDesc)
	{
		m_shortDesc = shortDesc;
	}
}
