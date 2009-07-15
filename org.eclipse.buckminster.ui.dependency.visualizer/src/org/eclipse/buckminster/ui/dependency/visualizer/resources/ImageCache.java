/*******************************************************************************
 * Copyright (c) 2009 Johannes Utzig.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Johannes Utzig - initial API and implementation
 *******************************************************************************/
package org.eclipse.buckminster.ui.dependency.visualizer.resources;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.ui.dependency.visualizer.Activator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * The ImageCache works as plugin internal image cache for the resources consumed by the visualization.
 * <p>
 * All resources stay cached until an explizit call to dispose.
 * <p>
 * Note that all methods are static so calling dispose will affect all consumers in the same context.
 * 
 * @author Johannes Utzig
 * 
 */
public class ImageCache
{

	private static Map<String, Image> imageMap = new HashMap<String, Image>();

	/**
	 * disposes all cached resources and clears out the internal mappings
	 */
	public static void dispose()
	{
		for(Image image : imageMap.values())
		{
			image.dispose();
		}
		imageMap.clear();
	}

	/**
	 * Tries to load an image from the given path and caches the result in an internal map.
	 * <p>
	 * If the path was requested before, the image is fetched from the cache instead of newly allocated. The resources
	 * stay in memory until {@link ImageCache#dispose()} is called.
	 * <p>
	 * If the image at the given path cannot be found, <code>null</code> is returned.
	 * 
	 * @param path
	 *            - the full path to an image relative to the plugin root
	 * @return the image or <code>null</code> if not found
	 */
	public static Image getImage(String path)
	{
		Image image = imageMap.get(path);
		if(image != null)
		{
			return image;
		}
		ImageDescriptor descriptor = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, path);
		if(descriptor != null)
		{
			image = descriptor.createImage();
			imageMap.put(path, image);
		}
		return image;

	}

}
