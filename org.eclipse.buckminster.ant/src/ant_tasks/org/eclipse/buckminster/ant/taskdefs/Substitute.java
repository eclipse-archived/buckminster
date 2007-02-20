/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.ant.taskdefs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * Performs substitutions on a given value and assings the result to
 * a named property.
 * @author Thomas Hallgren
 */
public class Substitute extends Task
{
	private String m_property;
	private String m_pattern;
	private String m_replacement;
	private String m_value;
	private boolean m_quotePattern;

	@Override
	public void execute()
	throws BuildException
	{
		if(m_property == null)
			throw new BuildException("\"property\" must be set");

		if(m_pattern == null)
			throw new BuildException("\"pattern\" must be set");

		if(m_replacement == null)
			throw new BuildException("\"replacement\" must be set");

		if(m_value == null)
			throw new BuildException("\"value\" must be set");

		try
		{
			String tmp = m_pattern;
			if(m_quotePattern)
				tmp = Pattern.quote(tmp);

			Pattern pattern = Pattern.compile(tmp);
			Matcher matcher = pattern.matcher(m_value);

			if(matcher.find())
			{
				StringBuffer sb = new StringBuffer();
				do
				{
					matcher.appendReplacement(sb, m_replacement);
				} while(matcher.find());
				matcher.appendTail(sb);
				tmp = sb.toString();
			}
			else
				tmp = m_value;
			
			this.getProject().setProperty(m_property, tmp);
		}
		catch(PatternSyntaxException e)
		{
			throw new BuildException(e.getMessage());
		}
	}

	/**
	 * Sets the regular expression pattern to use as the match for the
	 * substitution.
	 * @param pattern A regexp pattern, typcially containing groups that
	 * will be referenced by the replacement string.
	 */
	public void setPattern(String pattern)
	{
		m_pattern = pattern;
	}

	/**
	 * Sets the name of the property that will receive the substituted value.
	 * @param property A property name.
	 */
	public void setProperty(String property)
	{
		m_property = property;
	}

	/**
	 * Set to <code>true</code> if the pattern should be quoted prior to the regular
	 * expression parsing. The default is <code>false</code>.
	 * @param flag
	 */
	public void setQuotePattern(boolean flag)
	{
		m_quotePattern = flag;
	}

	/**
	 * Sets the replacement string to use in the substitution. <code>$1</code> denotes
	 * the first group of the regular expression, <code>$2</code> the second, and so on.
	 * @param replacement
	 */
	public void setReplacement(String replacement)
	{
		m_replacement = replacement;
	}

	/**
	 * Sets the source of the substitution.
	 * @param value
	 */
	public void setValue(String value)
	{
		m_value = value;
	}
}
