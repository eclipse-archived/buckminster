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

import java.util.regex.Matcher;

import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;


/**
 * The Split class will perform a regular expression based split on its source.
 * The pattern can be quoted in order to make the split function as a normal
 * (non expression based) split.
 * @author Thomas Hallgren
 */
public class GroupSplit extends AbstractSplit
{
	/**
	 * Create a <code>GroupSplit</code> that will split the value provided by
	 * a source using regexp group constructs in <code>pattern</code>.
	 * @param pattern The delimiting pattern
	 * @see java.util.regex.Pattern#matches(CharSequence) Pattern.matches
	 * @see java.util.regex.Pattern#quote(String) Pattern.quote
	 */
	public GroupSplit(String pattern)
	{
		super(pattern);
	}

	protected String[] checkedGetValues(IProperties properties, int recursionGuard)
	{
		String source = this.checkedGetSourceValue(properties, recursionGuard);
		Matcher m = this.getPattern().matcher(source);
		int nGroups = m.groupCount();
		String[] result = new String[nGroups];
		while(--nGroups >= 0)
			result[nGroups] = m.group(nGroups + 1);
		return result;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		super.addAttributes(attrs);
		Utils.addAttribute(attrs, ATTR_STYLE, "group");
	}
}

