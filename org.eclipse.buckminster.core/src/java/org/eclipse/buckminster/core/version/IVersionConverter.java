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
import org.eclipse.buckminster.core.rmap.model.BidirectionalTransformer;
import org.eclipse.core.runtime.CoreException;

/**
 * Converts a <code>VersionSelector</code> of type <code>PLAIN</code> to
 * something that can be understood by a source code control system.
 * 
 * @author Thomas Hallgren
 */
public interface IVersionConverter extends IBuckminsterExtension
{
	public static final String TAG = "tag";
	public static final String BRANCH = "branch";

	/**
	 * Converts the <code>version</code> into a <code>IVersionSelector</code>
	 * that can be understood by a source code control system. An implementation
	 * will typically use the <code>version</code> as a tag or change number
	 * on the <code>defaultBranch</code> or disregard the
	 * <code>defaultBranch</code> and use the <code>version</code> as a
	 * branch name for a <code>IVersionSelector</code> of type
	 * <code>LATEST</code>.
	 * 
	 * @param version
	 *            The version to convert.
	 * @return A version selector.
	 * @throws CoreException
	 *             if the conversion cannot be performed.
	 */
	IVersionSelector createSelector(IVersion version) throws CoreException;

	/**
	 * Converts the <code>versionSelector</code> into a <code>IVersion</code>.
	 * This is the reverse of {@link #createSelector(IVersion) }.
	 * 
	 * @param versionType The type used when creating the version.
	 * @param versionSelector
	 *            The version selector to convert.
	 * @return A IVersion.
	 * @throws CoreException
	 *             if the conversion cannot be performed.
	 */
	IVersion createVersion(IVersionType versionType, IVersionSelector versionSelector) throws CoreException;

	/**
	 * Returns the type used when creating version selectors.
	 */
	VersionSelectorType getType();

	/**
	 * Assigns the transformer used when converting between plain versions and a
	 * version component.
	 * 
	 * @param transformer
	 */
	void setTransformers(BidirectionalTransformer[] transformers);
}
