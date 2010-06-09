/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.build;

/**
 * @author kolwing
 * 
 */
public class LauncherDefinition {
	private final String pattern;

	private final String commandLine;

	public LauncherDefinition(String pattern, String commandLine) {
		this.pattern = pattern;
		this.commandLine = commandLine;
	}

	public String getCommandLine() {
		return commandLine;
	}

	public String getPattern() {
		return pattern;
	}
}
