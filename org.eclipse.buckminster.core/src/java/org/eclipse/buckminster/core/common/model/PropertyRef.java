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

import java.util.Map;

import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;


/**
 * An instance of this class represents a reference to a property. The
 * instance will resolve to the expanded value of that property.
 * 
 * @author Thomas Hallgren
 */
public class PropertyRef extends ValueHolder
{
	public static final String TAG = "propertyRef";
	public static final String ATTR_KEY = "key";

	private final String m_key;

	public PropertyRef(String key)
	{
		m_key = key;
	}

	@Override
	public String checkedGetValue(Map<String, String> properties, int recursionGuard)
	{
		String expandedKey = ExpandingProperties.expand(properties, m_key, recursionGuard + 1);
		if(properties instanceof ExpandingProperties)
			return ((ExpandingProperties)properties).getExpandedProperty(expandedKey, recursionGuard + 1);

		return ExpandingProperties.expand(properties, properties.get(expandedKey), recursionGuard + 1);
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	@Override
	public boolean equals(Object o)
	{
		return super.equals(o) && Trivial.equalsAllowNull(m_key, ((PropertyRef)o).m_key);
	}

	@Override
	public int hashCode()
	{
		int hc = super.hashCode();
		hc = 37 * hc + (m_key == null ? 0 : m_key.hashCode());
		return hc;
	}

	public void toSax(ContentHandler receiver, String namespace, String prefix, String localName)
	throws SAXException
	{
		AttributesImpl attrs = new AttributesImpl();
		Utils.addAttribute(attrs, ATTR_KEY, m_key);
		String qName = Utils.makeQualifiedName(prefix, localName);
		receiver.startElement(namespace, localName, qName, attrs);
		receiver.endElement(namespace, localName, qName);
	}
}

