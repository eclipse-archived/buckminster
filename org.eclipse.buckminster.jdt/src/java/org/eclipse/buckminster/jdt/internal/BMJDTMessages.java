/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.jdt.internal;

import org.eclipse.osgi.util.NLS;

public class BMJDTMessages extends NLS
{
	private static final String BUNDLE_NAME = "org.eclipse.buckminster.jdt.internal.bmjdt";//$NON-NLS-1$

	public static String BMClasspathContainer_description;

	static
	{
		NLS.initializeMessages(BUNDLE_NAME, BMJDTMessages.class);
	}
}
