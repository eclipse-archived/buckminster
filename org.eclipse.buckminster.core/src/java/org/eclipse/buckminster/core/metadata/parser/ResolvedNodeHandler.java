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

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.ResolvedNode;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.osgi.util.NLS;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
class ResolvedNodeHandler extends BomNodeHandler implements ChildPoppedListener
{
	public static final String TAG = ResolvedNode.TAG;

	private UUID m_resolutionId;

	private final ArrayList<UUID> m_children = new ArrayList<UUID>();

	private final ElementRefHandler m_childHandler = new ElementRefHandler(this, ResolvedNode.CHILD_TAG);

	ResolvedNodeHandler(AbstractHandler parent)
	{
		super(parent);
	}

	public void childPopped(ChildHandler child) throws SAXParseException
	{
		if(child == m_childHandler)
			m_children.add(m_childHandler.getRefId());
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		ChildHandler ch;
		if(m_childHandler.getTAG().equals(localName))
			ch = m_childHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	BOMNode getDepNode() throws SAXException
	{
		ArrayList<BOMNode> childNodes = new ArrayList<BOMNode>(m_children.size());
		for(UUID childId : m_children)
			childNodes.add((BOMNode)getWrapped(childId));

		try
		{
			return new ResolvedNode((Resolution)getWrapped(m_resolutionId), childNodes);
		}
		catch(ClassCastException e)
		{
			throw new SAXParseException(NLS.bind(Messages.Wrapper_0_does_not_wrap_resolution,
					m_resolutionId), getDocumentLocator());
		}
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		m_resolutionId = UUID.fromString(this.getStringValue(attrs, ResolvedNode.ATTR_RESOLUTION_ID));
		m_children.clear();
	}
}
