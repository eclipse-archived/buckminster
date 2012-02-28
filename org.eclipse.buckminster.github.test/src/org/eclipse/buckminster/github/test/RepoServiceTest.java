package org.eclipse.buckminster.github.test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.Download;
import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.service.DownloadService;
import org.junit.Assert;
import org.junit.Test;

public class RepoServiceTest extends ServiceTest {

	private static String fileName = "testfile";

	private int findTestFileId(DownloadService downloadService) throws IOException {
		List<Download> downloads = downloadService.getDownloads(getRepository());
		for(Download download : downloads) {
			if(fileName.equals(download.getName()))
				return download.getId();		
		}
		return -1;
	}

	@Test
	public void testDownload() throws Exception {
		IRepositoryIdProvider repo = getRepository();
		DownloadService downloadService = new DownloadService(getGitHubClient());
		int id = findTestFileId(downloadService);
		if(id >= 0)
			downloadService.deleteDownload(repo, id);
	
		// Test upload of a file
		File testFile = getTestData(fileName + ".txt");
		Download download = new Download();
		download.setDescription("A test file uploaded with an API call");
		download.setName(fileName);
		download.setContentType("text/plain");
		downloadService.createDownload(repo, download, testFile);

		// Verify that the file is present
		id = findTestFileId(downloadService);
		Assert.assertFalse("uploaded file not present", id < 0);

		// Delete the file again
		downloadService.deleteDownload(repo, id);
		id = findTestFileId(downloadService);
		Assert.assertTrue("uploaded file still present after delete", id < 0);
	}
}
