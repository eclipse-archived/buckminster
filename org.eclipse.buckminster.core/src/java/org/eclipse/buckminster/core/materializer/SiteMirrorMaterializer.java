/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.materializer;

import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.update.core.ISite;
import org.eclipse.update.core.ISiteFeatureReference;
import org.eclipse.update.core.model.InvalidSiteTypeException;
import org.eclipse.update.internal.mirror.MirrorSite;
import org.eclipse.update.internal.mirror.MirrorSiteFactory;

/**
 * Materializes each component to the local filesystem.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class SiteMirrorMaterializer extends AbstractSiteMaterializer
{
	public static final String MIRROR_SITE_URL_PROPERTY = "mirror.site.url";

	@Override
	protected ISite getDestinationSite(MaterializationContext context, IPath destination, IProgressMonitor monitor)
			throws CoreException
	{
		MirrorSiteFactory factory = new MirrorSiteFactory();
		MirrorSite mirrorSite;
		try
		{
			mirrorSite = (MirrorSite)factory.createSite(destination.toFile());
		}
		catch(InvalidSiteTypeException e)
		{
			throw BuckminsterException.wrap(e);
		}
		mirrorSite.setIgnoreNonPresentPlugins(context.isContinueOnError());
		MonitorUtils.complete(monitor);
		return mirrorSite;
	}

	@Override
	public String getMaterializerRootDir()
	{
		return "siteMirrors";
	}

	@Override
	protected void installFeatures(MaterializationContext context, ISite destinationSite, ISite fromSite,
			ISiteFeatureReference[] features, IProgressMonitor monitor) throws CoreException
	{
		((MirrorSite)destinationSite).mirrorAndExpose(fromSite, features, null, context.get(MIRROR_SITE_URL_PROPERTY));
		MonitorUtils.complete(monitor);
	}
}
