/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.jdt;

import org.eclipse.buckminster.runtime.LogAwarePlugin;
import org.eclipse.buckminster.runtime.Logger;
import org.osgi.framework.BundleContext;

/**
 * The main plugin class to be used in the desktop.
 */
public class JdtPlugin extends LogAwarePlugin
{
	public static final String PLUGIN_ID = "org.eclipse.buckminster.jdt"; //$NON-NLS-1$

	public static final String CLASSPATH_CONTAINER_ID = PLUGIN_ID + ".requiredComponents"; //$NON-NLS-1$

	private static JdtPlugin s_plugin;

	public static JdtPlugin getDefault()
	{
		return s_plugin;
	}

	public static Logger getLogger()
	{
		return s_plugin.getBundleLogger();
	}

	public JdtPlugin()
	{
		s_plugin = this;
	}

	@Override
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
	}

	@Override
	public void stop(BundleContext context) throws Exception
	{
		super.stop(context);
		s_plugin = null;
	}
}
