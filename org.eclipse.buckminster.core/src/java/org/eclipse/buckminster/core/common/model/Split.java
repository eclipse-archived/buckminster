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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * The Split class will perform a regular expression based split on its source.
 * 
 * @author Thomas Hallgren
 */
public class Split extends AbstractSplit {
	public static final String ATTR_LIMIT = "limit"; //$NON-NLS-1$

	private final int limit;

	/**
	 * Create a <code>Split</code> that will split the value provided by a
	 * source around instances of <code>splitter</code>.
	 * 
	 * @param pattern
	 *            The delimiting pattern
	 * @param limit
	 *            The result threshold
	 * @see java.util.regex.Pattern#split(CharSequence pattern, int limit)
	 */
	public Split(String splitter, int limit) {
		super(splitter);
		this.limit = limit;
	}

	@Override
	public boolean equals(Object o) {
		return super.equals(o) && limit == ((Split) o).limit;
	}

	@Override
	public int hashCode() {
		int hc = super.hashCode();
		hc = 37 * hc + limit;
		return hc;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException {
		super.addAttributes(attrs);
		if (limit != 0)
			Utils.addAttribute(attrs, ATTR_LIMIT, Integer.toString(limit));
	}

	@Override
	protected List<String> checkedGetValues(Map<String, ? extends Object> properties, int recursionGuard) {
		String source = checkedGetSourceValue(properties, recursionGuard);
		return source == null ? Collections.<String> emptyList() : Arrays.asList(getPattern().split(source, limit));
	}
}
