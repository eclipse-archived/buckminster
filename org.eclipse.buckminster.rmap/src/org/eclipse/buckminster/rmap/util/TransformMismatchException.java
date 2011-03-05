/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.rmap.util;

import org.eclipse.buckminster.rmap.Transform;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public class TransformMismatchException extends RuntimeException {
	private static final long serialVersionUID = -4031798723194792558L;

	public TransformMismatchException(Transform invalid) {
		super(NLS.bind(
				"The substitution {0} {1} is not reversed by {2} {3}",
				new Object[] { invalid.getFromPattern().toString(), invalid.getFromReplacement(), invalid.getToPattern().toString(),
						invalid.getToReplacement() }));
	}
}
