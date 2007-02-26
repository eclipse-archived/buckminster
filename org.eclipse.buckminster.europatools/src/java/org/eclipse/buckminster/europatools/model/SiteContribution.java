/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.europatools.model;

import java.util.List;

import org.eclipse.buckminster.core.metadata.model.UUIDKeyed;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class SiteContribution implements ISaxableElement
{
	public static final String TAG = "siteContribution";
	public static final String TAG_FEATURES = "features";
	public static final String TAG_CATEGORIES = "categories";
	public static final String ATTR_NAME = "name";
	public static final String ATTR_SOURCE = "source";

	private final String m_name;
	private final String m_source;
	private final List<Feature> m_features;
	private final List<Category> m_categories;

	public SiteContribution(String name, String source, List<Feature> features, List<Category> categories)
	{
		m_name = name;
		m_source = source;
		m_features = UUIDKeyed.createUnmodifiableList(features);
		m_categories = UUIDKeyed.createUnmodifiableList(categories);
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public String getName()
	{
		return m_name;
	}

	public String getSource()
	{
		return m_source;
	}

	public List<Category> getCategories()
	{
		return m_categories;
	}

	public List<Feature> getFeatures()
	{
		return m_features;
	}

	public void toSax(ContentHandler receiver, String namespace, String prefix, String localName) throws SAXException
	{
		AttributesImpl attrs = new AttributesImpl();
		Utils.addAttribute(attrs, ATTR_NAME, m_name);
		Utils.addAttribute(attrs, ATTR_SOURCE, m_source);
		String qName = Utils.makeQualifiedName(prefix, localName);
		receiver.startElement(namespace, localName, qName, attrs);
		Utils.emitCollection(namespace, prefix, TAG_FEATURES, null, m_features, receiver);
		Utils.emitCollection(namespace, prefix, TAG_CATEGORIES, null, m_categories, receiver);
		receiver.endElement(namespace, localName, qName);
	}
}
