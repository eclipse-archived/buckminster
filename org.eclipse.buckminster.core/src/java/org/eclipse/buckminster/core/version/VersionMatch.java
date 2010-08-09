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
import org.eclipse.buckminster.sax.AbstractSaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.equinox.p2.metadata.Version;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class VersionMatch extends AbstractSaxableElement {
	public static final VersionMatch DEFAULT = new VersionMatch(null, null, -1L, null, null);

	public static final String TAG = "versionMatch"; //$NON-NLS-1$

	public static final String ATTR_ARTIFACT_INFO = "artifactInfo"; //$NON-NLS-1$

	public static final String ATTR_BRANCH_OR_TAG = "branchOrTag"; //$NON-NLS-1$

	public static final String ATTR_REVISION = "revision"; //$NON-NLS-1$

	public static final String ATTR_TIMESTAMP = "timestamp"; //$NON-NLS-1$

	public static boolean satisfiesRevision(String revision, String satisfiedBy) {
		if (revision == null)
			return true;
		if (satisfiedBy == null)
			return false;
		if (revision.equals(satisfiedBy))
			return true;

		try {
			return Long.parseLong(revision) >= Long.parseLong(satisfiedBy);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private final String artifactInfo;

	private final VersionSelector branchOrTag;

	private final String revision;

	private final Date timestamp;

	private final Version version;

	public VersionMatch(Version version, VersionSelector branchOrTag, long revision, Date timestamp, String artifactInfo) {
		this(version, branchOrTag, revision == -1 ? null : Long.toString(revision), timestamp, artifactInfo);
	}

	public VersionMatch(Version version, VersionSelector branchOrTag, String revision, Date timestamp, String artifactInfo) {
		this.version = version;

		if (branchOrTag != null && branchOrTag.isDefault())
			branchOrTag = null;

		this.branchOrTag = branchOrTag;
		this.revision = revision;
		this.timestamp = timestamp;
		this.artifactInfo = artifactInfo;
	}

	public VersionMatch copyWithVersion(Version newVersion) {
		if (Trivial.equalsAllowNull(version, newVersion))
			return this;

		return new VersionMatch(newVersion, branchOrTag, -1, null, artifactInfo);
	}

	public String getArtifactInfo() {
		return artifactInfo;
	}

	public VersionSelector getBranchOrTag() {
		return branchOrTag;
	}

	@Override
	public String getDefaultTag() {
		return TAG;
	}

	public long getNumericRevision() {
		return revision == null ? -1 : Long.parseLong(revision);
	}

	public String getRevision() {
		return revision;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public Version getVersion() {
		return version;
	}

	public boolean satisfiesRevision(String rev) {
		return satisfiesRevision(rev, revision);
	}

	@Override
	public String toString() {
		StringBuilder bld = new StringBuilder();
		toString(bld);
		return bld.toString();
	}

	public void toString(StringBuilder bld) {
		boolean needSep = false;
		if (version != null) {
			bld.append(VersionHelper.getHumanReadable(version));
			needSep = true;
		}
		if (branchOrTag != null) {
			if (needSep)
				bld.append(':');
			branchOrTag.toString(bld);
		}
		if (revision != null) {
			if (needSep)
				bld.append(':');
			bld.append('#');
			bld.append(revision);
		}
		if (timestamp != null) {
			bld.append(':');
			bld.append(DateAndTimeUtils.toISOFormat(timestamp));
		}
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException {
		if (artifactInfo != null)
			Utils.addAttribute(attrs, ATTR_ARTIFACT_INFO, artifactInfo);

		if (branchOrTag != null)
			Utils.addAttribute(attrs, ATTR_BRANCH_OR_TAG, branchOrTag.toString());

		if (revision != null)
			Utils.addAttribute(attrs, ATTR_REVISION, revision);

		if (timestamp != null)
			Utils.addAttribute(attrs, ATTR_TIMESTAMP, DateAndTimeUtils.toISOFormat(timestamp));

		if (version != null)
			Utils.addAttribute(attrs, "version", version.toString()); //$NON-NLS-1$
	}
}
