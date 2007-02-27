/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.svn;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class SvnPlugin extends Plugin
{
	private static SvnPlugin s_plugin;

	private ResourceBundle m_resourceBundle;

	/**
	 * The constructor.
	 */
	public SvnPlugin()
	{
		super();
		s_plugin = this;
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

	/**
	 * Returns the shared instance.
	 */
	public static SvnPlugin getDefault()
	{
		return s_plugin;
	}

	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not
	 * found.
	 */
	public static String getResourceString(String key)
	{
		ResourceBundle bundle = SvnPlugin.getDefault().getResourceBundle();
		try
		{
			return (bundle != null) ? bundle.getString(key) : key;
		}
		catch(MissingResourceException e)
		{
			return key;
		}
	}

	/**
	 * Returns the plugin's resource bundle,
	 */
	public ResourceBundle getResourceBundle()
	{
		try
		{
			if(m_resourceBundle == null)
				m_resourceBundle = ResourceBundle.getBundle("org.eclipse.buckminster.svn.SvnPluginResources");
		}
		catch(MissingResourceException x)
		{
			m_resourceBundle = null;
		}
		return m_resourceBundle;
	}
}

