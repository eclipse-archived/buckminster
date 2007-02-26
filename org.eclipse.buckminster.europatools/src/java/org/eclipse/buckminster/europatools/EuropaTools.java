/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.europatools;

import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.runtime.LogAwarePlugin;
import org.eclipse.buckminster.runtime.Logger;
import org.osgi.framework.BundleContext;

/**
 * @author Thomas Hallgren
 */
public class EuropaTools extends LogAwarePlugin
{
	public static final String BM_SITE_CONTRIBUTION_PREFIX = "sc";
	public static final String BM_SITE_CONTRIBUTION_NS = XMLConstants.BM_PREFIX + "SiteContribution-1.0";
	public static final String BM_SITE_CONTRIBUTION_RESOURCE = "/siteContribution-1.0.xsd";

	public static final String PLUGIN_ID = "org.eclipse.buckminster.europatools"; //$NON-NLS-1$

	private static EuropaTools s_plugin;

	public static EuropaTools getDefault()
	{
		return s_plugin;
	}

	public static Logger getLogger()
	{
		return s_plugin.getBundleLogger();
	}

	public EuropaTools()
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
