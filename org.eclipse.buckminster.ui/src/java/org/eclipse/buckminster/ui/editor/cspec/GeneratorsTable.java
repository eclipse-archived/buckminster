/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.editor.cspec;

import java.util.List;

import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.GeneratorBuilder;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.ui.general.editor.IValidator;
import org.eclipse.buckminster.ui.general.editor.Table;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;

/**
 * @author Karel Brezina
 *
 */
public class GeneratorsTable extends Table<GeneratorBuilder>
{

	class NameValidator implements IValidator
	{
		public void validate(Object... arg) throws ValidatorException
		{
			String string = (String) arg[0];
			
			if(string == null || string.length() == 0)
			{
				throw new ValidatorException("Generator name cannot be empty");
			}
		}
	}
	
	class AttributeValidator implements IValidator
	{
		public void validate(Object... arg) throws ValidatorException
		{
			String string = (String) arg[0];
			
			if(string == null || string.length() == 0)
			{
				throw new ValidatorException("Attribute cannot be empty");
			}
		}
	}
	
	private CSpecBuilder m_cspecBuilder;
	
	public GeneratorsTable(List<GeneratorBuilder> data, CSpecBuilder cspecBuilder)
	{
		super(data);
		m_cspecBuilder = cspecBuilder;
	}

	public String[] getColumnHeaders()
	{
		return new String[]{"Name", "Attribute", "Component"};
	}

	public int[] getColumnWeights()
	{
		return new int[]{40, 20, 20};
	}

	public Object[] toRowArray(GeneratorBuilder t)
	{
		Object[] array = new Object[getColumns()];
		
		array[0] = t.getName();
		array[1] = t.getAttribute();
		array[2] = t.getComponent();
		
		return array;
	}

	public GeneratorBuilder toRowClass(Object[] args) throws ValidatorException
	{
		GeneratorBuilder builder = m_cspecBuilder.createGeneratorBuilder();
		
		builder.setName(TextUtils.notEmptyString((String) args[0]));
		builder.setAttribute(TextUtils.notEmptyString((String) args[1]));
		builder.setComponent(TextUtils.notEmptyString((String) args[2]));
		
		return builder;
	}

	@Override
	public IValidator getFieldValidator(int idx)
	{
		switch(idx)
		{
		case 0:
			return new NameValidator();
		case 1:
			return new AttributeValidator();
		default:
			return Table.getEmptyValidator();
		}
	}
}
