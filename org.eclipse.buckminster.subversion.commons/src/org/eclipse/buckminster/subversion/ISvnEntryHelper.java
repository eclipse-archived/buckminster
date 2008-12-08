package org.eclipse.buckminster.subversion;


public interface ISvnEntryHelper<SVN_ENTRY_TYPE>
{
	int getEntryKind(SVN_ENTRY_TYPE entry);

	String getEntryPath(SVN_ENTRY_TYPE entry);

	long getEntryRevisionNumber(SVN_ENTRY_TYPE entry);
}
