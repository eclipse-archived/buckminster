package org.eclipse.buckminster.subversive.internal;

import org.eclipse.buckminster.subversion.ISvnEntryHelper;
import org.eclipse.team.svn.core.connector.SVNEntry;

public class SubversiveEntryHelper implements ISvnEntryHelper<SVNEntry>
{

	public int getEntryKind(SVNEntry entry)
	{

		return entry.nodeKind;
	}

	public String getEntryPath(SVNEntry entry)
	{
		return entry.path;
	}

	public long getEntryRevisionNumber(SVNEntry entry)
	{
		return entry.revision;
	}

}
