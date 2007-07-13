/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp.cache;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.eclipse.buckminster.jnlp.bootstrap.BootstrapConstants;
import org.eclipse.buckminster.jnlp.bootstrap.JNLPException;

import sun.misc.BASE64Encoder;

/**
 * @author Filip Hrbek
 * 
 * Useful utilities.
 */
public class Utils
{
	/**
	 * Copies specified input stream into the output stream and closes the input stream whil the output stream
	 * remains open.
	 * 
	 * @param is
	 * @param os
	 * @throws IOException
	 */
	public static void streamCopy(InputStream is, OutputStream os) throws IOException
	{
		byte[] buffer = new byte[1024];
		int len;
		
		while ((len = is.read(buffer)) != -1)
		{
			os.write(buffer, 0, len);
		}
		is.close();
	}

	/**
	 * Creates a hash code for specified url, suitable for creating a folder in the cache structure.
	 * 
	 * @param jnlp
	 * @return
	 * @throws JNLPException
	 */
	public static String createHash(URL jnlp) throws JNLPException
	{
		MessageDigest md;

		try
		{
			md = MessageDigest.getInstance("MD5");
		}
		catch(NoSuchAlgorithmException e)
		{
			throw new JNLPException(e.getMessage(), "Report problem to distro vendor",
					BootstrapConstants.ERROR_CODE_JNLP_SAX_EXCEPTION, e);
		}

		return new BASE64Encoder().encode(md.digest(jnlp.toString().getBytes()));
	}
	

	/**
	 * Formats a timestamp to a format suitable for easy cache management.
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date)
	{
		return formatDate(Long.valueOf(date.getTime()));
	}

	/**
	 * Formats a timestamp to a format suitable for easy cache management.
	 * 
	 * @param timestamp
	 * @return
	 */
	public static String formatDate(Long timestamp)
	{
		return String.format("%d", timestamp);
	}

	public static void deleteRecursive(File file) throws JNLPException
	{
		if(!file.exists())
			return;

		try
		{
			File[] list = file.listFiles();
			int count = (list == null)
					? 0
					: list.length;
			if(count > 0)
			{
				while(--count >= 0)
					deleteRecursive(list[count]);
			}

			if(!file.delete() && file.exists())
				throw new JNLPException("Unable to delete " + file.getAbsolutePath(), "Check file permissions",
						BootstrapConstants.ERROR_CODE_FILE_IO_EXCEPTION);
		}
		catch(SecurityException e)
		{
			throw new JNLPException("Unable to delete " + file.getAbsolutePath() + ": " + e.getMessage(),
					"Check file permissions", BootstrapConstants.ERROR_CODE_FILE_IO_EXCEPTION, e);
		}
	}
}
