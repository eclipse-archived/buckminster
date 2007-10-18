/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.model;

import java.util.List;
import java.util.Set;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.Generator;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class GeneratorNode extends DepNode
{
	public static final String ATTR_ATTRIBUTE = "attribute";

	public static final String ATTR_COMPONENT = "component";

	public static final String ATTR_GENERATES = "generates";

	public static final String ATTR_DECLARING_CSPEC_ID = "declaringCSpecId";

	@SuppressWarnings("hiding")
	public static final String TAG = "generatorNode";

	private final String m_attribute;

	private final String m_component;

	private final String m_generates;

	private final CSpec m_declaringCSpec;

	public GeneratorNode(Generator generator)
	{
		m_declaringCSpec = generator.getCSpec();
		m_component = generator.getComponent();
		m_attribute = generator.getAttribute();
		m_generates = generator.getGenerates();
	}

	public GeneratorNode(CSpec declaringCSpec, String component, String attribute, String generates)
	{
		m_declaringCSpec = declaringCSpec;
		m_component = component;
		m_attribute = attribute;
		m_generates = generates;
	}

	/**
	 * @return the attribute
	 */
	public String getAttribute()
	{
		return m_attribute;
	}

	/**
	 * @return the component
	 */
	public String getComponent()
	{
		return m_component;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	/**
	 * @return the generates
	 */
	public String getGenerates()
	{
		return m_generates;
	}

	public CSpec getDeclaringCSpec()
	{
		return m_declaringCSpec;
	}

	@Override
	public QualifiedDependency getQualifiedDependency()
	{
		return new QualifiedDependency(getRequest(), null);
	}

	@Override
	public ComponentRequest getRequest()
	{
		return new ComponentRequest(m_generates, null, null);
	}

	@Override
	public String getViewName() throws CoreException
	{
		return getRequest().getViewName() + ":generated";
	}

	@Override
	public boolean isFullyResolved(ComponentQuery query) throws CoreException
	{
		return true;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs)
	{
		Utils.addAttribute(attrs, ATTR_DECLARING_CSPEC_ID, m_declaringCSpec.getId().toString());
		Utils.addAttribute(attrs, ATTR_ATTRIBUTE, m_attribute);
		if(m_component != null)
			Utils.addAttribute(attrs, ATTR_COMPONENT, m_component);
		Utils.addAttribute(attrs, ATTR_GENERATES, m_generates);
	}

	@Override
	void addMaterializationCandidates(RMContext context, List<Resolution> resolutions, ComponentQuery query, MaterializationSpec mspec, Set<Resolution> perused)
	throws CoreException
	{
	}
}
