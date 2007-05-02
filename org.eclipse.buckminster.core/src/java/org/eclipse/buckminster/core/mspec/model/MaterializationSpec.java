/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.mspec.model;

import java.net.URL;
import java.util.List;

import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.metadata.model.UUIDKeyed;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class MaterializationSpec extends MaterializationDirective implements ISaxable
{
	public static final String TAG = "mspec";
	public static final String ATTR_SHORT_DESC = "shortDesc";
	public static final String ATTR_URL = "url";

	private final String m_shortDesc;
	private final URL m_url;
	private final List<MaterializationNode> m_nodes;

	public MaterializationSpec(MaterializationSpecBuilder builder)
	{
		super(builder);
		m_shortDesc = builder.getShortDesc();
		m_url = builder.getURL();
		m_nodes = UUIDKeyed.createUnmodifiableList(builder.getNodes());
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public List<MaterializationNode> getNodes()
	{
		return m_nodes;
	}

	public String getShortDesc()
	{
		return m_shortDesc;
	}

	public URL getURL()
	{
		return m_url;
	}

	public void toSax(ContentHandler handler) throws SAXException
	{
		handler.startDocument();
		toSax(handler, XMLConstants.BM_METADATA_NS, XMLConstants.BM_METADATA_PREFIX, getDefaultTag());
		handler.endDocument();
	}

	@Override
	protected void appendAttributes(AttributesImpl attrs) throws SAXException
	{
		super.appendAttributes(attrs);
		Utils.addAttribute(attrs, ATTR_URL, m_url.toString());
		if(m_shortDesc != null)
			Utils.addAttribute(attrs, ATTR_SHORT_DESC, m_shortDesc);
	}

	@Override
	protected void emitElements(ContentHandler receiver, String namespace, String prefix) throws SAXException
	{
		for(MaterializationNode node : m_nodes)
			node.toSax(receiver, namespace, prefix, node.getDefaultTag());
	}
}
