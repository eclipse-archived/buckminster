/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.distro.model;

import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.cspec.ICSpecData;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.distro.IDistro;
import org.eclipse.buckminster.core.distro.builder.DistroBuilder;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.model.IUUIDPersisted;
import org.eclipse.buckminster.core.mspec.IMaterializationSpec;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.query.IComponentQuery;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.opml.IOPML;
import org.eclipse.buckminster.opml.model.OPML;
import org.eclipse.buckminster.sax.UUIDKeyed;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class Distro extends UUIDKeyed implements IUUIDPersisted, IDistro
{
	public static final String TAG = "distro";

	private final CSpec m_cspec;
	private final ComponentQuery m_cquery;
	private final MaterializationSpec m_mspec;
	private final OPML m_opml;

	public Distro(DistroBuilder builder)
	{
		m_cspec = new CSpec(builder.getCspecBuilder());
		m_cquery = new ComponentQuery(builder.getCqueryBuilder());
		m_mspec = new MaterializationSpec(builder.getMspecBuilder());
		m_opml = new OPML(builder.getOpmlBuilder());
	}

	public ICSpecData getCspec()
	{
		return m_cspec;
	}

	public IComponentQuery getCquery()
	{
		return m_cquery;
	}

	public IMaterializationSpec getMspec()
	{
		return m_mspec;
	}

	public IOPML getOpml()
	{
		return m_opml;
	}

	public boolean isPersisted(StorageManager sm) throws CoreException
	{
		return false;
	}

	public void remove(StorageManager sm) throws CoreException
	{
	}

	public void store(StorageManager sm) throws CoreException
	{
	}

	public void toSax(ContentHandler receiver) throws SAXException
	{
		receiver.startDocument();
		toSax(receiver, XMLConstants.BM_METADATA_NS, XMLConstants.BM_METADATA_PREFIX, getDefaultTag());
		receiver.endDocument();
	}

	@Override
	protected void emitElements(ContentHandler receiver, String namespace, String prefix) throws SAXException
	{
		m_cspec.toSax(receiver, namespace, prefix, m_cspec.getDefaultTag());
		m_cquery.toSax(receiver, namespace, prefix, m_cquery.getDefaultTag());
		m_mspec.toSax(receiver, namespace, prefix, m_mspec.getDefaultTag());
		m_opml.toSax(receiver, namespace, prefix, m_opml.getDefaultTag());
	}

	public String getDefaultTag()
	{
		return TAG;
	}
}
