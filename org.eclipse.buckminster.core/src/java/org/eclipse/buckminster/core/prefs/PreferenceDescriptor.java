/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.core.prefs;

/**
 * @author Thomas Hallgren
 * 
 */
public class PreferenceDescriptor implements IPreferenceDescriptor
{
	private Enum<?>[] m_enums;

	private String m_label;

	private final String m_preferenceName;

	private int m_textWidth = -1;

	private final PreferenceType m_type;

	private IPreferenceValidator m_validator;

	public PreferenceDescriptor(String preferenceName, PreferenceType type)
	{
		this(preferenceName, type, preferenceName);
	}

	public PreferenceDescriptor(String preferenceName, PreferenceType type, String label)
	{
		super();
		m_preferenceName = preferenceName;
		if(type == PreferenceType.Integer)
			m_textWidth = 10;
		m_type = type;
		m_label = label;
	}

	public Enum<?>[] getEnums()
	{
		return m_enums;
	}

	public String getLabel()
	{
		return m_label;
	}

	public String getName()
	{
		return m_preferenceName;
	}

	public int getTextWidth()
	{
		return m_textWidth;
	}

	public PreferenceType getType()
	{
		return m_type;
	}

	public IPreferenceValidator getValidator()
	{
		return m_validator;
	}

	public void setEnums(Enum<?>[] enums)
	{
		m_enums = enums;
	}

	public void setLabel(String label)
	{
		m_label = label;
	}

	public void setTextWidth(int textWidth)
	{
		m_textWidth = textWidth;
	}

	public void setValidator(IPreferenceValidator validator)
	{
		m_validator = validator;
	}
}
