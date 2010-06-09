/*****************************************************************************
 * Copyright (c) 2010, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.prefs;

import java.util.ArrayList;

import org.eclipse.buckminster.cmdline.BasicPreferenceHandler;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.api.tools.internal.provisional.ApiPlugin;
import org.eclipse.pde.api.tools.internal.provisional.IApiBaselineManager;
import org.eclipse.pde.api.tools.internal.provisional.model.IApiBaseline;
import org.eclipse.pde.api.tools.internal.util.Util;
import org.osgi.service.prefs.BackingStoreException;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class BaselineHandler extends BasicPreferenceHandler {
	public static void rebuildApiProjects() throws CoreException {
		ArrayList<IProject> projects = new ArrayList<IProject>();
		for (IProject project : ResourcesPlugin.getWorkspace().getRoot().getProjects())
			if (project.isAccessible() && project.hasNature(org.eclipse.pde.api.tools.internal.provisional.ApiPlugin.NATURE_ID))
				projects.add(project);

		if (projects.size() == 0)
			return;

		Job buildJob = Util.getBuildJob(projects.toArray(new IProject[projects.size()]));
		buildJob.schedule();
		try {
			buildJob.join();
			IStatus status = buildJob.getResult();
			if (status.getSeverity() == IStatus.ERROR)
				throw new CoreException(status);
		} catch (InterruptedException e) {
		}
	}

	@Override
	public String get(String defaultValue) throws CoreException {
		IApiBaselineManager baselineManager = ApiPlugin.getDefault().getApiBaselineManager();
		IApiBaseline baseline = baselineManager.getDefaultApiBaseline();
		return baseline == null ? null : baseline.getName();
	}

	@Override
	public void set(String baselineName) throws BackingStoreException {
		IApiBaselineManager baselineManager = ApiPlugin.getDefault().getApiBaselineManager();
		IApiBaseline active = baselineManager.getDefaultApiBaseline();
		if (active != null && active.getName().equals(baselineName))
			return;

		try {
			for (IApiBaseline baseline : baselineManager.getApiBaselines())
				if (baseline.getName().equals(baselineName)) {
					baselineManager.setDefaultApiBaseline(baselineName);
					rebuildApiProjects();
					return;
				}
			throw BuckminsterException.fromMessage(NLS.bind(Messages.Found_no_baseline_named_0, baselineName));
		} catch (CoreException e) {
			throw new BackingStoreException(e.getMessage(), e);
		}
	}

	@Override
	public void unset() throws BackingStoreException {
		IApiBaselineManager baselineManager = ApiPlugin.getDefault().getApiBaselineManager();
		if (baselineManager.getDefaultApiBaseline() != null) {
			try {
				baselineManager.setDefaultApiBaseline(null);
				rebuildApiProjects();
			} catch (CoreException e) {
				throw new BackingStoreException(e.getMessage(), e);
			}
		}
	}
}
