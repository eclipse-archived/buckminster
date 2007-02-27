/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.common.model;

import java.util.Map;

/**
 * The ToLower class will translate all upper case letters in its source
 * to lower case.
 *
 * @author Thomas Hallgren
 */
public class ToLower extends ValueHolderFilter
{
	public static final String TAG = "toLower";

	@Override
	public String checkedGetValue(Map<String, String> props, int recursionGuard)
	{
		String resolved = this.checkedGetSourceValue(props, recursionGuard);
		return (resolved == null || NO_VALUE.equals(resolved)) ? NO_VALUE : resolved.toLowerCase();
	}

	public String getDefaultTag()
	{
		return TAG;
	}
}

