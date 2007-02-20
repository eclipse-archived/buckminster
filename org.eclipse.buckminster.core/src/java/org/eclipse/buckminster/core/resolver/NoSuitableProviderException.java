/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.resolver;

import org.eclipse.buckminster.core.helpers.LocalizedException;

/**
 * @author Thomas Hallgren
 */
public class NoSuitableProviderException extends LocalizedException
{
	private static final long serialVersionUID = -1301891677802799097L;
	private final String m_searchPathName;
	private final String m_componentName;

	public NoSuitableProviderException(String searchPathName, String componentName)
	{
		super("No suitable provider for component {1} was found in searchPath {0}");
		m_searchPathName = searchPathName;
		m_componentName = componentName;
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return new String[] { m_searchPathName, m_componentName };
	}
}

