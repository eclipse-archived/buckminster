/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import org.eclipse.buckminster.core.cspec.builder.PrerequisitesBuilder;

/**
 * The special group that maintains the action prerequisites. It doesn't have a name, it does have an alias and its tag
 * is different.
 * 
 * @author Thomas Hallgren
 * 
 */
public class Prerequisites extends Group
{
	@SuppressWarnings("hiding")
	public static final String TAG = "prerequisites"; //$NON-NLS-1$

	public static final String ATTR_ALIAS = "alias"; //$NON-NLS-1$

	private final Action m_owner;

	public Prerequisites(Action owner, PrerequisitesBuilder builder)
	{
		super(builder);
		m_owner = owner;
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

	@Override
	public String getQualifiedName()
	{
		return getCSpec().getName() + '#' + m_owner.getName() + "_pqs"; //$NON-NLS-1$
	}
}
