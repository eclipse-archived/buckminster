/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.model;

import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.LocalizedException;

/**
 * @author Thomas Hallgren
 *
 */
public class UnresolvedNodeException extends LocalizedException
{
	private static final long serialVersionUID = -5339155252481631654L;
	private final String[] m_args;

	public UnresolvedNodeException(ComponentRequest request)
	{
		super("Attempt to use an unresolved node. Request is {0}");
		m_args = new String[] { request.toString() };
		assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return m_args;
	}
}
