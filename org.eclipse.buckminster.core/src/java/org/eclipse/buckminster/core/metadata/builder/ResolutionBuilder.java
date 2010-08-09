/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.metadata.IResolution;
import org.eclipse.buckminster.core.resolver.ResourceMapResolver;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.buckminster.runtime.IFileInfo;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 */
public class ResolutionBuilder implements IResolution {
	private String artifactInfo;

	private final List<String> attributes = new ArrayList<String>();

	private VersionSelector branchOrTag;

	private String componentTypeId;

	private String contentType;

	private final CSpecBuilder cspec;

	private long lastModified;

	private boolean materializable = true;

	private String remoteName;

	private Provider provider;

	private String readerTypeId;

	private String repository;

	private final ComponentRequest request = CommonFactory.eINSTANCE.createComponentRequest();

	private Filter resolutionFilter;

	private String revision;

	private long size = -1;

	private Date timestamp;

	private boolean unpack = false;

	private String persistentId;

	public ResolutionBuilder() {
		this(new CSpecBuilder());
	}

	public ResolutionBuilder(CSpecBuilder cspecBuilder) {
		cspec = cspecBuilder;
	}

	public void addAttribute(String attribute) {
		attributes.add(attribute);
	}

	public void clear() {
		artifactInfo = null;
		attributes.clear();
		branchOrTag = null;
		componentTypeId = null;
		contentType = null;
		cspec.clear();
		lastModified = 0L;
		materializable = true;
		remoteName = null;
		provider = null;
		readerTypeId = null;
		repository = null;
		setRequest(null);
		resolutionFilter = null;
		revision = null;
		size = -1L;
		timestamp = null;
		unpack = false;
	}

	@Override
	public String getArtifactInfo() {
		return artifactInfo;
	}

	@Override
	public List<String> getAttributes() {
		return attributes;
	}

	@Override
	public String getComponentTypeId() {
		return componentTypeId;
	}

	@Override
	public String getContentType() {
		return contentType;
	}

	@Override
	public CSpec getCSpec() {
		return cspec.createCSpec();
	}

	public CSpecBuilder getCSpecBuilder() {
		return cspec;
	}

	@Override
	public long getLastModified() {
		return lastModified;
	}

	@Override
	public VersionSelector getMatchedBranchOrTag() {
		return branchOrTag;
	}

	@Override
	public String getPersistentId() {
		return persistentId;
	}

	@Override
	public Provider getProvider() {
		if (provider == null) {
			String componentType = cspec.getComponentTypeID();
			if (componentType == null)
				componentType = IComponentType.UNKNOWN;
			return ResourceMapResolver.immutableProvider(readerTypeId, componentType, repository, resolutionFilter);
		}
		return provider;
	}

	@Override
	public String getReaderTypeId() {
		return readerTypeId;
	}

	@Override
	public String getRemoteName() {
		return remoteName;
	}

	@Override
	public String getRepository() {
		return repository;
	}

	@Override
	public ComponentRequest getRequest() {
		return request;
	}

	@Override
	public Filter getResolutionFilter() {
		return resolutionFilter;
	}

	@Override
	public String getSelectedRevision() {
		return revision;
	}

	@Override
	public Date getSelectedTimestamp() {
		return timestamp;
	}

	@Override
	public long getSize() {
		return size;
	}

	@Override
	public VersionMatch getVersionMatch() {
		return new VersionMatch(cspec.getVersion(), branchOrTag, revision, timestamp, artifactInfo);
	}

	public void initFrom(IResolution resolution) throws CoreException {
		clear();
		attributes.addAll(resolution.getAttributes());
		componentTypeId = resolution.getComponentTypeId();
		contentType = resolution.getContentType();
		cspec.initFrom(resolution.getCSpec());
		lastModified = resolution.getLastModified();
		materializable = resolution.isMaterializable();
		persistentId = resolution.getPersistentId();
		provider = resolution.getProvider();
		resolutionFilter = resolution.getResolutionFilter();
		remoteName = resolution.getRemoteName();
		repository = resolution.getRepository();
		setRequest(resolution.getRequest());
		size = resolution.getSize();
		artifactInfo = resolution.getArtifactInfo();
		branchOrTag = resolution.getMatchedBranchOrTag();
		revision = resolution.getSelectedRevision();
		timestamp = resolution.getSelectedTimestamp();
		unpack = resolution.isUnpack();
	}

	@Override
	public boolean isMaterializable() {
		return materializable;
	}

	@Override
	public boolean isUnpack() {
		return unpack;
	}

	public void setArtifactInfo(String artifactInfo) {
		this.artifactInfo = artifactInfo;
	}

	public void setAttributes(List<String> attrs) {
		attributes.clear();
		if (attrs != null)
			attributes.addAll(attrs);
	}

	public void setBranchOrTag(VersionSelector branchOrTag) {
		this.branchOrTag = branchOrTag;
	}

	public void setComponentTypeId(String componentTypeId) {
		this.componentTypeId = componentTypeId;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setFileInfo(IFileInfo info) {
		if (info == null) {
			setContentType(null);
			setRemoteName(null);
			setLastModified(0);
			setSize(-1);
		} else {
			setContentType(info.getContentType());
			setRemoteName(info.getRemoteName());
			setLastModified(info.getLastModified());
			setSize(info.getSize());

		}
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public void setMaterializable(boolean materializable) {
		this.materializable = materializable;
	}

	public void setPersistentId(String persistentId) {
		this.persistentId = persistentId;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public void setReaderTypeId(String readerTypeId) {
		this.readerTypeId = readerTypeId;
	}

	public void setRemoteName(String remoteName) {
		this.remoteName = remoteName;
	}

	public void setRepository(String repository) {
		this.repository = repository;
	}

	public final void setRequest(ComponentRequest cr) {
		if (cr == null) {
			request.setId(null);
			request.setType(null);
			request.setRange(null);
			request.setFilter(null);
		} else {
			request.setId(cr.getId());
			request.setType(cr.getType());
			request.setRange(cr.getRange());
			request.setFilter(cr.getFilter());
		}
	}

	public void setResolutionFilter(Filter resolutionFilter) {
		this.resolutionFilter = resolutionFilter;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public void setUnpack(boolean unpack) {
		this.unpack = unpack;
	}

	public void setVersionMatch(VersionMatch versionMatch) {
		if (versionMatch == null) {
			artifactInfo = null;
			branchOrTag = null;
			revision = null;
			timestamp = null;
		} else {
			artifactInfo = versionMatch.getArtifactInfo();
			branchOrTag = versionMatch.getBranchOrTag();
			revision = versionMatch.getRevision();
			timestamp = versionMatch.getTimestamp();
		}
	}
}
