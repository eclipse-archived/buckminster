package org.eclipse.buckminster.github.schema;

import java.util.Date;

public class Download {

	private Date createdAt;

	private String description;

	private int downloadCount;

	private String htmlUrl;

	private long size;

	private String name;

	private String id;

	private String url;

	public Date getCreatedAt() {
		return createdAt;
	}

	public String getDescription() {
		return description;
	}

	public int getDownloadCount() {
		return downloadCount;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public long getSize() {
		return size;
	}

	public String getUrl() {
		return url;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
