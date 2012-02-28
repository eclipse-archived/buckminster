package org.eclipse.buckminster.github.service;

import java.io.File;
import java.util.List;

import org.eclipse.buckminster.github.GitHubException;
import org.eclipse.buckminster.github.schema.Download;

public interface RepoService {
	/**
	 * Delete the download with the specified <code>id</code> from the given
	 * owner/repository
	 * 
	 * @param owner
	 *            The owner of the repository
	 * @param repository
	 *            The name of the repository
	 * @param id
	 *            The id of the download
	 * @see {@link Download#getId()}
	 */
	void deleteDownload(String owner, String repository, String id) throws GitHubException;

	/**
	 * Returns the downloads that have been created for the given
	 * owner/repository
	 * 
	 * @param owner
	 *            The owner of the repository
	 * @param repository
	 *            The name of the repository
	 * @return A list of downloads, possibly empty
	 * @throws GitHubException
	 */
	List<Download> getDownloads(String owner, String repository) throws GitHubException;

	/**
	 * Upload a new file to the downloads area of the repository identified by
	 * <code>owner</code> and <code>repositoryName</code>.
	 * 
	 * @param owner
	 *            The owner (GitHub organization or user) of the repository
	 * @param repository
	 *            The name of the repository
	 * @param fileName
	 *            The name of the file as it will be displayed on the downloads
	 *            page
	 * @param description
	 *            A brief description of the download
	 * @param contentType
	 *            The content type of the file
	 * @param file
	 *            The file to upload to the download area
	 * @throws GitHubException
	 *             If the upload failed for some reason
	 */
	void upload(String owner, String repository, String fileName, String description, String contentType, File file) throws GitHubException;
}
