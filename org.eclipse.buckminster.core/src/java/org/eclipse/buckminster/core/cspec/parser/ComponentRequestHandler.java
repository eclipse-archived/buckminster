/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.parser;

import org.eclipse.buckminster.core.cspec.builder.ComponentRequestBuilder;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.NamedElement;
import org.eclipse.buckminster.core.helpers.FilterUtils;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.core.runtime.CoreException;
import org.osgi.framework.InvalidSyntaxException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class ComponentRequestHandler extends ExtensionAwareHandler
{
	public static final String TAG = ComponentRequest.TAG;

	private final ComponentRequestBuilder m_builder;

	public ComponentRequestHandler(AbstractHandler parent, ComponentRequestBuilder builder)
	{
		super(parent);
		m_builder = builder;
	}

	@Override
	public void handleAttributes(Attributes attrs)
	throws SAXException
	{
		m_builder.clear();
		m_builder.setName(getStringValue(attrs, NamedElement.ATTR_NAME));
		m_builder.setComponentTypeID(getComponentType(attrs));
		try
		{
			m_builder.setVersionDesignator(
				getOptionalStringValue(attrs, ComponentRequest.ATTR_VERSION_DESIGNATOR),
				getOptionalStringValue(attrs, ComponentRequest.ATTR_VERSION_TYPE));
		}
		catch(CoreException e)
		{
			throw new SAXParseException(e.getMessage(), getDocumentLocator());
		}
		String filter = getOptionalStringValue(attrs, ComponentRequest.ATTR_FILTER);
		if(filter != null)
		{
			try
			{
				m_builder.setFilter(FilterUtils.createFilter(filter));
			}
			catch(InvalidSyntaxException e)
			{
				throw new SAXParseException(e.getMessage(), getDocumentLocator());
			}
		}
	}

	public ComponentRequestBuilder getBuilder()
	{
		return m_builder;
	}
}
