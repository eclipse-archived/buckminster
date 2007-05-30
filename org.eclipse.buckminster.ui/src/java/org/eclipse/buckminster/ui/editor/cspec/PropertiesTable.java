/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.editor.cspec;

import java.util.List;

import org.eclipse.buckminster.ui.general.editor.IValidator;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.buckminster.ui.general.editor.simple.SimpleTable;

/**
 * @author Karel Brezina
 *
 */
public class PropertiesTable extends SimpleTable<Property>
{
	public PropertiesTable(List<Property> data)
	{
		super(data);
	}

	public String[] getColumnHeaders()
	{
		return new String[] {"Key", "Value"};
	}

	public int[] getColumnWeights()
	{
		return new int[] {20, 30};
	}

	public Object[] toRowArray(Property t)
	{
		return new Object[]{t.getKey(), t.getValue()};
	}

	public Property toRowClass(Object[] args) throws ValidatorException
	{	
		return new Property((String) args[0], (String) args[1]);
	}
	
	@Override
	public IValidator getFieldValidator(int idx)
	{
		switch(idx)
		{
		case 0:
			return SimpleTable.createNotEmptyStringValidator("Key cannot be empty");
		default:
			return SimpleTable.getEmptyValidator();
		}
	}
}

class Property
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
	public void setKey(String key)
	{
		m_key = key;
	}
	public String getValue()
	{
		return m_value;
	}
	public void setValue(String value)
	{
		m_value = value;
	}
}


