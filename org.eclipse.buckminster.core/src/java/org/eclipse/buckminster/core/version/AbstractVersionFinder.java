/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.version;

import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ResolverDecision;
import org.eclipse.buckminster.core.resolver.ResolverDecisionType;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.ProviderScore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.equinox.internal.provisional.p2.metadata.Version;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public abstract class AbstractVersionFinder implements IVersionFinder
{
	private final Provider m_provider;

	private final NodeQuery m_query;

	private final IComponentType m_componentType;

	public AbstractVersionFinder(Provider provider, IComponentType componentType, NodeQuery query)
	{
		m_provider = provider;
		m_query = query;
		m_componentType = componentType;
	}

	public void close()
	{
	}

	public IComponentType getComponentType()
	{
		return m_componentType;
	}

	public IConnectContext getConnectContext()
	{
		IConnectContext cctx = m_provider.getConnectContext();
		if(cctx == null)
			cctx = m_query.getComponentQuery().getConnectContext();
		return cctx;
	}

	public Provider getProvider()
	{
		return m_provider;
	}

	public ProviderMatch getProviderMatch(VersionMatch versionMatch, IComponentType ctypeUsed, ProviderScore score)
			throws CoreException
	{
		return new ProviderMatch(m_provider, ctypeUsed, versionMatch, score, m_query);
	}

	public NodeQuery getQuery()
	{
		return m_query;
	}

	public ResolverDecision logDecision(ComponentRequest request, ResolverDecisionType decisionType, Object... args)
	{
		return m_query.logDecision(decisionType, args);
	}

	public ResolverDecision logDecision(ResolverDecisionType decisionType, Object... args)
	{
		return m_query.logDecision(decisionType, args);
	}

	protected Version getVersionFromArtifacts(VersionSelector branchOrTag, IProgressMonitor monitor)
			throws CoreException
	{
		VersionMatch match = new VersionMatch(null, branchOrTag, m_query.getRevision(), m_query.getTimestamp(), null);
		ProviderMatch rInfo = new ProviderMatch(m_provider, m_componentType, match, m_query);
		return m_componentType.getComponentVersion(rInfo, monitor);
	}
}
