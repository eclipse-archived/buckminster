/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.mspec.model;

import java.util.regex.Pattern;

import org.eclipse.buckminster.core.mspec.IMaterializationNode;
import org.eclipse.buckminster.core.mspec.builder.MaterializationNodeBuilder;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.IPath;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class MaterializationNode extends MaterializationDirective implements IMaterializationNode
{
	public static final String TAG = "mspecNode"; //$NON-NLS-1$

	public static final String ATTR_NAME_PATTERN = "namePattern"; //$NON-NLS-1$

	public static final String ATTR_LEAF_ARTIFACT = "leafArtifact"; //$NON-NLS-1$

	public static final String ATTR_COMPONENT_TYPE = "componentType"; //$NON-NLS-1$

	public static final String ATTR_EXCLUDE = "exclude"; //$NON-NLS-1$

	public static final String ATTR_RESOURCE_PATH = "resourcePath"; //$NON-NLS-1$

	public static final String ATTR_BINDING_NAME_PATTERN = "bindingNamePattern"; //$NON-NLS-1$

	public static final String ATTR_BINDING_NAME_REPLACEMENT = "bindingNameReplacement"; //$NON-NLS-1$

	public static final String ELEM_UNPACK = "unpack"; //$NON-NLS-1$

	public static final String ATTR_SUFFIX = "suffix"; //$NON-NLS-1$

	public static final String ATTR_EXPAND = "expand"; //$NON-NLS-1$

	private final Pattern m_namePattern;

	private final IPath m_leafArtifact;

	private final String m_componentTypeID;

	private final boolean m_exclude;

	private final IPath m_resourcePath;

	private final Pattern m_bindingNamePattern;

	private final String m_bindingNameReplacement;

	private final String m_suffix;

	private final boolean m_unpack;

	private final boolean m_expand;

	public MaterializationNode(MaterializationNodeBuilder builder)
	{
		super(builder);
		m_namePattern = builder.getNamePattern();
		m_leafArtifact = builder.getLeafArtifact();
		m_componentTypeID = builder.getComponentTypeID();
		m_exclude = builder.isExclude();
		m_resourcePath = builder.getResourcePath();
		m_bindingNamePattern = builder.getBindingNamePattern();
		m_bindingNameReplacement = builder.getBindingNameReplacement();
		m_suffix = builder.getSuffix();
		m_unpack = builder.isUnpack();
		m_expand = builder.isExpand();
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		super.addAttributes(attrs);
		Utils.addAttribute(attrs, ATTR_NAME_PATTERN, m_namePattern.toString());
		if(m_componentTypeID != null)
			Utils.addAttribute(attrs, ATTR_COMPONENT_TYPE, m_componentTypeID);
		if(m_leafArtifact != null)
			Utils.addAttribute(attrs, ATTR_LEAF_ARTIFACT, m_leafArtifact.toPortableString());
		if(m_resourcePath != null)
			Utils.addAttribute(attrs, ATTR_RESOURCE_PATH, m_resourcePath.toPortableString());
		if(m_exclude)
			Utils.addAttribute(attrs, ATTR_EXCLUDE, "true"); //$NON-NLS-1$
		if(m_bindingNamePattern != null)
			Utils.addAttribute(attrs, ATTR_BINDING_NAME_PATTERN, m_bindingNamePattern.toString());
		if(m_bindingNameReplacement != null)
			Utils.addAttribute(attrs, ATTR_BINDING_NAME_REPLACEMENT, m_bindingNameReplacement);
	}

	@Override
	protected void emitElements(ContentHandler receiver, String namespace, String prefix) throws SAXException
	{
		super.emitElements(receiver, namespace, prefix);
		if(m_unpack)
		{
			AttributesImpl attrs = new AttributesImpl();
			if(!m_expand)
				Utils.addAttribute(attrs, ATTR_EXPAND, "false"); //$NON-NLS-1$
			if(m_suffix != null)
				Utils.addAttribute(attrs, ATTR_SUFFIX, m_suffix);
			String qName = Utils.makeQualifiedName(prefix, ELEM_UNPACK);
			receiver.startElement(namespace, ELEM_UNPACK, qName, attrs);
			receiver.endElement(namespace, ELEM_UNPACK, qName);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter)
	{
		if(adapter.isAssignableFrom(MaterializationNodeBuilder.class))
		{
			MaterializationNodeBuilder bld = new MaterializationNodeBuilder();
			bld.initFrom(this);
			return bld;
		}
		return super.getAdapter(adapter);
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

	public String getDefaultTag()
	{
		return TAG;
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
}
