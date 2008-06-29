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

import org.eclipse.equinox.p2.authoring.spi.ITouchpointInstructionDescriptor;
import org.eclipse.equinox.p2.authoring.spi.ITouchpointTypeDescriptor;
import org.eclipse.equinox.p2.authoring.spi.TouchpointInstruction;
import org.eclipse.equinox.p2.authoring.spi.TouchpointParameter;

/**
 * Descriptor class for the p2 Eclipse touchpoint version 1.0.0. The description is used by
 * p2 meta data authoring to configure forms, provide validation and lookup.
 * 
 * @author Henrik Lindberg
 *
 */
public final class EclipseTouchpoint_1_0 implements ITouchpointTypeDescriptor
{
	public final static ITouchpointInstructionDescriptor[] s_types = new TouchpointInstruction[] {
			new TouchpointInstruction("installBundle", //$NON-NLS-1$
					"Install Bundle", new TouchpointParameter[] { //
					new TouchpointParameter("bundle", //$NON-NLS-1$
							"Bundle Artifact", "", TYPE_ARTIFACTREF, true) }),

			new TouchpointInstruction("uninstallBundle", //$NON-NLS-1$
					"Uninstall Bundle", new TouchpointParameter[] { //
					new TouchpointParameter("bundle", //$NON-NLS-1$
							"Bundle Artifact", "", TYPE_ARTIFACTREF, true) }),

			new TouchpointInstruction("addSourceBundle", //$NON-NLS-1$
					"Add Source Bundle", new TouchpointParameter[] { //
					new TouchpointParameter("bundle", //$NON-NLS-1$
							"Bundle Artifact", "", TYPE_ARTIFACTREF, true) }),

			new TouchpointInstruction("removeSourceBundle", //$NON-NLS-1$
					"Remove Source Bundle", new TouchpointParameter[] { //
					new TouchpointParameter("bundle", //$NON-NLS-1$
							"Bundle Artifact", "", TYPE_ARTIFACTREF, true) }),

			new TouchpointInstruction("installFeature", //$NON-NLS-1$
					"Install Feature", new TouchpointParameter[] { //
					new TouchpointParameter("feature", //$NON-NLS-1$
							"Feature Artifact", "", TYPE_ARTIFACTREF, true), //
							new TouchpointParameter("featureId", //$NON-NLS-1$
									"Feature ID", "", TYPE_NAME, true), //
							new TouchpointParameter("version", //$NON-NLS-1$
									"Feature Version", "", TYPE_DEFAULT_VERSION, true) }),

			new TouchpointInstruction("uninstallFeature", //$NON-NLS-1$
					"Uninstall Feature", new TouchpointParameter[] { //
					new TouchpointParameter("feature", //$NON-NLS-1$
							"Feature Artifact", "", TYPE_ARTIFACTREF, true), //
							new TouchpointParameter("featureId", //$NON-NLS-1$
									"Feature ID", "", TYPE_NAME, true), //
							new TouchpointParameter("version", //$NON-NLS-1$
									"Feature Version", "", TYPE_DEFAULT_VERSION, true) }),

			new TouchpointInstruction("setLauncherName", //$NON-NLS-1$
					"Launcher Name", new TouchpointParameter[] { //
					new TouchpointParameter("name", //$NON-NLS-1$
							"Name", "", TYPE_STRING, true) }), //

			new TouchpointInstruction("addProgramArgument", //$NON-NLS-1$
					"Add Program Argument", new TouchpointParameter[] { //
					new TouchpointParameter("programArg", //$NON-NLS-1$
							"Argument", "", TYPE_STRING, true) }), //

			new TouchpointInstruction("removeProgramArgument", //$NON-NLS-1$
					"Remove Program Argument", new TouchpointParameter[] { //
					new TouchpointParameter("programArg", //$NON-NLS-1$
							"Argument", "", TYPE_STRING, true) }), //

			new TouchpointInstruction("setStartLevel", //$NON-NLS-1$
					"Start Level", new TouchpointParameter[] { //
					new TouchpointParameter("startLevel", //$NON-NLS-1$
							"Start Level", "", TYPE_MIN0_INT, true) }), //

			new TouchpointInstruction("markStarted", //$NON-NLS-1$
					"Mark Started", new TouchpointParameter[] { //
					new TouchpointParameter("started", //$NON-NLS-1$
							"Started", "true", TYPE_BOOLEAN, true) }), //

			new TouchpointInstruction("setFrameworkDependentProperty", //$NON-NLS-1$
					"Set Framework Dependant Property", new TouchpointParameter[] { //
					new TouchpointParameter("propName", //$NON-NLS-1$
							"Property", "", TYPE_NAME, true), //
							new TouchpointParameter("propValue", //$NON-NLS-1$
									"Value", "", TYPE_STRING, true) }), //

			new TouchpointInstruction("setFrameworkIndependentProperty", //$NON-NLS-1$
					"Set Framework Independant Property", new TouchpointParameter[] { //
					new TouchpointParameter("propName", //$NON-NLS-1$
							"Property", "", TYPE_NAME, true), //
							new TouchpointParameter("propValue", //$NON-NLS-1$
									"Value", "", TYPE_STRING, true) }), //

			new TouchpointInstruction("setProgramProperty", //$NON-NLS-1$
					"Set Program Property", new TouchpointParameter[] { //
					new TouchpointParameter("propName", //$NON-NLS-1$
							"Property", "", TYPE_NAME, true), //
							new TouchpointParameter("propValue", //$NON-NLS-1$
									"Value", "", TYPE_STRING, true) }), //

			new TouchpointInstruction("addJVMArg", //$NON-NLS-1$
					"Add JVM Argument", new TouchpointParameter[] { //
					new TouchpointParameter("jvmArg", //$NON-NLS-1$
							"Argument", "", TYPE_STRING, true) }), //

			new TouchpointInstruction("removeJVMArg", //$NON-NLS-1$
					"Remove JVM Argument", new TouchpointParameter[] { //
					new TouchpointParameter("jvmArg", //$NON-NLS-1$
							"Argument", "", TYPE_STRING, true) }), //

			new TouchpointInstruction("mkdir", //$NON-NLS-1$
					"Create Directory", new TouchpointParameter[] { //
					new TouchpointParameter("path", //$NON-NLS-1$
							"Path", "", TYPE_PATH, true) }), //

			new TouchpointInstruction("rmdir", //$NON-NLS-1$
					"Remove Directory", new TouchpointParameter[] { //
					new TouchpointParameter("path", //$NON-NLS-1$
							"Path", "", TYPE_PATH, true) }), //

			new TouchpointInstruction("link", //$NON-NLS-1$
					"Create Symbolic Link", new TouchpointParameter[] //
					{ new TouchpointParameter("targetDir", //$NON-NLS-1$
							"Create In", "", TYPE_IMPLIED_PATH, true), //
							new TouchpointParameter("linkTarget", //$NON-NLS-1$
									"Source Path", "", TYPE_PATH, true), //
							new TouchpointParameter("linkName", //$NON-NLS-1$
									"Link Name", "", TYPE_FILENAME, true), //
							new TouchpointParameter("force", //$NON-NLS-1$
									"Force", "false", TYPE_BOOLEAN, true), }), //

			new TouchpointInstruction("chmod", //$NON-NLS-1$
					"Change Permissions", new TouchpointParameter[] //
					{ new TouchpointParameter("targetDir", //$NON-NLS-1$
							"Directory", "", TYPE_IMPLIED_PATH, true), //
							new TouchpointParameter("targetFile", //$NON-NLS-1$
									"File Name", "", TYPE_FILENAME, true), //
							new TouchpointParameter("permissions", //$NON-NLS-1$
									"Permissions", "755", TYPE_STRING, true), }), //
									
	// The following two instructions are created by the engine and should not be used when editing.
	// collect ()
	// checkTrust ()
	};

	/**
	 * Returns the Eclipse touchpoint instructions.
	 */
	public final ITouchpointInstructionDescriptor[] getInstructions()
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
	public boolean isNull()
	{
		return false;
	}
	public boolean isUnknown()
	{
		return false;
	}
}
