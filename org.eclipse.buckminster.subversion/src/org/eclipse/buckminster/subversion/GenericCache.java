package org.eclipse.buckminster.subversion;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GenericCache
{

	/**
	 * Create a string in the form &quot;url[revision]&quot;
	 * 
	 * @param url
	 *            The url to append
	 * @param revision
	 *            The revision to append
	 * @return A string representation denoting an explicit revision of the URL
	 */
	public static String cacheKey(URI url, Object revision)
	{
		StringBuilder bld = new StringBuilder();
		String protocol = url.getScheme();
		int port = url.getPort();
		bld.append(protocol);
		bld.append("://"); //$NON-NLS-1$
		if(url.getHost() != null)
		{
			bld.append(url.getHost());
			if(port != -1)
			{
				bld.append(":"); //$NON-NLS-1$
				bld.append(port);
			}
		}

		bld.append(url.getPath());
		bld.append('#');
		bld.append(revision);
		return bld.toString();
	}

	@SuppressWarnings("unchecked")
	public static <ENTRY_TYPE> Map<String, ENTRY_TYPE> getCache(Map<UUID, Object> ctxUserCache, final UUID CacheKey)
	{
		synchronized(ctxUserCache)
		{
			Map<String, ENTRY_TYPE> listCache = (Map<String, ENTRY_TYPE>)ctxUserCache.get(CacheKey);
			if(listCache == null)
			{
				listCache = Collections.synchronizedMap(new HashMap<String, ENTRY_TYPE>());
				ctxUserCache.put(CacheKey, listCache);
			}
			return listCache;
		}
	}
}
