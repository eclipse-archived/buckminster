/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.reader;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.resolver.IResolverBackchannel;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ResolverDecision;
import org.eclipse.buckminster.core.resolver.ResolverDecisionType;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.buckminster.rmap.VersionConverter;
import org.eclipse.buckminster.rmap.util.IComponentReader;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractReader implements IComponentReader, IResolverBackchannel {
	private final ProviderMatch providerMatch;

	private final IReaderType readerType;

	protected AbstractReader(IReaderType readerType, ProviderMatch providerMatch) {
		this.providerMatch = providerMatch;
		this.readerType = readerType;
	}

	public boolean canMaterialize() {
		return true;
	}

	@Override
	public void close() {
	}

	public IComponentType getComponentType() {
		return providerMatch.getComponentType();
	}

	public NodeQuery getNodeQuery() {
		return providerMatch.getNodeQuery();
	}

	@Override
	public Map<String, String> getProperties() {
		return providerMatch.getProvider().getProperties(providerMatch.getNodeQuery().getProperties());
	}

	public ProviderMatch getProviderMatch() {
		return providerMatch;
	}

	public IReaderType getReaderType() {
		return readerType;
	}

	@Override
	public String getReaderTypeID() {
		return readerType.getId();
	}

	public VersionConverter getVersionConverter() throws CoreException {
		return this.getProviderMatch().getVersionConverter();
	}

	@Override
	public ResolverDecision logDecision(ComponentRequest request, ResolverDecisionType decisionType, Object... args) {
		return getNodeQuery().logDecision(request, decisionType, args);
	}

	@Override
	public ResolverDecision logDecision(ResolverDecisionType decisionType, Object... args) {
		return getNodeQuery().logDecision(decisionType, args);
	}

	public abstract void materialize(IPath location, Resolution resolution, MaterializationContext ctx, IProgressMonitor monitor)
			throws CoreException;

	protected void copyOverlay(IPath destination, IProgressMonitor monitor) throws CoreException {
		// TODO: Handle file overlays
	}

	protected File createTempFile() throws IOException {
		return File.createTempFile(this.getReaderType().getId() + '-', ".tmp"); //$NON-NLS-1$
	}
}
