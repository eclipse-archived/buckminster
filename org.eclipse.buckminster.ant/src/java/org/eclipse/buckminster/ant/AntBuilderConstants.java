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
public interface AntBuilderConstants
{
	static final String PLUGIN_ID = "org.eclipse.buckminster.ant";
	static final String ARG_SCRIPT_FILE_KEY = "script.file";
	static final String ARG_OVERRIDE_BASEDIR_KEY = "override.basedir";
	static final String ARG_AUTO_KIND_TARGET_KEY = "auto.kind.target";
	static final String ARG_INCREMENTAL_KIND_TARGET_KEY = "incremental.kind.target";
	static final String ARG_FULL_KIND_TARGET_KEY = "full.kind.target";
	static final String ARG_CLEAN_KIND_TARGET_KEY = "clean.kind.target";
	static final String ARG_COMPONENT_NAME_PROPERTY_KEY = "component.name.property";
	static final String ARG_BUILD_KIND_PROPERTY_KEY = "build.kind.property";
	static final String DEFAULT_SCRIPT_FILE = "buckminster.ant";
	static final String DEFAULT_CLEAN_KIND_TARGET = "clean";
	static final String ANT_ACTOR_PROPERTY_BUILD_FILE_ID = "buildFileId";
	static final String BUILD_SCRIPT_POINT = PLUGIN_ID + ".buildScripts";
	static final String BUILDER_NAME = PLUGIN_ID + ".builder";
	static final String ANT_ACTOR_PROPERTY_TARGETS = "targets";
	static final String ANT_ACTOR_PROPERTY_BUILD_FILE = "buildFile";
}
