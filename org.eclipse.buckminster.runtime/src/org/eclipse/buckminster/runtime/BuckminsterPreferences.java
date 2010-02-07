/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.runtime;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.osgi.service.prefs.BackingStoreException;

public abstract class BuckminsterPreferences implements IBuckminsterPreferenceConstants {
	private static final IEclipsePreferences prefsNode;

	private static final IEclipsePreferences defaultNode;

	static {
		prefsNode = new InstanceScope().getNode(Buckminster.PLUGIN_ID);
		defaultNode = new DefaultScope().getNode(Buckminster.PLUGIN_ID);
		defaultNode.putInt(LOG_LEVEL_CONSOLE, LOG_LEVEL_CONSOLE_DEFAULT);
		defaultNode.putInt(LOG_LEVEL_ANT_LOGGER, LOG_LEVEL_ANT_LOGGER_DEFAULT);
		defaultNode.putInt(LOG_LEVEL_ECLIPSE_LOGGER, LOG_LEVEL_ECLIPSE_LOGGER_DEFAULT);
		defaultNode.putBoolean(LOG_ECLIPSE_TO_CONSOLE, LOG_ECLIPSE_TO_CONSOLE_DEFAULT);
		defaultNode.put(SITE_NAME, SITE_NAME_DEFAULT);
		defaultNode.put(QUERY_RESOLVER_SORT_ORDER, QUERY_RESOLVER_SORT_ORDER_DEFAULT);
		defaultNode.putInt(CONNECTION_RETRY_COUNT, CONNECTION_RETRY_COUNT_DEFAULT);
		defaultNode.putInt(CONNECTION_RETRY_DELAY, CONNECTION_RETRY_DELAY_DEFAULT);
		defaultNode.putBoolean(PREF_CONSOLE_SHOW_ON_MESSAGE, PREF_CONSOLE_SHOW_ON_MESSAGE_DEFAULT);
		defaultNode.putBoolean(PREF_CONSOLE_SHOW_ON_ERROR, PREF_CONSOLE_SHOW_ON_ERROR_DEFAULT);
		defaultNode.putBoolean(PREF_CONSOLE_LIMIT_OUTPUT, PREF_CONSOLE_LIMIT_OUTPUT_DEFAULT);
		defaultNode.putInt(PREF_CONSOLE_HIGH_WATER_MARK, PREF_CONSOLE_HIGH_WATER_MARK_DEFAULT);
		defaultNode.put(PREF_CONSOLE_MESSAGE_COLOR, PREF_CONSOLE_MESSAGE_COLOR_DEFAULT);
		defaultNode.put(PREF_CONSOLE_ERROR_COLOR, PREF_CONSOLE_ERROR_COLOR_DEFAULT);

		try {
			defaultNode.flush();
		} catch (BackingStoreException e) {
			Buckminster.getLogger().error(e, e.toString());
		}
	}

	public static void addListener(IPreferenceChangeListener listener) {
		prefsNode.addPreferenceChangeListener(listener);
	}

	public static String createQueryResolverSortOrder(String[] sortOrder) {
		if (sortOrder == null || sortOrder.length == 0)
			return null;

		StringBuffer bld = new StringBuffer();
		for (int idx = 0; idx < sortOrder.length; ++idx) {
			String resolverName = sortOrder[idx];
			if (resolverName == null)
				continue;

			resolverName = resolverName.trim();
			if (resolverName.length() == 0)
				continue;

			if (bld.length() > 0)
				bld.append(',');
			bld.append(resolverName);
		}
		return (bld.length() > 0) ? bld.toString() : null;
	}

	public static IPath getBuckminterProjectContents() {
		String tmp = prefsNode.get(BUCKMINSTER_PROJECT_CONTENTS, null);
		return (tmp == null) ? null : new Path(tmp);
	}

	public static int getConnectionRetryCount() {
		return prefsNode.getInt(CONNECTION_RETRY_COUNT, CONNECTION_RETRY_COUNT_DEFAULT);
	}

	public static int getConnectionRetryDelay() {
		return prefsNode.getInt(CONNECTION_RETRY_DELAY, CONNECTION_RETRY_DELAY_DEFAULT);
	}

	public static String getConsoleErrorColor() {
		return prefsNode.get(PREF_CONSOLE_ERROR_COLOR, PREF_CONSOLE_ERROR_COLOR_DEFAULT);
	}

	public static int getConsoleHighWaterMark() {
		return prefsNode.getInt(PREF_CONSOLE_HIGH_WATER_MARK, PREF_CONSOLE_HIGH_WATER_MARK_DEFAULT);
	}

	public static String getConsoleMessageColor() {
		return prefsNode.get(PREF_CONSOLE_MESSAGE_COLOR, PREF_CONSOLE_MESSAGE_COLOR_DEFAULT);
	}

	public static IEclipsePreferences getDefaultNode() {
		return defaultNode;
	}

	public static int getLogLevelAntLogger() {
		return prefsNode.getInt(LOG_LEVEL_ANT_LOGGER, LOG_LEVEL_ANT_LOGGER_DEFAULT);
	}

	public static int getLogLevelConsole() {
		return prefsNode.getInt(LOG_LEVEL_CONSOLE, LOG_LEVEL_CONSOLE_DEFAULT);
	}

	public static int getLogLevelEclipseLogger() {
		return prefsNode.getInt(LOG_LEVEL_ECLIPSE_LOGGER, LOG_LEVEL_ECLIPSE_LOGGER_DEFAULT);
	}

	public static IEclipsePreferences getNode() {
		return prefsNode;
	}

	public static String[] getQueryResolverSortOrder() {
		String qrso = prefsNode.get(QUERY_RESOLVER_SORT_ORDER, QUERY_RESOLVER_SORT_ORDER_DEFAULT);
		return qrso.split(","); //$NON-NLS-1$
	}

	public static String getSiteName() {
		return prefsNode.get(SITE_NAME, SITE_NAME_DEFAULT);
	}

	public static boolean isConsoleLimitOutput() {
		return prefsNode.getBoolean(PREF_CONSOLE_LIMIT_OUTPUT, PREF_CONSOLE_LIMIT_OUTPUT_DEFAULT);
	}

	public static boolean isConsoleShowOnError() {
		return prefsNode.getBoolean(PREF_CONSOLE_SHOW_ON_ERROR, PREF_CONSOLE_SHOW_ON_ERROR_DEFAULT);
	}

	public static boolean isConsoleShowOnMessage() {
		return prefsNode.getBoolean(PREF_CONSOLE_SHOW_ON_MESSAGE, PREF_CONSOLE_SHOW_ON_MESSAGE_DEFAULT);
	}

	public static boolean isCustomQuerySortOrder() {
		return prefsNode.getBoolean(CUSTOM_QUERY_RESOLVER_SORT_ORDER, false);
	}

	public static boolean isEclipseLoggerToConsole() {
		return prefsNode.getBoolean(LOG_ECLIPSE_TO_CONSOLE, LOG_ECLIPSE_TO_CONSOLE_DEFAULT);
	}

	public static void removeListener(IPreferenceChangeListener listener) {
		prefsNode.removePreferenceChangeListener(listener);
	}

	public static void save() throws BackingStoreException {
		prefsNode.flush();
	}

	public static void setBuckminsterProjectContents(IPath path) {
		if (path == null)
			prefsNode.remove(BUCKMINSTER_PROJECT_CONTENTS);
		else
			prefsNode.put(BUCKMINSTER_PROJECT_CONTENTS, path.toPortableString());
	}

	public static void setConnectionRetryCount(int retryCount) {
		prefsNode.putInt(CONNECTION_RETRY_COUNT, retryCount);
	}

	public static void setConnectionRetryDelay(int retryDelay) {
		prefsNode.putInt(CONNECTION_RETRY_DELAY, retryDelay);
	}

	public static void setConsoleErrorColor(String color) {
		if (color == null)
			prefsNode.remove(PREF_CONSOLE_ERROR_COLOR);
		else
			prefsNode.put(PREF_CONSOLE_ERROR_COLOR, color);
	}

	public static void setConsoleHighWaterMark(int waterMark) {
		prefsNode.putInt(PREF_CONSOLE_HIGH_WATER_MARK, waterMark);
	}

	public static void setConsoleLimitOutput(boolean flag) {
		prefsNode.putBoolean(PREF_CONSOLE_LIMIT_OUTPUT, flag);
	}

	public static void setConsoleMessageColor(String color) {
		if (color == null)
			prefsNode.remove(PREF_CONSOLE_MESSAGE_COLOR);
		else
			prefsNode.put(PREF_CONSOLE_MESSAGE_COLOR, color);
	}

	public static void setConsoleShowOnError(boolean flag) {
		prefsNode.putBoolean(PREF_CONSOLE_SHOW_ON_ERROR, flag);
	}

	public static void setConsoleShowOnMessage(boolean flag) {
		prefsNode.putBoolean(PREF_CONSOLE_SHOW_ON_MESSAGE, flag);
	}

	public static void setCustomQueryResolverSortOrder(boolean flag) {
		prefsNode.putBoolean(CUSTOM_QUERY_RESOLVER_SORT_ORDER, flag);
	}

	public static void setEclipseLoggerToConsole(boolean flag) {
		prefsNode.putBoolean(LOG_ECLIPSE_TO_CONSOLE, flag);
	}

	public static void setLogLevelAntLogger(int logLevel) {
		prefsNode.putInt(LOG_LEVEL_ANT_LOGGER, logLevel);
	}

	public static void setLogLevelConsole(int logLevel) {
		prefsNode.putInt(LOG_LEVEL_CONSOLE, logLevel);
	}

	public static void setLogLevelEclipseLogger(int logLevel) {
		prefsNode.putInt(LOG_LEVEL_ECLIPSE_LOGGER, logLevel);
	}

	public static void setQueryResolverSortOrder(String[] sortOrder) {
		String sortOrderString = createQueryResolverSortOrder(sortOrder);
		if (sortOrderString == null)
			prefsNode.remove(QUERY_RESOLVER_SORT_ORDER);
		else
			prefsNode.put(QUERY_RESOLVER_SORT_ORDER, sortOrderString);
	}

	public static void setSiteName(String siteName) {
		if (siteName == null)
			prefsNode.remove(SITE_NAME);
		else
			prefsNode.put(SITE_NAME, siteName);
	}
}
