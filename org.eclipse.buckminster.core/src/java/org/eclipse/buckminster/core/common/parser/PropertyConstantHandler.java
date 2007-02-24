/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.common.parser;

import java.util.Map;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.MissingRequiredAttributeException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public class PropertyConstantHandler extends PropertyHandler
{
	static final String TAG = "property";

	private String m_value;

	public PropertyConstantHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs)
	throws SAXException
	{
		super.handleAttributes(attrs);

		// Used to be 'getStringValue(...)', but that throws an exception 
		// if the value is empty string or just whitespace...
		//
		String qname = "value";
		m_value = attrs.getValue(qname);
		if(m_value == null)
			// considering the xsd, this should never happen...
			throw new MissingRequiredAttributeException(this.getTAG(), qname, this.getDocumentLocator());
	}

	@Override
	void addYourself(Map<String,String> props)
	{
		String key = getKey();
		props.put(key, m_value);
		if(props instanceof ExpandingProperties)
			((ExpandingProperties)props).setMutable(key, getMutable());
	}
}
