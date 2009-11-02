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
import org.eclipse.buckminster.core.cspec.IComponentIdentifier;
import org.eclipse.buckminster.core.cspec.IGenerator;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class GeneratorNode extends BOMNode
{
	public static final String ATTR_ATTRIBUTE = "attribute"; //$NON-NLS-1$

	public static final String ATTR_COMPONENT = "component"; //$NON-NLS-1$

	public static final String ATTR_GENERATES = "generates"; //$NON-NLS-1$

	public static final String ATTR_GENERATES_TYPE = "generatesType"; //$NON-NLS-1$

	public static final String ATTR_GENERATES_VERSION = "generatesType"; //$NON-NLS-1$

	public static final String ATTR_DECLARING_CSPEC_ID = "declaringCSpecId"; //$NON-NLS-1$

	@SuppressWarnings("hiding")
	public static final String TAG = "generatorNode"; //$NON-NLS-1$

	private final String m_attribute;

	private final String m_component;

	private final IComponentIdentifier m_generates;

	private final CSpec m_declaringCSpec;

	public GeneratorNode(CSpec declaringCSpec, IGenerator generator)
	{
		m_declaringCSpec = declaringCSpec;
		m_component = generator.getComponent();
		m_attribute = generator.getAttribute();
		m_generates = generator.getGeneratedIdentifier();
	}

	public GeneratorNode(CSpec declaringCSpec, String component, String attribute, IComponentIdentifier generates)
	{
		m_declaringCSpec = declaringCSpec;
		m_component = component;
		m_attribute = attribute;
		m_generates = generates;
	}

	public GeneratorNode(CSpec declaringCSpec, String component, String attribute, String generates)
	{
		this(declaringCSpec, component, attribute, new ComponentIdentifier(generates, null, null));
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

	public CSpec getDeclaringCSpec()
	{
		return m_declaringCSpec;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	/**
	 * @deprecated use #getGeneratesId()
	 */
	@Deprecated
	public String getGenerates()
	{
		return m_generates.getName();
	}

	public IComponentIdentifier getGeneratesId()
	{
		return m_generates;
	}

	@Override
	public QualifiedDependency getQualifiedDependency()
	{
		return new QualifiedDependency(getRequest(), null);
	}

	@Override
	public ComponentRequest getRequest()
	{
		VersionRange range = null;
		if(m_generates.getVersion() != null)
			range = new VersionRange(m_generates.getVersion(), true, m_generates.getVersion(), false);
		return new ComponentRequest(m_generates.getName(), m_generates.getComponentTypeID(), range);
	}

	@Override
	public String getViewName() throws CoreException
	{
		return getRequest().getViewName() + ":generated"; //$NON-NLS-1$
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
		Utils.addAttribute(attrs, ATTR_GENERATES, m_generates.getName());
		if(m_generates.getComponentTypeID() != null)
			Utils.addAttribute(attrs, ATTR_GENERATES_TYPE, m_generates.getComponentTypeID());
		if(m_generates.getVersion() != null)
			Utils.addAttribute(attrs, ATTR_GENERATES_VERSION, m_generates.getVersion().toString());
	}

	@Override
	void addMaterializationCandidates(RMContext context, List<Resolution> resolutions, ComponentQuery query,
			MaterializationSpec mspec, Set<Resolution> perused) throws CoreException
	{
	}
}
