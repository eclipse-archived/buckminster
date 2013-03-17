/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.parser;

import java.io.InputStream;
import java.util.List;

import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.parser.ParserFactory;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class BillOfMaterialsParser extends MetaDataParser<BillOfMaterials> {
	private BillOfMaterials resolution;

	public BillOfMaterialsParser(List<ParserFactory.ParserExtension> parserExtensions, boolean validating) throws CoreException {
		super(parserExtensions, validating);
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXException {
		resolution = (BillOfMaterials) ((BillOfMaterialsHandler) child).getDepNode();
	}

	@Override
	public BillOfMaterials parse(String systemID, InputStream input) throws CoreException {
		this.parseInput(systemID, input);
		return resolution;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
		if (BillOfMaterialsHandler.TAG.equals(localName) || "exportedBillOfMaterials".equals(localName)) //$NON-NLS-1$
			this.pushHandler(new BillOfMaterialsHandler(this), attrs);
		else
			super.startElement(uri, localName, qName, attrs);
	}
}
