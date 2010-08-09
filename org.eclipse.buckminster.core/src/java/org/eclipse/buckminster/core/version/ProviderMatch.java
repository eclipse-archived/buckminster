/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.version;

import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.metadata.builder.ResolutionBuilder;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ProviderScore;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.buckminster.rmap.VersionConverter;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public final class ProviderMatch implements Comparable<ProviderMatch> {
	private IComponentType componentType;

	private final Provider originalProvider;

	private Provider provider;

	private final ProviderScore providerScore;

	private final NodeQuery query;

	private VersionMatch versionMatch;

	private Map<String, String> matcherMap;

	private String repositoryURI;

	public ProviderMatch(Provider provider, IComponentType componentType, VersionMatch versionMatch, NodeQuery query) {
		this(provider, componentType, versionMatch, ProviderScore.PREFERRED, query);
	}

	public ProviderMatch(Provider provider, IComponentType componentType, VersionMatch versionMatch, ProviderScore providerScore, NodeQuery query) {
		this.provider = provider;
		this.originalProvider = provider;
		this.componentType = componentType;
		this.versionMatch = versionMatch == null ? VersionMatch.DEFAULT : versionMatch;
		this.providerScore = providerScore;
		this.query = query;
	}

	/**
	 * Compare this match to another match.
	 * 
	 * @param o
	 *            The match to compare to.
	 * @return 1 if this instance is considered a better match than
	 *         <code>o</code>, -1 if it's the opposite and 0 if the matches are
	 *         considered equal.
	 */
	@Override
	public int compareTo(ProviderMatch o) {
		int versionCompare = query.compare(versionMatch, o.getVersionMatch());
		return versionCompare == 0 ? providerScore.compareTo(o.getProviderScore()) : versionCompare;
	}

	/**
	 * Create a CSPEC builder that is initialized with the name, type, and
	 * version from this match.
	 * 
	 * @return The initialized builder
	 */
	public CSpecBuilder createCSpec() {
		CSpecBuilder bld = new CSpecBuilder();
		ComponentRequest request = query.getComponentRequest();
		bld.setName(request.getId());
		bld.setComponentTypeID(request.getType());
		bld.setVersion(versionMatch.getVersion());
		return bld;
	}

	public ResolutionBuilder createResolution(CSpecBuilder cspecBuilder, boolean unpack) throws CoreException {
		ResolutionBuilder resBld = new ResolutionBuilder(cspecBuilder);

		NodeQuery nq = getNodeQuery();
		resBld.setMaterializable(true);
		resBld.setComponentTypeId(getComponentType().getId());
		resBld.setRequest(nq.getComponentRequest());
		resBld.setAttributes(nq.getRequiredAttributes());
		resBld.setProvider(getProvider());
		resBld.setVersionMatch(getVersionMatch());
		resBld.setRepository(getRepositoryURI());
		resBld.setUnpack(unpack);
		return resBld;
	}

	public String getComponentName() {
		return query.getComponentRequest().getId();
	}

	public IComponentType getComponentType() {
		return componentType;
	}

	public Map<String, String> getMatcherMap() {
		return matcherMap;
	}

	public NodeQuery getNodeQuery() {
		return query;
	}

	public Provider getOriginalProvider() {
		return originalProvider;
	}

	/**
	 * Returns the provider that matched the request.
	 * 
	 * @return the provider that matched
	 */
	public Provider getProvider() {
		return provider;
	}

	/**
	 * Get the provider score for this match. The provider score is determined
	 * comparing the MutableLevel and SourceLevel with the corresponding
	 * settings of a provider.
	 * 
	 * @return the provider score.
	 */
	public final ProviderScore getProviderScore() {
		return providerScore;
	}

	/**
	 * Returns the query used when the math was created.
	 * 
	 * @return the query that was used.
	 */
	public NodeQuery getQuery() {
		return query;
	}

	/**
	 * Returns a reader initialized to materialize based on this match
	 * 
	 * @return
	 * @throws CoreException
	 */
	public IComponentReader getReader(IProgressMonitor monitor) throws CoreException {
		return getReaderType().getReader(this, monitor);
	}

	public IReaderType getReaderType() throws CoreException {
		return CorePlugin.getDefault().getReaderType(provider.getReaderType());
	}

	public synchronized String getRepositoryURI() {
		if (repositoryURI == null)
			repositoryURI = provider.getURI(query.getProperties());
		return repositoryURI;
	}

	public VersionConverter getVersionConverter() throws CoreException {
		return provider.getVersionConverter();
	}

	/**
	 * Returns the version that matched the requirement.
	 * 
	 * @return the version that matched
	 */
	public VersionMatch getVersionMatch() {
		return versionMatch;
	}

	public void setComponentType(IComponentType componentType) {
		this.componentType = componentType;
	}

	public void setMatcherMap(Map<String, String> matcherMap) {
		this.matcherMap = matcherMap;
	}

	public synchronized void setProvider(Provider provider) {
		this.provider = provider;
		this.repositoryURI = null;
	}

	public synchronized void setRepositoryURI(String repositoryURI) {
		this.repositoryURI = repositoryURI;
	}

	public void setVersionMatch(VersionMatch versionMatch) {
		this.versionMatch = versionMatch;
	}
}
