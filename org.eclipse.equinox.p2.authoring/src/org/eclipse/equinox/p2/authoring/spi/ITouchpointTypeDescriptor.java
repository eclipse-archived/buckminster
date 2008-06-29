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

package org.eclipse.equinox.p2.authoring.spi;


/**
 * An instance of ITouchpointTypeDesccriptor describes the instructions available for a touchpoint type.
 * The p2 meta data authoring makes use of this description to present options to the user editing
 * an installable unit as well as using the information to set correct labels and perform input validation.
 * 
 * Providers of new touchpoints (or new versions of existing touchpoints) can create new descriptors and
 * extend the p2 meta data authoring framework.
 * 
 * @author Henrik Lindberg
 *
 */
public interface ITouchpointTypeDescriptor extends ITouchpointActionParameterTypes
{
	/** 
	 * @return The ID for the touchpoint type.
	 */
	String getTypeId();
	
	/**
	 * @return The version of the touchpoint type being described in OSGi version String format.
	 */
	String getVersionString();
	
	/**
	 * @return An array of ITouchpointInstructionDescriptor that describes the available instructions.
	 */
	ITouchpointActionDescriptor[] getInstructions();
	
	/**
	 * @return true if this description has the meaning "no touchpoint type"
	 */
	boolean isNull();
	
	/**
	 * @return true if this description has the meaning "touchpoint type unknown to p2 authoring"
	 */
	boolean isUnknown();
}
