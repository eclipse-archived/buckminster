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

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * Functionality common to <code>Split</code> and <code>GroupSplit</code>.
 * 
 * @author Thomas Hallgren
 */
public abstract class AbstractSplit extends ValueHolderFilter {
	public static final String TAG = "split"; //$NON-NLS-1$

	public static final String ATTR_STYLE = "style"; //$NON-NLS-1$

	public static final String ATTR_PATTERN = "pattern"; //$NON-NLS-1$

	private final Pattern pattern;

	AbstractSplit(String splitter) {
		pattern = Pattern.compile(splitter);
	}

	@Override
	public boolean equals(Object o) {
		return super.equals(o) && pattern.equals(((AbstractSplit) o).pattern);
	}

	@Override
	public String getDefaultTag() {
		return TAG;
	}

	@Override
	public int hashCode() {
		int hc = super.hashCode();
		hc = 37 * hc + pattern.hashCode();
		return hc;
	}

	/**
	 * This method will always return <code>true</code> since a split may
	 * produce more then one value.
	 * 
	 * @return <code>true</code>, always.
	 */
	@Override
	public boolean isMultiValueProducer() {
		return true;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException {
		Utils.addAttribute(attrs, ATTR_PATTERN, pattern.toString());
	}

	@Override
	protected String checkedGetValue(Map<String, ? extends Object> properties, int recursionGuard) {
		List<String> values = checkedGetValues(properties, recursionGuard);
		int top = values.size();
		if (top == 0)
			return NO_VALUE;

		if (top == 1)
			return values.get(0);

		StringBuilder bld = new StringBuilder();
		for (int idx = 0; idx < top; ++idx)
			bld.append(values.get(idx));
		return bld.toString();
	}

	final Pattern getPattern() {
		return pattern;
	}
}
