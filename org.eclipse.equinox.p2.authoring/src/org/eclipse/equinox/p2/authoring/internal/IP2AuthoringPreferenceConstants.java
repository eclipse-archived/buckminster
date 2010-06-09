/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.equinox.p2.authoring.internal;

public interface IP2AuthoringPreferenceConstants
{
	public static final String LOG_LEVEL_CONSOLE = "logLevelConsole";

	public static final String LOG_LEVEL_ECLIPSE_LOGGER = "logLevelEclipseLogger";

	public static final String LOG_LEVEL_ANT_LOGGER = "logLevelAntLogger";

	public static final int LOG_LEVEL_CONSOLE_DEFAULT = Logger.INFO;

	public static final int LOG_LEVEL_ANT_LOGGER_DEFAULT = Logger.WARNING;

	public static final int LOG_LEVEL_ECLIPSE_LOGGER_DEFAULT = Logger.WARNING;

	public static final String LOG_ECLIPSE_TO_CONSOLE = "logEclipseToConsole";

	public static final boolean LOG_ECLIPSE_TO_CONSOLE_DEFAULT = false;

}
