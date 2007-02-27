/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.helpers;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.runtime.Trivial;

/**
 * @author kolwing
 *
 */
public class TextUtils
{
	public static String EMPTY_STRING = "";

	/**
	 * Create a standard bean getter method name that corresponds
	 * to <code>propName</code>
	 * @param propName The name of a bean property
	 * @return The getter method name
	 */
	public static String makeGetter(String propName)
	{
		final int len = propName.length();
		char[] allChars = new char[len + 3];
		allChars[0] = 'g';
		allChars[1] = 'e';
		allChars[2] = 't';
		allChars[3] = Character.toUpperCase(propName.charAt(0));
		for(int idx = 1; idx < len; ++idx)
			allChars[idx + 3] = propName.charAt(idx);
		return new String(allChars);
	}

	/**
	 * Helper method to turn a byte array into a hex string
	 */
	public static String makeHexString(byte[] bytes)
		{
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for (int i = 0 ; i < bytes.length ; i++)
			{
			int v = (bytes[i] & 0xff);
			if (v < 16)
				sb.append('0');
			sb.append(Integer.toHexString(v));
			}
		return sb.toString();
		}
	
	/**
	 * Helper method to turn a hex string into a byte array 
	 *
	 */
	public static byte[] makeByteArrayFromHexString(String s)
	{
		byte[] bytes = new byte[s.length() / 2];
		
		for (int i = 0 ; i < bytes.length ; i++)
			bytes[i] = (byte)Integer.parseInt(s.substring(i*2, i*2+2), 16);
		
		return bytes;
	}

	/**
	 * Builds a comma separated list of <code>strings</code>. It is assumed
	 * that none of the supplied strings contains commas. This method will not
	 * add escape characters or the like.
	 * 
	 * @param strings
	 *            The strings to concatenate.
	 * @return The concatenated result or <code>null</code> if the
	 *         <code>strings</code> argument is <code>null</code> or of zero
	 *         length.
	 */
	public static String toCommaSeparatedList(Collection<String> strings)
	{
		if(strings != null && strings.size() > 0)
		{
			StringBuilder bld = new StringBuilder();
			buildCommaSeparatedList(bld, strings);
			return bld.toString();
		}
		return null;
	}

	/**
	 * Builds a comma separated list of <code>strings</code>. It is assumed
	 * that none of the supplied strings contains commas. This method will not
	 * add escape characters or the like.
	 * 
	 * @param strings
	 *            The strings to concatenate.
	 * @return The concatenated result or <code>null</code> if the
	 *         <code>strings</code> argument is <code>null</code> or of zero
	 *         length.
	 */
	public static String toCommaSeparatedList(String[] strings)
	{
		if(strings != null && strings.length > 0)
		{
			StringBuilder bld = new StringBuilder();
			buildCommaSeparatedList(bld, strings);
			return bld.toString();
		}
		return null;
	}

	/**
	 * Appends a comma separated list of <code>strings</code> onto
	 * <code>builder</code>. It is assumed that none of the supplied strings
	 * contains commas. This method will not add escape characters or the like.
	 * No leading or trailing comma will be added and nothing at all will be
	 * added if the array <code>strings</code> is <code>null</code> or of
	 * zero length.
	 * 
	 * @param builder
	 *            The receiver.
	 * @param strings
	 *            The strings to concatenate.
	 */
	public static void buildCommaSeparatedList(StringBuilder builder, Collection<String> strings)
	{
		if(strings == null)
			return;

		boolean needComma = false;
		for(String str : strings)
		{
			if(needComma)
				builder.append(',');
			else
				needComma = true;
			builder.append(str);
		}
	}

	/**
	 * Appends a comma separated list of <code>strings</code> onto
	 * <code>builder</code>. It is assumed that none of the supplied strings
	 * contains commas. This method will not add escape characters or the like.
	 * No leading or trailing comma will be added and nothing at all will be
	 * added if the array <code>strings</code> is <code>null</code> or of
	 * zero length.
	 * 
	 * @param builder
	 *            The receiver.
	 * @param strings
	 *            The strings to concatenate.
	 */
	public static void buildCommaSeparatedList(StringBuilder builder, String[] strings)
	{
		if(strings == null)
			return;
		int top = strings.length;
		if(top > 0)
		{
			builder.append(strings[0]);
			for(int idx = 1; idx < top; ++idx)
			{
				builder.append(',');
				builder.append(strings[idx]);
			}
		}
	}

	/**
	 * Writes a comma separated list of <code>strings</code> on
	 * <code>writer</code>. It is assumed that none of the supplied strings
	 * contains commas. This method will not add escape characters or the like.
	 * No leading or trailing comma will be added and nothing at all will be
	 * added if the array <code>strings</code> is <code>null</code> or of
	 * zero length.
	 * 
	 * @param writer
	 *            The receiver.
	 * @param strings
	 *            The strings to concatenate.
	 */
	public static void buildCommaSeparatedList(Writer writer, String[] strings)
	throws IOException
	{
		if(strings == null)
			return;
		int top = strings.length;
		if(top > 0)
		{
			writer.append(strings[0]);
			for(int idx = 1; idx < top; ++idx)
			{
				writer.append(',');
				writer.append(strings[idx]);
			}
		}
	}
	
	public static Map<String,String> queryAsParameters(String query)
	{
		if(query == null)
			return Collections.emptyMap();

		String[] pairs = decodeToQueryPairs(query);
		int top = pairs.length;
		if(top == 0)
			return Collections.emptyMap();

		Map<String, String> p = new HashMap<String,String>(top);
		while(--top >= 0)
		{
			// now split the pair on the first '=' only
			// (one '=' is required to be there, even if the value is blank)
			//
			String[] kv = pairs[top].split("=", 2);
			p.put(kv[0], kv[1]);
		}
		return p;
	}

	public static String[] decodeToQueryPairs(String query)
	{
		if(query == null || query.length() == 0)
			return Trivial.EMPTY_STRING_ARRAY;

		// split on a solitary '&'
		//
		String[] pairs = query.split("(?<!&)&(?!&)");
		int idx = pairs.length;
		if(idx == 0)
			return Trivial.EMPTY_STRING_ARRAY;

		while(--idx >= 0)
			pairs[idx] = pairs[idx].replace("&&", "&");
		return pairs;
	}

	public static String encodeFromQueryPairs(List<String> pairs)
	{
		if(pairs == null || pairs.size() == 0)
			return null;

		StringBuilder query = new StringBuilder();
		for(String pair : pairs)
		{
			// if k/v pairs have already been added, add a single delimiter
			//
			if(query.length() > 0)
				query.append('&');

			// ensure that any 'delimiter' is doubled
			//
			query.append(pair.replace("&", "&&"));
		}

		return query.toString();
	}

	public static String notNullString(Object txt)
	{
		return txt == null ? "" : txt.toString();
	}

	public static String emptyTrimmedStringAsNull(String s)
	{
		if(s == null || s.length() == 0)
			return null;

		String ts = s.trim();
		if(ts.length() == 0)
			return null;
		
		return s;
	}
}
