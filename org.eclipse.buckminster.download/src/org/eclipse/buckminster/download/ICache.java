/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.buckminster.download.internal.Activator;
import org.eclipse.buckminster.runtime.IFileInfo;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public interface ICache
{
	File getLocation();

	IFileInfo getRemoteInfo(URL url) throws CoreException, FileNotFoundException;

	boolean isUpToDate(IFetchPolicy policy, URL remoteFile, IProgressMonitor monitor) throws CoreException, FileNotFoundException;

	boolean isUpToDate(URL remoteFile, String remoteName, IProgressMonitor monitor) throws CoreException, FileNotFoundException;

	boolean isUpToDate(URL remoteFile, URL remoteDigest, String algorithm, IProgressMonitor monitor) throws CoreException, FileNotFoundException;

	/**
	 * <p>This method will first assert that the remoteFile is placed in the cache. It will
	 * then return an stream that reads from the locally cached file.</p>
	 * <p>A file that is placed in the cache must first meet two distinct conditions<ul>
	 * <li>The <code>remoteDigest</code> must be successfully retrieved</li>
	 * <li>The digest calculated on the content of the <code>remoteFile</code> during download must
	 * match the <code>remoteDigest</code></li>
	 * </ul></p>
	 * <p>If a local copy of the digest is found in the cache and that local digest is
	 * not older then 30 seconds, it will be trusted and no connection attempt will be made
	 * to the remote source. In all other cases, the remote digest will be fetched and compared
	 * to the current local copy. A new copy of the <code>remoteFile</code> will be downloaded in case
	 * they don't match.
	 * </p>
	 *
	 * @param remoteFile The the remote file that should be cached
	 * @param remoteDigest The digest of the remote file
	 * @param algorithm The digest algorithm, i.e. SHA1 or MD5
	 * @param monitor A progress monitor tracking the download
	 * @return A stream suitable for reading the local copy of the cached <code>remoteFile</code>.
	 * @throws FileNotFoundException if the remote source could not be found
	 * @throws CoreException
	 */
	InputStream open(URL remoteFile, URL remoteDigest, String algorithm, IProgressMonitor monitor) throws CoreException, FileNotFoundException;

	/**
	 * <p>This method will first assert that the remoteFile is placed in the cache. It will
	 * then return an stream that reads from the locally cached file.</p>
	 * <p>A file that is placed in the cache might be subject to a dry run by {@link IDecompressor}
	 * instances and possibly also by an {@link IExpander}. This will happen if the remoteName
	 * matches decompressors and expanders that has been registered with their respective extension points
	 * {@link Activator#DECOMPRESSORS_POINT} and {@link Activator#EXPANDERS_POINT}.
	 * <p>The cached file will be trusted as long as its size is equal to the size of the remote file
	 * and its timestamp is younger then the timestamp of the remote file.</p>
	 *
	 * @param remoteFile The the remote file that should be cached
	 * @param remoteName This parameter is normally <code>null</code> but it can be used as an override in
	 * case the name obtained from the connection response cannot be trusted. 
	 * @param monitor A progress monitor tracking the download
	 * @return A stream suitable for reading the local copy of the cached <code>remoteFile</code>.
	 * @throws CoreException
	 */
	InputStream open(URL remoteFile, String remoteName, IProgressMonitor monitor) throws CoreException, FileNotFoundException;

	/**
	 * <p>This method will first assert that the remoteFile is placed in the cache. It will
	 * then return an stream that reads from the locally cached file.</p>
	 * <p>The cached file is kept up to date using the specified fetchPolicy.</p>
	 * @param fetchPolicy The policy used to keep the locally cached file up to date.
	 * @param remoteFile The the remote file that should be cached
	 * @param monitor A progress monitor tracking the download
	 *
	 * @return A stream suitable for reading the local copy of the cached <code>remoteFile</code>.
	 * @throws CoreException
	 */
	InputStream open(IFetchPolicy fetchPolicy, URL remoteFile, IProgressMonitor monitor) throws CoreException, FileNotFoundException;
	
	InputStream openRemote(URL remoteFile) throws CoreException, FileNotFoundException;
}
