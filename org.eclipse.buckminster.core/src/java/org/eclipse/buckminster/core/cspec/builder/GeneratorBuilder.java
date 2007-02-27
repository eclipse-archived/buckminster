/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.Generator;

/**
 * @author Thomas Hallgren
 */
public class GeneratorBuilder extends CSpecElementBuilder
{
	private String m_attribute;
	private String m_component;

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
	}

	public Generator createGenerator(CSpec cspec)
	{
		return new Generator(cspec, m_component, m_attribute, getName());
	}

	public String getComponent()
	{
		return m_component;
	}

	public String getAttribute()
	{
		return m_attribute;
	}

	public String getGenerates()
	{
		return getName();
	}

	public void initFrom(Generator generator)
	{
		super.initFrom(generator);
		m_component = generator.getComponent();
		m_attribute = generator.getAttribute();
	}

	public void setGenerates(String generates)
	{
		setName(generates);
	}

	public void setAttribute(String attribute)
	{
		m_attribute = attribute;
	}

	public void setComponent(String component)
	{
		m_component = component;
	}
}
