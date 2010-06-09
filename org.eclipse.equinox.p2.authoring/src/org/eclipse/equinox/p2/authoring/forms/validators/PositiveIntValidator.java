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

package org.eclipse.equinox.p2.authoring.forms.validators;

/**
 * Validates that the input is a non negative Integer.
 * This validator accepts empty input as valid. See {@link RequiredValidator} if a warning or error is needed on empty
 * input.
 * 
 * @author Henrik Lindberg
 * 
 */
public class PositiveIntValidator extends PatternValidator implements IEditValidator
{
	private static PositiveIntValidator s_instance;

	public static PositiveIntValidator instance()
	{
		if(s_instance == null)
			s_instance = new PositiveIntValidator("^[0]|([1-9][0-9]*)$"); //$NON-NLS-1$
		return s_instance;
	}

	private PositiveIntValidator(String pattern)
	{
		super(pattern);
	}
}
