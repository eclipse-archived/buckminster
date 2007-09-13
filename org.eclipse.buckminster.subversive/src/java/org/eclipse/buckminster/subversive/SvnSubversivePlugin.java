package org.eclipse.buckminster.subversive;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class SvnSubversivePlugin extends Plugin {
	private static SvnSubversivePlugin s_plugin;

	private ResourceBundle m_resourceBundle;

	/**
	 * The constructor.
	 */
	public SvnSubversivePlugin() {
		super();
		s_plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		super.stop(context);
		s_plugin = null;
		m_resourceBundle = null;
	}

	/**
	 * Returns the shared instance.
	 */
	public static SvnSubversivePlugin getDefault() {
		return s_plugin;
	}

	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not
	 * found.
	 */
	public static String getResourceString(String key) {
		ResourceBundle bundle = SvnSubversivePlugin.getDefault().getResourceBundle();
		try {
			return (bundle != null) ? bundle.getString(key) : key;
		} catch (MissingResourceException e) {
			return key;
		}
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle() {
		try {
			if (m_resourceBundle == null)
				m_resourceBundle = ResourceBundle.getBundle("org.eclipse.buckminster.svn.SvnPluginResources");
		} catch (MissingResourceException x) {
			m_resourceBundle = null;
		}
		return m_resourceBundle;
	}
}
