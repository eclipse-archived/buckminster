/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.parser;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.eclipse.buckminster.core.helpers.DateAndTimeUtils;
import org.eclipse.buckminster.core.metadata.IUUIDKeyed;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.IDWrapper;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class BillOfMaterialsHandler extends DepNodeHandler implements ChildPoppedListener
{
	public static final String TAG = BillOfMaterials.TAG;
	
	private final Map<UUID,IDWrapper> m_wrapperMap = new HashMap<UUID,IDWrapper>();
	private final IDWrapperHandler m_idWrapperHandler = new IDWrapperHandler(this);
	private UUID m_topNodeId;
	private UUID m_queryId;
	private Date m_timestamp;

	public BillOfMaterialsHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		super.handleAttributes(attrs);
		m_wrapperMap.clear();
		try
		{
			String tmp = getOptionalStringValue(attrs, BillOfMaterials.ATTR_TOP_NODE_ID);
			m_topNodeId = tmp == null ? null : UUID.fromString(tmp);
			m_queryId = UUID.fromString(this.getStringValue(attrs, BillOfMaterials.ATTR_QUERY_ID));
			m_timestamp = DateAndTimeUtils.fromISOFormat(this.getStringValue(attrs, BillOfMaterials.ATTR_TIMESTAMP));
		}
		catch(IllegalArgumentException e)
		{
			throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
		}
		catch(ParseException e)
		{
			throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
		}
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		ChildHandler ch;
		if(IDWrapperHandler.TAG.equals(localName))
			ch = m_idWrapperHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child == m_idWrapperHandler)
		{
			IDWrapper wrapper = m_idWrapperHandler.getWrapper();
			m_wrapperMap.put(wrapper.getId(), wrapper);
		}
	}

	@Override
	public IUUIDKeyed getWrapped(UUID id) throws SAXException
	{
		IDWrapper wrapper = m_wrapperMap.get(id);
		if(wrapper == null)
			throw new SAXParseException("id " + id + " appoints a non existing wrapper", getDocumentLocator());
		return wrapper.getWrapped();
	}

	ComponentQuery getQuery(UUID queryId) throws SAXException
	{
		try
		{
			return (ComponentQuery)getWrapped(queryId);
		}
		catch(ClassCastException e)
		{
			throw new SAXParseException("wrapper " + queryId + " does not wrap a query", getDocumentLocator());
		}
	}

	@Override
	DepNode getDepNode() throws SAXException
	{
		return new BillOfMaterials((DepNode)getWrapped(m_topNodeId), getQuery(m_queryId), m_timestamp);
	}
}
