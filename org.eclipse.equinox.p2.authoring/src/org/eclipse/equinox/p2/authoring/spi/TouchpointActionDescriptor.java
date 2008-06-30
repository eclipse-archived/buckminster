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
 * An implementaion of {@link ITouchpointActionDescriptor}.
 * @author Henrik Lindberg
 *
 */
public class TouchpointActionDescriptor implements ITouchpointActionDescriptor
{
	private String m_key;
	private String m_label;
	
	private ITouchpointActionParameterDescriptor[] m_parameters;
	public TouchpointActionDescriptor(String key, String label, ITouchpointActionParameterDescriptor[] parameters)
	{
		m_key = key;
		m_label = label;
		m_parameters = parameters;
	}
	public String getKey()
	{
		return m_key;
	}
	public String getLabel()
	{
		return m_label;
	}
	public ITouchpointActionParameterDescriptor[] getParameters()
	{
		return m_parameters;
	}
	public ITouchpointActionParameterDescriptor getParameter(String parameterKey)
	{
		if(m_parameters == null)
			return null;
		for(int i = 0; i < m_parameters.length; i++)
			if(m_parameters[i].getKey().equals(parameterKey))
				return m_parameters[i];
		return null;
	}
}
