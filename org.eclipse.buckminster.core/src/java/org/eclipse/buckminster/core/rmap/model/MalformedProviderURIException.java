/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.rmap.model;

import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.buckminster.core.reader.IReaderType;

/**
 * @author Thomas Hallgren
 */
public class MalformedProviderURIException extends LocalizedException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6610886445502684660L;
	private final String m_readerType;
	private final String m_uri;

	public MalformedProviderURIException(IReaderType readerType, String uri)
	{
		super("A reader of type {0} cannot use the uri {1}");
		m_readerType = readerType.getId();
		m_uri = uri;
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return new String[] { m_readerType, m_uri };
	}
}

