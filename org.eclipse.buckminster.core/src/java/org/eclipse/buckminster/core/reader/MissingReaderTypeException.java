/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.reader;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.LocalizedException;

/**
 * @author thhal
 */
public class MissingReaderTypeException extends LocalizedException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7064755637275007019L;
	private final String m_remoteReaderId;

	public MissingReaderTypeException(String remoteReaderId)
	{
		super("No reader type with id {0} has been registered with extension-point {1}");
		m_remoteReaderId = remoteReaderId;
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return new String[] { m_remoteReaderId, CorePlugin.READER_TYPE_POINT };
	}
}

