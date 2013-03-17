/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.model.common.util;

/**
 * @author Thomas Hallgren
 */
public class MissingVersionTypeException extends RuntimeException {
	private static final long serialVersionUID = 2871945079212872308L;

	public MissingVersionTypeException(String versionTypeId) {
		super(versionTypeId);
	}
}
