/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.runtime;

import java.util.IdentityHashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.IPreferenceChangeListener;
import org.eclipse.core.runtime.preferences.IEclipsePreferences.PreferenceChangeEvent;
import org.eclipse.osgi.util.NLS;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * The activator class controls the plug-in life cycle
 */
public class Buckminster extends LogAwarePlugin implements IPreferenceChangeListener, IBuckminsterPreferenceConstants
{
	public static final String NAMESPACE = "org.eclipse.buckminster"; //$NON-NLS-1$

	public static final String PLUGIN_ID = "org.eclipse.buckminster.runtime"; //$NON-NLS-1$

	private static Buckminster s_plugin;

	// this tracks if Buckminster was invoked headless
	// intended to be set by the buckminster application entry point
	// specifically should be an 'object' so it can only be set once
	// see isHeadless/setHeadless
	//
	private static boolean s_headless = false;

	public static Buckminster getDefault()
	{
		return s_plugin;
	}

	public static Logger getLogger()
	{
		return s_plugin.getBundleLogger();
	}

	/**
	 * Returns true if Buckminster was started through the application entry point.
	 * 
	 * @return The headless state
	 */
	public static boolean isHeadless()
	{
		return s_headless;
	}

	/**
	 * Sets the headless state.
	 */
	public static void setHeadless()
	{
		s_headless = true;
	}

	private IdentityHashMap<Object, ServiceReference> m_services;

	public Buckminster()
	{
		s_plugin = this;
	}

	public <T> T getService(Class<T> serviceClass) throws CoreException
	{
		BundleContext context = getBundle().getBundleContext();
		String serviceName = serviceClass.getName();
		ServiceReference serviceRef = context.getServiceReference(serviceName);
		if(serviceRef == null)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.Missing_OSGi_Service_0, serviceName));
		T service = serviceClass.cast(context.getService(serviceRef));
		if(m_services == null)
			m_services = new IdentityHashMap<Object, ServiceReference>();
		m_services.put(service, serviceRef);
		return service;
	}

	public void preferenceChange(PreferenceChangeEvent event)
	{
		if(LOG_LEVEL_CONSOLE.equals(event.getKey()))
		{
			Object newVal = event.getNewValue();
			Logger.setConsoleLevelThreshold(newVal == null
					? LOG_LEVEL_CONSOLE_DEFAULT
					: Integer.parseInt(newVal.toString()));
		}
		if(LOG_LEVEL_ECLIPSE_LOGGER.equals(event.getKey()))
		{
			Object newVal = event.getNewValue();
			Logger.setEclipseLoggerLevelThreshold(newVal == null
					? LOG_LEVEL_ECLIPSE_LOGGER_DEFAULT
					: Integer.parseInt(newVal.toString()));
		}
		else if(LOG_ECLIPSE_TO_CONSOLE.equals(event.getKey()))
		{
			Object newVal = event.getNewValue();
			Logger.setEclipseLoggerToConsole(newVal == null
					? LOG_ECLIPSE_TO_CONSOLE_DEFAULT
					: Boolean.valueOf(newVal.toString()).booleanValue());
		}
	}

	@Override
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
		Logger.setConsoleLevelThreshold(BuckminsterPreferences.getLogLevelConsole());
		Logger.setEclipseLoggerLevelThreshold(BuckminsterPreferences.getLogLevelEclipseLogger());
		Logger.setEclipseLoggerToConsole(BuckminsterPreferences.isEclipseLoggerToConsole());
		Logger.setDefaultLogger(this.getBundle());
		BuckminsterPreferences.addListener(this);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	@Override
	public void stop(BundleContext context) throws Exception
	{
		if(m_services != null)
		{
			for(ServiceReference serviceRef : m_services.values())
				context.ungetService(serviceRef);
			m_services = null;
		}
		s_plugin = null;
		super.stop(context);
	}

	public void ungetService(Object service)
	{
		if(m_services != null && service != null)
		{
			ServiceReference serviceRef = m_services.remove(service);
			if(serviceRef != null)
				getBundle().getBundleContext().ungetService(serviceRef);
		}
	}
}
