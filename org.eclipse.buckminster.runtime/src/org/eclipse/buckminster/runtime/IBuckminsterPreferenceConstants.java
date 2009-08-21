/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.runtime;

public interface IBuckminsterPreferenceConstants
{
	public static final String LOG_LEVEL_CONSOLE = "logLevelConsole"; //$NON-NLS-1$

	public static final String LOG_LEVEL_ECLIPSE_LOGGER = "logLevelEclipseLogger"; //$NON-NLS-1$

	public static final String LOG_LEVEL_ANT_LOGGER = "logLevelAntLogger"; //$NON-NLS-1$

	public static final int LOG_LEVEL_CONSOLE_DEFAULT = Logger.INFO;

	public static final int LOG_LEVEL_ANT_LOGGER_DEFAULT = Logger.WARNING;

	public static final int LOG_LEVEL_ECLIPSE_LOGGER_DEFAULT = Logger.WARNING;

	public static final String LOG_ECLIPSE_TO_CONSOLE = "logEclipseToConsole"; //$NON-NLS-1$

	public static final boolean LOG_ECLIPSE_TO_CONSOLE_DEFAULT = false;

	public static final String SITE_NAME = "siteName"; //$NON-NLS-1$

	public static final String SITE_NAME_DEFAULT = "default"; //$NON-NLS-1$

	public static final String BUCKMINSTER_PROJECT_CONTENTS = "bmProjectContents"; //$NON-NLS-1$

	public static final String BUCKMINSTER_PROJECT_CONTENTS_DEFAULT = ".buckminster"; //$NON-NLS-1$

	public static final String QUERY_RESOLVER_SORT_ORDER = "queryResolverSortOrder"; //$NON-NLS-1$

	public static final String QUERY_RESOLVER_SORT_ORDER_DEFAULT = "rmap"; //$NON-NLS-1$

	public static final String CUSTOM_QUERY_RESOLVER_SORT_ORDER = "customQueryResolverSort"; //$NON-NLS-1$

	public static final String CONNECTION_RETRY_COUNT = "connectionRetryCount"; //$NON-NLS-1$

	public static final int CONNECTION_RETRY_COUNT_DEFAULT = 3;

	public static final String CONNECTION_RETRY_DELAY = "connectionRetryDelay"; //$NON-NLS-1$

	public static final int CONNECTION_RETRY_DELAY_DEFAULT = 1;

	public static final String OPML_SUPPORT = "opmlSupport"; //$NON-NLS-1$

	public static final boolean OPML_SUPPORT_DEFAULT = false;

}
