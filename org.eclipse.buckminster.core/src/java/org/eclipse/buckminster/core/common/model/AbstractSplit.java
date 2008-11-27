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
import java.util.regex.Pattern;

import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * Functionality common to <code>Split</code> and <code>GroupSplit</code>.
 * 
 * @author Thomas Hallgren
 */
public abstract class AbstractSplit extends ValueHolderFilter
{
	public static final String TAG = "split";

	public static final String ATTR_STYLE = "style";

	public static final String ATTR_PATTERN = "pattern";

	private final Pattern m_pattern;

	AbstractSplit(String splitter)
	{
		m_pattern = Pattern.compile(splitter);
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		Utils.addAttribute(attrs, ATTR_PATTERN, m_pattern.toString());
	}

	@Override
	protected String checkedGetValue(Map<String, String> properties, int recursionGuard)
	{
		String[] values = this.checkedGetValues(properties, recursionGuard);
		int top = values.length;
		if(top == 0)
			return NO_VALUE;

		if(top == 1)
			return values[0];

		StringBuilder bld = new StringBuilder();
		for(int idx = 0; idx < top; ++idx)
			bld.append(values[idx]);
		return bld.toString();
	}

	@Override
	public boolean equals(Object o)
	{
		return super.equals(o) && m_pattern.equals(((AbstractSplit)o).m_pattern);
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	final Pattern getPattern()
	{
		return m_pattern;
	}

	@Override
	public int hashCode()
	{
		int hc = super.hashCode();
		hc = 37 * hc + m_pattern.hashCode();
		return hc;
	}

	/**
	 * This method will always return <code>true</code> since a split may produce more then one value.
	 * 
	 * @return <code>true</code>, always.
	 */
	@Override
	public boolean isMultiValueProducer()
	{
		return true;
	}
}
