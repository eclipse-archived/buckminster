/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.common.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.core.common.model.RxAssembly;
import org.eclipse.buckminster.core.common.model.RxGroup;
import org.eclipse.buckminster.core.common.model.RxPart;
import org.eclipse.buckminster.core.common.model.RxPattern;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class RxAssemblyHandler extends ExtensionAwareHandler implements ChildPoppedListener {
	static RxPartHandler getPartHandler(ExtensionAwareHandler parent, String localName, Map<String, RxPartHandler> handlerCache) {
		synchronized (handlerCache) {
			RxPartHandler ch = handlerCache.get(localName);
			if (ch != null)
				return ch;

			if (RxGroup.TAG.equals(localName))
				ch = new RxGroupHandler(parent);
			else if (RxPattern.TAG.equals(localName))
				ch = new RxPatternHandler(parent);
			else
				ch = new TaggedRxPatternHandler(parent, localName);

			handlerCache.put(localName, ch);
			return ch;
		}
	}

	private final HashMap<String, RxPartHandler> partHandlers = new HashMap<String, RxPartHandler>();

	private ArrayList<RxPart> parts;

	public RxAssemblyHandler(AbstractHandler parent) {
		super(parent);
	}

	public void childPopped(ChildHandler child) throws SAXException {
		if (child instanceof RxPartHandler) {
			if (parts == null)
				parts = new ArrayList<RxPart>();
			parts.add(((RxPartHandler) child).createPart());
		}
	}

	public RxAssembly createAssembly() throws SAXException {
		try {
			return new RxAssembly(parts);
		} catch (Exception e) {
			throw new SAXParseException(e.getMessage(), getDocumentLocator(), e);
		}
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		return getPartHandler(this, localName, partHandlers);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		parts = null;
	}

	protected ArrayList<RxPart> getParts() {
		return parts;
	}
}
