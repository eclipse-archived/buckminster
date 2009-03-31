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

import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.helpers.DateAndTimeUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.AbstractSaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class VersionMatch extends AbstractSaxableElement
{
	public static final VersionMatch DEFAULT = new VersionMatch(null, null, -1L, null, null);

	public static final String TAG = "versionMatch"; //$NON-NLS-1$

	public static final String ATTR_ARTIFACT_INFO = "artifactInfo"; //$NON-NLS-1$

	public static final String ATTR_BRANCH_OR_TAG = "branchOrTag"; //$NON-NLS-1$

	public static final String ATTR_REVISION = "revision"; //$NON-NLS-1$

	public static final String ATTR_TIMESTAMP = "timestamp"; //$NON-NLS-1$

	private final String m_artifactInfo;

	private final VersionSelector m_branchOrTag;

	private final long m_revision;

	private final Date m_timestamp;

	private final Version m_version;

	public VersionMatch(Version version, VersionSelector branchOrTag, long revision, Date timestamp, String artifactInfo)
	{
		m_version = version;

		if(branchOrTag != null && branchOrTag.isDefault())
			branchOrTag = null;

		m_branchOrTag = branchOrTag;
		m_revision = revision;
		m_timestamp = timestamp;
		m_artifactInfo = artifactInfo;
	}

	public VersionMatch copyWithVersion(Version version)
	{
		if(Trivial.equalsAllowNull(version, m_version))
			return this;

		return new VersionMatch(version, m_branchOrTag, -1, null, m_artifactInfo);
	}

	public String getArtifactInfo()
	{
		return m_artifactInfo;
	}

	public VersionSelector getBranchOrTag()
	{
		return m_branchOrTag;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public long getRevision()
	{
		return m_revision;
	}

	public Date getTimestamp()
	{
		return m_timestamp;
	}

	public Version getVersion()
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
			bld.append(VersionHelper.getHumanReadable(m_version));
			needSep = true;
		}
		if(m_branchOrTag != null)
		{
			if(needSep)
				bld.append(':');
			m_branchOrTag.toString(bld);
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

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		if(m_artifactInfo != null)
			Utils.addAttribute(attrs, ATTR_ARTIFACT_INFO, m_artifactInfo);

		if(m_branchOrTag != null)
			Utils.addAttribute(attrs, ATTR_BRANCH_OR_TAG, m_branchOrTag.toString());

		if(m_revision != -1)
			Utils.addAttribute(attrs, ATTR_REVISION, Long.toString(m_revision));

		if(m_timestamp != null)
			Utils.addAttribute(attrs, ATTR_TIMESTAMP, DateAndTimeUtils.toISOFormat(m_timestamp));

		if(m_version != null)
			Utils.addAttribute(attrs, ComponentIdentifier.ATTR_VERSION, m_version.toString());
	}
}
