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
 * Validates that the input string can be transformed into an email address. The verification is very simple - it only
 * checks that the email is on the form x@x.x. The RFC for email addresses is quite complex (example
 * "joe.blow(Joe Mc Blow)@[123.345.78.90]" ) so this validator is just there to help the user avoid obvious mistakes,
 * not to ensure that the email address format is 100% valid.
 * 
 * This validator accepts empty input as valid. See {@link RequiredValidator} if a warning or error is needed on empty
 * input.
 * 
 * @author Henrik Lindberg
 * 
 */
public class EmailEditValidator implements IEditValidator
{
	public boolean isValid(String input, EditAdapter editAdapter)
	{
		if(input != null && input.length() > 0)
		{
			// if there is input, it must be a valid email address
			// check that it is at least x@y.x
			//
			int atIndex = input.indexOf("@"); //$NON-NLS-1$
			int dotIndex = input.lastIndexOf("."); //$NON-NLS-1$
			if(atIndex < 1 || dotIndex < atIndex || dotIndex + 1 >= input.length())
			{
				editAdapter.setErrorMessage("Not a valid email address");
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
