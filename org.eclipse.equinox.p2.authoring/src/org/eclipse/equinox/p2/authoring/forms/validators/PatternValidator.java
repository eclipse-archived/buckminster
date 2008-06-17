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

import java.util.regex.Pattern;

import org.eclipse.equinox.p2.authoring.forms.EditAdapter;

/**
 * Validates that the input string matches a pattern
 * 
 * This validator accepts empty input as valid. See {@link RequiredValidator} if a warning or error is needed on empty
 * input.
 * 
 * @author Henrik Lindberg
 * 
 */
public class PatternValidator implements IEditValidator
{
	private Pattern m_pattern;

	public PatternValidator(String pattern)
	{
		m_pattern = Pattern.compile(pattern);
	}

	public boolean isValid(String input, EditAdapter editAdapter)
	{
		if(input != null && input.length() > 0)
		{
			if(!m_pattern.matcher(input).matches())
			{
				editAdapter.setErrorMessage("Invalid format");
				return false;
			}
		}
		editAdapter.clearMessages();
		return true;
	}

	public String inputFilter(String inputString)
	{
		return null;
	}
}
