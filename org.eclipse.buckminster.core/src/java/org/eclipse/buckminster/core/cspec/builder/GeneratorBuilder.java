/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import org.eclipse.buckminster.core.cspec.IComponentIdentifier;
import org.eclipse.buckminster.core.cspec.IGenerator;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.Generator;
import org.eclipse.equinox.internal.provisional.p2.metadata.Version;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class GeneratorBuilder extends CSpecElementBuilder implements IGenerator
{
	private String m_attribute;

	private String m_component;

	private String m_generatesType;

	private Version m_generatesVersion;

	GeneratorBuilder(CSpecBuilder cspecBuilder)
	{
		super(cspecBuilder);
	}

	@Override
	public void clear()
	{
		super.clear();
		m_component = null;
		m_attribute = null;
		m_generatesType = null;
		m_generatesVersion = null;
	}

	public Generator createGenerator(CSpec cspec)
	{
		return new Generator(cspec, m_component, m_attribute, getGeneratedIdentifier());
	}

	public String getAttribute()
	{
		return m_attribute;
	}

	public String getComponent()
	{
		return m_component;
	}

	public ComponentIdentifier getGeneratedIdentifier()
	{
		return new ComponentIdentifier(getName(), m_generatesType, m_generatesVersion);
	}

	public String getGenerates()
	{
		return getName();
	}

	public void initFrom(IGenerator generator)
	{
		IComponentIdentifier ci = generator.getGeneratedIdentifier();
		super.initFrom(ci.getName());
		m_component = generator.getComponent();
		m_attribute = generator.getAttribute();
		m_generatesType = ci.getComponentTypeID();
		m_generatesVersion = ci.getVersion();
	}

	public void setAttribute(String attribute)
	{
		m_attribute = attribute;
	}

	public void setComponent(String component)
	{
		m_component = component;
	}

	public void setGeneratesType(String generatesType)
	{
		m_generatesType = generatesType;
	}

	public void setGeneratesVersion(Version version)
	{
		m_generatesVersion = version;
	}
}
