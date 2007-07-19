/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.helpers;

/**
 * Gives more information about a Job
 * 
 * @author Karel Brezina
 */
public interface IJobInfo
{
	/**
	 * Gets a name that can be used in a progress monitor. Usage: "subTask(getOperationName() + " completed");" or "subTask(getOperationName() + " canceled");"
	 */
	public String getOperationName();
}
