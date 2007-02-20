/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.buckminster.ant.support;

import java.io.PrintStream;

import org.apache.tools.ant.BuildEvent;
import org.apache.tools.ant.DefaultLogger;

/**
 * @author ken1
 */
public class AntBuildLogger extends DefaultLogger
{
	StringBuilder m_msgBld = new StringBuilder();

	private Throwable m_buildResult;

	@Override
	public void buildStarted(BuildEvent event)
	{
		this.setEmacsMode(true);
		super.buildStarted(event);
	}

	@Override
	public void buildFinished(BuildEvent event)
	{
		m_buildResult = event.getException();
	}

	public Throwable getBuildResult()
	{
		return m_buildResult;
	}

	@Override
	protected synchronized void printMessage(final String message, final PrintStream stream, final int priority)
	{
		m_msgBld.setLength(0);
		m_msgBld.append("[ant] ");
		int top = message.length();
		for(int idx = 0; idx < top; ++idx)
		{
			char c = message.charAt(idx);
			m_msgBld.append(c);
			if(c == '\n')
				m_msgBld.append("[ant] ");
		}
		super.printMessage(m_msgBld.toString(), stream, priority);
	}
}
