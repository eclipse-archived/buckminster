/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.parser;

import org.eclipse.buckminster.core.cspec.builder.DependencyBuilder;
import org.eclipse.buckminster.core.cspec.builder.NamedElementBuilder;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.Dependency;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.helpers.FilterUtils;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.core.runtime.CoreException;
import org.osgi.framework.InvalidSyntaxException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class DependencyHandler extends CSpecElementHandler
{
	public static final String TAG = Prerequisite.TAG;

	public DependencyHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs)
	throws SAXException
	{
		super.handleAttributes(attrs);
		DependencyBuilder builder = (DependencyBuilder)getBuilder();
		builder.setComponentTypeID(getComponentType(attrs));
		try
		{
			builder.setVersionDesignator(
				getOptionalStringValue(attrs, ComponentRequest.ATTR_VERSION_DESIGNATOR),
				getOptionalStringValue(attrs, ComponentRequest.ATTR_VERSION_TYPE));
		}
		catch(CoreException e)
		{
			throw new SAXParseException(e.getMessage(), getDocumentLocator());
		}
		String filter = getOptionalStringValue(attrs, Dependency.ATTR_FILTER);
		if(filter != null)
		{
			try
			{
				builder.setFilter(FilterUtils.createFilter(filter));
			}
			catch(InvalidSyntaxException e)
			{
				throw new SAXParseException(e.getMessage(), getDocumentLocator());
			}
		}
	}

	@Override
	protected NamedElementBuilder createBuilder()
	{
		return getCSpecBuilder().createDependencyBuilder();
	}

	public ComponentRequest createDependency()
	{
		return ((DependencyBuilder)this.getBuilder()).createDependency();
	}
}
