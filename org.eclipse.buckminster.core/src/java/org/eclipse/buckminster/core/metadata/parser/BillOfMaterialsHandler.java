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
import java.util.UUID;

import org.eclipse.buckminster.core.helpers.DateAndTimeUtils;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class BillOfMaterialsHandler extends DepNodeHandler
{
	public static final String TAG = BillOfMaterials.TAG;
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
	DepNode getDepNode()
	{
		return new BillOfMaterials(m_topNodeId, m_queryId, m_timestamp);
	}

	UUID getQueryId()
	{
		return m_queryId;
	}

	Date getTimestamp()
	{
		return m_timestamp;
	}

	UUID getTopNodeId()
	{
		return m_topNodeId;
	}
}
