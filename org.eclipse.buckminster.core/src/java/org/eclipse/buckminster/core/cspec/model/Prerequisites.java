/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.util.List;

import org.eclipse.core.runtime.IPath;

/**
 * The special group that maintains the action prerequisites. It
 * doesn't have a name, it does have an alias and its tag is different.
 * @author Thomas Hallgren
 *
 */
public class Prerequisites extends Group
{
	public static final String TAG = "prerequisites";
	public static final String ATTR_ALIAS = "alias";

	public Prerequisites(String alias, IPath prerequisiteRebase, List<Prerequisite> prerequisites)
	{
		super(alias, false, null, null, prerequisiteRebase, prerequisites);
	}

	@Override
	public String getDefaultTag()
	{
		return TAG;
	}

	@Override
	public String getNameAttributeName()
	{
		return ATTR_ALIAS;
	}
}
