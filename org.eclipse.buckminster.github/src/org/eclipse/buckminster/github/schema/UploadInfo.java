package org.eclipse.buckminster.github.schema;

import java.util.Date;

public class UploadInfo {

	private String url;

	private String htmlUrl;

	private String id;

	private String name;

	private String description;

	private long size;

	private int downloadCount;

	private String policy;

	private String signature;

	private String bucket;

	private String accesskeyid;

	private String path;

	private String acl;

	private Date expirationdate;

	private String prefix;

	private String mimeType;

	private boolean redirect;

	private String s3Url;

	public String getAccesskeyid() {
		return accesskeyid;
	}

	public String getAcl() {
		return acl;
	}

	public String getBucket() {
		return bucket;
	}

	public String getDescription() {
		return description;
	}

	public int getDownloadCount() {
		return downloadCount;
	}

	public Date getExpirationdate() {
		return expirationdate;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public String getId() {
		return id;
	}

	public String getMimeType() {
		return mimeType;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public String getPolicy() {
		return policy;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getS3Url() {
		return s3Url;
	}

	public String getSignature() {
		return signature;
	}

	public long getSize() {
		return size;
	}

	public String getUrl() {
		return url;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setAccesskeyid(String accesskeyid) {
		this.accesskeyid = accesskeyid;
	}

	public void setAcl(String acl) {
		this.acl = acl;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

	public void setExpirationdate(Date expirationdate) {
		this.expirationdate = expirationdate;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	public void setS3Url(String s3Url) {
		this.s3Url = s3Url;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
