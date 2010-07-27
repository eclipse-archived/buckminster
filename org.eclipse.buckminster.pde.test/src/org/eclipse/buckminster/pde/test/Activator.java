package org.eclipse.buckminster.pde.test;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator
{
	public static BundleContext context;

	@Override
	public void start(BundleContext ctx) throws Exception
	{
		context = ctx;
	}

	@Override
	public void stop(BundleContext ctx) throws Exception
	{
		context = null;
	}
}
