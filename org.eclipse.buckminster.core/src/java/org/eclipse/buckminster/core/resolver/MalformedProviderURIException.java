/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.core.resolver;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public class MalformedProviderURIException extends LocalizedException {
	private static final long serialVersionUID = -17898613113143719L;

	public MalformedProviderURIException(IReaderType readerType, String uri) {
		super(NLS.bind(Messages.A_reader_of_type_0_cannot_use_the_uri_1, readerType.getId(), uri));
	}
}
