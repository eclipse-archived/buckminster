/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.sax;

/**
 * A key that uniqely identifies a handler in the handler cache.
 * 
 * @author Thomas Hallgren
 */
class HandlerKey implements Cloneable {
	static boolean nullEquals(String a, String b) {
		return (a == null) ? (b == null) : (b != null) && a.equals(b);
	}

	private String uri;

	private String localName;

	private String typeName;

	private int hash;

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (!(obj instanceof HandlerKey))
			return false;

		HandlerKey other = (HandlerKey) obj;
		return nullEquals(uri, other.uri) && nullEquals(typeName, other.typeName) && nullEquals(localName, other.localName);
	}

	@Override
	public int hashCode() {
		return hash;
	}

	void init(String newURI, String newLocalName, String newTypeName) {
		int newHash = (newLocalName == null) ? 1 : 31 + newLocalName.hashCode();
		if (newURI != null)
			newHash = 31 * newHash + newURI.hashCode();
		if (newTypeName != null)
			newHash = 31 * newHash + newTypeName.hashCode();

		uri = newURI;
		localName = newLocalName;
		typeName = newTypeName;
		hash = newHash;
	}
}
