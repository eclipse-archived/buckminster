/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import org.eclipse.buckminster.core.helpers.LocalizedException;

public class HintAlreadyDefinedException extends LocalizedException
{
	private static final long serialVersionUID = -8061340018978439600L;
	private final String m_name;
	private final String m_attribute;
	private final String m_hint;

	public HintAlreadyDefinedException(String name, String attribute, String hint)
	{
		super("CSpec {0}, attribute {1} already has a hint named {2}");
		m_name = name;
		m_attribute = attribute;
		m_hint = hint;
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return new String[] { m_name, m_attribute, m_hint };
	}
}

