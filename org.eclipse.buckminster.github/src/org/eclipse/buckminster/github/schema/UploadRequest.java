package org.eclipse.buckminster.github.schema;

public class UploadRequest {

	private String name;

	private long size;

	private String description;

	private String contentType;

	public String getContentType() {
		return contentType;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public long getSize() {
		return size;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSize(long size) {
		this.size = size;
	}
}
