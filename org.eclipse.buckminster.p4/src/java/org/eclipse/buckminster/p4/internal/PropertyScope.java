/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.p4.internal;

import java.util.Map;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;

/**
 * @author Thomas Hallgren
 */
public abstract class PropertyScope
{
	private final Map<String,String> m_scope;

	protected PropertyScope(Map<String,String> scope)
	{
		m_scope = scope;
	}

	protected String expand(String value)
	{
		return ExpandingProperties.expand(m_scope, value, 0);
	}

	protected Map<String,String> getScope()
	{
		return m_scope;
	}
}
