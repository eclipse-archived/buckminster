/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.mspec.parser;

import java.net.URL;

import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class MaterializationSpecHandler extends MaterializationDirectiveHandler
{
	public static final String TAG = MaterializationSpec.TAG;
	private final URL m_contextURL;

	public MaterializationSpecHandler(AbstractHandler parent, URL contextURL)
	{
		super(parent, TAG, new MaterializationSpecBuilder());
		m_contextURL = contextURL;
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		ChildHandler ch;
		if(MaterializationNodeHandler.TAG.equals(localName))
			ch = new MaterializationNodeHandler(this, ((MaterializationSpecBuilder)getBuilder()).addNodeBuilder());
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		super.handleAttributes(attrs);
		MaterializationSpecBuilder builder = (MaterializationSpecBuilder)getBuilder();
		builder.setContextURL(m_contextURL);
		builder.setName(getStringValue(attrs, MaterializationSpec.ATTR_NAME));
		builder.setShortDesc(getOptionalStringValue(attrs, MaterializationSpec.ATTR_SHORT_DESC));
		builder.setURL(getStringValue(attrs, MaterializationSpec.ATTR_URL));
	}

	public MaterializationSpec getMaterializationSpec()
	{
		return new MaterializationSpec((MaterializationSpecBuilder)getBuilder());
	}
}
