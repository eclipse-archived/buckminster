/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.parser;

import org.eclipse.buckminster.core.cspec.builder.ActionArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.parser.ProductsHandler;
import org.eclipse.buckminster.core.cspecext.builder.AlterActionArtifactBuilder;
import org.eclipse.buckminster.core.cspecext.builder.AlterActionBuilder;
import org.eclipse.buckminster.core.cspecext.builder.AlterAttributeBuilder;
import org.eclipse.buckminster.core.cspecext.model.AlterAction;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.core.runtime.Path;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
class AlterProductsHandler extends AlterHandler {
	class AlterActionArtifactHandler extends AlterArtifactHandler {
		AlterActionArtifactHandler(AbstractHandler parent, boolean publ) {
			super(parent, new ProductsHandler.ProductArtifactHandler(parent, publ));
		}

		@Override
		AlterAttributeBuilder createAlterAttributeBuilder(AttributeBuilder baseBuilder) {
			return new AlterActionArtifactBuilder((ActionArtifactBuilder) baseBuilder);
		}
	}

	public static final String TAG = AlterAction.ELEM_ALTER_PRODUCTS;

	private final RemoveHandler removeProductPathHandler = new RemoveHandler(this, "removeProductPath", "path"); //$NON-NLS-1$ //$NON-NLS-2$

	private final RemoveHandler removeAttributeHandler = new RemoveHandler(this, "removeAttribute", "name"); //$NON-NLS-1$ //$NON-NLS-2$

	private final AlterArtifactHandler publicAlterArtifactHandler = new AlterActionArtifactHandler(this, true);

	private final AlterArtifactHandler privateAlterArtifactHandler = new AlterActionArtifactHandler(this, false);

	AlterProductsHandler(AbstractHandler parent) {
		super(parent);
	}

	public void childPopped(ChildHandler child) throws SAXException {
		if (child == removeProductPathHandler)
			this.getAlterActionBuilder().addRemoveProductPath(Path.fromPortableString(removeProductPathHandler.getValue()));
		else if (child == removeAttributeHandler)
			this.getAlterCSpecBuilder().addRemoveAttribute(removeAttributeHandler.getValue());
		else if (child instanceof AlterActionArtifactHandler)
			this.getAlterCSpecBuilder().addAlterAttribute(((AlterActionArtifactHandler) child).getBuilder());
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (removeProductPathHandler.getTAG().equals(localName))
			ch = removeProductPathHandler;
		else if (removeAttributeHandler.getTAG().equals(localName))
			ch = removeAttributeHandler;
		else if (publicAlterArtifactHandler.getTAG().equals(localName))
			ch = publicAlterArtifactHandler;
		else if (privateAlterArtifactHandler.getTAG().equals(localName))
			ch = privateAlterArtifactHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	AlterActionBuilder getAlterActionBuilder() {
		return ((AlterActionHandler) this.getParentHandler()).getAlterActionBuilder();
	}
}
