/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.p4.ui;

import org.eclipse.ui.plugin.*;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.BundleContext;
import java.util.*;

/**
 * The main plugin class to be used in the desktop.
 */
public class P4UiPlugin extends AbstractUIPlugin
{
	private static P4UiPlugin s_plugin;

	private ResourceBundle m_resourceBundle;

	/**
	 * The constructor.
	 */
	public P4UiPlugin()
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
	public static P4UiPlugin getDefault()
	{
		return s_plugin;
	}

	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not
	 * found.
	 */
	public static String getResourceString(String key)
	{
		ResourceBundle bundle = P4UiPlugin.getDefault().getResourceBundle();
		try
		{
			return (bundle != null) ? bundle.getString(key) : key;
		}
		catch (MissingResourceException e)
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
			if (m_resourceBundle == null)
				m_resourceBundle = ResourceBundle.getBundle("org.eclipse.buckminster.p4.ui.P4UiPluginResources"); //$NON-NLS-1$
		}
		catch (MissingResourceException x)
		{
			m_resourceBundle = null;
		}
		return m_resourceBundle;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path.
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path)
	{
		return AbstractUIPlugin.imageDescriptorFromPlugin("org.eclipse.buckminster.p4.ui", path); //$NON-NLS-1$
	}
}

