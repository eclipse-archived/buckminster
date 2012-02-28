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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.common.model.SAXEmitter;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.UnmodifiableMapUnion;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.ResolvedNode;
import org.eclipse.buckminster.core.metadata.model.UnresolvedNode;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.parser.IParserFactory;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ResolverDecision;
import org.eclipse.buckminster.core.resolver.ResolverDecisionType;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.sax.AbstractSaxableElement;
import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.osgi.util.NLS;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class ResourceMap extends AbstractSaxableElement implements ISaxable {
	public static final String TAG = "rmap"; //$NON-NLS-1$

	public static ResourceMap fromURL(URL url, IConnectContext cctx) throws CoreException {
		IParserFactory pf = CorePlugin.getDefault().getParserFactory();
		IParser<ResourceMap> rmapParser = pf.getResourceMapParser(true);
		InputStream input = null;
		try {
			input = DownloadManager.read(url, cctx);
			return rmapParser.parse(url.toString(), input);
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			IOUtils.close(input);
		}
	}

	private static IStatus transformToWarning(IStatus status) {
		if (status instanceof MultiStatus) {
			IStatus[] children = status.getChildren();
			int idx = children.length;
			while (--idx >= 0)
				children[idx] = transformToWarning(children[idx]);
			return new MultiStatus(status.getPlugin(), status.getCode(), children, status.getMessage(), status.getException());
		}

		return (status.getSeverity() < IStatus.ERROR) ? status : new Status(IStatus.WARNING, status.getPlugin(), status.getCode(),
				status.getMessage(), status.getException());
	}

	private final ArrayList<Matcher> matchers = new ArrayList<Matcher>();

	private final HashMap<String, SearchPath> searchPaths = new HashMap<String, SearchPath>();

	private final Map<String, String> properties = new ExpandingProperties<String>(null);

	private Documentation documentation;

	private final URL contextURL;

	public ResourceMap(URL contextURL) {
		this.contextURL = contextURL;
	}

	public void addMatcher(Matcher matcher) {
		matchers.add(matcher);
	}

	public void addPrefixMappings(HashMap<String, String> prefixMappings) {
		prefixMappings.put("xsi", javax.xml.XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI); //$NON-NLS-1$
		prefixMappings.put(XMLConstants.BM_RMAP_PREFIX, XMLConstants.BM_RMAP_NS);
		for (SearchPath searchPath : searchPaths.values())
			searchPath.addPrefixMappings(prefixMappings);
	}

	public void addSearchPath(SearchPath searchPath) {
		searchPaths.put(searchPath.getName(), searchPath);
	}

	public void clear() {
		matchers.clear();
		searchPaths.clear();
		properties.clear();
		documentation = null;
	}

	public URL getContextURL() {
		return contextURL;
	}

	@Override
	public String getDefaultTag() {
		return TAG;
	}

	public Documentation getDocumentation() {
		return documentation;
	}

	/**
	 * Returns the first provider that the query appoints
	 * 
	 * @param query
	 * @return The provider or null
	 * @throws CoreException
	 */
	public Provider getFirstProvider(NodeQuery query) throws CoreException {
		ComponentRequest request = query.getComponentRequest();
		Map<String, ? extends Object> props = query.getProperties();
		if (!properties.isEmpty()) {
			query = new NodeQuery(query, properties, false);
			props = query.getProperties();
		}

		String componentName = request.getName();
		for (Matcher matcher : matchers) {
			if (!(matcher.isFilterMatchFor(query, null) && matcher.matches(componentName)))
				continue;

			if (matcher instanceof Redirect)
				return ((Redirect) matcher).getResourceMap(query).getFirstProvider(query);

			Locator locator = (Locator) matcher;
			String searchPathRef = locator.getSearchPath();
			searchPathRef = ExpandingProperties.expand(props, searchPathRef, 0);
			SearchPath sp = getSearchPathByReference(searchPathRef);
			for (Provider provider : sp.getProviders()) {
				if (provider.isFilterMatchFor(query, null)) {
					ProviderScore score = query.getProviderScore(provider.isMutable(), provider.hasSource());
					if (score == ProviderScore.REJECTED || !provider.supportsComponentType(query.getComponentRequest().getComponentTypeID()))
						continue;
					return provider;
				}
			}
		}
		return null;
	}

	public List<Matcher> getMatchers() {
		return matchers;
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	/**
	 * Returns the <code>props</code> argument in a union with the properties
	 * defined in this RMAP. The properties from the <code>props</code> argument
	 * has precedence.
	 * 
	 * @param props
	 *            The properties to back with defaults from this RMAP
	 * @return A union of the <code>props</code> argument and properties defined
	 *         in this RMAP.
	 */
	public Map<String, ? extends Object> getProperties(Map<String, ? extends Object> props) {
		if (!properties.isEmpty())
			props = new UnmodifiableMapUnion<String, Object>(props, properties);
		return props;
	}

	public SearchPath getSearchPathByReference(String searchPathRef) throws SearchPathNotFoundException {
		SearchPath sp = searchPaths.get(searchPathRef);
		if (sp == null)
			throw new SearchPathNotFoundException("reference " + searchPathRef); //$NON-NLS-1$
		return sp;
	}

	public Collection<SearchPath> getSearchPaths() {
		return searchPaths.values();
	}

	public void removeMatcher(Matcher matcher) {
		matchers.remove(matcher);
	}

	public BOMNode resolve(NodeQuery query, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask(null, 2000);

		ComponentRequest request = query.getComponentRequest();
		MultiStatus problemCollector = new MultiStatus(CorePlugin.getID(), 0, NLS.bind(
				Messages.no_suitable_provider_for_0_was_found_in_resourceMap_1, request, getContextURL()), null);

		Map<String, ? extends Object> props = query.getProperties();
		if (!properties.isEmpty()) {
			query = new NodeQuery(query, properties, false);
			props = query.getProperties();
		}

		String componentName = request.getName();
		for (Matcher matcher : matchers) {
			Filter[] filterHandle = new Filter[1];
			if (!(matcher.isFilterMatchFor(query, filterHandle) && matcher.matches(componentName)))
				continue;

			if (matcher instanceof Redirect)
				return ((Redirect) matcher).getResourceMap(query).resolve(query, monitor);

			Locator locator = (Locator) matcher;
			try {
				String searchPathRef = locator.getSearchPath();
				searchPathRef = ExpandingProperties.expand(props, searchPathRef, 0);
				SearchPath sp = getSearchPathByReference(searchPathRef);
				query.logDecision(ResolverDecisionType.USING_SEARCH_PATH, sp.getName());
				return resolve(query, sp, monitor);
			} catch (CoreException e) {
				problemCollector.add(e.getStatus());
				if (locator.isFailOnError())
					break;
			}
		}

		if (problemCollector.getChildren().length == 0) {
			query.logDecision(ResolverDecisionType.SEARCH_PATH_NOT_FOUND, (Object) null);
			problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, NLS.bind(Messages.Unable_to_find_a_searchPath_for_0,
					request), null));
		}

		if (request.isOptional()) {
			// The component is optional so this should not be considered an
			// error
			// A warning is appropriate though, since this probably indicates
			// some
			// kind of problem.
			//
			if (!request.isSynthetic())
				query.getContext().addRequestStatus(request, transformToWarning(problemCollector));
			return new UnresolvedNode(new QualifiedDependency(request, query.getRequiredAttributes()));
		}

		throw new CoreException(problemCollector);
	}

	public void setDocumentation(Documentation documentation) {
		this.documentation = documentation;
	}

	/**
	 * Emit the SAX events that forms the XML representation for this instance
	 * 
	 * @param handler
	 *            The handler that will receive the events.
	 * @throws SAXException
	 *             if the handler throws an exception when receiving the events
	 */
	@Override
	public void toSax(ContentHandler handler) throws SAXException {
		handler.startDocument();
		toSax(handler, XMLConstants.BM_RMAP_NS, XMLConstants.BM_RMAP_PREFIX, getDefaultTag());
		handler.endDocument();
	}

	@Override
	public void toSax(ContentHandler handler, String namespace, String prefix, String localName) throws SAXException {
		HashMap<String, String> prefixMappings = new HashMap<String, String>();
		addPrefixMappings(prefixMappings);
		Set<Map.Entry<String, String>> pfxMappings = prefixMappings.entrySet();
		for (Map.Entry<String, String> pfxMapping : pfxMappings)
			handler.startPrefixMapping(pfxMapping.getKey(), pfxMapping.getValue());
		super.toSax(handler, namespace, prefix, localName);
		for (Map.Entry<String, String> pfxMapping : pfxMappings)
			handler.endPrefixMapping(pfxMapping.getKey());
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException {
		if (documentation != null)
			documentation.toSax(handler, namespace, prefix, documentation.getDefaultTag());

		SAXEmitter.emitProperties(handler, properties, namespace, prefix, true, false);

		for (SearchPath searchPath : searchPaths.values())
			searchPath.toSax(handler, namespace, prefix, searchPath.getDefaultTag());

		for (Matcher matcher : matchers)
			matcher.toSax(handler, namespace, prefix, matcher.getDefaultTag());
	}

	private BOMNode resolve(NodeQuery query, SearchPath searchPath, IProgressMonitor monitor) throws CoreException {
		MultiStatus problemCollector = new MultiStatus(CorePlugin.getID(), IStatus.ERROR, NLS.bind(
				Messages.No_suitable_provider_for_component_0_was_found_in_searchPath_1, query.getComponentRequest(), searchPath.getName()), null);

		ArrayList<Provider> noGoodList = new ArrayList<Provider>();
		try {
			for (boolean first = true;; first = false) {
				ProviderMatch providerMatch = searchPath.getProvider(query, noGoodList, problemCollector,
						MonitorUtils.subMonitor(monitor, first ? 1000 : 0));
				MonitorUtils.testCancelStatus(monitor);

				Provider provider = providerMatch.getProvider();
				IComponentType cType = providerMatch.getComponentType();
				try {
					BOMNode node = cType.getResolution(providerMatch, MonitorUtils.subMonitor(monitor, first ? 1000 : 0));
					Resolution resolution = node.getResolution();
					MonitorUtils.testCancelStatus(monitor);
					Filter[] filterHandle = new Filter[1];
					if (!resolution.isFilterMatchFor(query, filterHandle)) {
						ResolverDecision decision = query.logDecision(ResolverDecisionType.FILTER_MISMATCH, filterHandle[0]);
						noGoodList.add(providerMatch.getOriginalProvider());
						problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, decision.toString(), null));
						continue;
					}

					CSpec cspec = resolution.getCSpec();

					// Assert that the cspec can handle required actions and
					// exports
					//
					Version version = cspec.getVersion();
					VersionRange range = query.getVersionRange();
					if (range != null && provider.getVersionConverterDesc() == null) {
						// A missing version converter means that the actual
						// version check is deferred
						// and later performed on the retreived CSpec. Later is
						// now ...
						//
						if (!range.isIncluded(version)) {
							ResolverDecision decision = query.logDecision(ResolverDecisionType.VERSION_REJECTED, version,
									NLS.bind(Messages.Not_designated_by_0, range));
							noGoodList.add(providerMatch.getOriginalProvider());
							problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, decision.toString(), null));
							continue;
						}
					}

					// Verify that all required attributes are in this cspec
					//
					cspec.getAttributes(query.getRequiredAttributes());
					if (version != null) {
						// Replace the resolution version if it is default and
						// a non default version is present in the CSpec
						//
						VersionMatch vm = resolution.getVersionMatch();
						if (vm.getVersion() == null)
							node = new ResolvedNode(new Resolution(version, resolution), node.getChildren());
					}
					return node;
				} catch (CoreException e) {
					ResolverDecision decision = query.logDecision(ResolverDecisionType.EXCEPTION, e.getMessage());
					problemCollector.add(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK, decision.toString(), e));
					noGoodList.add(providerMatch.getOriginalProvider());
				}
			}
		} finally {
			monitor.done();
		}
	}
}
