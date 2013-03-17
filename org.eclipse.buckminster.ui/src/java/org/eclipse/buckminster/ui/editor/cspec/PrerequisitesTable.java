/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.editor.cspec;

import java.util.List;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.buckminster.core.cspec.builder.ComponentRequestBuilder;
import org.eclipse.buckminster.core.cspec.builder.PrerequisiteBuilder;
import org.eclipse.buckminster.core.cspec.builder.TopLevelAttributeBuilder;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.AbstractComponentType;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.osgi.filter.FilterFactory;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.general.editor.IValidator;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.buckminster.ui.general.editor.simple.IWidgetin;
import org.eclipse.buckminster.ui.general.editor.simple.SimpleTable;
import org.eclipse.buckminster.ui.general.editor.simple.WidgetWrapper;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.osgi.framework.InvalidSyntaxException;

/**
 * @author Karel Brezina
 * 
 */
public class PrerequisitesTable extends SimpleTable<PrerequisiteBuilder> {
	private CSpecEditor editor;

	private AttributesTable<?> parentAttributesTable;

	private TopLevelAttributeBuilder attributeBuilder;

	private IWidgetin componentWidgetin = null;

	private IWidgetin typeWidgetin = null;

	private IWidgetin rangeWidgetin = null;

	private IWidgetin attributeWidgetin = null;

	public PrerequisitesTable(CSpecEditor editor, AttributesTable<?> parentAttributesTable, List<PrerequisiteBuilder> data,
			TopLevelAttributeBuilder attributeBuilder, boolean readOnly) {
		super(data, readOnly);
		this.editor = editor;
		this.parentAttributesTable = parentAttributesTable;
		this.attributeBuilder = attributeBuilder;
	}

	@Override
	public PrerequisiteBuilder createRowClass() {
		return attributeBuilder.createPrerequisiteBuilder();
	}

	@Override
	public String[] getColumnHeaders() {
		return new String[] { Messages.component, Messages.type, Messages.version_designator, Messages.attribute, Messages.alias,
				Messages.contributor, Messages.filter, Messages.include_pattern, Messages.exclude_pattern };
	}

	@Override
	public int[] getColumnWeights() {
		return new int[] { 20, 5, 5, 10, 10, 0, 0, 0, 0 };
	}

	@Override
	public IValidator getRowValidator() {
		return new IValidator() {

			@Override
			public void validate(Object... arg) throws ValidatorException {
				// Integer rowNum = (Integer) arg[0];

				PrerequisiteBuilder prerequisite = toRowClass((Object[]) arg[1]);

				if ((prerequisite.getName() == null || prerequisite.getName().length() == 0)
						&& (prerequisite.getComponentName() == null || prerequisite.getComponentName().length() == 0)) {
					throw new ValidatorException(Messages.name_or_component_has_to_be_entered);
				}
			}
		};
	}

	@Override
	public IWidgetin getWidgetin(Composite parent, int idx, Object value) {

		switch (idx) {
			case 0:
				return componentWidgetin = getComponentWidgetin(parent, idx, value, editor.getComponentNames(), SWT.NONE);
			case 1:
				return typeWidgetin = getTypeWidgetin(parent, idx, value, SWT.NONE);
			case 2:
				return rangeWidgetin = getTextWidgetin(parent, idx, value);
			case 3:
				attributeWidgetin = getAttributeWidgetin(parent, idx, value,
						editor.getAttributeNames(parentAttributesTable.getCurrentBuilder().getName()), SWT.NONE);

				setAttributeItems();

				return attributeWidgetin;
			case 5:
				return getBooleanCheckBoxWidgetin(parent, idx, (Boolean) value, Boolean.TRUE);
			default:
				return getTextWidgetin(parent, idx, value);
		}
	}

	@Override
	public Object[] toRowArray(PrerequisiteBuilder t) {
		return new Object[] { t.getComponentName(), TextUtils.notNullString(t.getComponentType()), TextUtils.notNullString(t.getVersionRange()),
				t.getName(), t.getAlias(), Boolean.valueOf(t.isContributor()), TextUtils.notNullString(t.getFilter()),
				TextUtils.notNullString(t.getIncludePattern()), TextUtils.notNullString(t.getExcludePattern()) };
	}

	@Override
	public void updateRowClass(PrerequisiteBuilder builder, Object[] args) throws ValidatorException {
		builder.setComponentName(TextUtils.notEmptyString((String) args[0]));
		builder.setComponentType(TextUtils.notEmptyString((String) args[1]));
		String vrStr = TextUtils.notEmptyString((String) args[2]);
		if (vrStr != null) {
			try {
				builder.setVersionRange(new VersionRange(vrStr));
			} catch (IllegalArgumentException e) {
				throw new ValidatorException(e.getMessage());
			}
		} else
			builder.setVersionRange(null);
		builder.setName(TextUtils.notEmptyString((String) args[3]));
		builder.setAlias(TextUtils.notEmptyString((String) args[4]));
		builder.setContributor(((Boolean) args[5]).booleanValue());

		String filterStr = TextUtils.notEmptyString((String) args[6]);
		if (filterStr != null) {
			try {
				builder.setFilter(FilterFactory.newInstance(filterStr));
			} catch (InvalidSyntaxException e) {
				throw new ValidatorException(e.getMessage());
			}
		} else
			builder.setFilter(null);

		String includePatternStr = TextUtils.notEmptyString((String) args[7]);
		if (includePatternStr != null) {
			try {
				builder.setIncludePattern(Pattern.compile(includePatternStr));
			} catch (PatternSyntaxException e) {
				throw new ValidatorException(e.getMessage());
			}
		} else
			builder.setIncludePattern(null);

		String excludePatternStr = TextUtils.notEmptyString((String) args[8]);
		if (excludePatternStr != null) {
			try {
				builder.setExcludePattern(Pattern.compile(excludePatternStr));
			} catch (PatternSyntaxException e) {
				throw new ValidatorException(e.getMessage());
			}
		} else
			builder.setExcludePattern(null);
	}

	protected IWidgetin getAttributeWidgetin(Composite parent, final int idx, Object value, String[] items, int style) {
		final String ITEMS_KEY = "items"; //$NON-NLS-1$

		final Combo combo = UiUtils.createGridCombo(parent, 0, 0, isReadOnly(), null, null, style);

		final IWidgetin widgetin = new WidgetWrapper(combo);

		String stringValue = value == null ? "" //$NON-NLS-1$
				: value.toString();

		combo.setText(stringValue);
		combo.setData(stringValue);
		combo.setData(ITEMS_KEY, items);

		combo.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				combo.setData(combo.getText());
				validateFieldInFieldListener(widgetin, getFieldValidator(idx), combo.getText());
			}
		});

		return widgetin;
	}

	protected IWidgetin getComponentWidgetin(Composite parent, final int idx, Object value, String[] items, int style) {
		final Combo combo = UiUtils.createGridCombo(parent, 0, 0, isReadOnly(), null, null, style);
		final IWidgetin widgetin = new WidgetWrapper(combo);

		combo.setItems(items);

		String stringValue = value == null ? "" //$NON-NLS-1$
				: value.toString();

		combo.setText(stringValue);
		combo.setData(stringValue);

		combo.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				combo.setData(combo.getText());
				validateFieldInFieldListener(widgetin, getFieldValidator(idx), combo.getText());
				setAttributeItems();
			}
		});

		return widgetin;
	}

	protected IWidgetin getTypeWidgetin(Composite parent, final int idx, Object value, int style) {
		final Combo combo = UiUtils.createGridCombo(parent, 0, 0, isReadOnly(), null, null, style);
		final IWidgetin widgetin = new WidgetWrapper(combo);

		combo.setItems(AbstractComponentType.getComponentTypeIDs(true));
		String stringValue = value == null ? "" //$NON-NLS-1$
				: value.toString();

		combo.setText(stringValue);
		combo.setData(stringValue);

		combo.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				combo.setData(combo.getText());
				validateFieldInFieldListener(widgetin, getFieldValidator(idx), combo.getText());
				setAttributeItems();
			}
		});
		return widgetin;
	}

	private void setAttributeItems() {
		if (componentWidgetin == null || attributeWidgetin == null)
			return;

		Combo componentCombo = ((Combo) ((WidgetWrapper) componentWidgetin).getWidget());

		Combo typeCombo = ((Combo) ((WidgetWrapper) typeWidgetin).getWidget());

		Text rangeText = ((Text) ((WidgetWrapper) rangeWidgetin).getWidget());

		Combo attributeCombo = ((Combo) ((WidgetWrapper) attributeWidgetin).getWidget());

		String currentAttribute = attributeCombo.getText();

		if (componentCombo.getText() == null || componentCombo.getText().length() == 0) {
			attributeCombo.setItems(((String[]) attributeCombo.getData("items"))); //$NON-NLS-1$
		} else {
			ComponentRequestBuilder builder = editor.getDependencyBuilder(componentCombo.getText(), typeCombo.getText(), rangeText.getText());
			ComponentRequest cr = new ComponentRequest(builder.getName(), builder.getComponentTypeID(), builder.getVersionRange());

			TreeSet<String> prereqAttributes = new TreeSet<String>();
			try {
				Resolution prereqResolution = WorkspaceInfo.getResolution(cr, false);
				CSpec prereqCSpec = prereqResolution.getCSpec();

				for (Attribute attribute : prereqCSpec.getAttributes().values())
					if (attribute.isPublic())
						prereqAttributes.add(attribute.getName());
			} catch (MissingComponentException e) {
				// the component is not found - cannot show attribute names
			} catch (CoreException e) {
				ErrorDialog.openError(editor.getSite().getShell(), null, Messages.cannot_get_attribute_names_for_the_selected_component,
						e.getStatus());
			}

			attributeCombo.setItems(prereqAttributes.toArray(new String[0]));
		}

		attributeCombo.setText(currentAttribute);
		attributeCombo.update();
	}
}
