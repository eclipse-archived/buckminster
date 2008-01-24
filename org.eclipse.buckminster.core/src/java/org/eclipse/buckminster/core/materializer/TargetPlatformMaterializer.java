/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.materializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;

import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.mspec.model.ConflictResolution;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.update.core.IFeature;
import org.eclipse.update.core.ISite;
import org.eclipse.update.core.ISiteFeatureReference;
import org.eclipse.update.core.SiteManager;

/**
 * Materializes each component to the local filesystem.
 * 
 * @author Thomas Hallgren
 */
public class TargetPlatformMaterializer extends AbstractSiteMaterializer
{
	@Override
	public String getMaterializerRootDir()
	{
		return "downloads";
	}

	@Override
	protected ISite getDestinationSite(MaterializationContext context, IPath destination, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 100);
		try
		{
			synchronized(SiteManager.class)
			{
				File folder = destination.hasTrailingSeparator()
					? destination.toFile()
					: destination.removeLastSegments(1).toFile();

				FileUtils.prepareDestination(folder, ConflictResolution.UPDATE, MonitorUtils.subMonitor(monitor, 5));					
				String destStr = destination.toPortableString();
				if(!destStr.startsWith("/"))
					//
					// An absolute path can start with C:/. Here it must be /C:/
					//
					destStr = '/' + destStr;

				URI uri = new URI("file", null, destStr, null);
				return SiteManager.getSite(uri.toURL(), false, MonitorUtils.subMonitor(monitor, 95));
			}
		}
		catch(CoreException e)
		{
			IStatus s = e.getStatus();
			IStatus[] children = s.getChildren();
			if(children.length == 1)
			{
				s = children[0];
				if(s.getException() instanceof FileNotFoundException && s.getMessage().startsWith("Unable to access"))
					e = BuckminsterException.fromMessage("No target platform found at: " + destination);
			}
			throw e;
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			monitor.done();
		}
	}

	@Override
	protected void installFeatures(MaterializationContext context, ISite destinationSite, ISite fromSite,
			ISiteFeatureReference[] featureRefs, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, featureRefs.length * 100);
		try
		{
			for(ISiteFeatureReference featureRef : featureRefs)
			{
				IFeature feature = featureRef.getFeature(MonitorUtils.subMonitor(monitor, 50));
				destinationSite.install(feature, null, MonitorUtils.subMonitor(monitor, 50));
			}
		}
		finally
		{
			monitor.done();
		}
	}
}
