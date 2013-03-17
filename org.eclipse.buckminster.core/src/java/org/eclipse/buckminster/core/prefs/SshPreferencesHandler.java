/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.prefs;

import org.eclipse.buckminster.cmdline.BasicPreferenceHandler;
import org.eclipse.jsch.internal.core.IConstants;
import org.eclipse.jsch.internal.core.JSchCorePlugin;
import org.osgi.service.prefs.BackingStoreException;

/**
 * Preference handler class to ensure that updates of the ssh preferences are
 * honored by the ssh code.
 * 
 * @author michal.ruzicka@cloudsmith.com
 */
@SuppressWarnings("restriction")
public class SshPreferencesHandler extends BasicPreferenceHandler {

	@Override
	public void set(String value) throws BackingStoreException {
		super.set(value);

		notifyJSchCorePlugin();
	}

	@Override
	public void unset() throws BackingStoreException {
		super.unset();

		notifyJSchCorePlugin();
	}

	protected void notifyJSchCorePlugin() {
		String key = pathAsNodeAndKey(getKey())[1];

		// make sure the ssh code picks up the updated preference value
		// when it is needed
		if (IConstants.KEY_PRIVATEKEY.equals(key)) {
			JSchCorePlugin.getPlugin().setNeedToLoadKeys(true);
		} else if (IConstants.KEY_SSH2HOME.equals(key)) {
			JSchCorePlugin.getPlugin().setNeedToLoadKnownHosts(true);
		}
	}

}
