/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.version;

import java.util.Date;

import org.eclipse.buckminster.core.helpers.DateAndTimeUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class VersionMatch implements ISaxableElement
{
	public static final VersionMatch DEFAULT = new VersionMatch(null, null, null, -1L, null, null);

	public static final String TAG = "versionMatch";

	public static final String ATTR_ARTIFACT_TYPE = "artifactType";
	public static final String ATTR_BRANCH_OR_TAG = "branchOrTag";
	public static final String ATTR_REVISION = "revision";
	public static final String ATTR_SPACE = "space";
	public static final String ATTR_TIMESTAMP = "timestamp";
	public static final String ATTR_VERSION = "version";
	public static final String ATTR_VERSION_TYPE = "versionType";

	private final String m_artifactType;

	private final VersionSelector m_branchOrTag;

	private final long m_revision;

	private final String m_space;

	private final Date m_timestamp;

	private final IVersion m_version;

	public VersionMatch(IVersion version, VersionSelector branchOrTag, String space, long revision, Date timestamp, String artifactType)
	{
		m_version = version;
		
		if(branchOrTag != null && branchOrTag.isDefault())
			branchOrTag = null;

		m_branchOrTag = branchOrTag;
		m_space = space;
		m_revision = revision;
		m_timestamp = timestamp;
		m_artifactType = artifactType;
	}

	public VersionMatch copyWithVersion(IVersion version)
	{
		if(Trivial.equalsAllowNull(version, m_version))
			return this;
		
		return new VersionMatch(version, m_branchOrTag, m_space, -1, null, m_artifactType);
	}

	public String getArtifactType()
	{
		return m_artifactType;
	}

	public VersionSelector getBranchOrTag()
	{
		return m_branchOrTag;
	}

	public long getRevision()
	{
		return m_revision;
	}

	public String getSpace()
	{
		return m_space;
	}

	public Date getTimestamp()
	{
		return m_timestamp;
	}

	public IVersion getVersion()
	{
		return m_version;
	}

	@Override
	public String toString()
	{
		StringBuilder bld = new StringBuilder();
		toString(bld);
		return bld.toString();
	}

	public void toString(StringBuilder bld)
	{
		boolean needSep = false;
		if(m_version != null)
		{
			bld.append(m_version);
			needSep = true;
		}
		if(m_branchOrTag != null)
		{
			if(needSep)
				bld.append(':');
			m_branchOrTag.toString(bld);
		}
		if(m_space != null)
		{
			if(needSep)
				bld.append(':');
			bld.append("@");
			bld.append(m_space);
		}
		if(m_revision != -1)
		{
			if(needSep)
				bld.append(':');
			bld.append('#');
			bld.append(m_revision);
		}
		if(m_timestamp != null)
		{
			bld.append(':');
			bld.append(DateAndTimeUtils.toISOFormat(m_timestamp));
		}
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public void toSax(ContentHandler receiver, String namespace, String prefix, String localName) throws SAXException
	{
		AttributesImpl attrs = new AttributesImpl();

		if(m_artifactType != null)
			Utils.addAttribute(attrs, ATTR_ARTIFACT_TYPE, m_artifactType);

		if(m_branchOrTag != null)
			Utils.addAttribute(attrs, ATTR_BRANCH_OR_TAG, m_branchOrTag.toString());

		if(m_revision != -1)
			Utils.addAttribute(attrs, ATTR_REVISION, Long.toString(m_revision));

		if(m_space != null)
			Utils.addAttribute(attrs, ATTR_SPACE, m_space);

		if(m_timestamp != null)
			Utils.addAttribute(attrs, ATTR_TIMESTAMP, DateAndTimeUtils.toISOFormat(m_timestamp));

		if(m_version != null)
		{
			Utils.addAttribute(attrs, ATTR_VERSION, m_version.toString());
			Utils.addAttribute(attrs, ATTR_VERSION_TYPE, m_version.getType().getId());
		}

		String qName = Utils.makeQualifiedName(prefix, localName);
		receiver.startElement(namespace, localName, qName, attrs);
		receiver.endElement(namespace, localName, qName);
	}
}
