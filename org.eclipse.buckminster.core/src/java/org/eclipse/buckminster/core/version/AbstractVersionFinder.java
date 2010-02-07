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
import org.eclipse.equinox.p2.metadata.Version;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractVersionFinder implements IVersionFinder {
	private final Provider provider;

	private final NodeQuery query;

	private final IComponentType componentType;

	public AbstractVersionFinder(Provider provider, IComponentType componentType, NodeQuery query) {
		this.provider = provider;
		this.query = query;
		this.componentType = componentType;
	}

	public void close() {
	}

	public IComponentType getComponentType() {
		return componentType;
	}

	public IConnectContext getConnectContext() {
		IConnectContext cctx = provider.getConnectContext();
		if (cctx == null)
			cctx = query.getComponentQuery().getConnectContext();
		return cctx;
	}

	public Provider getProvider() {
		return provider;
	}

	public ProviderMatch getProviderMatch(VersionMatch versionMatch, IComponentType ctypeUsed, ProviderScore score) throws CoreException {
		return new ProviderMatch(provider, ctypeUsed, versionMatch, score, query);
	}

	public NodeQuery getQuery() {
		return query;
	}

	public ResolverDecision logDecision(ComponentRequest request, ResolverDecisionType decisionType, Object... args) {
		return query.logDecision(decisionType, args);
	}

	public ResolverDecision logDecision(ResolverDecisionType decisionType, Object... args) {
		return query.logDecision(decisionType, args);
	}

	protected Version getVersionFromArtifacts(VersionSelector branchOrTag, IProgressMonitor monitor) throws CoreException {
		VersionMatch match = new VersionMatch(null, branchOrTag, query.getRevision(), query.getTimestamp(), null);
		ProviderMatch rInfo = new ProviderMatch(provider, componentType, match, query);
		return componentType.getComponentVersion(rInfo, monitor);
	}
}
