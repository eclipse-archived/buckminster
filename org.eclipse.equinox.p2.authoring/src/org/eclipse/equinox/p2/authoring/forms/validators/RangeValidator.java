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
import org.eclipse.osgi.service.resolver.VersionRange;

/**
 * Validates that the input string can represent an instance of {@link VersionRange}
 * 
 * This validator accepts empty input as valid. See {@link RequiredValidator} if a warning or error is needed on empty
 * input.
 * 
 * @author Henrik Lindberg
 * 
 */
public class RangeValidator implements IEditValidator
{
	private static RangeValidator s_instance;

	public static RangeValidator instance()
	{
		if(s_instance == null)
			s_instance = new RangeValidator();
		return s_instance;
	}

	public boolean isValid(String input, EditAdapter editAdapter)
	{
		if(input != null && input.trim().length() > 0)
		{
			try
			{
				new VersionRange(input);
			}
			catch(IllegalArgumentException e)
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
