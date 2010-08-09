/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.parser;

import java.util.ArrayList;

import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.parser.ComponentRequestHandler;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.UnresolvedNode;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
class UnresolvedNodeHandler extends BomNodeHandler implements ChildPoppedListener {
	public static final String TAG = UnresolvedNode.TAG;

	private ComponentRequest componentRequest;

	private final ComponentRequestHandler requestHandler = new ComponentRequestHandler(this);

	private ArrayList<String> attributes;

	private AttributeRefHandler attributeRefHandler;

	UnresolvedNodeHandler(AbstractHandler parent) {
		super(parent);
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXParseException {
		if (child == requestHandler)
			componentRequest = EcoreUtil.copy(requestHandler.getBuilder());
		else if (child == attributeRefHandler) {
			if (attributes == null)
				attributes = new ArrayList<String>();
			attributes.add(attributeRefHandler.getName());
		}
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (ComponentRequestHandler.TAG.equals(localName))
			ch = requestHandler;
		else if (AttributeRefHandler.TAG.equals(localName)) {
			if (attributeRefHandler == null)
				attributeRefHandler = new AttributeRefHandler(this);
			ch = attributeRefHandler;
		} else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		if (attributes != null)
			attributes.clear();
		componentRequest = null;
	}

	@Override
	BOMNode getDepNode() {
		return new UnresolvedNode(new QualifiedDependency(componentRequest, attributes));
	}
}
