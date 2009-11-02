/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.parser;

import java.util.UUID;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.GeneratorNode;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.osgi.util.NLS;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
class GeneratorNodeHandler extends BomNodeHandler
{
	public static final String TAG = GeneratorNode.TAG;

	private GeneratorNode m_node;

	GeneratorNodeHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		UUID cspecId = null;
		try
		{
			cspecId = UUID.fromString(this.getStringValue(attrs, GeneratorNode.ATTR_DECLARING_CSPEC_ID));
			String component = getOptionalStringValue(attrs, GeneratorNode.ATTR_COMPONENT);
			String attribute = getStringValue(attrs, GeneratorNode.ATTR_ATTRIBUTE);
			String generates = getStringValue(attrs, GeneratorNode.ATTR_GENERATES);
			String generatesType = getOptionalStringValue(attrs, GeneratorNode.ATTR_GENERATES_TYPE);
			String tmp = getOptionalStringValue(attrs, GeneratorNode.ATTR_GENERATES_VERSION);
			Version generatesVersion = null;
			if(tmp != null)
			{
				try
				{
					generatesVersion = VersionHelper.parseVersion(tmp);
				}
				catch(IllegalArgumentException e)
				{
					throw new SAXParseException(e.getMessage(), getDocumentLocator());
				}
			}

			m_node = new GeneratorNode((CSpec)getWrapped(cspecId), component, attribute, new ComponentIdentifier(
					generates, generatesType, generatesVersion));
		}
		catch(ClassCastException e)
		{
			throw new SAXParseException(NLS.bind(Messages.Wrapper_0_does_not_wrap_cspec, cspecId), getDocumentLocator());
		}
	}

	@Override
	BOMNode getDepNode()
	{
		return m_node;
	}
}
