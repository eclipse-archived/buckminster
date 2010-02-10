/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.cspec.IPrerequisite;
import org.eclipse.buckminster.core.cspec.builder.PrerequisiteBuilder;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class Prerequisite extends NamedElement implements IPrerequisite {
	public static final String ATTR_ALIAS = "alias"; //$NON-NLS-1$

	public static final String ATTR_COMPONENT = "component"; //$NON-NLS-1$

	public static final String ATTR_COMPONENT_TYPE = "componentType"; //$NON-NLS-1$

	public static final String ATTR_CONTRIBUTOR = "contributor"; //$NON-NLS-1$

	public static final String ATTR_EXCLUDE_PATTERN = "excludePattern"; //$NON-NLS-1$

	public static final String ATTR_INCLUDE_PATTERN = "includePattern"; //$NON-NLS-1$

	public static final String ATTR_FILTER = "filter"; //$NON-NLS-1$

	public static final String TAG = "attribute"; //$NON-NLS-1$

	public static boolean isMatch(String component, String attribute, Pattern excludePattern, Pattern includePattern) {
		CharSequence tmp;
		if (attribute == null && component == null)
			return false;

		if (excludePattern == null && includePattern == null)
			return true;

		if (attribute == null)
			tmp = component;
		else {
			StringBuilder bld = new StringBuilder();
			if (component != null)
				bld.append(component);
			bld.append('#');
			bld.append(attribute);
			tmp = bld;
		}
		if (excludePattern != null) {
			Matcher m = excludePattern.matcher(tmp);
			if (m.matches())
				return false;
		}
		if (includePattern != null) {
			Matcher m = includePattern.matcher(tmp);
			if (!m.matches())
				return false;
		}
		return true;
	}

	private final String alias;

	private final String componentName;

	private final String componentType;

	private final boolean contributor;

	private final Pattern excludePattern;

	private final Pattern includePattern;

	private final Filter filter;

	public Prerequisite(PrerequisiteBuilder bld) {
		super(bld.getName());
		alias = bld.getAlias();
		contributor = bld.isContributor();
		componentName = bld.getComponentName();
		componentType = bld.getComponentType();
		excludePattern = bld.getExcludePattern();
		includePattern = bld.getIncludePattern();
		filter = bld.getFilter();
	}

	@Override
	public final String getAlias() {
		return alias;
	}

	@Override
	public final String getAttribute() {
		return getName();
	}

	@Override
	public final String getComponentName() {
		return componentName;
	}

	@Override
	public final String getComponentType() {
		return componentType;
	}

	@Override
	public String getDefaultTag() {
		return TAG;
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

	public Attribute getReferencedAttribute(CSpec ownerCSpec, IModelCache ctx) throws CoreException {
		return (filter == null || filter.match(ctx.getProperties())) ? ownerCSpec.getReferencedAttribute(componentName, componentType,
				getName(), ctx) : null;
	}

	public CSpec getReferencedCSpec(CSpec ownerCSpec, IModelCache ctx) throws CoreException {
		return (filter == null || filter.match(ctx.getProperties())) ? ownerCSpec.getReferencedCSpec(componentName, componentType, ctx)
				: null;
	}

	@Override
	public boolean isContributor() {
		return contributor;
	}

	public boolean isEnabled(IModelCache cache, CSpec cspec) throws CoreException {
		if (!(filter == null || filter.match(cache.getProperties())))
			return false;

		return isExternal() ? (getReferencedAttribute(cspec, cache) != null) : cspec.getAttribute(getAttribute()).isEnabled(cache);
	}

	@Override
	public boolean isExternal() {
		return componentName != null;
	}

	@Override
	public boolean isMatch(String component, String attribute) {
		return isMatch(component, attribute, excludePattern, includePattern);
	}

	public boolean isPatternFilter() {
		return excludePattern != null || includePattern != null;
	}

	@Override
	public final String toString() {
		if (componentName == null)
			return getName();

		return componentName + '#' + getAttribute();
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) {
		super.addAttributes(attrs);
		if (alias != null)
			Utils.addAttribute(attrs, ATTR_ALIAS, alias);
		if (!contributor)
			Utils.addAttribute(attrs, ATTR_CONTRIBUTOR, "false"); //$NON-NLS-1$
		if (excludePattern != null)
			Utils.addAttribute(attrs, ATTR_EXCLUDE_PATTERN, excludePattern.toString());
		if (includePattern != null)
			Utils.addAttribute(attrs, ATTR_INCLUDE_PATTERN, includePattern.toString());
		if (componentName != null)
			Utils.addAttribute(attrs, ATTR_COMPONENT, componentName);
		if (componentType != null)
			Utils.addAttribute(attrs, ATTR_COMPONENT_TYPE, componentType);
		if (filter != null)
			Utils.addAttribute(attrs, ATTR_FILTER, filter.toString());
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException {
	}
}
