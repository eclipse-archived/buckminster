/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.common.parser;

import org.eclipse.buckminster.core.common.model.RxPart;
import org.eclipse.buckminster.core.common.model.RxPattern;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class RxPatternHandler extends RxPartHandler {
	public static final String TAG = RxPattern.TAG;

	private String pattern;

	private String prefix;

	private String suffix;

	public RxPatternHandler(AbstractHandler parent) {
		super(parent);
	}

	@Override
	public RxPart createPart() {
		return new RxPattern(getName(), isOptional(), pattern, prefix, suffix);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		super.handleAttributes(attrs);
		pattern = getStringValue(attrs, RxPattern.ATTR_PATTERN);
		prefix = getOptionalStringValue(attrs, RxPattern.ATTR_PREFIX);
		suffix = getOptionalStringValue(attrs, RxPattern.ATTR_SUFFIX);
	}

	protected final String getPattern() {
		return pattern;
	}

	protected final String getPrefix() {
		return prefix;
	}

	protected final String getSuffix() {
		return suffix;
	}
}
