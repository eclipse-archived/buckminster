/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.metadata;

import org.eclipse.buckminster.core.helpers.LocalizedException;

/**
 * @author Thomas Hallgren
 */
public class MissingComponentException extends LocalizedException
{
	private static final long serialVersionUID = -7425651036670165654L;
	private final String m_componentName;

	public MissingComponentException(String componentName)
	{
		super("No component named {0} is known to Buckminster");
		m_componentName = componentName;
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return new String[] { m_componentName };
	}
}

