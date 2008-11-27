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

import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * The Split class will perform a regular expression based split on its source.
 * 
 * @author Thomas Hallgren
 */
public class Split extends AbstractSplit
{
	public static final String ATTR_LIMIT = "limit";

	private final int m_limit;

	/**
	 * Create a <code>Split</code> that will split the value provided by a source around instances of
	 * <code>splitter</code>.
	 * 
	 * @param pattern
	 *            The delimiting pattern
	 * @param limit
	 *            The result threshold
	 * @see java.util.regex.Pattern#split(CharSequence pattern, int limit)
	 */
	public Split(String splitter, int limit)
	{
		super(splitter);
		m_limit = limit;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		super.addAttributes(attrs);
		if(m_limit != 0)
			Utils.addAttribute(attrs, ATTR_LIMIT, Integer.toString(m_limit));
	}

	@Override
	protected String[] checkedGetValues(Map<String, String> properties, int recursionGuard)
	{
		String source = this.checkedGetSourceValue(properties, recursionGuard);
		return this.getPattern().split(source, m_limit);
	}

	@Override
	public boolean equals(Object o)
	{
		return super.equals(o) && m_limit == ((Split)o).m_limit;
	}

	@Override
	public int hashCode()
	{
		int hc = super.hashCode();
		hc = 37 * hc + m_limit;
		return hc;
	}
}
