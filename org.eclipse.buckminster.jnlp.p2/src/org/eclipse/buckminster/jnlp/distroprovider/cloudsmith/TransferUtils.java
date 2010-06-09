/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.distroprovider.cloudsmith;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * @author Karel Brezina
 * 
 */
public class TransferUtils
{
	/**
	 * Compresses and converts into Base64
	 * 
	 * @param bytes
	 *            input byte[]
	 * @return
	 * @throws IOException
	 */
	public static byte[] compress(byte[] bytes) throws IOException
	{
		ByteArrayInputStream in = new ByteArrayInputStream(bytes);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		OutputStream gout = new GZIPOutputStream(out);
		FileUtils.copyFile(in, gout, new NullProgressMonitor());
		return Base64.encodeBase64(out.toByteArray());
	}

	/**
	 * Converts back from Base64 and decompresses
	 * 
	 * @param bytes
	 *            input byte[]
	 * @return
	 * @throws IOException
	 */
	public static byte[] decompress(byte[] bytes) throws IOException
	{
		byte[] gzipImage = Base64.decodeBase64(bytes);
		InputStream gin = new GZIPInputStream(new ByteArrayInputStream(gzipImage));
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		FileUtils.copyFile(gin, out, new NullProgressMonitor());
		return out.toByteArray();
	}
}
