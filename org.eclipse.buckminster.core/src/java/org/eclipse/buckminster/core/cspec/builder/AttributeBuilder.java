/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import java.util.Collections;
import java.util.List;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.IAttribute;
import org.eclipse.buckminster.core.cspec.IPrerequisite;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 */
public class AttributeBuilder extends CSpecElementBuilder implements IAttribute {
	private Documentation documentation;

	private Filter filter = null;

	AttributeBuilder(CSpecBuilder cspecBuilder) {
		super(cspecBuilder);
	}

	@Override
	public void clear() {
		super.clear();
		documentation = null;
		filter = null;
	}

	public Attribute createAttribute() {
		return new Attribute(this);
	}

	@Override
	public AttributeBuilder getAttributeBuilder(CSpecBuilder specBuilder) {
		return specBuilder == getCSpecBuilder() ? this : new AttributeBuilder(specBuilder);
	}

	@Override
	public Documentation getDocumentation() {
		return documentation;
	}

	@Override
	public Filter getFilter() {
		return filter;
	}

	public IPath getPrerequisiteRebase() {
		return null;
	}

	@Override
	public List<? extends IPrerequisite> getPrerequisites() {
		return Collections.emptyList();
	}

	@Override
	public String getQualifiedName() {
		return getCSpecBuilder().getComponentIdentifier().toString() + '#' + getName();
	}

	public void initFrom(IAttribute attribute) {
		super.initFrom(attribute.getName());
		documentation = attribute.getDocumentation();
		filter = attribute.getFilter();
	}

	@Override
	public boolean isPublic() {
		return true;
	}

	public void setDocumentation(Documentation documentation) {
		this.documentation = documentation;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}
}
