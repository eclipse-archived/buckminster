/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.model;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.metadata.ISaxableStorage;
import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.metadata.ReferentialIntegrityException;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
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

	public static final String ATTR_VERSION = "version";

	public static final String ATTR_VERSION_TYPE = "versionType";

	public static final String ATTR_FIXED_VERSION_SELECTOR = "fixedVersionSelector";

	public static final String ATTR_MATERIALIZABLE = "materializable";

	public static final String ATTR_PROVIDER_ID = "providerId";

	public static final String ATTR_QUERY_ID = "queryId";

	public static final String ATTR_REPOSITORY = "repository";

	public static final String ELEM_REQUEST = "request";

	public static final int SEQUENCE_NUMBER = 1;

	private final VersionMatch m_versionMatch;

	private final boolean m_materializable;

	private final ComponentRequest m_request;

	private final UUID m_providerId;

	private final Set<String> m_attributes;

	private final String m_repository;

	private final UUID m_cspecId;

	private transient CSpec m_cspec;

	private transient Provider m_provider;

	public Resolution(CSpec cspec, IComponentReader reader) throws CoreException
	{
		ProviderMatch providerMatch = reader.getProviderMatch();
		Provider provider = providerMatch.getProvider();
		NodeQuery nq = providerMatch.getNodeQuery();
		m_cspec = cspec;
		m_cspecId = cspec.getId();
		m_request = nq.getComponentRequest();
		m_attributes = UUIDKeyed.createUnmodifiableSet(nq.getRequiredAttributes());
		m_provider = provider;
		m_providerId = provider.getId();

		if(providerMatch == null)
			m_versionMatch = VersionMatch.DEFAULT;
		else
			m_versionMatch = providerMatch.getVersionMatch();
		m_materializable = reader.canMaterialize();
		m_repository = providerMatch.getRepositoryURI();
	}

	public Resolution(CSpec cspec, Resolution old) throws CoreException
	{
		m_cspec = cspec;
		m_cspecId = cspec.getId();
		m_request = old.getRequest();
		m_attributes = old.getAttributes();
		m_provider = old.getProvider();
		m_providerId = old.getProviderId();
		m_versionMatch = old.getVersionMatch();
		m_materializable = old.isMaterializable();
		m_repository = old.getRepository(null);
	}

	public Resolution(IVersion version, Resolution old) throws CoreException
	{
		m_cspec = old.getCSpec();
		m_cspecId = old.getCSpecId();
		m_request = old.getRequest();
		m_attributes = old.getAttributes();
		m_provider = old.getProvider();
		m_providerId = old.getProviderId();
		m_versionMatch = new VersionMatch(version, old.getVersionMatch().getFixedVersionSelector());
		m_materializable = old.isMaterializable();
		m_repository = old.getRepository(null);
	}

	public Resolution(UUID cspecId, IVersion version, IVersionSelector fixedVersionSelector, UUID providerId,
		boolean materializeable, ComponentRequest request, Set<String> attributes,
		String repository)
	{
		m_cspecId = cspecId;
		m_providerId = providerId;
		m_versionMatch = new VersionMatch(version == null ? VersionFactory.defaultVersion() : version,
			fixedVersionSelector);
		m_materializable = materializeable;
		m_request = request;
		m_attributes = UUIDKeyed.createUnmodifiableSet(attributes);
		m_repository = repository;
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

	public String getRepository(RMContext context) throws CoreException
	{
		if(m_repository != null)
			return m_repository;
		return getProvider().getURI(context.getProperties(getRequest()));
	}

	public final QualifiedDependency getQualifiedDependency()
	{
		return new QualifiedDependency(m_request, m_attributes);
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

		// If the request has a category then it must match
		//
		String category = request.getCategory();
		if(category != null && !category.equals(m_request.getCategory()))
			return false;

		IVersionDesignator vd = request.getVersionDesignator();
		return vd == null ? true : vd.designates(getVersion());
	}

	/**
	 * Returns <code>true</code> if the component is an Eclipse project which in essence means it
	 * contains a valid ProjectDescription (a .project file).
	 * @return <code>true</code> if the component is an Eclipse project
	 */
	public boolean isEclipseProject() throws CoreException
	{
		return getProvider().getComponentType().hasProjectDescription();
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
		UUID thisId = getId();
		StorageManager sm = StorageManager.getDefault();
		if(!sm.getMaterializations().getReferencingKeys(thisId, "resolutionId").isEmpty())
			throw new ReferentialIntegrityException(this, "remove", "Referenced from Materialization");

		if(!sm.getDepNodes().getReferencingKeys(thisId, "resolutionId").isEmpty())
			throw new ReferentialIntegrityException(this, "remove", "Referenced from ResolvedNode");

		getStorage().removeElement(thisId);
	}

	public void store() throws CoreException
	{
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

		IVersion version = m_versionMatch.getVersion();
		if(!version.isDefault())
		{
			Utils.addAttribute(attrs, ATTR_VERSION, version.toString());
			Utils.addAttribute(attrs, ATTR_VERSION_TYPE, version.getType().getId());
		}
		Utils.addAttribute(attrs, ATTR_FIXED_VERSION_SELECTOR,
			m_versionMatch.getFixedVersionSelector().toString());
		Utils.addAttribute(attrs, ATTR_MATERIALIZABLE, m_materializable ? "true" : "false");
		Utils.addAttribute(attrs, ATTR_PROVIDER_ID, m_providerId.toString());

		if(m_repository != null)
			Utils.addAttribute(attrs, ATTR_REPOSITORY, m_repository);

		String qName = Utils.makeQualifiedName(prefix, localName);
		handler.startElement(namespace, localName, qName, attrs);
		m_request.toSax(handler, XMLConstants.BM_METADATA_NS, XMLConstants.BM_METADATA_PREFIX, ELEM_REQUEST);
		handler.endElement(namespace, localName, qName);
		handler.endPrefixMapping(XMLConstants.BM_METADATA_PREFIX);
	}

	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		result.append("Name: ");
		result.append(m_request.getName());
		IVersion version = m_versionMatch.getVersion();
		if(!version.isDefault())
		{
			result.append(", Version: ");
			result.append(version);
		}
		result.append(", Fixed at: ");
		result.append(m_versionMatch.getFixedVersionSelector());
		return result.toString();
	}

	public WorkspaceBinding createBindSpec(RMContext context)
	throws CoreException
	{
		Materialization mat = WorkspaceInfo.getMaterialization(this);
		if(mat == null)
		{
			// We still want to bind stuff produced by the local reader
			//
			String readerTypeName = getProvider().getReaderTypeId();
			if(!IReaderType.LOCAL.equals(readerTypeName))
				//
				// From the platform. Don't bind this
				//
				return null;

			IReaderType localReaderType = CorePlugin.getDefault().getReaderType(readerTypeName);
			mat = new Materialization(localReaderType.getFixedLocation(this), this);
		}

		IPath wsRelativePath;
		if(isEclipseProject())
		{
			wsRelativePath = new Path(context.getProjectName(this)).addTrailingSeparator();
		}
		else
		{
			IPath matLoc = mat.getComponentLocation();
			IPath bmProjLoc = CorePlugin.getDefault().getBuckminsterProjectLocation();
			if(bmProjLoc.isPrefixOf(matLoc))
				wsRelativePath = matLoc.removeFirstSegments(bmProjLoc.segmentCount() - 1).setDevice(null);
			else
				//
				// This will become a link in the root of the .buckminster project
				//
				wsRelativePath = new Path(CorePlugin.BUCKMINSTER_PROJECT).append(matLoc.lastSegment());

			if(matLoc.hasTrailingSeparator())
				wsRelativePath.addTrailingSeparator();
		}
		return new WorkspaceBinding(wsRelativePath, mat);
	}

	void addMaterialization(List<Materialization> minfos, RMContext context) throws CoreException
	{
		if(isMaterializable())
			minfos.add(new Materialization(context.getDestination(this), this));
	}

	private ISaxableStorage<Resolution> getStorage() throws CoreException
	{
		return StorageManager.getDefault().getResolutions();
	}
}
