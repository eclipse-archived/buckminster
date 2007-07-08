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
	private String m_componentTypeID;
	private boolean m_exclude;
	private Pattern m_bindingNamePattern;
	private String m_bindingNameReplacement;
	private String m_suffix;
	private boolean m_unpack;
	private boolean m_expand = true;

	// Only valid when materializing into workspace
	//
	private IPath m_resourcePath;

	@Override
	public void clear()
	{
		super.clear();
		m_namePattern = null;
		m_componentTypeID = null;
		m_resourcePath = null;
		m_exclude = false;
		m_bindingNamePattern = null;
		m_bindingNameReplacement = null;
		m_unpack = false;
		m_expand = true;
		m_suffix = null;
	}

	public MaterializationNode createMaterializationNode()
	{
		return new MaterializationNode(this);
	}

	public Pattern getBindingNamePattern()
	{
		return m_bindingNamePattern;
	}

	public String getBindingNameReplacement()
	{
		return m_bindingNameReplacement;
	}

	public String getComponentTypeID()
	{
		return m_componentTypeID;
	}

	public void setComponentTypeID(String componentTypeID)
	{
		m_componentTypeID = componentTypeID;
	}

	public Pattern getNamePattern()
	{
		return m_namePattern;
	}

	public IPath getResourcePath()
	{
		return m_resourcePath;
	}

	public String getSuffix()
	{
		return m_suffix;
	}

	public boolean isExpand()
	{
		return m_expand;
	}

	public boolean isUnpack()
	{
		return m_unpack;
	}

	public void setBindingNamePattern(Pattern bindingNamePattern)
	{
		m_bindingNamePattern = bindingNamePattern;
	}

	public void setBindingNameReplacement(String bindingNameReplacement)
	{
		m_bindingNameReplacement = bindingNameReplacement;
	}

	public void setNamePattern(Pattern namePattern)
	{
		m_namePattern = namePattern;
	}

	public void initFrom(MaterializationNode mn)
	{
		super.initFrom(mn);
		m_namePattern = mn.getNamePattern();
		m_componentTypeID = mn.getComponentTypeID();
		m_resourcePath = mn.getResourcePath();
		m_exclude = mn.isExclude();
		m_bindingNamePattern = mn.getBindingNamePattern();
		m_bindingNameReplacement = mn.getBindingNameReplacement();
		m_unpack = mn.isUnpack();
		m_expand = mn.isExpand();
		m_suffix = mn.getSuffix();
	}

	public boolean isExclude()
	{
		return m_exclude;
	}

	public void setExclude(boolean exclude)
	{
		m_exclude = exclude;
	}

	public void setExpand(boolean expand)
	{
		m_expand = expand;
	}

	public void setResourcePath(IPath resourcePath)
	{
		m_resourcePath = resourcePath;
	}

	public void setSuffix(String suffix)
	{
		m_suffix = suffix;
	}

	public void setUnpack(boolean unpack)
	{
		m_unpack = unpack;
	}
}
