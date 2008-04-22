/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.generic.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * Plugin with generic UI functionality.
 */
public class GenericUiPlugin extends AbstractUIPlugin
{
	private static GenericUiPlugin s_plugin;

	// must be the same as the id in plugin.xml
	//
	static private final String s_id = "org.eclipse.buckminster.generic.ui"; //$NON-NLS-1$

	static public String getID()
	{
		return s_id;
	}

	/**
	 * The constructor.
	 */
	public GenericUiPlugin()
	{
		super();
		s_plugin = this;
	}

	/**
	 * This method is called upon plug-in activation
	 */
	@Override
	public void start(BundleContext context) throws Exception
	{
		super.start(context);
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
	 * Returns the shared instance.
	 */
	public static GenericUiPlugin getDefault()
	{
		return s_plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in relative path.
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path)
	{
		return AbstractUIPlugin.imageDescriptorFromPlugin(getID(), path); //$NON-NLS-1$
	}

	public static IStatus toStatus(Throwable t)
	{
		IStatus status = null;
		if(t instanceof CoreException)
			status = ((CoreException)t).getStatus();
		else
			status = new Status(IStatus.ERROR, getID(), -1, t.getMessage(), t);
		return status;
	}
}
