/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.rmap.model;

import org.eclipse.buckminster.core.helpers.LocalizedException;

/**
 * @author Thomas Hallgren
 */
public class TransformerMismatchException extends LocalizedException
{
	private static final long serialVersionUID = -4031798723194792558L;

	public TransformerMismatchException(BidirectionalTransformer invalid)
	{
		super("The substitution %s -> %s is not reversed by %s -> %s", invalid.getFromPattern().toString(), invalid
				.getFromReplacement(), invalid.getToPattern().toString(), invalid.getToReplacement());
	}
}
