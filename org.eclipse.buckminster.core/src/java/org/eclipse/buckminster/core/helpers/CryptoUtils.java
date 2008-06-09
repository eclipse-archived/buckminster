/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.helpers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Cryptology utils
 * 
 * @author Karel Brezina
 *
 */
public class CryptoUtils
{
	private static String ENCODING_UTF8 = "UTF-8";

	/**
	 * Encrypts an input string using a given algorithm 
	 * @param input
	 * @param algorithmName
	 * @return
	 */
	public static String encrypt(String input, String algorithmName)
	{
		return encrypt(input, ENCODING_UTF8, algorithmName);
	}
	
	/**
	 * Encrypts an input string using a given algorithm 
	 * @param input
	 * @param encodingCharsetName
	 * @param algorithmName
	 * @return
	 */
	public static String encrypt(String input, String encodingCharsetName, String algorithmName)
	{
		try
		{
			return encrypt(input.getBytes(encodingCharsetName), algorithmName);
		}
		catch(UnsupportedEncodingException e)
		{
			throw new RuntimeException("Internal error: " + ENCODING_UTF8 + " is not supported encoding", e);
		}
	}
	
	/**
	 * Encrypts an input byte array using a given algorithm 
	 * @param input
	 * @param algorithmName
	 * @return
	 */
	public static String encrypt(byte[] bytes, String algorithmName)
	{
		String md5val = "";
		MessageDigest algorithm = null;

		try
		{
			algorithm = MessageDigest.getInstance(algorithmName);
		}
		catch(NoSuchAlgorithmException nsae)
		{
			throw new IllegalArgumentException("Unknown encrypt algorithm: " + algorithmName);
		}

		algorithm.reset();
		algorithm.update(bytes);
		byte messageDigest[] = algorithm.digest();
		StringBuffer hexString = new StringBuffer();

		for(int i = 0; i < messageDigest.length; i++)
		{
			String hex = Integer.toHexString(0xFF & messageDigest[i]);
			if(hex.length() == 1)
			{
				hexString.append('0');
			}
			hexString.append(hex);
		}
		md5val = hexString.toString();
		return md5val;
	}

}
