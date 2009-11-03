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
import org.eclipse.buckminster.core.version.AbstractConverter;
import org.eclipse.buckminster.core.version.IVersionConverter;
import org.eclipse.buckminster.sax.AbstractSaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.internal.provisional.p2.metadata.VersionFormat;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

@SuppressWarnings("restriction")
public class VersionConverterDesc extends AbstractSaxableElement
{
	public static final String TAG = "versionConverter"; //$NON-NLS-1$

	public static final String ATTR_TYPE = "type"; //$NON-NLS-1$

	public static final String ATTR_VERSION_FORMAT = "versionFormat"; //$NON-NLS-1$

	public static final String ATTR_VERSION_TYPE = "versionType"; //$NON-NLS-1$

	private final String m_type;

	private final BidirectionalTransformer[] m_transformers;

	private final VersionFormat m_versionFormat;

	public VersionConverterDesc(String type, VersionFormat versionFormat, BidirectionalTransformer[] transformers)
	{
		m_type = type;
		m_transformers = transformers;
		m_versionFormat = versionFormat;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public final BidirectionalTransformer[] getTransformers()
	{
		return m_transformers;
	}

	public final String getType()
	{
		return m_type;
	}

	public IVersionConverter getVersionConverter() throws CoreException
	{
		AbstractConverter vct = (AbstractConverter)CorePlugin.getDefault().getVersionConverter(m_type);
		vct.setTransformers(m_transformers);
		vct.setVersionFormat(m_versionFormat);
		return vct;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		Utils.addAttribute(attrs, ATTR_TYPE, m_type);
		if(m_versionFormat != null)
			Utils.addAttribute(attrs, ATTR_VERSION_FORMAT, m_versionFormat.toString());
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException
	{
		for(BidirectionalTransformer transformer : m_transformers)
			transformer.toSax(handler, namespace, prefix, transformer.getDefaultTag());
	}
}
