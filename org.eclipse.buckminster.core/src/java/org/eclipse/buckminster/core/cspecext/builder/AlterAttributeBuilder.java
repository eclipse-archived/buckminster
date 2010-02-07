/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.builder;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.IAttribute;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspecext.model.AlterAttribute;

/**
 * @author Thomas Hallgren
 */
public abstract class AlterAttributeBuilder {
	private final AttributeBuilder baseBuilder;

	private final HashSet<String> removedHints = new HashSet<String>();

	private final ExpandingProperties<String> alteredHints = new ExpandingProperties<String>();

	private String cspecName = null;

	protected AlterAttributeBuilder(AttributeBuilder baseBuilder) {
		this.baseBuilder = baseBuilder;
	}

	public void addAlteredInstallerHInt(String key, String value) {
		alteredHints.put(key, value);
	}

	public void addRemovedInstallerHint(String key) {
		removedHints.add(key);
	}

	public void clear() {
		removedHints.clear();
		alteredHints.clear();
		baseBuilder.clear();
	}

	public abstract AlterAttribute<?> createAlterAttribute();

	public ExpandingProperties<String> getAlteredHints() {
		return alteredHints;
	}

	public AttributeBuilder getBaseBuilder() {
		return baseBuilder;
	}

	public String getCSpecName() {
		return cspecName;
	}

	public String getName() {
		return baseBuilder.getName();
	}

	public Set<String> getRemovedHints() {
		return removedHints;
	}

	public void setCSpecName(String cspecName) {
		this.cspecName = cspecName;
	}

	IAttribute createBase() {
		return getBaseBuilder().createAttribute();
	}
}
