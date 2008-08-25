/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.model;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.FilterUtils;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.metadata.IResolution;
import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.builder.ResolutionBuilder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.opml.model.OPML;
import org.eclipse.buckminster.sax.UUIDKeyed;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.osgi.framework.Filter;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class Resolution extends UUIDKeyed implements IUUIDPersisted, IResolution
{
	public static final String ATTR_ATTRIBUTES = "attributes";

	public static final String ATTR_COMPONENT_TYPE = "componentType";

	public static final String ATTR_CONTENT_TYPE = "contentType";

	public static final String ATTR_CSPEC_ID = "cspecId";

	public static final String ATTR_LAST_MODIFIED = "lastModified";

	public static final String ATTR_MATERIALIZABLE = "materializable";

	public static final String ATTR_OPML_ID = "opmlId";

	public static final String ATTR_PROVIDER_ID = "providerId";

	public static final String ATTR_QUERY_ID = "queryId";

	public static final String ATTR_REMOTE_NAME = "remoteName";

	public static final String ATTR_REPOSITORY = "repository";

	public static final String ATTR_SIZE = "size";

	public static final String ATTR_UNPACK = "unpack";

	public static final String ELEM_REQUEST = "request";

	public static final int SEQUENCE_NUMBER = 2;

	public static final String TAG = "resolution";

	private final List<String> m_attributes;

	private final String m_componentTypeId;

	private final String m_contentType;

	private transient CSpec m_cspec;

	private final long m_lastModified;

	private final boolean m_materializable;

	private transient OPML m_opml;

	private transient Provider m_provider;

	private final String m_remoteName;

	private final String m_repository;
	
	private final ComponentRequest m_request;

	private final long m_size;

	private final boolean m_unpack;

	private final VersionMatch m_versionMatch;

	public Resolution(CSpec cspec, OPML opml, Resolution old)
	{
		m_cspec = cspec;
		m_opml = opml;
		m_request = old.getRequest();
		m_attributes = old.getAttributes();
		m_provider = old.getProvider();
		m_componentTypeId = old.getComponentTypeId();
		m_versionMatch = old.getVersionMatch().copyWithVersion(cspec.getVersion());
		m_materializable = old.isMaterializable();
		m_repository = old.getRepository();
		m_remoteName = old.getRemoteName();
		m_contentType = old.getContentType();
		m_lastModified = old.getLastModified();
		m_size = old.getSize();
		m_unpack = old.isUnpack();
	}

	public Resolution(CSpec cspec, OPML opml, String componentTypeId, VersionMatch versionMatch, Provider provider,
		boolean materializeable, ComponentRequest request, List<String> attributes,
		String repository, String remoteName, String contentType, long lastModified, long size, boolean unpack)
	{
		m_cspec = cspec;
		m_opml = opml;
		m_provider = provider;
		m_componentTypeId = componentTypeId;
		m_versionMatch = versionMatch;
		m_materializable = materializeable;
		m_request = request;
		m_attributes = Utils.createUnmodifiableList(attributes);
		m_repository = repository;
		m_remoteName = remoteName;
		m_contentType = contentType;
		m_lastModified = lastModified;
		m_size = size;
		m_unpack = unpack;
	}

	public Resolution(IVersion version, Resolution old)
	{
		m_cspec = old.getCSpec();
		m_opml = old.getOPML();
		m_request = old.getRequest();
		m_attributes = old.getAttributes();
		m_provider = old.getProvider();
		m_componentTypeId = old.getComponentTypeId();
		m_versionMatch = old.getVersionMatch().copyWithVersion(version);
		m_materializable = old.isMaterializable();
		m_repository = old.getRepository();
		m_remoteName = old.getRemoteName();
		m_contentType = old.getContentType();
		m_lastModified = old.getLastModified();
		m_size = old.getSize();
		m_unpack = old.isUnpack();
	}

	public Resolution(ResolutionBuilder bld)
	{
		m_attributes = Utils.createUnmodifiableList(bld.getAttributes());
		m_componentTypeId = bld.getComponentTypeId();
		m_contentType = bld.getContentType();
		m_cspec = bld.getCSpec();
		m_lastModified = bld.getLastModified();
		m_materializable = bld.isMaterializable();
		m_opml = bld.getOPML();
		m_provider = bld.getProvider();
		m_remoteName = bld.getName();
		m_repository = bld.getRepository();
		m_request = bld.getRequest().createComponentRequest();
		m_size = bld.getSize();
		m_versionMatch = bld.getVersionMatch();
		m_unpack = bld.isUnpack();
	}

	@Override
	public void emitElements(ContentHandler handler, String namespace, String prefix)
	throws SAXException
	{
		m_request.toSax(handler, XMLConstants.BM_METADATA_NS, XMLConstants.BM_METADATA_PREFIX, ELEM_REQUEST);
		m_versionMatch.toSax(handler, XMLConstants.BM_METADATA_NS, XMLConstants.BM_METADATA_PREFIX, m_versionMatch.getDefaultTag());
	}

	public String getArtifactInfo()
	{
		return m_versionMatch.getArtifactInfo();
	}

	public URI getArtifactURI(RMContext context) throws CoreException
	{
		return getProvider().getReaderType().getArtifactURL(this, context);
	}

	public List<String> getAttributes()
	{
		return m_attributes;
	}

	public VersionSelector getMatchedBranchOrTag()
	{
		return m_versionMatch.getBranchOrTag();
	}

	/**
	 * Returns the identifier of the contained <code>CSpec</code>.
	 * @return The component identifier
	 * @throws CoreException
	 */
	public ComponentIdentifier getComponentIdentifier()
	{
		return getCSpec().getComponentIdentifier();
	}

	public IComponentType getComponentType() throws CoreException
	{
		return CorePlugin.getDefault().getComponentType(m_componentTypeId);
	}

	public String getComponentTypeId()
	{
		return m_componentTypeId;
	}

	public String getContentType()
	{
		return m_contentType;
	}

	/**
	 * Returns the CSpec at the time when this resolution was created. The actual cspec in the
	 * workspace might have changed since then.
	 * @return The resolved cspec.
	 */
	public CSpec getCSpec()
	{
		return m_cspec;
	}

	/**
	 * Returns the id of the contained CSpec.
	 * @return The id of the contained CSpec.
	 */
	public UUID getCSpecId()
	{
		return m_cspec.getId();
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public long getLastModified()
	{
		return m_lastModified;
	}

	/**
	 * Returns the name of the component.
	 * @return the name.
	 */
	public final String getName()
	{
		return m_request.getName();
	}

	/**
	 * Returns the OPML document
	 * @return The OPML or <code>null</code> if no OPML was present
	 */
	public OPML getOPML()
	{
		return m_opml;
	}

	/**
	 * Returns the id of the contained OPML.
	 * @return The id of the contained OPML or null if no OPML exists.
	 */
	public UUID getOPMLId()
	{
		return m_opml == null ? null : m_opml.getId();
	}

	/**
	 * Returns the provider used when reading the repository.
	 * @return the repository provider.
	 */
	public Provider getProvider()
	{
		return m_provider;
	}

	/**
	 * Returns the id of the contained provider
	 * @return the id of the contained provider
	 */
	public UUID getProviderId()
	{
		return m_provider.getId();
	}

	public ProviderMatch getProviderMatch(RMContext context) throws CoreException
	{
		ProviderMatch pm = new ProviderMatch(
			m_provider, getComponentType(), getVersionMatch(),
			context.getNodeQuery(getQualifiedDependency()));
		pm.setRepositoryURI(m_repository);
		return pm;
	}

	public final QualifiedDependency getQualifiedDependency()
	{
		return new QualifiedDependency(m_request, m_attributes);
	}

	public String getReaderTypeId()
	{
		return getProvider().getReaderTypeId();
	}

	public String getRemoteName()
	{
		return m_remoteName;
	}

	public String getRepository()
	{
		return m_repository;
	}

	/**
	 * @return Returns the properties.
	 */
	public final ComponentRequest getRequest()
	{
		return m_request;
	}

	public Filter getResolutionFilter()
	{
		return getProvider().getResolutionFilter();
	}

	public long getSelectedRevision()
	{
		return m_versionMatch.getRevision();
	}

	public long getSize()
	{
		return m_size;
	}

	/**
	 * Returns the final version that was used when the specification was obtained.
	 * @return the version used when retrieving the spec.
	 */
	public final IVersion getVersion()
	{
		return m_versionMatch.getVersion();
	}

	/**
	 * Returns the original version designator.
	 * @return The original (unresolved) version designator
	 */
	public final IVersionDesignator getVersionDesignator() throws CoreException
	{
		return m_request.getVersionDesignator();
	}

	public VersionMatch getVersionMatch()
	{
		return m_versionMatch;
	}

	/**
	 * Check if the request designates the versioned component that this component info represents.
	 * @param request the request that might appoint the component
	 * @return <code>true</code> if the versioned component is designated
	 * @throws CoreException
	 */
	public boolean isDesignatedBy(ComponentRequest request) throws CoreException
	{
		if(!request.getName().equals(m_request.getName()))
			return false;

		// If the request has a component type then it must match
		//
		String componentType = request.getComponentTypeID();
		if(componentType != null && !componentType.equals(m_request.getComponentTypeID()))
			return false;

		IVersionDesignator vd = request.getVersionDesignator();
		return vd == null ? true : vd.designates(getVersion());
	}

	/**
	 * Returns true if this resolution is a match for the given <code>query</code> with respect
	 * to provided properties. The method will update the filter attributes map of the query
	 * context.
	 * @param The query to match
	 * @return True if this resolution is a match for the given query.
	 * @see RMContext#getFilterAttributeUsageMap()
	 */
	public boolean isFilterMatchFor(NodeQuery query)
	{
		return isFilterMatchFor(query, null);
	}

	/**
	 * Returns true if this resolution is a match for the given <code>query</code> with respect
	 * to provided properties. The method will update the filter attributes map of the query
	 * context.
	 * @param The query to match
	 * @param A one element array that will receive the failing filter. Can be <code>null</code>.
	 * @return True if this resolution is a match for the given query.
	 * @see RMContext#getFilterAttributeUsageMap()
	 */
	public boolean isFilterMatchFor(NodeQuery query, Filter[] failingFilter)
	{
		Map<String,String[]> attributeUsageMap = query.getContext().getFilterAttributeUsageMap();
		Filter resFilter = getProvider().getResolutionFilter();
		FilterUtils.addConsultedAttributes(resFilter, attributeUsageMap);

		Filter cspecFilter = getCSpec().getFilter();
		FilterUtils.addConsultedAttributes(cspecFilter, attributeUsageMap);

		Map<String,String> properties = query.getProperties();
		if(!FilterUtils.isMatch(resFilter, properties))
		{
			if(failingFilter != null)
				failingFilter[0] = resFilter;
			return false;
		}

		if(!FilterUtils.isMatch(cspecFilter, properties))
		{
			if(failingFilter != null)
				failingFilter[0] = cspecFilter;
			return false;
		}
		return true;
	}
	
	/**
	 * Returns <code>true</code> if the reader associated with the component will be able to
	 * materialized the component. Readers that check for the existence of pre-installed components
	 * (such as Eclipse plugins that are already present in the running eclipse installation) will
	 * return <code>false</code>.
	 * @return <code>true</code> if the component can be materialized on disk.
	 */
	public boolean isMaterializable()
	{
		return m_materializable;
	}

	/**
	 * Returns <code>true</code> if the component is materialized at the given location according
	 * to the workspace meta-data.
	 * @return <code>true</code> if the component is materialized.
	 */
	public boolean isMaterialized(IPath location) throws CoreException
	{
		try
		{
			IPath myLocation = getCSpec().getComponentLocation();
			return location.equals(myLocation);
		}
		catch(MissingComponentException e)
		{
			return false;
		}
	}
	
	public boolean isPersisted(StorageManager sm) throws CoreException
	{
		return sm.getResolutions().contains(this);
	}
	
	public boolean isUnpack()
	{
		return m_unpack;
	}

	public void remove(StorageManager sm) throws CoreException
	{
		WorkspaceInfo.clearResolutionCache(getComponentIdentifier());
		sm.getResolutions().removeElement(getId());
	}

	public void store(StorageManager sm) throws CoreException
	{
		WorkspaceInfo.clearResolutionCache(getComponentIdentifier());
		m_cspec.store(sm);
		if(m_opml != null)
			sm.getOPMLs().putElement(m_opml);
		m_provider.store(sm);
		sm.getResolutions().putElement(this);
	}

	public void toSax(ContentHandler receiver) throws SAXException
	{
		receiver.startDocument();
		toSax(receiver, XMLConstants.BM_METADATA_NS, XMLConstants.BM_METADATA_PREFIX,
			getDefaultTag());
		receiver.endDocument();
	}

	@Override
	public void toSax(ContentHandler handler, String namespace, String prefix, String localName)
	throws SAXException
	{
		handler.startPrefixMapping(XMLConstants.BM_METADATA_PREFIX, XMLConstants.BM_METADATA_NS);
		super.toSax(handler, namespace, prefix, localName);
		handler.endPrefixMapping(XMLConstants.BM_METADATA_PREFIX);
	}

	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		result.append("Name: ");
		result.append(m_request.getName());
		result.append(", ");
		m_versionMatch.toString(result);
		return result.toString();
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		Utils.addAttribute(attrs, ATTR_CSPEC_ID, m_cspec.getId().toString());
		if(m_opml != null)
			Utils.addAttribute(attrs, ATTR_OPML_ID, m_opml.getId().toString());

		String tmp = TextUtils.concat(m_attributes, ",");
		if(tmp != null)
			Utils.addAttribute(attrs, ATTR_ATTRIBUTES, tmp);
		Utils.addAttribute(attrs, ATTR_MATERIALIZABLE, m_materializable ? "true" : "false");
		Utils.addAttribute(attrs, ATTR_PROVIDER_ID, m_provider.getId().toString());
		Utils.addAttribute(attrs, ATTR_REPOSITORY, m_repository);

		if(m_componentTypeId != null)
			Utils.addAttribute(attrs, ATTR_COMPONENT_TYPE, m_componentTypeId);
		if(m_remoteName != null)
			Utils.addAttribute(attrs, ATTR_REMOTE_NAME, m_remoteName);
		if(m_contentType != null)
			Utils.addAttribute(attrs, ATTR_CONTENT_TYPE, m_contentType);
		if(m_lastModified != 0L)
			Utils.addAttribute(attrs, ATTR_LAST_MODIFIED, Long.toString(m_lastModified));
		if(m_size != -1L)
			Utils.addAttribute(attrs, ATTR_SIZE, Long.toString(m_size));
		if(m_unpack)
			Utils.addAttribute(attrs, ATTR_UNPACK, "true");
	}

	@Override
	protected String getElementNamespace(String namespace)
	{
		return XMLConstants.BM_METADATA_NS;
	}

	@Override
	protected String getElementPrefix(String prefix)
	{
		return XMLConstants.BM_METADATA_PREFIX;
	}

	public String getSelectedSpace()
	{
		return getVersionMatch().getSpace();
	}

	public Date getSelectedTimestamp()
	{
		return getVersionMatch().getTimestamp();
	}
}
