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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.metadata.IResolution;
import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.builder.ResolutionBuilder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.sax.UUIDKeyed;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class Resolution extends UUIDKeyed implements IUUIDPersisted, IResolution {
	public static final String ATTR_ATTRIBUTES = "attributes"; //$NON-NLS-1$

	public static final String ATTR_COMPONENT_TYPE = "componentType"; //$NON-NLS-1$

	public static final String ATTR_CONTENT_TYPE = "contentType"; //$NON-NLS-1$

	public static final String ATTR_CSPEC_ID = "cspecId"; //$NON-NLS-1$

	public static final String ATTR_LAST_MODIFIED = "lastModified"; //$NON-NLS-1$

	public static final String ATTR_MATERIALIZABLE = "materializable"; //$NON-NLS-1$

	public static final String ATTR_PERSISTENT_ID = "persistentId"; //$NON-NLS-1$

	public static final String ATTR_PROVIDER_ID = "providerId"; //$NON-NLS-1$

	public static final String ATTR_QUERY_ID = "queryId"; //$NON-NLS-1$

	public static final String ATTR_REMOTE_NAME = "remoteName"; //$NON-NLS-1$

	public static final String ATTR_REPOSITORY = "repository"; //$NON-NLS-1$

	public static final String ATTR_SIZE = "size"; //$NON-NLS-1$

	public static final String ATTR_UNPACK = "unpack"; //$NON-NLS-1$

	public static final String ELEM_REQUEST = "request"; //$NON-NLS-1$

	public static final int SEQUENCE_NUMBER = 2;

	public static final String TAG = "resolution"; //$NON-NLS-1$

	private final List<String> attributes;

	private final String componentTypeId;

	private final String contentType;

	private transient CSpec cspec;

	private final long lastModified;

	private final boolean materializable;

	private final String persistentId;

	private transient Provider provider;

	private final String remoteName;

	private final String repository;

	private final ComponentRequest request;

	private final long size;

	private final boolean unpack;

	private final VersionMatch versionMatch;

	private Map<String, ? extends Object> properties;

	public Resolution(CSpec cspec, Resolution old) {
		this.cspec = cspec;
		this.request = old.getRequest();
		this.attributes = old.getAttributes();
		this.persistentId = old.getPersistentId();
		this.provider = old.getProvider();
		this.componentTypeId = old.getComponentTypeId();
		this.versionMatch = old.getVersionMatch().copyWithVersion(cspec.getVersion());
		this.materializable = old.isMaterializable();
		this.repository = old.getRepository();
		this.remoteName = old.getRemoteName();
		this.contentType = old.getContentType();
		this.lastModified = old.getLastModified();
		this.size = old.getSize();
		this.unpack = old.isUnpack();
	}

	public Resolution(CSpec cspec, String componentTypeId, VersionMatch versionMatch, Provider provider, boolean materializeable,
			ComponentRequest request, List<String> attributes, String persistentId, String repository, String remoteName, String contentType,
			long lastModified, long size, boolean unpack) {
		this.cspec = cspec;
		this.provider = provider;
		this.componentTypeId = componentTypeId;
		this.versionMatch = versionMatch;
		this.materializable = materializeable;
		this.request = request;
		this.attributes = Utils.createUnmodifiableList(attributes);
		this.persistentId = persistentId;
		this.repository = repository;
		this.remoteName = remoteName;
		this.contentType = contentType;
		this.lastModified = lastModified;
		this.size = size;
		this.unpack = unpack;
	}

	public Resolution(ResolutionBuilder bld) {
		this.attributes = Utils.createUnmodifiableList(bld.getAttributes());
		this.componentTypeId = bld.getComponentTypeId();
		this.contentType = bld.getContentType();
		this.cspec = bld.getCSpec();
		this.lastModified = bld.getLastModified();
		this.materializable = bld.isMaterializable();
		this.persistentId = bld.getPersistentId();
		this.provider = bld.getProvider();
		this.remoteName = bld.getRemoteName();
		this.repository = bld.getRepository();
		this.request = bld.getRequest().createComponentRequest();
		this.size = bld.getSize();
		this.versionMatch = bld.getVersionMatch();
		this.unpack = bld.isUnpack();
	}

	public Resolution(Version version, Resolution old) {
		this.cspec = old.getCSpec();
		this.request = old.getRequest();
		this.attributes = old.getAttributes();
		this.persistentId = old.getPersistentId();
		this.provider = old.getProvider();
		this.componentTypeId = old.getComponentTypeId();
		this.versionMatch = old.getVersionMatch().copyWithVersion(version);
		this.materializable = old.isMaterializable();
		this.repository = old.getRepository();
		this.remoteName = old.getRemoteName();
		this.contentType = old.getContentType();
		this.lastModified = old.getLastModified();
		this.size = old.getSize();
		this.unpack = old.isUnpack();
	}

	@Override
	public void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException {
		request.toSax(handler, XMLConstants.BM_METADATA_NS, XMLConstants.BM_METADATA_PREFIX, ELEM_REQUEST);
		versionMatch.toSax(handler, XMLConstants.BM_METADATA_NS, XMLConstants.BM_METADATA_PREFIX, versionMatch.getDefaultTag());
	}

	@Override
	public String getArtifactInfo() {
		return versionMatch.getArtifactInfo();
	}

	public URI getArtifactURI(RMContext context) throws CoreException {
		return getProvider().getReaderType().getArtifactURL(this, context);
	}

	@Override
	public List<String> getAttributes() {
		return attributes;
	}

	/**
	 * Returns the identifier of the contained <code>CSpec</code>.
	 * 
	 * @return The component identifier
	 * @throws CoreException
	 */
	public ComponentIdentifier getComponentIdentifier() {
		return getCSpec().getComponentIdentifier();
	}

	public IComponentType getComponentType() throws CoreException {
		return CorePlugin.getDefault().getComponentType(componentTypeId);
	}

	@Override
	public String getComponentTypeId() {
		return componentTypeId;
	}

	@Override
	public String getContentType() {
		return contentType;
	}

	/**
	 * Returns the CSpec at the time when this resolution was created. The
	 * actual cspec in the workspace might have changed since then.
	 * 
	 * @return The resolved cspec.
	 */
	@Override
	public CSpec getCSpec() {
		return cspec;
	}

	/**
	 * Returns the id of the contained CSpec.
	 * 
	 * @return The id of the contained CSpec.
	 */
	public UUID getCSpecId() {
		return cspec.getId();
	}

	@Override
	public String getDefaultTag() {
		return TAG;
	}

	@Override
	public long getLastModified() {
		return lastModified;
	}

	@Override
	public VersionSelector getMatchedBranchOrTag() {
		return versionMatch.getBranchOrTag();
	}

	/**
	 * Returns the name of the component.
	 * 
	 * @return the name.
	 */
	public final String getName() {
		return request.getName();
	}

	@Override
	public String getPersistentId() {
		return persistentId;
	}

	public synchronized Map<String, ? extends Object> getProperties() {
		if (properties == null) {
			HashMap<String, Object> props = new HashMap<String, Object>();
			props.put(KeyConstants.READER_TYPE, provider.getReaderTypeId());
			props.put(KeyConstants.IS_MUTABLE, Boolean.toString(provider.isMutable()));
			props.put(KeyConstants.IS_SOURCE, Boolean.toString(provider.hasSource()));
			props.putAll(cspec.getComponentIdentifier().getProperties());
			properties = props;
		}
		return properties;
	}

	/**
	 * Returns the provider used when reading the repository.
	 * 
	 * @return the repository provider.
	 */
	@Override
	public Provider getProvider() {
		return provider;
	}

	/**
	 * Returns the id of the contained provider
	 * 
	 * @return the id of the contained provider
	 */
	public UUID getProviderId() {
		return provider.getId();
	}

	public ProviderMatch getProviderMatch(RMContext context) throws CoreException {
		ProviderMatch pm = new ProviderMatch(provider, getComponentType(), getVersionMatch(), context.getNodeQuery(getQualifiedDependency()));
		pm.setRepositoryURI(repository);
		return pm;
	}

	public final QualifiedDependency getQualifiedDependency() {
		return new QualifiedDependency(request, attributes);
	}

	@Override
	public String getReaderTypeId() {
		return getProvider().getReaderTypeId();
	}

	@Override
	public String getRemoteName() {
		return remoteName;
	}

	@Override
	public String getRepository() {
		return repository;
	}

	/**
	 * @return Returns the properties.
	 */
	@Override
	public final ComponentRequest getRequest() {
		return request;
	}

	@Override
	public Filter getResolutionFilter() {
		return getProvider().getResolutionFilter();
	}

	@Override
	public String getSelectedRevision() {
		return versionMatch.getRevision();
	}

	@Override
	public Date getSelectedTimestamp() {
		return getVersionMatch().getTimestamp();
	}

	@Override
	public long getSize() {
		return size;
	}

	/**
	 * Returns the final version that was used when the specification was
	 * obtained.
	 * 
	 * @return the version used when retrieving the spec.
	 */
	public final Version getVersion() {
		return versionMatch.getVersion();
	}

	/**
	 * Returns the original version designator.
	 * 
	 * @return The original (unresolved) version designator
	 */
	public final VersionRange getVersionDesignator() throws CoreException {
		return request.getVersionRange();
	}

	@Override
	public VersionMatch getVersionMatch() {
		return versionMatch;
	}

	/**
	 * Check if the request designates the versioned component that this
	 * component info represents.
	 * 
	 * @param rq
	 *            the request that might appoint the component
	 * @return <code>true</code> if the versioned component is designated
	 * @throws CoreException
	 */
	public boolean isDesignatedBy(ComponentRequest rq) throws CoreException {
		if (!rq.getName().equals(request.getName()))
			return false;

		// If the request has a component type then it must match
		//
		String componentType = rq.getComponentTypeID();
		if (componentType != null && !componentType.equals(request.getComponentTypeID()))
			return false;

		VersionRange vd = rq.getVersionRange();
		return vd == null ? true : vd.isIncluded(getVersion());
	}

	/**
	 * Returns true if this resolution is a match for the given
	 * <code>query</code> with respect to provided properties. The method will
	 * update the filter attributes map of the query context.
	 * 
	 * @param The
	 *            query to match
	 * @return True if this resolution is a match for the given query.
	 * @see RMContext#getFilterAttributeUsageMap()
	 */
	public boolean isFilterMatchFor(NodeQuery query) {
		return isFilterMatchFor(query, null);
	}

	/**
	 * Returns true if this resolution is a match for the given
	 * <code>query</code> with respect to provided properties. The method will
	 * update the filter attributes map of the query context.
	 * 
	 * @param The
	 *            query to match
	 * @param A
	 *            one element array that will receive the failing filter. Can be
	 *            <code>null</code>.
	 * @return True if this resolution is a match for the given query.
	 * @see RMContext#getFilterAttributeUsageMap()
	 */
	public boolean isFilterMatchFor(NodeQuery query, Filter[] failingFilter) {
		Filter cspecFilter = getCSpec().getFilter();
		if (cspecFilter == null)
			return true;

		Map<String, String[]> attributeUsageMap = query.getContext().getFilterAttributeUsageMap();
		Map<String, ? extends Object> queryProps = query.getProperties();
		cspecFilter.addConsultedAttributes(attributeUsageMap);
		if (cspecFilter.matchCase(queryProps))
			return true;

		if (failingFilter != null)
			failingFilter[0] = cspecFilter;
		return false;
	}

	/**
	 * Returns <code>true</code> if the reader associated with the component
	 * will be able to materialized the component. Readers that check for the
	 * existence of pre-installed components (such as Eclipse plugins that are
	 * already present in the running eclipse installation) will return
	 * <code>false</code>.
	 * 
	 * @return <code>true</code> if the component can be materialized on disk.
	 */
	@Override
	public boolean isMaterializable() {
		return materializable;
	}

	/**
	 * Returns <code>true</code> if the component is materialized at the given
	 * location according to the workspace meta-data.
	 * 
	 * @return <code>true</code> if the component is materialized.
	 */
	public boolean isMaterialized(IPath location) throws CoreException {
		try {
			IPath myLocation = getCSpec().getComponentLocation();
			return location.equals(myLocation);
		} catch (MissingComponentException e) {
			return false;
		}
	}

	@Override
	public boolean isPersisted(StorageManager sm) throws CoreException {
		return sm.getResolutions().contains(this);
	}

	@Override
	public boolean isUnpack() {
		return unpack;
	}

	@Override
	public void remove(StorageManager sm) throws CoreException {
		WorkspaceInfo.updateResolutionCache(getComponentIdentifier(), null);
		synchronized (sm.getResolutions()) {
			sm.getResolutions().removeElement(getId());
		}
	}

	@Override
	public void store(StorageManager sm) throws CoreException {
		WorkspaceInfo.updateResolutionCache(getComponentIdentifier(), this);
		cspec.store(sm);
		provider.store(sm);
		synchronized (sm.getResolutions()) {
			sm.getResolutions().putElement(this);
		}
	}

	@Override
	public void toSax(ContentHandler receiver) throws SAXException {
		receiver.startDocument();
		toSax(receiver, XMLConstants.BM_METADATA_NS, XMLConstants.BM_METADATA_PREFIX, getDefaultTag());
		receiver.endDocument();
	}

	@Override
	public void toSax(ContentHandler handler, String namespace, String prefix, String localName) throws SAXException {
		handler.startPrefixMapping(XMLConstants.BM_METADATA_PREFIX, XMLConstants.BM_METADATA_NS);
		super.toSax(handler, namespace, prefix, localName);
		handler.endPrefixMapping(XMLConstants.BM_METADATA_PREFIX);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(Messages.Name);
		result.append(request.getName());
		result.append(", "); //$NON-NLS-1$
		versionMatch.toString(result);
		return result.toString();
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException {
		Utils.addAttribute(attrs, ATTR_CSPEC_ID, cspec.getId().toString());
		String tmp = TextUtils.concat(attributes, ","); //$NON-NLS-1$
		if (tmp != null)
			Utils.addAttribute(attrs, ATTR_ATTRIBUTES, tmp);
		Utils.addAttribute(attrs, ATTR_MATERIALIZABLE, materializable ? "true" //$NON-NLS-1$
				: "false"); //$NON-NLS-1$
		Utils.addAttribute(attrs, ATTR_PROVIDER_ID, provider.getId().toString());
		Utils.addAttribute(attrs, ATTR_REPOSITORY, repository);

		if (componentTypeId != null)
			Utils.addAttribute(attrs, ATTR_COMPONENT_TYPE, componentTypeId);
		if (persistentId != null)
			Utils.addAttribute(attrs, ATTR_PERSISTENT_ID, persistentId);
		if (remoteName != null)
			Utils.addAttribute(attrs, ATTR_REMOTE_NAME, remoteName);
		if (contentType != null)
			Utils.addAttribute(attrs, ATTR_CONTENT_TYPE, contentType);
		if (lastModified != -1L)
			Utils.addAttribute(attrs, ATTR_LAST_MODIFIED, Long.toString(lastModified));
		if (size != -1L)
			Utils.addAttribute(attrs, ATTR_SIZE, Long.toString(size));
		if (unpack)
			Utils.addAttribute(attrs, ATTR_UNPACK, "true"); //$NON-NLS-1$
	}

	@Override
	protected String getElementNamespace(String namespace) {
		return XMLConstants.BM_METADATA_NS;
	}

	@Override
	protected String getElementPrefix(String prefix) {
		return XMLConstants.BM_METADATA_PREFIX;
	}
}
