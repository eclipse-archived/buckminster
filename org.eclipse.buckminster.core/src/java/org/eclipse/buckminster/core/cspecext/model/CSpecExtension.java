/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.model;

import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.GeneratorBuilder;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.ActionArtifact;
import org.eclipse.buckminster.core.cspec.model.Artifact;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.Generator;
import org.eclipse.buckminster.core.metadata.model.UUIDKeyed;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 */
public class CSpecExtension
{
	public static final String TAG = "cspecExtension";

	public static final String ELEM_ALTER_ACTIONS = "alterActions";

	public static final String ELEM_ALTER_ARTIFACTS = "alterArtifacts";

	public static final String ELEM_ALTER_DEPENDENCIES = "alterDependencies";

	public static final String ELEM_ALTER_GROUPS = "alterGroups";

	private final CSpec m_base;

	private final Set<String> m_removedDependencies;

	private final Set<String> m_removedAttributes;

	private final Map<String, AlterAttribute<? extends Attribute>> m_alteredAttributes;

	private final Map<String, AlterDependency> m_alteredDependencies;

	public CSpecExtension(CSpec base, Set<String> removedDependencies,
			Map<String, AlterDependency> alteredDependencies, Set<String> removedAttributes,
			Map<String, AlterAttribute<? extends Attribute>> alteredAttributes)
	{
		m_base = base;
		m_removedDependencies = UUIDKeyed.createUnmodifiableSet(removedDependencies);
		m_removedAttributes = UUIDKeyed.createUnmodifiableSet(removedAttributes);
		m_alteredAttributes = UUIDKeyed.createUnmodifiableMap(alteredAttributes);
		m_alteredDependencies = UUIDKeyed.createUnmodifiableMap(alteredDependencies);
	}

	public CSpec alterCSpec(CSpec original) throws CoreException
	{
		// Only create a deps copy if we have modifications
		//
		CSpecBuilder cspecBuilder = new CSpecBuilder();
		cspecBuilder.initFrom(original);

		for(String removedDep : m_removedDependencies)
		{
			cspecBuilder.getRequiredDependency(removedDep);
			cspecBuilder.removeDependency(removedDep);
		}

		for(AlterDependency alterDep : m_alteredDependencies.values())
			alterDep.alterDependency(cspecBuilder.getRequiredDependency(alterDep.getName()));

		Map<String, ComponentRequest> addedDeps = m_base.getDependencies();
		for(ComponentRequest addedDep : addedDeps.values())
			cspecBuilder.addDependency(addedDep);

		Map<String,Generator> addedGenerators = m_base.getGenerators();
		for(Generator addedGenerator : addedGenerators.values())
		{
			GeneratorBuilder bld = cspecBuilder.createGeneratorBuilder();
			bld.initFrom(addedGenerator);
			cspecBuilder.addGenerator(bld);
		}

		for(String removedAttr : m_removedAttributes)
		{
			cspecBuilder.getRequiredAttribute(removedAttr);
			cspecBuilder.removeAttribute(removedAttr);
		}

		for(AlterAttribute<?> alterAttr : m_alteredAttributes.values())
			alterAttr.alterAttribute(cspecBuilder.getRequiredAttribute(alterAttr.getName()));

		Map<String, Attribute> addedAttrs = m_base.getAttributes();
		for(Attribute addedAttr : addedAttrs.values())
		{
			AttributeBuilder attrBld;
			if(addedAttr instanceof ActionArtifact)
				attrBld = cspecBuilder.createActionArtifactBuilder();
			else if(addedAttr instanceof Action)
				attrBld = cspecBuilder.createActionBuilder();
			else if(addedAttr instanceof Artifact)
				attrBld = cspecBuilder.createArtifactBuilder();
			else
				attrBld = cspecBuilder.createGroupBuilder();
			attrBld.initFrom(addedAttr);
			cspecBuilder.addAttribute(attrBld);
		}

		// On the top element, we never override a value with NULL unless it is
		// explicitly set to the string "null"
		//
		cspecBuilder.setCategory(overrideCheckNull(original.getCategory(), m_base.getCategory()));
		cspecBuilder.setVersion(overrideCheckNull(original.getVersion(), m_base.getVersion()));

		Documentation origDoc = original.getDocumentation();
		Documentation baseDoc = m_base.getDocumentation();
		cspecBuilder.setDocumentation(origDoc == null ? baseDoc : origDoc.merge(baseDoc));

		CSpec cspec = cspecBuilder.createCSpec();
		cspec.verifyConsistency();
		return cspec;
	}

	public static <T> T overrideCheckNull(T a, T b)
	{
		if(a == null)
			return b;
		if(b == null)
			return a;
		if(b.toString().equalsIgnoreCase("null"))
			return null;
		return b;
	}
}
