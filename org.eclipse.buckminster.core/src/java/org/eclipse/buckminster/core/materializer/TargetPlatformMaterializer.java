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
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.TargetPlatform;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.ConflictResolution;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Preferences;
import org.eclipse.osgi.util.NLS;
import org.eclipse.update.configuration.IConfiguredSite;
import org.eclipse.update.core.IFeature;
import org.eclipse.update.core.ISite;
import org.eclipse.update.core.ISiteFeatureReference;
import org.eclipse.update.core.SiteManager;

/**
 * Materializes each component to the local filesystem.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("deprecation")
public class TargetPlatformMaterializer extends AbstractSiteMaterializer
{
	public static final String TARGET_PLATFORM_PATH = "targetPlatformPathForMaterialization"; //$NON-NLS-1$

	private static IPath getDefaultInstallRoot() throws CoreException
	{
		try
		{
			return Path.fromOSString(TargetPlatform.getPlatformInstallLocation().getCanonicalPath());
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	private static ISite getDefaultInstallSite() throws CoreException
	{
		IConfiguredSite installSite = null;
		IConfiguredSite[] configuredSites = SiteManager.getLocalSite().getCurrentConfiguration().getConfiguredSites();
		for(int idx = 0; idx < configuredSites.length; ++idx)
		{
			IConfiguredSite configuredSite = configuredSites[idx];
			if(configuredSite.isProductSite() && configuredSite.isUpdatable())
			{
				installSite = configuredSite;
				break;
			}
		}
		if(installSite == null)
			throw BuckminsterException.fromMessage(Messages.Could_not_find_a_site_to_install_to);
		return installSite.getSite();
	}

	@Override
	public IPath getDefaultInstallRoot(MaterializationContext context, Resolution resolution) throws CoreException
	{
		CorePlugin corePlugin = CorePlugin.getDefault();
		Preferences preferences = corePlugin.getPluginPreferences();
		String path = preferences.getString(TARGET_PLATFORM_PATH);
		if(!"".equals(path)) //$NON-NLS-1$
		{
			return Path.fromOSString(path);
		}
		return getDefaultInstallRoot();
	}

	@Override
	public String getMaterializerRootDir()
	{
		return "downloads"; //$NON-NLS-1$
	}

	@Override
	protected ISite getDestinationSite(MaterializationContext context, IPath destination, IProgressMonitor monitor)
			throws CoreException
	{
		monitor.beginTask(null, 100);
		try
		{
			synchronized(SiteManager.class)
			{
				if(destination.equals(getDefaultInstallRoot()))
					return getDefaultInstallSite();

				File folder = destination.hasTrailingSeparator()
						? destination.toFile()
						: destination.removeLastSegments(1).toFile();

				FileUtils.prepareDestination(folder, ConflictResolution.UPDATE, MonitorUtils.subMonitor(monitor, 5));
				String destStr = destination.toPortableString();
				if(!destStr.startsWith("/")) //$NON-NLS-1$
					//
					// An absolute path can start with C:/. Here it must be /C:/
					//
					destStr = '/' + destStr;

				// Note. We really WANT an incorrect URL here in case the path contains
				// spaces. If we create a correctly escaped one by passing it through a
				// URI, the SiteManager will puke.
				//
				URL url = new URL("file", null, destStr); //$NON-NLS-1$
				return SiteManager.getSite(url, false, MonitorUtils.subMonitor(monitor, 95));
			}
		}
		catch(CoreException e)
		{
			IStatus s = e.getStatus();
			IStatus[] children = s.getChildren();
			if(children.length == 1)
			{
				s = children[0];
				if(s.getException() instanceof FileNotFoundException && s.getMessage().startsWith("Unable to access")) //$NON-NLS-1$
					e = BuckminsterException.fromMessage(NLS.bind(Messages.No_target_platform_found_at_0, destination));
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
			URL url = destinationSite.getURL();
			if("file".equals(url.getProtocol())) //$NON-NLS-1$
			{
				try
				{
					File a = Path.fromPortableString(url.getPath()).toFile().getCanonicalFile();
					File b = getDefaultInstallRoot().toFile().getCanonicalFile();
					if(a.equals(b))
						context.setRebootNeeded(true);
				}
				catch(IOException e)
				{
					// Ignore since this won't happen with the default install root
				}
			}

			for(ISiteFeatureReference featureRef : featureRefs)
			{
				IFeature feature = featureRef.getFeature(MonitorUtils.subMonitor(monitor, 50));
				IFeature platformIngoringFeature = convertFeature(feature, context);
				destinationSite.install(platformIngoringFeature, null, MonitorUtils.subMonitor(monitor, 50));
			}
		}
		finally
		{
			monitor.done();
		}
	}

	/**
	 * @param aFeature
	 *            The feature to convert into a new IFeature instance that treats platform specific bundles as regular
	 *            ones. Thus installs even non matching bundles.
	 * @param context
	 * @return
	 * @throws CoreException
	 */
	private IFeature convertFeature(IFeature aFeature, MaterializationContext context) throws CoreException
	{
		// A shallow copy should be sufficient
		IFeature piFeature = new PlatformIgnoringFeature(context);
		try
		{
			Class<?> clazz = aFeature.getClass();
			while(clazz != null)
			{
				Field[] fields = clazz.getDeclaredFields();
				for(int i = 0; i < fields.length; i++)
				{
					Field field = fields[i];
					if(!(Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers()))
							|| field.isEnumConstant())
					{
						field.setAccessible(true);
						field.set(piFeature, field.get(aFeature));
					}
				}
				clazz = clazz.getSuperclass();
			}
		}
		catch(IllegalArgumentException e)
		{
			throw BuckminsterException.wrap(e);
		}
		catch(IllegalAccessException e)
		{
			throw BuckminsterException.wrap(e);
		}
		return piFeature;
	}
}
