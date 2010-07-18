/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.prefs;

import java.io.File;

import org.eclipse.buckminster.cmdline.BasicPreferenceHandler;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.internal.core.target.AbstractBundleContainer;
import org.eclipse.pde.internal.core.target.provisional.IBundleContainer;
import org.eclipse.pde.internal.core.target.provisional.ITargetDefinition;
import org.eclipse.pde.internal.core.target.provisional.ITargetHandle;
import org.eclipse.pde.internal.core.target.provisional.ITargetPlatformService;
import org.eclipse.pde.internal.core.target.provisional.LoadTargetDefinitionJob;
import org.osgi.service.prefs.BackingStoreException;

/**
 * This class is for backward compatibility. It creates a Directory bundle
 * container based on a given path and sets that as the currently active target
 * platform
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class TargetPlatformPathHandler extends BasicPreferenceHandler {
	@Override
	public String get(String defaultValue) throws CoreException {
		Buckminster bucky = Buckminster.getDefault();
		ITargetPlatformService service = bucky.getService(ITargetPlatformService.class);
		ITargetHandle activeHandle = service.getWorkspaceTargetHandle();
		if (activeHandle == null)
			throw BuckminsterException.fromMessage(Messages.No_active_target_platform);

		ITargetDefinition activeTarget = activeHandle.getTargetDefinition();
		IBundleContainer[] containers = activeTarget.getBundleContainers();
		if (containers == null || containers.length == 0)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.Active_target_0_has_no_containers, activeTarget.getName()));

		if (containers.length > 1)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.Active_target_0_is_too_complex, activeTarget.getName()));

		IBundleContainer activeContainer = containers[0];
		if (activeContainer instanceof AbstractBundleContainer)
			return ((AbstractBundleContainer) activeContainer).getLocation(true);

		throw BuckminsterException.fromMessage(NLS.bind(Messages.Unable_to_determine_path_for_active_target_0, activeTarget.getName()));
	}

	@Override
	public void set(String targetPlatform) throws BackingStoreException {
		Buckminster bucky = Buckminster.getDefault();
		ITargetPlatformService service = null;
		try {
			service = bucky.getService(ITargetPlatformService.class);
			ITargetDefinition target = null;
			for (ITargetHandle targetHandle : service.getTargets(new NullProgressMonitor())) {
				ITargetDefinition candidate = targetHandle.getTargetDefinition();
				IBundleContainer[] containers = candidate.getBundleContainers();
				if (containers == null || containers.length != 1)
					continue;
				if (targetPlatform.equals(((AbstractBundleContainer) containers[0]).getLocation(true))) {
					ITargetHandle activeHandle = service.getWorkspaceTargetHandle();
					if (activeHandle != null && activeHandle.equals(targetHandle))
						// This target is already active. Nothing left to do
						// here
						return;

					target = candidate;
					break;
				}
			}

			if (target == null) {
				target = service.newTarget();
				IBundleContainer container = service.newDirectoryContainer(targetPlatform);
				target.setBundleContainers(new IBundleContainer[] { container });
				target.setName("Directory " + targetPlatform); //$NON-NLS-1$
				File tpDir = new File(targetPlatform);
				tpDir.mkdirs();
				if (!tpDir.isDirectory())
					throw new BackingStoreException("Unable to create directory: " + targetPlatform); //$NON-NLS-1$
			}

			service.saveTargetDefinition(target);
			LoadTargetDefinitionJob job = new LoadTargetDefinitionJob(target);
			IStatus status = job.run(new NullProgressMonitor());
			if (status.getSeverity() == IStatus.ERROR)
				throw new CoreException(status);
		} catch (CoreException e) {
			throw new BackingStoreException(e.getMessage(), e);
		} finally {
			bucky.ungetService(service);
		}
	}

	@Override
	public void unset() {
	}
}
