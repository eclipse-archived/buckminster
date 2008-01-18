/*****************************************************************************
* Copyright (c) 2006-2007, Cloudsmith Inc.
* The code, documentation and other materials contained herein have been
* licensed under the Eclipse Public License - v 1.0 by the copyright holder
* listed above, as the Initial Contributor under such license. The text of
* such license is available at www.eclipse.org.
*****************************************************************************/

package org.eclipse.buckminster.remote;

import org.eclipse.core.runtime.CoreException;

/**
 * Provides basic remote service operations
 * 
 * @author Karel Brezina
 */
public interface IServiceConnection
{
	/**
	 * Cancel remote operation
	 * 
	 * @throws CoreException
	 */
	void cancel() throws CoreException;
	
	/**
	 * Gets progress information
	 * 
	 * @return progress information
	 * @throws CoreException
	 */
	IProgressInfo getProgressInfo() throws CoreException;

	/**
	 * Checks if cancel was sent
	 * 
	 * @return true=cancel was sent
	 */
	boolean isCancelSent();

	/**
	 * Checks if the remote operation has finished
	 * 
	 * @return
	 */
	boolean isDone();

	/**
	 * Resets remote service connections
	 * 
	 * @throws CoreException
	 */
	void reset() throws CoreException;
	
	/**
	 * Releases remote service connections
	 * 
	 * @throws CoreException
	 */
	void releaseConnection() throws CoreException;	
}
