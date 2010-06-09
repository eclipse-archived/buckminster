/*******************************************************************************
 * Copyright (c) 2008
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed below, as Initial Contributors under such license.
 * The text of such license is available at 
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 * 		Henrik Lindberg
 *******************************************************************************/

package org.eclipse.equinox.p2.authoring.internal.touchpoints;

import org.eclipse.equinox.p2.authoring.spi.ITouchpointActionDescriptor;
import org.eclipse.equinox.p2.authoring.spi.ITouchpointTypeDescriptor;
import org.eclipse.equinox.p2.authoring.spi.TouchpointActionDescriptor;
import org.eclipse.equinox.p2.authoring.spi.TouchpointActionParameterDescriptor;
import org.eclipse.equinox.p2.authoring.spi.TouchpointTypeDescriptor;

/**
 * Descriptor class for the p2 Eclipse touchpoint version 1.0.0. The description is used by
 * p2 meta data authoring to configure forms, provide validation and lookup.
 * 
 * @author Henrik Lindberg
 *
 */
public final class EclipseTouchpoint_1_0 extends TouchpointTypeDescriptor implements ITouchpointTypeDescriptor
{
	public final static ITouchpointActionDescriptor[] s_types = new TouchpointActionDescriptor[] {
			new TouchpointActionDescriptor("installBundle", //$NON-NLS-1$
					"Install Bundle", new TouchpointActionParameterDescriptor[] { //
					new TouchpointActionParameterDescriptor("bundle", //$NON-NLS-1$
							"Bundle Artifact", "", TYPE_ARTIFACTREF, true) }),

			new TouchpointActionDescriptor("uninstallBundle", //$NON-NLS-1$
					"Uninstall Bundle", new TouchpointActionParameterDescriptor[] { //
					new TouchpointActionParameterDescriptor("bundle", //$NON-NLS-1$
							"Bundle Artifact", "", TYPE_ARTIFACTREF, true) }),

			new TouchpointActionDescriptor("addSourceBundle", //$NON-NLS-1$
					"Add Source Bundle", new TouchpointActionParameterDescriptor[] { //
					new TouchpointActionParameterDescriptor("bundle", //$NON-NLS-1$
							"Bundle Artifact", "", TYPE_ARTIFACTREF, true) }),

			new TouchpointActionDescriptor("removeSourceBundle", //$NON-NLS-1$
					"Remove Source Bundle", new TouchpointActionParameterDescriptor[] { //
					new TouchpointActionParameterDescriptor("bundle", //$NON-NLS-1$
							"Bundle Artifact", "", TYPE_ARTIFACTREF, true) }),

			new TouchpointActionDescriptor("installFeature", //$NON-NLS-1$
					"Install Feature", new TouchpointActionParameterDescriptor[] { //
					new TouchpointActionParameterDescriptor("feature", //$NON-NLS-1$
							"Feature Artifact", "", TYPE_ARTIFACTREF, true), //
							new TouchpointActionParameterDescriptor("featureId", //$NON-NLS-1$
									"Feature ID", "", TYPE_NAME, true), //
							new TouchpointActionParameterDescriptor("version", //$NON-NLS-1$
									"Feature Version", "", TYPE_DEFAULT_VERSION, true) }),

			new TouchpointActionDescriptor("uninstallFeature", //$NON-NLS-1$
					"Uninstall Feature", new TouchpointActionParameterDescriptor[] { //
					new TouchpointActionParameterDescriptor("feature", //$NON-NLS-1$
							"Feature Artifact", "", TYPE_ARTIFACTREF, true), //
							new TouchpointActionParameterDescriptor("featureId", //$NON-NLS-1$
									"Feature ID", "", TYPE_NAME, true), //
							new TouchpointActionParameterDescriptor("version", //$NON-NLS-1$
									"Feature Version", "", TYPE_DEFAULT_VERSION, true) }),

			new TouchpointActionDescriptor("setLauncherName", //$NON-NLS-1$
					"Launcher Name", new TouchpointActionParameterDescriptor[] { //
					new TouchpointActionParameterDescriptor("name", //$NON-NLS-1$
							"Name", "", TYPE_STRING, true) }), //

			new TouchpointActionDescriptor("addProgramArgument", //$NON-NLS-1$
					"Add Program Argument", new TouchpointActionParameterDescriptor[] { //
					new TouchpointActionParameterDescriptor("programArg", //$NON-NLS-1$
							"Argument", "", TYPE_STRING, true) }), //

			new TouchpointActionDescriptor("removeProgramArgument", //$NON-NLS-1$
					"Remove Program Argument", new TouchpointActionParameterDescriptor[] { //
					new TouchpointActionParameterDescriptor("programArg", //$NON-NLS-1$
							"Argument", "", TYPE_STRING, true) }), //

			new TouchpointActionDescriptor("setStartLevel", //$NON-NLS-1$
					"Start Level", new TouchpointActionParameterDescriptor[] { //
					new TouchpointActionParameterDescriptor("startLevel", //$NON-NLS-1$
							"Start Level", "", TYPE_MIN0_INT, true) }), //

			new TouchpointActionDescriptor("markStarted", //$NON-NLS-1$
					"Mark Started", new TouchpointActionParameterDescriptor[] { //
					new TouchpointActionParameterDescriptor("started", //$NON-NLS-1$
							"Started", "true", TYPE_BOOLEAN, true) }), //

			new TouchpointActionDescriptor("setFrameworkDependentProperty", //$NON-NLS-1$
					"Set Framework Dependant Property", new TouchpointActionParameterDescriptor[] { //
					new TouchpointActionParameterDescriptor("propName", //$NON-NLS-1$
							"Property", "", TYPE_NAME, true), //
							new TouchpointActionParameterDescriptor("propValue", //$NON-NLS-1$
									"Value", "", TYPE_STRING, true) }), //

			new TouchpointActionDescriptor("setFrameworkIndependentProperty", //$NON-NLS-1$
					"Set Framework Independant Property", new TouchpointActionParameterDescriptor[] { //
					new TouchpointActionParameterDescriptor("propName", //$NON-NLS-1$
							"Property", "", TYPE_NAME, true), //
							new TouchpointActionParameterDescriptor("propValue", //$NON-NLS-1$
									"Value", "", TYPE_STRING, true) }), //

			new TouchpointActionDescriptor("setProgramProperty", //$NON-NLS-1$
					"Set Program Property", new TouchpointActionParameterDescriptor[] { //
					new TouchpointActionParameterDescriptor("propName", //$NON-NLS-1$
							"Property", "", TYPE_NAME, true), //
							new TouchpointActionParameterDescriptor("propValue", //$NON-NLS-1$
									"Value", "", TYPE_STRING, true) }), //

			new TouchpointActionDescriptor("addJVMArg", //$NON-NLS-1$
					"Add JVM Argument", new TouchpointActionParameterDescriptor[] { //
					new TouchpointActionParameterDescriptor("jvmArg", //$NON-NLS-1$
							"Argument", "", TYPE_STRING, true) }), //

			new TouchpointActionDescriptor("removeJVMArg", //$NON-NLS-1$
					"Remove JVM Argument", new TouchpointActionParameterDescriptor[] { //
					new TouchpointActionParameterDescriptor("jvmArg", //$NON-NLS-1$
							"Argument", "", TYPE_STRING, true) }), //

			new TouchpointActionDescriptor("mkdir", //$NON-NLS-1$
					"Create Directory", new TouchpointActionParameterDescriptor[] { //
					new TouchpointActionParameterDescriptor("path", //$NON-NLS-1$
							"Path", "", TYPE_PATH, true) }), //

			new TouchpointActionDescriptor("rmdir", //$NON-NLS-1$
					"Remove Directory", new TouchpointActionParameterDescriptor[] { //
					new TouchpointActionParameterDescriptor("path", //$NON-NLS-1$
							"Path", "", TYPE_PATH, true) }), //

			new TouchpointActionDescriptor("link", //$NON-NLS-1$
					"Create Symbolic Link", new TouchpointActionParameterDescriptor[] //
					{ new TouchpointActionParameterDescriptor("targetDir", //$NON-NLS-1$
							"Create In", "", TYPE_IMPLIED_PATH, true), //
							new TouchpointActionParameterDescriptor("linkTarget", //$NON-NLS-1$
									"Source Path", "", TYPE_PATH, true), //
							new TouchpointActionParameterDescriptor("linkName", //$NON-NLS-1$
									"Link Name", "", TYPE_FILENAME, true), //
							new TouchpointActionParameterDescriptor("force", //$NON-NLS-1$
									"Force", "false", TYPE_BOOLEAN, true), }), //

			new TouchpointActionDescriptor("chmod", //$NON-NLS-1$
					"Change Permissions", new TouchpointActionParameterDescriptor[] //
					{ new TouchpointActionParameterDescriptor("targetDir", //$NON-NLS-1$
							"Directory", "", TYPE_IMPLIED_PATH, true), //
							new TouchpointActionParameterDescriptor("targetFile", //$NON-NLS-1$
									"File Name", "", TYPE_FILENAME, true), //
							new TouchpointActionParameterDescriptor("permissions", //$NON-NLS-1$
									"Permissions", "755", TYPE_STRING, true), }), //
									
	// The following two instructions are created by the engine and should not be used when editing.
	// collect ()
	// checkTrust ()
	};

	/**
	 * Returns the Eclipse touchpoint instructions.
	 */
	public final ITouchpointActionDescriptor[] getActions()
	{
		return s_types;
	}

	/**
	 * Returns the Eclipse touchpoint id string.
	 */
	public final String getTypeId()
	{
		return "org.eclipse.equinox.p2.osgi";
	}

	/**
	 * Returns the version of this touchpoint (1.0.0).
	 */
	public final String getVersionString()
	{
		return "1.0.0";
	}
}
