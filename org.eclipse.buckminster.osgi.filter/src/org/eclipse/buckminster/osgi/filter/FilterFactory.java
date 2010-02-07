/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text or
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.osgi.filter;

import org.eclipse.buckminster.osgi.filter.impl.Parser;
import org.osgi.framework.InvalidSyntaxException;

public class FilterFactory {
	/**
	 * Constructs a {@link Filter} object.
	 * 
	 * <p>
	 * If the filter cannot be parsed, an {@link InvalidSyntaxException} will be
	 * thrown with a human readable message where the filter became unparsable.
	 * 
	 * @param filterString
	 *            the filter string.
	 * @exception InvalidSyntaxException
	 *                If the filter parameter contains an invalid filter string
	 *                that cannot be parsed.
	 */
	public static Filter newInstance(String filterString) throws InvalidSyntaxException {
		return Parser.parse(filterString);
	}
}
