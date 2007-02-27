/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.europatools.parser;

import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionSyntaxException;
import org.eclipse.buckminster.europatools.model.Feature;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
class FeatureHandler extends ExtensionAwareHandler
{
	private Feature m_feature;

	public FeatureHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		IVersionDesignator vd = null;
		String vdStr = getOptionalStringValue(attrs, Feature.ATTR_VERSION_DESIGNATOR);
		if(vdStr != null)
		{
			try
			{
				vd = VersionFactory.createDesignator(VersionFactory.OSGiType, vdStr);
			}
			catch(VersionSyntaxException e)
			{
				throw new SAXParseException(e.getMessage(), getDocumentLocator());
			}
		}
		m_feature = new Feature(getStringValue(attrs, Feature.ATTR_NAME), vd, getOptionalStringValue(attrs,
				Feature.ATTR_CATEGORY));
	}

	Feature getFeature()
	{
		return m_feature;
	}
}
