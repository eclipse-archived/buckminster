/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.model;

import java.util.Set;
import java.util.UUID;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.metadata.ISaxableStorage;
import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.metadata.ReferentialIntegrityException;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IFileReader;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.IFileInfo;
import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class Resolution extends UUIDKeyed implements ISaxable, ISaxableElement
{
	public static final String TAG = "resolution";

	public static final String ATTR_ATTRIBUTES = "attributes";

	public static final String ATTR_CSPEC_ID = "cspecId";

	public static final String ATTR_MATERIALIZABLE = "materializable";

	public static final String ATTR_PROVIDER_ID = "providerId";

	public static final String ATTR_COMPONENT_TYPE = "componentType";

	public static final String ATTR_QUERY_ID = "queryId";

	public static final String ATTR_REPOSITORY = "repository";

	public static final String ATTR_REMOTE_NAME = "remoteName";

	public static final String ATTR_CONTENT_TYPE = "contentType";

	public static final String ATTR_SIZE = "size";

	public static final String ELEM_REQUEST = "request";

	public static final int SEQUENCE_NUMBER = 2;

	private final VersionMatch m_versionMatch;

	private final boolean m_materializable;

	private final ComponentRequest m_request;

	private final UUID m_providerId;

	private final Set<String> m_attributes;

	private final String m_repository;

	private final String m_componentTypeId;

	private final UUID m_cspecId;

	private final String m_remoteName;

	private final String m_contentType;

	private final long m_size;

	private transient CSpec m_cspec;

	private transient Provider m_provider;

	public Resolution(CSpec cspec, IComponentReader reader) throws CoreException
	{
		ProviderMatch providerMatch = reader.getProviderMatch();
		Provider provider = providerMatch.getProvider();
		NodeQuery nq = providerMatch.getNodeQuery();
		m_cspec = cspec;
		m_cspecId = cspec.getId();
		m_componentTypeId = providerMatch.getComponentType().getId();
		m_request = nq.getComponentRequest();
		m_attributes = UUIDKeyed.createUnmodifiableSet(nq.getRequiredAttributes());
		m_provider = provider;
		m_providerId = provider.getId();
		m_versionMatch = providerMatch.getVersionMatch();
		m_materializable = reader.canMaterialize();
		m_repository = providerMatch.getRepositoryURI();
		
		String contentType = null;
		String remoteName = null;
		long size = -1L;
		if(reader instanceof IFileReader)
		{
			IFileInfo fileInfo = ((IFileReader)reader).getFileInfo();
			if(fileInfo != null)
			{
				contentType = fileInfo.getContentType();
				remoteName = fileInfo.getName();
				size = fileInfo.getSize();
			}
		}
		m_remoteName = remoteName;
		m_contentType = contentType;
		m_size = size;
	}

	public Resolution(CSpec cspec, Resolution old) throws CoreException
	{
		m_cspec = cspec;
		m_cspecId = cspec.getId();
		m_request = old.getRequest();
		m_attributes = old.getAttributes();
		m_provider = old.getProvider();
		m_componentTypeId = old.getComponentTypeId();
		m_providerId = old.getProviderId();
		m_versionMatch = old.getVersionMatch().copyWithVersion(cspec.getVersion());
		m_materializable = old.isMaterializable();
		m_repository = old.getRepository();
		m_remoteName = old.getRemoteName();
		m_contentType = old.getContentType();
		m_size = old.getSize();
	}

	public Resolution(IVersion version, Resolution old) throws CoreException
	{
		m_cspec = old.getCSpec();
		m_cspecId = old.getCSpecId();
		m_request = old.getRequest();
		m_attributes = old.getAttributes();
		m_provider = old.getProvider();
		m_componentTypeId = old.getComponentTypeId();
		m_providerId = old.getProviderId();
		m_versionMatch = old.getVersionMatch().copyWithVersion(version);
		m_materializable = old.isMaterializable();
		m_repository = old.getRepository();
		m_remoteName = old.getRemoteName();
		m_contentType = old.getContentType();
		m_size = old.getSize();
	}

	public Resolution(UUID cspecId, String componentTypeId, VersionMatch versionMatch, UUID providerId,
		boolean materializeable, ComponentRequest request, Set<String> attributes,
		String repository, String remoteName, String contentType, long size)
	{
		m_cspecId = cspecId;
		m_providerId = providerId;
		m_componentTypeId = componentTypeId;
		m_versionMatch = versionMatch;
		m_materializable = materializeable;
		m_request = request;
		m_attributes = UUIDKeyed.createUnmodifiableSet(attributes);
		m_repository = repository;
		m_remoteName = remoteName;
		m_contentType = contentType;
		m_size = size;
	}

	public Set<String> getAttributes()
	{
		return m_attributes;
	}

	/**
	 * Returns the identifier of the contained <code>CSpec</code>.
	 * @return The component identifier
	 * @throws CoreException
	 */
	public ComponentIdentifier getComponentIdentifier() throws CoreException
	{
		return getCSpec().getComponentIdentifier();
	}

	/**
	 * Returns the CSpec at the time when this resolution was created. The actual cspec in the
	 * workspace might have changed since then.
	 * @return The resolved cspec.
	 */
	public synchronized CSpec getCSpec() throws CoreException
	{
		if(m_cspec == null)
			m_cspec = StorageManager.getDefault().getCSpecs().getElement(m_cspecId);
		return m_cspec;
	}

	public String getComponentTypeId()
	{
		return m_componentTypeId;
	}

	public IComponentType getComponentType() throws CoreException
	{
		return CorePlugin.getDefault().getComponentType(m_componentTypeId);
	}

	public String getContentType()
	{
		return m_contentType;
	}

	/**
	 * Returns the id of the CSpec at the time when this resolution was created. The actual cspec in
	 * the workspace might have changed since then.
	 * @return The id of the resolved cspec.
	 */
	public UUID getCSpecId()
	{
		return m_cspecId;
	}

	public String getDefaultTag()
	{
		return TAG;
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
	 * Returns the provider used when reading the repository.
	 * @return the repository provider.
	 */
	public synchronized Provider getProvider() throws CoreException
	{
		if(m_provider == null)
			m_provider = StorageManager.getDefault().getProviders().getElement(m_providerId);
		return m_provider;
	}

	/**
	 * Returns the persistent id of the provider used when reading the repository.
	 * @return the persistent id of the repository provider.
	 */
	public final UUID getProviderId()
	{
		return m_providerId;
	}

	public String getRepository()
	{
		return m_repository;
	}

	public String getRemoteName()
	{
		return m_remoteName;
	}

	public final QualifiedDependency getQualifiedDependency()
	{
		return new QualifiedDependency(m_request, m_attributes);
	}

	public long getSize()
	{
		return m_size;
	}

	/**
	 * @return Returns the properties.
	 */
	public final ComponentRequest getRequest()
	{
		return m_request;
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

	public boolean isPersisted() throws CoreException
	{
		return getStorage().contains(this);
	}

	public void remove() throws CoreException
	{
		WorkspaceInfo.clearResolutionCache(getComponentIdentifier());

		UUID thisId = getId();
		StorageManager sm = StorageManager.getDefault();
		if(!sm.getDepNodes().getReferencingKeys(thisId, "resolutionId").isEmpty())
			throw new ReferentialIntegrityException(this, "remove", "Referenced from ResolvedNode");

		getStorage().removeElement(thisId);
	}

	public void store() throws CoreException
	{
		WorkspaceInfo.clearResolutionCache(getComponentIdentifier());

		if(m_cspec == null)
			getCSpec();
		else
			m_cspec.store();

		if(m_provider == null)
			getProvider();
		else
			m_provider.store();

		getStorage().putElement(this);
	}

	public void toSax(ContentHandler receiver) throws SAXException
	{
		receiver.startDocument();
		toSax(receiver, XMLConstants.BM_METADATA_NS, XMLConstants.BM_METADATA_PREFIX,
			getDefaultTag());
		receiver.endDocument();
	}

	public void toSax(ContentHandler handler, String namespace, String prefix, String localName)
	throws SAXException
	{
		handler.startPrefixMapping(XMLConstants.BM_METADATA_PREFIX, XMLConstants.BM_METADATA_NS);

		AttributesImpl attrs = new AttributesImpl();

		Utils.addAttribute(attrs, ATTR_CSPEC_ID, m_cspecId.toString());
		String tmp = TextUtils.concat(m_attributes, ",");
		if(tmp != null)
			Utils.addAttribute(attrs, ATTR_ATTRIBUTES, tmp);
		Utils.addAttribute(attrs, ATTR_MATERIALIZABLE, m_materializable ? "true" : "false");
		Utils.addAttribute(attrs, ATTR_PROVIDER_ID, m_providerId.toString());
		Utils.addAttribute(attrs, ATTR_REPOSITORY, m_repository);

		if(m_componentTypeId != null)
			Utils.addAttribute(attrs, ATTR_COMPONENT_TYPE, m_componentTypeId);
		if(m_remoteName != null)
			Utils.addAttribute(attrs, ATTR_REMOTE_NAME, m_remoteName);
		if(m_contentType != null)
			Utils.addAttribute(attrs, ATTR_CONTENT_TYPE, m_contentType);
		if(m_size != -1)
			Utils.addAttribute(attrs, ATTR_SIZE, Long.toString(m_size));

		String qName = Utils.makeQualifiedName(prefix, localName);
		handler.startElement(namespace, localName, qName, attrs);
		m_request.toSax(handler, XMLConstants.BM_METADATA_NS, XMLConstants.BM_METADATA_PREFIX, ELEM_REQUEST);
		m_versionMatch.toSax(handler, XMLConstants.BM_METADATA_NS, XMLConstants.BM_METADATA_PREFIX, m_versionMatch.getDefaultTag());
		handler.endElement(namespace, localName, qName);
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

	private ISaxableStorage<Resolution> getStorage() throws CoreException
	{
		return StorageManager.getDefault().getResolutions();
	}
}
