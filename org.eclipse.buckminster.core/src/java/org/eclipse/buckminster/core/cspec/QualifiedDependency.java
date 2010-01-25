/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.query.IAdvisorNode;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.p2.metadata.VersionRange;

public class QualifiedDependency
{
	private final ComponentRequest m_request;

	private final Set<String> m_attributes;

	public QualifiedDependency(ComponentRequest request, Collection<String> attributes)
	{
		m_request = request;
		m_attributes = Utils.createUnmodifiableSet(attributes);
	}

	public QualifiedDependency applyAdvice(IAdvisorNode advice)
	{
		if(advice == null)
			return this;

		boolean change = false;
		ComponentRequest request = m_request;
		VersionRange dsg = advice.getVersionOverride();
		if(dsg != null)
		{
			change = true;
			request = new ComponentRequest(request.getName(), request.getComponentTypeID(), dsg);
		}

		Collection<String> attrs = advice.getAttributes();
		if(attrs.size() > 0)
		{
			change = true;
			if(advice.isPrune() && m_attributes.size() > 0 && !m_attributes.containsAll(attrs))
			{
				HashSet<String> pruned = new HashSet<String>();
				for(String attrName : attrs)
					if(m_attributes.contains(attrName))
						pruned.add(attrName);

				if(pruned.size() == 0)
					//
					// We don't want anything from this cspec
					//
					return null;

				attrs = pruned;
			}
		}
		else
			attrs = m_attributes;

		return change
				? new QualifiedDependency(request, attrs)
				: this;
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o)
			return true;
		if(!(o instanceof QualifiedDependency))
			return false;

		QualifiedDependency that = (QualifiedDependency)o;
		return m_request.equals(that.m_request) && m_attributes.equals(that.m_attributes);
	}

	public final Set<String> getAttributeNames()
	{
		return m_attributes;
	}

	public final ComponentRequest getRequest()
	{
		return m_request;
	}

	public boolean hasAllAttributes(Collection<String> attributes)
	{
		return m_attributes.containsAll(attributes);
	}

	public boolean hasAllAttributes(String[] attributes)
	{
		return hasAllAttributes(Arrays.asList(attributes));
	}

	@Override
	public int hashCode()
	{
		return m_request.hashCode() * 31 + m_attributes.hashCode();
	}

	/**
	 * Merges the version designator and the attributes of the new dependency with the current one. The method will
	 * return this instance if the merge is a no-op.
	 * 
	 * @param newQDep
	 *            the new qualified depenency
	 * @return This instance or a new instance if modifications where necessary.
	 * @throws CoreException
	 *             if the qualification is in conflict with the previously defined dependency with respect to its
	 *             version designator
	 */
	public QualifiedDependency mergeDependency(QualifiedDependency newQDep) throws CoreException
	{
		Set<String> attrs = newQDep.getAttributeNames();
		ComponentRequest newRequest = m_request.mergeDesignator(newQDep.getRequest());
		if(newRequest == m_request && hasAllAttributes(attrs))
			return this;

		Set<String> allAttrs;
		if(m_attributes.size() == 0)
			allAttrs = attrs;
		else if(attrs.size() == 0)
			allAttrs = m_attributes;
		else
		{
			allAttrs = new HashSet<String>(m_attributes);
			allAttrs.addAll(attrs);
		}
		return new QualifiedDependency(newRequest, allAttrs);
	}
}
