/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.maven.internal;

import org.eclipse.buckminster.sax.AbstractSaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Matt Biggs
 */
class Scope extends AbstractSaxableElement {
	public static final String TAG = "scope"; //$NON-NLS-1$

	public static final String ATTR_NAME = "name"; //$NON-NLS-1$

	public static final String ATTR_EXCLUDE = "exclude"; //$NON-NLS-1$

	private final String name;

	private final boolean exclude;

	public Scope(String name, boolean exclude) {
		this.name = name;
		this.exclude = exclude;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException {
		Utils.addAttribute(attrs, ATTR_NAME, name);
		Utils.addAttribute(attrs, ATTR_EXCLUDE, String.valueOf(exclude));
	}

	@Override
	public String getDefaultTag() {
		return ATTR_NAME;
	}

	public final String getName() {
		return name;
	}

	public final boolean isExcluded() {
		return exclude;
	}

	public boolean isMatchFor(String n, boolean e) {
		return name.equals(name) && exclude == e;
	}
}
