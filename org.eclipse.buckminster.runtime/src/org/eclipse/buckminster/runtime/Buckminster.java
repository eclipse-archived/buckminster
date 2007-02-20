/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.runtime;

import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Buckminster extends LogAwarePlugin implements IPreferenceChangeListener, IBuckminsterPreferenceConstants
{
	public static final String NAMESPACE = "org.eclipse.buckminster";
	public static final String PLUGIN_ID = "org.eclipse.buckminster.runtime";

	private static Buckminster s_plugin;

	// this tracks if Buckminster was invoked headless
	// intended to be set by the buckminster application entry point
	// specifically should be an 'object' so it can only be set once
	// see isHeadless/setHeadless
	//
	private static boolean s_headless = false;

	public Buckminster()
	{
		s_plugin = this;
	}

	public void start(BundleContext context) throws Exception
	{
		super.start(context);
		Logger.setConsoleLevelThreshold(BuckminsterPreferences.getLogLevelConsole());
		Logger.setEclipseLoggerLevelThreshold(BuckminsterPreferences.getLogLevelEclipseLogger());
		Logger.setEclipseLoggerToConsole(BuckminsterPreferences.isEclipseLoggerToConsole());
		Logger.setDefaultLogger(this.getBundle());
		BuckminsterPreferences.addListener(this);
	}

	public void stop(BundleContext context) throws Exception
	{
		s_plugin = null;
		super.stop(context);
	}

	public static Buckminster getDefault()
	{
		return s_plugin;
	}

	public static Logger getLogger()
	{
		return s_plugin.getBundleLogger();
	}

	public void preferenceChange(PreferenceChangeEvent event)
	{
		if(LOG_LEVEL_CONSOLE.equals(event.getKey()))
		{
			Object newVal = event.getNewValue();
			Logger.setConsoleLevelThreshold(newVal == null ? LOG_LEVEL_CONSOLE_DEFAULT : Integer.parseInt(newVal.toString()));
		}
		if(LOG_LEVEL_ECLIPSE_LOGGER.equals(event.getKey()))
		{
			Object newVal = event.getNewValue();
			Logger.setEclipseLoggerLevelThreshold(newVal == null ? LOG_LEVEL_ECLIPSE_LOGGER_DEFAULT : Integer.parseInt(newVal.toString()));
		}
		else if(LOG_ECLIPSE_TO_CONSOLE.equals(event.getKey()))
		{
			Object newVal = event.getNewValue();
			Logger.setEclipseLoggerToConsole(newVal == null ? LOG_ECLIPSE_TO_CONSOLE_DEFAULT : Boolean.valueOf(newVal.toString()).booleanValue());
		}
	}

	/**
	 * Sets the headless state. 
	 */
	public static void setHeadless()
	{
		s_headless = true;
	}

	/**
	 * Returns true if Buckminster was started through the application entry
	 * point.
	 * 
	 * @return The headless state
	 */
	public static boolean isHeadless()
	{
		return s_headless;
	}
}
