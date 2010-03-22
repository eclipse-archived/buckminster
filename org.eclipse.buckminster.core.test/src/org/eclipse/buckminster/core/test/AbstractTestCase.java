/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.test;

import junit.framework.TestCase;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.BuckminsterPreferences;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractTestCase extends TestCase {
	public AbstractTestCase() {
	}

	public AbstractTestCase(String name) {
		super(name);
	}

	@Override
	public void setUp() throws Exception {
		BuckminsterPreferences.setLogLevelConsole(Logger.DEBUG);
		BuckminsterPreferences.setLogLevelEclipseLogger(Logger.SILENT);
	}

	protected CorePlugin getPlugin() throws Exception {
		CorePlugin plugin = CorePlugin.getDefault();
		if (plugin == null)
			throw new Exception("This test must be run as a \"JUnit Plug-in Test\""); //$NON-NLS-1$
		return plugin;
	}

	@Override
	protected void runTest() throws Throwable {
		try {
			super.runTest();
		} catch (CoreException e) {
			BuckminsterException.deeplyPrint(e, System.err, true);
			throw e;
		}
	}
}
