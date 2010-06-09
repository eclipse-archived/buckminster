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

import org.eclipse.equinox.internal.p2.metadata.InstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.Copyright;
import org.eclipse.equinox.internal.provisional.p2.metadata.License;
import org.osgi.framework.Version;

/**
 * P2 Metadata Factory used to centralize creation of P2 metadata as API is likely to change.
 * 
 * @author Henrik Lindberg
 * 
 */
@SuppressWarnings("restriction")
public class P2MetadataFactory implements P2MetadataConstants
{
	public static InstallableUnit createDefaultInstallableUnit(String name)
	{
		// Build an IU
		InstallableUnit iu = new InstallableUnit();

		// set up IU with some defaults
		iu.setProperty(InstallableUnit.PROP_NAME, name); // human readable name
		iu.setProperty(InstallableUnit.NAMESPACE_FLAVOR, "org.eclipse.equinox.p2.iu"); //$NON-NLS-1$
		iu.setId(name);
		iu.setCopyright(new Copyright("", "TODO: edit generated text - " + DEFAULT_IU_COPYRIGHT_TEMPLATE)); //$NON-NLS-1$
		iu.setLicense(new License("", "TODO: edit generated text - " + DEFAULT_IU_LICENSE_TEMPLATE)); //$NON-NLS-1$
		iu.setVersion(new Version(DEFAULT_IU_VERSION_STRING));
		iu.setFilter(""); //$NON-NLS-1$
		return iu;
	}
}
