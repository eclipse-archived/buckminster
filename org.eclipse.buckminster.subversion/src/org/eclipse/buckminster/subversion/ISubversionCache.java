package org.eclipse.buckminster.subversion;

import java.util.Map;
import java.util.UUID;

public interface ISubversionCache<SVN_ENTRY_TYPE> {
	public SVN_ENTRY_TYPE[] get(String key);

	public void initialize(Map<UUID, Object> userCache);

	public void put(String key, SVN_ENTRY_TYPE[] value);
}
