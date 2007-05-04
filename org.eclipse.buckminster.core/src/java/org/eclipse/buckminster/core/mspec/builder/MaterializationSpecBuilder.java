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

import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.mspec.model.MaterializationNode;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;

/**
 * @author Thomas Hallgren
 *
 */
public class MaterializationSpecBuilder extends MaterializationDirectiveBuilder
{
	private final List<MaterializationNodeBuilder> m_nodes = new ArrayList<MaterializationNodeBuilder>();
	private String m_shortDesc;
	private String m_name;
	private URL m_url;

	@Override
	public void clear()
	{
		super.clear();
		m_name = null;
		m_shortDesc = null;
		m_url = null;
		m_nodes.clear();
	}

	public MaterializationSpec createMaterializationSpec()
	{
		return new MaterializationSpec(this);
	}

	public MaterializationNodeBuilder getMatchingNode(ComponentName cName)
	{
		String name = cName.getName();
		for(MaterializationNodeBuilder aNode : m_nodes)
		{
			Pattern pattern = aNode.getNamePattern();
			if(pattern != null && pattern.matcher(name).find())
			{
				String matchingCategory = aNode.getCategory();
				if(matchingCategory == null || matchingCategory.equals(cName.getCategory()))
					return aNode;
			}
		}
		return null;
	}

	public String getName()
	{
		return m_name;
	}

	public List<MaterializationNodeBuilder> getNodes()
	{
		return m_nodes;
	}

	public String getShortDesc()
	{
		return m_shortDesc;
	}

	public URL getURL()
	{
		return m_url;
	}

	public void initFrom(MaterializationSpec mspec)
	{
		super.initFrom(mspec);
		m_name = mspec.getName();
		m_shortDesc = mspec.getShortDesc();
		m_url = mspec.getURL();
		for(MaterializationNode node : mspec.getNodes())
		{
			MaterializationNodeBuilder nodeBuilder = new MaterializationNodeBuilder();
			nodeBuilder.initFrom(node);
			m_nodes.add(nodeBuilder);
		}
	}

	public void setName(String name)
	{
		m_name = name;
	}

	public void setShortDesc(String shortDesc)
	{
		m_shortDesc = shortDesc;
	}

	public void setURL(URL url)
	{
		m_url = url;
	}
}
