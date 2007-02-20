/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import org.eclipse.buckminster.core.helpers.LocalizedException;

/**
 * @author Thomas Hallgren
 */
public class MissingAttributeException extends LocalizedException
{
	private static final long serialVersionUID = 8322562700921210552L;
	private final String m_name;
	private final String m_target;
	private final String m_visibility;

	public MissingAttributeException(String name, String target)
	{
		this(name, target, false);
	}

	public MissingAttributeException(String name, String target, boolean withVisibility)
	{
		super("CSpec {0} has no {1}action, group, or local artifact named {2}");
		m_name = name;
		m_visibility = withVisibility ? "public " : "";
		m_target = target;
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return new String[] { m_name, m_visibility, m_target };
	}
}

