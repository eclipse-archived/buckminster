/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.distroprovider.cloudsmith;

/**
 * @author Karel Brezina
 * 
 */
public class ProgressInfo implements IProgressInfo
{
	private String m_message;

	private int m_worked;

	private boolean m_isDone;

	public ProgressInfo()
	{
	}

	public ProgressInfo(String message, int worked, boolean isDone)
	{
		super();
		m_message = message;
		m_worked = worked;
		m_isDone = isDone;
	}

	public String getMessage()
	{
		return m_message;
	}

	public int getWorked()
	{
		return m_worked;
	}

	public boolean isDone()
	{
		return m_isDone;
	}

	public void setDone(boolean isDone)
	{
		m_isDone = isDone;
	}

	public void setMessage(String message)
	{
		m_message = message;
	}

	public void setWorked(int worked)
	{
		m_worked = worked;
	}

}
