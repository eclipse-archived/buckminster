package org.eclipse.buckminster.subclipse.internal;

import org.eclipse.buckminster.subversion.ISvnEntryHelper;
import org.tigris.subversion.svnclientadapter.ISVNDirEntry;

public class SvnEntryHelper implements ISvnEntryHelper<ISVNDirEntry> {

	public int getEntryKind(ISVNDirEntry entry) {
		return entry.getNodeKind().toInt();
	}

	public String getEntryPath(ISVNDirEntry entry) {
		return entry.getPath();
	}

	public long getEntryRevisionNumber(ISVNDirEntry entry) {
		return entry.getLastChangedRevision().getNumber();
	}

}
