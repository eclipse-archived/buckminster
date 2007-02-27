/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.rmap.model;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.version.IVersionConverter;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;


public class VersionConverterDesc implements ISaxableElement
{
	public static final String TAG = "versionConverter";
	public static final String ATTR_TYPE = "type";

	private final String m_type;
	private final BidirectionalTransformer[] m_transformers;

	public VersionConverterDesc(String type, BidirectionalTransformer[] transformers)
	{
		m_type = type;
		m_transformers = transformers;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public IVersionConverter getVersionConverter()
	throws CoreException
	{
		IVersionConverter vct = CorePlugin.getDefault().getVersionConverter(m_type);
		vct.setTransformers(m_transformers);
		return vct;
	}

	public void toSax(ContentHandler handler, String namespace, String prefix, String localName) throws SAXException
	{
		String qName = Utils.makeQualifiedName(prefix, localName);
		AttributesImpl attrs = new AttributesImpl();
		Utils.addAttribute(attrs, ATTR_TYPE, m_type);
		handler.startElement(namespace, localName, qName, attrs);
		for(BidirectionalTransformer transformer : m_transformers)
			transformer.toSax(handler, namespace, prefix, transformer.getDefaultTag());
		handler.endElement(namespace, localName, qName);
	}

	public final BidirectionalTransformer[] getTransformers()
	{
		return m_transformers;
	}

	public final String getType()
	{
		return m_type;
	}
}
