/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.parser;

import java.util.ArrayList;
import java.util.UUID;

import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.ExportedBillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.IDWrapper;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class ExportedBillOfMaterialsHandler extends BillOfMaterialsHandler implements ChildPoppedListener
{
	@SuppressWarnings("hiding")
	public static final String TAG = ExportedBillOfMaterials.TAG;
	
	private final ArrayList<IDWrapper> m_contents = new ArrayList<IDWrapper>();
	private final IDWrapperHandler m_idWrapperHandler = new IDWrapperHandler(this);
	private UUID m_graphId;

	public ExportedBillOfMaterialsHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		super.handleAttributes(attrs);
		m_contents.clear();
		try
		{
			m_graphId = UUID.fromString(this.getStringValue(attrs, IDWrapper.ATTR_ID));
		}
		catch(IllegalArgumentException e)
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
			m_contents.add(m_idWrapperHandler.getWrapper());
	}

	@Override
	DepNode getDepNode()
	{
		return new ExportedBillOfMaterials(m_graphId, this.getTopNodeId(), this.getQueryId(), this.getTimestamp(), m_contents);
	}
}
