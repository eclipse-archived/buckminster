/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.mspec.parser;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.buckminster.core.mspec.builder.MaterializationDirectiveBuilder;
import org.eclipse.buckminster.core.mspec.builder.MaterializationNodeBuilder;
import org.eclipse.buckminster.core.mspec.model.MaterializationNode;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.core.runtime.Path;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

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

	public MaterializationNodeBuilder getMaterializationNodeBuilder()
	{
		return (MaterializationNodeBuilder)getBuilder();
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		super.handleAttributes(attrs);
		MaterializationNodeBuilder builder = (MaterializationNodeBuilder)getBuilder();
		builder.setNamePattern(Pattern.compile(this.getStringValue(attrs, MaterializationNode.ATTR_NAME_PATTERN)));
		builder.setCategory(getOptionalStringValue(attrs, MaterializationNode.ATTR_CATEGORY));
		builder.setExclude("true".equalsIgnoreCase(getOptionalStringValue(attrs, MaterializationNode.ATTR_EXCLUDE)));
		String tmp = getOptionalStringValue(attrs, MaterializationNode.ATTR_RESOURCE_PATH);
		if(tmp != null)
			builder.setResourcePath(Path.fromPortableString(tmp));
		tmp = getOptionalStringValue(attrs, MaterializationNode.ATTR_BINDING_NAME_PATTERN);
		if(tmp != null)
		{
			try
			{
				builder.setBindingNamePattern(Pattern.compile(tmp));
			}
			catch(PatternSyntaxException e)
			{
				throw new SAXParseException("Attribute \"" + MaterializationNode.ATTR_BINDING_NAME_PATTERN + "\" is not a valid regexp", getDocumentLocator(), e);
			}
		}
		builder.setBindingNameReplacement(getOptionalStringValue(attrs, MaterializationNode.ATTR_BINDING_NAME_REPLACEMENT));
		builder.setUnpack(getOptionalBooleanValue(attrs, MaterializationNode.ATTR_UNPACK, false));
	}

	@Override
	MaterializationDirectiveBuilder createBuilder()
	{
		return new MaterializationNodeBuilder();
	}
}
