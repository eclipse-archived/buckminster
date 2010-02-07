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

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.parser.AbstractParser;
import org.eclipse.buckminster.core.parser.ParserFactory;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class ComponentQueryParser extends AbstractParser<ComponentQuery> implements ChildPoppedListener {
	private ComponentQuery componentQuery;

	private URL contextURL;

	public ComponentQueryParser(List<ParserFactory.ParserExtension> parserExtensions, boolean validating) throws CoreException {
		super(parserExtensions, new String[] { XMLConstants.XHTML_NS, XMLConstants.XML_NS, XMLConstants.BM_COMMON_NS, XMLConstants.BM_CSPEC_NS,
				XMLConstants.BM_CQUERY_NS }, new String[] { XMLConstants.XHTML_RESOURCE, XMLConstants.XML_RESOURCE, XMLConstants.BM_COMMON_RESOURCE,
				XMLConstants.BM_CSPEC_RESOURCE, XMLConstants.BM_CQUERY_RESOURCE }, validating);
	}

	public void childPopped(ChildHandler child) throws SAXException {
		componentQuery = ((ComponentQueryHandler) child).getComponentQuery();
	}

	public ComponentQuery parse(String systemId, InputStream input) throws CoreException {
		try {
			contextURL = URLUtils.normalizeToURL(systemId);
		} catch (MalformedURLException e) {
			contextURL = null;
		}
		this.parseInput(systemId, input);
		return componentQuery;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
		if (ComponentQuery.TAG.equals(localName)) {
			ComponentQueryHandler rmh = new ComponentQueryHandler(this, contextURL);
			this.pushHandler(rmh, attrs);
		} else
			super.startElement(uri, localName, qName, attrs);
	}
}
