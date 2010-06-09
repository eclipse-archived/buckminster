package org.eclipse.buckminster.fetcher;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * Interface for fetching resources
 * 
 * @author Guillaume CHATELET
 */
public interface IResourceFetcher {
	/**
	 * Actually fetches the resource
	 * 
	 * @param monitor
	 * @throws IOException
	 * @throws CoreException
	 */
	public void fetch(IProgressMonitor monitor) throws IOException, CoreException;

	/**
	 * Set credential for this resource
	 * 
	 * @param login
	 * @param password
	 */
	public void setBasicAuthCredential(String login, String password);
}
