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
import org.eclipse.buckminster.core.cspec.builder.ComponentRequestBuilder;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.metadata.IResolution;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.opml.IOPML;
import org.eclipse.buckminster.opml.builder.OPMLBuilder;
import org.eclipse.buckminster.opml.model.OPML;
import org.eclipse.buckminster.runtime.IFileInfo;
import org.osgi.framework.Filter;

/**
 * @author Thomas Hallgren
 */
public class ResolutionBuilder implements IResolution
{
	private String m_artifactInfo;

	private final List<String> m_attributes = new ArrayList<String>();

	private VersionSelector m_branchOrTag;

	private String m_componentTypeId;

	private String m_contentType;

	private final CSpecBuilder m_cspec;

	private long m_lastModified;

	private boolean m_materializable = true;

	private String m_remoteName;

	private OPMLBuilder m_opml;

	private Provider m_provider;

	private String m_readerTypeId;

	private String m_repository;

	private final ComponentRequestBuilder m_request = new ComponentRequestBuilder();

	private Filter m_resolutionFilter;

	private long m_revision;

	private long m_size = -1;

	private Date m_timestamp;

	private boolean m_unpack = false;

	private String m_persistentId;

	public ResolutionBuilder()
	{
		this(new CSpecBuilder(), null);
	}

	public ResolutionBuilder(CSpecBuilder cspecBuilder, OPMLBuilder opmlBuilder)
	{
		m_cspec = cspecBuilder;
		m_opml = opmlBuilder;
	}

	public void addAttribute(String attribute)
	{
		m_attributes.add(attribute);
	}

	public void clear()
	{
		m_artifactInfo = null;
		m_attributes.clear();
		m_branchOrTag = null;
		m_componentTypeId = null;
		m_contentType = null;
		m_cspec.clear();
		m_lastModified = 0L;
		m_materializable = true;
		m_remoteName = null;
		m_opml = null;
		m_provider = null;
		m_readerTypeId = null;
		m_repository = null;
		m_request.clear();
		m_resolutionFilter = null;
		m_revision = 0L;
		m_size = -1L;
		m_timestamp = null;
		m_unpack = false;
	}

	public String getArtifactInfo()
	{
		return m_artifactInfo;
	}

	public List<String> getAttributes()
	{
		return m_attributes;
	}

	public String getComponentTypeId()
	{
		return m_componentTypeId;
	}

	public String getContentType()
	{
		return m_contentType;
	}

	public CSpec getCSpec()
	{
		return m_cspec.createCSpec();
	}

	public CSpecBuilder getCSpecBuilder()
	{
		return m_cspec;
	}

	public long getLastModified()
	{
		return m_lastModified;
	}

	public VersionSelector getMatchedBranchOrTag()
	{
		return m_branchOrTag;
	}

	public OPML getOPML()
	{
		return m_opml == null
				? null
				: new OPML(m_opml);
	}

	public OPMLBuilder getOPMLBuilder()
	{
		if(m_opml == null)
			m_opml = new OPMLBuilder();
		return m_opml;
	}

	public String getPersistentId()
	{
		return m_persistentId;
	}

	public Provider getProvider()
	{
		if(m_provider == null)
		{
			String componentType = m_cspec.getComponentTypeID();
			if(componentType == null)
				componentType = IComponentType.UNKNOWN;
			return new Provider(m_readerTypeId, new String[] { componentType }, m_repository, m_resolutionFilter);
		}
		return m_provider;
	}

	public String getReaderTypeId()
	{
		return m_readerTypeId;
	}

	public String getRemoteName()
	{
		return m_remoteName;
	}

	public String getRepository()
	{
		return m_repository;
	}

	public ComponentRequestBuilder getRequest()
	{
		return m_request;
	}

	public Filter getResolutionFilter()
	{
		return m_resolutionFilter;
	}

	public long getSelectedRevision()
	{
		return m_revision;
	}

	public Date getSelectedTimestamp()
	{
		return m_timestamp;
	}

	public long getSize()
	{
		return m_size;
	}

	public VersionMatch getVersionMatch()
	{
		return new VersionMatch(m_cspec.getVersion(), m_branchOrTag, m_revision, m_timestamp, m_artifactInfo);
	}

	public void initFrom(IResolution resolution)
	{
		clear();
		m_attributes.addAll(resolution.getAttributes());
		m_componentTypeId = resolution.getComponentTypeId();
		m_contentType = resolution.getContentType();
		m_cspec.initFrom(resolution.getCSpec());
		m_lastModified = resolution.getLastModified();
		m_materializable = resolution.isMaterializable();
		IOPML opml = resolution.getOPML();
		if(opml != null)
		{
			m_opml = new OPMLBuilder();
			m_opml.initFrom(opml);
		}
		m_persistentId = resolution.getPersistentId();
		m_provider = resolution.getProvider();
		m_resolutionFilter = resolution.getResolutionFilter();
		m_remoteName = resolution.getRemoteName();
		m_repository = resolution.getRepository();
		m_request.initFrom(resolution.getRequest());
		m_size = resolution.getSize();
		m_artifactInfo = resolution.getArtifactInfo();
		m_branchOrTag = resolution.getMatchedBranchOrTag();
		m_revision = resolution.getSelectedRevision();
		m_timestamp = resolution.getSelectedTimestamp();
		m_unpack = resolution.isUnpack();
	}

	public boolean isMaterializable()
	{
		return m_materializable;
	}

	public boolean isUnpack()
	{
		return m_unpack;
	}

	public void setArtifactInfo(String artifactInfo)
	{
		m_artifactInfo = artifactInfo;
	}

	public void setAttributes(List<String> attributes)
	{
		m_attributes.clear();
		if(attributes != null)
			m_attributes.addAll(attributes);
	}

	public void setBranchOrTag(VersionSelector branchOrTag)
	{
		m_branchOrTag = branchOrTag;
	}

	public void setComponentTypeId(String componentTypeId)
	{
		m_componentTypeId = componentTypeId;
	}

	public void setContentType(String contentType)
	{
		m_contentType = contentType;
	}

	public void setFileInfo(IFileInfo info)
	{
		if(info == null)
		{
			setContentType(null);
			setRemoteName(null);
			setLastModified(0);
			setSize(-1);
		}
		else
		{
			setContentType(info.getContentType());
			setRemoteName(info.getRemoteName());
			setLastModified(info.getLastModified());
			setSize(info.getSize());

		}
	}

	public void setLastModified(long lastModified)
	{
		m_lastModified = lastModified;
	}

	public void setMaterializable(boolean materializable)
	{
		m_materializable = materializable;
	}

	public void setPersistentId(String persistentId)
	{
		m_persistentId = persistentId;
	}

	public void setProvider(Provider provider)
	{
		m_provider = provider;
	}

	public void setReaderTypeId(String readerTypeId)
	{
		m_readerTypeId = readerTypeId;
	}

	public void setRemoteName(String remoteName)
	{
		m_remoteName = remoteName;
	}

	public void setRepository(String repository)
	{
		m_repository = repository;
	}

	public void setResolutionFilter(Filter resolutionFilter)
	{
		m_resolutionFilter = resolutionFilter;
	}

	public void setRevision(long revision)
	{
		m_revision = revision;
	}

	public void setSize(long size)
	{
		m_size = size;
	}

	public void setTimestamp(Date timestamp)
	{
		m_timestamp = timestamp;
	}

	public void setUnpack(boolean unpack)
	{
		m_unpack = unpack;
	}

	public void setVersionMatch(VersionMatch versionMatch)
	{
		if(versionMatch == null)
		{
			m_artifactInfo = null;
			m_branchOrTag = null;
			m_revision = 0;
			m_timestamp = null;
		}
		else
		{
			m_artifactInfo = versionMatch.getArtifactInfo();
			m_branchOrTag = versionMatch.getBranchOrTag();
			m_revision = versionMatch.getRevision();
			m_timestamp = versionMatch.getTimestamp();
		}
	}
}
