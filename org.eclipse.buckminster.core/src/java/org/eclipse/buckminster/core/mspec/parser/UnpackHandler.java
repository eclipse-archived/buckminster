/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.mspec.parser;

import org.eclipse.buckminster.core.mspec.builder.MaterializationNodeBuilder;
import org.eclipse.buckminster.core.mspec.model.MaterializationNode;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class UnpackHandler extends ChildHandler {
	public static final String TAG = MaterializationNode.ELEM_UNPACK;

	public UnpackHandler(AbstractHandler parent) {
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		super.handleAttributes(attrs);
		MaterializationNodeBuilder builder = ((MaterializationNodeHandler) getParentHandler()).getMaterializationNodeBuilder();
		builder.setUnpack(true);
		builder.setExpand(getOptionalBooleanValue(attrs, MaterializationNode.ATTR_EXPAND, true));
		builder.setSuffix(getOptionalStringValue(attrs, MaterializationNode.ATTR_SUFFIX));
	}
}
