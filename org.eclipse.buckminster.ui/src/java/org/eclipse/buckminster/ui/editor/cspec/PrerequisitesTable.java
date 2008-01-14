/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.editor.cspec;

import java.util.List;
import java.util.TreeSet;

import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.DependencyBuilder;
import org.eclipse.buckminster.core.cspec.builder.PrerequisiteBuilder;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.general.editor.IValidator;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.buckminster.ui.general.editor.simple.IWidgetin;
import org.eclipse.buckminster.ui.general.editor.simple.SimpleTable;
import org.eclipse.buckminster.ui.general.editor.simple.WidgetWrapper;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.ErrorDialog;
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

	private IWidgetin m_componentWidgetin = null;

	private IWidgetin m_attributeWidgetin = null;

	public PrerequisitesTable(CSpecEditor editor, AttributesTable<?> parentAttributesTable,
			List<PrerequisiteBuilder> data, AttributeBuilder attributeBuilder)
	{
		super(data);
		m_editor = editor;
		m_parentAttributesTable = parentAttributesTable;
		m_attributeBuilder = attributeBuilder;
	}

	public String[] getColumnHeaders()
	{
		return new String[] { "Component", "Attribute", "Alias", "Contributor", "Optional" };
	}

	public int[] getColumnWeights()
	{
		return new int[] { 20, 10, 10, 0, 0 };
	}

	public Object[] toRowArray(PrerequisiteBuilder t)
	{
		return new Object[] { t.getComponent(), t.getName(), t.getAlias(), Boolean.valueOf(t.isContributor()),
				Boolean.valueOf(t.isOptional()) };
	}

	public PrerequisiteBuilder createRowClass()
	{
		return m_attributeBuilder.createPrerequisiteBuilder();
	}

	public void updateRowClass(PrerequisiteBuilder builder, Object[] args) throws ValidatorException
	{
		builder.setComponent(TextUtils.notEmptyString((String)args[0]));
		builder.setName(TextUtils.notEmptyString((String)args[1]));
		builder.setAlias(TextUtils.notEmptyString((String)args[2]));
		builder.setContributor(((Boolean)args[3]).booleanValue());
		builder.setOptional(((Boolean)args[4]).booleanValue());
	}

	@Override
	public IWidgetin getWidgetin(Composite parent, int idx, Object value)
	{

		switch(idx)
		{
		case 0:
			return m_componentWidgetin = getComponentWidgetin(parent, idx, value, m_editor.getComponentNames(),
					SWT.NONE);
		case 1:
			m_attributeWidgetin = getAttributeWidgetin(parent, idx, value, m_editor
					.getAttributeNames(m_parentAttributesTable.getCurrentBuilder().getName()), SWT.NONE);
			
			setAttributeItems();
			
			return m_attributeWidgetin;
		case 2:
			return getTextWidgetin(parent, idx, value);
		case 3:
			return getBooleanCheckBoxWidgetin(parent, idx, (Boolean)value, Boolean.TRUE);
		case 4:
			return getBooleanCheckBoxWidgetin(parent, idx, (Boolean)value);
		default:
			return getTextWidgetin(parent, idx, value);
		}
	}

	protected IWidgetin getComponentWidgetin(Composite parent, final int idx, Object value, String[] items, int style)
	{
		final Combo combo = UiUtils.createGridCombo(parent, 0, 0, null, null, style);
		final IWidgetin widgetin = new WidgetWrapper(combo);

		combo.setItems(items);

		String stringValue = value == null
				? ""
				: value.toString();

		combo.setText(stringValue);
		combo.setData(stringValue);

		combo.addModifyListener(new ModifyListener()
		{

			public void modifyText(ModifyEvent e)
			{
				combo.setData(combo.getText());
				validateFieldInFieldListener(widgetin, getFieldValidator(idx), combo.getText());
				setAttributeItems();
			}
		});

		return widgetin;
	}

	protected IWidgetin getAttributeWidgetin(Composite parent, final int idx, Object value, String[] items, int style)
	{
		final String ITEMS_KEY = "items";

		final Combo combo = UiUtils.createGridCombo(parent, 0, 0, null, null, style);

		final IWidgetin widgetin = new WidgetWrapper(combo);

		String stringValue = value == null
				? ""
				: value.toString();

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

	private void setAttributeItems()
	{
		if(m_componentWidgetin == null || m_attributeWidgetin == null)
			return;
		
		Combo componentCombo = ((Combo)((WidgetWrapper)m_componentWidgetin).getWidget());

		Combo attributeCombo = ((Combo)((WidgetWrapper)m_attributeWidgetin).getWidget());
		
		String currentAttribute = attributeCombo.getText();

		if(componentCombo.getText() == null || componentCombo.getText().length() == 0)
		{
			attributeCombo.setItems(((String[])attributeCombo.getData("items")));
		}
		else
		{
			DependencyBuilder builder = m_editor.getDependencyBuilder(componentCombo.getText());
			ComponentRequest cr = new ComponentRequest(builder.getName(), builder.getComponentTypeID(), builder.getVersionDesignator()); 

			TreeSet<String> prereqAttributes = new TreeSet<String>();
			Resolution prereqResolution;
			try
			{
				prereqResolution = WorkspaceInfo.getResolution(cr, false);
				CSpec prereqCSpec = prereqResolution.getCSpec();
				
				for(Attribute attribute : prereqCSpec.getAttributes().values())
					if(attribute.isPublic())
						prereqAttributes.add(attribute.getName());
			}
			catch(MissingComponentException e)
			{
				// the component is not found - cannot show attribute names
			}
			catch(CoreException e)
			{
				ErrorDialog.openError(m_editor.getSite().getShell(), null, "Cannot get attribute names for the selected component", e.getStatus());
			}
			
			attributeCombo.setItems(prereqAttributes.toArray(new String[0]));
		}

		attributeCombo.setText(currentAttribute);
		attributeCombo.update();
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
