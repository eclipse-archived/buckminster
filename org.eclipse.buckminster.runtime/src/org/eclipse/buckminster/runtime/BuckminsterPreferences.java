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

public abstract class BuckminsterPreferences implements IBuckminsterPreferenceConstants
{
	private static final IEclipsePreferences s_prefsNode;
	private static final IEclipsePreferences s_defaultNode;

	static
	{
		s_prefsNode = new InstanceScope().getNode(Buckminster.PLUGIN_ID);
		s_defaultNode = new DefaultScope().getNode(Buckminster.PLUGIN_ID);
		s_defaultNode.putInt(LOG_LEVEL_CONSOLE, LOG_LEVEL_CONSOLE_DEFAULT);
		s_defaultNode.putInt(LOG_LEVEL_ANT_LOGGER, LOG_LEVEL_ANT_LOGGER_DEFAULT);
		s_defaultNode.putInt(LOG_LEVEL_ECLIPSE_LOGGER, LOG_LEVEL_ECLIPSE_LOGGER_DEFAULT);
		s_defaultNode.putBoolean(LOG_ECLIPSE_TO_CONSOLE, LOG_ECLIPSE_TO_CONSOLE_DEFAULT);
		s_defaultNode.put(SITE_NAME, SITE_NAME_DEFAULT);
		s_defaultNode.put(QUERY_RESOLVER_SORT_ORDER, QUERY_RESOLVER_SORT_ORDER_DEFAULT);
		try
		{
			s_defaultNode.flush();
		}
		catch(BackingStoreException e)
		{
			Buckminster.getLogger().error(e.toString(), e);
		}
	}

	public static IEclipsePreferences getNode()
	{
		return s_prefsNode;
	}

	public static IEclipsePreferences getDefaultNode()
	{
		return s_defaultNode;
	}

	public static void addListener(IPreferenceChangeListener listener)
	{
		s_prefsNode.addPreferenceChangeListener(listener);
	}

	public static void removeListener(IPreferenceChangeListener listener)
	{
		s_prefsNode.removePreferenceChangeListener(listener);
	}

	public static IPath getBuckminterProjectContents()
	{
		String tmp = s_prefsNode.get(BUCKMINSTER_PROJECT_CONTENTS, null);
		return (tmp == null) ? null : new Path(tmp);
	}

	public static int getLogLevelConsole()
	{
		return s_prefsNode.getInt(LOG_LEVEL_CONSOLE, LOG_LEVEL_CONSOLE_DEFAULT);
	}

	public static int getLogLevelAntLogger()
	{
		return s_prefsNode.getInt(LOG_LEVEL_ANT_LOGGER, LOG_LEVEL_ANT_LOGGER_DEFAULT);
	}

	public static int getLogLevelEclipseLogger()
	{
		return s_prefsNode.getInt(LOG_LEVEL_ECLIPSE_LOGGER, LOG_LEVEL_ECLIPSE_LOGGER_DEFAULT);
	}

	public static boolean isEclipseLoggerToConsole()
	{
		return s_prefsNode.getBoolean(LOG_ECLIPSE_TO_CONSOLE, LOG_ECLIPSE_TO_CONSOLE_DEFAULT);
	}

	public static String[] getQueryResolverSortOrder()
	{
		String qrso = s_prefsNode.get(QUERY_RESOLVER_SORT_ORDER, QUERY_RESOLVER_SORT_ORDER_DEFAULT);
		return qrso.split(",");
	}

	public static String getSiteName()
	{
		return s_prefsNode.get(SITE_NAME, SITE_NAME_DEFAULT);
	}

	public static void save() throws BackingStoreException
	{
		s_prefsNode.flush();
	}

	public static void setBuckminsterProjectContents(IPath path)
	{
		if(path == null)
			s_prefsNode.remove(BUCKMINSTER_PROJECT_CONTENTS);
		else
			s_prefsNode.put(BUCKMINSTER_PROJECT_CONTENTS, path.toPortableString());
	}

	public static void setLogLevelConsole(int logLevel)
	{
		s_prefsNode.putInt(LOG_LEVEL_CONSOLE, logLevel);
	}

	public static void setLogLevelAntLogger(int logLevel)
	{
		s_prefsNode.putInt(LOG_LEVEL_ANT_LOGGER, logLevel);
	}

	public static void setLogLevelEclipseLogger(int logLevel)
	{
		s_prefsNode.putInt(LOG_LEVEL_ECLIPSE_LOGGER, logLevel);
	}

	public static void setEclipseLoggerToConsole(boolean flag)
	{
		s_prefsNode.putBoolean(LOG_ECLIPSE_TO_CONSOLE, flag);
	}

	public static String createQueryResolverSortOrder(String[] sortOrder)
	{
		if(sortOrder == null || sortOrder.length == 0)
			return null;

		StringBuffer bld = new StringBuffer();
		for(int idx = 0; idx < sortOrder.length; ++idx)
		{
			String resolverName = sortOrder[idx];
			if(resolverName == null)
				continue;

			resolverName = resolverName.trim();
			if(resolverName.length() == 0)
				continue;

			if(bld.length() > 0)
				bld.append(',');
			bld.append(resolverName);
		}
		return (bld.length() > 0) ? bld.toString() : null;
	}

	public static void setQueryResolverSortOrder(String[] sortOrder)
	{
		String sortOrderString = createQueryResolverSortOrder(sortOrder);
		if(sortOrderString == null)
			s_prefsNode.remove(QUERY_RESOLVER_SORT_ORDER);
		else
			s_prefsNode.put(QUERY_RESOLVER_SORT_ORDER, sortOrderString);
	}

	public static void setSiteName(String siteName)
	{
		if(siteName == null)
			s_prefsNode.remove(SITE_NAME);
		else
			s_prefsNode.put(SITE_NAME, siteName);
	}
}
