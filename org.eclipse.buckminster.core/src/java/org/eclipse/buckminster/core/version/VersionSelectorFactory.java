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

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.internal.version.BranchSelector;
import org.eclipse.buckminster.core.internal.version.ChangeNumberSelector;
import org.eclipse.buckminster.core.internal.version.DefaultQuery;
import org.eclipse.buckminster.core.internal.version.TagSelector;
import org.eclipse.buckminster.core.internal.version.TimestampSelector;
import org.eclipse.buckminster.core.internal.version.VersionSelector;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 */
public abstract class VersionSelectorFactory
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
	 * The version selector that reflects the latest version on the main branch.
	 */
	public static final IVersionSelector MAIN_LATEST = new BranchSelector(DEFAULT_BRANCH, null);

	public static IVersionSelector changeNumber(long number)
	{
		return new ChangeNumberSelector(DEFAULT_BRANCH, number, null);
	}

	public static IVersionSelector changeNumber(String branch, long number)
	{
		return changeNumber(branch, number, null);
	}

	public static IVersionSelector changeNumber(String branch, long number, String typeInfo)
	{
		return new ChangeNumberSelector(canonicalBranch(branch), number, typeInfo);
	}

	public static IVersionSelector timestamp(long timestamp)
	{
		return new TimestampSelector(DEFAULT_BRANCH, timestamp, null);
	}

	public static IVersionSelector timestamp(String branch, long timestamp)
	{
		return timestamp(branch, timestamp, null);
	}

	public static IVersionSelector timestamp(String branch, long timestamp, String typeInfo)
	{
		return new TimestampSelector(canonicalBranch(branch), timestamp, typeInfo);
	}

	public static IVersionSelector tag(String tag)
	{
		return tag(DEFAULT_BRANCH, tag, null);
	}

	public static IVersionSelector tag(String branch, String tag)
	{
		return tag(branch, tag, null);
	}

	public static IVersionSelector tag(String branch, String tag, String typeInfo)
	{
		return TAG_LATEST.equals(tag) ? latest(branch, typeInfo) : new TagSelector(canonicalBranch(branch), tag, typeInfo);
	}

	public static IVersionSelector latest()
	{
		return MAIN_LATEST;
	}

	public static IVersionSelector latest(String branch)
	{
		return latest(branch, null);
	}

	public static IVersionSelector latest(String branch, String typeInfo)
	{
		branch = canonicalBranch(branch);
		return DEFAULT_BRANCH == branch ? MAIN_LATEST : new BranchSelector(branch, typeInfo);
	}

	public static IVersionSelector fromString(CharSequence versionString) throws BuckminsterException
	{
		return VersionSelector.fromString(versionString);
	}

	public static IVersionQuery createQuery(IVersionConverter versionConverter, IVersionDesignator versionDesignator)
	throws CoreException
	{
		return new DefaultQuery(versionConverter, versionDesignator);
	}

	private static final String canonicalBranch(String branch)
	{
		if(branch == null)
			return DEFAULT_BRANCH;
		branch = branch.trim();
		return (branch.length() == 0 || branch.equals(DEFAULT_BRANCH)) ? DEFAULT_BRANCH : branch;
	}
}
