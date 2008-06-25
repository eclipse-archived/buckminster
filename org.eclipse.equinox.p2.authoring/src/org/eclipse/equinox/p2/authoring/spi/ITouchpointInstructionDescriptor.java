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
 * An instance of ITouchpointInstructionDescriptor describes one instruction understood by a particular
 * version of a touchpoint type (as described by an instance of {@link ITouchpointTypeDescriptor}.
 *  
 * @author Henrik Lindberg
 *
 */
public interface ITouchpointInstructionDescriptor
{
	/**
	 * @return The instruction key as it appears in IU meta data
	 */
	String getKey();
	
	/**
	 * @return The human readable label for the instruction
	 */
	String getLabel();
	
	/**
	 * @return array of parameter descriptors
	 */
	ITouchpointInstructionParameterDescriptor[] getParameters();

}
