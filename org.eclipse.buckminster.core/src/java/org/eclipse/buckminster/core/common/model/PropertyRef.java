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

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * An instance of this class represents a reference to a property. The instance will resolve to the expanded value of
 * that property.
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
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		Utils.addAttribute(attrs, ATTR_KEY, m_key);
	}

	@Override
	public String checkedGetValue(Map<String, String> properties, int recursionGuard)
	{
		String expandedKey = ExpandingProperties.expand(properties, m_key, recursionGuard + 1);
		if(properties instanceof ExpandingProperties)
			return ((ExpandingProperties)properties).getExpandedProperty(expandedKey, recursionGuard + 1);
		final String replacementValue = properties.get(expandedKey);
		if(replacementValue == null)
			CorePlugin.getLogger().warning("The property ${" + m_key + "} has not been set and will default to null");
		return ExpandingProperties.expand(properties, replacementValue, recursionGuard + 1);
	}

	@Override
	public boolean equals(Object o)
	{
		return super.equals(o) && Trivial.equalsAllowNull(m_key, ((PropertyRef)o).m_key);
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	@Override
	public int hashCode()
	{
		int hc = super.hashCode();
		hc = 37 * hc + (m_key == null
				? 0
				: m_key.hashCode());
		return hc;
	}
}
