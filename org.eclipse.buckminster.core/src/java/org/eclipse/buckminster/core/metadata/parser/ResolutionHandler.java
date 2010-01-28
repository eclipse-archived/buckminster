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
import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.cspec.ICSpecData;
import org.eclipse.buckminster.core.cspec.builder.ComponentRequestBuilder;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.parser.ComponentRequestHandler;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.osgi.util.NLS;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class ResolutionHandler extends ExtensionAwareHandler implements ChildPoppedListener
{
	public static final String TAG = Resolution.TAG;

	private final ComponentRequestHandler m_componentRequestHandler = new ComponentRequestHandler(this,
			new ComponentRequestBuilder());

	private final VersionMatchHandler m_versionMatchHandler = new VersionMatchHandler(this);

	private final ArrayList<String> m_attributes = new ArrayList<String>();

	private UUID m_cspecId;

	private UUID m_providerId;

	private ComponentRequest m_request;

	private String m_componentType;

	private VersionMatch m_versionMatch;

	private boolean m_materializable;

	private String m_persistentId;

	private String m_repository;

	private String m_remoteName;

	private String m_contentType;

	private long m_lastModified;

	private long m_size;

	private boolean m_unpack;

	public ResolutionHandler(AbstractHandler parent)
	{
		super(parent);
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child == m_componentRequestHandler)
			m_request = m_componentRequestHandler.getBuilder().createComponentRequest();
		else if(child == m_versionMatchHandler)
			m_versionMatch = m_versionMatchHandler.getVersionMatch();
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		ChildHandler ch;
		if(Resolution.ELEM_REQUEST.equals(localName))
			ch = m_componentRequestHandler;
		else if(VersionMatch.TAG.equals(localName))
			ch = m_versionMatchHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	public Resolution getResolution() throws SAXException
	{
		if(m_request == null)
			throw new SAXParseException(NLS.bind(Messages.Missing_required_element_0, XMLConstants.BM_METADATA_PREFIX
					+ '.' + Resolution.ELEM_REQUEST), this.getDocumentLocator());

		if(m_versionMatch == null)
			throw new SAXParseException(NLS.bind(Messages.Missing_required_element_0, XMLConstants.BM_METADATA_PREFIX
					+ '.' + VersionMatch.TAG), this.getDocumentLocator());

		if(m_componentType == null)
			m_componentType = legacyComponentType();

		AbstractHandler parent = getParentHandler();
		CSpec cspec;
		Provider provider;
		if(parent instanceof IDWrapperHandler)
		{
			IDWrapperHandler wh = (IDWrapperHandler)parent;
			cspec = (CSpec)wh.getWrapped(m_cspecId);
			provider = (Provider)wh.getWrapped(m_providerId);
		}
		else
		{
			try
			{
				StorageManager sm = StorageManager.getDefault();
				cspec = sm.getCSpecs().getElement(m_cspecId);
				provider = sm.getProviders().getElement(m_providerId);
			}
			catch(CoreException e)
			{
				throw new SAXParseException(e.getMessage(), getDocumentLocator(), e);
			}
		}

		return new Resolution(cspec, m_componentType, m_versionMatch, provider, m_materializable, m_request,
				m_attributes, m_persistentId, m_repository, m_remoteName, m_contentType, m_lastModified, m_size,
				m_unpack);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		m_versionMatch = null;
		m_cspecId = UUID.fromString(this.getStringValue(attrs, Resolution.ATTR_CSPEC_ID));
		m_materializable = getBooleanValue(attrs, Resolution.ATTR_MATERIALIZABLE);
		m_providerId = UUID.fromString(getStringValue(attrs, Resolution.ATTR_PROVIDER_ID));
		m_componentType = getOptionalStringValue(attrs, Resolution.ATTR_COMPONENT_TYPE);
		m_persistentId = getOptionalStringValue(attrs, Resolution.ATTR_PERSISTENT_ID);
		m_repository = getStringValue(attrs, Resolution.ATTR_REPOSITORY);
		m_remoteName = getOptionalStringValue(attrs, Resolution.ATTR_REMOTE_NAME);
		m_contentType = getOptionalStringValue(attrs, Resolution.ATTR_CONTENT_TYPE);
		m_size = getOptionalLongValue(attrs, Resolution.ATTR_SIZE, -1);
		m_lastModified = getOptionalLongValue(attrs, Resolution.ATTR_LAST_MODIFIED, -1);
		m_unpack = getOptionalBooleanValue(attrs, Resolution.ATTR_UNPACK, false);
		m_request = null;

		m_attributes.clear();
		String attributes = getOptionalStringValue(attrs, Resolution.ATTR_ATTRIBUTES);
		if(attributes != null)
		{
			for(String attr : attributes.split(",")) //$NON-NLS-1$
			{
				if(!m_attributes.contains(attr))
					m_attributes.add(attr);
			}
		}
	}

	private String legacyComponentType() throws SAXException
	{
		AbstractHandler parent = getParentHandler();
		ICSpecData cspec;
		Provider provider;
		if(parent instanceof IDWrapperHandler)
		{
			IDWrapperHandler wh = (IDWrapperHandler)parent;
			cspec = (ICSpecData)wh.getWrapped(m_cspecId);
			provider = (Provider)wh.getWrapped(m_providerId);
		}
		else
		{
			try
			{
				StorageManager sm = StorageManager.getDefault();
				cspec = sm.getCSpecs().getElement(m_cspecId);
				provider = sm.getProviders().getElement(m_providerId);
			}
			catch(CoreException e)
			{
				throw new SAXParseException(e.getMessage(), getDocumentLocator(), e);
			}
		}
		String[] ctypeIDs = provider.getComponentTypeIDs();
		if(ctypeIDs.length == 1)
			return ctypeIDs[0];

		String ctype = cspec.getComponentIdentifier().getComponentTypeID();
		if(ctype != null)
			return ctype;

		if(ctypeIDs.length == 3 && ctypeIDs[0].equals(IComponentType.OSGI_BUNDLE)
				&& ctypeIDs[1].equals(IComponentType.ECLIPSE_FEATURE) && ctypeIDs[2].equals(IComponentType.BUCKMINSTER))
			return IComponentType.BUCKMINSTER;

		return IComponentType.UNKNOWN;
	}
}
