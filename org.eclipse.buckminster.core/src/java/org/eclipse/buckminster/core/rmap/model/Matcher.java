/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.rmap.model;

import java.util.regex.Pattern;

import org.eclipse.buckminster.sax.AbstractSaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public abstract class Matcher extends AbstractSaxableElement {
	public static final String ATTR_PATTERN = "pattern"; //$NON-NLS-1$

	private final ResourceMap owner;

	private final Pattern pattern;

	public Matcher(ResourceMap owner, String pattern) {
		this.owner = owner;
		this.pattern = pattern == null ? null : Pattern.compile(pattern);
	}

	public final ResourceMap getOwner() {
		return owner;
	}

	public final Pattern getPattern() {
		return pattern;
	}

	public final boolean matches(String componentName) {
		return pattern == null || pattern.matcher(componentName).find();
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException {
		if (pattern != null)
			Utils.addAttribute(attrs, ATTR_PATTERN, pattern.toString());
	}
}
