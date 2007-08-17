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
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.general.editor.IValidator;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.buckminster.ui.general.editor.simple.IWidgetin;
import org.eclipse.buckminster.ui.general.editor.simple.SimpleTable;
import org.eclipse.buckminster.ui.general.editor.simple.WidgetinWrapper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
/**
 * @author Karel Brezina
 *
 */
public class PrerequisitesTable extends SimpleTable<PrerequisiteBuilder>
{
	private CSpecEditor m_editor;
	private AttributesTable<?> m_parentAttributesTable;
	private AttributeBuilder m_attributeBuilder;
	private Combo m_attributeCombo;

	public PrerequisitesTable(CSpecEditor editor, AttributesTable<?> parentAttributesTable, List<PrerequisiteBuilder> data, AttributeBuilder attributeBuilder)
	{
		super(data);
		m_editor = editor;
		m_parentAttributesTable = parentAttributesTable;
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
				return getAttributeWidgetin(parent, idx, value, m_editor.getAttributeNames(m_parentAttributesTable.getCurrentBuilder().getName()), SWT.NONE);
			case 1:
				return getTextWidgetin(parent, idx, value);
			case 2:
				return getComponentWidgetin(parent, idx, value, m_editor.getComponentNames(), SWT.NONE);
			case 3:
			case 4:
				return getBooleanCheckBoxWidgetin(parent, idx, value);
			default:
				return getTextWidgetin(parent, idx, value);
		}
	}
	
	protected IWidgetin getAttributeWidgetin(Composite parent, final int idx, Object value, String[] items, int style)
	{
		final String ITEMS_KEY = "items";
		
		final Combo combo = UiUtils.createGridCombo(parent, 0, 0, null, null, style);
		
		m_attributeCombo = combo;
		
		final IWidgetin widgetin = new WidgetinWrapper(combo);
		
		combo.setItems(items);
		
		String stringValue = value == null ? "" : value.toString();

		combo.setText(stringValue);
		combo.setData(stringValue);
		combo.setData(ITEMS_KEY, items);
		
		combo.addModifyListener(new ModifyListener()
		{

			public void modifyText(ModifyEvent e)
			{
				combo.setData(combo.getText());
				validateFieldInFieldListener(widgetin, getFieldValidator(idx), combo.getText());				
			}
		});

		return widgetin;		
	}

	protected IWidgetin getComponentWidgetin(Composite parent, final int idx, Object value, String[] items, int style)
	{
		final Combo combo = UiUtils.createGridCombo(parent, 0, 0, null, null, style);
		final IWidgetin widgetin = new WidgetinWrapper(combo);
		
		combo.setItems(items);
		
		String stringValue = value == null ? "" : value.toString();

		combo.setText(stringValue);
		combo.setData(stringValue);
		setAttributeItems(combo.getText());
		
		combo.addModifyListener(new ModifyListener()
		{

			public void modifyText(ModifyEvent e)
			{
				combo.setData(combo.getText());
				validateFieldInFieldListener(widgetin, getFieldValidator(idx), combo.getText());
				setAttributeItems(combo.getText());
			}
		});

		return widgetin;		
	}

	private void setAttributeItems(String string)
	{
		String currentAttribute = m_attributeCombo.getText();
		if(string == null || string.length() == 0)
		{
			m_attributeCombo.setItems(((String[]) m_attributeCombo.getData("items")));
		} else
		{
			m_attributeCombo.setItems(new String[]{});
		}
		
		m_attributeCombo.setText(currentAttribute);
		m_attributeCombo.update();
	}

	@Override
	public IValidator getRowValidator()
	{
		return new IValidator()
		{

			public void validate(Object... arg) throws ValidatorException
			{
				// Integer rowNum = (Integer) arg[0];

				PrerequisiteBuilder prerequisite = toRowClass((Object[])arg[1]);

				if((prerequisite.getName() == null || prerequisite.getName().length() == 0)
						&& (prerequisite.getComponent() == null || prerequisite.getComponent().length() == 0))
				{
					throw new ValidatorException("Name or component has to be filled");
				}
			}
		};
	}
}
