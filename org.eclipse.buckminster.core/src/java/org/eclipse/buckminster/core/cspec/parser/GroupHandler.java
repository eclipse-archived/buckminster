/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.parser;

import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.builder.PrerequisiteBuilder;
import org.eclipse.buckminster.core.cspec.builder.TopLevelAttributeBuilder;
import org.eclipse.buckminster.core.cspec.model.Group;
import org.eclipse.buckminster.core.cspec.model.PrerequisiteAlreadyDefinedException;
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
public class GroupHandler extends TopLevelAttributeHandler implements ChildPoppedListener
{
	private final PrerequisiteHandler m_prerequisiteHandler = new PrerequisiteHandler(this);

	public GroupHandler(AbstractHandler parent, boolean publ)
	{
		super(parent, publ);
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child == m_prerequisiteHandler)
		{
			try
			{
				((GroupBuilder)this.getBuilder()).addPrerequisite(
					(PrerequisiteBuilder)m_prerequisiteHandler.getBuilder());
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
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		ChildHandler ch;
		if(PrerequisiteHandler.TAG.equals(localName))
			ch = m_prerequisiteHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public void handleAttributes(Attributes attrs)
	throws SAXException
	{
		super.handleAttributes(attrs);
		String tmp = getOptionalStringValue(attrs, Group.ATTR_REBASE);
		if(tmp != null)
			this.getGroupBuilder().setPrerequisiteRebase(Path.fromPortableString(tmp));
	}

	final GroupBuilder getGroupBuilder()
	{
		return (GroupBuilder)this.getBuilder();
	}

	@Override
	protected TopLevelAttributeBuilder createAttributeBuilder()
	{
		return this.getCSpecBuilder().createGroupBuilder();
	}
}
