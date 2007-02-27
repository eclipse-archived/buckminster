/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.europatools.model;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class Category implements ISaxableElement
{
	public static final String TAG = "category";
	public static final String DESCRIPTION_TAG = "description";
	public static final String ATTR_NAME = "name";
	public static final String ATTR_LABEL = "label";

	private final String m_label;
	private final String m_name;
	private final Documentation m_documentation;

	public Category(String name, String label, Documentation documentation)
	{
		m_name = name;
		m_label = label;
		m_documentation = documentation;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public Documentation getDocumentation()
	{
		return m_documentation;
	}

	public String getLabel()
	{
		return m_label;
	}

	public String getName()
	{
		return m_name;
	}

	public void toSax(ContentHandler receiver, String namespace, String prefix, String localName) throws SAXException
	{
		AttributesImpl attrs = new AttributesImpl();
		Utils.addAttribute(attrs, ATTR_NAME, m_name);
		if(m_label != null)
			Utils.addAttribute(attrs, ATTR_LABEL, m_label);
		String qName = Utils.makeQualifiedName(prefix, localName);
		receiver.startElement(namespace, localName, qName, attrs);
		if(m_documentation != null)
			m_documentation.toSax(receiver, namespace, prefix, DESCRIPTION_TAG);
		receiver.endElement(namespace, localName, qName);
	}
}
