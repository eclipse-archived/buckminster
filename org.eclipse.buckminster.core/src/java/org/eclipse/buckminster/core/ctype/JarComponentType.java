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
import org.eclipse.buckminster.core.helpers.AbstractComponentType;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author thhal
 */
public class JarComponentType extends AbstractComponentType
{
	private static final IResolutionBuilder s_builder = new AbstractResolutionBuilder()
	{
		public DepNode build(IComponentReader[] readerHandle, IProgressMonitor monitor) throws CoreException
		{
			IComponentReader reader = readerHandle[0];
			ProviderMatch ri = reader.getProviderMatch();
			CSpecBuilder dflt = ri.createCSpec();
			addSelfAsJarArtifactGroups(dflt);
			return createResolution(reader, applyExtensions(dflt.createCSpec(), reader, monitor));
		}
	};

	public IResolutionBuilder getResolutionBuilder(IComponentReader reader, IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return s_builder;
	}
}
