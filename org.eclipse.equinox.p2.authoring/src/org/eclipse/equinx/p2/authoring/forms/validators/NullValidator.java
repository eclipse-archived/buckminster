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
 * Always validates as true.
 * 
 * @author Henrik Lindberg
 * 
 */
public class NullValidator implements IEditValidator
{
	private static NullValidator s_instance;

	public static NullValidator instance()
	{
		if(s_instance == null)
			s_instance = new NullValidator();
		return s_instance;
	}

	public boolean isValid(String input, EditAdapter editAdapter)
	{
		editAdapter.clearMessages();
		return true;
	}

	public String inputFilter(String input)
	{
		return null;
	}
}
