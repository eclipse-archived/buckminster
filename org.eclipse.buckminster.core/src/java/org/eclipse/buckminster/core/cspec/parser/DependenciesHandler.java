/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.parser;

import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.ComponentRequestBuilder;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
class DependenciesHandler extends ExtensionAwareHandler implements ChildPoppedListener, ICSpecBuilderSupport {
	public static final String TAG = CSpec.ELEM_DEPENDENCIES;

	DependenciesHandler(AbstractHandler parent) {
		super(parent);
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXException {
		if (child instanceof ComponentRequestHandler) {
			try {
				getCSpecBuilder().addDependency(((ComponentRequestHandler) child).getBuilder());
			} catch (CoreException e) {
				throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
			}
		}
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (CSpec.ELEM_DEPENDENCY.equals(localName))
			ch = new ComponentRequestHandler(this, new ComponentRequestBuilder());
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public CSpecBuilder getCSpecBuilder() {
		return ((ICSpecBuilderSupport) this.getParentHandler()).getCSpecBuilder();
	}
}
