/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.mspec.model;

import java.util.regex.Pattern;

import org.eclipse.buckminster.core.mspec.builder.MaterializationNodeBuilder;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.IPath;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class MaterializationNode extends MaterializationDirective
{
	public static final String TAG = "mspecNode";
	public static final String ATTR_NAME_PATTERN = "namePattern";
	public static final String ATTR_CATEGORY = "category";
	public static final String ATTR_EXCLUDE = "exclude";
	public static final String ATTR_RESOURCE_PATH = "resourcePath";

	private final Pattern m_namePattern;
	private final String m_category;
	private final boolean m_exclude;
	private final IPath m_resourcePath;

	public MaterializationNode(MaterializationNodeBuilder builder)
	{
		super(builder);
		m_namePattern = builder.getNamePattern();
		m_category = builder.getCategory();
		m_exclude = builder.isExclude();
		m_resourcePath = builder.getResourcePath();
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public String getCategory()
	{
		return m_category;
	}

	public Pattern getNamePattern()
	{
		return m_namePattern;
	}

	public IPath getResourcePath()
	{
		return m_resourcePath;
	}

	public boolean isExclude()
	{
		return m_exclude;
	}

	@Override
	protected void appendAttributes(AttributesImpl attrs) throws SAXException
	{
		super.appendAttributes(attrs);
		Utils.addAttribute(attrs, ATTR_NAME_PATTERN, m_namePattern.toString());
		if(m_category != null)
			Utils.addAttribute(attrs, ATTR_CATEGORY, m_category);
		if(m_resourcePath != null)
			Utils.addAttribute(attrs, ATTR_RESOURCE_PATH, m_resourcePath.toPortableString());
		if(m_exclude)
			Utils.addAttribute(attrs, ATTR_EXCLUDE, "true");
	}
}
