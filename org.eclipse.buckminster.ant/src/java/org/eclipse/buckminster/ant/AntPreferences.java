/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ant;

import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;

/**
 * @author Thomas Hallgren
 */
public abstract class AntPreferences
{
	public static final String LOG_LEVEL = "logLevelConsole";

	public static final int LOG_LEVEL_DEFAULT = Logger.INFO;

	private static final IEclipsePreferences s_prefsNode = (IEclipsePreferences)Platform.getPreferencesService().getRootNode().node(
		InstanceScope.SCOPE).node(AntPlugin.PLUGIN_ID);

	public static int getLogLevel()
	{
		return s_prefsNode.getInt(LOG_LEVEL, LOG_LEVEL_DEFAULT);
	}

	public static void setLogLevel(int logLevel)
	{
		s_prefsNode.putInt(LOG_LEVEL, logLevel);
	}
}
