/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.mspec.builder;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.cspec.IComponentName;
import org.eclipse.buckminster.core.mspec.IMaterializationNode;
import org.eclipse.buckminster.core.mspec.IMaterializationSpec;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.Platform;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 * 
 */
public class MaterializationSpecBuilder extends MaterializationDirectiveBuilder implements IMaterializationSpec
{
	private final List<MaterializationNodeBuilder> m_nodes = new ArrayList<MaterializationNodeBuilder>();

	private String m_shortDesc;

	private String m_name;

	private String m_url;

	private URL m_contextURL;

	public MaterializationNodeBuilder addNodeBuilder()
	{
		MaterializationNodeBuilder node = new MaterializationNodeBuilder();
		m_nodes.add(node);
		return node;
	}

	@Override
	public void clear()
	{
		super.clear();
		m_name = null;
		m_shortDesc = null;
		m_url = null;
		m_contextURL = null;
		m_nodes.clear();
	}

	public MaterializationSpec createMaterializationSpec()
	{
		return new MaterializationSpec(this);
	}

	@SuppressWarnings("unchecked")
	public Object getAdapter(Class adapter)
	{
		if(adapter.isInstance(this))
			return this;
		if(adapter.isAssignableFrom(MaterializationSpec.class))
			return createMaterializationSpec();
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

	public URL getContextURL()
	{
		return m_contextURL;
	}

	public IMaterializationNode getMatchingNode(IComponentName cName)
	{
		return getMatchingNodeBuilder(cName);
	}

	public MaterializationNodeBuilder getMatchingNodeBuilder(IComponentName cName)
	{
		String name = cName.getName();
		for(MaterializationNodeBuilder aNode : m_nodes)
		{
			Pattern pattern = aNode.getNamePattern();
			if(pattern != null && pattern.matcher(name).find())
			{
				String matchingCType = aNode.getComponentTypeID();
				if(matchingCType == null || matchingCType.equals(cName.getComponentTypeID()))
					return aNode;
			}
		}
		return null;
	}

	public String getName()
	{
		return m_name;
	}

	public List<MaterializationNodeBuilder> getNodeBuilders()
	{
		return m_nodes;
	}

	public List<? extends IMaterializationNode> getNodes()
	{
		return getNodeBuilders();
	}

	public URL getResolvedURL()
	{
		return URLUtils.resolveURL(m_contextURL, m_url);
	}

	public String getShortDesc()
	{
		return m_shortDesc;
	}

	public String getURL()
	{
		return m_url;
	}

	public void initFrom(IMaterializationSpec mspec)
	{
		super.initFrom(mspec);
		m_name = mspec.getName();
		m_shortDesc = mspec.getShortDesc();
		m_url = mspec.getURL();
		m_contextURL = mspec.getContextURL();
		for(IMaterializationNode node : mspec.getNodes())
		{
			MaterializationNodeBuilder nodeBuilder = new MaterializationNodeBuilder();
			nodeBuilder.initFrom(node);
			m_nodes.add(nodeBuilder);
		}
	}

	public void setContextURL(URL contextURL)
	{
		m_contextURL = contextURL;
	}

	public void setName(String name)
	{
		m_name = name;
	}

	public void setShortDesc(String shortDesc)
	{
		m_shortDesc = shortDesc;
	}

	public void setURL(String url)
	{
		m_url = url;
	}

	public void toSax(ContentHandler receiver) throws SAXException
	{
		MaterializationSpec mspec = new MaterializationSpec(this);
		mspec.toSax(receiver);
	}
}
