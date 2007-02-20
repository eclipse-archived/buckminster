/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.model;

import java.util.UUID;

import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.buckminster.core.metadata.ISaxableStorage;

/**
 * @author Thomas Hallgren
 *
 */
public class ElementNotFoundException extends LocalizedException
{
	private static final long serialVersionUID = 1347735898272498412L;
	private final String[] m_args;

	public ElementNotFoundException(ISaxableStorage<?> storage, UUID uuid)
	{
		super("No element with id {1} was found in storage {0}");
		m_args = new String[] { storage.getName(), uuid.toString() };
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return m_args;
	}
}
