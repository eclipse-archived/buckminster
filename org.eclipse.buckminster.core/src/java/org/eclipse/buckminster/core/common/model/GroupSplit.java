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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * The Split class will perform a regular expression based split on its source.
 * The pattern can be quoted in order to make the split function as a normal
 * (non expression based) split.
 * 
 * @author Thomas Hallgren
 */
public class GroupSplit extends AbstractSplit {
	/**
	 * Create a <code>GroupSplit</code> that will split the value provided by a
	 * source using regexp group constructs in <code>pattern</code>.
	 * 
	 * @param pattern
	 *            The delimiting pattern
	 * @see java.util.regex.Pattern#matches(CharSequence) Pattern.matches
	 * @see java.util.regex.Pattern#quote(String) Pattern.quote
	 */
	public GroupSplit(String pattern) {
		super(pattern);
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException {
		super.addAttributes(attrs);
		Utils.addAttribute(attrs, ATTR_STYLE, "group"); //$NON-NLS-1$
	}

	@Override
	protected List<String> checkedGetValues(Map<String, ? extends Object> properties, int recursionGuard) {
		String source = checkedGetSourceValue(properties, recursionGuard);
		Matcher m = getPattern().matcher(source);
		int nGroups = m.groupCount();
		ArrayList<String> result = new ArrayList<String>(nGroups);
		for (int idx = 0; idx < nGroups; ++idx)
			result.add(m.group(nGroups + 1));
		return result;
	}
}
