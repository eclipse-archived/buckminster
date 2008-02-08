/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.editor.cspec;

import java.util.List;

import org.eclipse.buckminster.core.cspec.builder.PrerequisiteBuilder;
import org.eclipse.buckminster.core.cspec.builder.TopLevelAttributeBuilder;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.buckminster.ui.general.editor.simple.IWidgetin;
import org.eclipse.swt.widgets.Composite;

/**
 * PrerequisitesTable without Alias field
 * 
 * @author Karel Brezina
 *
 */
public class GroupPrerequisitesTable extends PrerequisitesTable
{

	public GroupPrerequisitesTable(CSpecEditor editor, AttributesTable<?> parentAttributesTable,
			List<PrerequisiteBuilder> data, TopLevelAttributeBuilder attributeBuilder)
	{
		super(editor, parentAttributesTable, data, attributeBuilder);
	}

	@Override
	public String[] getColumnHeaders()
	{
		return new String[] {"Component", "Name", "Contributor", "Optional"};
	}

	@Override
	public int[] getColumnWeights()
	{
		return new int[] {20, 10, 0, 0};
	}

	@Override
	public Object[] toRowArray(PrerequisiteBuilder t)
	{
		return new Object[] {
				t.getComponent(),
				t.getName(),
				Boolean.valueOf(t.isContributor()),
				Boolean.valueOf(t.isOptional())};
	}

	@Override
	public void updateRowClass(PrerequisiteBuilder builder, Object[] args) throws ValidatorException
	{
		builder.setComponent(TextUtils.notEmptyString((String) args[0]));
		builder.setName(TextUtils.notEmptyString((String) args[1]));
		builder.setContributor(((Boolean) args[2]).booleanValue());
		builder.setOptional(((Boolean) args[3]).booleanValue());
	}
	
	@Override
	public IWidgetin getWidgetin(Composite parent, int idx, Object value)
	{
		if(idx < 2)
			return super.getWidgetin(parent, idx, value);

		// Alias is removed here
		return super.getWidgetin(parent, ++idx, value);
	}
}
