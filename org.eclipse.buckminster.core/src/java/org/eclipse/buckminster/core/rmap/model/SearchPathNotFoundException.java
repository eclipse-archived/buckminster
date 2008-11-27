/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.rmap.model;

import org.eclipse.buckminster.core.helpers.LocalizedException;

/**
 * @author Thomas Hallgren
 */
public class SearchPathNotFoundException extends LocalizedException
{
	private static final long serialVersionUID = 6638732630104642555L;

	public SearchPathNotFoundException(String name)
	{
		super("Unable to find a searchPath for %s", name);
	}
}
