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
 * <p>The version designator is modeled after the OSGi version range notation.
 * The actual versions in such a range is however not limited to the OSGi
 * syntax.</p>
 *
 * <p>A version designator can appoint an explicit version or a range of
 * versions. The range might be infinite and its bounds might be included
 * or excluded in the range.</p>
 * 
 * @author Thomas Hallgren
 */
public interface IVersionDesignator
{
	public enum ComparisonResult
	{
		/**
		 * This instance designates all versions of another designator
		 * and then some.
		 */
		Contains,

		/**
		 * The other designator designates all versions of this instance
		 * and then some.
		 */
		ContainedBy,

		/**
		 * This instance designates the exact same versions as another designator.
		 */
		Equal,

		/**
		 * The lower versions designated by this instance intersects with
		 * the higher versions designated by another designator but neither
		 * contain the other completely.
		 */
		IntersectLow,

		/**
		 * The higher versions designated by this instance intersects with
		 * the lower versions designated by another designator but neither
		 * contain the other completely.
		 */
		IntersectHigh,

		/**
		 * The two designators does not designate any common versions.
		 */
		Disparate
	};

	/**
	 * Compares the two designators and returns the result.
	 * @param designator The designator to compare with
	 * @return The result of the comparison.
	 */
	ComparisonResult compare(IVersionDesignator designator);

	/**
	 * Returns the version. If the designator is a range, this will be the
	 * lower bound of that range.
	 * @return The version.
	 */
	IVersion getVersion();

	/**
	 * Returns the to version or null if the designator is not a range.
	 * @return The version.
	 */
	IVersion getToVersion();

	/**
	 * Returns <code>true</code> if this designator has an upper bound.
	 * @return <code>true</code> if an upper bound is present. 
	 */
	boolean hasUpperBound();

	/**
	 * Returns <code>true</code> if the designator includes the lower bound in the match
	 * and <code>false</code> if the match starts with versions greater then the lower
	 * bound.
	 * @return true if the lower bound is included in the match.
	 */
	boolean includesLowerBound();
	
	/**
	 * Returns <code>true</code> if the designator includes the upper bound in the match
	 * and <code>false</code> if the match only includes versions less then the upper
	 * bound.
	 * @return true if the upper bound is included in the match.
	 */
	boolean includesUpperBound();

	/**
	 * Returns <code>true</code> if this is instance designates an explicit
	 * version. This means that only a version that is exactly equal to the
	 * version returned by {@link #getVersion()} will yield true for a call
	 * to {@link #designates(String) }.
	 * @return <code>true</code> if this is instance designates an explicit
	 * version.
	 */
	boolean isExplicit();

	/**
	 * Returns true if the given version is an ideal match for this designator.
	 * @return <code>true</code> if no other version is a better match.
	 */
	boolean isIdeal(IVersion version);
	
	/**
	 * Merge the two designators so that a new designator is created that will
	 * reflect the intesecting range.
	 * @param that The other designator
	 * @return A new designator for the intersecting range or <code>null</code>
	 * if no such range is possible.
	 */
	IVersionDesignator merge(IVersionDesignator that);

	/**
	 * Checks if the given version is designated by this designator.
	 * @param version The version to check.
	 * @return <code>true</code> if the version is designated by this designator.
	 */
	boolean designates(String version) throws VersionSyntaxException;

	/**
	 * Checks if the given version is designated by this designator. The version
	 * must have the same {@link IVersionType} as the one returned by {@link #getVersion()}.
	 * @param version The version to check.
	 * @return <code>true</code> if the version is designated by this designator.
	 */
	boolean designates(IVersion version);
}
