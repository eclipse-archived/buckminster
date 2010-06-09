/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.opml.model;

import java.net.URI;
import java.util.Date;

import org.eclipse.buckminster.opml.IOutline;
import org.eclipse.buckminster.opml.OutlineType;
import org.eclipse.buckminster.opml.builder.OutlineBuilder;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 * 
 */
public class Outline extends Body implements IOutline
{
	@SuppressWarnings("hiding")
	public static final String TAG = "outline"; //$NON-NLS-1$

	public static final String ATTR_CATEGORY = "category"; //$NON-NLS-1$

	public static final String ATTR_CREATED = "created"; //$NON-NLS-1$

	public static final String ATTR_DESCRIPTION = "description"; //$NON-NLS-1$

	public static final String ATTR_HTML_URL = "htmlUrl"; //$NON-NLS-1$

	public static final String ATTR_IS_BREAKPOINT = "isBreakpoint"; //$NON-NLS-1$

	public static final String ATTR_IS_COMMENT = "isComment"; //$NON-NLS-1$

	public static final String ATTR_LANGUAGE = "language"; //$NON-NLS-1$

	public static final String ATTR_TEXT = "text"; //$NON-NLS-1$

	public static final String ATTR_TITLE = "title"; //$NON-NLS-1$

	public static final String ATTR_TYPE = "type"; //$NON-NLS-1$

	public static final String ATTR_URL = "url"; //$NON-NLS-1$

	public static final String ATTR_VERSION = "version"; //$NON-NLS-1$

	public static final String ATTR_XML_URL = "xmlUrl"; //$NON-NLS-1$

	private final boolean m_breakpoint;

	private final String m_category;

	private final boolean m_comment;

	private final Date m_created;

	private final String m_description;

	private final URI m_htmlUrl;

	private final String m_language;

	private final String m_text;

	private final String m_title;

	private final OutlineType m_type;

	private final String m_typeString;

	private final URI m_url;

	private final String m_version;

	private final URI m_xmlUrl;

	public Outline(OutlineBuilder outline)
	{
		super(outline);
		m_breakpoint = outline.isBreakpoint();
		m_category = outline.getCategory();
		m_comment = outline.isComment();
		m_created = outline.getCreated();
		m_description = outline.getDescription();
		m_htmlUrl = outline.getHtmlUrl();
		m_language = outline.getLanguage();
		m_text = outline.getText();
		m_title = outline.getTitle();
		m_type = outline.getType();
		m_typeString = outline.getTypeString();
		m_url = outline.getUrl();
		m_version = outline.getVersion();
		m_xmlUrl = outline.getXmlUrl();
	}

	public String getCategory()
	{
		return m_category;
	}

	public Date getCreated()
	{
		return m_created;
	}

	@Override
	public String getDefaultTag()
	{
		return TAG;
	}

	public String getDescription()
	{
		return m_description;
	}

	public URI getHtmlUrl()
	{
		return m_htmlUrl;
	}

	public String getLanguage()
	{
		return m_language;
	}

	public String getText()
	{
		return m_text;
	}

	public String getTitle()
	{
		return m_title;
	}

	public OutlineType getType()
	{
		return m_type;
	}

	public String getTypeString()
	{
		return m_typeString;
	}

	public URI getUrl()
	{
		return m_url;
	}

	public String getVersion()
	{
		return m_version;
	}

	public URI getXmlUrl()
	{
		return m_xmlUrl;
	}

	public boolean isBreakpoint()
	{
		return m_breakpoint;
	}

	public boolean isComment()
	{
		return m_comment;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		if(m_category != null)
			Utils.addAttribute(attrs, ATTR_CATEGORY, m_category);
		if(m_created != null)
			Utils.addAttribute(attrs, ATTR_CATEGORY, Head.RFC_822_4DY_FORMAT.format(m_created));
		if(m_description != null)
			Utils.addAttribute(attrs, ATTR_DESCRIPTION, m_description);
		if(m_htmlUrl != null)
			Utils.addAttribute(attrs, ATTR_HTML_URL, m_htmlUrl.toString());
		if(m_breakpoint)
			Utils.addAttribute(attrs, ATTR_IS_BREAKPOINT, "true"); //$NON-NLS-1$
		if(m_comment)
			Utils.addAttribute(attrs, ATTR_IS_COMMENT, "true"); //$NON-NLS-1$
		if(m_language != null)
			Utils.addAttribute(attrs, ATTR_LANGUAGE, m_language);
		if(m_text != null)
			Utils.addAttribute(attrs, ATTR_TEXT, m_text);
		if(m_title != null)
			Utils.addAttribute(attrs, ATTR_TITLE, m_title);
		if(m_typeString != null)
			Utils.addAttribute(attrs, ATTR_TYPE, m_typeString);
		if(m_url != null)
			Utils.addAttribute(attrs, ATTR_URL, m_url.toString());
		if(m_version != null)
			Utils.addAttribute(attrs, ATTR_VERSION, m_version);
		if(m_xmlUrl != null)
			Utils.addAttribute(attrs, ATTR_XML_URL, m_xmlUrl.toString());
	}
}
