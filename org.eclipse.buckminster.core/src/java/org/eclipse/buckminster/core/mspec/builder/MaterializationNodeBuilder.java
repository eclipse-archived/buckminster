/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.mspec.builder;

import java.util.regex.Pattern;

import org.eclipse.buckminster.core.mspec.model.MaterializationNode;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 *
 */
public class MaterializationNodeBuilder extends MaterializationDirectiveBuilder
{
	private Pattern m_namePattern;
	private String m_category;
	private boolean m_exclude;

	// Only valid when materializing into workspace
	//
	private IPath m_resourcePath;

	@Override
	public void clear()
	{
		super.clear();
		m_namePattern = null;
		m_category = null;
		m_resourcePath = null;
		m_exclude = false;
	}

	public MaterializationNode createMaterializationNode()
	{
		return new MaterializationNode(this);
	}

	public String getCategory()
	{
		return m_category;
	}

	public void setCategory(String category)
	{
		m_category = category;
	}

	public Pattern getNamePattern()
	{
		return m_namePattern;
	}

	public IPath getResourcePath()
	{
		return m_resourcePath;
	}

	public void setNamePattern(Pattern namePattern)
	{
		m_namePattern = namePattern;
	}

	public void initFrom(MaterializationNode mn)
	{
		super.initFrom(mn);
		m_namePattern = mn.getNamePattern();
		m_category = mn.getCategory();
		m_resourcePath = mn.getResourcePath();
		m_exclude = mn.isExclude();
	}

	public boolean isExclude()
	{
		return m_exclude;
	}

	public void setExclude(boolean exclude)
	{
		m_exclude = exclude;
	}

	public void setResourcePath(IPath resourcePath)
	{
		m_resourcePath = resourcePath;
	}
}
