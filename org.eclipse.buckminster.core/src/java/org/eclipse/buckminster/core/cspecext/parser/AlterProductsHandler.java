/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.parser;

import org.eclipse.buckminster.core.cspec.builder.ActionArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.model.AttributeAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.parser.ProductsHandler;
import org.eclipse.buckminster.core.cspecext.builder.AlterActionArtifactBuilder;
import org.eclipse.buckminster.core.cspecext.builder.AlterActionBuilder;
import org.eclipse.buckminster.core.cspecext.builder.AlterAttributeBuilder;
import org.eclipse.buckminster.core.cspecext.model.AlterAction;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.core.runtime.Path;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
class AlterProductsHandler extends AlterHandler
{
	public static final String TAG = AlterAction.ELEM_ALTER_PRODUCTS;

	class AlterActionArtifactHandler extends AlterArtifactHandler
	{
		AlterActionArtifactHandler(AbstractHandler parent, boolean publ)
		{
			super(parent, new ProductsHandler.ProductArtifactHandler(parent, publ));
		}

		@Override
		AlterAttributeBuilder createAlterAttributeBuilder(AttributeBuilder baseBuilder)
		{
			return new AlterActionArtifactBuilder((ActionArtifactBuilder)baseBuilder);
		}
	}
	private final RemoveHandler m_removeProductPathHandler = new RemoveHandler(this, "removeProductPath", "path");
	private final RemoveHandler m_removeAttributeHandler = new RemoveHandler(this, "removeAttribute", "name");
	private final AlterArtifactHandler m_publicAlterArtifactHandler = new AlterActionArtifactHandler(this, true);
	private final AlterArtifactHandler m_privateAlterArtifactHandler = new AlterActionArtifactHandler(this, false);

	AlterProductsHandler(AbstractHandler parent)
	{
		super(parent);
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child == m_removeProductPathHandler)
			this.getAlterActionBuilder().addRemoveProductPath(Path.fromPortableString(m_removeProductPathHandler.getValue()));
		else if(child == m_removeAttributeHandler)
			this.getAlterCSpecBuilder().addRemoveAttribute(m_removeAttributeHandler.getValue());
		else if(child instanceof AlterActionArtifactHandler)
			try
			{
				this.getAlterCSpecBuilder().addAlterAttribute(((AlterActionArtifactHandler)child).getBuilder());
			}
			catch(AttributeAlreadyDefinedException e)
			{
				throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
			}
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		ChildHandler ch;
		if(m_removeProductPathHandler.getTAG().equals(localName))
			ch = m_removeProductPathHandler;
		else if(m_removeAttributeHandler.getTAG().equals(localName))
			ch = m_removeAttributeHandler;
		else if(m_publicAlterArtifactHandler.getTAG().equals(localName))
			ch = m_publicAlterArtifactHandler;
		else if(m_privateAlterArtifactHandler.getTAG().equals(localName))
			ch = m_privateAlterArtifactHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	AlterActionBuilder getAlterActionBuilder()
	{
		return ((AlterActionHandler)this.getParentHandler()).getAlterActionBuilder();
	}
}
