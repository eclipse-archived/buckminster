/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.parser;

import org.eclipse.buckminster.core.common.parser.PropertyManagerHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * The AlterPropertiesHandler makes it possible to alter or remove properties
 * from an existing property set. It must always be used in a conjunction with a
 * normal property handler that makes it possible to add properties.
 * 
 * @author Thomas Hallgren
 */
abstract class AlterPropertiesHandler extends PropertyManagerHandler {
	private final RemoveHandler removeHandler = new RemoveHandler(this, "remove", "key"); //$NON-NLS-1$ //$NON-NLS-2$

	AlterPropertiesHandler(AbstractHandler parent, String tag) {
		super(parent, tag);
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXException {
		if (child == removeHandler)
			addRemovedProperty(removeHandler.getValue());
		else
			super.childPopped(child);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (removeHandler.getTAG().equals(localName))
			ch = removeHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	protected abstract void addRemovedProperty(String key) throws SAXException;
}
