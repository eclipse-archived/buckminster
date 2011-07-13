/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.cspec.IPrerequisite;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.equinox.p2.metadata.VersionRange;

/**
 * @author Thomas Hallgren
 */
public class PrerequisiteBuilder extends CSpecElementBuilder implements IPrerequisite {
	private String alias;

	private final AttributeBuilder attributeBuilder;

	private String component;

	private String componentType;

	private VersionRange versionRange;

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
		versionRange = null;
		contributor = true;
		excludePattern = null;
		includePattern = null;
		filter = null;
	}

	public Prerequisite createPrerequisite() {
		return new Prerequisite(this);
	}

	@Override
	public String getAlias() {
		return alias;
	}

	@Override
	public String getAttribute() {
		return getAttributeBuilder().getName();
	}

	public AttributeBuilder getAttributeBuilder() {
		return attributeBuilder;
	}

	@Override
	public String getComponentName() {
		return component;
	}

	@Override
	public String getComponentType() {
		return componentType;
	}

	@Override
	public Pattern getExcludePattern() {
		return excludePattern;
	}

	@Override
	public Filter getFilter() {
		return filter;
	}

	@Override
	public Pattern getIncludePattern() {
		return includePattern;
	}

	@Override
	public VersionRange getVersionRange() {
		return versionRange;
	}

	public void initFrom(IPrerequisite prerequisite) {
		super.initFrom(prerequisite.getName());
		alias = prerequisite.getAlias();
		component = prerequisite.getComponentName();
		componentType = prerequisite.getComponentType();
		versionRange = prerequisite.getVersionRange();
		contributor = prerequisite.isContributor();
		excludePattern = prerequisite.getExcludePattern();
		includePattern = prerequisite.getIncludePattern();
		filter = prerequisite.getFilter();
	}

	@Override
	public boolean isContributor() {
		return contributor;
	}

	@Override
	public boolean isExternal() {
		return component != null;
	}

	@Override
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

	public void setVersionRange(VersionRange versionRange) {
		this.versionRange = versionRange;
	}

	@Override
	public String toString() {
		String name = getName();
		if (name == null)
			name = ""; //$NON-NLS-1$

		if (component == null)
			return name;

		StringBuilder bld = new StringBuilder();
		bld.append(component);
		if (componentType != null) {
			bld.append(':');
			bld.append(componentType);
		}
		bld.append('#');
		bld.append(name);
		if (versionRange != null) {
			bld.append('/');
			bld.append(VersionHelper.getHumanReadable(versionRange));
		}
		return bld.toString();
	}

	void finalWrapUp(List<ComponentRequestBuilder> dependencies) {
		if (componentType != null || versionRange != null) {
			// Count number of hits on this type and number of hits total
			int hitsTotal = 0;
			int hitsOnType = 0;
			int hitsOnRange = 0;
			int idx = dependencies.size();
			while (--idx >= 0) {
				ComponentRequestBuilder dep = dependencies.get(idx);
				if (component.equals(dep.getName())) {
					++hitsTotal;
					if (componentType != null) {
						if (componentType.equals(dep.getComponentTypeID())) {
							++hitsOnType;
							if (versionRange != null) {
								if (dep.getVersionRange() != null && dep.getVersionRange().intersect(versionRange) != null) {
									++hitsOnRange;
								}
							}
						}
						continue;
					}
					if (versionRange != null) {
						if (dep.getVersionRange() != null && dep.getVersionRange().intersect(versionRange) != null)
							++hitsOnRange;
						else
							continue;
					}

				}
			}
			// Remove unnecessary qualification
			if (hitsOnType == hitsTotal)
				componentType = null;
			if (hitsOnRange == hitsTotal)
				versionRange = null;
		}
	}
}
