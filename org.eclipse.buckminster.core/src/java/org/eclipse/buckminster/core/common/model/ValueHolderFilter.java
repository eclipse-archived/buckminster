/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.common.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.runtime.Trivial;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public abstract class ValueHolderFilter extends ValueHolder<String> {
	private ArrayList<ValueHolder<String>> valueHolders;

	/**
	 * Add a value holder that will become part of the source to this filter.
	 * 
	 * @param valueHolder
	 *            The holder to add.
	 */
	public void addValueHolder(ValueHolder<String> valueHolder) {
		if (valueHolders == null)
			valueHolders = new ArrayList<ValueHolder<String>>();
		valueHolders.add(valueHolder);
	}

	@Override
	public boolean equals(Object o) {
		return super.equals(o) && Trivial.equalsAllowNull(valueHolders, ((ValueHolderFilter) o).valueHolders);
	}

	@Override
	public int hashCode() {
		int hc = super.hashCode();
		hc = 37 * hc + (valueHolders == null ? 0 : valueHolders.hashCode());
		return hc;
	}

	protected List<String> checkedGetSourceValues(Map<String, ? extends Object> properties, int recursionGuard) {
		int top = (valueHolders == null) ? 0 : valueHolders.size();

		if (top == 0)
			return Collections.emptyList();
		else if (top == 1)
			return valueHolders.get(0).checkedGetValues(properties, recursionGuard);

		ArrayList<String> parameters = new ArrayList<String>(top);
		for (int idx = 0; idx < top; ++idx) {
			ValueHolder<String> valueHolder = valueHolders.get(idx);
			if (valueHolder.isMultiValueProducer()) {
				List<String> vs = valueHolder.checkedGetValues(properties, recursionGuard);
				for (int vidx = 0; vidx < vs.size(); ++vidx)
					parameters.add(vs.get(vidx));
			} else
				parameters.add(valueHolder.checkedGetValue(properties, recursionGuard));
		}
		return parameters;
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException {
		if (valueHolders != null) {
			for (ValueHolder<String> vh : valueHolders)
				vh.toSax(handler, namespace, prefix, vh.getDefaultTag());
		}
	}

	@Override
	protected String getElementNamespace(String namespace) {
		return XMLConstants.BM_COMMON_NS;
	}

	@Override
	protected String getElementPrefix(String prefix) {
		return XMLConstants.BM_COMMON_PREFIX;
	}

	String checkedGetSourceValue(Map<String, ? extends Object> properties, int recursionGuard) {
		int top = (valueHolders == null) ? 0 : valueHolders.size();

		if (top == 0)
			return NO_VALUE;
		else if (top == 1)
			return valueHolders.get(0).checkedGetValue(properties, recursionGuard);

		StringBuilder bld = new StringBuilder();
		for (int idx = 0; idx < top; ++idx) {
			ValueHolder<String> valueHolder = valueHolders.get(idx);
			if (valueHolder.isMultiValueProducer()) {
				List<String> vs = valueHolder.checkedGetValues(properties, recursionGuard);
				for (int vidx = 0; vidx < vs.size(); ++vidx)
					bld.append(vs.get(vidx));
			} else
				bld.append(valueHolder.checkedGetValue(properties, recursionGuard));
		}
		return bld.toString();
	}
}
