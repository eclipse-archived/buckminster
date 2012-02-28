package org.eclipse.buckminster.github.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.eclipse.buckminster.github.GitHubException;
import org.eclipse.buckminster.github.schema.Download;
import org.eclipse.buckminster.github.schema.UploadInfo;
import org.eclipse.buckminster.github.schema.UploadRequest;
import org.eclipse.buckminster.github.service.RepoService;

public class RepoServiceImpl extends AbstractService implements RepoService {
	public static final String DOWNLOADS_URL = "https://api.github.com/repos/%s/%s/downloads"; //$NON-NLS-1$

	public static final String DOWNLOAD_ID_URL = "https://api.github.com/repos/%s/%s/downloads/%s"; //$NON-NLS-1$

	public static final String POST_OBJECT_URL = "https://github.s3.amazonaws.com/"; //$NON-NLS-1$

	private static final Charset UTF_8 = Charset.forName("UTF-8"); //$NON-NLS-1$

	protected RepoServiceImpl(String accessToken) {
		super(accessToken);
	}

	@Override
	public void deleteDownload(String userName, String repositoryName, String id) throws GitHubException {
		callApiMethod(HttpDelete.METHOD_NAME, String.format(DOWNLOAD_ID_URL, userName, repositoryName, id), null, null, 204);
	}

	@Override
	public List<Download> getDownloads(String userName, String repositoryName) throws GitHubException {
		return unmarshall(new TypeToken<List<Download>>() {}, callApiGet(String.format(DOWNLOADS_URL, userName, repositoryName)));
	}

	@Override
	public void upload(String userName, String repositoryName, String fileName, String description, String contentType, File file)
			throws GitHubException {
		UploadRequest request = new UploadRequest();
		request.setName(fileName);
		request.setDescription(description);
		request.setContentType(contentType);
		request.setSize(file.length());

		String jsonStr = getGsonBuilder().create().toJson(request);
		UploadInfo info = unmarshall(UploadInfo.class,
				callApiMethod(HttpPost.METHOD_NAME, String.format(DOWNLOADS_URL, userName, repositoryName), jsonStr, "application/json", 201)); //$NON-NLS-1$

		MultipartEntity mpEntity = new MultipartEntity();
		try {
			mpEntity.addPart("key", new StringBody(info.getPath(), UTF_8)); //$NON-NLS-1$
			mpEntity.addPart("acl", new StringBody(info.getAcl(), UTF_8)); //$NON-NLS-1$
			mpEntity.addPart("success_action_status", new StringBody("201", UTF_8)); //$NON-NLS-1$ //$NON-NLS-2$
			mpEntity.addPart("Filename", new StringBody(info.getName(), UTF_8)); //$NON-NLS-1$
			mpEntity.addPart("AWSAccessKeyId", new StringBody(info.getAccesskeyid(), UTF_8)); //$NON-NLS-1$
			mpEntity.addPart("Policy", new StringBody(info.getPolicy(), UTF_8)); //$NON-NLS-1$
			mpEntity.addPart("Signature", new StringBody(info.getSignature(), UTF_8)); //$NON-NLS-1$
			mpEntity.addPart("Content-Type", new StringBody(info.getMimeType())); //$NON-NLS-1$
			mpEntity.addPart("file", new FileBody(file, info.getMimeType())); //$NON-NLS-1$
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost post = new HttpPost(POST_OBJECT_URL);
			post.setEntity(mpEntity);
			HttpResponse response = httpClient.execute(post);
			StatusLine status = response.getStatusLine();
			if (status.getStatusCode() != 201)
				throw new GitHubException(status.getReasonPhrase());
		} catch (IOException e) {
			throw new GitHubException(e);
		}
	}
}
