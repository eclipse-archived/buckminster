/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.helpers;

/**
 * @author Thomas Hallgren
 */
public class IllegalParameterException extends LocalizedException
{
	private static final long serialVersionUID = -7278614920416619013L;
	private final String[] m_args;

	public IllegalParameterException(String extensionPointId, String id, String parameterName)
	{
		super("Parameter {0} is illegal for id {1} (extension point {2})");
		m_args = new String[] { parameterName, id, extensionPointId };
		assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return m_args;
	}

}
