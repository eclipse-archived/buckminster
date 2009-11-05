/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.util;

import org.eclipse.buckminster.aggregator.p2.InstallableUnit;

/**
 * @author Karel Brezina
 * 
 */
public class GeneralUtils
{

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
