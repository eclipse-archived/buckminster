/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.ant;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.LogAwarePlugin;
import org.eclipse.buckminster.runtime.Logger;
import org.osgi.framework.BundleContext;

/**
 * @author Thomas Hallgren
 */
public class AntPlugin extends LogAwarePlugin
{
	public static final String PLUGIN_ID = Buckminster.NAMESPACE + ".ant";

	public static final String BUILD_SCRIPT_POINT = PLUGIN_ID + ".buildScripts";

	public static final String BUILDER_NAME = PLUGIN_ID + ".builder";

	public final static String ANT_ACTOR_PROPERTY_TARGETS = "targets";

	public final static String ANT_ACTOR_PROPERTY_BUILD_FILE_ID = "buildFileId";

	public final static String ANT_ACTOR_PROPERTY_BUILD_FILE = "buildFile";

	private static AntPlugin s_plugin;

	/**
	 * Returns the shared instance.
	 */
	public static AntPlugin getDefault()
	{
		return s_plugin;
	}

	public static Logger getLogger()
	{
		return s_plugin.getBundleLogger();
	}

	/**
	 * Returns the string from the plugin's resource bundle, or 'key' if not found.
	 */
	public static String getResourceString(String key)
	{
		ResourceBundle bundle = AntPlugin.getDefault().getResourceBundle();
		try
		{
			return (bundle != null) ? bundle.getString(key) : key;
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
	public AntPlugin()
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
				m_resourceBundle = ResourceBundle.getBundle("org.eclipse.buckminster.ant.AntPluginResources");
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
