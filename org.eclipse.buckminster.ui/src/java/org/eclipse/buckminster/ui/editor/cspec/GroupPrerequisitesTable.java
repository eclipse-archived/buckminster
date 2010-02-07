/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.editor.cspec;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.buckminster.core.cspec.builder.PrerequisiteBuilder;
import org.eclipse.buckminster.core.cspec.builder.TopLevelAttributeBuilder;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.osgi.filter.FilterFactory;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.buckminster.ui.general.editor.simple.IWidgetin;
import org.eclipse.swt.widgets.Composite;
import org.osgi.framework.InvalidSyntaxException;

/**
 * PrerequisitesTable without Alias field
 * 
 * @author Karel Brezina
 * 
 */
public class GroupPrerequisitesTable extends PrerequisitesTable {

	public GroupPrerequisitesTable(CSpecEditor editor, AttributesTable<?> parentAttributesTable, List<PrerequisiteBuilder> data,
			TopLevelAttributeBuilder attributeBuilder, boolean readOnly) {
		super(editor, parentAttributesTable, data, attributeBuilder, readOnly);
	}

	@Override
	public String[] getColumnHeaders() {
		return new String[] { Messages.component, Messages.name, Messages.contributor, Messages.filter, Messages.include_pattern,
				Messages.exclude_pattern };
	}

	@Override
	public int[] getColumnWeights() {
		return new int[] { 20, 10, 0, 0, 0, 0 };
	}

	@Override
	public IWidgetin getWidgetin(Composite parent, int idx, Object value) {
		if (idx < 2)
			return super.getWidgetin(parent, idx, value);

		// Alias is removed here
		return super.getWidgetin(parent, ++idx, value);
	}

	@Override
	public Object[] toRowArray(PrerequisiteBuilder t) {
		return new Object[] { t.getComponentName(), t.getName(), Boolean.valueOf(t.isContributor()), TextUtils.notNullString(t.getFilter()),
				TextUtils.notNullString(t.getIncludePattern()), TextUtils.notNullString(t.getExcludePattern()) };
	}

	@Override
	public void updateRowClass(PrerequisiteBuilder builder, Object[] args) throws ValidatorException {
		builder.setComponentName(TextUtils.notEmptyString((String) args[0]));
		builder.setName(TextUtils.notEmptyString((String) args[1]));
		builder.setContributor(((Boolean) args[2]).booleanValue());

		String filterStr = TextUtils.notEmptyString((String) args[3]);
		if (filterStr != null) {
			try {
				builder.setFilter(FilterFactory.newInstance(filterStr));
			} catch (InvalidSyntaxException e) {
				throw new ValidatorException(e.getMessage());
			}
		} else
			builder.setFilter(null);

		String includePatternStr = TextUtils.notEmptyString((String) args[4]);
		if (includePatternStr != null) {
			try {
				builder.setIncludePattern(Pattern.compile(includePatternStr));
			} catch (PatternSyntaxException e) {
				throw new ValidatorException(e.getMessage());
			}
		} else
			builder.setIncludePattern(null);

		String excludePatternStr = TextUtils.notEmptyString((String) args[5]);
		if (excludePatternStr != null) {
			try {
				builder.setExcludePattern(Pattern.compile(excludePatternStr));
			} catch (PatternSyntaxException e) {
				throw new ValidatorException(e.getMessage());
			}
		} else
			builder.setExcludePattern(null);
	}
}
