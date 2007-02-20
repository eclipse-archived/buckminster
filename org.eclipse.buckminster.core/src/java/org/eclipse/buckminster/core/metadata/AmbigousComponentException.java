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
 */
public class AmbigousComponentException extends LocalizedException
{
	private static final long serialVersionUID = -7425651036670165654L;
	private final String m_componentName;

	public AmbigousComponentException(String componentName)
	{
		super("More then one version of component {0} is known to Buckminster");
		m_componentName = componentName;
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return new String[] { m_componentName };
	}
}

