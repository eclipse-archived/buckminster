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

import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.ProviderScore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * A <code>VersionSelectorMatch</code> is the result of a comparison between a
 * desired version and an existing version using a specific {@link MatchRule}.
 * The rule denoted in a <code>VersionSelectorMatch</code> is often tighter
 * then the rule expressed as a rule when the match was performed.
 * @author Thomas Hallgren
 */
public final class ProviderMatch implements Comparable<ProviderMatch>
{
	private final Provider m_provider;
	private final ProviderScore m_providerScore;
	private final VersionMatch m_versionMatch;
	private final NodeQuery m_query;

	public ProviderMatch(Provider provider, VersionMatch versionMatch, ProviderScore providerScore, NodeQuery query)
	{
		m_provider = provider;
		m_versionMatch = versionMatch == null ? VersionMatch.DEFAULT : versionMatch;
		m_providerScore = providerScore;
		m_query = query;
	}

	/**
	 * Compare this match to another match.
	 * @param o The match to compare to.
	 * @return 1 if this instance is considered a better match than
	 *         <code>o</code>, -1 if it's the opposite and 0 if the matches
	 *         are considered equal.
	 */
	public int compareTo(ProviderMatch o)
	{
		int versionCompare = m_versionMatch.getVersion().compareTo(o.getVersionMatch().getVersion());
		return versionCompare == 0 ? m_providerScore.compareTo(o.getProviderScore()) : versionCompare;
	}

	/**
	 * Create a cspec builder that is initialized with the name, category, and
	 * version from this match.
	 * @return
	 */
	public CSpecBuilder createCSpec()
	{
		CSpecBuilder bld = new CSpecBuilder();
		ComponentRequest request = m_query.getComponentRequest();
		bld.setName(request.getName());
		bld.setCategory(request.getCategory());

		IVersion version = m_versionMatch.getVersion();
		if(version != null && !version.isDefault())
			bld.setVersion(version);

		return bld;
	}

	public String getComponentName()
	{
		return m_query.getComponentRequest().getName();
	}

	public NodeQuery getNodeQuery()
	{
		return m_query;
	}

	/**
	 * Returns the provider that matched the requirement.
	 * @return the provider that matched
	 */
	public Provider getProvider()
	{
		return m_provider;
	}

	/**
	 * Get the provider score for this match. The provider score is determined
	 * comparing the MutableLevel and SourceLevel with the corresponding
	 * settings of a provider.
	 * @return the provider score.
	 */
	public final ProviderScore getProviderScore()
	{
		return m_providerScore;
	}

	/**
	 * Returns the query used when the math was created.
	 * @return the query that was used.
	 */
	public NodeQuery getQuery()
	{
		return m_query;
	}

	/**
	 * Returns a reader initialized to materialize based on this match
	 * @return
	 * @throws CoreException
	 */
	public IComponentReader getReader(IProgressMonitor monitor) throws CoreException
	{
		return this.getReaderType().getReader(this, monitor);
	}

	public IReaderType getReaderType()
	throws CoreException
	{
		return m_provider.getReaderType();
	}

	public String getRepositoryURI()
	{
		return m_provider.getURI(m_query.getProperties());
	}

	public IVersionConverter getVersionConverter()
	throws CoreException
	{
		return m_provider.getVersionConverter();
	}

	/**
	 * Returns the version that matched the requirement.
	 * @return the version that matched
	 */
	public VersionMatch getVersionMatch()
	{
		return m_versionMatch;
	}
}
