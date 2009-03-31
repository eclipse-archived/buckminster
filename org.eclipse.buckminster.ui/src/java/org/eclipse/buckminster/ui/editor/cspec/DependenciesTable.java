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
import org.eclipse.buckminster.core.cspec.builder.ComponentRequestBuilder;
import org.eclipse.buckminster.core.ctype.AbstractComponentType;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.osgi.filter.FilterFactory;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.editor.VersionDesignator;
import org.eclipse.buckminster.ui.editor.VersionDesignatorEvent;
import org.eclipse.buckminster.ui.editor.VersionDesignatorListener;
import org.eclipse.buckminster.ui.general.editor.IValidator;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.buckminster.ui.general.editor.simple.IWidgetin;
import org.eclipse.buckminster.ui.general.editor.simple.SimpleTable;
import org.eclipse.buckminster.ui.general.editor.simple.WidgetWrapper;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.osgi.framework.InvalidSyntaxException;

/**
 * @author Karel Brezina
 * 
 */
@SuppressWarnings("restriction")
public class DependenciesTable extends SimpleTable<ComponentRequestBuilder>
{
	class FilterValidator implements IValidator
	{
		private ValidatorException m_lastFilterException;

		public Filter createFilter(String value)
		{
			value = TextUtils.notEmptyString(value);
			Filter filter = null;
			m_lastFilterException = null;

			try
			{
				filter = value == null
						? null
						: FilterFactory.newInstance(value);
			}
			catch(InvalidSyntaxException e)
			{
				m_lastFilterException = new ValidatorException(e.getMessage());
			}

			return filter;
		}

		public void validate(Object... arg) throws ValidatorException
		{
			if(m_lastFilterException != null)
				throw m_lastFilterException;
		}
	}

	class VersionDesignatorValidator implements IValidator
	{
		VersionDesignator m_designator;

		public VersionDesignatorValidator(VersionDesignator designator)
		{
			m_designator = designator;
		}

		public void validate(Object... arg) throws ValidatorException
		{
			try
			{
				m_designator.getDirectVersionDesignator();
			}
			catch(CoreException e)
			{
				throw new ValidatorException(e.getMessage());
			}
		}
	}

	private CSpecBuilder m_cspecBuilder;

	private VersionDesignatorValidator m_versionDesignatorValidator;

	private FilterValidator m_filterValidator;

	public DependenciesTable(List<ComponentRequestBuilder> data, CSpecBuilder cspecBuilder)
	{
		super(data);
		m_cspecBuilder = cspecBuilder;
		m_filterValidator = new FilterValidator();
	}

	public ComponentRequestBuilder createRowClass()
	{
		return m_cspecBuilder.createDependencyBuilder();
	}

	@Override
	public IWidgetin[] fillGrid(Composite parent, Object[] fieldValues)
	{
		((GridLayout)parent.getLayout()).numColumns = 3;

		IWidgetin[] widgetins = new IWidgetin[getColumns()];

		UiUtils.createGridLabel(parent, getColumnHeaders()[0] + ":", 1, 0, SWT.NONE); //$NON-NLS-1$
		widgetins[0] = getWidgetin(parent, 0, fieldValues[0]);

		UiUtils.createGridLabel(parent, getColumnHeaders()[1] + ":", 1, 0, SWT.NONE); //$NON-NLS-1$
		widgetins[1] = getWidgetin(parent, 1, fieldValues[1]);
		new Label(parent, SWT.NONE);

		widgetins[2] = getWidgetin(parent, 2, fieldValues[2]);

		UiUtils.createGridLabel(parent, getColumnHeaders()[3] + ":", 1, 0, SWT.NONE); //$NON-NLS-1$
		widgetins[3] = getWidgetin(parent, 3, fieldValues[3]);

		return widgetins;
	}

	public String[] getColumnHeaders()
	{
		return new String[] { Messages.name, Messages.component_type, Messages.version_designator, Messages.filter };
	}

	public int[] getColumnWeights()
	{
		return new int[] { 40, 20, 20, 20 };
	}

	@Override
	public IValidator getFieldValidator(int idx)
	{
		switch(idx)
		{
		case 0:
			return SimpleTable.createNotEmptyStringValidator(Messages.dependency_name_cannot_be_empty);
		case 2:
			return m_versionDesignatorValidator;
		case 3:
			return m_filterValidator;
		default:
			return SimpleTable.getEmptyValidator();
		}
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
			VersionDesignator designator = getVersionDesignator(parent, idx, value);
			m_versionDesignatorValidator = new VersionDesignatorValidator(designator);
			return designator;
		case 3:
			return getFilter(parent, idx, value);
		default:
			return getTextWidgetin(parent, idx, value);
		}
	}

	public Object[] toRowArray(ComponentRequestBuilder t)
	{
		Object[] array = new Object[getColumns()];

		array[0] = t.getName();
		array[1] = t.getComponentTypeID();
		array[2] = t.getVersionRange();
		array[3] = t.getFilter();

		return array;
	}

	public void updateRowClass(ComponentRequestBuilder builder, Object[] args) throws ValidatorException
	{
		builder.setName(TextUtils.notEmptyString((String)args[0]));
		builder.setComponentTypeID(TextUtils.notEmptyString((String)args[1]));
		builder.setVersionRange((VersionRange)args[2]);
		builder.setFilter((Filter)args[3]);
	}

	private IWidgetin getFilter(Composite parent, final int idx, Object value)
	{
		final Text text = UiUtils.createGridText(parent, 2, 0, SWT.NONE);

		final IWidgetin widgetin = new WidgetWrapper(text);

		String stringValue = TextUtils.notNullString(value);
		text.setText(stringValue);
		text.setData(value);

		text.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				String filterStr = TextUtils.notEmptyTrimmedString(text.getText());
				widgetin.setData(m_filterValidator.createFilter(filterStr));
				validateFieldInFieldListener(widgetin, getFieldValidator(idx), widgetin.getData());
			}
		});
		return widgetin;
	}

	private IWidgetin getName(Composite parent, final int idx, Object value)
	{
		final Text text = UiUtils.createGridText(parent, 2, 0, SWT.NONE);

		final IWidgetin widgetin = new WidgetWrapper(text);

		String stringValue = value == null
				? "" : value.toString(); //$NON-NLS-1$

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
		designator.refreshValues((VersionRange)value);

		designator.setData(value);

		designator.addVersionDesignatorListener(new VersionDesignatorListener()
		{

			public void modifyVersionDesignator(VersionDesignatorEvent e)
			{
				try
				{
					VersionRange designatorValue = designator.getDirectVersionDesignator();
					designator.setData(designatorValue);
				}
				catch(CoreException e1)
				{
					// nothing - error message is displayed using validateFieldInFieldListener method
				}

				validateFieldInFieldListener(designator, getFieldValidator(idx), null);
			}
		});

		return designator;
	}
}
