/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.common.model;

import java.util.Map;

import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * An instance of this class will produce the resolved value of the constant
 * that it holds on to.
 * 
 * @author Thomas Hallgren
 */
public class Constant<T> extends ValueHolder<T> {
	public static final String TAG = "constant"; //$NON-NLS-1$

	public static final String ATTR_VALUE = "value"; //$NON-NLS-1$

	private final T value;

	public Constant(T value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		return super.equals(o) && Trivial.equalsAllowNull(value, ((Constant<?>) o).value);
	}

	public T getConstantValue() {
		return value;
	}

	public String getDefaultTag() {
		return TAG;
	}

	@Override
	public int hashCode() {
		int hc = super.hashCode();
		hc = 37 * hc + (value == null ? 0 : value.hashCode());
		return hc;
	}

	@Override
	public String toString() {
		return value == null ? null : value.toString();
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException {
		Utils.addAttribute(attrs, ATTR_VALUE, toString());
	}

	@Override
	protected T checkedGetValue(Map<String, ? extends Object> properties, int recursionGuard) {
		return ExpandingProperties.expand(properties, value, recursionGuard + 1);
	}
}
