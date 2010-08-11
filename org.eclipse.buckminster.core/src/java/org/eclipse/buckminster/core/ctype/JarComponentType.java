/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.ctype;

import org.eclipse.buckminster.core.cspec.AbstractResolutionBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.reader.AbstractReader;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.rmap.util.IComponentReader;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author thhal
 */
public class JarComponentType extends AbstractComponentType {
	private static final IResolutionBuilder builder = new AbstractResolutionBuilder() {
		@Override
		public BOMNode build(IComponentReader[] readerHandle, boolean forResolutionAidOnly, IProgressMonitor monitor) throws CoreException {
			IComponentReader reader = readerHandle[0];
			ProviderMatch ri = ((AbstractReader) reader).getProviderMatch();
			CSpecBuilder dflt = ri.createCSpec();
			addSelfAsJarArtifactGroups(dflt);
			applyExtensions(dflt, forResolutionAidOnly, reader, monitor);
			return createNode(reader, dflt);
		}
	};

	@Override
	public IResolutionBuilder getResolutionBuilder(IComponentReader reader, IProgressMonitor monitor) throws CoreException {
		MonitorUtils.complete(monitor);
		return builder;
	}
}
