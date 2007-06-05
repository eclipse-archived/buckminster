/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.editor.cspec;

import java.util.List;

import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.PrerequisiteBuilder;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.ui.general.editor.IValidator;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.buckminster.ui.general.editor.simple.IWidgetin;
import org.eclipse.buckminster.ui.general.editor.simple.SimpleTable;
import org.eclipse.swt.widgets.Composite;
/**
 * @author Karel Brezina
 *
 */
public class PrerequisitesTable extends SimpleTable<PrerequisiteBuilder>
{
	private AttributeBuilder m_attributeBuilder;

	public PrerequisitesTable(List<PrerequisiteBuilder> data, AttributeBuilder attributeBuilder)
	{
		super(data);
		m_attributeBuilder = attributeBuilder;
	}

	public String[] getColumnHeaders()
	{
		return new String[] {"Name", "Alias", "Component", "Contributor", "Optional"};
	}

	public int[] getColumnWeights()
	{
		return new int[] {10, 10, 20, 0, 0};
	}

	public Object[] toRowArray(PrerequisiteBuilder t)
	{
		return new Object[] {
				t.getName(),
				t.getAlias(),
				t.getComponent(),
				Boolean.valueOf(t.isContributor()),
				Boolean.valueOf(t.isOptional())};
	}

	public PrerequisiteBuilder toRowClass(Object[] args) throws ValidatorException
	{
		PrerequisiteBuilder builder = m_attributeBuilder.createPrerequisiteBuilder();
		
		builder.setName(TextUtils.notEmptyString((String) args[0]));
		builder.setAlias(TextUtils.notEmptyString((String) args[1]));
		builder.setComponent(TextUtils.notEmptyString((String) args[2]));
		builder.setContributor(((Boolean) args[3]).booleanValue());
		builder.setOptional(((Boolean) args[4]).booleanValue());
		
		return builder;
	}
	
	@Override
	public IWidgetin getWidgetin(Composite parent, int idx, Object value)
	{
		switch(idx)
		{
			case 0:
			case 1:
			case 2:
				return getTextWidgetin(parent, idx, value);
			case 3:
			case 4:
				return getBooleanCheckBoxWidgetin(parent, idx, value);
			default:
				return getTextWidgetin(parent, idx, value);
		}
	}
	
	@Override
	public IValidator getFieldValidator(int idx)
	{
		switch(idx)
		{
		case 0:
			return SimpleTable.createNotEmptyStringValidator("Prerequisite name cannot be empty");
		default:
			return SimpleTable.getEmptyValidator();
		}
	}
}
