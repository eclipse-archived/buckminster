/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core;

/**
 * @author Thomas Hallgren
 */
public interface KeyConstants
{
	public static final String COMPONENT_NAME     = "buckminster.component";
	public static final String VERSION_DESIGNATOR = "buckminster.version.designator";
	public static final String VERSION_TYPE       = "buckminster.version.type";
	public static final String COMPONENT_VERSION  = "buckminster.version";
	public static final String MUTABLERULE        = "buckminster.rule.mutable";
	public static final String SOURCERULE         = "buckminster.rule.source";
	public static final String LOGIN_NAME         = "buckminster.login";
	public static final String LOGIN_PASSWORD     = "buckminster.password";
	public static final String SNAPSHOT           = "buckminster.snapshot";
	public static final String OVERRIDE_ECLIPSE_INSTALLED = "buckminster.override.eclipse.installed";
	public static final String COMPONENT_TYPE     = "buckminster.component.type";
	public static final String ACTION_OUTPUT_ROOT = "buckminster.output.root";
	public static final String ACTION_OUTPUT      = "buckminster.output";
	public static final String ACTION_HOME        = "buckminster.home";
	public static final String ACTION_TEMP        = "buckminster.temp";
	public static final String ACTION_HOME_REF  = "${" + ACTION_HOME + "}";
	public static final String ACTION_OUTPUT_REF  = "${" + ACTION_OUTPUT + "}";
	public static final String ACTION_TEMPDIR_REF  = "${" + ACTION_TEMP + "}";
}

