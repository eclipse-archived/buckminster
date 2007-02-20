/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.materializer;

import org.eclipse.buckminster.core.helpers.LocalizedException;

/**
 * @author Thomas Hallgren
 */
public class ProjectNameMismatchException extends LocalizedException
{
	private static final long serialVersionUID = 6405574395014251751L;
	private final String m_wantedName;
	private final String m_existingName;

	/**
	 * @param defaultMessageFormat
	 */
	public ProjectNameMismatchException(String wantedName, String existingName)
	{
		super("ProjectBinding name conflict. Bind information indicates {0} for project named {1}");
		m_wantedName = wantedName;
		m_existingName = existingName;
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return new String[] { m_wantedName, m_existingName };
	}

}

