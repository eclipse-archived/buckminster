/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.europatools.model;

import org.eclipse.buckminster.core.cspec.model.CSpec;
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
	public static final String ATTR_RMAP_PROVIDER_URL = "rmapProviderURL";

	private final String m_rmapProviderURL;
	private final CSpec m_cspec;

	public SiteContribution(String rmapProviderURL, CSpec cspec)
	{
		m_rmapProviderURL = rmapProviderURL;
		m_cspec = cspec;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public CSpec getCSpec()
	{
		return m_cspec;
	}

	public String getRmapProviderURL()
	{
		return m_rmapProviderURL;
	}

	public void toSax(ContentHandler receiver, String namespace, String prefix, String localName) throws SAXException
	{
		AttributesImpl attrs = new AttributesImpl();
		Utils.addAttribute(attrs, ATTR_RMAP_PROVIDER_URL, m_rmapProviderURL);
		String qName = Utils.makeQualifiedName(prefix, localName);
		receiver.startElement(namespace, localName, qName, attrs);
		m_cspec.toSax(receiver, namespace, prefix, localName);
		receiver.endElement(namespace, localName, qName);
	}
}
