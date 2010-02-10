/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.parser;

import org.eclipse.buckminster.core.cspec.model.NamedElement;
import org.eclipse.buckminster.core.cspec.model.TopLevelAttribute;
import org.eclipse.buckminster.core.cspecext.builder.AlterCSpecBuilder;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
abstract class AlterAttributesHandler extends AlterHandler {
	private final AlterAttributeHandler publicHandler;

	private final AlterAttributeHandler privateHandler;

	private final RemoveHandler removeHandler = new RemoveHandler(this, "remove", NamedElement.ATTR_NAME); //$NON-NLS-1$

	private final RenameHandler renameHandler = new RenameHandler(this);

	AlterAttributesHandler(AbstractHandler parent) {
		super(parent);
		publicHandler = this.createAttributeHandler(true);
		privateHandler = this.createAttributeHandler(false);
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXException {
		AlterCSpecBuilder alterCSpec = ((AlterCSpecHandler) getParentHandler()).getAlterCSpecBuilder();
		if (child == removeHandler)
			alterCSpec.addRemoveAttribute(removeHandler.getValue());
		else if (child == renameHandler)
			alterCSpec.addRenameAttribute(renameHandler.getOldName(), renameHandler.getNewName());
		else
			alterCSpec.addAlterAttribute(((AlterAttributeHandler) child).getBuilder());
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (TopLevelAttribute.PUBLIC_TAG.equals(localName))
			ch = publicHandler;
		else if (TopLevelAttribute.PRIVATE_TAG.equals(localName))
			ch = privateHandler;
		else if (removeHandler.getTAG().equals(localName))
			ch = removeHandler;
		else if (renameHandler.getTAG().equals(localName))
			ch = renameHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	abstract AlterAttributeHandler createAttributeHandler(boolean publ);
}
