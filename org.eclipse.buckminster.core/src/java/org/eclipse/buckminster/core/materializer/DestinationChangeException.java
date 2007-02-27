/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.materializer;

import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 *
 */
public class DestinationChangeException extends LocalizedException
{
	private static final long serialVersionUID = 2183675159512733191L;
	private final String[] m_args;

	public DestinationChangeException(IPath fixedDest, IPath wantedDest)
	{
		super("An attempt was made to change the fixed materialization location {0} to {1}");
		m_args = new String[] { fixedDest.toPortableString(), wantedDest.toPortableString() };
		assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return m_args;
	}
}
