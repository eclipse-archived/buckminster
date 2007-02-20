/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.util.List;

import org.eclipse.buckminster.core.helpers.LocalizedException;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("serial")
public class CircularDependencyException extends LocalizedException
{
	private final String[] m_componentChain;

	public CircularDependencyException(List<String> componentNames)
	{
		super("Circular component dependency detected. Chain is {0}");
		StringBuilder bld = new StringBuilder();
		bld.append(componentNames.get(0));
		int top = componentNames.size();
		for(int idx = 1; idx < top; ++idx)
		{
			bld.append(" -> ");
			bld.append(componentNames.get(idx));
		}
		m_componentChain = new String[] { bld.toString() };
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return m_componentChain;
	}
}
