/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.reader;

import org.eclipse.buckminster.core.version.IVersionQuery;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * A ITagFinder will find the component tags that matches a certain
 * query.
 * @author Thomas Hallgren
 */
public interface IVersionFinder
{
	/**
	 * Return the version to use when no specific version requirement exists.
	 * @param monitor
	 * @return The default version.
	 * @throws CoreException
	 */
	VersionMatch getDefaultVersion(IProgressMonitor monitor)
	throws CoreException;

	/**
	 * Return the best match for the given version query.  
	 * @param query The version query to match.
	 * @param monitor A monitor where progress can be reported.
	 * @return The best match or <code>null</code> if no match can be found.
	 * @throws CoreException
	 */
	VersionMatch getBestVersion(IVersionQuery query, IProgressMonitor monitor)
	throws CoreException;

	/**
	 * Closes all resources utilized by this instance.
	 */
	void close();
}
