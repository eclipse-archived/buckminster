package org.eclipse.buckminster.subversive.internal;

import java.util.Map;
import java.util.UUID;

import org.eclipse.buckminster.subversion.GenericCache;
import org.eclipse.buckminster.subversion.ISubversionCache;
import org.eclipse.team.svn.core.connector.SVNEntry;

public class SubversiveCache implements ISubversionCache<SVNEntry>
{
	private Map<String, SVNEntry[]> m_listCache;

	private static final UUID CACHE_KEY_LIST_CACHE = UUID.randomUUID();

	public SVNEntry[] get(String key)
	{
		return m_listCache.get(key);
	}

	public void initialize(Map<UUID, Object> userCache)
	{
		m_listCache = GenericCache.getCache(userCache, CACHE_KEY_LIST_CACHE);
	}

	public void put(String key, SVNEntry[] value)
	{
		m_listCache.put(key, value);
	}
}
