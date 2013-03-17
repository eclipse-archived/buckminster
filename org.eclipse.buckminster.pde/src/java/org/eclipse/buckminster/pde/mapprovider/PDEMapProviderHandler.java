/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.mapprovider;

import org.eclipse.buckminster.core.common.model.Replace;
import org.eclipse.buckminster.core.common.parser.ReplaceHandler;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.parser.ProviderHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class PDEMapProviderHandler extends ProviderHandler {

	ReplaceHandler replaceHandler;
	Replace replace;

	public PDEMapProviderHandler(AbstractHandler parent) {
		super(parent);
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXException {
		if (child instanceof ReplaceHandler)
			replace = ((Replace) ((ReplaceHandler) child).getValueHolder());
		else
			super.childPopped(child);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (localName.equals("replace")) { //$NON-NLS-1$
			if (replaceHandler == null)
				replaceHandler = new ReplaceHandler(this);
			ch = replaceHandler;
		} else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public Provider getProvider() {
		return new PDEMapProvider(getSearchPath(), getReaderType(), getComponentTypes(), getVersionConverter(), getUriFormat(),
				getResolutionFilter(), getProperties(), replace, getDocumentation());
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		super.handleAttributes(attrs);
		replace = null;
	}
}
