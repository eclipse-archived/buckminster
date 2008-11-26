/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.remote;

/**
 * Holds progress information
 * 
 * @author Filip Hrbek
 */
public interface IProgressInfo
{
	/**
	 * Returns description of the task currently being processed
	 * 
	 * @return current task description
	 */
	String getMessage();

	/**
	 * Returns progress in ticks
	 * 
	 * @return progress in ticks
	 */
	int getWorked();

	/**
	 * Returns true if the process being monitored has already finished
	 * 
	 * @return true or false
	 */
	boolean isDone();
}
