/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.p2.bootstrap;

/**
 * @author Karel Brezina
 * 
 */
public class JNLPException extends Exception
{
	private static final long serialVersionUID = 0L;

	private String m_solution;

	private String m_errorCode;

	private boolean m_reportable;

	public JNLPException(String message, String solution, String errorCode)
	{
		this(message, solution, errorCode, null);
	}

	public JNLPException(String message, String solution, String errorCode, boolean reportable)
	{
		this(message, solution, errorCode, null, reportable);
	}

	public JNLPException(String message, String solution, String errorCode, Throwable cause)
	{
		this(message, solution, errorCode, cause, true);
	}

	public JNLPException(String message, String solution, String errorCode, Throwable cause, boolean reportable)
	{
		super(message, cause);
		m_solution = solution;
		m_errorCode = errorCode;
		m_reportable = reportable;
	}

	public String getErrorCode()
	{
		return m_errorCode;
	}

	public String getProblem()
	{
		return getMessage();
	}

	public String getSolution()
	{
		return m_solution;
	}

	public boolean isReportable()
	{
		return m_reportable;
	}
}
