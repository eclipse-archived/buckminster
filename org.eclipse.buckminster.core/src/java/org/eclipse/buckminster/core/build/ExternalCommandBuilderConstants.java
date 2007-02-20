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

/**
 * @author kolwing
 *
 */
public interface ExternalCommandBuilderConstants
{
	public static final String LAUNCHERDEFINITIONS_FILE_KEY = "launcherdefinitions.file";
	public static final String LAUNCHERDEFINITION_TO_USE_KEY = "launcherdefinition.to.use";
	public static final String ADDITIONAL_ARGUMENTS_KEY = "additional.arguments";
	
	public static final String DEFAULT_LAUNCHERDEFINITIONS_FILE = ".launcherdefinitions";
	public static final String DEFAULT_LAUNCHERDEFINITION_TO_USE = "${system:OS}";
}
