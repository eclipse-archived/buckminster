/*******************************************************************************
 * Copyright (c) 2008
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed below, as Initial Contributors under such license.
 * The text of such license is available at 
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 * 		Henrik Lindberg
 *******************************************************************************/

package org.eclipse.equinox.p2.authoring.internal;

/**
 * @author Henrik Lindberg
 * 
 */
public interface P2MetadataConstants
{

	static final String IU_METADATA_FORMAT = "InstallableUnit"; //$NON-NLS-1$

	static final String INSTALLABLE_ELEMENT = "installable"; //$NON-NLS-1$

	static final String INSTALLABLE_VERSION = "1.0.0"; //$NON-NLS-1$

	static final String IU_METADATA_FORMAT_VERSION = "1.0.0"; //$NON-NLS-1$

	static final String DEFAULT_IU_VERSION_STRING = "1.0.0"; //$NON-NLS-1$

	static final String DEFAULT_IU_LICENSE_TEMPLATE = "The code, documentation and other materials contained herein have been"
			+ " licensed under the NAMEOFLICENSE - VERSION by the copyright holder(s)"
			+ " listed in the Copyright section.";

	static final String DEFAULT_IU_COPYRIGHT_TEMPLATE = "Annotation to copyright URL, or the actual copyright text.";

}
