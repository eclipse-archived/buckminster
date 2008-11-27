/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.actor;

import java.util.Map;
import java.util.UUID;

import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;

public interface IGlobalContext extends IModelCache
{
	/**
	 * Returns the property set to use when performing <code>attribute</code>.
	 * 
	 * @param attribute
	 *            The attribute for which we need the properties
	 * @return The properties to use for attribute.
	 */
	Map<String, String> getExecutionProperties(Attribute attribute) throws CoreException;

	/**
	 * Returns the cache that is in effect for the whole top level invocation
	 * 
	 * @return A cache that various actions can use for arbitrary purposes
	 */
	Map<UUID, Object> getInvocationCache();

	/**
	 * Returns the status of the last perform issued using this context
	 * 
	 * @return The status of the last perform.
	 */
	IStatus getStatus();

	/**
	 * Schedule the removal of <code>path</code> when the top build invocation ends. The <code>path</code> must be an
	 * absolute path.
	 * 
	 * @param path
	 *            The absolute path of the file or directory to remove
	 */
	void scheduleRemoval(IPath path) throws CoreException;
}
