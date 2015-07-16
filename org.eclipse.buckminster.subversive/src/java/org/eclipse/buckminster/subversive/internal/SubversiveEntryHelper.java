package org.eclipse.buckminster.subversive.internal;

import org.eclipse.buckminster.subversion.ISvnEntryHelper;
import org.eclipse.team.svn.core.connector.SVNEntry;

public class SubversiveEntryHelper implements ISvnEntryHelper<SVNEntry> {

	@Override
	public int getEntryKind(SVNEntry entry) {
		switch (entry.nodeKind) {
			case DIR:
				return ISvnEntryHelper.DIR;
			case FILE:
				return ISvnEntryHelper.FILE;
			case NONE:
				return ISvnEntryHelper.NONE;
			default:
				return ISvnEntryHelper.UNKNOWN;
		}
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
