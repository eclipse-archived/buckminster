/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.cspec.builder;

import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.core.runtime.IPath;

public class MissingPathException extends LocalizedException
{
	private static final long serialVersionUID = 3228454630316889796L;

	public MissingPathException(String name, String attribute, IPath path)
	{
		super("CSpec {0}, attribute {1} does not define path {2}", name, attribute, path);
	}
}
