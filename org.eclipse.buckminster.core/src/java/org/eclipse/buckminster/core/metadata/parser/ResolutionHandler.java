/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.parser;

import static org.eclipse.buckminster.core.metadata.model.Resolution.ATTR_ATTRIBUTES;
import static org.eclipse.buckminster.core.metadata.model.Resolution.ATTR_CSPEC_ID;
import static org.eclipse.buckminster.core.metadata.model.Resolution.ATTR_FIXED_VERSION_SELECTOR;
import static org.eclipse.buckminster.core.metadata.model.Resolution.ATTR_MATERIALIZABLE;
import static org.eclipse.buckminster.core.metadata.model.Resolution.ATTR_PROVIDER_ID;
import static org.eclipse.buckminster.core.metadata.model.Resolution.ATTR_REPOSITORY;
import static org.eclipse.buckminster.core.metadata.model.Resolution.ATTR_VERSION;
import static org.eclipse.buckminster.core.metadata.model.Resolution.ATTR_VERSION_TYPE;
import static org.eclipse.buckminster.core.metadata.model.Resolution.ELEM_REQUEST;

import java.util.HashSet;
import java.util.UUID;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.core.query.parser.ComponentRequestHandler;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.core.version.VersionSelectorFactory;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class ResolutionHandler extends ExtensionAwareHandler implements ChildPoppedListener
{
	public static final String TAG = Resolution.TAG;

	private final ComponentRequestHandler m_componentRequestHandler = new ComponentRequestHandler(this);
	private final HashSet<String> m_attributes = new HashSet<String>();
	private UUID m_cspecId;
	private UUID m_providerId;
	private ComponentRequest m_request;
	private IVersion m_version;
	private IVersionSelector m_fixedVersionSelector;
	private boolean m_materializable;
	private String m_repository;

	public ResolutionHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		try
		{
			m_cspecId = UUID.fromString(this.getStringValue(attrs, ATTR_CSPEC_ID));
			String version = getOptionalStringValue(attrs, ATTR_VERSION);
			if(version != null)
			{
				String tmp = getOptionalStringValue(attrs, ATTR_VERSION_TYPE);
				IVersionType versionType = CorePlugin.getDefault().getVersionType(tmp);
				m_version = versionType.fromString(version);
			}
			else
				m_version = null;

			String fixedSelector = this.getStringValue(attrs, ATTR_FIXED_VERSION_SELECTOR);
			m_fixedVersionSelector = VersionSelectorFactory.fromString(fixedSelector);
			m_materializable = this.getBooleanValue(attrs, ATTR_MATERIALIZABLE);
			m_providerId = UUID.fromString(getStringValue(attrs, ATTR_PROVIDER_ID));
			m_repository = getStringValue(attrs, ATTR_REPOSITORY);
			m_request = null;

			m_attributes.clear();
			String attributes = getOptionalStringValue(attrs, ATTR_ATTRIBUTES);
			if(attributes != null)
			{
				for(String attr : attributes.split(","))
					m_attributes.add(attr);
			}
		}
		catch(CoreException e)
		{
			throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
		}
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		ChildHandler ch;
		if(ELEM_REQUEST.equals(localName))
			ch = m_componentRequestHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	public Resolution getResolution() throws SAXException
	{
		if(m_request == null)
			throw new SAXParseException("Missing required element <" +
					XMLConstants.BM_METADATA_PREFIX + '.' + ELEM_REQUEST + '>',
					this.getDocumentLocator());

		return new Resolution(
				m_cspecId,
				m_version,
				m_fixedVersionSelector,
				m_providerId,
				m_materializable,
				m_request,
				m_attributes,
				m_repository);
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child == m_componentRequestHandler)
			m_request = m_componentRequestHandler.getComponentRequest();
	}
}

