/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.eclipse.buckminster.core.metadata.IUUIDKeyed;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * The exported (self contained) resolution graph
 * @author Thomas Hallgren
 */
public class ExportedBillOfMaterials extends BillOfMaterials
{
	@SuppressWarnings("hiding")
	public static final String TAG = "exportedBillOfMaterials";

	private final UUID m_graphId;
	private final List<IDWrapper> m_contents;

	public ExportedBillOfMaterials(UUID graphId, UUID topNodeId, UUID componentQueryId, Date timestamp, List<IDWrapper> contents)
	{
		super(topNodeId, componentQueryId, timestamp);
		m_graphId = graphId;
		m_contents = contents;
	}

	public List<IDWrapper> getContents()
	{
		return m_contents;
	}

	@Override
	public String getDefaultTag()
	{
		return TAG;
	}

	@Override
	public void toSax(ContentHandler receiver, String namespace, String prefix, String localName) throws SAXException
	{
		// Note, we do not call super here since we want the top element to contain the collection
		//
		HashMap<String, String> prefixMappings = new HashMap<String, String>();
		for(IDWrapper wrapper : m_contents)
		{
			IUUIDKeyed wrapped = wrapper.getWrapped();
			if(wrapped instanceof Provider)
				((Provider)wrapped).addPrefixMappings(prefixMappings);
		}

		Set<Map.Entry<String, String>> pfxMappings = prefixMappings.entrySet();
		if(pfxMappings.size() > 0)
		{
			for(Map.Entry<String, String> pfxMapping : pfxMappings)
				receiver.startPrefixMapping(pfxMapping.getKey(), pfxMapping.getValue());
		}

		AttributesImpl attrs = new AttributesImpl();
		addAttributes(attrs);
		Utils.emitCollection(namespace, prefix, localName, IDWrapper.TAG, attrs, m_contents, receiver);

		if(pfxMappings.size() > 0)
		{
			for(Map.Entry<String, String> pfxMapping : pfxMappings)
				receiver.endPrefixMapping(pfxMapping.getKey());
		}
	}

	@Override
	void addAttributes(AttributesImpl attrs)
	{
		super.addAttributes(attrs);
		Utils.addAttribute(attrs, IDWrapper.ATTR_ID, m_graphId.toString());
	}
}
