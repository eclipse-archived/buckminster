/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.version;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.LocalizedException;

/**
 * @author Thomas Hallgren
 */
public class MissingVersionTypeException extends LocalizedException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8936848954087478097L;
	private final String m_argument;

	public MissingVersionTypeException(String matcherId)
	{
		super("No version type with id {0} has been registered with extension-point {1}");
		m_argument = matcherId;
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return new String[] { m_argument, CorePlugin.VERSION_TYPES_POINT };
	}
}

