/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.opml.builder;

import java.net.URI;
import java.util.Date;

import org.eclipse.buckminster.opml.IOutline;
import org.eclipse.buckminster.opml.OutlineType;

/**
 * @author Thomas Hallgren
 * 
 */
public class OutlineBuilder extends BodyBuilder implements IOutline
{
	private boolean m_breakpoint;

	private String m_category;

	private boolean m_comment;

	private Date m_created;

	private String m_description;

	private URI m_htmlUrl;

	private String m_language;

	private String m_text;

	private String m_title;

	private String m_typeString;

	private URI m_url;

	private String m_version;

	private URI m_xmlUrl;

	@Override
	public void clear()
	{
		super.clear();
		m_breakpoint = false;
		m_category = null;
		m_comment = false;
		m_created = null;
		m_description = null;
		m_htmlUrl = null;
		m_language = null;
		m_text = null;
		m_title = null;
		m_typeString = null;
		m_url = null;
		m_version = null;
		m_xmlUrl = null;
	}

	public String getCategory()
	{
		return m_category;
	}

	public Date getCreated()
	{
		return m_created;
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
		OutlineType type;
		if(m_typeString == null)
			type = OutlineType.TEXT;
		else
		{
			String tmp = m_typeString.toUpperCase();
			try
			{
				if("TEXT/X-OPML".equals(tmp))
					tmp = "OPML";
				type = OutlineType.valueOf(tmp);
			}
			catch(IllegalArgumentException e)
			{
				type = OutlineType.UNKNOWN;
			}
		}
		return type;
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

	public void initFrom(IOutline outline)
	{
		super.initFrom(outline);
		m_breakpoint = outline.isBreakpoint();
		m_category = outline.getCategory();
		m_comment = outline.isComment();
		m_created = outline.getCreated();
		m_description = outline.getDescription();
		m_htmlUrl = outline.getHtmlUrl();
		m_language = outline.getLanguage();
		m_text = outline.getText();
		m_title = outline.getTitle();
		m_typeString = outline.getTypeString();
		m_url = outline.getUrl();
		m_version = outline.getVersion();
		m_xmlUrl = outline.getXmlUrl();
	}

	public boolean isBreakpoint()
	{
		return m_breakpoint;
	}

	public boolean isComment()
	{
		return m_comment;
	}

	public void setBreakpoint(boolean breakpoint)
	{
		m_breakpoint = breakpoint;
	}

	public void setCategory(String category)
	{
		m_category = category;
	}

	public void setComment(boolean comment)
	{
		m_comment = comment;
	}

	public void setCreated(Date created)
	{
		m_created = created;
	}

	public void setDescription(String description)
	{
		m_description = description;
	}

	public void setHtmlUrl(URI htmlUrl)
	{
		m_htmlUrl = htmlUrl;
	}

	public void setLanguage(String language)
	{
		m_language = language;
	}

	public void setText(String text)
	{
		m_text = text;
	}

	public void setTitle(String title)
	{
		m_title = title;
	}

	public void setTypeString(String type)
	{
		m_typeString = type;
	}

	public void setUrl(URI url)
	{
		m_url = url;
	}

	public void setVersion(String version)
	{
		m_version = version;
	}

	public void setXmlUrl(URI xmlUrl)
	{
		m_xmlUrl = xmlUrl;
	}
}
