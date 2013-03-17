/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.parser;

import org.eclipse.buckminster.core.cspec.builder.CSpecElementBuilder;
import org.eclipse.buckminster.core.cspec.builder.TopLevelAttributeBuilder;
import org.eclipse.buckminster.sax.AbstractHandler;

/**
 * @author Thomas Hallgren
 */
public abstract class TopLevelAttributeHandler extends AttributeHandler {
	private final boolean publ;

	public TopLevelAttributeHandler(AbstractHandler parent, boolean publ) {
		super(parent);
		this.publ = publ;
	}

	@Override
	public final TopLevelAttributeBuilder getAttributeBuilder() {
		return (TopLevelAttributeBuilder) getBuilder();
	}

	protected abstract TopLevelAttributeBuilder createAttributeBuilder();

	@Override
	protected CSpecElementBuilder createBuilder() {
		TopLevelAttributeBuilder bld = createAttributeBuilder();
		bld.setPublic(publ);
		return bld;
	}
}
