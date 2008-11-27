/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.p4;

import java.util.Hashtable;

import org.eclipse.buckminster.p4.scheme.p4.Handler;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.LogAwarePlugin;
import org.eclipse.buckminster.runtime.Logger;
import org.osgi.framework.BundleContext;
import org.osgi.service.url.URLConstants;
import org.osgi.service.url.URLStreamHandlerService;

public class P4Plugin extends LogAwarePlugin
{
	public static final String PROP_DEFAULT_SERVER_MAP_PREFIX = "buckminster.p4.server."; //$NON-NLS-1$
	public static final String DEFAULT_SERVER = "default"; //$NON-NLS-1$

	public static final String PLUGIN_ID = Buckminster.NAMESPACE + ".p4"; //$NON-NLS-1$

	private static P4Plugin s_plugin;

	/**
	 * The constructor.
	 */
	public P4Plugin()
	{
		super();
		s_plugin = this;
	}

	public static Logger getLogger()
	{
		return s_plugin.getBundleLogger();
	}

	/**
	 * This method is called upon plug-in activation
	 */
	@Override
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
		Hashtable<String, String[]> properties = new Hashtable<String, String[]>(1);
		properties.put(URLConstants.URL_HANDLER_PROTOCOL, new String[] { Handler.PROTOCOL });
		String serviceClass = URLStreamHandlerService.class.getName();
		context.registerService(serviceClass, new Handler(), properties);
	}


	/**
	 * This method is called when the plug-in is stopped
	 */
	@Override
	public void stop(BundleContext context) throws Exception
	{
		super.stop(context);
		s_plugin = null;
	}

	/**
	 * Returns the name of the p4 client binary
	 */
	public String getP4Binary()
	{
		return "p4"; //$NON-NLS-1$
	}

	/**
	 * Returns the shared instance.
	 */
	public static P4Plugin getDefault()
	{
		return s_plugin;
	}
}

