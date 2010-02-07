/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.download.internal;

import org.eclipse.ecf.core.ContainerFactory;
import org.eclipse.ecf.core.IContainer;
import org.eclipse.ecf.filetransfer.IRetrieveFileTransferContainerAdapter;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author Thomas Hallgren
 */
public class Activator implements BundleActivator {
	public static final String PLUGIN_ID = "org.eclipse.buckminster.download"; //$NON-NLS-1$

	private static Activator plugin;

	private IContainer container;

	public static final String EXPANDERS_POINT = PLUGIN_ID + ".expanders"; //$NON-NLS-1$

	public static final String DECOMPRESSORS_POINT = PLUGIN_ID + ".decompressors"; //$NON-NLS-1$

	public static Activator getDefault() {
		return plugin;
	}

	public synchronized IRetrieveFileTransferContainerAdapter createRetrieveFileTransfer() {
		return (IRetrieveFileTransferContainerAdapter) container.getAdapter(IRetrieveFileTransferContainerAdapter.class);
	}

	public void start(BundleContext context) throws Exception {
		plugin = this;
		container = ContainerFactory.getDefault().createContainer();
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		container = null;
	}
}
