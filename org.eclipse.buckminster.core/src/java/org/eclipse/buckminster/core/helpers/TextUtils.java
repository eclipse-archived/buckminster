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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.buckminster.runtime.Trivial;

/**
 * @author kolwing
 * 
 */
public class TextUtils
{
	public static String EMPTY_STRING = "";

	/**
	 * Builds a <code>delim</code> separated list of <code>strings</code>. It is assumed that none of the supplied
	 * strings contains the delimiter. This method will not add escape characters or the like.
	 * 
	 * @param strings
	 *            The strings to concatenate.
	 * @param delim
	 *            The delimiter to use between the concatenated strings
	 * @return The concatenated result or <code>null</code> if the <code>strings</code> argument is
	 *         <code>null</code> or of zero length.
	 */
	public static String concat(Collection<String> strings, String delim)
	{
		if(strings != null && strings.size() > 0)
		{
			StringBuilder bld = new StringBuilder();
			concat(bld, strings, delim);
			return bld.toString();
		}
		return null;
	}

	/**
	 * Builds a <code>delim</code> separated list of <code>strings</code>. It is assumed that none of the supplied
	 * strings contains the delimiter. This method will not add escape characters or the like.
	 * 
	 * @param strings
	 *            The strings to concatenate.
	 * @param delim
	 *            The delimiter to use between the concatenated strings
	 * @return The concatenated result or <code>null</code> if the <code>strings</code> argument is
	 *         <code>null</code> or of zero length.
	 */
	public static String concat(String[] strings, String delim)
	{
		if(strings != null && strings.length > 0)
		{
			StringBuilder bld = new StringBuilder();
			concat(bld, strings, delim);
			return bld.toString();
		}
		return null;
	}

	/**
	 * Appends a <code>delim</code> separated list of <code>strings</code> onto <code>builder</code>. It is
	 * assumed that none of the supplied strings contains the <code>delim</code>. This method will not add escape
	 * characters or the like. No leading or trailing <code>delim</code> will be added and nothing at all will be
	 * added if the array <code>strings</code> is <code>null</code> or of zero length.
	 * 
	 * @param builder
	 *            The receiver.
	 * @param strings
	 *            The strings to concatenate.
	 * @param delim
	 *            The delimiter to use between the concatenated elements
	 */
	public static void concat(StringBuilder builder, Collection<String> strings, String delim)
	{
		if(strings == null)
			return;

		boolean needDelim = false;
		for(String str : strings)
		{
			if(needDelim)
				builder.append(delim);
			else
				needDelim = true;
			builder.append(str);
		}
	}

	/**
	 * Appends a <code>delim</code> separated list of <code>strings</code> onto <code>builder</code>. It is
	 * assumed that none of the supplied strings contains the <code>delim</code>. This method will not add escape
	 * characters or the like. No leading or trailing <code>delim</code> will be added and nothing at all will be
	 * added if the array <code>strings</code> is <code>null</code> or of zero length.
	 * 
	 * @param builder
	 *            The receiver.
	 * @param strings
	 *            The strings to concatenate.
	 * @param delim
	 *            The delimiter to use
	 */
	public static void concat(StringBuilder builder, String[] strings, String delim)
	{
		if(strings == null)
			return;
		int top = strings.length;
		if(top > 0)
		{
			builder.append(strings[0]);
			for(int idx = 1; idx < top; ++idx)
			{
				builder.append(delim);
				builder.append(strings[idx]);
			}
		}
	}

	/**
	 * Writes a <code>delim</code> separated list of <code>strings</code> onto <code>writer</code>. It is assumed
	 * that none of the supplied strings contains the <code>delim</code>. This method will not add escape characters
	 * or the like. No leading or trailing <code>delim</code> will be written and nothing at all will be written if
	 * the array <code>strings</code> is <code>null</code> or of zero length.
	 * 
	 * @param writer
	 *            The receiver.
	 * @param strings
	 *            The strings to concatenate.
	 * @param delim
	 *            The delimiter to use between the concatenated elements
	 */
	public static void concat(Writer writer, String[] strings, String delim) throws IOException
	{
		if(strings == null)
			return;
		int top = strings.length;
		if(top > 0)
		{
			writer.append(strings[0]);
			for(int idx = 1; idx < top; ++idx)
			{
				writer.append(delim);
				writer.append(strings[idx]);
			}
		}
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

	/**
	 * Returns the index of <code>element</code> in the array <code>array</code> or
	 * <code>-1</code> if <code>element</code> is not equal to any of the
	 * <code>array</code> elements. The method will return <code>-1</code> if any of the arguments
	 * is <code>null</code>.
	 * @param array The array to search in
	 * @param element The element to find
	 * @return The index of the found element or <code>-1</code>
	 */
	public static int indexOf(String[] array, String element)
	{
		if(array == null || element == null)
			return -1;

		int idx = array.length;
		while(--idx >= 0)
			if(element.equals(array[idx]))
				break;
		return idx;
	}

	/**
	 * Helper method to turn a hex string into a byte array
	 * 
	 */
	public static byte[] makeByteArrayFromHexString(String s)
	{
		byte[] bytes = new byte[s.length() / 2];

		for(int i = 0; i < bytes.length; i++)
			bytes[i] = (byte)Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16);

		return bytes;
	}

	/**
	 * Create a standard bean getter method name that corresponds to <code>propName</code>
	 * 
	 * @param propName
	 *            The name of a bean property
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
		for(int i = 0; i < bytes.length; i++)
		{
			int v = (bytes[i] & 0xff);
			if(v < 16)
				sb.append('0');
			sb.append(Integer.toHexString(v));
		}
		return sb.toString();
	}

	public static String notNullString(Object txt)
	{
		return txt == null
				? ""
				: txt.toString();
	}

	public static String notEmptyString(String s)
	{
		return (s == null || s.length() == 0) ? null : s;
	}

	public static String notEmptyTrimmedString(String s)
	{
		if(s == null || s.length() == 0)
			s = null;
		else
		{
			s = s.trim();
			if(s.length() == 0)
				s = null;
		}
		return s;
	}

	public static Map<String, String> queryAsParameters(String query)
	{
		if(query == null)
			return Collections.emptyMap();

		String[] pairs = decodeToQueryPairs(query);
		int top = pairs.length;
		if(top == 0)
			return Collections.emptyMap();

		Map<String, String> p = new HashMap<String, String>(top);
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

	public static String[] split(String str, String delim)
	{
		if(str == null || str.length() == 0)
			return Trivial.EMPTY_STRING_ARRAY;

		ArrayList<String> bld = new ArrayList<String>();
		StringTokenizer tokens = new StringTokenizer(str, delim);
		while(tokens.hasMoreTokens())
			bld.add(tokens.nextToken());
		return bld.toArray(new String[bld.size()]);
	}

	public static String[] splitAndTrim(String str, String delim)
	{
		ArrayList<String> bld = null;
		if(str != null)
		{
			StringTokenizer tokens = new StringTokenizer(str, delim);
			while(tokens.hasMoreTokens())
			{
				String token = tokens.nextToken().trim();
				if(token.length() > 0)
				{
					if(bld == null)
						bld = new ArrayList<String>();
					bld.add(token);
				}
			}
		}
		return bld == null ? Trivial.EMPTY_STRING_ARRAY : bld.toArray(new String[bld.size()]);
	}
}
