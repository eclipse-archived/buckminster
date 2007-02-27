/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.maven;

import org.eclipse.buckminster.runtime.LogAwarePlugin;
import org.eclipse.buckminster.runtime.Logger;
import org.osgi.framework.BundleContext;

/**
 * @author Thomas Hallgren
 */
public class MavenPlugin extends LogAwarePlugin
{
	public static final String PLUGIN_ID = "org.eclipse.buckminster.maven"; //$NON-NLS-1$

	private static MavenPlugin s_plugin;

	public static MavenPlugin getDefault()
	{
		return s_plugin;
	}

	public static Logger getLogger()
	{
		return s_plugin.getBundleLogger();
	}

	public MavenPlugin()
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
