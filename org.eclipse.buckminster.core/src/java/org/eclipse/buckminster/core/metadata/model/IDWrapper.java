/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.model;

import java.util.UUID;

import org.eclipse.buckminster.core.metadata.IUUIDKeyed;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 *
 */
public class IDWrapper implements ISaxableElement, Comparable<IDWrapper>
{
	public static final String TAG = "idwrapper";
	public static final String ATTR_ID = "id";

	private final UUID m_id;
	private final IUUIDKeyed m_wrapped;

	public IDWrapper(IUUIDKeyed wrapped) throws CoreException
	{
		this(wrapped.getId(), wrapped);
	}

	public IDWrapper(UUID id, IUUIDKeyed wrapped)
	{
		assert wrapped instanceof ISaxableElement;
		m_id = id;
		m_wrapped = wrapped;
	}

	public int compareTo(IDWrapper o)
	{
		return m_id.compareTo(o.m_id);
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public UUID getId()
	{
		return m_id;
	}

	public IUUIDKeyed getWrapped()
	{
		return m_wrapped;
	}

	public void toSax(ContentHandler receiver, String namespace, String prefix, String localName) throws SAXException
	{
		AttributesImpl attrs = new AttributesImpl();
		Utils.addAttribute(attrs, ATTR_ID, m_id.toString());
		String qName = Utils.makeQualifiedName(prefix, localName);
		receiver.startElement(namespace, localName, qName, attrs);
		if(m_wrapped instanceof BillOfMaterials)
			((BillOfMaterials)m_wrapped).wrappedToSax(receiver, namespace, prefix, ((ISaxableElement)m_wrapped).getDefaultTag());
		else
			((ISaxableElement)m_wrapped).toSax(receiver, namespace, prefix, ((ISaxableElement)m_wrapped).getDefaultTag());
		receiver.endElement(namespace, localName, qName);
	}
}
