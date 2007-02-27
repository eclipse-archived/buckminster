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
public class GeneratorAlreadyDefinedException extends LocalizedException
{
	private static final long serialVersionUID = -1799750917800728901L;
	private final String m_generates;
	private final String m_componentName;

	public GeneratorAlreadyDefinedException(String componentName, String generates)
	{
		super("A generator that generates {1} is defined more then once in component {0}");
		m_componentName = componentName;
		m_generates = generates;
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return new String[] { m_componentName, m_generates };
	}
}
