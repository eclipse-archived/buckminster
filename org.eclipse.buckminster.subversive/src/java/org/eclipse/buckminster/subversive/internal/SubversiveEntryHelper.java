package org.eclipse.buckminster.subversive.internal;

import org.eclipse.buckminster.subversion.ISvnEntryHelper;
import org.eclipse.team.svn.core.connector.SVNEntry;

public class SubversiveEntryHelper implements ISvnEntryHelper<SVNEntry> {

	@Override
	public int getEntryKind(SVNEntry entry) {
		return entry.nodeKind;
	}

	@Override
	public String getEntryPath(SVNEntry entry) {
		return entry.path;
	}

	@Override
	public long getEntryRevisionNumber(SVNEntry entry) {
		return entry.revision;
	}

}
