/*****************************************************************************
 * Copyright (c) 2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.prefs;

import org.eclipse.buckminster.cmdline.BasicPreferenceHandler;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.internal.core.target.provisional.ITargetDefinition;
import org.eclipse.pde.internal.core.target.provisional.ITargetHandle;
import org.eclipse.pde.internal.core.target.provisional.ITargetPlatformService;
import org.eclipse.pde.internal.core.target.provisional.LoadTargetDefinitionJob;
import org.osgi.service.prefs.BackingStoreException;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class TargetDefinitionHandler extends BasicPreferenceHandler {
	@Override
	public String get(String defaultValue) throws CoreException {
		Buckminster bucky = Buckminster.getDefault();
		ITargetPlatformService service = bucky.getService(ITargetPlatformService.class);
		try {
			ITargetHandle activeHandle = service.getWorkspaceTargetHandle();
			if (activeHandle == null)
				return defaultValue;
			return activeHandle.getTargetDefinition().getName();
		} finally {
			bucky.ungetService(service);
		}
	}

	@Override
	public void set(String targetDefinitionName) throws BackingStoreException {
		Buckminster bucky = Buckminster.getDefault();
		ITargetPlatformService service = null;
		try {
			service = bucky.getService(ITargetPlatformService.class);
			ITargetDefinition target = null;
			for (ITargetHandle targetHandle : service.getTargets(new NullProgressMonitor())) {
				ITargetDefinition candidate = targetHandle.getTargetDefinition();
				if (Trivial.equalsAllowNull(targetDefinitionName, candidate.getName())) {
					ITargetHandle activeHandle = service.getWorkspaceTargetHandle();
					if (activeHandle != null && activeHandle.equals(targetHandle))
						// This target is already active. Nothing left to do
						// here
						return;

					target = candidate;
					break;
				}
			}

			if (target == null)
				throw BuckminsterException.fromMessage(NLS.bind(Messages.Found_no_target_definition_named_0, targetDefinitionName));

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
