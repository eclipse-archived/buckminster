/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.europatools.parser;

import java.util.List;

import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.europatools.model.Category;
import org.eclipse.buckminster.europatools.model.Feature;
import org.eclipse.buckminster.europatools.model.SiteContribution;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class SiteContributionHandler extends ExtensionAwareHandler implements ChildPoppedListener
{
	private CategoriesHandler m_categoriesHandler;
	private FeaturesHandler m_featuresHandler;
	private String m_name;
	private String m_source;
	private List<Category> m_categories;
	private List<Feature> m_features;

	public SiteContributionHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		ChildHandler ch;
		if(SiteContribution.TAG_CATEGORIES.equals(localName))
		{
			if(m_categoriesHandler == null)
				m_categoriesHandler = new CategoriesHandler(this);
			ch = m_categoriesHandler;
		}
		else if(SiteContribution.TAG_FEATURES.equals(localName))
		{
			if(m_featuresHandler == null)
				m_featuresHandler = new FeaturesHandler(this);
			ch = m_featuresHandler;
		}
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		m_name = getStringValue(attrs, SiteContribution.ATTR_NAME);
		m_source = getStringValue(attrs, SiteContribution.ATTR_SOURCE);
		m_categories = null;
		m_features = null;
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child == m_categoriesHandler)
			m_categories = m_categoriesHandler.getCategorysAndClear();
		else if(child == m_featuresHandler)
			m_features = m_featuresHandler.getFeaturesAndClear();
	}

	@Override
	public void endElement(String uri, String localName, String qName)
	throws SAXException
	{
		super.endElement(uri, localName, qName);
		((SiteContributionParser)this.getTopHandler()).setSiteContribution(
				new SiteContribution(m_name, m_source, m_features, m_categories));
	}
}
