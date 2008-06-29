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
 * An implementation of the interface {@link ITouchpointActionParameterDescriptor}.
 * @author Henrik Lindberg
 *
 */
public class TouchpointActionParameterDescriptor implements ITouchpointActionParameterDescriptor
{
	private String m_default;
	private String m_key;
	private String m_label;
	private String m_type;
	private boolean m_required;
	
	public TouchpointActionParameterDescriptor(String key, String label, String defaultValue, String type, boolean required)
	{
		m_key = key;
		m_label = label;
		m_default = defaultValue;
		m_type = type;
		m_required = required;
	}
	public String getDefaultValue()
	{
		return m_default;
	}

	public String getKey()
	{
		return m_key;
	}

	public String getLabel()
	{
		return m_label;
	}

	public String getType()
	{
		return m_type;
	}

	public boolean isRequired()
	{
		return m_required;
	}

}
