/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.buckminster.core.ctype.AbstractComponentType;
import org.eclipse.buckminster.core.mspec.builder.MaterializationNodeBuilder;
import org.eclipse.buckminster.core.mspec.model.ConflictResolution;
import org.eclipse.buckminster.jnlp.ui.UiUtils;
import org.eclipse.buckminster.jnlp.ui.general.editor.IValidator;
import org.eclipse.buckminster.jnlp.ui.general.editor.Table;
import org.eclipse.buckminster.jnlp.ui.general.editor.ValidatorException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Text;

/**
 * @author Karel Brezina
 * 
 */
public class MaterializationNodeTable extends Table<MaterializationNodeBuilder>
{

	class PatternValidator implements IValidator
	{
		public void validate(Object... arg) throws ValidatorException
		{
			String namePattern = (String)arg[0];

			if(namePattern == null || namePattern.length() == 0)
			{
				throw new ValidatorException("Name Pattern cannot be empty");
			}

			try
			{
				Pattern.compile(namePattern);
			}
			catch(PatternSyntaxException e)
			{
				throw new ValidatorException("Pattern syntax error");
			}
		}
	}

	public MaterializationNodeTable(List<MaterializationNodeBuilder> data)
	{
		super(data);
	}

	public String[] getColumnHeaders()
	{
		return new String[] { "Name Pattern", "Category", "Download Location", "Materializer", "Conflict Resolution" };
	}

	public int[] getColumnWeights()
	{
		return new int[] { 20, 20, 50, 0, 0 };
	}

	public Object[] toRowArray(MaterializationNodeBuilder t)
	{
		Object[] array = new Object[getColumns()];

		array[0] = t.getNamePattern();
		array[1] = t.getComponentTypeID();
		array[2] = t.getInstallLocation();
		array[3] = t.getMaterializer();
		array[4] = t.getConflictResolution();

		return array;
	}

	public MaterializationNodeBuilder toRowClass(Object[] args) throws ValidatorException
	{
		MaterializationNodeBuilder builder = new MaterializationNodeBuilder();

		builder.setNamePattern(Pattern.compile((String)args[0]));
		builder.setComponentTypeID((String)args[1]);
		builder.setInstallLocation(Path.fromOSString((String)args[2]));
		builder.setMaterializer((String)args[3]);
		builder.setConflictResolution((ConflictResolution)args[4]);

		return builder;
	}

	@Override
	public IValidator getFieldValidator(int idx)
	{
		switch(idx)
		{
		case 0:
			return new PatternValidator();
		default:
			return Table.getEmptyValidator();
		}
	}

	@Override
	public Control getControl(Composite parent, int idx, Object value)
	{
		switch(idx)
		{
		case 1:
			return getCategoryCombo(parent, idx, value);
		case 2:
			return getLocationComposite(parent, idx, value);
		case 3:
			return getMaterializerCombo(parent, idx, value);
		case 4:
			return getConflictResolutionCombo(parent, idx, value);
		default:
			return super.getControl(parent, idx, value);

		}
	}

	private Combo getCategoryCombo(Composite parent, final int idx, Object value)
	{
		final Combo combo = UiUtils.createGridCombo(parent, 0, 0, null, null, SWT.READ_ONLY);
		combo.setItems(AbstractComponentType.getComponentTypeIDs(true));

		int selectionIdx = 0;
		if(value != null)
		{
			selectionIdx = combo.indexOf((String)value);

			if(selectionIdx < 0)
			{
				selectionIdx = 0;
			}
		}

		combo.select(selectionIdx);
		combo.setData(combo.getText());

		combo.addModifyListener(new ModifyListener()
		{

			public void modifyText(ModifyEvent e)
			{
				combo.setData(combo.getText());
				validateFieldInFieldListener(combo, getFieldValidator(idx), combo.getText());
			}
		});

		return combo;
	}

	private Composite getLocationComposite(final Composite parent, final int idx, Object value)
	{
		final Composite locationComposite = new Composite(parent, SWT.NONE);

		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginHeight = gridLayout.marginWidth = 0;
		locationComposite.setLayout(gridLayout);
		locationComposite.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));

		final Text locationText = new Text(locationComposite, SWT.BORDER);
		locationText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		String stringValue = value == null
				? ""
				: ((IPath)value).toOSString();

		locationText.setText(stringValue);
		locationComposite.setData(stringValue);

		locationText.addModifyListener(new ModifyListener()
		{

			public void modifyText(ModifyEvent e)
			{
				locationComposite.setData(locationText.getText());
				validateFieldInFieldListener(locationComposite, getFieldValidator(idx), locationText.getText());
			}
		});

		Button browseButton = new Button(locationComposite, SWT.PUSH);
		browseButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		browseButton.setText("Browse");
		browseButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				DirectoryDialog dlg = new DirectoryDialog(parent.getShell());
				dlg.setFilterPath(locationText.getText());
				String dir = dlg.open();

				if(dir != null)
				{
					locationText.setText(dir);
				}
			}
		});

		return locationComposite;
	}

	private Combo getMaterializerCombo(Composite parent, final int idx, Object value)
	{
		final Combo combo = UiUtils.createGridCombo(parent, 0, 0, null, null, SWT.READ_ONLY);

		// TODO prepare materializers a better way
		// combo.setItems(AbstractMaterializer.getMaterializerIDs(false));
		combo.setItems(InstallWizard.MATERIALIZERS);

		int selectionIdx = 0;
		if(value != null)
		{
			selectionIdx = combo.indexOf((String)value);

			if(selectionIdx < 0)
			{
				selectionIdx = 0;
			}
		}

		combo.select(selectionIdx);
		combo.setData(combo.getText());

		combo.addModifyListener(new ModifyListener()
		{

			public void modifyText(ModifyEvent e)
			{
				combo.setData(combo.getText());
				validateFieldInFieldListener(combo, getFieldValidator(idx), combo.getText());
			}
		});

		return combo;
	}

	private Combo getConflictResolutionCombo(Composite parent, final int idx, Object value)
	{
		final Combo combo = UiUtils.createGridEnumCombo(parent, 0, 0, ConflictResolution.values(), null, null,
				SWT.READ_ONLY);

		ConflictResolution crValue;
		if(value == null)
		{
			crValue = ConflictResolution.getDefault();
		}
		else
		{
			crValue = (ConflictResolution)value;
		}

		combo.select(crValue.ordinal());
		combo.setData(crValue);

		for(ConflictResolution cr : ConflictResolution.values())
		{
			combo.setData(String.valueOf(cr.ordinal()), cr);
		}

		combo.addModifyListener(new ModifyListener()
		{

			public void modifyText(ModifyEvent e)
			{
				ConflictResolution cr = (ConflictResolution)combo.getData(String.valueOf(combo.getSelectionIndex()));
				combo.setData(cr);
				validateFieldInFieldListener(combo, getFieldValidator(idx), cr);
			}
		});

		return combo;
	}
}
