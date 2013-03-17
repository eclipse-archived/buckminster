/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.pde.tasks;

import java.io.File;
import java.util.Map;
import java.util.Properties;

import org.eclipse.buckminster.ant.tasks.VersionQualifierTask;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 */
public abstract class VersionConsolidator extends VersionQualifierTask {
	public static boolean getBooleanProperty(Map<String, ? extends Object> properties, String key, boolean defaultSetting) {
		if (properties == null)
			return defaultSetting;
		return toBoolean(properties.get(key), defaultSetting);
	}

	public static boolean getBooleanProperty(Properties properties, String key, boolean defaultSetting) {
		if (properties == null)
			return defaultSetting;
		return toBoolean(properties.getProperty(key), defaultSetting);
	}

	private static boolean toBoolean(Object v, boolean defaultSetting) {
		if (!(v instanceof String))
			return defaultSetting;

		String s = (String) v;
		if ("true".equalsIgnoreCase(s)) //$NON-NLS-1$
			return true;
		if ("false".equalsIgnoreCase(s)) //$NON-NLS-1$
			return false;
		return defaultSetting;
	}

	private final File outputFile;

	VersionConsolidator(File outputFile, File propertiesFile, String qualifier) throws CoreException {
		super(propertiesFile, qualifier);
		this.outputFile = outputFile;
	}

	protected boolean getBooleanProperty(String key, boolean defaultSetting) {
		return toBoolean(getProperties().get(key), defaultSetting);
	}

	File getOutputFile() {
		return outputFile;
	}
}
