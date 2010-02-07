/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.prefs;

import org.eclipse.buckminster.cmdline.BasicPreferenceHandler;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.internal.core.ICoreConstants;
import org.eclipse.pde.internal.core.target.provisional.ITargetDefinition;
import org.eclipse.pde.internal.core.target.provisional.ITargetHandle;
import org.eclipse.pde.internal.core.target.provisional.ITargetPlatformService;
import org.osgi.service.prefs.BackingStoreException;

/**
 * Custom preference handler for the target preferences.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
abstract class TargetVariableHandler extends BasicPreferenceHandler implements ICoreConstants {
	@Override
	public String get(String defaultValue) throws CoreException {
		Buckminster bucky = Buckminster.getDefault();
		ITargetPlatformService service = null;
		try {
			service = bucky.getService(ITargetPlatformService.class);
			ITargetHandle activeHandle = service.getWorkspaceTargetHandle();
			if (activeHandle == null)
				return defaultValue;
			ITargetDefinition definition = activeHandle.getTargetDefinition();
			String value = get(definition);
			if (value == null)
				value = defaultValue;
			return value;
		} finally {
			bucky.ungetService(service);
		}
	}

	@Override
	public void set(String value) throws BackingStoreException {
		Buckminster bucky = Buckminster.getDefault();
		ITargetPlatformService service = null;
		try {
			service = bucky.getService(ITargetPlatformService.class);
			ITargetHandle activeHandle = service.getWorkspaceTargetHandle();
			if (activeHandle == null)
				throw new BackingStoreException(Messages.No_active_target_platform);
			ITargetDefinition definition = activeHandle.getTargetDefinition();
			set(definition, value);
			service.saveTargetDefinition(definition);
		} catch (CoreException e) {
			throw new BackingStoreException(e.getMessage(), e);
		} finally {
			bucky.ungetService(service);
		}
	}

	abstract protected String get(ITargetDefinition definition) throws CoreException;

	abstract protected void set(ITargetDefinition definition, String value) throws CoreException;
}
