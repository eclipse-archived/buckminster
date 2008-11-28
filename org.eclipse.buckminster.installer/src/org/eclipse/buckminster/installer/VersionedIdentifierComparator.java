/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.installer;

import java.util.Comparator;

import org.eclipse.update.core.VersionedIdentifier;

/**
 * @author kolwing
 * 
 */
public class VersionedIdentifierComparator implements Comparator<VersionedIdentifier>
{
	public final static VersionedIdentifierComparator ASCENDING = new VersionedIdentifierComparator(1);

	public final static VersionedIdentifierComparator DESCENDING = new VersionedIdentifierComparator(-1);

	public static int compareStatic(VersionedIdentifier vid1, VersionedIdentifier vid2)
	{
		return internalCompare(vid1, vid2);
	}

	@Deprecated
	private static int internalCompare(VersionedIdentifier vid1, VersionedIdentifier vid2)
	{
		// test the id string first
		//
		int compared = vid1.getIdentifier().compareTo(vid2.getIdentifier());
		if(compared != 0)
			return compared;

		// the id string is equal, now test the version
		//
		org.eclipse.core.runtime.PluginVersionIdentifier pvid1 = vid1.getVersion();
		org.eclipse.core.runtime.PluginVersionIdentifier pvid2 = vid2.getVersion();
		if(pvid1.isPerfect(pvid2))
			return 0;

		// the versions are not equal, is the first greater than the second?
		//
		if(pvid1.isGreaterThan(pvid2))
			return 1;

		// the first is less than the second
		//
		return -1;
	}

	private final int m_direction;

	private VersionedIdentifierComparator(int direction)
	{
		m_direction = direction;
	}

	public int compare(VersionedIdentifier vid1, VersionedIdentifier vid2)
	{
		return internalCompare(vid1, vid2) * m_direction;
	}
}
