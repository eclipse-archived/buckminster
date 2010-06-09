/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.core.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.internal.preferences.EclipsePreferences;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

/**
 * A IStreamConsumer responsible for reading and parsing eclipse preferences
 * from an input stream
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class EclipsePreferencesReader implements IStreamConsumer<IEclipsePreferences> {
	private static class ECRemotePrefs extends EclipsePreferences {
		public static IEclipsePreferences loadFromStream(InputStream stream) throws IOException {
			Properties props = new Properties();
			props.load(stream);
			EclipsePreferences prefs = new EclipsePreferences();
			EclipsePreferences.convertFromProperties(prefs, props, false);
			return prefs;
		}
	}

	public static final EclipsePreferencesReader INSTANCE = new EclipsePreferencesReader();

	public static final String BUCKMINSTER_PROJECT_PREFS_PATH = EclipsePreferences.DEFAULT_PREFERENCES_DIRNAME + '/' + CorePlugin.getID() + ".prefs"; //$NON-NLS-1$

	@Override
	public IEclipsePreferences consumeStream(IComponentReader fileReader, String streamName, InputStream stream, IProgressMonitor monitor)
			throws CoreException, IOException {
		monitor = MonitorUtils.ensureNotNull(monitor);
		try {
			monitor.beginTask(null, 1);
			monitor.subTask(Messages.Loading_preferences);
			return ECRemotePrefs.loadFromStream(stream);
		} finally {
			monitor.done();
		}
	}
}
