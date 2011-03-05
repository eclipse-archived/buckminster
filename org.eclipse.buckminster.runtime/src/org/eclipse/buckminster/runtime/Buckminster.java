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
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

/**
 * The activator class controls the plug-in life cycle
 */
public class Buckminster extends LogAwarePlugin implements IPreferenceChangeListener, IBuckminsterPreferenceConstants {
	public static final String NAMESPACE = "org.eclipse.buckminster"; //$NON-NLS-1$

	public static final String PLUGIN_ID = "org.eclipse.buckminster.runtime"; //$NON-NLS-1$

	public static final String ACTION_TEMP_ROOT = "buckminster.temp.root"; //$NON-NLS-1$

	public static final String ACTION_OUTPUT_ROOT = "buckminster.output.root"; //$NON-NLS-1$

	private static Buckminster plugin;

	// this tracks if Buckminster was invoked headless
	// intended to be set by the buckminster application entry point
	// specifically should be an 'object' so it can only be set once
	// see isHeadless/setHeadless
	//
	private static boolean headless = false;

	public static Buckminster getDefault() {
		return plugin;
	}

	public static Logger getLogger() {
		return plugin.getBundleLogger();
	}

	/**
	 * Returns true if Buckminster was started through the application entry
	 * point.
	 * 
	 * @return The headless state
	 */
	public static boolean isHeadless() {
		return headless;
	}

	/**
	 * Sets the headless state.
	 */
	public static void setHeadless() {
		headless = true;
	}

	private IdentityHashMap<Object, ServiceReference> services;

	public Buckminster() {
		plugin = this;
	}

	public <T> T getService(Class<T> serviceClass) throws CoreException {
		BundleContext context = getBundle().getBundleContext();
		String serviceName = serviceClass.getName();
		ServiceReference serviceRef = context.getServiceReference(serviceName);
		if (serviceRef == null)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.Missing_OSGi_Service_0, serviceName));
		T service = serviceClass.cast(context.getService(serviceRef));
		if (services == null)
			services = new IdentityHashMap<Object, ServiceReference>();
		services.put(service, serviceRef);
		return service;
	}

	public <T> T getService(Class<T> serviceClass, String filter) throws CoreException {
		BundleContext context = getBundle().getBundleContext();
		String serviceName = serviceClass.getName();
		ServiceReference[] serviceRef;
		try {
			serviceRef = context.getServiceReferences(serviceName, filter);
		} catch (InvalidSyntaxException e) {
			throw BuckminsterException.wrap(e);
		}
		if (serviceRef == null || serviceRef.length == 0)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.Missing_OSGi_Service_0, serviceName));
		T service = serviceClass.cast(context.getService(serviceRef[0]));
		if (services == null)
			services = new IdentityHashMap<Object, ServiceReference>();
		services.put(service, serviceRef[0]);
		return service;
	}

	@Override
	public void preferenceChange(PreferenceChangeEvent event) {
		if (LOG_LEVEL_CONSOLE.equals(event.getKey())) {
			Object newVal = event.getNewValue();
			Logger.setConsoleLevelThreshold(newVal == null ? LOG_LEVEL_CONSOLE_DEFAULT : Integer.parseInt(newVal.toString()));
		}
		if (LOG_LEVEL_ECLIPSE_LOGGER.equals(event.getKey())) {
			Object newVal = event.getNewValue();
			Logger.setEclipseLoggerLevelThreshold(newVal == null ? LOG_LEVEL_ECLIPSE_LOGGER_DEFAULT : Integer.parseInt(newVal.toString()));
		} else if (LOG_ECLIPSE_TO_CONSOLE.equals(event.getKey())) {
			Object newVal = event.getNewValue();
			Logger.setEclipseLoggerToConsole(newVal == null ? LOG_ECLIPSE_TO_CONSOLE_DEFAULT : Boolean.valueOf(newVal.toString()).booleanValue());
		}
	}

	@Override
	public void start(BundleContext context) throws Exception {
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
	public void stop(BundleContext context) throws Exception {
		if (services != null) {
			for (ServiceReference serviceRef : services.values())
				context.ungetService(serviceRef);
			services = null;
		}
		plugin = null;
		super.stop(context);
	}

	public void ungetService(Object service) {
		if (services != null && service != null) {
			ServiceReference serviceRef = services.remove(service);
			if (serviceRef != null)
				getBundle().getBundleContext().ungetService(serviceRef);
		}
	}
}
