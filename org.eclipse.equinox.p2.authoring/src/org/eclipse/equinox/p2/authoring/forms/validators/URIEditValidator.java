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

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.equinox.p2.authoring.forms.EditAdapter;

/**
 * Validates that the input string can be transformed into a URI. This is done by 1) making sure a URI can be
 * constructed from the input string, and that the resulting URI has a scheme. Further testing is not possible without
 * understanding the semantics of the URI scheme.
 * 
 * Note that empty input is considered valid. Use {@link RequiredValidator} if value is required.
 * 
 * @author Henrik Lindberg
 * 
 */
public class URIEditValidator implements IEditValidator
{
	private static URIEditValidator s_instance;

	static public URIEditValidator instance()
	{
		if(s_instance == null)
			s_instance = new URIEditValidator();
		return s_instance;
	}

	public boolean isValid(String input, EditAdapter editAdapter)
	{
		if(input != null && input.length() > 0)
		{
			URI uri;
			try
			{
				uri = new URI(input);
				if(uri.getScheme() == null)
				{
					editAdapter.setErrorMessage("Missing scheme");
					return false;
				}
			}
			catch(URISyntaxException e)
			{
				editAdapter.setErrorMessage(e.getMessage());
				return false;
			}
		}
		editAdapter.clearMessages();
		return true;
	}

	public String inputFilter(String input)
	{
		return null;
	}

}
