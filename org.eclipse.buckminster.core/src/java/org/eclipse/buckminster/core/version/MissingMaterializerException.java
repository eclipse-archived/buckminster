/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.version;

import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.buckminster.core.materializer.IMaterializer;

/**
 * @author Thomas Hallgren
 */
public class MissingMaterializerException extends LocalizedException
{
	private static final long serialVersionUID = -1363518852613277665L;
	private final String m_argument;

	public MissingMaterializerException(String matcherId)
	{
		super("No materializer with id {0} has been registered with extension-point {1}");
		m_argument = matcherId;
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return new String[] { m_argument, IMaterializer.MATERIALIZERS_POINT };
	}
}

