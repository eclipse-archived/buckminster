/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.query.model;

import static org.eclipse.buckminster.core.XMLConstants.BM_CQUERY_NS;
import static org.eclipse.buckminster.core.XMLConstants.BM_CQUERY_PREFIX;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.helpers.SAXEmitter;
import org.eclipse.buckminster.core.metadata.StorageManager;
import org.eclipse.buckminster.core.metadata.model.IUUIDPersisted;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.parser.IParserFactory;
import org.eclipse.buckminster.core.query.IAdvisorNode;
import org.eclipse.buckminster.core.query.IComponentQuery;
import org.eclipse.buckminster.core.query.builder.AdvisorNodeBuilder;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.resolver.ProviderScore;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.model.common.ComponentName;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.buckminster.model.common.util.BMProperties;
import org.eclipse.buckminster.model.common.util.ExpandingProperties;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.buckminster.sax.UUIDKeyed;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.osgi.util.NLS;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class ComponentQuery extends UUIDKeyed implements IUUIDPersisted, IComponentQuery {
	public static final String ATTR_PROPERTIES = "properties"; //$NON-NLS-1$

	public static final String ATTR_RESOURCE_MAP = "resourceMap"; //$NON-NLS-1$

	public static final String ATTR_SHORT_DESC = "shortDesc"; //$NON-NLS-1$

	public static final String ELEM_ROOT_REQUEST = "rootRequest"; //$NON-NLS-1$

	public static final int SEQUENCE_NUMBER = 4;

	public static final String TAG = "componentQuery"; //$NON-NLS-1$

	public static ComponentQuery fromStream(URL url, IConnectContext cctx, InputStream stream, boolean validating) throws CoreException {
		try {
			IParserFactory pf = CorePlugin.getDefault().getParserFactory();
			IParser<ComponentQuery> parser = pf.getComponentQueryParser(false);
			ComponentQuery cquery = parser.parse(url.toString(), stream);
			cquery.setConnectContext(cctx);
			return cquery;
		} catch (Exception e) {
			throw BuckminsterException.wrap(e);
		}
	}

	public static ComponentQuery fromURL(URL url, IConnectContext cctx, boolean validating) throws CoreException {
		InputStream stream = null;
		try {
			stream = DownloadManager.read(url, cctx);
			return fromStream(url, cctx, stream, validating);
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			IOUtils.close(stream);
		}
	}

	private final List<AdvisorNode> advisorNodes;

	private transient Map<String, String> allProperties;

	private final Documentation documentation;

	private final Map<String, String> properties;

	private final URL contextURL;

	private final String propertiesURL;

	private final String resourceMapURL;

	private final ComponentRequest rootRequest;

	private final String shortDesc;

	private transient IConnectContext connectContext;

	public ComponentQuery(ComponentQueryBuilder bld) {
		documentation = bld.getDocumentation();
		shortDesc = bld.getShortDesc();
		propertiesURL = bld.getPropertiesURL();
		resourceMapURL = bld.getResourceMapURL();
		rootRequest = EcoreUtil.copy(bld.getRootRequest());

		List<AdvisorNodeBuilder> advisorNodeBuilders = bld.getAdvisoryNodes();
		if (advisorNodeBuilders.size() == 0)
			advisorNodes = Collections.emptyList();
		else {
			ArrayList<AdvisorNode> advisorNodeList = new ArrayList<AdvisorNode>(advisorNodeBuilders.size());
			for (AdvisorNodeBuilder nodeBld : advisorNodeBuilders)
				advisorNodeList.add(nodeBld.create());
			advisorNodes = Collections.unmodifiableList(advisorNodeList);
		}

		Map<String, String> props = bld.getDeclaredProperties();
		if (props == null || props.size() == 0)
			properties = Collections.emptyMap();
		else
			properties = Collections.unmodifiableMap(new ExpandingProperties(props));

		contextURL = bld.getContextURL();
	}

	public boolean allowCircularDependency(ComponentName cName, Map<String, ? extends Object> props) {
		IAdvisorNode node = getMatchingNode(cName, props);
		return node == null ? false : node.allowCircularDependency();
	}

	@Override
	public List<? extends IAdvisorNode> getAdvisoryNodes() {
		return advisorNodes;
	}

	public List<String> getAttributes(ComponentName cName, Map<String, ? extends Object> props) {
		IAdvisorNode node = getMatchingNode(cName, props);
		return node == null ? Collections.<String> emptyList() : node.getAttributes();
	}

	public VersionSelector[] getBranchTagPath(ComponentName cName, Map<String, ? extends Object> props) {
		IAdvisorNode node = getMatchingNode(cName, props);
		return node == null ? VersionSelector.EMPTY_PATH : node.getBranchTagPath();
	}

	public IConnectContext getConnectContext() {
		return connectContext;
	}

	@Override
	public URL getContextURL() {
		return contextURL;
	}

	@Override
	public Map<String, String> getDeclaredProperties() {
		return properties;
	}

	@Override
	public String getDefaultTag() {
		return TAG;
	}

	@Override
	public Documentation getDocumentation() {
		return documentation;
	}

	public ComponentRequest getExpandedRootRequest(Map<String, String> props) {
		String name = rootRequest.getId();
		String expName = ExpandingProperties.expand(props, name, 0);
		if (name.equals(expName))
			return rootRequest;
		ComponentRequest result = EcoreUtil.copy(rootRequest);
		result.setId(expName);
		return result;
	}

	public synchronized Map<String, String> getGlobalProperties() {
		if (allProperties != null)
			return allProperties;

		allProperties = new ExpandingProperties();
		allProperties.putAll(properties);

		if (propertiesURL != null) {
			URL propsURL = getResolvedPropertiesURL();
			InputStream input = null;
			try {
				input = DownloadManager.read(propsURL, getConnectContext());
				Map<String, String> urlProps = new BMProperties(input);
				if (urlProps.size() > 0) {
					allProperties = new ExpandingProperties(allProperties);
					allProperties.putAll(urlProps);
				}
			} catch (Exception e) {
				// We allow missing properties but we log it nevertheless
				//
				CorePlugin.getLogger().info(NLS.bind(Messages.Unable_to_read_property_file_0_1, propsURL, e.toString()));
			} finally {
				IOUtils.close(input);
			}
		}
		return allProperties;
	}

	public IAdvisorNode getMatchingNode(ComponentName cName, Map<String, ? extends Object> props) {
		String name = cName.getId();
		for (IAdvisorNode aNode : advisorNodes) {
			Pattern pattern = aNode.getNamePattern();
			if (!(pattern == null || pattern.matcher(name).find()))
				continue;

			String matchingType = aNode.getComponentTypeID();
			if (!(matchingType == null || matchingType.equals(cName.getType())))
				continue;

			Filter filter = aNode.getFilter();
			if (filter == null || filter.match(props))
				return aNode;
		}
		return null;
	}

	@Override
	public AdvisorNode getNodeByCriteria(Pattern pattern, String componentType, Filter filter) {
		for (AdvisorNode node : advisorNodes)
			if (Trivial.equalsAllowNull(node.getNamePattern(), pattern) && Trivial.equalsAllowNull(node.getComponentTypeID(), componentType)
					&& Trivial.equalsAllowNull(node.getFilter(), filter))
				return node;
		return null;
	}

	public URL getOverlayFolder(ComponentName cName, Map<String, ? extends Object> props) {
		IAdvisorNode node = getMatchingNode(cName, props);
		return node == null ? null : node.getOverlayFolder();
	}

	@Override
	public String getPropertiesURL() {
		return propertiesURL;
	}

	public ProviderScore getProviderScore(ComponentName cName, boolean mutable, boolean source, Map<String, ? extends Object> props) {
		IAdvisorNode node = getMatchingNode(cName, props);
		if (node == null)
			return ProviderScore.GOOD;

		ProviderScore mutableScore = ProviderScore.FAIR;
		switch (node.getMutableLevel()) {
			case REQUIRE:
				if (!mutable)
					return ProviderScore.REJECTED;
				mutableScore = ProviderScore.PREFERRED;
				break;
			case DESIRE:
				mutableScore = mutable ? ProviderScore.GOOD : ProviderScore.BAD;
				break;
			case AVOID:
				mutableScore = mutable ? ProviderScore.BAD : ProviderScore.GOOD;
				break;
			case REJECT:
				if (mutable)
					return ProviderScore.REJECTED;
				mutableScore = ProviderScore.PREFERRED;
				break;
			default:
		}

		ProviderScore sourceScore = ProviderScore.FAIR;
		switch (node.getSourceLevel()) {
			case REQUIRE:
				if (!source)
					return ProviderScore.REJECTED;
				sourceScore = ProviderScore.PREFERRED;
				break;
			case DESIRE:
				sourceScore = source ? ProviderScore.GOOD : ProviderScore.BAD;
				break;
			case AVOID:
				sourceScore = source ? ProviderScore.BAD : ProviderScore.GOOD;
				break;
			case REJECT:
				if (source)
					return ProviderScore.REJECTED;
				sourceScore = ProviderScore.PREFERRED;
				break;
			default:
		}
		return ProviderScore.values()[(sourceScore.ordinal() + mutableScore.ordinal()) / 2];
	}

	public int[] getResolutionPrio(ComponentName cName, Map<String, ? extends Object> props) {
		IAdvisorNode node = getMatchingNode(cName, props);
		return node == null ? IAdvisorNode.DEFAULT_RESOLUTION_PRIO : node.getResolutionPrio();
	}

	public URL getResolvedPropertiesURL() {
		return propertiesURL == null ? null : URLUtils.resolveURL(contextURL,
				ExpandingProperties.expand(BMProperties.getSystemProperties(), propertiesURL, 0));
	}

	public URL getResolvedResourceMapURL() {
		return resourceMapURL == null ? null : URLUtils.resolveURL(contextURL,
				ExpandingProperties.expand(BMProperties.getSystemProperties(), resourceMapURL, 0));
	}

	@Override
	public String getResourceMapURL() {
		return resourceMapURL;
	}

	public String getRevision(ComponentName cName, Map<String, ? extends Object> props) {
		IAdvisorNode node = getMatchingNode(cName, props);
		return node == null ? null : node.getRevision();
	}

	@Override
	public ComponentRequest getRootRequest() {
		return rootRequest;
	}

	@Override
	public String getShortDesc() {
		return shortDesc;
	}

	public String getTagInfo() {
		return NLS.bind(Messages.Query_for_0, rootRequest);
	}

	public Date getTimestamp(ComponentName cName, Map<String, ? extends Object> props) {
		IAdvisorNode node = getMatchingNode(cName, props);
		return node == null ? null : node.getTimestamp();
	}

	public VersionRange getVersionOverride(ComponentName cName, Map<String, ? extends Object> props) {
		IAdvisorNode node = getMatchingNode(cName, props);
		return node == null ? null : node.getVersionOverride();
	}

	@Override
	public boolean isPersisted(StorageManager sm) throws CoreException {
		return false;
	}

	public boolean isPrune(ComponentName cName, Map<String, ? extends Object> props) {
		IAdvisorNode node = getMatchingNode(cName, props);
		return node == null ? false : node.isPrune();
	}

	@Override
	public void remove(StorageManager sm) throws CoreException {
		throw new UnsupportedOperationException();
	}

	public void removeAdvisorNode(IAdvisorNode node) {
		advisorNodes.remove(node);
	}

	public ComponentQuery resolve() {
		ComponentQueryBuilder bld = new ComponentQueryBuilder();
		bld.initFrom(this);
		URL tmp = getResolvedPropertiesURL();
		if (tmp != null)
			bld.setPropertiesURL(tmp.toString());
		tmp = getResolvedResourceMapURL();
		if (tmp != null)
			bld.setResourceMapURL(tmp.toString());
		bld.setContextURL(null);
		return bld.createComponentQuery();
	}

	public boolean skipComponent(ComponentName cName, Map<String, ? extends Object> props) {
		IAdvisorNode node = getMatchingNode(cName, props);
		return node == null ? false : node.skipComponent();
	}

	@Override
	public void store(StorageManager sm) throws CoreException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void toSax(ContentHandler handler) throws SAXException {
		handler.startDocument();
		toSax(handler, BM_CQUERY_NS, BM_CQUERY_PREFIX, getDefaultTag());
		handler.endDocument();
	}

	@Override
	public void toSax(ContentHandler handler, String namespace, String prefix, String localName) throws SAXException {
		handler.startPrefixMapping(BM_CQUERY_PREFIX, BM_CQUERY_NS);
		super.toSax(handler, namespace, prefix, localName);
		handler.endPrefixMapping(BM_CQUERY_PREFIX);
	}

	public boolean useMaterialization(ComponentName cName, Map<String, ? extends Object> props) {
		IAdvisorNode node = getMatchingNode(cName, props);
		return node == null ? true : node.isUseMaterialization();
	}

	public boolean useResolutionService(ComponentName cName, Map<String, ? extends Object> props) {
		IAdvisorNode node = getMatchingNode(cName, props);
		return node == null ? true : node.isUseRemoteResolution();
	}

	public boolean useTargetPlatform(ComponentName cName, Map<String, ? extends Object> props) {
		IAdvisorNode node = getMatchingNode(cName, props);
		return node == null ? true : node.isUseTargetPlatform();
	}

	public boolean useWorkspace(ComponentName cName, Map<String, ? extends Object> props) {
		IAdvisorNode node = getMatchingNode(cName, props);
		return node == null ? true : node.isUseWorkspace();
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException {
		if (resourceMapURL != null)
			Utils.addAttribute(attrs, ATTR_RESOURCE_MAP, resourceMapURL.toString());
		if (propertiesURL != null)
			Utils.addAttribute(attrs, ATTR_PROPERTIES, propertiesURL.toString());
		if (shortDesc != null)
			Utils.addAttribute(attrs, ATTR_SHORT_DESC, shortDesc);
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException {
		if (documentation != null)
			documentation.toSax(handler, namespace, prefix, documentation.getDefaultTag());

		SAXEmitter.emit(handler, rootRequest, namespace, prefix, ELEM_ROOT_REQUEST);
		SAXEmitter.emitProperties(handler, properties, namespace, prefix, true, false);

		for (AdvisorNode node : advisorNodes)
			node.toSax(handler, namespace, prefix, node.getDefaultTag());
	}

	@Override
	protected String getElementNamespace(String namespace) {
		return BM_CQUERY_NS;
	}

	@Override
	protected String getElementPrefix(String prefix) {
		return BM_CQUERY_PREFIX;
	}

	private void setConnectContext(IConnectContext cctx) {
		connectContext = cctx;
	}
}
