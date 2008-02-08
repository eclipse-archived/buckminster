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
import java.util.Map;

import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.model.AttributeAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.model.DependencyAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.model.TopLevelAttribute;
import org.eclipse.buckminster.core.cspecext.model.AlterAttribute;
import org.eclipse.buckminster.core.cspecext.model.AlterDependency;
import org.eclipse.buckminster.core.cspecext.model.CSpecExtension;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 */
public class AlterCSpecBuilder
{
	private final CSpecBuilder m_baseBuilder;
	private final HashSet<String> m_removedDependencies = new HashSet<String>();
	private final HashSet<String> m_removedAttributes = new HashSet<String>();
	private final Map<String, AlterAttributeBuilder> m_alteredAttributes = new HashMap<String, AlterAttributeBuilder>();
	private final Map<String, AlterDependencyBuilder> m_alteredDependencies = new HashMap<String, AlterDependencyBuilder>();
	private String m_name;

	public AlterCSpecBuilder(CSpecBuilder baseBuilder)
	{
		m_baseBuilder = baseBuilder;
	}

	public void clear()
	{
		m_removedAttributes.clear();
		m_alteredAttributes.clear();
		m_removedDependencies.clear();
		m_alteredDependencies.clear();
		m_baseBuilder.clear();
		m_name = null;
	}

	public CSpecExtension createAlteredCSpec() throws CoreException
	{
		HashMap<String,AlterAttribute<? extends TopLevelAttribute>> alterAttributes = new HashMap<String, AlterAttribute<? extends TopLevelAttribute>>(m_alteredAttributes.size());
		for(Map.Entry<String,AlterAttributeBuilder> entry : m_alteredAttributes.entrySet())
			alterAttributes.put(entry.getKey(), entry.getValue().createAlterAttribute());
		
		HashMap<String,AlterDependency> alterDependencies = new HashMap<String, AlterDependency>(m_alteredDependencies.size());
		for(Map.Entry<String, AlterDependencyBuilder> entry : m_alteredDependencies.entrySet())
			alterDependencies.put(entry.getKey(), entry.getValue().createAlterDependency());
		return new CSpecExtension(m_baseBuilder.createCSpec(), m_removedDependencies, alterDependencies, m_removedAttributes, alterAttributes);
	}

	public void addRemoveDependency(String key)
	{
		m_removedDependencies.add(key);
	}

	public void addAlterDependency(AlterDependencyBuilder value) throws DependencyAlreadyDefinedException
	{
		String key = value.getName();
		if(m_alteredDependencies.containsKey(key))
			throw new DependencyAlreadyDefinedException(m_name, key);
		m_alteredDependencies.put(key, value);
	}

	public void addRemoveAttribute(String key)
	{
		m_removedAttributes.add(key);
	}

	public void addAlterAttribute(AlterAttributeBuilder value) throws AttributeAlreadyDefinedException
	{
		String key = value.getName();
		if(m_alteredAttributes.containsKey(key))
			throw new AttributeAlreadyDefinedException(m_name, key);
		m_alteredAttributes.put(key, value);
	}

	public CSpecBuilder getBaseBuilder()
	{
		return m_baseBuilder;
	}
	
	public final String getName()
	{
		return m_name;
	}
	
	public void setName(String name)
	{
		m_name = name;
	}
}
