/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.parser;

import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.builder.PrerequisiteBuilder;
import org.eclipse.buckminster.core.cspec.model.NamedElement;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.cspec.model.PrerequisiteAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.parser.AttributeHandler;
import org.eclipse.buckminster.core.cspec.parser.GroupHandler;
import org.eclipse.buckminster.core.cspec.parser.PrerequisiteHandler;
import org.eclipse.buckminster.core.cspecext.builder.AlterAttributeBuilder;
import org.eclipse.buckminster.core.cspecext.builder.AlterGroupBuilder;
import org.eclipse.buckminster.core.cspecext.model.AlterGroup;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
class AlterGroupHandler extends AlterAttributeHandler
{
	private final RemoveHandler m_removeHandler = new RemoveHandler(this, AlterGroup.ELEM_REMOVE,
			NamedElement.ATTR_NAME);

	private final PrerequisiteHandler m_alterHandler = new PrerequisiteHandler(this);

	AlterGroupHandler(AbstractHandler parent, AttributeHandler baseHandler)
	{
		super(parent, baseHandler);
	}

	AlterGroupHandler(AbstractHandler parent, boolean publ)
	{
		super(parent, new GroupHandler(parent, publ));
	}

	void addAlterPrerequisite(Prerequisite prereq) throws PrerequisiteAlreadyDefinedException
	{
		((AlterGroupBuilder)this.getBuilder()).addAlterPrerequisite(prereq);
	}

	void addRemovePrerequisite(String key)
	{
		((AlterGroupBuilder)this.getBuilder()).addRemovePrerequisite(key);
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child == m_removeHandler)
			this.addRemovePrerequisite(m_removeHandler.getValue());
		else if(child == m_alterHandler)
		{
			try
			{
				this.addAlterPrerequisite(((PrerequisiteBuilder)m_alterHandler.getBuilder()).createPrerequisite());
			}
			catch(PrerequisiteAlreadyDefinedException e)
			{
				throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
			}
		}
		else
			super.childPopped(child);
	}

	@Override
	AlterAttributeBuilder createAlterAttributeBuilder(AttributeBuilder baseBuilder)
	{
		return new AlterGroupBuilder((GroupBuilder)baseBuilder);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		ChildHandler ch;
		if(m_removeHandler.getTAG().equals(localName))
			ch = m_removeHandler;
		else if(AlterGroup.ELEM_ALTER_ATTRIBUTE.equals(localName))
			ch = m_alterHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}
}
