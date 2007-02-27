/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.europatools.model;

import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class Feature implements ISaxableElement
{
	public static final String TAG = "feature";
	public static final String ATTR_CATEGORY = "category";
	public static final String ATTR_NAME = "name";
	public static final String ATTR_VERSION_DESIGNATOR = "versionDesignator";

	private final String m_category;
	private final String m_name;
	private final IVersionDesignator m_versionDesignator;

	public Feature(String name, IVersionDesignator versionDesignator, String category)
	{
		m_name = name;
		m_versionDesignator = versionDesignator;
		m_category = category;
	}

	public String getCategory()
	{
		return m_category;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public String getName()
	{
		return m_name;
	}

	public IVersionDesignator getVersionDesignator()
	{
		return m_versionDesignator;
	}

	public void toSax(ContentHandler receiver, String namespace, String prefix, String localName) throws SAXException
	{
		AttributesImpl attrs = new AttributesImpl();
		Utils.addAttribute(attrs, ATTR_NAME, m_name);
		if(m_versionDesignator != null)
			Utils.addAttribute(attrs, ATTR_VERSION_DESIGNATOR, m_versionDesignator.toString());
		if(m_category != null)
			Utils.addAttribute(attrs, ATTR_CATEGORY, m_category);
		String qName = Utils.makeQualifiedName(prefix, localName);
		receiver.startElement(namespace, localName, qName, attrs);
		receiver.endElement(namespace, localName, qName);
	}
}
