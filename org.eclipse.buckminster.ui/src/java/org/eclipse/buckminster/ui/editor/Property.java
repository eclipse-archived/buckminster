/*******************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.editor;

/**
 * @author Karel Brezina
 * 
 */
public class Property
{
	private String m_key;

	private String m_value;

	public Property(String key, String value)
	{
		m_key = key;
		m_value = value;
	}

	public String getKey()
	{
		return m_key;
	}

	public String getValue()
	{
		return m_value;
	}

	public void setKey(String key)
	{
		m_key = key;
	}

	public void setValue(String value)
	{
		m_value = value;
	}
}
