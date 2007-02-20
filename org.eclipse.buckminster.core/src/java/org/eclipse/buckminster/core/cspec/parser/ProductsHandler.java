/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.parser;

import org.eclipse.buckminster.core.cspec.builder.ActionArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.Artifact;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.AttributeAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.core.runtime.Path;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


/**
 * @author Thomas Hallgren
 */
public class ProductsHandler extends ExtensionAwareHandler implements ChildPoppedListener, ICSpecBuilderSupport, IAttributeBuilderSupport
{
	public static final String TAG = Action.ELEM_PRODUCTS;

	public static class ProductArtifactHandler extends ArtifactHandler
	{
		public ProductArtifactHandler(AbstractHandler parent, boolean publ)
		{
			super(parent, publ);
		}

		@Override
		public void handleAttributes(Attributes attrs) throws SAXException
		{
			super.handleAttributes(attrs);
			((ActionArtifactBuilder)this.getBuilder()).setActionName(((ProductsHandler)this.getParentHandler()).getActionName());
		}

		@Override
		protected AttributeBuilder createAttributeBuilder()
		{
			return this.getCSpecBuilder().createActionArtifactBuilder();
		}
	}

	private final PathHandler m_pathHandler = new PathHandler(this);
	private final ProductArtifactHandler m_privateHandler = new ProductArtifactHandler(this, false);

	private final ProductArtifactHandler m_publicHandler = new ProductArtifactHandler(this, true);
	
	ProductsHandler(AbstractHandler parent)
	{
		super(parent);
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child == m_pathHandler)
			((ActionBuilder)this.getAttributeBuilder()).addProductPath(m_pathHandler.getPath());
		else if(child == m_publicHandler)
			this.addProductArtifact(m_publicHandler.getAttributeBuilder());
		else if(child == m_privateHandler)
			this.addProductArtifact(m_privateHandler.getAttributeBuilder());
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		ChildHandler ch = null;
		if(PathHandler.TAG.equals(localName))
			ch = m_pathHandler;
		else if(Attribute.PUBLIC_TAG.equals(localName))
			ch = m_publicHandler;
		else if(Attribute.PRIVATE_TAG.equals(localName))
			ch = m_privateHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		ActionHandler parent = (ActionHandler)this.getParentHandler();
		parent.setProductAlias(getOptionalStringValue(attrs, Prerequisite.ATTR_ALIAS));
		String tmp = getOptionalStringValue(attrs, Artifact.ATTR_BASE);
		if(tmp != null)
			parent.setProductBase(Path.fromPortableString(tmp));
	}

	final String getActionName()
	{
		return ((CSpecElementHandler)this.getParentHandler()).getBuilder().getName();
	}

	final void addProductArtifact(AttributeBuilder artifact) throws SAXException
	{
		try
		{
			this.getCSpecBuilder().addAttribute(artifact);
		}
		catch(AttributeAlreadyDefinedException e)
		{
			throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
		}
	}

	public AttributeBuilder getAttributeBuilder()
	{
		return ((IAttributeBuilderSupport)this.getParentHandler()).getAttributeBuilder();
	}

	public CSpecBuilder getCSpecBuilder()
	{
		return ((ICSpecBuilderSupport)this.getParentHandler()).getCSpecBuilder();
	}
}
