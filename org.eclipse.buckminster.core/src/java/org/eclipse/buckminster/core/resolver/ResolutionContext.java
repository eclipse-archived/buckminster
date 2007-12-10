/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.resolver;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.Generator;
import org.eclipse.buckminster.core.helpers.MapUnion;
import org.eclipse.buckminster.core.metadata.model.GeneratorNode;
import org.eclipse.buckminster.core.query.model.AdvisorNode;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.core.runtime.IStatus;

/**
 * @author Thomas Hallgren
 */
public class ResolutionContext extends RMContext
{
	private final ComponentQuery m_componentQuery;
	private HashMap<String,GeneratorNode> m_generators;
	private final ResolutionContext m_parentContext;

	public ResolutionContext(ComponentQuery componentQuery)
	{
		this(componentQuery, null);
	}

	public ResolutionContext(ComponentQuery componentQuery, ResolutionContext parentContext)
	{
		super(parentContext == null ? componentQuery.getGlobalProperties() : new MapUnion<String, String>(componentQuery.getGlobalProperties(), parentContext));
		m_componentQuery = componentQuery;
		m_parentContext = parentContext;
	}

	@Override
	public synchronized void addException(ComponentRequest request, IStatus resolveStatus)
	{
		if(m_parentContext != null)
			m_parentContext.addException(request, resolveStatus);
		else
			super.addException(request, resolveStatus);
	}

	@Override
	public synchronized void clearStatus()
	{
		if(m_parentContext != null)
			m_parentContext.clearStatus();
		else
			super.clearStatus();
	}

	@Override
	public synchronized Map<String,String> getBindingProperties()
	{
		return (m_parentContext != null)
			? m_parentContext.getBindingProperties()
			: super.getBindingProperties();
	}

	@Override
	public ComponentQuery getComponentQuery()
	{
		return m_componentQuery;
	}

	public GeneratorNode getGeneratorNode(String name)
	{
		if(m_generators != null)
		{
			GeneratorNode node = m_generators.get(name);
			if(node != null)
				return node;
		}
		return (m_parentContext == null) ? null : m_parentContext.getGeneratorNode(name);
	}

	@Override
	public Map<String, String> getProperties(ComponentName cName)
	{
		AdvisorNode node;
		Map<String,String> p = super.getProperties(cName);
		if(m_parentContext != null)
		{
			node = m_parentContext.getComponentQuery().getMatchingNode(cName);
			if(node != null)
				p.putAll(node.getProperties());
		}
		node = getComponentQuery().getMatchingNode(cName);
		if(node != null)
			p.putAll(node.getProperties());
		return p;
	}

	@Override
	public synchronized IStatus getStatus()
	{
		return (m_parentContext != null)
			? m_parentContext.getStatus()
			: super.getStatus();
	}

	@Override
	public synchronized Map<UUID,Object> getUserCache()
	{
		return (m_parentContext != null)
			? m_parentContext.getUserCache()
			: super.getUserCache();
	}

	@Override
	public synchronized boolean isContinueOnError()
	{
		return (m_parentContext != null)
			? m_parentContext.isContinueOnError()
			: super.isContinueOnError();
	}

	@Override
	public void setContinueOnError(boolean flag)
	{
		if(m_parentContext != null)
			m_parentContext.setContinueOnError(flag);
		else
			super.setContinueOnError(flag);
	}

	public void setGenerators(Collection<Generator> generators)
	{
		for(Generator generator : generators)
		{
			if(m_generators == null)
				m_generators = new HashMap<String, GeneratorNode>();
			m_generators.put(generator.getGenerates(), new GeneratorNode(generator));
		}
	}
}
