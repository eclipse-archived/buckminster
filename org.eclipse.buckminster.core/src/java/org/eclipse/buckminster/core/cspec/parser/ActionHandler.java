/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.parser;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.common.parser.PropertyManagerHandler;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.core.runtime.IPath;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class ActionHandler extends AttributeHandler
{
	private final PropertyManagerHandler m_actorPropsHandler;

	private final PrerequisitesHandler m_prerequisitesHandler;

	private final ProductsHandler m_productsHandler;

	private final PropertyManagerHandler m_propsHandler;

	public ActionHandler(AbstractHandler parent, boolean publ)
	{
		super(parent, publ);
		m_actorPropsHandler = new PropertyManagerHandler(this, Action.ELEM_ACTOR_PROPERTIES)
		{
			@Override
			public ExpandingProperties getProperties()
			{
				return getActionBuilder().getActorProperties();
			}
		};
		m_prerequisitesHandler = new PrerequisitesHandler(this);
		m_productsHandler = new ProductsHandler(this);
		m_propsHandler = new PropertyManagerHandler(this, Action.ELEM_PROPERTIES)
		{
			@Override
			public ExpandingProperties getProperties()
			{
				return getActionBuilder().getProperties();
			}
		};
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		ChildHandler ch;
		if(m_actorPropsHandler.getTAG().equals(localName))
			ch = m_actorPropsHandler;
		else if(m_propsHandler.getTAG().equals(localName))
			ch = m_propsHandler;
		else if(PrerequisitesHandler.TAG.equals(localName))
			ch = m_prerequisitesHandler;
		else if(Action.ELEM_PRODUCTS.equals(localName))
			ch = m_productsHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}
	
	public ActionBuilder getActionBuilder()
	{
		return (ActionBuilder)this.getBuilder();
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		super.handleAttributes(attrs);
		ActionBuilder builder = this.getActionBuilder();
		builder.setActorName(getOptionalStringValue(attrs, Action.ATTR_ACTOR));
		builder.setAlways(getOptionalBooleanValue(attrs, Action.ATTR_ALWAYS, Action.ALWAYS_DEFAULT));
		builder.setEnabled(getOptionalBooleanValue(attrs, Action.ATTR_ENABLED, Action.ENABLED_DEFAULT));
		builder.setAssignConsoleSupport(getOptionalBooleanValue(attrs, Action.ATTR_ASSIGN_CONSOLE_SUPPORT,
				Action.ASSIGN_CONSOLE_SUPPORT_DEFAULT));
	}

	final void addProductPath(IPath path)
	{
		this.getActionBuilder().addProductPath(path);
	}

	final void setProductAlias(String productAlias)
	{
		this.getActionBuilder().setProductAlias(productAlias);
	}

	final void setProductBase(IPath productBase)
	{
		this.getActionBuilder().setProductBase(productBase);
	}

	@Override
	protected AttributeBuilder createAttributeBuilder()
	{
		return this.getCSpecBuilder().createActionBuilder();
	}
}
