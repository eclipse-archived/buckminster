/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.util;

import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.Version;

/**
 * @author Karel Brezina
 * 
 */
public class GeneralUtils
{

	public static String encodeFilterValue(String value)
	{
		boolean encoded = false;
		int inlen = value.length();
		int outlen = inlen << 1; /* inlen * 2 */

		char[] output = new char[outlen];
		value.getChars(0, inlen, output, inlen);

		int cursor = 0;
		for(int i = inlen; i < outlen; i++)
		{
			char c = output[i];

			switch(c)
			{
			case '(':
			case '*':
			case ')':
			case '\\':
			{
				output[cursor] = '\\';
				cursor++;
				encoded = true;

				break;
			}
			}

			output[cursor] = c;
			cursor++;
		}

		return encoded
				? new String(output, 0, cursor)
				: value;
	}

	public static String getLocalizedProperty(InstallableUnit iu, String key)
	{
		String value = iu.getProperty(key);

		if(value != null && value.startsWith("%"))
		{
			String localizedKey = "df_LT." + value.substring(1);
			String localizedValue = iu.getProperty(localizedKey);

			if(localizedValue != null)
				value = localizedValue;
		}

		return value;
	}

	public static String stringifyVersion(Version version)
	{
		String result = version.getOriginal();

		if(result == null)
			result = version.toString();

		return result;
	}

	public static String trimmedOrNull(String str)
	{
		if(str == null)
			return null;

		str = str.trim();
		if(str.length() == 0)
			return null;

		return str;
	}
}
