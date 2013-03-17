/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.buckminster.core.cspec.IAttribute;
import org.eclipse.buckminster.core.cspec.IGroup;
import org.eclipse.buckminster.core.cspec.IPrerequisite;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.Group;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.cspec.model.PrerequisiteAlreadyDefinedException;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 */
public class GroupBuilder extends TopLevelAttributeBuilder implements IGroup {
	public static int indexOfPrerequisite(List<? extends IPrerequisite> prerequisites, String prerequisiteKey) {
		int idx = prerequisites.size();
		while (--idx >= 0)
			if (prerequisites.get(idx).toString().equals(prerequisiteKey))
				return idx;
		return -1;
	}

	private final ArrayList<PrerequisiteBuilder> prerequisites = new ArrayList<PrerequisiteBuilder>();

	private IPath rebase;

	GroupBuilder(CSpecBuilder cspecBuilder) {
		super(cspecBuilder);
	}

	@Override
	public PrerequisiteBuilder addPrerequisite(PrerequisiteBuilder prerequisite) throws PrerequisiteAlreadyDefinedException {
		String key = prerequisite.toString();
		if (indexOfPrerequisite(prerequisites, key) >= 0)
			throw new PrerequisiteAlreadyDefinedException(getCSpecName(), getName(), key);
		prerequisites.add(prerequisite);
		return prerequisite;
	}

	public void addSelfRequirement() throws PrerequisiteAlreadyDefinedException {
		addLocalPrerequisite(CSpec.SELF_ARTIFACT);
	}

	@Override
	public void clear() {
		super.clear();
		prerequisites.clear();
		rebase = null;
	}

	@Override
	public Group createAttribute() {
		return new Group(this);
	}

	@Override
	public AttributeBuilder getAttributeBuilder(CSpecBuilder specBuilder) {
		return specBuilder == getCSpecBuilder() ? this : new GroupBuilder(specBuilder);
	}

	public PrerequisiteBuilder getPrerequisite(String prerequisteName) {
		int idx = indexOfPrerequisite(prerequisites, prerequisteName);
		return (idx < 0) ? null : prerequisites.get(idx);
	}

	public List<Prerequisite> getPrerequisiteList() {
		int top = (prerequisites == null) ? 0 : prerequisites.size();
		if (top == 0)
			return Collections.emptyList();

		ArrayList<Prerequisite> bld = new ArrayList<Prerequisite>(top);
		for (int idx = 0; idx < top; ++idx)
			bld.add(prerequisites.get(idx).createPrerequisite());
		return bld;
	}

	@Override
	public IPath getPrerequisiteRebase() {
		return rebase;
	}

	@Override
	public List<PrerequisiteBuilder> getPrerequisites() {
		return prerequisites;
	}

	@Override
	public void initFrom(IAttribute attribute) {
		IGroup group = (IGroup) attribute;
		super.initFrom(group);
		for (IPrerequisite pq : group.getPrerequisites()) {
			PrerequisiteBuilder pb = createPrerequisiteBuilder();
			pb.initFrom(pq);
			prerequisites.add(pb);
		}
		rebase = group.getPrerequisiteRebase();
	}

	@Override
	public void removePrerequisite(String prerequisteName) {
		int idx = indexOfPrerequisite(prerequisites, prerequisteName);
		if (idx >= 0)
			prerequisites.remove(idx);
	}

	public void setPrerequisiteRebase(IPath rebase) {
		this.rebase = rebase == null ? null : rebase.addTrailingSeparator();
	}

	void finalWrapUp(List<ComponentRequestBuilder> dependencies) {
		for (PrerequisiteBuilder pq : prerequisites)
			pq.finalWrapUp(dependencies);
	}
}
