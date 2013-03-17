/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.mspec;

/**
 * An enum that describes what to do when content exists at the location where
 * the component should be materialized.
 * 
 * @author Thomas Hallgren
 */
public enum ConflictResolution {
	/**
	 * Fail with an exception.
	 */
	FAIL("Fail"), //$NON-NLS-1$

	/**
	 * Simply clear the location prior to materializing.
	 */
	REPLACE("Replace"), //$NON-NLS-1$

	/**
	 * Update with new content. Some materializers may consider this equal to
	 * <code>REPLACE</code>.
	 */
	UPDATE("Update"), //$NON-NLS-1$

	/**
	 * Reuse (trust) the content at the location and don't perform a new
	 * materialization.
	 */
	KEEP("Keep"); //$NON-NLS-1$

	public static ConflictResolution getDefault() {
		return UPDATE;
	}

	private final String string;

	private ConflictResolution(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return string;
	}
}
