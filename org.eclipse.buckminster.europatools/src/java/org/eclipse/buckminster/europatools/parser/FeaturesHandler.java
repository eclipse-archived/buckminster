/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.europatools.parser;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.europatools.model.Feature;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

class FeaturesHandler extends ExtensionAwareHandler implements ChildPoppedListener
{
	private FeatureHandler m_featureHandler;
	private List<Feature> m_features;

	public FeaturesHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		ChildHandler ch;
		if(Feature.TAG.equals(localName))
		{
			if(m_featureHandler == null)
				m_featureHandler = new FeatureHandler(this);
			ch = m_featureHandler;
		}
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	public void childPopped(ChildHandler child)
	{
		if(child == m_featureHandler)
		{
			if(m_features == null)
				m_features = new ArrayList<Feature>();
			m_features.add(m_featureHandler.getFeature());
		}
	}

	public List<Feature> getFeaturesAndClear()
	{
		List<Feature> features = m_features;
		m_features = null;
		return features;
	}
}
