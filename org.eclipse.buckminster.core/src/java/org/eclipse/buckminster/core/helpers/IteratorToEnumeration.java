/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.helpers;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * @author Thomas Hallgren
 */
public class IteratorToEnumeration<V> implements Enumeration<V> {
	private final Iterator<V> wrappedIterator;

	public IteratorToEnumeration(Iterator<V> wrappedIterator) {
		this.wrappedIterator = wrappedIterator;
	}

	@Override
	public boolean hasMoreElements() {
		return wrappedIterator.hasNext();
	}

	@Override
	public V nextElement() {
		return wrappedIterator.next();
	}
}
