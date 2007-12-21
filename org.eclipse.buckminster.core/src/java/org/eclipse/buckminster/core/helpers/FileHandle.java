/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.core.helpers;

import java.io.File;

/**
 * 
 * @author Thomas Hallgren
 *
 */
public class FileHandle
{
	private final String m_name;

	private final File m_file;

	private final boolean m_isTemporary;

	public FileHandle(String name, File file, boolean isTemporary)
	{
		m_name = name;
		m_file = file;
		m_isTemporary = isTemporary;
	}

	public String getName()
	{
		return m_name;
	}

	public File getFile()
	{
		return m_file;
	}

	public boolean isTemporary()
	{
		return m_isTemporary;
	}
}
