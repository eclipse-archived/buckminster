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
import org.eclipse.buckminster.core.common.model.AbstractSaxableElement;
import org.eclipse.buckminster.core.version.AbstractConverter;
import org.eclipse.buckminster.core.version.IVersionConverter;
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;


public class VersionConverterDesc extends AbstractSaxableElement
{
	public static final String TAG = "versionConverter";
	public static final String ATTR_TYPE = "type";
	public static final String ATTR_VERSION_TYPE = "versionType";

	private final String m_type;
	private final BidirectionalTransformer[] m_transformers;
	private final IVersionType m_versionType;

	public VersionConverterDesc(String type, IVersionType versionType, BidirectionalTransformer[] transformers)
	{
		m_type = type;
		m_transformers = transformers;
		m_versionType = versionType;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public IVersionConverter getVersionConverter()
	throws CoreException
	{
		AbstractConverter vct = (AbstractConverter)CorePlugin.getDefault().getVersionConverter(m_type);
		vct.setTransformers(m_transformers);
		vct.setVersionType(m_versionType);
		return vct;
	}

	public final BidirectionalTransformer[] getTransformers()
	{
		return m_transformers;
	}

	public final String getType()
	{
		return m_type;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		Utils.addAttribute(attrs, ATTR_TYPE, m_type);
		if(m_versionType != null)
			Utils.addAttribute(attrs, ATTR_VERSION_TYPE, m_versionType.getId());
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException
	{
		for(BidirectionalTransformer transformer : m_transformers)
			transformer.toSax(handler, namespace, prefix, transformer.getDefaultTag());
	}
}
