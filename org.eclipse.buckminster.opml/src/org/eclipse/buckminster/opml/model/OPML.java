/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.opml.model;

import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.buckminster.sax.UUIDKeyed;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 *
 */
public class OPML extends UUIDKeyed implements ISaxable
{
	public static final String OPML_NAMESPACE = "http://opml.org/spec2";
	public static final String OPML_PREFIX = "opml";
	public static final String OPML_NS_RESOURCE = "/opml-2.0-ns.xsd";

	public static final String ATTR_VERSION = "version";
	public static final String TAG = "opml";

	public static final int SEQUENCE_NUMBER = 1;

	private final Body m_body;
	private final Head m_head;
	private final String m_version;

	public OPML(String version, Head head, Body body)
	{
		m_version = version;
		m_head = head;
		m_body = body;
	}

	public Body getBody()
	{
		return m_body;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public Head getHead()
	{
		return m_head;
	}

	public String getVersion()
	{
		return m_version;
	}

	public void toSax(ContentHandler receiver) throws SAXException
	{
		receiver.startDocument();
		toSax(receiver, "", "", TAG);
		receiver.endDocument();
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		if(m_version != null)
			Utils.addAttribute(attrs, ATTR_VERSION, m_version);
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException
	{
		m_head.toSax(handler, namespace, prefix, m_head.getDefaultTag());
		m_body.toSax(handler, namespace, prefix, m_body.getDefaultTag());
	}
	
	@Override
	protected String getElementNamespace(String namespace)
	{
		return OPML_NAMESPACE;
	}
	
	@Override
	protected String getElementPrefix(String prefix)
	{
		return OPML_PREFIX;
	}
}
