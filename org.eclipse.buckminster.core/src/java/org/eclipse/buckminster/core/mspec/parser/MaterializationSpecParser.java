/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.mspec.parser;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.eclipse.buckminster.core.metadata.parser.MetaDataParser;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.parser.ParserFactory;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class MaterializationSpecParser extends MetaDataParser<MaterializationSpec> implements ChildPoppedListener {
	private URL contextURL;

	private MaterializationSpec materializationSpec;

	public MaterializationSpecParser(List<ParserFactory.ParserExtension> parserExtensions, boolean validating) throws CoreException {
		super(parserExtensions, validating);
	}

	public void childPopped(ChildHandler child) throws SAXException {
		materializationSpec = ((MaterializationSpecHandler) child).getMaterializationSpec();
	}

	public MaterializationSpec parse(String systemId, InputStream input) throws CoreException {
		try {
			contextURL = URLUtils.normalizeToURL(systemId);
		} catch (MalformedURLException e) {
			contextURL = null;
		}
		this.parseInput(systemId, input);
		return materializationSpec;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
		if (MaterializationSpec.TAG.equals(localName)) {
			MaterializationSpecHandler rmh = new MaterializationSpecHandler(this, contextURL);
			this.pushHandler(rmh, attrs);
		} else
			super.startElement(uri, localName, qName, attrs);
	}
}
