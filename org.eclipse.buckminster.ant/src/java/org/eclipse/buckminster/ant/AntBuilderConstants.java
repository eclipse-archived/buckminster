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
	public static final String ARG_SCRIPT_FILE_KEY = "script.file";
	public static final String ARG_OVERRIDE_BASEDIR_KEY = "override.basedir";
	public static final String ARG_AUTO_KIND_TARGET_KEY = "auto.kind.target";
	public static final String ARG_INCREMENTAL_KIND_TARGET_KEY = "incremental.kind.target";
	public static final String ARG_FULL_KIND_TARGET_KEY = "full.kind.target";
	public static final String ARG_CLEAN_KIND_TARGET_KEY = "clean.kind.target";
	public static final String ARG_COMPONENT_NAME_PROPERTY_KEY = "component.name.property";
	public static final String ARG_BUILD_KIND_PROPERTY_KEY = "build.kind.property";
	public static final String DEFAULT_SCRIPT_FILE = "buckminster.ant";
	public static final String DEFAULT_CLEAN_KIND_TARGET = "clean";
}
