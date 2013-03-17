/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.parser;

import org.eclipse.buckminster.core.cspec.model.NamedElement;
import org.eclipse.buckminster.core.cspec.parser.ComponentRequestHandler;
import org.eclipse.buckminster.core.cspecext.builder.AlterCSpecBuilder;
import org.eclipse.buckminster.core.cspecext.model.CSpecExtension;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
class AlterDependenciesHandler extends AlterHandler {
	public static final String TAG = CSpecExtension.ELEM_ALTER_DEPENDENCIES;

	private final AlterDependencyHandler alterDependencyHandler = new AlterDependencyHandler(this);

	private final RemoveHandler removeDependencyHandler = new RemoveHandler(this, "remove", NamedElement.ATTR_NAME); //$NON-NLS-1$

	AlterDependenciesHandler(AbstractHandler parent) {
		super(parent);
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXException {
		AlterCSpecBuilder alterCSpec = ((AlterCSpecHandler) this.getParentHandler()).getAlterCSpecBuilder();
		if (child == alterDependencyHandler)
			alterCSpec.addAlterDependency(alterDependencyHandler.getBuilder());
		else if (child == removeDependencyHandler)
			alterCSpec.addRemoveDependency(removeDependencyHandler.getValue());
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (ComponentRequestHandler.TAG.equals(localName))
			ch = alterDependencyHandler;
		else if (removeDependencyHandler.getTAG().equals(localName))
			ch = removeDependencyHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}
}
