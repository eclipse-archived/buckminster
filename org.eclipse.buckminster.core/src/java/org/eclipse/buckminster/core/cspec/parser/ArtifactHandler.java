/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.parser;

import org.eclipse.buckminster.core.cspec.builder.ArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.TopLevelAttributeBuilder;
import org.eclipse.buckminster.core.cspec.model.Artifact;
import org.eclipse.buckminster.core.cspec.model.PathAlreadyDefinedException;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.core.runtime.Path;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class ArtifactHandler extends TopLevelAttributeHandler
{
	private final PathHandler m_pathHandler = new PathHandler(this);

	public ArtifactHandler(AbstractHandler parent, boolean publ)
	{
		super(parent, publ);
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child == m_pathHandler)
		{
			try
			{
				this.getArtifactBuilder().addPath(m_pathHandler.getPath());
			}
			catch(PathAlreadyDefinedException e)
			{
				throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
			}
		}
		else
			super.childPopped(child);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		ChildHandler ch;
		if(PathHandler.TAG.equals(localName))
			ch = m_pathHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		super.handleAttributes(attrs);
		ArtifactBuilder builder = this.getArtifactBuilder();
		String tmp = getOptionalStringValue(attrs, Artifact.ATTR_BASE);
		if(tmp != null)
			builder.setBase(Path.fromPortableString(tmp));

		String pathStr = getOptionalStringValue(attrs, Artifact.ATTR_PATH);
		if(pathStr != null)
			try
			{
				builder.addPath(Path.fromPortableString(pathStr));
			}
			catch(PathAlreadyDefinedException e)
			{
				throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
			}
	}

	@Override
	protected TopLevelAttributeBuilder createAttributeBuilder()
	{
		return getCSpecBuilder().createArtifactBuilder();
	}

	final ArtifactBuilder getArtifactBuilder()
	{
		return (ArtifactBuilder)this.getBuilder();
	}
}
