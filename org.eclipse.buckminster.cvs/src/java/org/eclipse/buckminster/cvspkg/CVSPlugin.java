/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.cvspkg;

import java.util.Hashtable;

import org.eclipse.buckminster.cvspkg.scheme.cvspkg.Handler;
import org.eclipse.buckminster.runtime.LogAwarePlugin;
import org.eclipse.buckminster.runtime.Logger;
import org.osgi.framework.BundleContext;
import org.osgi.service.url.URLConstants;
import org.osgi.service.url.URLStreamHandlerService;

/**
 * The main plugin class to be used in the desktop.
 */
public class CVSPlugin extends LogAwarePlugin
{
	private static CVSPlugin s_plugin;

	public static CVSPlugin getDefault()
	{
		return s_plugin;
	}

	public static Logger getLogger()
	{
		return s_plugin.getBundleLogger();
	}

	public CVSPlugin()
	{
		super();
		s_plugin = this;
	}

	@Override
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
		Hashtable<String, String[]> properties = new Hashtable<String, String[]>(1);
		properties.put(URLConstants.URL_HANDLER_PROTOCOL, new String[] { Handler.PROTOCOL });
		String serviceClass = URLStreamHandlerService.class.getName();
		context.registerService(serviceClass, new Handler(), properties);
	}

	@Override
	public void stop(BundleContext context) throws Exception
	{
		super.stop(context);
		s_plugin = null;
	}
}
