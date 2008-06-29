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

package org.eclipse.equinox.p2.authoring.spi;

/**
 * Default (abstract) implementation of the {@link ITouchpointTypeDescriptor} interface.
 * @author Henrik Lindberg
 *
 */
public abstract class TouchpointTypeDescriptor implements ITouchpointTypeDescriptor
{
	private static final String[] s_defaultPhases = { "install", "uninstall", "configure", "unconfigure" };
	
	/** Returns a default array of phases "install, uninstall, configure, unconfigure"
	 * @see org.eclipse.equinox.p2.authoring.spi.ITouchpointTypeDescriptor#getPhases()
	 */
	public String[] getPhases()
	{
		return s_defaultPhases;
	}


	/** Returns false.
	 * @see org.eclipse.equinox.p2.authoring.spi.ITouchpointTypeDescriptor#isNull()
	 */
	public boolean isNull()
	{
		return false;
	}

	/** 
	 * Returns false
	 * @see org.eclipse.equinox.p2.authoring.spi.ITouchpointTypeDescriptor#isUnknown()
	 */
	public boolean isUnknown()
	{
		return false;
	}

}
