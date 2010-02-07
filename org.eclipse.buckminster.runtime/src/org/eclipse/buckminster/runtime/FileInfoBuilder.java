/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.runtime;

import java.util.Properties;

/**
 * @author Filip Hrbek
 * 
 */
public class FileInfoBuilder implements IFileInfo {
	private String contentType;

	private long lastModified = 0L;

	private String name;

	private long size = -1L;

	public FileInfoBuilder() {
	}

	public FileInfoBuilder(IFileInfo fileInfo) {
		initFrom(fileInfo);
	}

	public FileInfoBuilder(Properties properties) {
		name = properties.getProperty(PROPERTY_NAME);
		contentType = properties.getProperty(PROPERTY_CONTENT_TYPE);

		String v = properties.getProperty(PROPERTY_LAST_MODIFIED);
		if (v != null)
			lastModified = Long.parseLong(v);

		v = properties.getProperty(PROPERTY_SIZE);
		if (v != null)
			size = Long.parseLong(v);
	}

	public void addProperties(Properties properties) {
		if (contentType != null)
			properties.setProperty(PROPERTY_CONTENT_TYPE, contentType);
		if (lastModified != 0L)
			properties.setProperty(PROPERTY_LAST_MODIFIED, Long.toString(lastModified));
		if (name != null)
			properties.setProperty(PROPERTY_NAME, name);
		if (size != -1L)
			properties.setProperty(PROPERTY_SIZE, Long.toString(size));
	}

	public final String getContentType() {
		return contentType;
	}

	public long getLastModified() {
		return lastModified;
	}

	public final String getRemoteName() {
		return name;
	}

	public final long getSize() {
		return size;
	}

	public void initFrom(IFileInfo info) {
		setName(info.getRemoteName());
		setContentType(info.getContentType());
		setSize(info.getSize());
		setLastModified(info.getLastModified());
	}

	public void reset() {
		name = null;
		contentType = null;
		size = -1;
		lastModified = 0;
	}

	public final void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setLastModified(long timestamp) {
		this.lastModified = timestamp;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final void setSize(long size) {
		this.size = size;
	}
}
