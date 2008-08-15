/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.opml.builder.OPMLBuilder;
import org.eclipse.buckminster.opml.model.OPML;
import org.eclipse.buckminster.runtime.IFileInfo;

/**
 * @author Thomas Hallgren
 */
public class ResolutionBuilder
{
	private final List<String> m_attributes = new ArrayList<String>();

	private CSpecBuilder m_cspec;

	private String m_componentTypeId;

	private String m_contentType;

	private long m_lastModified;

	private boolean m_materializable;

	private OPMLBuilder m_opml;

	private Provider m_provider;

	private String m_remoteName;

	private String m_repository;

	private ComponentRequest m_request;

	private long m_size;

	private VersionMatch m_versionMatch;

	public void addAttribute(String attribute)
	{
		m_attributes.add(attribute);
	}

	public void clear()
	{
		m_attributes.clear();
		m_componentTypeId = null;
		m_contentType = null;
		m_cspec = null;
		m_lastModified = 0L;
		m_materializable = false;
		m_opml = null;
		m_provider = null;
		m_remoteName = null;
		m_repository = null;
		m_request = null;
		m_size = -1L;
		m_versionMatch = null;
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

	public CSpecBuilder getCSpecBuilder()
	{
		return m_cspec;
	}

	public long getLastModified()
	{
		return m_lastModified;
	}

	public OPMLBuilder getOPMLBuilder()
	{
		return m_opml;
	}

	public Provider getProvider()
	{
		return m_provider;
	}

	public String getRemoteName()
	{
		return m_remoteName;
	}

	public String getRepository()
	{
		return m_repository;
	}

	public ComponentRequest getRequest()
	{
		return m_request;
	}

	public long getSize()
	{
		return m_size;
	}

	public VersionMatch getVersionMatch()
	{
		return m_versionMatch;
	}

	public void initFrom(Resolution resolution)
	{
		clear();
		m_attributes.addAll(resolution.getAttributes());
		m_componentTypeId = resolution.getComponentTypeId();
		m_contentType = resolution.getContentType();
		m_cspec = new CSpecBuilder();
		m_cspec.initFrom(resolution.getCSpec());
		m_lastModified = resolution.getLastModified();
		m_materializable = resolution.isMaterializable();
		OPML opml = resolution.getOPML();
		if(opml != null)
		{
			m_opml = new OPMLBuilder();
			m_opml.initFrom(opml);
		}
		m_provider = resolution.getProvider();
		m_remoteName = resolution.getRemoteName();
		m_repository = resolution.getRepository();
		m_request = resolution.getRequest();
		m_size = resolution.getSize();
		m_versionMatch = resolution.getVersionMatch();
	}

	public boolean isMaterializable()
	{
		return m_materializable;
	}

	public void setAttributes(List<String> attributes)
	{
		m_attributes.clear();
		if(attributes != null)
			m_attributes.addAll(attributes);
	}

	public void setComponentTypeId(String componentTypeId)
	{
		m_componentTypeId = componentTypeId;
	}

	public void setContentType(String contentType)
	{
		m_contentType = contentType;
	}

	public void setCSpecBuilder(CSpecBuilder cspec)
	{
		m_cspec = cspec;
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
			setRemoteName(info.getName());
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

	public void setOpml(OPMLBuilder opml)
	{
		m_opml = opml;
	}

	public void setProvider(Provider provider)
	{
		m_provider = provider;
	}

	public void setRemoteName(String remoteName)
	{
		m_remoteName = remoteName;
	}

	public void setRepository(String repository)
	{
		m_repository = repository;
	}

	public void setRequest(ComponentRequest request)
	{
		m_request = request;
	}

	public void setSize(long size)
	{
		m_size = size;
	}

	public void setVersionMatch(VersionMatch versionMatch)
	{
		m_versionMatch = versionMatch;
	}
}
