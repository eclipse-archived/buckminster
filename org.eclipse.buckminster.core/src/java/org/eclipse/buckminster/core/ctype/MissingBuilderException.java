/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.ctype;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.LocalizedException;

/**
 * @author Thomas Hallgren
 */
public class MissingBuilderException extends LocalizedException
{
	private static final long serialVersionUID = 7752898568820712216L;
	private final String m_argument;

	public MissingBuilderException(String builderId)
	{
		super("No Component Specification (cspec) builder with id {0} has been registered with extension-point {1}");
		m_argument = builderId;
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return new String[] { m_argument, CorePlugin.CSPEC_BUILDER_POINT };
	}
}

