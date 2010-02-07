package org.eclipse.buckminster.subclipse.internal;

import java.util.Map;
import java.util.UUID;

import org.eclipse.buckminster.subversion.GenericCache;
import org.eclipse.buckminster.subversion.ISubversionCache;
import org.tigris.subversion.svnclientadapter.ISVNDirEntry;

public class SvnCache implements ISubversionCache<ISVNDirEntry> {
	private Map<String, ISVNDirEntry> dirCache;

	private Map<String, ISVNDirEntry[]> listCache;

	private static final UUID CACHE_KEY_LIST_CACHE = UUID.randomUUID();

	private static final UUID CACHE_KEY_DIR_CACHE = UUID.randomUUID();

	public boolean dirContainsKey(String key) {
		return dirCache.containsKey(key);
	}

	public ISVNDirEntry[] get(String key) {
		return listCache.get(key);
	}

	public ISVNDirEntry getDir(String key) {
		return dirCache.get(key);
	}

	public void initialize(Map<UUID, Object> userCache) {
		dirCache = GenericCache.getCache(userCache, CACHE_KEY_DIR_CACHE);
		listCache = GenericCache.getCache(userCache, CACHE_KEY_LIST_CACHE);
	}

	public void put(String key, ISVNDirEntry[] value) {
		listCache.put(key, value);
	}

	public void putDir(String key, ISVNDirEntry value) {
		dirCache.put(key, value);
	}
}
