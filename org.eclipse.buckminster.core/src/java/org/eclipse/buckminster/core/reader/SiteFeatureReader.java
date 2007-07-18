/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.reader;

import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.update.core.IFeature;

/**
 * @author Thomas Hallgren
 */
public class SiteFeatureReader extends AbstractRemoteReader
{

	protected SiteFeatureReader(IReaderType readerType, ProviderMatch rInfo) throws CoreException
	{
		super(readerType, rInfo);
	}

	public IFeature getFeature(IProgressMonitor monitor) throws CoreException
	{
		NodeQuery query = getNodeQuery();
		ProviderMatch match = getProviderMatch();
		String remoteSite = match.getProvider().getURI(query.getProperties());
		ComponentRequest request = query.getComponentRequest();
		ComponentIdentifier ci = new ComponentIdentifier(request.getName(), request.getComponentTypeID(), match.getVersionMatch().getVersion());
		return SiteFeatureReaderType.getSiteFeature(remoteSite, ci, monitor);
	}

	public void innerMaterialize(IPath destination, IProgressMonitor monitor) throws CoreException
	{
		throw new UnsupportedOperationException("A SiteFeatureReader is not capable of materializing");
	}
}
