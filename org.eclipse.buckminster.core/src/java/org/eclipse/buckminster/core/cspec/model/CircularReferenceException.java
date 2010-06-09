/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.util.List;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public class CircularReferenceException extends LocalizedException {
	private static final long serialVersionUID = 7698765446267065328L;

	private static String buildChain(List<String> attributeNames, String recursionStart) {
		StringBuilder bld = new StringBuilder();
		for (String attributeName : attributeNames) {
			bld.append(attributeName);
			bld.append(" -> "); //$NON-NLS-1$
		}
		bld.append(recursionStart);
		return bld.toString();
	}

	public CircularReferenceException(String componentName, List<String> attributeNames, String recursionStart) {
		super(NLS.bind(Messages.Component_0_has_an_internal_circular_attribute_reference_Attribute_chain_is_1, componentName, buildChain(
				attributeNames, recursionStart)));
	}
}
