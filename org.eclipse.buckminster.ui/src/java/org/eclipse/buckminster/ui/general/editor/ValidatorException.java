/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.general.editor;

/**
 * @author Karel Brezina
 * 
 */
public class ValidatorException extends Exception
{
	private static final long serialVersionUID = -7184605046992342189L;

	private String m_message;

	public ValidatorException(String message)
	{
		m_message = message;
	}

	@Override
	public String getMessage()
	{
		return m_message;
	}

	@Override
	public String toString()
	{
		return m_message;
	}
}
