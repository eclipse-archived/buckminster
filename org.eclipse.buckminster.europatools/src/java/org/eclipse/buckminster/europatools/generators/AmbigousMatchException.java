/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.europatools.generators;

import org.eclipse.buckminster.core.helpers.LocalizedException;

/**
 * @author Thomas Hallgren
 *
 */
public class AmbigousMatchException extends LocalizedException
{
	private static final long serialVersionUID = 6987427767432037138L;
	private final String[] m_args;

	public AmbigousMatchException(String pattern, String featureName, String contributionName)
	{
		super("Pattern {0} is resource map locator or redirect matches feature {1} in contribution {2} although it doesn't belong to that contirbution");
		m_args = new String[] { pattern, featureName, contributionName };
		assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return m_args;
	}
}
