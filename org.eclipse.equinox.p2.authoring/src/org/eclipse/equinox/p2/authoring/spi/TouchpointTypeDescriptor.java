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

import java.util.HashMap;
import java.util.Map;

/**
 * Default (abstract) implementation of the {@link ITouchpointTypeDescriptor} interface.
 * @author Henrik Lindberg
 *
 */
public abstract class TouchpointTypeDescriptor implements ITouchpointTypeDescriptor
{
	private static final String[] s_defaultPhases = { "install", "uninstall", "configure", "unconfigure" };
	private Map<String, ITouchpointActionDescriptor> m_actions;
	
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
	
	/**
	 * This default implementation of {@link ITouchpointTypeDescriptor#getActionDescriptor(String)} 
	 * looks up the actionName parameter in a map constructed from {@link ITouchpointTypeDescriptor#getActions()}.
	 */
	public ITouchpointActionDescriptor getActionDescriptor(String actionName)
	{
		if(m_actions == null)
		{
			ITouchpointActionDescriptor[] descs = getActions();
			m_actions = new HashMap<String, ITouchpointActionDescriptor>();
			for(int i = 0; i < descs.length; i++)
				m_actions.put(descs[i].getKey(), descs[i]);
		}
		return m_actions.get(actionName);
	}

}
