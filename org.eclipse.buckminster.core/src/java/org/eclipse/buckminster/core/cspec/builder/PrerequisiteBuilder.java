/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.cspec.IPrerequisite;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.osgi.filter.Filter;

/**
 * @author Thomas Hallgren
 */
public class PrerequisiteBuilder extends CSpecElementBuilder implements IPrerequisite {
	private String alias;

	private final AttributeBuilder attributeBuilder;

	private String component;

	private String componentType;

	private boolean contributor = true;

	private Pattern excludePattern;

	private Pattern includePattern;

	private Filter filter;

	PrerequisiteBuilder(AttributeBuilder attributeBuilder) {
		super(attributeBuilder.getCSpecBuilder());
		this.attributeBuilder = attributeBuilder;
	}

	@Override
	public void clear() {
		super.clear();
		alias = null;
		component = null;
		componentType = null;
		contributor = true;
		excludePattern = null;
		includePattern = null;
		filter = null;
	}

	public Prerequisite createPrerequisite() {
		return new Prerequisite(this);
	}

	public String getAlias() {
		return alias;
	}

	public String getAttribute() {
		return getAttributeBuilder().getName();
	}

	public AttributeBuilder getAttributeBuilder() {
		return attributeBuilder;
	}

	public String getComponentName() {
		return component;
	}

	public String getComponentType() {
		return componentType;
	}

	public Pattern getExcludePattern() {
		return excludePattern;
	}

	public Filter getFilter() {
		return filter;
	}

	public Pattern getIncludePattern() {
		return includePattern;
	}

	public void initFrom(IPrerequisite prerequisite) {
		super.initFrom(prerequisite.getName());
		alias = prerequisite.getAlias();
		component = prerequisite.getComponentName();
		componentType = prerequisite.getComponentType();
		contributor = prerequisite.isContributor();
		excludePattern = prerequisite.getExcludePattern();
		includePattern = prerequisite.getIncludePattern();
		filter = prerequisite.getFilter();
	}

	public boolean isContributor() {
		return contributor;
	}

	public boolean isExternal() {
		return component != null;
	}

	public boolean isMatch(String componentName, String attribute) {
		return Prerequisite.isMatch(componentName, attribute, excludePattern, includePattern);
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public void setComponentName(String component) {
		this.component = component;
	}

	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}

	public void setContributor(boolean contributor) {
		this.contributor = contributor;
	}

	public void setExcludePattern(Pattern excludePattern) {
		this.excludePattern = excludePattern;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public void setIncludePattern(Pattern includePattern) {
		this.includePattern = includePattern;
	}

	@Override
	public String toString() {
		if (component == null)
			return getName();

		StringBuilder bld = new StringBuilder();
		bld.append(component);
		if (componentType != null) {
			bld.append(':');
			bld.append(componentType);
		}
		bld.append('#');
		bld.append(getName());
		return bld.toString();
	}

	void finalWrapUp(Map<String, ComponentRequestBuilder> dependencies) {
		if (componentType != null && dependencies.containsKey(component))
			componentType = null;
	}
}
