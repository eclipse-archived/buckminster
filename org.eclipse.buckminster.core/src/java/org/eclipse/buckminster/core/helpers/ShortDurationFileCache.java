/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public class ShortDurationFileCache extends TimedHashMap<String, CacheEntry>
{
	public interface Materializer
	{
		File materialize(boolean[] isTemporary, IProgressMonitor monitor) throws IOException, CoreException;

		String getKey();
	}

	public ShortDurationFileCache(long keepAlive, String prefix, String suffix, File tempDir)
	{
		super(keepAlive, new TimedHashMap.EvictionPolicy<String, CacheEntry>()
		{
			public void evict(Entry<String, CacheEntry> entry)
			{
				CacheEntry ce = entry.getValue();
				if(ce != null)
					ce.remove();
			}
		});
	}

	public InputStream open(Materializer materializer, IProgressMonitor monitor) throws IOException, CoreException
	{
		String key = materializer.getKey();
		CacheEntry ce;

		// Synchronize the actual cache access. Not the whole method. We do not
		// want everyone to wait for every file.
		//
		synchronized(this)
		{
			ce = this.get(key);
			if(ce == null)
			{
				ce = new CacheEntry();
				this.put(key, ce);
			}
		}

		// This call is synchronized and will only do something for the first
		// caller.
		//
		synchronized(ce)
		{
			ce.initialize(materializer, monitor);
			return ce.open();
		}
	}
}

class CacheEntry
{
	class DeletingInputStream extends FileInputStream
	{
		DeletingInputStream(File file) throws FileNotFoundException
		{
			super(file);
		}

		@Override
		public void close() throws IOException
		{
			try
			{
				super.close();
			}
			finally
			{
				synchronized(CacheEntry.this)
				{
					if(--m_openFileCounter < 1 && m_removePending)
						m_tempFile.delete();
				}
			}
		}
	}

	private int m_openFileCounter = 0;

	private boolean m_removePending = false;

	private File m_tempFile;

	private boolean m_fileIsTemporary;

	public synchronized void initialize(ShortDurationFileCache.Materializer materializer, IProgressMonitor monitor) throws CoreException, IOException
	{
		if(m_tempFile == null)
		{
			boolean[] isTemporary = new boolean[1];
			m_tempFile = materializer.materialize(isTemporary, monitor);
			m_fileIsTemporary = isTemporary[0];
			if(m_fileIsTemporary)
				m_tempFile.deleteOnExit();
		}
	}

	public synchronized InputStream open() throws FileNotFoundException
	{
		if(m_removePending)
			throw new FileNotFoundException("File is closed");

		if(m_fileIsTemporary)
		{
			++m_openFileCounter;
			return new DeletingInputStream(m_tempFile);
		}
		return new FileInputStream(m_tempFile);
	}

	public final synchronized void remove()
	{
		if(m_openFileCounter > 0)
			//
			// Streams are currently open on this file
			//
			m_removePending = true;
		else
		if(m_fileIsTemporary && !m_removePending)
		{
			m_removePending = true;
			m_tempFile.delete();
		}
	}
}
