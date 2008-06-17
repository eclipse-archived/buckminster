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
 * Validates that the input string represents a structured name on the form "namepart[.namepart]+". A namepart must
 * start with a character a-zA-Z$_ and can be followed by an optional sequence of what regexp considers to be a
 * "word character" (i.e. the \\w pattern) or a $.
 * 
 * This validator accepts empty input as valid. See {@link RequiredValidator} if a warning or error is needed on empty
 * input.
 * 
 * @author Henrik Lindberg
 * 
 */
public class StructuredNameValidator extends PatternValidator implements IEditValidator
{
	private static StructuredNameValidator s_instance;

	public static StructuredNameValidator instance()
	{
		if(s_instance == null)
			s_instance = new StructuredNameValidator("^[a-zA-Z_\\$][\\w\\$]*(?:\\.[a-zA-Z_\\$][\\w\\$]*)*$"); //$NON-NLS-1$
		return s_instance;
	}

	private StructuredNameValidator(String pattern)
	{
		super(pattern);
	}
}
