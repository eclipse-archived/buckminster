/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.builder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.eclipse.buckminster.core.cspec.IPrerequisite;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.cspec.model.PrerequisiteAlreadyDefinedException;
import org.eclipse.buckminster.core.cspecext.model.AlterAction;
import org.eclipse.buckminster.core.cspecext.model.AlterAttribute;
import org.eclipse.buckminster.model.common.util.ExpandingProperties;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 */
public class AlterActionBuilder extends AlterAttributeBuilder {
	private final HashMap<String, Prerequisite> alteredPrerequisites = new HashMap<String, Prerequisite>();

	private final HashSet<String> removedPrerequisites = new HashSet<String>();

	private final ExpandingProperties alteredActorProperties = new ExpandingProperties();

	private final HashSet<String> removedActorProperties = new HashSet<String>();

	private final ExpandingProperties alteredProperties = new ExpandingProperties();

	private final HashSet<String> removedProperties = new HashSet<String>();

	private final HashSet<IPath> removedPaths = new HashSet<IPath>();

	public AlterActionBuilder(ActionBuilder baseBuilder) {
		super(baseBuilder);
	}

	public void addAlterActorProperty(String key, String value) {
		alteredActorProperties.put(key, value);
	}

	public void addAlterPrerequisite(Prerequisite value) throws PrerequisiteAlreadyDefinedException {
		String key = value.toString();
		if (alteredPrerequisites.containsKey(key))
			throw new PrerequisiteAlreadyDefinedException(getCSpecName(), getName(), key);

		List<? extends IPrerequisite> basePreqs = ((ActionBuilder) getBaseBuilder()).getPrerequisitesBuilder().getPrerequisites();
		if (GroupBuilder.indexOfPrerequisite(basePreqs, key) >= 0)
			throw new PrerequisiteAlreadyDefinedException(getCSpecName(), getName(), key);

		alteredPrerequisites.put(key, value);
	}

	public void addAlterProperty(String key, String value) {
		alteredProperties.put(key, value);
	}

	public void addRemoveActorProperty(String key) {
		removedActorProperties.add(key);
	}

	public void addRemovePrerequisite(String key) {
		removedPrerequisites.add(key);
	}

	public void addRemoveProductPath(IPath path) {
		removedPaths.add(path);
	}

	public void addRemoveProperty(String key) {
		removedProperties.add(key);
	}

	@Override
	public void clear() {
		super.clear();
		alteredPrerequisites.clear();
		removedPrerequisites.clear();
		alteredActorProperties.clear();
		removedActorProperties.clear();
		alteredProperties.clear();
		removedProperties.clear();
		removedPaths.clear();
	}

	@Override
	public AlterAttribute<?> createAlterAttribute() {
		return new AlterAction(createBase(), getRemovedHints(), getAlteredHints(), removedPrerequisites, alteredPrerequisites,
				removedActorProperties, alteredActorProperties, removedProperties, alteredProperties, removedPaths);
	}

	public ExpandingProperties getAlterActorProperties() {
		return alteredActorProperties;
	}

	public ExpandingProperties getAlterProperties() {
		return alteredProperties;
	}

	@Override
	Action createBase() {
		return ((ActionBuilder) getBaseBuilder()).createAttribute();
	}
}
