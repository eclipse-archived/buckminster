/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.core.helpers;

import java.util.Properties;

/**
 * @author Filip Hrbek
 * 
 *         The
 *         <CODE>StringUtil<CODE/> class contains static methods for manipulating Strings
 */
public class StringUtil {

	/**
	 * Converts a given String into a new String which is safe fror HTML
	 * 
	 * @param string
	 *            the original String
	 * @return the new String
	 */
	public static String escapeHTML(String string) {
		if (string == null)
			return null;

		StringBuilder sb = new StringBuilder(string.length());
		// true if last char was blank
		boolean lastWasBlankChar = false;
		int len = string.length();
		char c;

		for (int i = 0; i < len; i++) {
			c = string.charAt(i);
			if (c == ' ') {
				// blank gets extra work,
				// this solves the problem you get if you replace all
				// blanks with &nbsp;, if you do that you loss
				// word breaking
				if (lastWasBlankChar) {
					lastWasBlankChar = false;
					sb.append("&nbsp;"); //$NON-NLS-1$
				} else {
					lastWasBlankChar = true;
					sb.append(' ');
				}
			} else {
				lastWasBlankChar = false;
				//
				// HTML Special Chars
				if (c == '"')
					sb.append("&quot;"); //$NON-NLS-1$
				else if (c == '&')
					sb.append("&amp;"); //$NON-NLS-1$
				else if (c == '<')
					sb.append("&lt;"); //$NON-NLS-1$
				else if (c == '>')
					sb.append("&gt;"); //$NON-NLS-1$
				else if (c == '\n')
					sb.append("<BR>"); //$NON-NLS-1$
				else if (c == '\r') {
					// ignore this character
				} else {
					int ci = 0xffff & c;
					if (ci < 160)
						// nothing special only 7 Bit
						sb.append(c);
					else {
						// Not 7 Bit use the unicode system
						sb.append("&#"); //$NON-NLS-1$
						sb.append(new Integer(ci).toString());
						sb.append(';');
					}
				}
			}
		}

		return sb.toString();
	}

	/**
	 * Duplicates quotation marks
	 * 
	 * @param string
	 *            the original String to be escaped
	 * @return the new String
	 */
	public static String escapeSQL(String string) {
		return escapeSQL(string, false);
	}

	/**
	 * Duplicates quotation marks and backslashes (according to the second
	 * parameter)
	 * 
	 * @param string
	 *            the original String to be escaped
	 * @param escapeBackslashes
	 *            true = duplicate backslashes
	 * @return the new String
	 */
	public static String escapeSQL(String string, boolean escapeBackslashes) {
		if (string == null)
			return null;

		if (escapeBackslashes) {
			return string.replaceAll("'", "''").replaceAll("\\\\", "\\\\\\\\"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		}

		return string.replaceAll("'", "''"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Fills a new String with a given character
	 * 
	 * @param c
	 *            character which fills the String
	 * @param count
	 *            number of characters in the String
	 * @return created String
	 */
	public static String filler(char c, int count) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < count; i++) {
			sb.append(c);
		}

		return sb.toString();
	}

	/**
	 * Fills a new String with a given String
	 * 
	 * @param str
	 *            String which fills the new String
	 * @param count
	 *            number of parameter Strings in the new String
	 * @return created String
	 */
	public static String filler(String str, int count) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < count; i++) {
			sb.append(str);
		}

		return sb.toString();
	}

	/**
	 * Puts the given String into quotation marks, original quotation marks are
	 * escaped
	 * 
	 * @param string
	 *            the original String
	 * @return the new quoted String
	 */
	public static String quoteSQL(String string) {
		if (string == null)
			return "NULL"; //$NON-NLS-1$

		return "'" + escapeSQL(string, false) + "'"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Puts the given String into quotation marks, original quotation marks are
	 * escaped, according to the second parameter backslashes can be escaped too
	 * 
	 * @param string
	 *            the original String
	 * @param escapeBackslashes
	 *            true = duplicate backslashes
	 * @return the new quoted String
	 */
	public static String quoteSQL(String string, boolean escapeBackslashes) {
		if (string == null)
			return "NULL"; //$NON-NLS-1$

		return "'" + escapeSQL(string, escapeBackslashes) + "'"; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Right trim of a given String
	 * 
	 * @param str
	 *            String to be trimmed
	 * @return trimmed String
	 */
	public static String rtrim(String str) {
		int len = str.length();
		char[] val = str.toCharArray();
		int count = len;

		while (len > 0 && (val[len - 1] <= ' ')) {
			len--;
		}
		return (len < count) ? str.substring(0, len) : str;
	}

	/**
	 * Puts {@link Properties} into {@link String}
	 * 
	 * @param properties
	 *            the input properies
	 * @return the properties formated in a string
	 */
	public static String stringifyProperties(Properties properties) {
		SmartArrayList<String> propList = new SmartArrayList<String>();

		for (Object key : properties.keySet()) {
			propList.add(String.format("%s=\"%s\"", key, properties.getProperty((String) key).replaceAll("[\"\\\\]", //$NON-NLS-1$ //$NON-NLS-2$
					"\\\\1"))); //$NON-NLS-1$
		}

		return propList.toString();
	}

	/**
	 * Removes backslashes from a String
	 * 
	 * @param str
	 *            original String
	 * @return String without backlashes
	 */
	public static String stripBackslashes(String str) {
		return str.replaceAll("\\\\(.)", "$1"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Private constructor forbids instantiation of the class
	 */
	private StringUtil() {
	}
}
