/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.model;

import java.util.List;
import java.util.Map;
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
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class GeneratorNode extends BOMNode {
	public static final String ATTR_ATTRIBUTE = "attribute"; //$NON-NLS-1$

	public static final String ATTR_COMPONENT = "component"; //$NON-NLS-1$

	public static final String ATTR_GENERATES = "generates"; //$NON-NLS-1$

	public static final String ATTR_GENERATES_TYPE = "generatesType"; //$NON-NLS-1$

	public static final String ATTR_GENERATES_VERSION = "generatesType"; //$NON-NLS-1$

	public static final String ATTR_DECLARING_CSPEC_ID = "declaringCSpecId"; //$NON-NLS-1$

	@SuppressWarnings("hiding")
	public static final String TAG = "generatorNode"; //$NON-NLS-1$

	private final String attribute;

	private final String component;

	private final IComponentIdentifier generates;

	private final CSpec declaringCSpec;

	public GeneratorNode(CSpec declaringCSpec, IGenerator generator) {
		this.declaringCSpec = declaringCSpec;
		this.component = generator.getComponent();
		this.attribute = generator.getAttribute();
		this.generates = generator.getGeneratedIdentifier();
	}

	public GeneratorNode(CSpec declaringCSpec, String component, String attribute, IComponentIdentifier generates) {
		this.declaringCSpec = declaringCSpec;
		this.component = component;
		this.attribute = attribute;
		this.generates = generates;
	}

	public GeneratorNode(CSpec declaringCSpec, String component, String attribute, String generates) {
		this(declaringCSpec, component, attribute, new ComponentIdentifier(generates, null, null));
	}

	/**
	 * @return the attribute
	 */
	public String getAttribute() {
		return attribute;
	}

	/**
	 * @return the component
	 */
	public String getComponent() {
		return component;
	}

	public CSpec getDeclaringCSpec() {
		return declaringCSpec;
	}

	public String getDefaultTag() {
		return TAG;
	}

	public IComponentIdentifier getGeneratesId() {
		return generates;
	}

	@Override
	public QualifiedDependency getQualifiedDependency() {
		return new QualifiedDependency(getRequest(), null);
	}

	@Override
	public ComponentRequest getRequest() {
		VersionRange range = null;
		if (generates.getVersion() != null)
			range = new VersionRange(generates.getVersion(), true, generates.getVersion(), false);
		return new ComponentRequest(generates.getName(), generates.getComponentTypeID(), range);
	}

	@Override
	public String getViewName() throws CoreException {
		return getRequest().getViewName() + ":generated"; //$NON-NLS-1$
	}

	@Override
	public boolean isFullyResolved(ComponentQuery query, Map<String, ? extends Object> properties) throws CoreException {
		return true;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) {
		Utils.addAttribute(attrs, ATTR_DECLARING_CSPEC_ID, declaringCSpec.getId().toString());
		Utils.addAttribute(attrs, ATTR_ATTRIBUTE, attribute);
		if (component != null)
			Utils.addAttribute(attrs, ATTR_COMPONENT, component);
		Utils.addAttribute(attrs, ATTR_GENERATES, generates.getName());
		if (generates.getComponentTypeID() != null)
			Utils.addAttribute(attrs, ATTR_GENERATES_TYPE, generates.getComponentTypeID());
		if (generates.getVersion() != null)
			Utils.addAttribute(attrs, ATTR_GENERATES_VERSION, generates.getVersion().toString());
	}

	@Override
	void addMaterializationCandidates(RMContext context, List<Resolution> resolutions, ComponentQuery query, MaterializationSpec mspec,
			Set<Resolution> perused) throws CoreException {
	}
}
