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
 * Descriptor class for the p2 Native touchpoint version 1.0.0. The description is used by
 * p2 meta data authoring to configure forms, provide validation and lookup.
 * 
 * @author Henrik Lindberg
 *
 */
public final class NativeTouchpoint_1_0 implements ITouchpointTypeDescriptor
{
	public static ITouchpointInstructionDescriptor[] s_types = new TouchpointInstruction[] {
		
			new TouchpointInstruction("cleanupzip", //$NON-NLS-1$
					"Cleanup Zip", new TouchpointParameter[] { //
					new TouchpointParameter("source", //$NON-NLS-1$
							"Source Path", "", TYPE_PATH, true), //
							new TouchpointParameter("target", //$NON-NLS-1$
									"Target Path", "", TYPE_PATH, true) }), //

			new TouchpointInstruction("unzip", //$NON-NLS-1$
					"Cleanup Zip", new TouchpointParameter[] { //
					new TouchpointParameter("source", //$NON-NLS-1$
							"Source Path", "", TYPE_PATH, true), //
							new TouchpointParameter("target", //$NON-NLS-1$
									"Target Path", "", TYPE_PATH, true) }),

			new TouchpointInstruction("link", //$NON-NLS-1$
					"Create Symbolic Link", new TouchpointParameter[] //
					{ new TouchpointParameter("targetDir", //$NON-NLS-1$
							"Create In", "", TYPE_IMPLIED_PATH, true), //
							new TouchpointParameter("linkTarget", //$NON-NLS-1$
									"Source Path", "", TYPE_PATH, true), //
							new TouchpointParameter("linkName", //$NON-NLS-1$
									"Link Name", "", TYPE_FILENAME, true), //
							new TouchpointParameter("force", //$NON-NLS-1$
									"Force", "false", TYPE_BOOLEAN, true), }), // //$NON-NLS-2$

			new TouchpointInstruction("chmod", //$NON-NLS-1$
					"Change Permissions", new TouchpointParameter[] //
					{ new TouchpointParameter("targetDir", //$NON-NLS-1$
							"Directory", "", TYPE_PATH, true), //
							new TouchpointParameter("targetFile", //$NON-NLS-1$
									"File Name", "", TYPE_FILENAME, true), //
							new TouchpointParameter("permissions", //$NON-NLS-1$
									"Permissions", "755", TYPE_STRING, true), }), //

	// The following instruction is created by the engine and should not be used when editing.
	// collect ()
	};

	/**
	 * Returns the instructions for the native touchpoint version 1.0.0
	 */
	public ITouchpointInstructionDescriptor[] getInstructions()
	{
		return s_types;
	}

	/**
	 * Returns "org.eclipse.equinox.p2.native" - the id string for the native touchpoint.
	 */
	public String getTypeId()
	{
		return "org.eclipse.equinox.p2.native"; //$NON-NLS-1$
	}

	/**
	 * Returns "1.0.0".
	 */
	public String getVersionString()
	{
		return "1.0.0"; //$NON-NLS-1$
	}

}
