package org.eclipse.buckminster.p2.remote.change;

import java.util.ArrayList;
import java.util.List;

public class SynchronizationBlock
{
	private long m_lastChangeNumber;

	private List<RepositoryChange> m_changes = new ArrayList<RepositoryChange>();

	public List<RepositoryChange> getChanges()
	{
		return m_changes;
	}

	public long getLastChangeNumber()
	{
		return m_lastChangeNumber;
	}

	public void setChanges(List<RepositoryChange> changes)
	{
		m_changes = changes;
	}

	public void setLastChangeNumber(long lastChangeNumber)
	{
		m_lastChangeNumber = lastChangeNumber;
	}
}
