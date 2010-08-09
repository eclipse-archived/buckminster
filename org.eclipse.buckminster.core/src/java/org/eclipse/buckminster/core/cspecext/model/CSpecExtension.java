/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.model;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.IAction;
import org.eclipse.buckminster.core.cspec.IActionArtifact;
import org.eclipse.buckminster.core.cspec.IArtifact;
import org.eclipse.buckminster.core.cspec.IAttribute;
import org.eclipse.buckminster.core.cspec.ICSpecData;
import org.eclipse.buckminster.core.cspec.IGenerator;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.GeneratorBuilder;
import org.eclipse.buckminster.core.cspec.builder.TopLevelAttributeBuilder;
import org.eclipse.buckminster.core.cspec.model.TopLevelAttribute;
import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.p2.metadata.Version;

/**
 * @author Thomas Hallgren
 */
public class CSpecExtension {
	public static final String TAG = "cspecExtension"; //$NON-NLS-1$

	public static final String ELEM_ALTER_ACTIONS = "alterActions"; //$NON-NLS-1$

	public static final String ELEM_ALTER_ARTIFACTS = "alterArtifacts"; //$NON-NLS-1$

	public static final String ELEM_ALTER_DEPENDENCIES = "alterDependencies"; //$NON-NLS-1$

	public static final String ELEM_ALTER_GROUPS = "alterGroups"; //$NON-NLS-1$

	public static <T> T overrideCheckNull(T a, T b) {
		if (a == null)
			return b;
		if (b == null)
			return a;
		if (b.toString().equalsIgnoreCase("null")) //$NON-NLS-1$
			return null;
		return b;
	}

	private final ICSpecData base;

	private final Set<String> removedDependencies;

	private final Set<String> removedAttributes;

	private final Map<String, String> renamedAttributes;

	private final Map<String, AlterAttribute<? extends TopLevelAttribute>> alteredAttributes;

	private final Map<String, AlterDependency> alteredDependencies;

	public CSpecExtension(ICSpecData base, Set<String> removedDependencies, Map<String, AlterDependency> alteredDependencies,
			Set<String> removedAttributes, Map<String, String> renamedAttributes,
			Map<String, AlterAttribute<? extends TopLevelAttribute>> alteredAttributes) {
		this.base = base;
		this.removedDependencies = Utils.createUnmodifiableSet(removedDependencies);
		this.removedAttributes = Utils.createUnmodifiableSet(removedAttributes);
		this.renamedAttributes = Utils.createUnmodifiableMap(renamedAttributes);
		this.alteredAttributes = Utils.createUnmodifiableMap(alteredAttributes);
		this.alteredDependencies = Utils.createUnmodifiableMap(alteredDependencies);
	}

	public void alterCSpec(CSpecBuilder cspecBuilder) throws CoreException {
		for (String removedDep : removedDependencies) {
			ComponentRequest cr = CommonFactory.eINSTANCE.createComponentRequest();
			cr.setId(removedDep);
			cspecBuilder.getRequiredDependency(cr);
			cspecBuilder.removeDependency(removedDep);
		}

		for (AlterDependency alterDep : alteredDependencies.values())
			alterDep.alterDependency(cspecBuilder.getRequiredDependency(alterDep));

		Collection<? extends ComponentRequest> addedDeps = base.getDependencies();
		for (ComponentRequest addedDep : addedDeps)
			cspecBuilder.addDependency(addedDep);

		for (IGenerator addedGenerator : base.getGeneratorList()) {
			GeneratorBuilder bld = cspecBuilder.createGeneratorBuilder();
			bld.initFrom(addedGenerator);
			cspecBuilder.addGenerator(bld);
		}

		for (String removedAttr : removedAttributes) {
			cspecBuilder.getRequiredAttribute(removedAttr);
			cspecBuilder.removeAttribute(removedAttr);
		}

		for (Map.Entry<String, String> renamedAttribute : renamedAttributes.entrySet()) {
			String oldName = renamedAttribute.getKey();
			AttributeBuilder bld = cspecBuilder.getRequiredAttribute(oldName);
			bld.setName(renamedAttribute.getValue());
			cspecBuilder.removeAttribute(oldName);
			cspecBuilder.addAttribute(bld);
		}

		for (AlterAttribute<?> alterAttr : alteredAttributes.values())
			alterAttr.alterAttribute((TopLevelAttributeBuilder) cspecBuilder.getRequiredAttribute(alterAttr.getName()));

		Map<String, ? extends IAttribute> addedAttrs = base.getAttributes();
		for (IAttribute addedAttr : addedAttrs.values()) {
			AttributeBuilder attrBld;
			if (addedAttr instanceof IActionArtifact)
				attrBld = cspecBuilder.createActionArtifactBuilder();
			else if (addedAttr instanceof IAction)
				attrBld = cspecBuilder.createActionBuilder();
			else if (addedAttr instanceof IArtifact)
				attrBld = cspecBuilder.createArtifactBuilder();
			else
				attrBld = cspecBuilder.createGroupBuilder();
			attrBld.initFrom(addedAttr);
			cspecBuilder.addAttribute(attrBld);
		}

		// On the top element, we never override a value with NULL unless it is
		// explicitly set to the string "null"
		//
		cspecBuilder.setComponentTypeID(overrideCheckNull(cspecBuilder.getComponentTypeID(), base.getComponentTypeID()));
		cspecBuilder.setVersion(overrideCheckNull(cspecBuilder.getVersion(), base.getVersion()));

		Documentation origDoc = cspecBuilder.getDocumentation();
		Documentation baseDoc = base.getDocumentation();
		cspecBuilder.setDocumentation(origDoc == null ? baseDoc : origDoc.merge(baseDoc));
	}

	/**
	 * Returns the CSPEC base that contains the top element attribute overrides
	 * and all pure additions
	 * 
	 * @return A Cspec that acts as the base for the extension
	 */
	public void alterTopElement(CSpecBuilder bld) throws CoreException {
		Version extVersion = base.getVersion();
		if (extVersion != null)
			bld.setVersion(extVersion);

		String ctype = base.getComponentTypeID();
		if (ctype != null)
			bld.setComponentTypeID(ctype);
	}
}
