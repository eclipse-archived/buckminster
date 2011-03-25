/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import org.eclipse.buckminster.core.cspec.IAttribute;
import org.eclipse.buckminster.core.cspec.IComponentRequest;
import org.eclipse.buckminster.core.cspec.IPrerequisite;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.PrerequisiteAlreadyDefinedException;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.equinox.p2.metadata.VersionRange;

/**
 * @author Thomas Hallgren
 */
public abstract class TopLevelAttributeBuilder extends AttributeBuilder {
	private boolean publ = false;

	TopLevelAttributeBuilder(CSpecBuilder cspecBuilder) {
		super(cspecBuilder);
	}

	public final PrerequisiteBuilder addExternalPrerequisite(IComponentRequest request, String attr) throws PrerequisiteAlreadyDefinedException {
		return addPrerequisite(createPrerequisite(request.getName(), request.getComponentTypeID(), request.getVersionRange(), attr, null, null));
	}

	public final PrerequisiteBuilder addLocalPrerequisite(AttributeBuilder attr) throws PrerequisiteAlreadyDefinedException {
		return addLocalPrerequisite(attr.getName());
	}

	public final PrerequisiteBuilder addLocalPrerequisite(String attr) throws PrerequisiteAlreadyDefinedException {
		return addPrerequisite(createPrerequisite(null, null, null, attr, null, null));
	}

	public final PrerequisiteBuilder addLocalPrerequisite(String attr, String alias) throws PrerequisiteAlreadyDefinedException {
		return addPrerequisite(createPrerequisite(null, null, null, attr, alias, null));
	}

	public final PrerequisiteBuilder addLocalPrerequisite(String attr, String alias, Filter filter) throws PrerequisiteAlreadyDefinedException {
		return addPrerequisite(createPrerequisite(null, null, null, attr, alias, filter));
	}

	public PrerequisiteBuilder addPrerequisite(PrerequisiteBuilder prerequisite) throws PrerequisiteAlreadyDefinedException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		super.clear();
		publ = false;
	}

	@Override
	public abstract Attribute createAttribute();

	public PrerequisiteBuilder createPrerequisiteBuilder() {
		return new PrerequisiteBuilder(this);
	}

	@Override
	public void initFrom(IAttribute attribute) {
		super.initFrom(attribute);
		publ = attribute.isPublic();
	}

	@Override
	public boolean isPublic() {
		return publ;
	}

	public void removePrerequisite(IPrerequisite pq) {
		removePrerequisite(pq.toString());
	}

	public void removePrerequisite(String prerequisiteName) {
		throw new UnsupportedOperationException();
	}

	public void setPublic(boolean flag) {
		publ = flag;
	}

	private PrerequisiteBuilder createPrerequisite(String component, String type, VersionRange versionRange, String name, String alias, Filter filter) {
		PrerequisiteBuilder bld = createPrerequisiteBuilder();
		bld.setComponentName(component);
		bld.setComponentType(type);
		bld.setVersionRange(versionRange);
		bld.setName(name);
		bld.setAlias(alias);
		bld.setFilter(filter);
		return bld;
	}
}
