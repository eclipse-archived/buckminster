/*******************************************************************************
 * Copyright (c) 2008
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed below, as Initial Contributors under such license.
 * The text of such license is available at 
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 * 		Henrik Lindberg
 *******************************************************************************/

package org.eclipse.equinox.p2.authoring;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbench;

/**
 * An Image Facade for images used by p2 authoring.
 * 
 * @author Henrik Lindberg
 * 
 */
public class P2AuthoringImages
{
	public static final String IMG_HORIZONTAL = "horizontal"; //$NON-NLS-1$

	public static final String IMG_VERTICAL = "vertical"; //$NON-NLS-1$

	public static final String IMG_IU = "iu"; //$NON-NLS-1$

	public static final String IMG_FRAGMENT = "fragment"; //$NON-NLS-1$

	public static final String IMG_FRAGMENTS = "fragments"; //$NON-NLS-1$

	public static final String IMG_BUNDLE = "bundle"; //$NON-NLS-1$

	public static final String IMG_REQ_CAPABILITY = "reqcap"; //$NON-NLS-1$

	public static final String IMG_REQ_CAPABILITIES = "reqcaps"; //$NON-NLS-1$

	public static final String IMG_PROV_CAPABILITY = "provcap"; //$NON-NLS-1$

	public static final String IMG_PROV_CAPABILITIES = "provcaps"; //$NON-NLS-1$

	public static final String IMG_OVERVIEW = "overview"; //$NON-NLS-1$

	public static final String IMG_JAR = "jar"; //$NON-NLS-1$

	public static final String IMG_LIB = "lib"; //$NON-NLS-1$

	public static final String IMG_PLUGIN = "plugin"; //$NON-NLS-1$	

	public static final String IMG_CATEGORY = "category"; //$NON-NLS-1$	

	public static final String IMG_FEATURE = "feature"; //$NON-NLS-1$;

	public static final String IMG_FILE = "file"; //$NON-NLS-1$;

	public static final String IMG_FOLDER = "folder"; //$NON-NLS-1$;

	public static final String IMG_RSS = "rss"; //$NON-NLS-1$;

	public static final String IMG_PROJECT = "proj"; //$NON-NLS-1$;

	public static final String IMG_PACKAGE = "pkg"; //$NON-NLS-1$;

	/**
	 * Returns an Image descriptor to a managed image descriptor. The user <i>must not</i> dispose of the created image.
	 * 
	 * @param imageKey
	 * @return
	 */
	public static ImageDescriptor getImageDescriptor(String imageKey)
	{
		return P2AuthoringUIPlugin.getDefault().getImageRegistry().getDescriptor(imageKey);
	}

	public static Image getIMG_HORIZONTAL()
	{
		return getImage(IMG_HORIZONTAL);
	}

	public static Image getIMG_VERTICAL()
	{
		return getImage(IMG_VERTICAL);
	}

	public static Image getIMG_IU()
	{
		return getImage(IMG_IU);
	}

	public static Image getIMG_FRAGMENT()
	{
		return getImage(IMG_FRAGMENT);
	}

	public static Image getIMG_FRAGMENTS()
	{
		return getImage(IMG_FRAGMENTS);
	}

	public static Image getIMG_BUNDLE()
	{
		return getImage(IMG_BUNDLE);
	}

	public static Image getIMG_REQ_CAPABILITY()
	{
		return getImage(IMG_REQ_CAPABILITY);
	}

	public static Image getIMG_REQ_CAPABILITIES()
	{
		return getImage(IMG_REQ_CAPABILITIES);
	}

	public static Image getIMG_PROV_CAPABILITY()
	{
		return getImage(IMG_PROV_CAPABILITY);
	}

	public static Image getIMG_PROV_CAPABILITIES()
	{
		return getImage(IMG_PROV_CAPABILITIES);
	}

	public static Image getIMG_OVERVIEW()
	{
		return getImage(IMG_OVERVIEW);
	}

	public static Image getIMG_JAR()
	{
		return getImage(IMG_JAR);
	}

	public static Image getIMG_LIB()
	{
		return getImage(IMG_LIB);
	}

	public static Image getIMG_PLUGIN()
	{
		return getImage(IMG_PLUGIN);
	}

	public static Image getIMG_CATEGORY()
	{
		return getImage(IMG_CATEGORY);
	}

	public static Image getIMG_FEATURE()
	{
		return getImage(IMG_FEATURE);
	}

	public static Image getIMG_FILE()
	{
		return getImage(IMG_FILE);
	}

	public static Image getIMG_FOLDER()
	{
		return getImage(IMG_FOLDER);
	}

	public static Image getIMG_RSS()
	{
		return getImage(IMG_RSS);
	}

	public static Image getIMG_PROJECT()
	{
		return getImage(IMG_PROJECT);
	}

	public static Image getIMG_PACKAGE()
	{
		return getImage(IMG_PACKAGE);
	}

	/**
	 * Returns a managed image. The user <i>must not</i> dispose of the returned image.
	 * 
	 * @param imageKey
	 * @return
	 */
	public static Image getImage(String imageKey)
	{
		return P2AuthoringUIPlugin.getDefault().getImageRegistry().getDescriptor(imageKey).createImage();
	}

	/**
	 * Returns an ImageDescriptor from the workbench's registry of images associated with file names.
	 * 
	 * @param fileName
	 *            - e.g. "page.html"
	 * @return a managed image descriptor
	 */
	public static ImageDescriptor getImageDescriptorForFile(String fileName)
	{
		return getWorkbench().getEditorRegistry().getImageDescriptor(fileName);
	}

	/**
	 * Get the workbench when there is no other starting point. This method uses a restricted API to get the workbench
	 * from UIPlugin.
	 * 
	 * @return
	 */
	@SuppressWarnings("restriction")
	private static IWorkbench getWorkbench()
	{
		return org.eclipse.ui.internal.UIPlugin.getDefault().getWorkbench();
	}

}
