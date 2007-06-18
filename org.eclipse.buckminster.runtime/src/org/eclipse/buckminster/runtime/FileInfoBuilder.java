/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.runtime;

/**
 * @author Filip Hrbek
 * 
 */
public class FileInfoBuilder implements IFileInfo
{
	private String m_name;

	private String m_contentType;

	private Long m_size;

	/**
	 * Creates an empty FileInfoBuilder
	 */
	public FileInfoBuilder()
	{
		reset();
	}

	/**
	 * Creates a new instance as a copy of specified file info
	 * 
	 * @param fileInfo
	 */
	public FileInfoBuilder(FileInfoBuilder fileInfo)
	{
		setAll(fileInfo);
	}

	/**
	 * Resets all attributes to null
	 */
	public void reset()
	{
		m_name = null;
		m_contentType = null;
		m_size = null;
	}

	/**
	 * Copies all attributes from specified source
	 * 
	 * @param info
	 */
	public void setAll(IFileInfo info)
	{
		setName(info.getName());
		setContentType(info.getContentType());
		setSize(info.getSize());
	}

	/**
	 * @return the name
	 */
	public final String getName()
	{
		return m_name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public final void setName(String name)
	{
		m_name = name;
	}

	/**
	 * @return the contentType
	 */
	public final String getContentType()
	{
		return m_contentType;
	}

	/**
	 * @param contentType
	 *            the contentType to set
	 */
	public final void setContentType(String contentType)
	{
		m_contentType = contentType;
	}

	/**
	 * @return the size
	 */
	public final Long getSize()
	{
		return m_size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public final void setSize(Long size)
	{
		m_size = size;
	}

	/**
	 * @return the most suitable extension (may return null)
	 */
	public String getExtension()
	{
		String[] extensions = getExtensions();

		return extensions.length > 0
				? extensions[0]
				: null;
	}

	/**
	 * @return all extensions mapped to the file's content type (if not known, returns an empty array)
	 */
	public String[] getExtensions()
	{
		// A very stupid implementation for now... Should use some real MIME type mapping,
		// e.g. from javax.activation
		if(m_contentType != null)
		{
			if(m_contentType.endsWith("/zip") || m_contentType.endsWith("/x-zip-compressed")
					|| m_contentType.endsWith("/x-compressed") || m_contentType.endsWith("/x-zip"))
				return new String[] { "zip" };
			else if(m_contentType.endsWith("/gzip") || m_contentType.endsWith("/x-gzip"))
				return new String[] { "tgz" };
			else if(m_contentType.endsWith("/x-tar"))
				return new String[] { "tgz" };
			else if(m_contentType.endsWith("/java-archive") || m_contentType.endsWith("/x-jar"))
				return new String[] { "jar" };
			if(m_contentType.endsWith("/exe") || m_contentType.endsWith("/x-msdownload"))
				return new String[] { "exe" };
		}

		return new String[0];
	}
}
