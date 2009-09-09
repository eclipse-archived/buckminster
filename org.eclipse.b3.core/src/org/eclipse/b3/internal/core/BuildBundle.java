/*******************************************************************************
 * Copyright (c) 2009, Cloudsmith Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Cloudsmith Inc - initial API and implementation
 *******************************************************************************/

package org.eclipse.b3.internal.core;

import org.eclipse.b3.provisional.core.IBuildContext;
import org.eclipse.b3.provisional.core.IBuildConstants;
import org.eclipse.b3.provisional.core.ILogger;
import org.eclipse.b3.provisional.core.ServicesHelper;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The activator class controls the plug-in life cycle
 */
public class BuildBundle extends Plugin  {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.b3.core"; //$NON-NLS-1$

	// The shared instance
	private static BuildBundle plugin;

	private ServiceTracker defaultContextTracker;
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		// create trackers
		defaultContextTracker = new ServiceTracker(getBundle().getBundleContext(), 
				ServicesHelper.getTrackerFilter(IBuildConstants.DEFAULT_BUILD_CONTEXT,
						IBuildContext.class), 
						null); // no customizer (yet?) - the tracker returns available service
	}
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		// dispose trackers
		defaultContextTracker.close();

		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static BuildBundle getDefault() {
		return plugin;
	}
	public IBuildContext getDefaultBuildContext()
	{
		defaultContextTracker.open();
		return (IBuildContext) defaultContextTracker.getService();
	}
	
	
	// TODO: Cheating by using Buckminster's Logger
	//
	private ILogger logger;

	public synchronized ILogger getBundleLogger()
	{
		if(logger == null)
			logger = new BuckminsterBuildLogger(this.getBundle());
		return logger;
	}
	public static ILogger getLogger()
	{
		return getDefault().getBundleLogger();
	}

}
