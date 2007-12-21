/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.reader;

import java.io.IOException;

import org.eclipse.buckminster.core.helpers.FileHandle;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author thhal
 */
public class RemoteFile
{
	private final ICatalogReader m_reader;
	private final String m_fileName;

	public RemoteFile(ICatalogReader reader, String fileName)
	{
		m_reader = reader;
		m_fileName = fileName;
	}

	@Override
	public String toString()
	{
		return m_reader.toString() + ',' + m_fileName;
	}

	public FileHandle getContents(IProgressMonitor monitor) throws CoreException, IOException
	{
		return m_reader.getContents(m_fileName, monitor);
	}
	
	public void close()
	{
		m_reader.close();
	}
}
