/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import org.eclipse.buckminster.sax.AbstractSaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public abstract class NamedElement extends AbstractSaxableElement {
	public static final String ATTR_NAME = "name"; //$NON-NLS-1$

	private final String name;

	protected NamedElement(NamedElement source) {
		name = source.getName();
	}

	protected NamedElement(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getNameAttributeName() {
		return ATTR_NAME;
	}

	@Override
	public String toString() {
		return name == null ? "null" //$NON-NLS-1$
				: name;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) {
		if (name != null)
			Utils.addAttribute(attrs, this.getNameAttributeName(), name);
	}
}
