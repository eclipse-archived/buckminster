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
 * Describes a parameter to a p2 Touchpoint Type's instruction.
 * @author Henrik Lindberg
 *
 */
public interface ITouchpointActionParameterDescriptor extends ITouchpointActionParameterTypes
{
	
	/**
	 * @return  The parameter key to be used in the installable unit meta data
	 */
	String getKey();
	
	/**
	 * @return A label string for the parameter presented to the user. 
	 */
	String getLabel();
	
	/**
	 * @return True if the parameter is required
	 */
	boolean isRequired();
	
	/**
	 * Return the type of the parameter as described by the various TYPE_XXX static Strings 
	 * in {@link ITouchpointActionParameterTypes}. This is
	 * used by the p2 authoring to validate the input, and to provide lookup.
	 * 
	 * @return a TYPE_XXX String as described in this interface
	 */
	String getType();
	
	/**
	 * Return a suitable default value for the parameter. This is used when a new instance of an instruction is
	 * added to the set of instructions in the p2 meta data authoring.
	 * @return A suitable default value for the parameter.
	 */
	String getDefaultValue();
	
}
