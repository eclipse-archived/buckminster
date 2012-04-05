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
import org.eclipse.buckminster.pde.PDEPlugin;
import org.eclipse.buckminster.pde.internal.PDETargetPlatform;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.internal.core.target.AbstractBundleContainer;
import org.eclipse.pde.internal.core.target.provisional.IBundleContainer;
import org.eclipse.pde.internal.core.target.provisional.ITargetDefinition;
import org.eclipse.pde.internal.core.target.provisional.ITargetHandle;
import org.eclipse.pde.internal.core.target.provisional.ITargetPlatformService;
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
	public static void setTargetPlatform(String targetPlatformPath, String name, boolean active) throws BackingStoreException {
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
				if (targetPlatformPath.equals(((AbstractBundleContainer) containers[0]).getLocation(true))) {
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
				IBundleContainer container = service.newDirectoryContainer(targetPlatformPath);
				target.setBundleContainers(new IBundleContainer[] { container });
				target.setName(name);
				File tpDir = new File(targetPlatformPath);
				if (!tpDir.isDirectory()) {
					PDEPlugin.getLogger().warning(NLS.bind(Messages.tpdir_0_does_not_exist, targetPlatformPath));
					tpDir.mkdirs();
					if (!tpDir.isDirectory())
						throw new BackingStoreException(NLS.bind(Messages.unable_to_create_tpdir_0, targetPlatformPath));
				}
			}

			service.saveTargetDefinition(target);
			if (active)
				PDETargetPlatform.setTargetActive(target, new NullProgressMonitor());
		} catch (CoreException e) {
			throw new BackingStoreException(e.getMessage(), e);
		} finally {
			bucky.ungetService(service);
		}
	}

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
		setTargetPlatform(targetPlatform, "Directory " + targetPlatform, true); //$NON-NLS-1$
	}

	@Override
	public void unset() {
	}
}
