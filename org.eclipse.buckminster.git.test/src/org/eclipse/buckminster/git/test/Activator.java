package org.eclipse.buckminster.git.test;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.buckminster.git.test";

	// The shared instance
	private static Activator plugin;

	private static BundleContext bundleContext;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		bundleContext = context;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		bundleContext = null;
		super.stop(context);
	}

	public static BundleContext getContext() {
		return bundleContext;
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

}
