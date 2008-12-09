package org.eclipse.buckminster.subversion;

public interface ISvnEntryHelper<SVN_ENTRY_TYPE>
{
	static final int NONE = 0;

	static final int FILE = 1;

	static final int DIR = 2;

	static final int UNKNOWN = 3;

	int getEntryKind(SVN_ENTRY_TYPE entry);

	String getEntryPath(SVN_ENTRY_TYPE entry);

	long getEntryRevisionNumber(SVN_ENTRY_TYPE entry);
}
