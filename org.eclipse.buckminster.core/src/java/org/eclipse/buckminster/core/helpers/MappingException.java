/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.core.helpers;

/**
 * @author Filip Hrbek
 * 
 */
public final class MappingException extends Exception {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -7032473008202164630L;

	/**
	 * Creates an empty mapping exception without any message.
	 */
	public MappingException() {
		super();
	}

	/**
	 * Creates a mapping exception with specified message.
	 */
	public MappingException(String message) {
		super(message);
	}

	/**
	 * Creates a mapping exception with specified message and cause.
	 */
	public MappingException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Creates a mapping exception with specified cause.
	 */
	public MappingException(Throwable cause) {
		super(cause);
	}
}
