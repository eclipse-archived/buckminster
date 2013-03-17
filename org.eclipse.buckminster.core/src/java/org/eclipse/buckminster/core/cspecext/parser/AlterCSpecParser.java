/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.parser;

import java.io.InputStream;
import java.util.List;

import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.cspecext.model.CSpecExtension;
import org.eclipse.buckminster.core.parser.AbstractParser;
import org.eclipse.buckminster.core.parser.ParserFactory;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class AlterCSpecParser extends AbstractParser<CSpecExtension> implements ChildPoppedListener {
	private CSpecExtension cSpecExtension;

	public AlterCSpecParser(List<ParserFactory.ParserExtension> parserExtensions, boolean validating) throws CoreException {
		super(parserExtensions, new String[] { XMLConstants.XHTML_NS, XMLConstants.XML_NS, XMLConstants.BM_COMMON_NS, XMLConstants.BM_CSPEC_NS },
				new String[] { XMLConstants.XHTML_RESOURCE, XMLConstants.XML_RESOURCE, XMLConstants.BM_COMMON_RESOURCE,
						XMLConstants.BM_CSPEC_RESOURCE }, validating);
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXException {
		cSpecExtension = ((AlterCSpecHandler) child).getCSpecExtension();
	}

	@Override
	public CSpecExtension parse(String systemId, InputStream input) throws CoreException {
		this.parseInput(systemId, input);
		return cSpecExtension;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
		if (AlterCSpecHandler.TAG.equals(localName)) {
			AlterCSpecHandler rmh = new AlterCSpecHandler(this);
			this.pushHandler(rmh, attrs);
		} else
			super.startElement(uri, localName, qName, attrs);
	}
}
