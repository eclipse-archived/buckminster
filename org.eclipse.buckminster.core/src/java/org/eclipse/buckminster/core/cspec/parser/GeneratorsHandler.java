/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.parser;

import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.GeneratorBuilder;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.GeneratorAlreadyDefinedException;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
class GeneratorsHandler extends ExtensionAwareHandler implements ChildPoppedListener, ICSpecBuilderSupport {
	public static final String TAG = CSpec.ELEM_GENERATORS;

	private final GeneratorHandler generatorHandler = new GeneratorHandler(this);

	GeneratorsHandler(AbstractHandler parent) {
		super(parent);
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXException {
		if (child == generatorHandler)
			try {
				this.getCSpecBuilder().addGenerator((GeneratorBuilder) generatorHandler.getBuilder());
			} catch (GeneratorAlreadyDefinedException e) {
				throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
			}
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (GeneratorHandler.TAG.equals(localName))
			ch = generatorHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public CSpecBuilder getCSpecBuilder() {
		return ((ICSpecBuilderSupport) this.getParentHandler()).getCSpecBuilder();
	}
}
