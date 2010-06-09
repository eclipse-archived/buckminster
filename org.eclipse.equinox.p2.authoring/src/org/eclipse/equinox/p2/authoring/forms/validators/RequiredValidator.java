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

import org.eclipse.equinox.p2.authoring.forms.EditAdapter;

/**
 * Validates that the input string is not empty, can issue a warning or an error. This validator should wrap a validator
 * that validates the input if it is not empty. Use the constructor that takes no arguments if only required value
 * should be validated.
 * 
 * @author Henrik Lindberg
 * 
 */
public class RequiredValidator implements IEditValidator
{
	private final boolean m_emptyIsError;

	private final IEditValidator m_valueValidator;

	/**
	 * Uses a null value validator - i.e. any value is valid. Null or empty input generates a warning or error message
	 * under control of the emptyIsError flag.
	 * 
	 * @param emptyIsError
	 *            true for error message, and false for warning message
	 */
	public RequiredValidator(boolean emptyIsError)
	{
		this(NullValidator.instance(), emptyIsError);
	}

	/**
	 * Treats empty input as error.
	 * 
	 * @param valueValidator
	 */
	public RequiredValidator(IEditValidator valueValidator)
	{
		this(valueValidator, true);
	}

	public RequiredValidator(IEditValidator valueValidator, boolean emptyIsError)
	{
		m_valueValidator = valueValidator;
		m_emptyIsError = emptyIsError;
	}

	public boolean isValid(String input, EditAdapter editAdapter)
	{
		if(input == null || input.length() < 1)
		{
			if(m_emptyIsError)
			{
				editAdapter.setErrorMessage("Required value is empty.");
				return false;
			}
			editAdapter.setWarningMessage("Value is empty");
			return true;
		}
		return m_valueValidator.isValid(input, editAdapter);
	}

	public String inputFilter(String input)
	{
		return m_valueValidator.inputFilter(input);
	}
}
