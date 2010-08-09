/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.parser;

import org.eclipse.buckminster.core.cspec.model.NamedElement;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.buckminster.osgi.filter.FilterFactory;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.core.runtime.CoreException;
import org.osgi.framework.InvalidSyntaxException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class ComponentRequestHandler extends ExtensionAwareHandler {
	public static final String TAG = "component"; //$NON-NLS-1$

	private final ComponentRequest builder;

	public ComponentRequestHandler(AbstractHandler parent) {
		this(parent, CommonFactory.eINSTANCE.createComponentRequest());
	}

	public ComponentRequestHandler(AbstractHandler parent, ComponentRequest builder) {
		super(parent);
		this.builder = builder;
	}

	public ComponentRequest getBuilder() {
		return builder;
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		builder.setFilter(null);
		builder.setId(getStringValue(attrs, NamedElement.ATTR_NAME));
		builder.setType(getOptionalStringValue(attrs, "componentType")); //$NON-NLS-1$
		try {
			builder.setRange(VersionHelper.parseVersionRangeAttributes(attrs));
		} catch (CoreException e) {
			throw new SAXParseException(e.getMessage(), getDocumentLocator());
		}
		String filter = getOptionalStringValue(attrs, "filter"); //$NON-NLS-1$
		if (filter == null)
			builder.setFilter(null);
		else {
			try {
				builder.setFilter(FilterFactory.newInstance(filter));
			} catch (InvalidSyntaxException e) {
				throw new SAXParseException(e.getMessage(), getDocumentLocator());
			}
		}
	}
}
