/*******************************************************************************
 * Copyright (c) 2010, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.reader;

import java.util.Map;

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
	 * Tag the given <code>resources</code> with the given <code>tag</code>
	 * possibly using a different way of accessing (as specified in
	 * <code>mappings</code>) the repository the <code>resources</code> are
	 * shared with than would be normally used by the associated
	 * {@link RepositoryProvider}.
	 * 
	 * @param provider
	 *            the <code>RepositoryProvider</code> the <code>resources</code>
	 *            are associated with
	 * @param resources
	 *            the resources to tag
	 * @param mappings
	 *            the mappings of the ways of accessing the repositories (these
	 *            are typically used to specify credentials with write
	 *            permissions to the repositories in cases the corresponding
	 *            repository providers offer just a read only access)
	 * @param tag
	 *            the tag to tag the resources with
	 * @param recurse
	 *            whether the tagging should be deep or shallow
	 * @param monitor
	 *            a progress monitor
	 * @return the resulting status of the tagging
	 * @throws CoreException
	 */
	IStatus tag(RepositoryProvider provider, IResource[] resources, Map<String, String> mappings, String tag, boolean recurse,
			IProgressMonitor monitor) throws CoreException;
}
