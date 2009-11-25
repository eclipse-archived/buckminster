/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.util;

import org.eclipse.buckminster.aggregator.loader.IRepositoryLoader;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

/**
 * @author Filip Hrbek
 * 
 */
public class RepositoryLoaderUtils
{
	public static IConfigurationElement getLoaderFor(String nature) throws CoreException
	{
		for(IConfigurationElement extension : Platform.getExtensionRegistry().getConfigurationElementsFor(
				IRepositoryLoader.EXTENSION_POINT_ID))
		{
			if(nature.equals(extension.getAttribute(IRepositoryLoader.EXTENSION_POINT_ATTRIBUTE_NATURE)))
				return extension;
		}

		throw BuckminsterException.fromMessage("No repository loader for nature %s", nature);
	}
}
