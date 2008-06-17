/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.p2.remote.client;

import org.jabsorb.client.HTTPSession;
import org.jabsorb.client.TransportRegistry;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator
{
	public static final String ID = "org.eclipse.buckminster.p2.remote.client"; //$NON-NLS-1$

	private static BundleContext bundleContext;

	private static TransportRegistry registry;

	public static BundleContext getContext()
	{
		return bundleContext;
	}

	public static TransportRegistry getRegistry()
	{
		return registry;
	}

	public void start(BundleContext context) throws Exception
	{
		bundleContext = context;
		registry = new TransportRegistry();
		HTTPSession.register(registry);
	}

	public void stop(BundleContext context) throws Exception
	{
		bundleContext = null;
	}
}
