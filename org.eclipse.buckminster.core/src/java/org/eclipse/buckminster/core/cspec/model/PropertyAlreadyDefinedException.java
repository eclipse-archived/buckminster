/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.osgi.util.NLS;

public class PropertyAlreadyDefinedException extends LocalizedException {
	private static final long serialVersionUID = 2174227050516251086L;

	public PropertyAlreadyDefinedException(String name, String attribute, String propertyCategory, String propertyName) {
		super(NLS.bind(Messages.CSpec_0_attribute_1_already_has_a_2_named_3, new Object[] { name, attribute, propertyCategory, propertyName }));
	}
}
