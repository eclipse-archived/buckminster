/*****************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.pde;

import java.util.HashSet;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.buckminster.runtime.LogAwarePlugin;
import org.eclipse.buckminster.runtime.Logger;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class PDEPlugin extends LogAwarePlugin implements IPDEConstants {
	private static final HashSet<String> namesOfInterest = new HashSet<String>();

	private static PDEPlugin plugin;

	private static BundleContext context;

	static {
		namesOfInterest.add(PLUGIN_FILE.toLowerCase());
		namesOfInterest.add(FEATURE_FILE.toLowerCase());
		namesOfInterest.add(SITE_FILE.toLowerCase());
		namesOfInterest.add(FRAGMENT_FILE.toLowerCase());
		namesOfInterest.add(BUILD_PROPERTIES_FILE.toLowerCase());
		namesOfInterest.add(MANIFEST.toLowerCase());
	}

	public static BundleContext getContext() {
		return context;
	}

	/**
	 * Returns the shared instance.
	 */
	public static PDEPlugin getDefault() {
		return plugin;
	}

	public static Logger getLogger() {
		return plugin.getBundleLogger();
	}

	public static String getPluginId() {
		return plugin.getBundle().getSymbolicName();
	}

	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not
	 * found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = PDEPlugin.getDefault().getResourceBundle();
		try {
			return (bundle != null) ? bundle.getString(key) : key;
		} catch (MissingResourceException e) {
			return key;
		}
	}

	private ResourceBundle resourceBundle;

	/**
	 * The constructor.
	 */
	public PDEPlugin() {
		super();
		plugin = this;
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		try {
			if (resourceBundle == null)
				resourceBundle = ResourceBundle.getBundle("org.eclipse.buckminster.pde.PdePluginResources"); //$NON-NLS-1$
		} catch (MissingResourceException x) {
			resourceBundle = null;
		}
		return resourceBundle;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	@Override
	public void start(BundleContext ctx) throws Exception {
		context = ctx;
		super.start(ctx);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	@Override
	public void stop(BundleContext ctx) throws Exception {
		super.stop(ctx);
		context = null;
		plugin = null;
		resourceBundle = null;
	}
}
