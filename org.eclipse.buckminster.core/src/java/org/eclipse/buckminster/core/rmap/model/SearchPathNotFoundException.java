/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.rmap.model;

import org.eclipse.buckminster.core.helpers.LocalizedException;

/**
 * @author Thomas Hallgren
 */
public class SearchPathNotFoundException extends LocalizedException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6454414549521244436L;
	private final String m_name;

	public SearchPathNotFoundException(String name)
	{
		super("Unable to find a searchPath for {0}");
		m_name = name;
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return new String[] { m_name };
	}
}

