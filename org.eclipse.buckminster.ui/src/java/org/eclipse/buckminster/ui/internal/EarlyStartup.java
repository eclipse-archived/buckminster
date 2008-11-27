/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.ui.internal;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.ui.IStartup;

/**
 * @author Thomas Hallgren
 * 
 */
public class EarlyStartup implements IStartup
{
	public void earlyStartup()
	{
		// Referencing the CorePlugin will cause it to be loaded
		// That's all we want here.
		//
		CorePlugin.getLogger().debug(Messages.core_plugin_initialized);
	}
}
