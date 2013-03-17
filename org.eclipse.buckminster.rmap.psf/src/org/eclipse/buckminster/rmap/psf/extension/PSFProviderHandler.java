/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.rmap.psf.extension;

import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.parser.ProviderHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class PSFProviderHandler extends ProviderHandler {
	private String psfFile;

	public PSFProviderHandler(AbstractHandler parent) {
		super(parent);
	}

	@Override
	public Provider getProvider() {
		return new PSFProvider(getSearchPath(), getReaderType(), getComponentTypes(), getVersionConverter(), getUriFormat(), getDigestFormat(),
				getDigestAlgorithm(), getResolutionFilter(), getProperties(), null, getDocumentation(), psfFile);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		super.handleAttributes(attrs);
		psfFile = getOptionalStringValue(attrs, PSFProvider.ATTR_PSF_FILE);
	}
}
