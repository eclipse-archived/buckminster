/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.mspec.parser;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.buckminster.core.mspec.builder.MaterializationDirectiveBuilder;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class MaterializationSpecHandler extends MaterializationDirectiveHandler
{
	public static final String TAG = MaterializationSpec.TAG;
	
	private MaterializationNodeHandler m_materializationNodeHandler;

	public MaterializationSpecHandler(AbstractHandler parent)
	{
		super(parent, TAG);
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child == m_materializationNodeHandler)
		{
			MaterializationSpecBuilder builder = (MaterializationSpecBuilder)getBuilder();
			builder.getNodes().add(m_materializationNodeHandler.getMaterializationNode());
		}
		else
			super.childPopped(child);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		ChildHandler ch;
		if(MaterializationNodeHandler.TAG.equals(localName))
		{
			if(m_materializationNodeHandler == null)
				m_materializationNodeHandler = new MaterializationNodeHandler(this);
			ch = m_materializationNodeHandler;
		}
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		super.handleAttributes(attrs);
		MaterializationSpecBuilder builder = (MaterializationSpecBuilder)getBuilder();
		builder.setShortDesc(getOptionalStringValue(attrs, MaterializationSpec.ATTR_SHORT_DESC));
		String url = this.getStringValue(attrs, MaterializationSpec.ATTR_URL);
		try
		{
			builder.setURL(new URL(url));
		}
		catch(MalformedURLException e)
		{
			throw new SAXParseException("Invalid URL", getDocumentLocator(), e);
		}
	}

	public MaterializationSpec getMaterializationSpec()
	{
		return new MaterializationSpec((MaterializationSpecBuilder)getBuilder());
	}

	@Override
	MaterializationDirectiveBuilder createBuilder()
	{
		return new MaterializationSpecBuilder();
	}
}
