/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.builder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.model.TopLevelAttribute;
import org.eclipse.buckminster.core.cspecext.model.AlterAttribute;
import org.eclipse.buckminster.core.cspecext.model.AlterDependency;
import org.eclipse.buckminster.core.cspecext.model.CSpecExtension;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 */
public class AlterCSpecBuilder {
	private final CSpecBuilder baseBuilder;

	private final HashSet<String> removedDependencies = new HashSet<String>();

	private final HashSet<String> removedAttributes = new HashSet<String>();

	private final Map<String, String> renamedAttributes = new HashMap<String, String>();

	private final Map<String, AlterAttributeBuilder> alteredAttributes = new HashMap<String, AlterAttributeBuilder>();

	private final Map<String, AlterDependencyBuilder> alteredDependencies = new HashMap<String, AlterDependencyBuilder>();

	private String name;

	public AlterCSpecBuilder(CSpecBuilder baseBuilder) {
		this.baseBuilder = baseBuilder;
	}

	public void addAlterAttribute(AlterAttributeBuilder value) {
		alteredAttributes.put(value.getName(), value);
	}

	public void addAlterDependency(AlterDependencyBuilder value) {
		alteredDependencies.put(value.getName(), value);
	}

	public void addRemoveAttribute(String key) {
		removedAttributes.add(key);
	}

	public void addRemoveDependency(String key) {
		removedDependencies.add(key);
	}

	public void addRenameAttribute(String oldName, String newName) {
		renamedAttributes.put(oldName, newName);
	}

	public void clear() {
		removedAttributes.clear();
		renamedAttributes.clear();
		alteredAttributes.clear();
		removedDependencies.clear();
		alteredDependencies.clear();
		baseBuilder.clear();
		name = null;
	}

	public CSpecExtension createAlteredCSpec() throws CoreException {
		HashMap<String, AlterAttribute<? extends TopLevelAttribute>> alterAttributes = new HashMap<String, AlterAttribute<? extends TopLevelAttribute>>(
				alteredAttributes.size());
		for (Map.Entry<String, AlterAttributeBuilder> entry : alteredAttributes.entrySet())
			alterAttributes.put(entry.getKey(), entry.getValue().createAlterAttribute());

		HashMap<String, AlterDependency> alterDependencies = new HashMap<String, AlterDependency>(alteredDependencies.size());
		for (Map.Entry<String, AlterDependencyBuilder> entry : alteredDependencies.entrySet())
			alterDependencies.put(entry.getKey(), entry.getValue().createAlterDependency());
		return new CSpecExtension(baseBuilder.createCSpec(), removedDependencies, alterDependencies, removedAttributes, renamedAttributes,
				alterAttributes);
	}

	public CSpecBuilder getBaseBuilder() {
		return baseBuilder;
	}

	public final String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
