/*******************************************************************************
 * Copyright (c) 2009 Cloudsmith Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Cloudsmith Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.buckminster.util.test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.packageadmin.PackageAdmin;

public class Activator implements BundleActivator
{
	public static final String PI_PROV_TESTS = "org.eclipse.buckminster.test";

	public static BundleContext context;

	private static PackageAdmin packageAdmin = null;

	private static ServiceReference packageAdminRef = null;

	public static String TEST_DATA_PATH = "testData"; //$NON-NLS-1$

	public static Bundle getBundle(String symbolicName)
	{
		if(packageAdmin == null)
			return null;
		Bundle[] bundles = packageAdmin.getBundles(symbolicName, null);
		if(bundles == null)
			return null;
		// Return the first bundle that is not installed or uninstalled
		for(int i = 0; i < bundles.length; i++)
		{
			if((bundles[i].getState() & (Bundle.INSTALLED | Bundle.UNINSTALLED)) == 0)
			{
				return bundles[i];
			}
		}
		return null;
	}

	public static BundleContext getContext()
	{
		return context;
	}

	public static File getTestDataFolder()
	{
		try
		{
			URL url = context.getBundle().getEntry(Activator.TEST_DATA_PATH);
			return new File(FileLocator.resolve(url).getFile());
		}
		catch(IOException e)
		{
			return null;
		}
	}

	public void start(BundleContext ctx) throws Exception
	{
		Activator.context = ctx;
		packageAdminRef = ctx.getServiceReference(PackageAdmin.class.getName());
		packageAdmin = (PackageAdmin)ctx.getService(packageAdminRef);

	}

	public void stop(BundleContext ctx) throws Exception
	{
		Activator.context = null;
	}

}
