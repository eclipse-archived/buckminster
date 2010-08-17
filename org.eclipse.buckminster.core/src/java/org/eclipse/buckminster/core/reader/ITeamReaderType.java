/*******************************************************************************
 * Copyright (c) 2010, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.reader;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.team.core.RepositoryProvider;

/**
 * ITeamReaderType extends IRederType with SCM related functionality.
 * 
 * @author michal.ruzicka@cloudsmith.com
 */
public interface ITeamReaderType extends IReaderType {
	/**
	 * Return a source reference suitable for the Eclipse-SourceReference
	 * Manifest header for the given resource
	 * 
	 * @param resource
	 *            Resource for which a source reference should be obtained
	 * @param monitor
	 *            a progress monitor
	 * @return A source reference or <code>null</code> if no such reference
	 *         could be obtained.
	 * @throws CoreException
	 */
	String getSourceReference(IResource resource, IProgressMonitor monitor) throws CoreException;

	/**
	 * Tag the given <code>resources</code> with the given <code>tag</code>.
	 * 
	 * @param provider
	 *            the <code>RepositoryProvider</code> the <code>resources</code>
	 *            are associated with
	 * @param resources
	 *            the resources to tag
	 * @param tag
	 *            the tag to tag the resources with
	 * @param recurse
	 *            whether the tagging should be deep or shallow
	 * @param monitor
	 *            a progress monitor
	 * @return the resulting status of the tagging
	 * @throws CoreException
	 */
	IStatus tag(RepositoryProvider provider, IResource[] resources, String tag, boolean recurse, IProgressMonitor monitor) throws CoreException;
}
