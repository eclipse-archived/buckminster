/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.mspec;

/**
 * An enum that describes what to do when content exists
 * at the location where the component should be materialized.
 * 
 * @author Thomas Hallgren
 */
public enum ConflictResolution
{
	/**
	 * Fail with an exception.
	 */
	FAIL("Fail"),

	/**
	 * Simply clear the location prior to materializing.
	 */
	REPLACE("Replace"),

	/**
	 * Update with new content. Some materializers may
	 * consider this equal to <code>REPLACE</code>.
	 */
	UPDATE("Update"),

	/**
	 * Reuse (trust) the content at the location and don't
	 * perform a new materialization.
	 */
	KEEP("Keep");

	private final String m_string;

	private ConflictResolution(String string)
	{
		m_string = string;
	}

	@Override
	public String toString()
	{
		return m_string;
	}

	public static ConflictResolution getDefault()
	{
		return UPDATE;
	}
}
