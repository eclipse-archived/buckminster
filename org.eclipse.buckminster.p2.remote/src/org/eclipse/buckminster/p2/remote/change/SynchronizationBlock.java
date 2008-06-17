package org.eclipse.buckminster.p2.remote.change;

import java.util.List;

public class SynchronizationBlock
{
	private long m_lastChangeNumber;

	private List<RepositoryChange> m_changes;

	public void setChanges(List<RepositoryChange> changes)
	{
		m_changes = changes;
	}

	public List<RepositoryChange> getChanges()
	{
		return m_changes;
	}

	public void setLastChangeNumber(long lastChangeNumber)
	{
		m_lastChangeNumber = lastChangeNumber;
	}

	public long getLastChangeNumber()
	{
		return m_lastChangeNumber;
	}
}
