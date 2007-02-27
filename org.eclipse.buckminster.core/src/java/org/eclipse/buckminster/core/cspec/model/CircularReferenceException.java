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
public class CircularReferenceException extends LocalizedException
{
	private final String m_componentName;
	private final String m_attributeChain;

	public CircularReferenceException(String componentName, List<String> attributeNames, String recursionStart)
	{
		super("Component {0} has an internal circular attribute reference. Attribute chain is {1}");
		m_componentName = componentName;
		StringBuilder bld = new StringBuilder();
		for(String attributeName : attributeNames)
		{
			bld.append(attributeName);
			bld.append(" -> ");
		}
		bld.append(recursionStart);
		m_attributeChain = bld.toString();
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return new String[] { m_componentName, m_attributeChain };
	}
}
