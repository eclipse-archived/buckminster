/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.parser;

import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.model.Artifact;
import org.eclipse.buckminster.core.cspec.parser.ArtifactHandler;
import org.eclipse.buckminster.core.cspecext.builder.AlterArtifactBuilder;
import org.eclipse.buckminster.core.cspecext.builder.AlterAttributeBuilder;
import org.eclipse.buckminster.core.cspecext.model.AlterArtifact;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.core.runtime.Path;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
class AlterArtifactHandler extends AlterAttributeHandler
{
	private final RemoveHandler m_removePathHandler = new RemoveHandler(this, AlterArtifact.ELEM_REMOVE_PATH,
			Artifact.ATTR_PATH);

	AlterArtifactHandler(AbstractHandler parent, ArtifactHandler baseHandler)
	{
		super(parent, baseHandler);
	}

	AlterArtifactHandler(AbstractHandler parent, boolean publ)
	{
		this(parent, new ArtifactHandler(parent, publ));
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXException
	{

		if(child == m_removePathHandler)
			((AlterArtifactBuilder)this.getBuilder()).addRemovedPath(Path.fromPortableString(m_removePathHandler
					.getValue()));
		else
			super.childPopped(child);
	}

	@Override
	AlterAttributeBuilder createAlterAttributeBuilder(AttributeBuilder baseBuilder)
	{
		return new AlterArtifactBuilder(baseBuilder);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		ChildHandler ch;
		if(m_removePathHandler.getTAG().equals(localName))
			ch = m_removePathHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}
}
