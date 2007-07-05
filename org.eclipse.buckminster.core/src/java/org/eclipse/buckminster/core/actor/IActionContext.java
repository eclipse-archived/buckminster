/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.actor;

import java.io.File;
import java.io.PrintStream;
import java.util.Map;
import java.util.UUID;

import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

public interface IActionContext extends IModelCache
{
	/**
	 * Returns the action.
	 * @return The action associated with this context
	 */
	Action getAction();

	/**
	 * Returns the component location as an file system absolute path
	 * @return The absolute path in the file system to the component.
	 * @throws CoreException
	 */
	IPath getComponentLocation() throws CoreException;

	/**
	 * Returns the component specification
	 * @return The CSPEC
	 */
	CSpec getCSpec() throws CoreException;

	/**
	 * Returns the cache that is in effect for the whole top level invocation
	 * @return A cache that various actions can use for arbitrary purposes
	 */
	Map<UUID,Object> getInvocationCache();

	/**
	 * Returns the stream that should be used for information and debug messages.
	 * @return A stream for information and debug messages.
	 */
	PrintStream getOutputStream();

	/**
	 * Returns all named path group arrays for the invocation.
	 * @return An map of named path group arrays.
	 * @throws CoreException
	 */
	Map<String, PathGroup[]> getNamedPathGroupArrays() throws CoreException;

	/**
	 * Add all named prerequisite path groups 
	 * @param pgas The map that will receive the groups
	 * @throws CoreException
	 */
	void addPrerequisitePathGroups(Map<String, PathGroup[]> pgas) throws CoreException;

	/**
	 * Returns the stream that should be used for error messages.
	 * @return A stream for error messages.
	 */
	PrintStream getErrorStream();

	/**
	 * Return the absolute path of <code>path</code>. If path is already
	 * absolut, it is returend unaltered. If it is relative it will be made
	 * absolute using the component root.
	 * @param path The path to make absolute. Cannot be <code>null</code>.
	 * @return An absolute path.
	 * @throws CoreException
	 */
	File makeAbsolute(File path) throws CoreException;

	/**
	 * Return the absolute path of <code>path</code>. If path is already
	 * absolut, it is returend unaltered. If it is relative it will be made
	 * absolute using the component root.
	 * @param path The path to make absolute. Cannot be <code>null</code>.
	 * @return An absolute path.
	 * @throws CoreException
	 */
	IPath makeAbsolute(IPath path) throws CoreException;

	/**
	 * Schedule the removal of <code>path</code> when the top build invocation
	 * ends. If <code>path</code> is relative, it will be made absolute using the component
	 * location as base.
	 * @param path The file or directory to remove
	 */
	void scheduleRemoval(IPath path) throws CoreException;

	/**
	 * Returns a progress monitor that can be used for cancellation purposes.
	 * @return A progress monitor
	 */
	IProgressMonitor getCancellationMonitor();
}
