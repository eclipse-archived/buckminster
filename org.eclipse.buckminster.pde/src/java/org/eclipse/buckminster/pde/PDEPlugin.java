/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

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
public class PDEPlugin extends LogAwarePlugin implements IPDEConstants
{
	private static final HashSet<String> s_namesOfInterest = new HashSet<String>();

	private static PDEPlugin s_plugin;

	static
	{
		s_namesOfInterest.add(PLUGIN_FILE.toLowerCase());
		s_namesOfInterest.add(FEATURE_FILE.toLowerCase());
		s_namesOfInterest.add(SITE_FILE.toLowerCase());
		s_namesOfInterest.add(FRAGMENT_FILE.toLowerCase());
		s_namesOfInterest.add(BUILD_PROPERTIES_FILE.toLowerCase());
		s_namesOfInterest.add(MANIFEST.toLowerCase());
	}

	/**
	 * Returns the shared instance.
	 */
	public static PDEPlugin getDefault()
	{
		return s_plugin;
	}

	public static Logger getLogger()
	{
		return s_plugin.getBundleLogger();
	}

	public static String getPluginId()
	{
		return s_plugin.getBundle().getSymbolicName();
	}

	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not found.
	 */
	public static String getResourceString(String key)
	{
		ResourceBundle bundle = PDEPlugin.getDefault().getResourceBundle();
		try
		{
			return (bundle != null)
					? bundle.getString(key)
					: key;
		}
		catch(MissingResourceException e)
		{
			return key;
		}
	}

	private ResourceBundle m_resourceBundle;

	/**
	 * The constructor.
	 */
	public PDEPlugin()
	{
		super();
		s_plugin = this;
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle()
	{
		try
		{
			if(m_resourceBundle == null)
				m_resourceBundle = ResourceBundle.getBundle("org.eclipse.buckminster.pde.PdePluginResources"); //$NON-NLS-1$
		}
		catch(MissingResourceException x)
		{
			m_resourceBundle = null;
		}
		return m_resourceBundle;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	@Override
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
	}

	/**
	 * This method is called when the plug-in is stopped
	 */
	@Override
	public void stop(BundleContext context) throws Exception
	{
		super.stop(context);
		s_plugin = null;
		m_resourceBundle = null;
	}
}
