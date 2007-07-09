/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.query.parser;

import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.NamedElement;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class ComponentRequestHandler extends ExtensionAwareHandler
{
	public static final String TAG = ComponentRequest.TAG;

	private ComponentRequest m_componentRequest;

	public ComponentRequestHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs)
	throws SAXException
	{
		try
		{
			m_componentRequest = new ComponentRequest(
				this.getStringValue(attrs, NamedElement.ATTR_NAME),
				getComponentType(attrs),
				getOptionalStringValue(attrs, ComponentRequest.ATTR_VERSION_DESIGNATOR),
				getOptionalStringValue(attrs, ComponentRequest.ATTR_VERSION_TYPE));
		}
		catch(CoreException e)
		{
			throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
		}
	}

	public ComponentRequest getComponentRequest()
	{
		return m_componentRequest;
	}
}
