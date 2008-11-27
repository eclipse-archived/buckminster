/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.version;

import org.eclipse.buckminster.core.IBuckminsterExtension;
import org.eclipse.core.runtime.CoreException;

/**
 * Converts a <code>VersionSelector</code> of type <code>PLAIN</code> to something that can be understood by a source
 * code control system.
 * 
 * @author Thomas Hallgren
 */
public interface IVersionConverter extends IBuckminsterExtension
{
	static final String TAG = "tag";

	static final String BRANCH = "branch";

	/**
	 * Converts the <code>version</code> into a <code>BranchOrTag</code> that can be understood by a source code control
	 * system. An implementation will typically use the <code>version</code> as a tag or branch name, possibly modified
	 * using a substitution pattern
	 * 
	 * @param version
	 *            The version to convert.
	 * @return A branch or tag
	 * @throws CoreException
	 *             if the conversion cannot be performed.
	 */
	VersionSelector createSelector(IVersion version) throws CoreException;

	/**
	 * Converts the <code>branchOrTag</code> into a <code>IVersion</code>. This is the reverse of
	 * {@link #createSelector(IVersion) }.
	 * 
	 * @param branchOrTag
	 *            The branch or tag to convert.
	 * @return A IVersion.
	 * @throws CoreException
	 *             if the conversion cannot be performed.
	 */
	IVersion createVersion(VersionSelector branchOrTag) throws CoreException;

	/**
	 * Returns the type of the selectors that this converter will produce. Can be either {@link VersionSelector#TAG} or
	 * {@link VersionSelector#BRANCH}.
	 * 
	 * @return The type of the produced selectors
	 */
	int getSelectorType();

	/**
	 * Returns the type of the versions that this converter will produce
	 * 
	 * @return The type of the produced versions
	 */
	IVersionType getVersionType();
}
