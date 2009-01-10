/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.osgi.filter.impl;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.osgi.filter.impl.messages"; //$NON-NLS-1$

	public static String FILTER_TRAILING_CHARACTERS;

	public static String FILTER_MISSING_LEFTPAREN;

	public static String FILTER_MISSING_RIGHTPAREN;

	public static String FILTER_INVALID_OPERATOR;

	public static String FILTER_MISSING_ATTR;

	public static String FILTER_INVALID_VALUE;

	public static String FILTER_MISSING_VALUE;

	public static String FILTER_PREMATURE_END;

	static
	{
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages()
	{
	}
}
