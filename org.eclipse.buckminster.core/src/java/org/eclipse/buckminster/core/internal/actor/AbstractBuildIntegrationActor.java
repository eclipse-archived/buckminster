/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.internal.actor;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.cspec.WellknownActions;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;

/**
 * @author kolwing
 * 
 */
public abstract class AbstractBuildIntegrationActor extends AbstractActor {
	public static final String PROPERTY_IGNORE_PROBLEMS = "buckminster.problems.ignore"; //$NON-NLS-1$
	public static final String PROPERTY_EXCLUDE_BUILDERS = "buckminster.builders.exclude"; //$NON-NLS-1$
	public static final String PROPERTY_INCLUDE_BUILDERS = "buckminster.builders.include"; //$NON-NLS-1$

	private static void build(IProject project, int buildKind, ICommand[] buildSpec, IProgressMonitor buildMonitor) throws CoreException {
		if (buildSpec == null) {
			project.build(buildKind, buildMonitor);
		} else {
			buildMonitor.beginTask(NLS.bind(Messages.Building_0, project.getName()), buildSpec.length);
			for (ICommand buildCommand : buildSpec) {
				IProgressMonitor commandMonitor = MonitorUtils.subMonitor(buildMonitor, 1);
				project.build(buildKind, buildCommand.getBuilderName(), buildCommand.getArguments(), commandMonitor);
				commandMonitor.done();
			}
			buildMonitor.done();
		}
	}

	private static ICommand[] filterBuildSpec(IProject project, Pattern[] includeBuilders, Pattern[] skipBuilders) throws CoreException {
		if (includeBuilders == null && skipBuilders == null) {
			return null;
		}
		ICommand[] buildSpec = project.getDescription().getBuildSpec();
		int skipped = 0;
		for (int i = 0; i < buildSpec.length; i++) {
			ICommand buildCommand = buildSpec[i];
			String builderName = buildCommand.getBuilderName();
			if ((includeBuilders != null && !matches(builderName, includeBuilders)) || (skipBuilders != null && matches(builderName, skipBuilders))) {
				skipped++;
			} else if (skipped != 0) {
				buildSpec[i - skipped] = buildCommand;
			}
		}
		if (skipped != 0) {
			ICommand[] filteredSpec = new ICommand[buildSpec.length - skipped];
			System.arraycopy(buildSpec, 0, filteredSpec, 0, filteredSpec.length);
			return filteredSpec;
		}
		return null;
	}

	private static Pattern[] getFilterPatterns(IActionContext ctx, String key) {
		String filterSpecification = (String) ctx.getProperties().get(key);
		if (filterSpecification == null)
			return null;

		String[] filterStrings = filterSpecification.split(","); //$NON-NLS-1$
		Pattern[] filterPatterns = new Pattern[filterStrings.length];
		for (int i = 0; i < filterStrings.length; i++) {
			String filter = filterStrings[i].trim();
			Pattern pattern = Pattern.compile(filter);
			filterPatterns[i] = pattern;
		}
		return filterPatterns;
	}

	private static boolean isMarkerIgnored(IMarker problem, Pattern[] ignoreMarkerPatterns, Set<String> ignoreMarkerCache) throws CoreException {
		if (ignoreMarkerPatterns == null) {
			return false;
		}
		String markerType = problem.getType();
		if (ignoreMarkerCache == null) {
			return matches(markerType, ignoreMarkerPatterns);
		}
		if (ignoreMarkerCache.contains(markerType)) {
			return true;
		}
		if (matches(markerType, ignoreMarkerPatterns)) {
			ignoreMarkerCache.add(markerType);
			return true;
		}
		return false;
	}

	private static boolean matches(String name, Pattern[] filterPatterns) {
		for (Pattern pattern : filterPatterns) {
			if (pattern.matcher(name).matches())
				return true;
		}
		return false;
	}

	protected String getNameForKind(IActionContext ctx) {
		return ctx.getAction().getName();
	}

	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask(null, 300);
		try {
			IProject project = WorkspaceInfo.getProject(ctx.getCSpec().getComponentIdentifier());
			if (project == null)
				return Status.OK_STATUS;

			project.refreshLocal(IResource.DEPTH_INFINITE, MonitorUtils.subMonitor(monitor, 100));

			ICommand[] buildSpec = filterBuildSpec(project, getFilterPatterns(ctx, PROPERTY_INCLUDE_BUILDERS),
					getFilterPatterns(ctx, PROPERTY_EXCLUDE_BUILDERS));
			build(project, WellknownActions.ECLIPSE.name2Kind(getNameForKind(ctx)), buildSpec, MonitorUtils.subMonitor(monitor, 200));

			Pattern[] ignoreMarkerPatterns = getFilterPatterns(ctx, PROPERTY_IGNORE_PROBLEMS);
			Set<String> ignoreMarkerCache = ignoreMarkerPatterns == null ? null : new HashSet<String>();
			for (IMarker problem : project.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE)) {
				switch (problem.getAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO)) {
					case IMarker.SEVERITY_ERROR:
						if (isMarkerIgnored(problem, ignoreMarkerPatterns, ignoreMarkerCache))
							continue;
						throw new CoreException(new Status(IStatus.ERROR, CorePlugin.getID(), IStatus.OK,
								problem.getAttribute(IMarker.MESSAGE, ""), null)); //$NON-NLS-1$
					case IMarker.SEVERITY_WARNING:
						if (isMarkerIgnored(problem, ignoreMarkerPatterns, ignoreMarkerCache))
							continue;
						return new Status(IStatus.WARNING, CorePlugin.getID(), IStatus.OK, problem.getAttribute(IMarker.MESSAGE, ""), null); //$NON-NLS-1$
				}
			}
			return Status.OK_STATUS;
		} finally {
			monitor.done();
		}
	}
}
