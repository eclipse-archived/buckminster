/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.europatools.parser;

import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.parser.CSpecHandler;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
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
	private final CSpecHandler m_cspecHandler = new CSpecHandler(this);
	private CSpec m_cspec;
	private String m_rmapProviderURL;

	public SiteContributionHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		ChildHandler ch;
		if(CSpec.TAG.equals(localName))
			ch = m_cspecHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		m_rmapProviderURL = getStringValue(attrs, SiteContribution.ATTR_RMAP_PROVIDER_URL);
		m_cspec = null;
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child == m_cspecHandler)
			m_cspec = m_cspecHandler.getCSpec();
	}

	@Override
	public void endElement(String uri, String localName, String qName)
	throws SAXException
	{
		super.endElement(uri, localName, qName);
		((SiteContributionParser)this.getTopHandler()).setSiteContribution(new SiteContribution(m_rmapProviderURL, m_cspec));
	}
}
