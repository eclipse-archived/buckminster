/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.parser;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.parser.ActionHandler;
import org.eclipse.buckminster.core.cspecext.builder.AlterActionBuilder;
import org.eclipse.buckminster.core.cspecext.builder.AlterAttributeBuilder;
import org.eclipse.buckminster.core.cspecext.model.AlterAction;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
class AlterActionHandler extends AlterAttributeHandler
{
	private final AlterPropertiesHandler m_alterActorPropertiesHandler = new AlterPropertiesHandler(this,
			AlterAction.ELEM_ALTER_ACTOR_PROPERTIES)
	{
		@Override
		protected void addRemovedProperty(String key) throws SAXException
		{
			getAlterActionBuilder().addRemoveActorProperty(key);
		}

		@Override
		public ExpandingProperties getProperties()
		{
			return getAlterActionBuilder().getAlterActorProperties();
		}
	};

	private final AlterPropertiesHandler m_alterPropertiesHandler = new AlterPropertiesHandler(this,
			AlterAction.ELEM_ALTER_PROPERTIES)
	{
		@Override
		protected void addRemovedProperty(String key) throws SAXException
		{
			getAlterActionBuilder().addRemoveProperty(key);
		}

		@Override
		public ExpandingProperties getProperties()
		{
			return getAlterActionBuilder().getAlterProperties();
		}
	};

	private final AlterPrerequisitesHandler m_alterPrerequisitesHandler = new AlterPrerequisitesHandler(this);

	private final AlterProductsHandler m_alterProductsHandler = new AlterProductsHandler(this);

	AlterActionHandler(AbstractHandler parent, boolean publ)
	{
		super(parent, new ActionHandler(parent, publ));
	}

	@Override
	AlterAttributeBuilder createAlterAttributeBuilder(AttributeBuilder baseBuilder)
	{
		return new AlterActionBuilder((ActionBuilder)baseBuilder);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		ChildHandler ch;
		if(m_alterActorPropertiesHandler.getTAG().equals(localName))
			ch = m_alterActorPropertiesHandler;
		else if(m_alterPropertiesHandler.getTAG().equals(localName))
			ch = m_alterPropertiesHandler;
		else if(AlterPrerequisitesHandler.TAG.equals(localName))
			ch = m_alterPrerequisitesHandler;
		else if(AlterProductsHandler.TAG.equals(localName))
			ch = m_alterProductsHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	public ActionBuilder getActionBuilder()
	{
		return (ActionBuilder)this.getBaseHandler().getAttributeBuilder();
	}

	AlterActionBuilder getAlterActionBuilder()
	{
		return (AlterActionBuilder)this.getBuilder();
	}
}
