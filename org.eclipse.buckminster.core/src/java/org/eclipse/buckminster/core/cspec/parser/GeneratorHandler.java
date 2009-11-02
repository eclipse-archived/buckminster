/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.parser;

import org.eclipse.buckminster.core.cspec.builder.GeneratorBuilder;
import org.eclipse.buckminster.core.cspec.builder.NamedElementBuilder;
import org.eclipse.buckminster.core.cspec.model.Generator;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
class GeneratorHandler extends CSpecElementHandler
{
	public static final String TAG = Generator.TAG;

	GeneratorHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		super.handleAttributes(attrs);
		GeneratorBuilder builder = (GeneratorBuilder)getBuilder();
		builder.setComponent(getOptionalStringValue(attrs, Generator.ATTR_COMPONENT));
		builder.setAttribute(getStringValue(attrs, Generator.ATTR_ATTRIBUTE));
		builder.setGeneratesType(getOptionalStringValue(attrs, Generator.ATTR_GENERATES_TYPE));

		String version = getOptionalStringValue(attrs, Generator.ATTR_GENERATES_VERSION);
		if(version != null)
		{
			try
			{
				builder.setGeneratesVersion(VersionHelper.parseVersion(version));
			}
			catch(IllegalArgumentException e)
			{
				throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
			}
		}
	}

	@Override
	protected NamedElementBuilder createBuilder()
	{
		return this.getCSpecBuilder().createGeneratorBuilder();
	}

	@Override
	protected String getNameAttribute(Attributes attrs) throws SAXException
	{
		return this.getStringValue(attrs, Generator.ATTR_GENERATES);
	}
}
