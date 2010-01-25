/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.mspec.builder;

import java.util.regex.Pattern;

import org.eclipse.buckminster.core.mspec.IMaterializationNode;
import org.eclipse.buckminster.core.mspec.model.MaterializationNode;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;

/**
 * @author Thomas Hallgren
 * 
 */
public class MaterializationNodeBuilder extends MaterializationDirectiveBuilder implements IMaterializationNode
{
	private Pattern m_namePattern;

	private Filter m_filter;

	private IPath m_leafArtifact;

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
		m_filter = null;
		m_leafArtifact = null;
		m_componentTypeID = null;
		m_resourcePath = null;
		m_exclude = false;
		m_bindingNamePattern = null;
		m_bindingNameReplacement = null;
		m_suffix = null;
		m_unpack = false;
		m_expand = true;
	}

	public MaterializationNode createMaterializationNode()
	{
		return new MaterializationNode(this);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object getAdapter(Class adapter)
	{
		if(adapter.isInstance(this))
			return this;
		if(adapter.isAssignableFrom(MaterializationNode.class))
			return new MaterializationNode(this);
		return Platform.getAdapterManager().getAdapter(this, adapter);
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

	public Filter getFilter()
	{
		return m_filter;
	}

	public IPath getLeafArtifact()
	{
		return m_leafArtifact;
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

	public void initFrom(IMaterializationNode mn)
	{
		super.initFrom(mn);
		m_namePattern = mn.getNamePattern();
		m_filter = mn.getFilter();
		m_leafArtifact = mn.getLeafArtifact();
		m_componentTypeID = mn.getComponentTypeID();
		m_resourcePath = mn.getResourcePath();
		m_exclude = mn.isExclude();
		m_bindingNamePattern = mn.getBindingNamePattern();
		m_bindingNameReplacement = mn.getBindingNameReplacement();
		m_suffix = mn.getSuffix();
		m_unpack = mn.isUnpack();
		m_expand = mn.isExpand();
	}

	public boolean isExclude()
	{
		return m_exclude;
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

	public void setComponentTypeID(String componentTypeID)
	{
		m_componentTypeID = componentTypeID;
	}

	public void setExclude(boolean exclude)
	{
		m_exclude = exclude;
	}

	public void setExpand(boolean expand)
	{
		m_expand = expand;
	}

	public void setFilter(Filter filter)
	{
		m_filter = filter;
	}

	public void setLeafArtifact(IPath leafArtifact)
	{
		m_leafArtifact = leafArtifact;
	}

	public void setNamePattern(Pattern namePattern)
	{
		m_namePattern = namePattern;
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
