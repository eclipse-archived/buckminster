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

import java.text.MessageFormat;
import java.util.Map;

import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;


/**
 * An instance of this class acts as a {@link java.text.MessageFormat} that
 * gets its values from a {@link CollectionHolder} instance.
 *
 * @author Thomas Hallgren
 */
public class Format extends ValueHolderFilter
{
	public static final String ATTR_FORMAT = "format";
	public static final String TAG = "format";

	private final String m_format;

	public Format(String pattern)
	{
		m_format = pattern;
	}

	@Override
	public boolean equals(Object o)
	{
		return super.equals(o) && m_format.equals(((Format)o).m_format);
	}

	@Override
	public int hashCode()
	{
		int hc = super.hashCode();
		hc = 37 * hc + m_format.hashCode();
		return hc;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs)
	throws SAXException
	{
		Utils.addAttribute(attrs, ATTR_FORMAT, m_format);
	}

	@Override
	protected String checkedGetValue(Map<String, String> properties, int recursionGuard)
	{
		String format = ExpandingProperties.expand(properties, m_format, recursionGuard + 1);
		MessageFormat messageFormat = new MessageFormat(format);
		return messageFormat.format(this.checkedGetSourceValues(properties, recursionGuard + 1));
	}

	public String getDefaultTag()
	{
		return TAG;
	}
}

