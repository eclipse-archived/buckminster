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
import org.eclipse.ant.internal.core.ant.InternalAntMessages;

/**
 * @author ken1
 */
public class AntBuildLogger extends DefaultLogger {
	StringBuilder msgBld = new StringBuilder();

	private Throwable buildResult;

	@Override
	public void buildFinished(BuildEvent event) {
		buildResult = event.getException();
	}

	@Override
	public void buildStarted(BuildEvent event) {
		this.setEmacsMode(true);
		super.buildStarted(event);
	}

	public Throwable getBuildResult() {
		return buildResult;
	}

	@Override
	protected synchronized void printMessage(final String message, final PrintStream stream, final int priority) {
		if(InternalAntMessages.InternalAntRunner_BUILD_SUCCESSFUL_1.equals(message))
			//
			// It's not enough to override the buildFinished method. The Eclipse
			// InternalAntRunner will still insist on writing this message. Well,
			// we don't want that printout after each and every task that we execute.
			//
			return;

		msgBld.setLength(0);
		msgBld.append("[ant] ");
		int top = message.length();
		for (int idx = 0; idx < top; ++idx) {
			char c = message.charAt(idx);
			msgBld.append(c);
			if (c == '\n')
				msgBld.append("[ant] ");
		}
		super.printMessage(msgBld.toString(), stream, priority);
	}
}
