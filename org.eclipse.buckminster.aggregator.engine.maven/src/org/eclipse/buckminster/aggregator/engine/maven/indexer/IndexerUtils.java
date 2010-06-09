/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.engine.maven.indexer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

/**
 * @author Filip Hrbek (filip.hrbek@cloudsmith.com)
 * 
 */
public class IndexerUtils
{
	public static IMaven2Indexer getIndexer(String name)
	{
		for(IConfigurationElement extension : Platform.getExtensionRegistry().getConfigurationElementsFor(
				IMaven2Indexer.EXTENSION_POINT_ID))
		{
			if(name.equals(extension.getAttribute(IMaven2Indexer.EXTENSION_POINT_ATTRIBUTE_ID)))
				try
				{
					return (IMaven2Indexer)extension.createExecutableExtension("class");
				}
				catch(CoreException e)
				{
					// try another one
				}
		}

		return null;
	}
}
