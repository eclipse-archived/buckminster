/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.equinox.p2.authoring.internal;

import java.util.Properties;

/**
 * @author Filip Hrbek
 * 
 */
public class FileInfoBuilder implements IFileInfo
{
	private String m_contentType;

	private long m_lastModified = 0L;

	private String m_name;

	private long m_size = -1L;

	public FileInfoBuilder()
	{
	}

	public FileInfoBuilder(IFileInfo fileInfo)
	{
		initFrom(fileInfo);
	}

	public FileInfoBuilder(Properties properties)
	{
		m_name = properties.getProperty(PROPERTY_NAME);
		m_contentType = properties.getProperty(PROPERTY_CONTENT_TYPE);

		String v = properties.getProperty(PROPERTY_LAST_MODIFIED);
		if(v != null)
			m_lastModified = Long.parseLong(v);

		v = properties.getProperty(PROPERTY_SIZE);
		if(v != null)
			m_size = Long.parseLong(v);
	}

	public void addProperties(Properties properties)
	{
		if(m_contentType != null)
			properties.setProperty(PROPERTY_CONTENT_TYPE, m_contentType);
		if(m_lastModified != 0L)
			properties.setProperty(PROPERTY_LAST_MODIFIED, Long.toString(m_lastModified));
		if(m_name != null)
			properties.setProperty(PROPERTY_NAME, m_name);
		if(m_size != -1L)
			properties.setProperty(PROPERTY_SIZE, Long.toString(m_size));
	}

	public final String getContentType()
	{
		return m_contentType;
	}

	public long getLastModified()
	{
		return m_lastModified;
	}

	public final String getName()
	{
		return m_name;
	}

	public final long getSize()
	{
		return m_size;
	}

	public void initFrom(IFileInfo info)
	{
		setName(info.getName());
		setContentType(info.getContentType());
		setSize(info.getSize());
		setLastModified(info.getLastModified());
	}

	public void reset()
	{
		m_name = null;
		m_contentType = null;
		m_size = -1;
		m_lastModified = 0;
	}

	public final void setContentType(String contentType)
	{
		m_contentType = contentType;
	}

	public void setLastModified(long timestamp)
	{
		m_lastModified = timestamp;
	}

	public final void setName(String name)
	{
		m_name = name;
	}

	public final void setSize(long size)
	{
		m_size = size;
	}
}
