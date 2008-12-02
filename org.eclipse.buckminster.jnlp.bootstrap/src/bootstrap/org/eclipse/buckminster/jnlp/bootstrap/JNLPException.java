/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.bootstrap;

/**
 * @author kaja
 * 
 */
public class JNLPException extends Exception
{
	private static final long serialVersionUID = 0L;

	private String m_solution;

	private String m_errorCode;

	public JNLPException(String message, String solution, String errorCode)
	{
		this(message, solution, errorCode, null);
	}

	public JNLPException(String message, String solution, String errorCode, Throwable cause)
	{
		super(message, cause);
		m_solution = solution;
		m_errorCode = errorCode;
	}

	public String getErrorCode()
	{
		return m_errorCode;
	}

	public String getSolution()
	{
		return m_solution;
	}
}
