/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import org.eclipse.buckminster.core.cspec.IGenerator;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.Generator;
import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.ComponentIdentifier;
import org.eclipse.equinox.p2.metadata.Version;

/**
 * @author Thomas Hallgren
 */
public class GeneratorBuilder extends CSpecElementBuilder implements IGenerator {
	private String attribute;

	private String component;

	private String generatesType;

	private Version generatesVersion;

	GeneratorBuilder(CSpecBuilder cspecBuilder) {
		super(cspecBuilder);
	}

	@Override
	public void clear() {
		super.clear();
		component = null;
		attribute = null;
		generatesType = null;
		generatesVersion = null;
	}

	public Generator createGenerator(CSpec cspec) {
		return new Generator(cspec, component, attribute, getGeneratedIdentifier());
	}

	@Override
	public String getAttribute() {
		return attribute;
	}

	@Override
	public String getComponent() {
		return component;
	}

	@Override
	public ComponentIdentifier getGeneratedIdentifier() {
		ComponentIdentifier ci = CommonFactory.eINSTANCE.createComponentIdentifier();
		ci.setId(getName());
		ci.setType(generatesType);
		ci.setVersion(generatesVersion);
		return ci;
	}

	public String getGenerates() {
		return getName();
	}

	public void initFrom(IGenerator generator) {
		ComponentIdentifier ci = generator.getGeneratedIdentifier();
		super.initFrom(ci.getId());
		component = generator.getComponent();
		attribute = generator.getAttribute();
		generatesType = ci.getType();
		generatesVersion = ci.getVersion();
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public void setGeneratesType(String generatesType) {
		this.generatesType = generatesType;
	}

	public void setGeneratesVersion(Version version) {
		this.generatesVersion = version;
	}
}
