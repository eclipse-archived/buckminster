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

import org.eclipse.buckminster.core.mspec.model.MaterializationNode;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;

/**
 * @author Thomas Hallgren
 *
 */
public class MaterializationSpecBuilder extends MaterializationDirectiveBuilder
{
	private final List<MaterializationNode> m_nodes = new ArrayList<MaterializationNode>();
	private String m_shortDesc;
	private URL m_url;

	@Override
	public void clear()
	{
		super.clear();
		m_shortDesc = null;
		m_url = null;
		m_nodes.clear();
	}

	public List<MaterializationNode> getNodes()
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
		m_shortDesc = mspec.getShortDesc();
		m_url = mspec.getURL();
		m_nodes.addAll(mspec.getNodes());
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
