/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.helpers.FilterUtils;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.osgi.framework.Filter;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class Attribute extends NamedElement implements Cloneable
{
	public static final String TAG = "define";

	public static final String ATTR_FILTER = "filter";
	
	private final Documentation m_documentation;

	private final Filter m_filter;

	private CSpec m_cspec;

	public Attribute(AttributeBuilder builder)
	{
		super(builder.getName());
		m_documentation = builder.getDocumentation();
		m_filter = builder.getFilter();
	}

	Attribute(String name)
	{
		super(name);
		m_documentation = null;
		m_filter = null;
	}

	/**
	 * Create a copy of this Attribute with the owner set to <code>null</code>.
	 * @return A copy that has no cspec owner assigned.
	 */
	public Attribute copy()
	{
		Attribute copy;
		try
		{
			copy = (Attribute)clone();
		}
		catch(CloneNotSupportedException e)
		{
			throw new RuntimeException(e);
		}
		copy.m_cspec = null;
		return copy;
	}

	public void addDynamicProperties(Map<String, String> properties)
	throws CoreException
	{
	}

	public AttributeBuilder getAttributeBuilder(CSpecBuilder cspecBuilder)
	{
		AttributeBuilder bld = createAttributeBuilder(cspecBuilder);
		bld.initFrom(this);
		return bld;
	}

	public final CSpec getCSpec()
	{
		assert m_cspec != null;
		return m_cspec;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public Documentation getDocumentation()
	{
		return m_documentation;
	}

	public Filter getFilter()
	{
		return m_filter;
	}

	public PathGroup[] getPathGroups(IModelCache ctx, Stack<IAttributeFilter> filters) throws CoreException
	{
		return PathGroup.EMPTY_ARRAY;
	}

	public List<Prerequisite> getPrerequisites()
	{
		return getPrerequisites(null);
	}

	public List<Prerequisite> getPrerequisites(Stack<IAttributeFilter> filters)
	{
		// Only targets have artifact group prerequisites
		//
		return Collections.emptyList();
	}

	public String getQualifiedName()
	{
		return getCSpec().getComponentIdentifier().toString() + '#' + getName();
	}

	public boolean isEnabled(IModelCache ctx) throws CoreException
	{
		if(m_filter == null)
			return true;

		return FilterUtils.isMatch(m_filter, ctx.getProperties());
	}

	public boolean isProducedByActions(IModelCache cache) throws CoreException
	{
		return false;
	}

	public boolean isPublic()
	{
		return true;
	}

	@Override
	public final String toString()
	{
		StringBuilder bld = new StringBuilder();
		toString(bld);
		return bld.toString();
	}

	public void toString(StringBuilder bld)
	{
		getCSpec().getComponentIdentifier().toString(bld);
		bld.append('#');
		bld.append(getName());
	}

	@Override
	protected void addAttributes(AttributesImpl attrs)
	{
		super.addAttributes(attrs);
		if(m_filter != null)
			Utils.addAttribute(attrs, ATTR_FILTER, m_filter.toString());
	}

	protected AttributeBuilder createAttributeBuilder(CSpecBuilder cspecBuilder)
	{
		return cspecBuilder.createAttributeBuilder();
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException
	{
		if(m_documentation != null)
			m_documentation.toSax(handler, namespace, prefix, m_documentation.getDefaultTag());
	}

	/**
	 * It would be wonderful if we could have everything final. Double references does however
	 * create a hen and egg problem. This is the hen telling the egg that it is its mother.
	 * @param cspec The owner cspec
	 */
	void setCSPec(CSpec cspec)
	{
		assert m_cspec == null;
		m_cspec = cspec;
	}
}
