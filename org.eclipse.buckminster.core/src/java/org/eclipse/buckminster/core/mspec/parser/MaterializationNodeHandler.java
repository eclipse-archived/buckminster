/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.mspec.parser;

import java.util.regex.Pattern;

import org.eclipse.buckminster.core.mspec.builder.MaterializationDirectiveBuilder;
import org.eclipse.buckminster.core.mspec.builder.MaterializationNodeBuilder;
import org.eclipse.buckminster.core.mspec.model.MaterializationNode;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class MaterializationNodeHandler extends MaterializationDirectiveHandler
{
	public static final String TAG = MaterializationNode.TAG;

	public MaterializationNodeHandler(AbstractHandler parent)
	{
		super(parent, TAG);
	}

	public MaterializationNode getMaterializationNode()
	{
		return new MaterializationNode((MaterializationNodeBuilder)getBuilder());
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		super.handleAttributes(attrs);
		MaterializationNodeBuilder builder = (MaterializationNodeBuilder)getBuilder();
		builder.setNamePattern(Pattern.compile(this.getStringValue(attrs, MaterializationNode.ATTR_NAME_PATTERN)));
		builder.setCategory(getOptionalStringValue(attrs, MaterializationNode.ATTR_CATEGORY));
		builder.setExclude("true".equalsIgnoreCase(getOptionalStringValue(attrs, MaterializationNode.ATTR_EXCLUDE)));
	}

	@Override
	MaterializationDirectiveBuilder createBuilder()
	{
		return new MaterializationNodeBuilder();
	}
}
