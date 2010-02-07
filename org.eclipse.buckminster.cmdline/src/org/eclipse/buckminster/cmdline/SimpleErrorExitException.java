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
package org.eclipse.buckminster.cmdline;

/**
 * @author ken1
 * 
 *         Throw with a message and/or a exit code to quickly break out in a
 *         simple error condition. Specially treated in the Launcher, so only
 *         the message is printed.
 */
public class SimpleErrorExitException extends Exception {
	private static final long serialVersionUID = -2764770876554381163L;

	private final int exitValue;

	public SimpleErrorExitException() {
		this(Headless.EXIT_FAIL);
	}

	public SimpleErrorExitException(int exitValue) {
		this.exitValue = exitValue;
	}

	public SimpleErrorExitException(String errorMessage) {
		this(errorMessage, Headless.EXIT_FAIL, null);
	}

	public SimpleErrorExitException(String errorMessage, int exitValue, Throwable cause) {
		super(errorMessage, cause);
		this.exitValue = exitValue;
	}

	public SimpleErrorExitException(String errorMessage, Throwable cause) {
		this(errorMessage, Headless.EXIT_FAIL, cause);
	}

	public int getExitValue() {
		return exitValue;
	}
}
