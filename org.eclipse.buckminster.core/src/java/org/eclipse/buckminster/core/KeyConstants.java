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
	public static final String COMPONENT_NAME = "buckminster.component"; //$NON-NLS-1$

	public static final String VERSION_DESIGNATOR = "buckminster.version.designator"; //$NON-NLS-1$

	public static final String COMPONENT_VERSION = "buckminster.version"; //$NON-NLS-1$

	public static final String MUTABLERULE = "buckminster.rule.mutable"; //$NON-NLS-1$

	public static final String SOURCERULE = "buckminster.rule.source"; //$NON-NLS-1$

	public static final String LOGIN_NAME = "buckminster.login"; //$NON-NLS-1$

	public static final String LOGIN_PASSWORD = "buckminster.password"; //$NON-NLS-1$

	public static final String SNAPSHOT = "buckminster.snapshot"; //$NON-NLS-1$

	public static final String OVERRIDE_ECLIPSE_INSTALLED = "buckminster.override.eclipse.installed"; //$NON-NLS-1$

	public static final String COMPONENT_TYPE = "buckminster.component.type"; //$NON-NLS-1$

	public static final String ACTION_OUTPUT_ROOT = "buckminster.output.root"; //$NON-NLS-1$

	public static final String ACTION_TEMP_ROOT = "buckminster.temp.root"; //$NON-NLS-1$

	public static final String ACTION_OUTPUT = "buckminster.output"; //$NON-NLS-1$

	public static final String ACTION_HOME = "buckminster.home"; //$NON-NLS-1$

	public static final String ACTION_TEMP = "buckminster.temp"; //$NON-NLS-1$

	public static final String ACTION_HOME_REF = "${" + ACTION_HOME + "}"; //$NON-NLS-1$ //$NON-NLS-2$

	public static final String ACTION_OUTPUT_REF = "${" + ACTION_OUTPUT + "}"; //$NON-NLS-1$ //$NON-NLS-2$

	public static final String ACTION_TEMPDIR_REF = "${" + ACTION_TEMP + "}"; //$NON-NLS-1$ //$NON-NLS-2$
}
