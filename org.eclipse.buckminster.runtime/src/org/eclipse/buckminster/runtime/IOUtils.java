/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.runtime;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * Class containing some trivial IO tasks that we don't want to duplicate everywhere. Keep this class small. It's not
 * supposed to be a general purpose can do it all sort of thing.
 * 
 * @author Thomas Hallgren
 */
public class IOUtils
{
	/**
	 * Copies all data read from <code>in</code> to <code>out</code> using a byte buffer of size 2048 bytes.
	 * 
	 * @param in
	 *            The stream to copy from
	 * @param out
	 *            The stream to copy to
	 * @throws IOException
	 */
	public static final void copy(InputStream in, OutputStream out) throws IOException
	{
		byte[] buffer = new byte[2048];
		int len;
		while((len = in.read(buffer)) > 0)
			out.write(buffer, 0, len);
	}

	/**
	 * Copies all data read from <code>in</code> to <code>out</code> using a char buffer of size 2048 bytes.
	 * 
	 * @param in
	 *            The stream to copy from
	 * @param out
	 *            The stream to copy to
	 * @throws IOException
	 */
	public static final void copy(Reader in, Writer out) throws IOException
	{
		char[] buffer = new char[1024];
		int len;
		while((len = in.read(buffer)) > 0)
			out.write(buffer, 0, len);
	}

	/**
	 * Close but no cigar, sorry no Exception...
	 * @param stream
	 */
	public static void close(Closeable stream)
	{
		if(stream != null)
		{
			try
			{
				stream.close();
			}
			catch(IOException e)
			{
			}
		}
	}
}
