/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.resolver;

import org.eclipse.buckminster.core.helpers.LocalizedException;

/**
 * @author Thomas Hallgren
 */
public class CategoryMismatchException extends LocalizedException
{
	private static final long serialVersionUID = -7949001269236698658L;
	private final String[] m_params = new String[3];

	public CategoryMismatchException(String componentName, String expectedCategory, String builderCategory)
	{
		super("Category mismatch exception for component {0}. Requested category was {1} but cspec builder says {2}");
		m_params[0] = componentName;
		m_params[1] = expectedCategory;
		m_params[2] = builderCategory;
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return m_params;
	}
}
