/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.runtime;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Class containing some trivial IO tasks that we don't want to duplicate
 * everywhere. Keep this class small. It's not supposed to be a general purpose
 * can do it all sort of thing.
 * 
 * @author Thomas Hallgren
 */
public class IOUtils {
	private static HashSet<File> foldersToRemove;

	/**
	 * Close but no cigar, sorry no Exception...
	 * 
	 * @param stream
	 */
	public static void close(Closeable stream) {
		if (stream != null) {
			try {
				stream.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * Copies all data read from <code>in</code> to <code>out</code> using a
	 * byte buffer of size 2048 bytes.
	 * 
	 * @param in
	 *            The stream to copy from
	 * @param out
	 *            The stream to copy to
	 * @throws IOException
	 */
	public static final void copy(InputStream in, OutputStream out, IProgressMonitor monitor) throws IOException {
		MonitorUtils.begin(monitor, IProgressMonitor.UNKNOWN);
		try {
			byte[] buffer = new byte[2048];
			int len;
			while ((len = in.read(buffer)) > 0) {
				MonitorUtils.worked(monitor, 1);
				out.write(buffer, 0, len);
			}
		} finally {
			MonitorUtils.done(monitor);
		}
	}

	/**
	 * Copies all data read from <code>in</code> to <code>out</code> using a
	 * char buffer of size 2048 bytes.
	 * 
	 * @param in
	 *            The stream to copy from
	 * @param out
	 *            The stream to copy to
	 * @throws IOException
	 */
	public static final void copy(Reader in, Writer out, IProgressMonitor monitor) throws IOException {
		MonitorUtils.begin(monitor, IProgressMonitor.UNKNOWN);
		try {
			char[] buffer = new char[1024];
			int len;
			while ((len = in.read(buffer)) > 0) {
				MonitorUtils.worked(monitor, 1);
				out.write(buffer, 0, len);
			}
		} finally {
			MonitorUtils.done(monitor);
		}
	}

	/**
	 * Creates a folder based on an abstract file handle. The folder and all its
	 * content will be deleted when the process exists. The <code>tmpDir</code>
	 * directory must not exist when this method is called.
	 * 
	 * @param tmpDir
	 *            The directory to create
	 * @throws IOException
	 *             If the directory could not be created.
	 */
	public static synchronized void createTempFolder(File tmpDir) throws IOException {
		if (tmpDir.exists())
			throw new IOException("Path already exists: " + tmpDir.getAbsolutePath()); //$NON-NLS-1$

		if (!tmpDir.mkdirs())
			throw new IOException("Unable to create directory: " + tmpDir.getAbsolutePath()); //$NON-NLS-1$

		if (foldersToRemove == null) {
			foldersToRemove = new HashSet<File>();
			Runtime.getRuntime().addShutdownHook(new Thread() {
				@Override
				public void run() {
					// Prevent that foldersToRemove is updated during the
					// remove
					//
					HashSet<File> folders = new HashSet<File>(foldersToRemove);
					foldersToRemove = null;
					for (File folder : folders) {
						try {
							deleteRecursive(folder, null);
						} catch (Exception e) {
							// We're shutting down so this is ignored
							System.err.println("Failed to remove directory " + folder.getAbsolutePath() + " :" + e.toString()); //$NON-NLS-1$ //$NON-NLS-2$
						}
					}
				}
			});
		}
		foldersToRemove.add(tmpDir);
	}

	/**
	 * Creates a folder based on a generated name. The folder and all its
	 * content will be deleted when the process exists.
	 */
	public static synchronized File createTempFolder(String prefix, String suffix, File directory) throws IOException {
		File tmpFile = File.createTempFile(prefix, suffix, directory);
		if (!tmpFile.delete())
			throw new IOException("Unable to delete file: " + tmpFile.getAbsolutePath()); //$NON-NLS-1$

		createTempFolder(tmpFile);
		return tmpFile;
	}

	public static void deleteRecursive(File file, IProgressMonitor monitor) throws IOException {
		MonitorUtils.begin(monitor, 1000);
		try {
			if (file == null)
				return;

			File[] list = file.listFiles();
			int count = (list == null) ? 0 : list.length;
			if (count > 0) {
				IProgressMonitor subMon = MonitorUtils.subMonitor(monitor, 900);
				MonitorUtils.begin(subMon, count * 100);
				try {
					if (foldersToRemove != null)
						foldersToRemove.remove(file);

					while (--count >= 0)
						deleteRecursive(list[count], MonitorUtils.subMonitor(subMon, 100));
				} finally {
					MonitorUtils.done(subMon);
				}
			} else
				MonitorUtils.worked(monitor, 900);

			if (!file.delete() && file.exists())
				throw new IOException("Unable to delete file: " + file.getAbsolutePath()); //$NON-NLS-1$
			MonitorUtils.worked(monitor, 100);
		} catch (SecurityException e) {
			throw new IOException("Unable to delete file: " + file.getAbsolutePath(), e); //$NON-NLS-1$
		} finally {
			MonitorUtils.done(monitor);
		}
	}

	public static File getTempRoot(Map<String, String> properties) {
		String tempRootStr = properties.get(Buckminster.ACTION_TEMP_ROOT);
		File tempRoot;
		if (tempRootStr == null) {
			tempRoot = new File(new File(System.getProperty("java.io.tmpdir")), "buckminster"); //$NON-NLS-1$ //$NON-NLS-2$
			properties.put(Buckminster.ACTION_TEMP_ROOT, tempRoot.getAbsolutePath());
		} else
			tempRoot = new File(tempRootStr);
		return tempRoot;
	}

	/**
	 * Convert an <code>String</code> into a <code>URI</code>.
	 * 
	 * @param uriStr
	 *            The String to be converted or <code>null</code>.
	 * @return <code>null</code> if <code>null</code> was passed, otherwise the
	 *         corresponding URI.
	 * @throws IllegalArgumentException
	 *             if the String could not be converted.
	 */
	public static URI uri(String uriStr) {
		try {
			return uriStr == null ? null : new URI(uriStr);
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("Unable to convert String into URI: " + uriStr); //$NON-NLS-1$
		}
	}

	/**
	 * Convert an <code>URL</code> into a <code>URI</code>.
	 * 
	 * @param url
	 *            The url to be converted or <code>null</code>.
	 * @return <code>null</code> if <code>null</code> was passed, otherwise the
	 *         corresponding URI.
	 * @throws IllegalArgumentException
	 *             if the URL could not be converted.
	 */
	public static URI uri(URL url) {
		try {
			return url == null ? null : url.toURI();
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException("Unable to convert URL into URI: " + url); //$NON-NLS-1$
		}
	}

	/**
	 * Convert an <code>File</code> into a <code>URL</code>.
	 * 
	 * @param file
	 *            The file to be converted or <code>null</code>.
	 * @return <code>null</code> if <code>null</code> was passed, otherwise the
	 *         corresponding URL.
	 * @throws IllegalArgumentException
	 *             if the File could not be converted.
	 */
	public static URL url(File file) {
		return file == null ? null : url(file.toURI());
	}

	/**
	 * Convert an <code>URI</code> into a <code>URL</code>.
	 * 
	 * @param uri
	 *            The uri to be converted or <code>null</code>.
	 * @return <code>null</code> if <code>null</code> was passed, otherwise the
	 *         corresponding URL.
	 * @throws IllegalArgumentException
	 *             if the URI could not be converted.
	 */
	public static URL url(URI uri) {
		try {
			return uri == null ? null : uri.toURL();
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException("Unable to convert URI into URL: " + uri); //$NON-NLS-1$
		}
	}
}
