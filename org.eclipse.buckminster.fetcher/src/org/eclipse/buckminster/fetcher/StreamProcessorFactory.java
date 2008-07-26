package org.eclipse.buckminster.fetcher;

import java.net.URL;
import java.util.List;

import org.eclipse.core.runtime.CoreException;

/**
 * Provides {@link IResourceFetcher} instances
 * 
 * @author Guillaume CHATELET
 */
public class StreamProcessorFactory
{

	public static IResourceFetcher getCopyInstance(URL url, String toDir, String filename)
	{
		return new SimpleResourceFetcher(url, toDir, filename);
	}

	public static IResourceFetcher getUncompressInstance(URL url, String toDir, List<String> includes,
			boolean shouldFlatten) throws CoreException
	{
		return new ArchivedResourceFetcher(url, toDir, shouldFlatten, includes);
	}
}
