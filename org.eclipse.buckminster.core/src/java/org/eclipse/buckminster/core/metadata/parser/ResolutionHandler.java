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
import java.util.HashSet;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.DateAndTimeUtils;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.core.query.parser.ComponentRequestHandler;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.VersionMatch;
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
	private final VersionMatchHandler m_versionMatchHandler = new VersionMatchHandler(this);
	private final HashSet<String> m_attributes = new HashSet<String>();
	private UUID m_cspecId;
	private UUID m_providerId;
	private ComponentRequest m_request;
	private VersionMatch m_versionMatch;
	private boolean m_materializable;
	private String m_repository;
	private String m_remoteName;
	private String m_contentType;
	private long m_size;

	// For backward compatibility with the 0.1.0 Resolution
	//
	private String m_version;
	private String m_versionType;
	private String m_fixedVersionSelector;

	public ResolutionHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		m_versionMatch = null;
		m_cspecId = UUID.fromString(this.getStringValue(attrs, Resolution.ATTR_CSPEC_ID));
		m_materializable = getBooleanValue(attrs, Resolution.ATTR_MATERIALIZABLE);
		m_providerId = UUID.fromString(getStringValue(attrs, Resolution.ATTR_PROVIDER_ID));
		m_repository = getStringValue(attrs, Resolution.ATTR_REPOSITORY);
		m_remoteName = getOptionalStringValue(attrs, Resolution.ATTR_REMOTE_NAME);
		m_contentType = getOptionalStringValue(attrs, Resolution.ATTR_CONTENT_TYPE);
		m_size = getOptionalLongValue(attrs, Resolution.ATTR_SIZE, -1);
		m_request = null;

		m_attributes.clear();
		String attributes = getOptionalStringValue(attrs, Resolution.ATTR_ATTRIBUTES);
		if(attributes != null)
		{
			for(String attr : attributes.split(","))
				m_attributes.add(attr);
		}

		// For backward compatibility with the 0.1.0 Resolution
		//
		m_version = getOptionalStringValue(attrs, VersionMatch.ATTR_VERSION);
		m_versionType = getOptionalStringValue(attrs, VersionMatch.ATTR_VERSION_TYPE);
		m_fixedVersionSelector = getOptionalStringValue(attrs, "fixedVersionSelector");
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
			throw new SAXParseException("Missing required element <" +
					XMLConstants.BM_METADATA_PREFIX + '.' + Resolution.ELEM_REQUEST + '>',
					this.getDocumentLocator());

		if(m_versionMatch == null)
		{
			if(m_version != null)
				m_versionMatch = legacyVersionMatch();
			else
				throw new SAXParseException("Missing required element <" +
						XMLConstants.BM_METADATA_PREFIX + '.' + VersionMatch.TAG + '>',
						this.getDocumentLocator());
		}

		return new Resolution(
				m_cspecId,
				m_versionMatch,
				m_providerId,
				m_materializable,
				m_request,
				m_attributes,
				m_repository,
				m_remoteName,
				m_contentType,
				m_size);
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child == m_componentRequestHandler)
			m_request = m_componentRequestHandler.getComponentRequest();
		else if(child == m_versionMatchHandler)
			m_versionMatch = m_versionMatchHandler.getVersionMatch();
	}

	private static final Pattern s_versionExp = Pattern.compile("^\\s*(.*?)([@/#][^@/#]*?)?(?:\\|(.+))?\\s*$");

	private static final Pattern s_numberExp = Pattern.compile("^#(\\d+)$");

	private static final Pattern s_tagExp = Pattern.compile("^/(.*)$");

	private VersionMatch legacyVersionMatch() throws SAXException
	{
		IVersion version;
		try
		{
			version = CorePlugin.getDefault().getVersionType(m_versionType).fromString(m_version);
		}
		catch(CoreException e)
		{
			throw new SAXParseException(e.getMessage(), getDocumentLocator(), e);
		}

		if(m_fixedVersionSelector == null || m_fixedVersionSelector.length() == 0)
			return new VersionMatch(version, null, null, -1, null, null);

		Matcher m = s_versionExp.matcher(m_fixedVersionSelector);
		if(m.matches())
		{
			String branch = m.group(1);
			if(branch.length() == 0 || VersionSelector.DEFAULT_BRANCH.equals(branch))
				branch = null;

			String qualifier = m.group(2);
			String artifactType = m.group(3);
			VersionSelector btag = (branch == null) ? null : VersionSelector.branch(branch);
			if(qualifier == null)
				return new VersionMatch(version, btag, null, -1, null, artifactType);

			m = s_tagExp.matcher(qualifier);
			if(m.matches())
			{
				String tag = m.group(1);
				if(tag != null && !"LATEST".equals(tag)) // The LATEST comparison is for backward compatibility
					btag = VersionSelector.tag(tag);				
				return new VersionMatch(version, btag, null, -1, null, artifactType);
			}

			try
			{
				Date d = DateAndTimeUtils.fromISOFormat(qualifier.substring(1));
				return new VersionMatch(version, btag, null, -1, d, artifactType);
			}
			catch(ParseException e)
			{}

			m = s_numberExp.matcher(qualifier);
			if(m.matches())
				return new VersionMatch(version, btag, null, Long.parseLong(m.group(1)), null, artifactType);
		}
		throw new SAXParseException("Unable to parse legacy version selector string \"" + m_fixedVersionSelector + "\"", getDocumentLocator());
	}
}

