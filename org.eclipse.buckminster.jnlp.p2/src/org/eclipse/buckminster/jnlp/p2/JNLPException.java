/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.p2;

/**
 * @author kaja
 *
 */
public class JNLPException extends RuntimeException
{
	private static final long serialVersionUID = 0L;

	private String m_errorCode;
	
	public JNLPException(String message, String errorCode, Throwable cause)
	{
		super(message, cause);
		m_errorCode = errorCode;
	}
	
	public JNLPException(String message, String errorCode)
	{
		this(message, errorCode, null);
	}
	
	public String getErrorCode()
	{
		return m_errorCode;
	}
}
