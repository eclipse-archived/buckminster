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

/**
 * @author Thomas Hallgren
 *
 */
public class MaterializationNodeBuilder extends MaterializationDirectiveBuilder
{
	private Pattern m_namePattern;
	private String m_category;
	private boolean m_exclude;

	@Override
	public void clear()
	{
		super.clear();
		m_namePattern = null;
		m_category = null;
		m_exclude = false;
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

	public void setNamePattern(Pattern namePattern)
	{
		m_namePattern = namePattern;
	}

	public void initFrom(MaterializationNode mn)
	{
		super.initFrom(mn);
		m_namePattern = mn.getNamePattern();
		m_category = mn.getCategory();
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
}
