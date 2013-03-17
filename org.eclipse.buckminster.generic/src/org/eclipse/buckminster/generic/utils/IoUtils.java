/*******************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.generic.utils;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.eclipse.buckminster.generic.Messages;
import org.eclipse.buckminster.generic.plugin.AbstractPlugin;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 * @author Henrik Lindberg
 * 
 */
public class IoUtils {
	/**
	 * Close the given stream. Catch and log any {@link IOException} that might
	 * be thrown. This method is suitable for use in finally block since you
	 * don't want an exception that is thrown there to shadow other exceptions
	 * thrown from the try block.
	 * 
	 * @param plugin
	 *            The plugin (with support for logging) where the error
	 *            occurred.
	 * @param stream
	 *            The stream to close. Can be <code>null</code> in which case it
	 *            is ignored.
	 */
	public static void close(AbstractPlugin plugin, Closeable stream) {
		if (stream == null)
			return;

		try {
			stream.close();
		} catch (Exception e) {
			plugin.logError(NLS.bind(Messages.error_closing_instance_0, stream.getClass().getName()), e);
		}
	}

	/**
	 * Delete the file or folder given in <code>fileOrFolder</code>. A folder
	 * will be cleaned out (recursively) before it is deleted.
	 * 
	 * @param fileOrFolder
	 *            The file or folder to delete.
	 */
	public static void deleteFileOrFolder(File fileOrFolder) {
		File[] files = fileOrFolder.listFiles();
		if (files != null) {
			int idx = files.length;
			while (--idx >= 0)
				deleteFileOrFolder(files[idx]);
		}
		fileOrFolder.delete();
	}

	/**
	 * Open the input stream of the given file store and exposes any
	 * FileNotFoundException that hides in a potential CoreException.
	 * 
	 * @param fs
	 *            The file store to open.
	 * @param monitor
	 * @return The stream returned from the
	 *         {@link IFileStore#openInputStream(int, IProgressMonitor)} call.
	 * @throws CoreException
	 * @throws FileNotFoundException
	 */
	public static InputStream openInput(IFileStore fs, IProgressMonitor monitor) throws CoreException, FileNotFoundException {
		try {
			return fs.openInputStream(EFS.NONE, monitor);
		} catch (CoreException e) {
			Throwable cause = e.getStatus().getException();
			if (cause instanceof FileNotFoundException)
				throw (FileNotFoundException) cause;

			throw e;
		}
	}

}
