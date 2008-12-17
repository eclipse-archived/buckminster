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

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.runtime.FileInfoBuilder;
import org.eclipse.buckminster.runtime.IFileInfo;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

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
						m_tempFile.getFile().delete();
				}
			}
		}
	}

	private int m_openFileCounter = 0;

	private boolean m_removePending = false;

	private FileHandle m_tempFile;

	private IFileInfo m_fileInfo = null;

	public synchronized void initialize(ShortDurationFileCache cache, ShortDurationFileCache.Materializer materializer,
			IProgressMonitor monitor, FileInfoBuilder fileInfo) throws CoreException, IOException
	{
		boolean success = false;
		try
		{
			if(m_tempFile == null)
			{
				m_tempFile = materializer.materialize(monitor, fileInfo);
				if(m_tempFile.isTemporary())
					m_tempFile.getFile().deleteOnExit();
				if(fileInfo != null)
					m_fileInfo = new FileInfoBuilder(fileInfo);
				cache.schedule(materializer.getKey());
			}
			else if(fileInfo != null && m_fileInfo != null)
				fileInfo.initFrom(m_fileInfo);

			// All is well. No exceptions will bring us here.
			//
			success = true;
		}
		finally
		{
			if(!success)
				//
				// We're leaving because of some exception. Remove the
				// entry immediately.
				//
				cache.remove(materializer.getKey());
		}
	}

	public synchronized InputStream open() throws FileNotFoundException
	{
		if(m_removePending)
			throw new FileNotFoundException(Messages.File_is_closed);

		if(m_tempFile.isTemporary())
		{
			++m_openFileCounter;
			return new DeletingInputStream(m_tempFile.getFile());
		}
		return new FileInputStream(m_tempFile.getFile());
	}

	public final synchronized void remove()
	{
		if(m_openFileCounter > 0)
			//
			// Streams are currently open on this file
			//
			m_removePending = true;
		else if(m_tempFile != null && m_tempFile.isTemporary() && !m_removePending)
		{
			m_removePending = true;
			m_tempFile.getFile().delete();
		}
	}
}

/**
 * @author Thomas Hallgren
 */
public class ShortDurationFileCache extends TimedHashMap<String, CacheEntry>
{
	public interface Materializer
	{
		String getKey();

		FileHandle materialize(IProgressMonitor monitor, FileInfoBuilder fileInfo) throws IOException, CoreException;
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
		return open(materializer, monitor, null);
	}

	public InputStream open(Materializer materializer, IProgressMonitor monitor, FileInfoBuilder fileInfo)
			throws IOException, CoreException
	{
		String key = materializer.getKey();
		CacheEntry ce;

		// Synchronize the actual cache access. Not the whole method. We do not
		// want everyone to wait for every file.
		//
		synchronized(this)
		{
			ce = get(key);
			if(ce == null)
			{
				ce = new CacheEntry();
				put(key, ce);
			}
		}

		// This call is synchronized and will only do something for the first
		// caller.
		//
		synchronized(ce)
		{
			ce.initialize(this, materializer, monitor, fileInfo);
			return ce.open();
		}
	}

	/**
	 * This method will always return false since we want to defer scheduling until the completion of a materialization.
	 * 
	 * @return false
	 */
	@Override
	public boolean scheduleOnPut()
	{
		return false;
	}
}
