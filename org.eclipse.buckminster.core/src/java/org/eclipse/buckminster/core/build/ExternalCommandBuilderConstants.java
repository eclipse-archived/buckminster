/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.build;

import org.eclipse.buckminster.core.Messages;

/**
 * @author kolwing
 * 
 */
public interface ExternalCommandBuilderConstants
{
	public static final String LAUNCHERDEFINITIONS_FILE_KEY = "launcherdefinitions.file"; //$NON-NLS-1$

	public static final String LAUNCHERDEFINITION_TO_USE_KEY = "launcherdefinition.to.use"; //$NON-NLS-1$

	public static final String ADDITIONAL_ARGUMENTS_KEY = "additional.arguments"; //$NON-NLS-1$

	public static final String DEFAULT_LAUNCHERDEFINITIONS_FILE = ".launcherdefinitions"; //$NON-NLS-1$

	public static final String DEFAULT_LAUNCHERDEFINITION_TO_USE = "${system:OS}"; //$NON-NLS-1$
}
