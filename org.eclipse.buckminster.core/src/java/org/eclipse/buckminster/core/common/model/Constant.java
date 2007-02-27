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
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;


/**
 * An instance of this class will produce the resolved value of the constant
 * that it holds on to.
 * 
 * @author Thomas Hallgren
 */
public class Constant extends ValueHolder
{
	public static final String TAG = "constant";
	public static final String ATTR_VALUE = "value";

	private final String m_value;

	public Constant(String value)
	{
		m_value = value;
	}

	@Override
	public boolean equals(Object o)
	{
		return super.equals(o) && Trivial.equalsAllowNull(m_value, ((Constant)o).m_value);
	}

	@Override
	public int hashCode()
	{
		int hc = super.hashCode();
		hc = 37 * hc + (m_value == null ? 0 : m_value.hashCode());
		return hc;
	}

	@Override
	public String toString()
	{
		return m_value;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public void toSax(ContentHandler receiver, String namespace, String prefix, String localName)
	throws SAXException
	{
		AttributesImpl attrs = new AttributesImpl();
		Utils.addAttribute(attrs, ATTR_VALUE, m_value);
		String qName = Utils.makeQualifiedName(prefix, localName);
		receiver.startElement(namespace, localName, qName, attrs);
		receiver.endElement(namespace, localName, qName);
	}

	@Override
	protected String checkedGetValue(Map<String, String> properties, int recursionGuard)
	{
		return ExpandingProperties.expand(properties, m_value, recursionGuard + 1);
	}
}

