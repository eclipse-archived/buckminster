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

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

import org.eclipse.equinox.p2.authoring.forms.EditAdapter;

/**
 * Validates that the input string can represent an instance of an LDAP filter as described in RFC 2254, but without
 * LDAP extensions, or OCTET STRING
 * 
 * This validator accepts empty input as valid. See {@link RequiredValidator} if a warning or error is needed on empty
 * input.
 * 
 * @author Henrik Lindberg
 * 
 */
public class LDAPFilterValidator implements IEditValidator
{
	private static LDAPFilterValidator s_instance;

	public static LDAPFilterValidator instance()
	{
		if(s_instance == null)
			s_instance = new LDAPFilterValidator();
		return s_instance;
	}

	public boolean isValid(String input, EditAdapter editAdapter)
	{
		if(input != null && input.length() > 0)
		{
			try
			{
				parse(input);
			}
			catch(IllegalArgumentException e)
			{
				editAdapter.setErrorMessage("Invalid format");
				return false;
			}
			catch(ParseException e)
			{
				editAdapter.setErrorMessage("Invalid format: " + e.message + " : at position "
						+ Integer.toString(e.index));
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

	private void parse(String input) throws ParseException
	{
		StringCharacterIterator citor = new StringCharacterIterator(input);
		char c = skipWhitespace(citor);
		if(c == CharacterIterator.DONE)
			return; // empty input is ok

		expectFilter(citor);
		c = skipWhitespace(citor);
		if(c != CharacterIterator.DONE)
			throw new ParseException("Trailing '"+ safeCharToString(c)+"' found after valid expression", citor.getIndex());
	}

	public void expectFilter(StringCharacterIterator citor) throws ParseException
	{
		char c = skipWhitespace(citor);
		if(c != '(')
			throw new ParseException("Filter must be enclosed in parentheses", citor.getIndex());
		else
			c = citor.next();
		switch(c)
		{
		case StringCharacterIterator.DONE:
			throw new ParseException("Reached end while expecting a filter specification", -1);
		case '&':
			citor.next();
			expectFilterList(citor);
			break;
		case '|':
			citor.next();
			expectFilterList(citor);
			break;
		case '!':
			citor.next();
			expectFilter(citor);
			break;
		default:
			expectItem(citor);
			break;
		}

		// matching RPAR at end
		c = skipWhitespace(citor);
		if(c != ')')
			throw new ParseException("Expected ')' - got " + safeCharToString(c), citor.getIndex());
		citor.next(); // consume the ')'
	}

	private String safeCharToString(char c)
	{
		if(c == StringCharacterIterator.DONE)
			return "END";
		return Character.toString(c);
	}

	public void expectFilterList(StringCharacterIterator citor) throws ParseException
	{
		// Expects at least 1 filter
		char c = StringCharacterIterator.DONE;
		do
		{
			expectFilter(citor);
			c = skipWhitespace(citor);
		} while(c == '(');
	}

	public void expectItem(StringCharacterIterator citor) throws ParseException
	{
		expectName(citor);
		expectOperator(citor);
		expectValue(citor);
	}

	public void expectName(StringCharacterIterator citor) throws ParseException
	{
		// allow sequence of a-zA-Z0-9-.
		char c = citor.current();
		do
		{
			if(!isLetter(c))
				throw new ParseException("attribute name must start with a letter", citor.getIndex());
			do
			{
				c = citor.next();
			} while(isWordChar(c));
			if(c != '.')
				break;
			c = citor.next();
		} while(true);

	}

	/**
	 * Allows a-z and A-Z
	 * 
	 * @param c
	 * @return
	 */
	private boolean isLetter(char c)
	{
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}

	/**
	 * Allows 0-9
	 * 
	 * @param c
	 * @return
	 */
	private boolean isDigit(char c)
	{
		return c >= '0' && c <= '9';
	}

	private boolean isHexDigit(char c)
	{
		return isDigit(c) || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
	}

	/**
	 * Allows Letter, Digit or Hyphen
	 * 
	 * @param c
	 * @return
	 */
	private boolean isWordChar(char c)
	{
		return isLetter(c) || isDigit(c) || c == '-';
	}

	public void expectOperator(StringCharacterIterator citor) throws ParseException
	{
		switch(citor.current())
		{
		case '=':
			citor.next();
			return;
		case '<':
			citor.next();
			break;
		case '>':
			citor.next();
			break;
		case '¬':
			if(citor.next() != '=')
				throw new ParseException("Expected '=' after '~'", citor.getIndex());
			return;
		default:
			throw new ParseException("Expression does not have a valid operator", citor.getIndex());
		}
		// end up here for < and > which can be followed by "=", but does not have to
		if(citor.current() != '=')
			return;
		// skip the '='
		citor.next();
		return;
	}

	public void expectValue(StringCharacterIterator citor) throws ParseException
	{
		char c = citor.current();
		int length = 0;
		boolean lastWasAsterix = false;
		for(;;)
		{
			switch(c)
			{
			case StringCharacterIterator.DONE:
			case ')':
			case '(':
				// not allowed in value - terminators
				if(length < 1)
					throw new ParseException("value in expression can not be empty", citor.getIndex());
				return;
			case '*':
				if(lastWasAsterix)
					throw new ParseException("two adjacent '*' not allowed", citor.getIndex());
				lastWasAsterix = true;
				c = citor.next();
				length++;
				break;
			case 0x5c: // backslash
				if(!(isHexDigit(citor.next()) && isHexDigit(citor.next())))
					throw new ParseException("'\' must be followed by two hex digits", citor.getIndex());
				lastWasAsterix = false;
				c = citor.next();
				length++;
				break;
			default:
				// terminate on whitespace - perhaps too lenient
				if(Character.isWhitespace(c))
				{
					if(length < 1)
						throw new ParseException("value in expression can not be empty", citor.getIndex());
					else
						return;
				}
				length++;
				c = citor.next();
				lastWasAsterix = false;
				break;
			}
		}
	}

	/**
	 * Returns the first non whitespace char starting with 'current position'
	 * 
	 * @param citor
	 * @return
	 */
	private char skipWhitespace(StringCharacterIterator citor)
	{
		char c = CharacterIterator.DONE;
		for(c = citor.current(); c != CharacterIterator.DONE && Character.isWhitespace(c); c = citor.next())
			;
		return c;
	}

	private static class ParseException extends Exception
	{
		private static final long serialVersionUID = 1L;

		public String message;

		public int index;

		public ParseException(String message, int index)
		{
			this.message = message;
			this.index = index;
		}
	}
}
