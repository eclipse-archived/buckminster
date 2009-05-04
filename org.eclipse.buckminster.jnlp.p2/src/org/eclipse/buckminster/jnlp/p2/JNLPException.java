/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.p2;

/**
 * @author Karel Brezina
 * 
 */
public class JNLPException extends RuntimeException
{
	private static final long serialVersionUID = 0L;

	private String m_errorCode;

	private boolean m_reportable;

	public JNLPException(String message, String errorCode)
	{
		this(message, errorCode, null, true);
	}

	public JNLPException(String message, String errorCode, Throwable cause)
	{
		this(message, errorCode, cause, true);
	}

	public JNLPException(String message, String errorCode, Throwable cause, boolean reportable)
	{
		super(message, cause);
		m_errorCode = errorCode;
		m_reportable = reportable;
	}

	public String getErrorCode()
	{
		return m_errorCode;
	}

	public boolean isReportable()
	{
		return m_reportable;
	}
}
