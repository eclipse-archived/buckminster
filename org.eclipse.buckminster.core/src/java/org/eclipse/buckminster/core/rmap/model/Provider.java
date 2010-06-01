/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.rmap.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.common.model.SAXEmitter;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.ctype.MissingCSpecSourceException;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.metadata.ReferentialIntegrityException;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.model.IUUIDPersisted;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ResolverDecision;
import org.eclipse.buckminster.core.resolver.ResolverDecisionType;
import org.eclipse.buckminster.core.version.IVersionConverter;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.UUIDKeyed;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.osgi.util.NLS;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class Provider extends UUIDKeyed implements IUUIDPersisted {
	public static final String ATTR_COMPONENT_TYPES = "componentTypes"; //$NON-NLS-1$

	public static final String ATTR_ALGORITHM = "algorithm"; //$NON-NLS-1$

	public static final String ATTR_READER_TYPE = "readerType"; //$NON-NLS-1$

	public static final String ATTR_RESOLUTION_FILTER = "resolutionFilter"; //$NON-NLS-1$

	public static final String ATTR_VERSION_CONVERTER = "versionConverter"; //$NON-NLS-1$

	public static final String TAG = "provider"; //$NON-NLS-1$

	public static final String TAG_URI = "uri"; //$NON-NLS-1$

	public static final int SEQUENCE_NUMBER = 2;

	public static final String TAG_DIGEST = "digest"; //$NON-NLS-1$

	public static Provider immutableProvider(String readerType, String componentType, String uri) {
		return immutableProvider(readerType, componentType, uri, null);
	}

	public static Provider immutableProvider(String readerType, String componentType, String uri, Filter resolutionFilter) {
		Map<String, String> props = new HashMap<String, String>(2);
		props.put(KeyConstants.IS_MUTABLE, "false"); //$NON-NLS-1$
		props.put(KeyConstants.IS_SOURCE, "false"); //$NON-NLS-1$
		return new Provider(null, readerType, new String[] { componentType }, null, new Format(uri), null, null, resolutionFilter, props, null, null);
	}

	private final Documentation documentation;

	private final String[] componentTypeIDs;

	private final String readerTypeId;

	private final Format uri;

	private final Format digest;

	private final String digestAlgorithm;

	private final VersionConverterDesc versionConverter;

	private final SearchPath searchPath;

	private final URIMatcher uriMatcher;

	private final Filter resolutionFilter;

	private final Map<String, String> properties;

	/**
	 * Creates a new fully initialized Provider
	 * 
	 * @param searchPath
	 *            The search path that this provider belongs to.
	 * @param remoteReaderType
	 *            The reader type used by the provider
	 * @param componentTypeIDs
	 *            An array of component types supported by this provider
	 * @param versionConverterDesc
	 *            The description of the version converter or <code>null</code>
	 *            if not applicable.
	 * @param uri
	 *            The URI used by the reader type.
	 * @param digest
	 *            The digest URI or <code>null</code> if not applicable
	 * @param digestAlgorithm
	 *            The digest algorithm or <code>null</code> if not applicable
	 * @param resolutionFilter
	 *            Filter indicating when this provider is active
	 * @param properties
	 *            Provider specific properties
	 * @param uriMatcher
	 *            The URI matcher for the provider or <code>null</code> if not
	 *            applicable.
	 * @param documentation
	 *            Documentation in xhtml format.
	 */
	public Provider(SearchPath searchPath, String remoteReaderType, String[] componentTypeIDs, VersionConverterDesc versionConverterDesc, Format uri,
			Format digest, String digestAlgorithm, Filter resolutionFilter, Map<String, String> properties, URIMatcher uriMatcher,
			Documentation documentation) {
		this.searchPath = searchPath;
		this.readerTypeId = remoteReaderType;
		this.componentTypeIDs = componentTypeIDs == null ? Trivial.EMPTY_STRING_ARRAY : componentTypeIDs;
		this.versionConverter = versionConverterDesc;
		this.uri = uri;
		this.digest = digest;
		this.digestAlgorithm = digestAlgorithm;
		this.resolutionFilter = resolutionFilter;
		this.properties = properties == null ? Collections.<String, String> emptyMap() : properties;
		this.uriMatcher = uriMatcher;
		this.documentation = documentation;
	}

	public void addPrefixMappings(HashMap<String, String> prefixMappings) {
		prefixMappings.put("xsi", javax.xml.XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI); //$NON-NLS-1$
	}

	public ProviderMatch findMatch(NodeQuery query, MultiStatus problemCollector, IProgressMonitor monitor) throws CoreException {
		String readerType = getReaderTypeId();
		String providerURI = searchPath == null ? getURI(query.getProperties()) : searchPath.getProviderURI(query, this);
		ProviderScore score = query.getProviderScore(isMutable(), hasSource());
		if (score == ProviderScore.REJECTED) {
			ResolverDecision decision = query.logDecision(ResolverDecisionType.REJECTING_PROVIDER, readerType, providerURI,
					Messages.Score_is_below_threshold);
			problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, decision.toString(), null));
			return null;
		}

		if (uriMatcher != null)
			return uriMatcher.getMatch(this, query, monitor);

		IVersionFinder versionFinder = null;
		monitor.beginTask(null, 120);
		try {
			ComponentRequest request = query.getComponentRequest();
			String componentTypeID = request.getComponentTypeID();

			// The component request is equipped with a component type. It must
			// match the types that this provider provides.
			//
			IComponentType[] componentTypes = getComponentTypes();
			if (componentTypeID != null) {
				boolean found = false;
				int idx = componentTypes.length;
				while (--idx >= 0) {
					IComponentType ctype = componentTypes[idx];
					if (ctype.getId().equals(componentTypeID)) {
						// Limit the component types to this one type
						//
						componentTypes = new IComponentType[] { ctype };
						found = true;
						break;
					}
				}

				if (!found) {
					// The ECLIPSE_PLATFORM reader is silent here since it is
					// always consulted
					//
					if (!getReaderTypeId().equals(IReaderType.ECLIPSE_PLATFORM)) {
						ResolverDecision decision = query.logDecision(ResolverDecisionType.REJECTING_PROVIDER, readerType, providerURI,
								String.format(NLS.bind(Messages.Components_of_type_0_are_not_supported, componentTypeID)));
						problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, decision.toString(), null));
					}
					return null;
				}
			}

			VersionMatch candidate = null;
			IComponentType ctypeUsed = null;
			CoreException problem = null;
			try {
				for (IComponentType ctype : componentTypes) {
					try {
						versionFinder = getReaderType().getVersionFinder(this, ctype, query, MonitorUtils.subMonitor(monitor, 20));
						candidate = versionFinder.getBestVersion(MonitorUtils.subMonitor(monitor, 80));
						if (candidate == null)
							continue;
						ctypeUsed = ctype;
					} catch (MissingCSpecSourceException e) {
						continue;
					}
					break;
				}
			} catch (CoreException e) {
				problem = e;
			}

			if (candidate == null) {
				ResolverDecision decision = query.logDecision(ResolverDecisionType.REJECTING_PROVIDER, readerType, providerURI,
						Messages.No_component_match_was_found);
				problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, decision.toString(), problem == null ? null
						: BuckminsterException.unwind(problem)));
				return null;
			}
			query.logDecision(ResolverDecisionType.MATCH_FOUND, candidate);
			return versionFinder.getProviderMatch(candidate, ctypeUsed, score);
		} finally {
			if (versionFinder != null)
				versionFinder.close();
			monitor.done();
		}
	}

	public final String[] getComponentTypeIDs() {
		return componentTypeIDs;
	}

	public final IComponentType[] getComponentTypes() throws CoreException {
		CorePlugin plugin = CorePlugin.getDefault();
		int idx = componentTypeIDs.length;
		IComponentType[] ctypes = new IComponentType[idx];
		while (--idx >= 0)
			ctypes[idx] = plugin.getComponentType(componentTypeIDs[idx]);
		return ctypes;
	}

	public IConnectContext getConnectContext() {
		return null;
	}

	@Override
	public String getDefaultTag() {
		return TAG;
	}

	/**
	 * @return Returns the Digest URI.
	 */
	public final String getDigest(Map<String, String> props) {
		return digest == null ? null : digest.getValue(getProperties(props));
	}

	public final String getDigestAlgorithm() {
		return digestAlgorithm;
	}

	public Documentation getDocumentation() {
		return documentation;
	}

	public Map<String, ? extends Object> getProperties(Map<String, ? extends Object> props) {
		if (searchPath != null)
			props = searchPath.getResourceMap().getProperties(props);
		return props;
	}

	public Map<String, String> getProviderProperties() {
		return properties;
	}

	public final IReaderType getReaderType() throws CoreException {
		return CorePlugin.getDefault().getReaderType(readerTypeId);
	}

	public final String getReaderTypeId() {
		return readerTypeId;
	}

	public Filter getResolutionFilter() {
		return resolutionFilter;
	}

	public final SearchPath getSearchPath() {
		return searchPath;
	}

	/**
	 * @return Returns the possibly parameterized <code>Format</code> instance
	 *         that represents File or Repository URI.
	 */
	public final Format getURI() {
		return uri;
	}

	/**
	 * @return Returns expanded the File or Repository URI.
	 */
	public final String getURI(Map<String, ? extends Object> props) {
		return uri.getValue(getProperties(props));
	}

	public URIMatcher getURIMatcher() {
		return uriMatcher;
	}

	public IVersionConverter getVersionConverter() throws CoreException {
		VersionConverterDesc vcd = getVersionConverterDesc();
		return vcd == null ? null : vcd.getVersionConverter();
	}

	public final VersionConverterDesc getVersionConverterDesc() {
		return versionConverter;
	}

	/**
	 * @return Returns the hasSource.
	 */
	public final boolean hasSource() {
		String source = properties.get(KeyConstants.IS_SOURCE);
		return source == null ? true : Boolean.parseBoolean(source);
	}

	/**
	 * Returns true if this provider is a match for the given <code>query</code>
	 * with respect to provided properties. The method will update the filter
	 * attributes map of the query context.
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
		if (resolutionFilter == null)
			return true;

		Map<String, String[]> attributeUsageMap = query.getContext().getFilterAttributeUsageMap();
		Filter resFilter = getResolutionFilter();
		Map<String, ? extends Object> props = query.getProperties();

		resolutionFilter.addConsultedAttributes(attributeUsageMap);
		if (resolutionFilter.matchCase(props))
			return true;

		if (failingFilter != null)
			failingFilter[0] = resFilter;
		return false;
	}

	/**
	 * @return Returns the readOnly.
	 */
	public final boolean isMutable() {
		String mutable = properties.get(KeyConstants.IS_MUTABLE);
		return mutable == null ? true : Boolean.parseBoolean(mutable);
	}

	@Override
	public boolean isPersisted(StorageManager sm) throws CoreException {
		return sm.getProviders().contains(this);
	}

	@Override
	public void remove(StorageManager sm) throws CoreException {
		UUID thisId = getId();
		if (!sm.getResolutions().getReferencingKeys(thisId, "providerId").isEmpty()) //$NON-NLS-1$
			throw new ReferentialIntegrityException(this, "remove", Messages.Referenced_from_Resolution); //$NON-NLS-1$

		sm.getProviders().removeElement(thisId);
	}

	@Override
	public void store(StorageManager sm) throws CoreException {
		sm.getProviders().putElement(this);
	}

	@Override
	public void toSax(ContentHandler handler) throws SAXException {
		handler.startDocument();

		HashMap<String, String> prefixMappings = new HashMap<String, String>();
		addPrefixMappings(prefixMappings);
		for (Map.Entry<String, String> pfxMapping : prefixMappings.entrySet())
			handler.startPrefixMapping(pfxMapping.getKey(), pfxMapping.getValue());

		toSax(handler, XMLConstants.BM_RMAP_NS, XMLConstants.BM_RMAP_PREFIX, getDefaultTag());
		for (String pfx : prefixMappings.keySet())
			handler.endPrefixMapping(pfx);

		handler.endDocument();
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException {
		Utils.addAttribute(attrs, ATTR_READER_TYPE, readerTypeId);
		if (componentTypeIDs.length > 0)
			Utils.addAttribute(attrs, ATTR_COMPONENT_TYPES, TextUtils.concat(componentTypeIDs, ",")); //$NON-NLS-1$
		if (resolutionFilter != null)
			Utils.addAttribute(attrs, ATTR_RESOLUTION_FILTER, resolutionFilter.toString());
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException {
		if (documentation != null)
			documentation.toSax(handler, namespace, prefix, documentation.getDefaultTag());

		if (uriMatcher != null)
			uriMatcher.toSax(handler, namespace, prefix, uriMatcher.getDefaultTag());

		uri.toSax(handler, namespace, prefix, TAG_URI);

		SAXEmitter.emitProperties(handler, properties, namespace, prefix, true, false);

		if (digest != null) {
			AttributesImpl attrs = new AttributesImpl();
			Utils.addAttribute(attrs, Format.ATTR_FORMAT, digest.getFormat());
			Utils.addAttribute(attrs, ATTR_ALGORITHM, digestAlgorithm);
			String qName = Utils.makeQualifiedName(prefix, TAG_DIGEST);
			handler.startElement(namespace, TAG_DIGEST, qName, attrs);
			handler.endElement(namespace, TAG_DIGEST, qName);
		}

		if (versionConverter != null)
			versionConverter.toSax(handler, namespace, prefix, versionConverter.getDefaultTag());
	}

	@Override
	protected String getElementNamespace(String namespace) {
		return XMLConstants.BM_RMAP_NS;
	}

	@Override
	protected String getElementPrefix(String prefix) {
		return XMLConstants.BM_RMAP_PREFIX;
	}
}
