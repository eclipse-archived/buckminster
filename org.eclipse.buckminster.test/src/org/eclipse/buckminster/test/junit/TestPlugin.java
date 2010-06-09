/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.test.junit;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
import org.osgi.service.packageadmin.PackageAdmin;
import org.osgi.util.tracker.ServiceTracker;

/**
 * <p>
 * JUnit testing support for Buckminster.
 * </p>
 * <p>
 * The {@link org.osgi.framework.BundleActivator} of the <code>org.eclipse.buckminster.test</code> bundle.
 * <p>
 * 
 * @author Michal Rùžièka
 */
public class TestPlugin extends Plugin
{
	private static TestPlugin s_testPlugin;

	public static TestPlugin getDefault()
	{
		return s_testPlugin;
	}

	private ServiceTracker m_adminTracker;

	public TestPlugin()
	{
		super();
		s_testPlugin = this;
	}

	public PackageAdmin getPackageAdmin()
	{
		ServiceTracker adminTracker = m_adminTracker;
		PackageAdmin packageAdmin;

		if(adminTracker == null || (packageAdmin = (PackageAdmin)adminTracker.getService()) == null)
		{
			throw new IllegalStateException(getBundle().getSymbolicName() + " is not running.");
		}

		return packageAdmin;
	}

	@Override
	public void start(BundleContext context) throws Exception
	{
		m_adminTracker = new ServiceTracker(context, PackageAdmin.class.getName(), null);
		m_adminTracker.open();
		super.start(context);
	}

	@Override
	public void stop(BundleContext context) throws Exception
	{
		m_adminTracker.close();
		m_adminTracker = null;
		super.stop(context);
	}
}
