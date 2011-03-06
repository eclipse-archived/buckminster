/*****************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.internal.actor;

import java.util.Collections;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.TargetPlatform;
import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.actor.IPerformManager;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.FilterUtils;
import org.eclipse.buckminster.core.metadata.IResolution;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.osgi.filter.FilterFactory;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.osgi.framework.InvalidSyntaxException;

/**
 * @author Thomas Hallgren
 */
public class FragmentsActor extends AbstractActor {
	public static final String ID = "copyTargetFragments"; //$NON-NLS-1$

	public static final String PROP_FRAGMENT_ATTRIBUTE = "fragment.attribute"; //$NON-NLS-1$

	@Override
	public boolean isUpToDate(Action action, IModelCache ctx, long prerequisiteAge, long oldestTarget) throws CoreException {
		ComponentIdentifier cid = action.getCSpec().getComponentIdentifier();
		IPath outputDir = action.getProductBase();
		if (outputDir == null)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.missing_product_base_in_0_actor, ID));

		Map<String, ? extends Object> properties = ctx.getProperties();
		outputDir = new Path(ExpandingProperties.expand(properties, outputDir.toPortableString(), 0));

		IPluginModelBase plugin = PluginRegistry.findModel(cid.getName());
		if (plugin == null)
			return true;

		BundleDescription bundle = plugin.getBundleDescription();
		if (bundle == null)
			return true;

		BundleDescription[] fragments = bundle.getFragments();
		if (fragments == null || fragments.length == 0)
			return true;

		int count = 0;
		for (BundleDescription fragment : fragments) {
			String fragmentName = fragment.getName();
			if (fragmentName.contains(".compatibility") || fragmentName.endsWith(".test") || fragmentName.endsWith(".dummy")) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				//
				// Compatibility fragments must be explicitly brought in using
				// a product or a feature
				//
				continue;

			ComponentRequest request = new ComponentRequest(fragmentName, IComponentType.OSGI_BUNDLE, VersionHelper.exactRange(fragment.getVersion()));

			String filterStr = fragment.getPlatformFilter();
			if (filterStr != null) {
				try {
					Filter filter = FilterFactory.newInstance(fragment.getPlatformFilter());
					filter = FilterUtils.replaceAttributeNames(filter, "osgi", TargetPlatform.TARGET_PREFIX); //$NON-NLS-1$
					if (!filter.matches(properties))
						continue;
				} catch (InvalidSyntaxException e) {
					throw BuckminsterException.wrap(e);
				}
			}

			IResolution res = WorkspaceInfo.getResolution(request, false);
			if (res == null)
				continue;

			count++;
		}
		if (count == 0)
			return true;

		String[] fragFiles = outputDir.toFile().list();
		return (fragFiles != null && fragFiles.length >= count);
	}

	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException {
		ComponentIdentifier cid = ctx.getCSpec().getComponentIdentifier();

		IPath outputDir = ctx.getAction().getProductBase();
		if (outputDir == null)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.missing_product_base_in_0_actor, ID));

		Map<String, ? extends Object> properties = ctx.getProperties();
		outputDir = new Path(ExpandingProperties.expand(properties, outputDir.toPortableString(), 0));

		IPluginModelBase launcherPlugin = PluginRegistry.findModel(cid.getName());
		if (launcherPlugin == null) {
			MonitorUtils.complete(monitor);
			return Status.OK_STATUS;
		}

		BundleDescription bundle = launcherPlugin.getBundleDescription();
		if (bundle == null) {
			MonitorUtils.complete(monitor);
			return Status.OK_STATUS;
		}

		BundleDescription[] fragments = bundle.getFragments();
		if (fragments == null || fragments.length == 0) {
			MonitorUtils.complete(monitor);
			return Status.OK_STATUS;
		}

		String fragmentAttribute = (String) properties.get(PROP_FRAGMENT_ATTRIBUTE);
		if (fragmentAttribute == null)
			fragmentAttribute = IPDEConstants.ATTRIBUTE_BUNDLE_JAR;

		monitor.beginTask(null, 100 + 100 * fragments.length);
		IPerformManager performManager = CorePlugin.getPerformManager();
		try {
			for (BundleDescription fragment : fragments) {
				String fragmentName = fragment.getName();
				if (fragmentName.contains(".compatibility")) //$NON-NLS-1$
					//
					// Compatibility fragments must be explicitly brought in
					// using
					// a product or a feature
					//
					continue;

				ComponentRequest request = new ComponentRequest(fragmentName, IComponentType.OSGI_BUNDLE, VersionHelper.exactRange(fragment
						.getVersion()));

				String filterStr = fragment.getPlatformFilter();
				if (filterStr != null) {
					try {
						Filter filter = FilterFactory.newInstance(fragment.getPlatformFilter());
						filter = FilterUtils.replaceAttributeNames(filter, "osgi", TargetPlatform.TARGET_PREFIX); //$NON-NLS-1$
						if (!filter.matches(properties))
							continue;
					} catch (InvalidSyntaxException e) {
						throw BuckminsterException.wrap(e);
					}
				}

				Resolution res = WorkspaceInfo.getResolution(request, false);
				if (res == null) {
					MonitorUtils.worked(monitor, 100);
					continue;
				}

				// Obtain the bundle.jars attribute from the cspec. Make sure
				// the action is executed
				// (if it indeed is an action)
				//
				CSpec cspec = res.getCSpec();
				Attribute bundleJar = cspec.getAttribute(fragmentAttribute);
				performManager.perform(Collections.singletonList(bundleJar), ctx.getGlobalContext(), MonitorUtils.subMonitor(monitor, 70));

				// Copy the path groups to the given destination
				//
				PathGroup[] groups = bundleJar.getPathGroups(ctx, null);
				IProgressMonitor copyMon = MonitorUtils.subMonitor(monitor, 30);
				copyMon.beginTask(null, groups.length * 100);
				for (PathGroup pathGroup : groups)
					pathGroup.copyTo(outputDir, MonitorUtils.subMonitor(copyMon, 100));
				copyMon.done();
			}
			return Status.OK_STATUS;
		} finally {
			monitor.done();
		}
	}
}
