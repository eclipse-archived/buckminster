/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

/**
 * @author Thomas Hallgren
 */
public abstract class NamedElementBuilder
{
	private String m_name;

	public void clear()
	{
		m_name = null;
	}

	public String getName()
	{
		return m_name;
	}

	public void initFrom(String name)
	{
		clear();
		m_name = name;
	}

	public void setName(String name)
	{
		m_name = name;
	}
}
