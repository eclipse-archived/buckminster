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

import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Class containing some trivial IO tasks that we don't want to duplicate
 * everywhere. Keep this class small. It's not supposed to be a general purpose
 * can do it all sort of thing.
 * 
 * @author Thomas Hallgren
 */
public class IOUtils {
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
