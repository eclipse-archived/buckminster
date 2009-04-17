/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp.p2.bootstrap;

import static org.eclipse.buckminster.jnlp.p2.bootstrap.BootstrapConstants.ERROR_CODE_REMOTE_IO_EXCEPTION;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import sun.misc.BASE64Encoder;

/**
 * @author Filip Hrbek
 * 
 *         Useful utilities.
 */
public class Utils
{
	public static void close(Closeable closeable)
	{
		if(closeable != null)
		{
			try
			{
				closeable.close();
			}
			catch(IOException e)
			{
			}
		}
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
			md = MessageDigest.getInstance("MD5"); //$NON-NLS-1$
		}
		catch(NoSuchAlgorithmException e)
		{
			throw new JNLPException(e.getMessage(), Messages.getString("report_problem_to_distro_vendor"), //$NON-NLS-1$
					BootstrapConstants.ERROR_CODE_JNLP_SAX_EXCEPTION, e);
		}

		return new BASE64Encoder().encode(md.digest(jnlp.toString().getBytes()));
	}

	public static Image createImage(byte[] imageData)
	{
		return imageData != null
				? Toolkit.getDefaultToolkit().createImage(imageData)
				: null;
	}

	public static Image createImage(String url) throws JNLPException
	{
		return createImage(loadData(url));
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
				throw new JNLPException(
						Messages.getString("unable_to_delete") + file.getAbsolutePath(), Messages.getString("check_file_permissions"), //$NON-NLS-1$ //$NON-NLS-2$
						BootstrapConstants.ERROR_CODE_FILE_IO_EXCEPTION);
		}
		catch(SecurityException e)
		{
			throw new JNLPException(
					Messages.getString("unable_to_delete") + file.getAbsolutePath() + ": " + e.getMessage(), //$NON-NLS-1$ //$NON-NLS-2$
					Messages.getString("check_file_permissions"), BootstrapConstants.ERROR_CODE_FILE_IO_EXCEPTION, e); //$NON-NLS-1$
		}
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
		return String.format("%d", timestamp); //$NON-NLS-1$
	}

	public static byte[] loadData(String url) throws JNLPException
	{
		byte[] data = null;
		if(url != null)
		{
			InputStream is = null;
			try
			{
				is = new URL(url).openStream();
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				byte[] buf = new byte[0x1000];
				int count;
				while((count = is.read(buf)) > 0)
					os.write(buf, 0, count);
				data = os.toByteArray();

			}
			catch(IOException e)
			{
				throw new JNLPException(
						Messages.getString("unable_to_read_a_splash_screen_or_window_icon_image"), //$NON-NLS-1$
						Messages.getString("check_your_internet_connection_and_try_again"), ERROR_CODE_REMOTE_IO_EXCEPTION, e); //$NON-NLS-1$
			}
			finally
			{
				Utils.close(is);
			}
		}

		return data;
	}

	/**
	 * Copies specified input stream into the output stream and closes the input stream whil the output stream remains
	 * open.
	 * 
	 * @param is
	 * @param os
	 * @throws IOException
	 */
	public static void streamCopy(InputStream is, OutputStream os) throws IOException
	{
		byte[] buffer = new byte[1024];
		int len;

		while((len = is.read(buffer)) != -1)
		{
			os.write(buffer, 0, len);
		}
		is.close();
	}

}
