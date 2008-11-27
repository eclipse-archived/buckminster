/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author Thomas Hallgren
 * 
 */
public class UUIDUtil
{
	/**
	 * Create an UUID from a byte array of length 16 that is the raw data for the UUID.
	 * 
	 * @param data
	 *            The raw data for the UUID
	 * @return The created UUID.
	 */
	public static UUID fromRawBytes(byte[] data)
	{
		long msb = 0;
		long lsb = 0;
		assert data.length == 16;
		for(int i = 0; i < 8; i++)
			msb = (msb << 8) | (data[i] & 0xff);
		for(int i = 8; i < 16; i++)
			lsb = (lsb << 8) | (data[i] & 0xff);
		return new UUID(msb, lsb);
	}

	/**
	 * Static factory to retrieve a type 3 (name based) <tt>UUID</tt> based on the specified byte array.
	 * 
	 * @param name
	 *            a byte array to be used to construct a <tt>UUID</tt>.
	 * @param offset
	 *            the offset to start from in the array of bytes.
	 * @param len
	 *            the number of bytes to use, starting at <code>offset</code>.
	 * 
	 * @return a <tt>UUID</tt> generated from the specified array.
	 * @see UUID#nameUUIDFromBytes(byte[])
	 */
	public static UUID nameUUIDFromBytes(byte[] name, int offset, int len)
	{
		MessageDigest md;
		try
		{
			md = MessageDigest.getInstance("MD5");
		}
		catch(NoSuchAlgorithmException nsae)
		{
			throw new InternalError("MD5 not supported");
		}
		md.update(name, offset, len);
		byte[] md5Bytes = md.digest();
		md5Bytes[6] &= 0x0f; /* clear version */
		md5Bytes[6] |= 0x30; /* set to version 3 */
		md5Bytes[8] &= 0x3f; /* clear variant */
		md5Bytes[8] |= 0x80; /* set to IETF variant */
		return fromRawBytes(md5Bytes);
	}
}
