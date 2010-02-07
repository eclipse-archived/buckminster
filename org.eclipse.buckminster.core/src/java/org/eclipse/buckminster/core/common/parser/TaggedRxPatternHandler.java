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
import org.eclipse.buckminster.core.common.model.TaggedRxPattern;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class TaggedRxPatternHandler extends RxPatternHandler {
	private final String tag;

	public TaggedRxPatternHandler(AbstractHandler parent, String tag) {
		super(parent);
		this.tag = tag;
	}

	@Override
	public RxPart createPart() {
		return new TaggedRxPattern(tag, getName(), isOptional(), getPattern(), getPrefix(), getSuffix());
	}

	@Override
	public String getTAG() {
		return tag;
	}

	@Override
	protected String getNameAttributeValue(Attributes attrs) throws SAXException {
		return TaggedRxPattern.TAGGED_PREFIX + tag;
	}
}
