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

package org.eclipse.equinx.p2.authoring.forms.validators;

import org.eclipse.equinox.p2.authoring.forms.EditAdapter;

/**
 * Describes an edit validator used with eclipse ui forms.
 * 
 * @author Henrik Lindberg
 * 
 */
public interface IEditValidator
{
	/**
	 * This method should check the validity of the input String and update the edit adapter with relevant error/warning
	 * message, or if the input is valid, clear any previously set message.
	 * 
	 * @param input
	 *            - the input string
	 * @param editAdapter
	 *            - the edit adapter using this validator
	 * @return true if the input is valid, false if it is not.
	 */
	boolean isValid(String input, EditAdapter editAdapter);

	/**
	 * This method should return a new filtered String where illegal input characters are filtered out. Returning null
	 * means that there is no need to filter the input (this is more efficient than returning the input string).
	 * 
	 * @param input
	 * @return null if no filtering is required, or a new String with illegal characters filtered out.
	 */
	String inputFilter(String input);
}
