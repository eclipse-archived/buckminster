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

import java.net.URL;
import java.util.Map;

import org.eclipse.buckminster.core.common.parser.DocumentationHandler;
import org.eclipse.buckminster.core.common.parser.PropertyManagerHandler;
import org.eclipse.buckminster.core.cspec.parser.ComponentRequestHandler;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class ComponentQueryHandler extends PropertyManagerHandler {
	private final URL contextURL;

	private DocumentationHandler documentationHandler;

	private AdvisorNodeHandler advisorNodeHandler;

	private final ComponentQueryBuilder builder = new ComponentQueryBuilder();

	public ComponentQueryHandler(AbstractHandler parent, URL contextURL) {
		super(parent, ComponentQuery.TAG);
		this.contextURL = contextURL;
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXException {
		if (child == advisorNodeHandler)
			builder.addAdvisorNode(advisorNodeHandler.getAdvisorNodeBuilder());
		else if (child == documentationHandler)
			builder.setDocumentation(documentationHandler.createDocumentation());
		else
			super.childPopped(child);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (ComponentQuery.ELEM_ROOT_REQUEST.equals(localName))
			ch = new ComponentRequestHandler(this, builder.getRootRequestBuilder());
		else if (AdvisorNodeHandler.TAG.equals(localName)) {
			if (advisorNodeHandler == null)
				advisorNodeHandler = new AdvisorNodeHandler(this);
			ch = advisorNodeHandler;
		} else if (DocumentationHandler.TAG.equals(localName)) {
			if (documentationHandler == null)
				documentationHandler = new DocumentationHandler(this);
			ch = documentationHandler;
		} else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	public ComponentQuery getComponentQuery() throws SAXException {
		return builder.createComponentQuery();
	}

	@Override
	public Map<String, String> getProperties() {
		return builder.getDeclaredProperties();
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		builder.clear();
		builder.setContextURL(contextURL);
		builder.setPropertiesURL(getOptionalStringValue(attrs, ComponentQuery.ATTR_PROPERTIES));
		builder.setResourceMapURL(getOptionalStringValue(attrs, ComponentQuery.ATTR_RESOURCE_MAP));
		builder.setShortDesc(getOptionalStringValue(attrs, ComponentQuery.ATTR_SHORT_DESC));
	}
}
