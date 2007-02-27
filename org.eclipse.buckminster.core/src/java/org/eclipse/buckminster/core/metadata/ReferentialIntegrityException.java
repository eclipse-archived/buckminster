/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata;

import org.eclipse.buckminster.core.helpers.LocalizedException;

/**
 * @author Thomas Hallgren
 * 
 */
public class ReferentialIntegrityException extends LocalizedException
{
	private static final long serialVersionUID = 4272444003054478928L;
	private final String[] m_arguments;

	public ReferentialIntegrityException(IUUIDKeyed instance, String operation, String reason)
	{
		super("Unable to {0} the {1} with id {2}: {3}");
		m_arguments = new String[] { operation, instance.getClass().getName(), instance.getId().toString(),
				reason };
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return m_arguments;
	}
}
