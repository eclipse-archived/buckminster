/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.parser;

import java.util.UUID;

import org.eclipse.buckminster.core.cspec.parser.CSpecHandler;
import org.eclipse.buckminster.core.metadata.IUUIDKeyed;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.IDWrapper;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.core.query.parser.ComponentQueryHandler;
import org.eclipse.buckminster.core.rmap.parser.ProviderHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class IDWrapperHandler extends ExtensionAwareHandler implements ChildPoppedListener
{
	public static final String TAG = IDWrapper.TAG;

	private final CSpecHandler m_cspecHandler = new CSpecHandler(this);
	private final ResolutionHandler m_resolutionHandler = new ResolutionHandler(this);
	private final ResolvedNodeHandler m_resolvedNodeHandler = new ResolvedNodeHandler(this);
	private final UnresolvedNodeHandler m_unresolvedNodeHandler = new UnresolvedNodeHandler(this);
	private final GeneratorNodeHandler m_generatorNodeHandler = new GeneratorNodeHandler(this);
	private final ComponentQueryHandler m_componentQueryHandler = new ComponentQueryHandler(this);
	private BillOfMaterialsHandler m_billOfMaterialsHandler;

	private UUID m_id;
	private IDWrapper m_wrapper;

	public IDWrapperHandler(BillOfMaterialsHandler parent)
	{
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		m_id = UUID.fromString(this.getStringValue(attrs, IDWrapper.ATTR_ID));
		m_wrapper = null;
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		ChildHandler ch;
		if(CSpecHandler.TAG.equals(localName))
			ch = m_cspecHandler;
		else if(ProviderHandler.TAG.equals(localName))
			ch = createContentHandler(ProviderHandler.class, uri, attrs);
		else if(ResolutionHandler.TAG.equals(localName))
			ch = m_resolutionHandler;
		else if(ResolvedNodeHandler.TAG.equals(localName))
			ch = m_resolvedNodeHandler;
		else if(BillOfMaterialsHandler.TAG.equals(localName))
		{
			if(m_billOfMaterialsHandler == null)
				m_billOfMaterialsHandler = new BillOfMaterialsHandler(this);
			ch = m_billOfMaterialsHandler;
		}
		else if(UnresolvedNodeHandler.TAG.equals(localName))
			ch = m_unresolvedNodeHandler;
		else if(GeneratorNodeHandler.TAG.equals(localName))
			ch = m_generatorNodeHandler;
		else if(m_componentQueryHandler.getTAG().equals(localName))
			ch = m_componentQueryHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child instanceof DepNodeHandler)
			m_wrapper = new IDWrapper(m_id, ((DepNodeHandler)child).getDepNode());
		else if(child == m_cspecHandler)
			m_wrapper = new IDWrapper(m_id, m_cspecHandler.getCSpec());
		else if(child instanceof ProviderHandler)
			m_wrapper = new IDWrapper(m_id, ((ProviderHandler)child).getProvider());
		else if(child == m_resolutionHandler)
			m_wrapper = new IDWrapper(m_id, m_resolutionHandler.getResolution());
		else if(child == m_componentQueryHandler)
			m_wrapper = new IDWrapper(m_id, m_componentQueryHandler.getComponentQuery());
	}

	public IDWrapper getWrapper()
	{
		return m_wrapper;
	}

	IUUIDKeyed getWrapped(UUID id) throws SAXException
	{
		return ((BillOfMaterialsHandler)getParentHandler()).getWrapped(id);
	}

	DepNode getDepNode(UUID id) throws SAXException
	{
		return ((BillOfMaterialsHandler)getParentHandler()).getDepNode(id);
	}
}

