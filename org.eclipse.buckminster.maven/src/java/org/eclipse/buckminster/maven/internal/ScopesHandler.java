/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.maven.internal;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Matt Biggs
 */
class ScopesHandler extends ExtensionAwareHandler implements ChildPoppedListener {

	private ScopeHandler scopeEntryHandler;

	private TreeMap<String, Scope> scopes;

	public ScopesHandler(AbstractHandler parent) {
		super(parent);
	}

	@Override
	public void childPopped(ChildHandler child) {
		if (child instanceof ScopeHandler) {
			if (scopes == null)
				scopes = new TreeMap<String, Scope>();
			Scope scope = ((ScopeHandler) child).createEntry();
			scopes.put(scope.getName(), scope);
		}
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (localName.equals(Scope.TAG)) {
			if (scopeEntryHandler == null)
				scopeEntryHandler = new ScopeHandler(this);
			ch = scopeEntryHandler;
		} else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	public Map<String, Scope> getScopesAndClear() {
		Map<String, Scope> scopeMap = scopes;
		scopes = null;
		return (scopeMap == null) ? Collections.<String, Scope> emptyMap() : scopeMap;
	}
}
