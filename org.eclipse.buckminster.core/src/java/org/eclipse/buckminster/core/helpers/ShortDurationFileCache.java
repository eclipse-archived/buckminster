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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public class ShortDurationFileCache extends TimedHashMap<String, CacheEntry> {
	public interface Materializer {
		String getKey();

		FileHandle materialize(IProgressMonitor monitor, FileInfoBuilder fileInfo) throws IOException, CoreException;
	}

	public ShortDurationFileCache(long keepAlive, String prefix, String suffix, File tempDir) {
		super(keepAlive, new TimedHashMap.EvictionPolicy<String, CacheEntry>() {
			@Override
			public void evict(Entry<String, CacheEntry> entry) {
				CacheEntry ce = entry.getValue();
				if (ce != null)
					ce.remove();
			}
		});
	}

	public InputStream open(Materializer materializer, IProgressMonitor monitor) throws IOException, CoreException {
		return open(materializer, monitor, null);
	}

	public InputStream open(Materializer materializer, IProgressMonitor monitor, FileInfoBuilder fileInfo) throws IOException, CoreException {
		String key = materializer.getKey();
		CacheEntry ce;

		// Synchronize the actual cache access. Not the whole method. We do not
		// want everyone to wait for every file.
		//
		synchronized (this) {
			ce = get(key);
			if (ce == null) {
				ce = new CacheEntry();
				put(key, ce);
			}
		}

		// This call is synchronized and will only do something for the first
		// caller.
		//
		synchronized (ce) {
			ce.initialize(this, materializer, monitor, fileInfo);
			return ce.open();
		}
	}

	/**
	 * This method will always return false since we want to defer scheduling
	 * until the completion of a materialization.
	 * 
	 * @return false
	 */
	@Override
	public boolean scheduleOnPut() {
		return false;
	}
}

class CacheEntry {
	class DeletingInputStream extends FileInputStream {
		DeletingInputStream(File file) throws FileNotFoundException {
			super(file);
		}

		@Override
		public void close() throws IOException {
			try {
				super.close();
			} finally {
				synchronized (CacheEntry.this) {
					if (--openFileCounter < 1 && removePending)
						tempFile.getFile().delete();
				}
			}
		}
	}

	private int openFileCounter = 0;

	private boolean removePending = false;

	private FileHandle tempFile;

	private FileInfoBuilder fileInfo = null;

	public synchronized void initialize(ShortDurationFileCache cache, ShortDurationFileCache.Materializer materializer, IProgressMonitor monitor,
			FileInfoBuilder info) throws CoreException, IOException {
		boolean success = false;
		try {
			if (tempFile == null) {
				tempFile = materializer.materialize(monitor, info);
				if (tempFile.isTemporary())
					tempFile.getFile().deleteOnExit();
				if (info != null)
					fileInfo = new FileInfoBuilder(info);
				cache.schedule(materializer.getKey());
			} else if (fileInfo != null && info != null)
				fileInfo.initFrom(info);

			// All is well. No exceptions will bring us here.
			//
			success = true;
		} finally {
			if (!success)
				//
				// We're leaving because of some exception. Remove the
				// entry immediately.
				//
				cache.remove(materializer.getKey());
		}
	}

	public synchronized InputStream open() throws FileNotFoundException {
		if (removePending)
			throw new FileNotFoundException(Messages.File_is_closed);

		if (tempFile.isTemporary()) {
			++openFileCounter;
			return new DeletingInputStream(tempFile.getFile());
		}
		return new FileInputStream(tempFile.getFile());
	}

	public final synchronized void remove() {
		if (openFileCounter > 0)
			//
			// Streams are currently open on this file
			//
			removePending = true;
		else if (tempFile != null && tempFile.isTemporary() && !removePending) {
			removePending = true;
			tempFile.getFile().delete();
		}
	}
}
