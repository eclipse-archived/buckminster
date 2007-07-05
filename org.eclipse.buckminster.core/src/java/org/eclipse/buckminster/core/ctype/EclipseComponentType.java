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

import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.helpers.AbstractComponentType;
import org.eclipse.buckminster.core.metadata.MetadataSynchronizer;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

/**
 * @author Thomas Hallgren
 */
public class EclipseComponentType extends AbstractComponentType
{
	public static final String BUNDLE_FILE = "META-INF/MANIFEST.MF";

	public static final String FEATURE_FILE = "feature.xml";

	public static final String PLUGIN_FILE = "plugin.xml";

	public static final String FRAGMENT_FILE = "fragment.xml";

	@Override
	public boolean hasProjectDescription() throws BuckminsterException
	{
		return true;
	}

	public IResolutionBuilder getResolutionBuilder(IComponentReader reader, IProgressMonitor monitor)
	throws CoreException
	{
		CorePlugin plugin = CorePlugin.getDefault();
		monitor.beginTask(null, 7000);
		if(!(reader instanceof ICatalogReader))
			throw new IllegalArgumentException("EclipseComponentType must work with catalog reader");

		// TODO: We need a way to define priority when searching for known meta-data files
		//
		ICatalogReader catalogReader = (ICatalogReader)reader;
		String category = guessCategory(catalogReader, MonitorUtils.subMonitor(monitor, 3000));
		String builderId = IResolutionBuilder.DEFAULT;
		if(category == null)
		{
			// Not a plugin or feature. Check if it has a manually added cquery or cspec
			//
			NodeQuery query = reader.getNodeQuery();

			// Not that we cannot look for a cquery at the very top. If we did, then we
			// would end up in an endless recursion since an attempt to resolve it would
			// just find it again.
			//
			boolean atTop = query.getComponentRequest().equals(query.getComponentQuery().getRootRequest());
			if(!atTop && catalogReader.exists(CorePlugin.CQUERY_FILE, MonitorUtils.subMonitor(monitor, 1000)))
			{
				builderId = IResolutionBuilder.CQUERY2BOM;
				MonitorUtils.worked(monitor, 1000);
			}
			else if(catalogReader.exists(CorePlugin.CSPEC_FILE, MonitorUtils.subMonitor(monitor, 1000)))
				builderId = IResolutionBuilder.CSPEC2CSPEC;
			else
			{
				Map<IPath,String> cspecSources = MetadataSynchronizer.getDefault().getCSpecSources();
				for(Map.Entry<IPath, String> entry : cspecSources.entrySet())
				{
					String ctypeId = entry.getValue();
					if(ctypeId.equals(getId()))
						continue;

					IPath path = entry.getKey();
					if(guessCategory(path) != null)
						continue;

					if(path.segmentCount() == 1)
					{
						String name = path.lastSegment();
						if(name.equals(CorePlugin.CQUERY_FILE) || name.equals(CorePlugin.CSPEC_FILE))
							continue;
					}

					if(catalogReader.exists(path.toOSString(), MonitorUtils.subMonitor(monitor, 1000)))
					{
						AbstractComponentType ctype =  (AbstractComponentType)plugin.getComponentType(ctypeId);
						return ctype.getResolutionBuilder(reader, monitor);
					}
				}
			}
		}
		else
		{
			builderId = KeyConstants.PLUGIN_CATEGORY.equals(category) ? IResolutionBuilder.PLUGIN2CSPEC
				: IResolutionBuilder.FEATURE2CSPEC;
			MonitorUtils.worked(monitor, 4000);
		}
		return plugin.getResolutionBuilder(builderId);
	}

	public static String guessCategory(ICatalogReader reader, IProgressMonitor monitor) throws CoreException
	{
		// This check is made in the order that is expected to get the best
		// hit-rate
		//
		String managedCategories[] = reader.getProviderMatch().getProvider().getManagedCategories();

		// We optimize the file search based on categories if the provider defines
		// the managedCategories discriminator
		//
		boolean canManagePlugin = true;
		boolean canManageFeature = true;
		if(managedCategories.length > 0)
		{
			canManagePlugin = false;
			canManageFeature = false;
			for(String managedCategory : managedCategories)
			{
				if(KeyConstants.FEATURE_CATEGORY.equals(managedCategory))
					canManageFeature = true;
				else if(KeyConstants.PLUGIN_CATEGORY.equals(managedCategory))
					canManagePlugin = true;
			}
		}

		if(!(canManagePlugin || canManageFeature))
		{
			MonitorUtils.complete(monitor);
			return null;
		}

		if(managedCategories.length == 1)
		{
			MonitorUtils.complete(monitor);
			return managedCategories[0];
		}

		monitor.beginTask(null, 4000);
		try
		{
			String category;
			if(canManagePlugin && reader.exists(BUNDLE_FILE, MonitorUtils.subMonitor(monitor, 1000)))
			{
				category = KeyConstants.PLUGIN_CATEGORY;
				MonitorUtils.worked(monitor, 3000);
			}
			else if(canManageFeature && reader.exists(FEATURE_FILE, MonitorUtils.subMonitor(monitor, 1000)))
			{
				category = KeyConstants.FEATURE_CATEGORY;
				MonitorUtils.worked(monitor, 2000);
			}
			else
			{
				category = null;
			}
			return category;
		}
		finally
		{
			monitor.done();
		}
	}

	public static String guessCategory(IPath path) throws CoreException
	{
		// This check is made in the order that is expected to get the best
		// hit-rate
		//
		String category;
		if(exists(path, BUNDLE_FILE))
			category = KeyConstants.PLUGIN_CATEGORY;
		else if(exists(path, FEATURE_FILE))
			category = KeyConstants.FEATURE_CATEGORY;
		else if(exists(path, FRAGMENT_FILE))
			category = KeyConstants.PLUGIN_CATEGORY;
		else if(exists(path, PLUGIN_FILE))
			category = KeyConstants.PLUGIN_CATEGORY;
		else
			category = null;
		return category;
	}

	public static String guessCategory(IProject project)
	{
		if(!project.isOpen())
			return null;

		// This check is made in the order that is expected to get the best
		// hit-rate
		//
		String category;
		if(exists(project, BUNDLE_FILE))
			category = KeyConstants.PLUGIN_CATEGORY;
		else if(exists(project, FEATURE_FILE))
			category = KeyConstants.FEATURE_CATEGORY;
		else if(exists(project, FRAGMENT_FILE))
			category = KeyConstants.PLUGIN_CATEGORY;
		else if(exists(project, PLUGIN_FILE))
			category = KeyConstants.PLUGIN_CATEGORY;
		else
			category = null;
		return category;
	}

	private static boolean exists(IPath base, String path)
	{
		return base.append(path).toFile().exists();
	}

	private static boolean exists(IProject project, String path)
	{
		return project.exists(new Path(path));
	}
}
