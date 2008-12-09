package org.eclipse.buckminster.subclipse.internal;

import java.util.Map;
import java.util.UUID;

import org.eclipse.buckminster.subversion.GenericCache;
import org.eclipse.buckminster.subversion.ISubversionCache;
import org.tigris.subversion.svnclientadapter.ISVNDirEntry;

public class SvnCache implements ISubversionCache<ISVNDirEntry>
{
	private Map<String, ISVNDirEntry> m_dirCache;

	private Map<String, ISVNDirEntry[]> m_listCache;

	private static final UUID CACHE_KEY_LIST_CACHE = UUID.randomUUID();

	private static final UUID CACHE_KEY_DIR_CACHE = UUID.randomUUID();

	public boolean dirContainsKey(String key)
	{
		return m_dirCache.containsKey(key);
	}

	public ISVNDirEntry[] get(String key)
	{
		return m_listCache.get(key);
	}

	public ISVNDirEntry getDir(String key)
	{
		return m_dirCache.get(key);
	}

	public void initialize(Map<UUID, Object> userCache)
	{
		m_dirCache = GenericCache.getCache(userCache, CACHE_KEY_DIR_CACHE);
		m_listCache = GenericCache.getCache(userCache, CACHE_KEY_LIST_CACHE);
	}

	public void put(String key, ISVNDirEntry[] value)
	{
		m_listCache.put(key, value);
	}

	public void putDir(String key, ISVNDirEntry value)
	{
		m_dirCache.put(key, value);
	}
}
