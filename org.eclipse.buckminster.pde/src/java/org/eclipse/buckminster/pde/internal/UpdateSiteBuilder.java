/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.pde.internal;

import org.eclipse.buckminster.core.cspec.AbstractResolutionBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * This builder will create a default cspec. An update-site is more like
 * a repository of features then a component with dependencies. In fact,
 * it may contain any number of components, versions of components, etc.
 * and they don't need to have anything in common. In essence, an update-site
 * can be compared to a CVS, or Maven repository.
 * 
 * @author ken1
 * @author Thomas Hallgren
 */
public class UpdateSiteBuilder extends AbstractResolutionBuilder
{
	public DepNode build(IComponentReader[] readerHandle, boolean forResolutionAidOnly, IProgressMonitor monitor) throws CoreException
	{
		IComponentReader reader = readerHandle[0];
		ProviderMatch ri = reader.getProviderMatch();
		CSpecBuilder cspecBld = ri.createCSpec();
		CSpec cspec = applyExtensions(cspecBld.createCSpec(), forResolutionAidOnly, reader, monitor);
		return createResolution(reader, cspec, null);
	}
}

