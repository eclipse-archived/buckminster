/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.download.policy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.buckminster.download.Messages;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 * 
 */
public class Hex
{
	public static final int MAX_FAILURES = 2;

	public static byte[] decode(byte[] hexChars)
	{
		return decode(hexChars, hexChars.length);
	}

	public static byte[] decode(byte[] hexChars, int length)
	{
		int size = length / 2;
		byte[] result = new byte[size];
		for(int idx = 0; idx < size; ++idx)
		{
			int cidx = idx << 1;
			int b = (hexDigit(hexChars[cidx]) << 4) | hexDigit(hexChars[cidx + 1]);
			result[idx] = (byte)(b & 0xff);
		}
		return result;
	}

	public static byte[] readHex(String name, InputStream stream, int size) throws IOException
	{
		byte[] buffer = new byte[size * 2];
		int bytesRead;
		int remain = buffer.length;
		int totRead = 0;
		while(remain > 0 && (bytesRead = stream.read(buffer, totRead, remain)) > 0)
		{
			totRead += bytesRead;
			remain -= bytesRead;
		}

		if(totRead != buffer.length)
			throw new IOException(NLS.bind(Messages.unable_to_read_0_hex_chars_from_1, String.valueOf(buffer.length),
					name));

		return decode(buffer);
	}

	public static void writeHex(byte[] bytes, OutputStream stream) throws IOException
	{
		for(int idx = 0; idx < bytes.length; ++idx)
		{
			byte b = bytes[idx];
			int x = (b & 0xf0) >> 4;
			stream.write(x >= 10
					? x + ('a' - 10)
					: x + '0');
			x = b & 0x0f;
			stream.write(x >= 10
					? x + ('a' - 10)
					: x + '0');
		}
	}

	private static int hexDigit(byte c)
	{
		int v = 0;
		if(c >= '0' && c <= '9')
			v = c - '0';
		else if(c >= 'a' && c <= 'f')
			v = (c - 'a') + 10;
		else if(c >= 'A' && c <= 'F')
			v = (c - 'A') + 10;
		return v;
	}
}
