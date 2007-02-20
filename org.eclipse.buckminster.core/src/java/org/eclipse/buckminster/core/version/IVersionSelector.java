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

/**
 * @author Thomas Hallgren
 */
public interface IVersionSelector
{
	/**
	 * The name of the main (also know as default) branch.
	 */
	public static final String DEFAULT_BRANCH = "main";

	/**
	 * The name of the logical tag that denotes the latest revision on a branch.
	 */
	public static final String TAG_LATEST = "LATEST";

	/**
	 * Returns the branch of the version selector or <code>null</code> if this
	 * version is not associated with a branch.
	 * @return
	 */
	String getBranchName();

	/**
	 * Returns the qualifier or <code>null</code> if this version selector has no qualifier.
	 * @return The qualifier in String form.
	 */
	String getQualifier();

	/**
	 * Returns the qualifier in numeric form if possible. Change numbers and timestamps
	 * will implement this.
	 * @return The qualifier in numeric form or zero if not applicable.
	 */
	long getNumericQualifier();

	/**
	 * Returns the type of the version selector.
	 * @return The type
	 */
	VersionSelectorType getType();

	/**
	 * Returns the type info or <code>null</code> if no such info has been assigned.
	 */
	String getTypeInfo();

	/**
	 * Returns true if this selector denotes something on the branch that
	 * is considered the default.
	 */
	boolean isDefaultBranch();
}
