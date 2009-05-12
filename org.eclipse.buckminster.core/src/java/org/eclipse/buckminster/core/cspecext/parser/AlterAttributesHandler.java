/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.parser;

import org.eclipse.buckminster.core.cspec.model.NamedElement;
import org.eclipse.buckminster.core.cspec.model.TopLevelAttribute;
import org.eclipse.buckminster.core.cspecext.builder.AlterCSpecBuilder;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
abstract class AlterAttributesHandler extends AlterHandler
{
	private final AlterAttributeHandler m_publicHandler;

	private final AlterAttributeHandler m_privateHandler;

	private final RemoveHandler m_removeHandler = new RemoveHandler(this, "remove", NamedElement.ATTR_NAME); //$NON-NLS-1$

	private final RenameHandler m_renameHandler = new RenameHandler(this);

	AlterAttributesHandler(AbstractHandler parent)
	{
		super(parent);
		m_publicHandler = this.createAttributeHandler(true);
		m_privateHandler = this.createAttributeHandler(false);
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		AlterCSpecBuilder alterCSpec = ((AlterCSpecHandler)getParentHandler()).getAlterCSpecBuilder();
		if(child == m_removeHandler)
			alterCSpec.addRemoveAttribute(m_removeHandler.getValue());
		else if(child == m_renameHandler)
			alterCSpec.addRenameAttribute(m_renameHandler.getOldName(), m_renameHandler.getNewName());
		else
			alterCSpec.addAlterAttribute(((AlterAttributeHandler)child).getBuilder());
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		ChildHandler ch;
		if(TopLevelAttribute.PUBLIC_TAG.equals(localName))
			ch = m_publicHandler;
		else if(TopLevelAttribute.PRIVATE_TAG.equals(localName))
			ch = m_privateHandler;
		else if(m_removeHandler.getTAG().equals(localName))
			ch = m_removeHandler;
		else if(m_renameHandler.getTAG().equals(localName))
			ch = m_renameHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	abstract AlterAttributeHandler createAttributeHandler(boolean publ);
}
