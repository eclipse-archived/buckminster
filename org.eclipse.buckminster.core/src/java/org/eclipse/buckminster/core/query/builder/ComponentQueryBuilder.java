/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.query.builder;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.TargetPlatform;
import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.FilterUtils;
import org.eclipse.buckminster.core.query.model.AdvisorNode;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.runtime.Trivial;

/**
 * @author Thomas Hallgren
 */
public class ComponentQueryBuilder
{
	private final ArrayList<AdvisorNodeBuilder> m_advisorNodes = new ArrayList<AdvisorNodeBuilder>();

	private Documentation m_documentation;

	private Map<String, String> m_properties;

	private URL m_contextURL;

	private String m_propertiesURL;

	private String m_resourceMapURL;

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
		m_contextURL = null;
		m_properties = null;
		m_propertiesURL = null;
		m_resourceMapURL = null;
		m_rootRequest = null;
		m_documentation = null;
		m_shortDesc = null;
	}

	public ComponentQuery createComponentQuery()
	{
		return new ComponentQuery(this);
	}

	public List<AdvisorNodeBuilder> getAdvisoryNodeList()
	{
		return m_advisorNodes;
	}

	public URL getContextURL()
	{
		return m_contextURL;
	}

	public Documentation getDocumentation()
	{
		return m_documentation;
	}

	public AdvisorNodeBuilder getNodeByPattern(String pattern, String componentType)
	{
		for(AdvisorNodeBuilder node : m_advisorNodes)
			if(node.getNamePattern().toString().equals(pattern)
					&& Trivial.equalsAllowNull(node.getComponentTypeID(), componentType))
				return node;
		return null;
	}

	public Map<String, String> getProperties()
	{
		if(m_properties == null)
			m_properties = new HashMap<String,String>();
		return m_properties;
	}

	public String getPropertiesURL()
	{
		return m_propertiesURL;
	}

	public String getResourceMapURL()
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

		Map<String,String> props = query.getDeclaredProperties();
		if(props.size() > 0)
			m_properties = new HashMap<String,String>(props);

		m_contextURL = query.getContextURL();
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

	public final void setContextURL(URL contextURL)
	{
		m_contextURL = contextURL;
	}

	public void setDocumentation(Documentation documentation)
	{
		m_documentation = documentation;
	}

	public void setPlatformAgnostic(boolean flag)
	{
		if(flag == false)
		{
			if(m_properties == null)
				return;
			m_properties.remove(TargetPlatform.TARGET_OS);
			m_properties.remove(TargetPlatform.TARGET_WS);
			m_properties.remove(TargetPlatform.TARGET_ARCH);
			m_properties.remove(TargetPlatform.TARGET_NL);
			if(m_properties.size() == 0)
				m_properties = null;
		}
		else
		{
			Map<String,String> props = getProperties();
			props.put(TargetPlatform.TARGET_OS, FilterUtils.MATCH_ALL);
			props.put(TargetPlatform.TARGET_WS, FilterUtils.MATCH_ALL);
			props.put(TargetPlatform.TARGET_ARCH, FilterUtils.MATCH_ALL);
			props.put(TargetPlatform.TARGET_NL, FilterUtils.MATCH_ALL);
		}
	}

	public final void setPropertiesURL(String propertiesURL)
	{
		m_propertiesURL = propertiesURL;
	}

	public final void setResourceMapURL(String resourceMapURL)
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
