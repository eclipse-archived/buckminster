/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
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
import org.eclipse.core.runtime.Path;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class MaterializationNodeHandler extends MaterializationDirectiveHandler
{
	public static final String TAG = MaterializationNode.TAG;

	private UnpackHandler m_unpackHandler;

	public MaterializationNodeHandler(AbstractHandler parent, MaterializationNodeBuilder builder)
	{
		super(parent, TAG, builder);
	}

	public MaterializationNodeBuilder getMaterializationNodeBuilder()
	{
		return (MaterializationNodeBuilder)getBuilder();
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		super.handleAttributes(attrs);
		MaterializationNodeBuilder builder = getMaterializationNodeBuilder();
		builder.setNamePattern(getPatternValue(attrs, MaterializationNode.ATTR_NAME_PATTERN));
		builder.setComponentTypeID(getComponentType(attrs));
		builder.setExclude(getOptionalBooleanValue(attrs, MaterializationNode.ATTR_EXCLUDE, false));
		String tmp = getOptionalStringValue(attrs, MaterializationNode.ATTR_LEAF_ARTIFACT);
		if(tmp != null)
			builder.setLeafArtifact(Path.fromPortableString(tmp));

		tmp = getOptionalStringValue(attrs, MaterializationNode.ATTR_RESOURCE_PATH);
		if(tmp != null)
			builder.setResourcePath(Path.fromPortableString(tmp));
		builder.setBindingNamePattern(getOptionalPatternValue(attrs, MaterializationNode.ATTR_BINDING_NAME_PATTERN));
		builder.setBindingNameReplacement(getOptionalStringValue(attrs, MaterializationNode.ATTR_BINDING_NAME_REPLACEMENT));
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		ChildHandler ch;
		if(UnpackHandler.TAG.equals(localName))
		{
			if(m_unpackHandler == null)
				m_unpackHandler = new UnpackHandler(this);
			ch = m_unpackHandler;
		}
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}
}
