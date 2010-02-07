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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ResolverDecisionType;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.sax.AbstractSaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.osgi.util.NLS;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class SearchPath extends AbstractSaxableElement {
	public static final String TAG = "searchPath"; //$NON-NLS-1$

	public static final String ATTR_NAME = "name"; //$NON-NLS-1$

	private final String name;

	private final ArrayList<Provider> providers = new ArrayList<Provider>();

	private final ResourceMap resourceMap;

	public SearchPath(ResourceMap rmap, String name) {
		this.resourceMap = rmap;
		this.name = name;
	}

	public void addPrefixMappings(HashMap<String, String> prefixMappings) {
		for (Provider provider : providers)
			provider.addPrefixMappings(prefixMappings);
	}

	public final void addProvider(Provider provider) {
		providers.add(provider);
	}

	public String getDefaultTag() {
		return TAG;
	}

	/**
	 * @return Returns the name.
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Find a provider that meets the requirements as closely as possible.
	 */
	public ProviderMatch getProvider(NodeQuery query, List<Provider> noGoodList, MultiStatus problemCollector, IProgressMonitor monitor)
			throws CoreException {
		int provCnt = providers.size();
		if (provCnt == 0)
			throw new CoreException(problemCollector);

		monitor.beginTask("Matching providers", provCnt * 1000); //$NON-NLS-1$
		try {
			ProviderMatch bestMatch = null;
			VersionRange desiredVersion = query.getVersionRange();

			for (Provider provider : providers) {
				if (noGoodList.contains(provider))
					continue;

				Filter[] filterHandle = new Filter[1];
				if (!provider.isFilterMatchFor(query, filterHandle)) {
					query.logDecision(ResolverDecisionType.FILTER_MISMATCH, filterHandle[0]);
					continue;
				}

				query.logDecision(ResolverDecisionType.TRYING_PROVIDER, provider.getReaderTypeId(), provider.getURI());
				ProviderMatch match = provider.findMatch(query, problemCollector, MonitorUtils.subMonitor(monitor, 1000));
				if (match == null) {
					noGoodList.add(provider);
					continue;
				}

				String readerType = provider.getReaderTypeId();
				ProviderScore score = match.getProviderScore();

				if ((score.ordinal() >= ProviderScore.FAIR.ordinal() && IReaderType.LOCAL.equals(readerType))
						|| (score.ordinal() >= ProviderScore.GOOD.ordinal() && desiredVersion != null
								&& desiredVersion.getMinimum().equals(match.getVersionMatch().getVersion()) && desiredVersion.getMinimum().equals(
								desiredVersion.getMaximum()))) {
					// No use continuing the search. It won't get better
					// than this.
					//
					bestMatch = match;
					break;
				}

				if (bestMatch == null || match.compareTo(bestMatch) > 0) {
					if (bestMatch != null) {
						Provider rejected = bestMatch.getOriginalProvider();
						query.logDecision(ResolverDecisionType.REJECTING_PROVIDER, rejected.getReaderTypeId(), rejected.getURI(), NLS.bind(
								Messages._0_1_is_producing_a_better_match, provider.getReaderTypeId(), provider.getURI()));
					}
					bestMatch = match;
					continue;
				}

				Provider best = bestMatch.getOriginalProvider();
				query.logDecision(ResolverDecisionType.REJECTING_PROVIDER, provider.getReaderTypeId(), provider.getURI(), NLS.bind(
						Messages._0_1_is_producing_a_better_match, best.getReaderTypeId(), best.getURI()));
			}

			if (bestMatch == null) {
				query.logDecision(ResolverDecisionType.PROVIDER_NOT_FOUND);
				throw new CoreException(problemCollector);
			}

			Provider best = bestMatch.getOriginalProvider();
			query.logDecision(ResolverDecisionType.USING_PROVIDER, best.getReaderTypeId(), best.getURI());
			return bestMatch;
		} finally {
			monitor.done();
		}
	}

	public List<Provider> getProviders() {
		return providers;
	}

	/**
	 * Returns the resource map in which this search path is defined
	 * 
	 * @return the resource map
	 */
	public ResourceMap getResourceMap() {
		return resourceMap;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException {
		Utils.addAttribute(attrs, ATTR_NAME, name);
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException {
		for (Provider provider : providers)
			provider.toSax(handler, namespace, prefix, provider.getDefaultTag());
	}
}
