/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.adapters;

import org.eclipse.buckminster.core.cspec.model.ComponentRequest;

/**
 * Not a true adapter, but used to carry data that is otherwise in just a Map.
 * 
 * @author Henrik Lindberg
 * 
 */
public class ComponentReference
{
	public enum Mode
	{
		IN, OUT, ;
	}

	private String m_componentName;

	private ComponentRequest m_componentRequest;

	private Mode m_mode;

	public ComponentReference(String name, ComponentRequest request, Mode mode)
	{
		m_componentName = name;
		m_componentRequest = request;
		m_mode = mode;
	}

	public String getComponentName()
	{
		return m_componentName;
	}

	public ComponentRequest getComponentRequest()
	{
		return m_componentRequest;
	}

	public Mode getMode()
	{
		return m_mode;
	}
}
