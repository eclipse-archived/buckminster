/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.rmap.parser;

import java.util.ArrayList;

import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.core.rmap.model.BidirectionalTransformer;
import org.eclipse.buckminster.core.rmap.model.VersionConverterDesc;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.p2.metadata.IVersionFormat;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionFormatException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class VersionConverterHandler extends ExtensionAwareHandler implements ChildPoppedListener {
	static final String TAG = VersionConverterDesc.TAG;

	private String type;

	private IVersionFormat versionFormat;

	private final BidirectionalTransformerHandler transformerHandler = new BidirectionalTransformerHandler(this);

	private final ArrayList<BidirectionalTransformer> transformers = new ArrayList<BidirectionalTransformer>();

	public VersionConverterHandler(AbstractHandler parent) {
		super(parent);
	}

	public void childPopped(ChildHandler child) throws SAXException {
		if (child instanceof BidirectionalTransformerHandler)
			transformers.add(((BidirectionalTransformerHandler) child).getTransformer());
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (BidirectionalTransformerHandler.TAG.equals(localName))
			ch = transformerHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	public VersionConverterDesc getVersionConverter() {
		return new VersionConverterDesc(type, versionFormat, transformers.toArray(new BidirectionalTransformer[transformers.size()]));
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		type = this.getStringValue(attrs, VersionConverterDesc.ATTR_TYPE);
		String tmp = getOptionalStringValue(attrs, VersionConverterDesc.ATTR_VERSION_FORMAT);
		if (tmp != null) {
			try {
				versionFormat = Version.compile(tmp);
			} catch (VersionFormatException e) {
				throw new SAXParseException(e.getMessage(), getDocumentLocator(), e);
			}
		} else {
			tmp = getOptionalStringValue(attrs, VersionConverterDesc.ATTR_VERSION_TYPE);
			if (tmp == null)
				versionFormat = null;
			else
				try {
					versionFormat = VersionHelper.getVersionType(tmp).getFormat();
				} catch (CoreException e) {
					throw new SAXParseException(e.getMessage(), getDocumentLocator(), e);
				}
		}
		transformers.clear();
	}
}
