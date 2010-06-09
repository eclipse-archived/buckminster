/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.parser;

import java.util.ArrayList;
import java.util.UUID;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.ResolvedNode;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.osgi.util.NLS;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
class ResolvedNodeHandler extends BomNodeHandler implements ChildPoppedListener {
	public static final String TAG = ResolvedNode.TAG;

	private UUID resolutionId;

	private final ArrayList<UUID> children = new ArrayList<UUID>();

	private final ElementRefHandler childHandler = new ElementRefHandler(this, ResolvedNode.CHILD_TAG);

	ResolvedNodeHandler(AbstractHandler parent) {
		super(parent);
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXParseException {
		if (child == childHandler)
			children.add(childHandler.getRefId());
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (childHandler.getTAG().equals(localName))
			ch = childHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		resolutionId = UUID.fromString(this.getStringValue(attrs, ResolvedNode.ATTR_RESOLUTION_ID));
		children.clear();
	}

	@Override
	BOMNode getDepNode() throws SAXException {
		ArrayList<BOMNode> childNodes = new ArrayList<BOMNode>(children.size());
		for (UUID childId : children)
			childNodes.add((BOMNode) getWrapped(childId));

		try {
			return new ResolvedNode((Resolution) getWrapped(resolutionId), childNodes);
		} catch (ClassCastException e) {
			throw new SAXParseException(NLS.bind(Messages.Wrapper_0_does_not_wrap_resolution, resolutionId), getDocumentLocator());
		}
	}
}
