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
public class MissingDependencyException extends LocalizedException
{
	private static final long serialVersionUID = -7209154215301619393L;
	private final String m_dependencyName;
	private final String m_componentName;

	public MissingDependencyException(String componentName, String dependencyName)
	{
		super("Component {0} has no declared dependency to component {1}");
		m_componentName = componentName;
		m_dependencyName = dependencyName;
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return new String[] { m_componentName, m_dependencyName };
	}
}
