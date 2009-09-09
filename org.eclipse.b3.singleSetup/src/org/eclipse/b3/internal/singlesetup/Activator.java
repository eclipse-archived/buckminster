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

package org.eclipse.b3.internal.singlesetup;

import org.eclipse.b3.internal.core.BuildEventBus;
import org.eclipse.b3.provisional.core.IBuildContext;
import org.eclipse.b3.provisional.core.ServicesHelper;
import org.eclipse.b3.provisional.core.eventbus.IBuildEventBus;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * Default b3 services activation for a single instance b3 setup (for running in
 * an Eclipse IDE).
 * 
 */
public class Activator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.eclipse.b3.singleSetup"; //$NON-NLS-1$

	// // The shared instance
	// private static Activator plugin;

	private BuildEventBus bus;

	private BundleContext context;

	private ServiceRegistration registrationBus;

	private DefaultBuildContext buildContext;

	private ServiceRegistration registrationBuildContext;

	// / public Activator() {
	// }

	// public static Activator getDefault() {
	// return plugin;
	// }
	private void registerServices() {
		buildContext = new DefaultBuildContext();
		// register as both a context and a bus
		//
		registrationBuildContext = context.registerService(new String[] {
				IBuildContext.SERVICE_NAME, IBuildEventBus.SERVICE_NAME },
				buildContext, ServicesHelper
						.getBuildContextProperties(buildContext));
		// registrationBus = context.registerService(
		// new String[] { IBuildEventBus.SERVICE_NAME }, buildContext,
		// ServicesHelper.getBuildContextProperties(buildContext));
	}

	@Override
	public void start(BundleContext aContext) throws Exception {
		context = aContext;
		super.start(aContext);
		// plugin = this;
		registerServices();
	}

	@Override
	public void stop(BundleContext aContext) throws Exception {
		// plugin = null;
		super.stop(aContext);
		unregisterServices();
		context = null;
	}

	private void unregisterServices() {
		// unregister and close the event bus first - this flushes all events
		// before
		// build context goes away.
		//
		registrationBus.unregister();
		registrationBus = null;
		bus.close();

		registrationBuildContext.unregister();
		registrationBuildContext = null;
	}

}
