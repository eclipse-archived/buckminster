/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.opml.model;

import org.eclipse.buckminster.opml.IBody;
import org.eclipse.buckminster.opml.IHead;
import org.eclipse.buckminster.opml.IOPML;
import org.eclipse.buckminster.opml.builder.OPMLBuilder;
import org.eclipse.buckminster.sax.UUIDKeyed;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 * 
 */
public class OPML extends UUIDKeyed implements IOPML
{
	public static final String OPML_NAMESPACE = "http://opml.org/spec2"; //$NON-NLS-1$

	public static final String OPML_PREFIX = "opml"; //$NON-NLS-1$

	public static final String OPML_NS_RESOURCE = "/opml-2.0-ns.xsd"; //$NON-NLS-1$

	public static final String ATTR_VERSION = "version"; //$NON-NLS-1$

	public static final String TAG = "opml"; //$NON-NLS-1$

	public static final int SEQUENCE_NUMBER = 1;

	private final Body m_body;

	private final Head m_head;

	private final String m_version;

	public OPML(OPMLBuilder builder)
	{
		m_version = builder.getVersion();
		m_head = new Head(builder.getHeadBuilder());
		m_body = new Body(builder.getBodyBuilder());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter)
	{
		if(adapter.isAssignableFrom(OPMLBuilder.class))
		{
			OPMLBuilder bld = new OPMLBuilder();
			bld.initFrom(this);
			return bld;
		}
		return super.getAdapter(adapter);
	}

	public IBody getBody()
	{
		return m_body;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public IHead getHead()
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
		toSax(receiver, "", "", TAG); //$NON-NLS-1$ //$NON-NLS-2$
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
		return "".equals(namespace) ? "" : OPML_NAMESPACE; //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	protected String getElementPrefix(String prefix)
	{
		return "".equals(prefix) ? "" : OPML_PREFIX; //$NON-NLS-1$ //$NON-NLS-2$
	}
}
