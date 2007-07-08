/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.ctype;

import org.eclipse.buckminster.core.helpers.LocalizedException;

/**
 * @author Thomas Hallgren
 */
public class ComponentTypeMismatchException extends LocalizedException
{
	private static final long serialVersionUID = -7949001269236698658L;
	private final String[] m_params;

	public ComponentTypeMismatchException(String componentName, String expectedType, String actualType)
	{
		super("Component type mismatch exception for component {0}. Requested type was {1} but actual type is {2}");
		m_params = new String[] { componentName, expectedType, actualType };
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return m_params;
	}
}
