/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.internal;

import java.util.Collections;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.TargetPlatform;
import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.actor.IPerformManager;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.FilterUtils;
import org.eclipse.buckminster.core.helpers.MapToDictionary;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;

/**
 * @author Thomas Hallgren
 */
public class FragmentsActor extends AbstractActor
{
	public static final String ID = "fragments";

	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException
	{
		ComponentIdentifier cid = ctx.getCSpec().getComponentIdentifier();

		IPath outputDir = ctx.getAction().getProductBase();
		if(outputDir == null)
			throw BuckminsterException.fromMessage("missing product base in copyTargetFragments actor");

		Map<String,String> properties = ctx.getProperties();
		outputDir = new Path(ExpandingProperties.expand(properties, outputDir.toPortableString(), 0));

		IPluginModelBase launcherPlugin = PluginRegistry.findModel(cid.getName());
		if(launcherPlugin == null)
		{
			MonitorUtils.complete(monitor);
			return Status.OK_STATUS;
		}

		BundleDescription bundle = launcherPlugin.getBundleDescription();
		if(bundle == null)
		{
			MonitorUtils.complete(monitor);
			return Status.OK_STATUS;
		}

		BundleDescription[] fragments = bundle.getFragments();
		if(fragments == null || fragments.length == 0)
		{
			MonitorUtils.complete(monitor);
			return Status.OK_STATUS;
		}

		monitor.beginTask(null, 100 + 100 * fragments.length);
		IPerformManager performManager = CorePlugin.getPerformManager();
		try
		{
			for(BundleDescription fragment : bundle.getFragments())
			{
				String fragmentName = fragment.getName();
				if(fragmentName.contains(".compatibility"))
					//
					// Compatibility fragments must be explicitly brought in using
					// a product or a feature
					//
					continue;

				ComponentRequest request = new ComponentRequest(
					fragmentName, IComponentType.OSGI_BUNDLE,
					VersionFactory.createExplicitDesignator(VersionFactory.OSGiType.coerce(fragment.getVersion()))); 

				String filterStr = fragment.getPlatformFilter();
				if(filterStr != null)
				{
					try
					{
						Filter filter = FilterUtils.createFilter(fragment.getPlatformFilter());
						filter = FilterUtils.replaceAttributeNames(filter, "osgi", TargetPlatform.TARGET_PREFIX);
						if(!filter.match(MapToDictionary.wrap(properties)))
							continue;
					}
					catch(InvalidSyntaxException e)
					{
						throw BuckminsterException.wrap(e);
					}
				}

				Resolution res = WorkspaceInfo.getResolution(request, false);
				if(res == null)
				{
					MonitorUtils.worked(monitor, 100);
					continue;
				}

				// Obtain the bundle.jars attribute from the cspec. Make sure the action is executed
				// (if it indeed is an action)
				//
				CSpec cspec = res.getCSpec();
				Attribute bundleJars = cspec.getAttribute(IPDEConstants.ATTRIBUTE_BUNDLE_JARS);
				performManager.perform(Collections.singletonList(bundleJars), ctx.getGlobalContext(), MonitorUtils.subMonitor(monitor, 70));

				// Copy the path groups to the given destination
				//
				PathGroup[] groups = bundleJars.getPathGroups(ctx, null);
				IProgressMonitor copyMon = MonitorUtils.subMonitor(monitor, 30);
				copyMon.beginTask(null, groups.length * 100);
				for(PathGroup pathGroup : groups)
					pathGroup.copyTo(outputDir, MonitorUtils.subMonitor(copyMon, 100));
				copyMon.done();
			}
			return Status.OK_STATUS;
		}
		finally
		{
			monitor.done();
		}
	}
}
