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
import org.eclipse.buckminster.core.cspec.builder.DependencyBuilder;
import org.eclipse.buckminster.core.ctype.AbstractComponentType;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.editor.VersionDesignator;
import org.eclipse.buckminster.ui.editor.VersionDesignatorEvent;
import org.eclipse.buckminster.ui.editor.VersionDesignatorListener;
import org.eclipse.buckminster.ui.general.editor.IValidator;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.buckminster.ui.general.editor.simple.IWidgetin;
import org.eclipse.buckminster.ui.general.editor.simple.SimpleTable;
import org.eclipse.buckminster.ui.general.editor.simple.WidgetWrapper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 *
 */
public class DependenciesTable extends SimpleTable<DependencyBuilder>
{
	private CSpecBuilder m_cspecBuilder;
	
	public DependenciesTable(List<DependencyBuilder> data, CSpecBuilder cspecBuilder)
	{
		super(data);
		m_cspecBuilder = cspecBuilder;
	}

	public String[] getColumnHeaders()
	{
		return new String[]{"Name", "Component Type", "Version Designator"};
	}

	public int[] getColumnWeights()
	{
		return new int[]{40, 20, 20};
	}

	public Object[] toRowArray(DependencyBuilder t)
	{
		Object[] array = new Object[getColumns()];
		
		array[0] = t.getName();
		array[1] = t.getComponentTypeID();
		array[2] = t.getVersionDesignator();
		
		return array;
	}

	public DependencyBuilder toRowClass(Object[] args) throws ValidatorException
	{
		DependencyBuilder builder = m_cspecBuilder.createDependencyBuilder();
		
		builder.setName(TextUtils.notEmptyString((String) args[0]));
		builder.setComponentTypeID(TextUtils.notEmptyString((String) args[1]));
		builder.setVersionDesignator((IVersionDesignator) args[2]);
		
		return builder;
	}

	
	@Override
	public IWidgetin getWidgetin(Composite parent, int idx, Object value)
	{
		switch(idx)
		{
			case 0:
				return getName(parent, idx, value);
			case 1:
				return getComboWidgetin(parent, idx, value, AbstractComponentType.getComponentTypeIDs(true), SWT.READ_ONLY);
			case 2:
				return getVersionDesignator(parent, idx, value);
			default:
				return getTextWidgetin(parent, idx, value);
		}
	}

	private IWidgetin getName(Composite parent, final int idx, Object value)
	{
		final Text text = UiUtils.createGridText(parent, 2, 0, SWT.NONE);
		
		final IWidgetin widgetin = new WidgetWrapper(text);
		
		String stringValue = value == null ? "" : value.toString();
		
		text.setText(stringValue);
		text.setData(stringValue);
		
		text.addModifyListener(new ModifyListener()
		{

			public void modifyText(ModifyEvent e)
			{
				widgetin.setData(text.getText());
				validateFieldInFieldListener(widgetin, getFieldValidator(idx), text.getText());
			}
		});

		return widgetin;
	}

	private VersionDesignator getVersionDesignator(Composite parent, final int idx, Object value)
	{
		final VersionDesignator designator = new VersionDesignator(parent);
		designator.refreshValues((IVersionDesignator)value);
		
		designator.setData(value);

		designator.addVersionDesignatorListener(new VersionDesignatorListener()
		{

			public void modifyVersionDesignator(VersionDesignatorEvent e)
			{
				IVersionDesignator designatorValue = designator.getVersionDesignator();
				designator.setData(designatorValue);
				validateFieldInFieldListener(designator, getFieldValidator(idx), designatorValue);				
			}
		});

		return designator;		
	}
	
	@Override
	public IWidgetin[] fillGrid(Composite parent, Object[] fieldValues)
	{
		((GridLayout) parent.getLayout()).numColumns = 3; 
		
		IWidgetin[] widgetins = new IWidgetin[getColumns()];
		
		UiUtils.createGridLabel(parent, getColumnHeaders()[0] + ":", 1, 0, SWT.NONE);
		widgetins[0] = getWidgetin(parent, 0, fieldValues[0]);
		
		UiUtils.createGridLabel(parent, getColumnHeaders()[1] + ":", 1, 0, SWT.NONE);
		widgetins[1] = getWidgetin(parent, 1, fieldValues[1]);
		new Label(parent, SWT.NONE);
		
		widgetins[2] = getWidgetin(parent, 2, fieldValues[2]);
		
		return widgetins;
	}

	@Override
	public IValidator getFieldValidator(int idx)
	{
		switch(idx)
		{
		case 0:
			return SimpleTable.createNotEmptyStringValidator("Dependency name cannot be empty");
		default:
			return SimpleTable.getEmptyValidator();
		}
	}
}
