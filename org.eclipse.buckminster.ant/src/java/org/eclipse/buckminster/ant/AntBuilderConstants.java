/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.ant;

/**
 * @author kolwing
 */
public interface AntBuilderConstants {
	static final String PLUGIN_ID = "org.eclipse.buckminster.ant"; //$NON-NLS-1$

	static final String ARG_SCRIPT_FILE_KEY = "script.file"; //$NON-NLS-1$

	static final String ARG_OVERRIDE_BASEDIR_KEY = "override.basedir"; //$NON-NLS-1$

	static final String ARG_AUTO_KIND_TARGET_KEY = "auto.kind.target"; //$NON-NLS-1$

	static final String ARG_INCREMENTAL_KIND_TARGET_KEY = "incremental.kind.target"; //$NON-NLS-1$

	static final String ARG_FULL_KIND_TARGET_KEY = "full.kind.target"; //$NON-NLS-1$

	static final String ARG_CLEAN_KIND_TARGET_KEY = "clean.kind.target"; //$NON-NLS-1$

	static final String ARG_COMPONENT_NAME_PROPERTY_KEY = "component.name.property"; //$NON-NLS-1$

	static final String ARG_BUILD_KIND_PROPERTY_KEY = "build.kind.property"; //$NON-NLS-1$

	static final String DEFAULT_SCRIPT_FILE = "buckminster.ant"; //$NON-NLS-1$

	static final String DEFAULT_CLEAN_KIND_TARGET = "clean"; //$NON-NLS-1$

	static final String BUILDER_NAME = PLUGIN_ID + ".builder"; //$NON-NLS-1$
}
