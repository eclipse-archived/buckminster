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
import org.eclipse.buckminster.core.cspec.IComponentRequest;
import org.eclipse.buckminster.core.cspec.IGenerator;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.GeneratorBuilder;
import org.eclipse.buckminster.core.cspec.builder.TopLevelAttributeBuilder;
import org.eclipse.buckminster.core.cspec.model.TopLevelAttribute;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.internal.provisional.p2.core.Version;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class CSpecExtension
{
	public static final String TAG = "cspecExtension"; //$NON-NLS-1$

	public static final String ELEM_ALTER_ACTIONS = "alterActions"; //$NON-NLS-1$

	public static final String ELEM_ALTER_ARTIFACTS = "alterArtifacts"; //$NON-NLS-1$

	public static final String ELEM_ALTER_DEPENDENCIES = "alterDependencies"; //$NON-NLS-1$

	public static final String ELEM_ALTER_GROUPS = "alterGroups"; //$NON-NLS-1$

	public static <T> T overrideCheckNull(T a, T b)
	{
		if(a == null)
			return b;
		if(b == null)
			return a;
		if(b.toString().equalsIgnoreCase("null")) //$NON-NLS-1$
			return null;
		return b;
	}

	private final ICSpecData m_base;

	private final Set<String> m_removedDependencies;

	private final Set<String> m_removedAttributes;

	private final Map<String, AlterAttribute<? extends TopLevelAttribute>> m_alteredAttributes;

	private final Map<String, AlterDependency> m_alteredDependencies;

	public CSpecExtension(ICSpecData base, Set<String> removedDependencies,
			Map<String, AlterDependency> alteredDependencies, Set<String> removedAttributes,
			Map<String, AlterAttribute<? extends TopLevelAttribute>> alteredAttributes)
	{
		m_base = base;
		m_removedDependencies = Utils.createUnmodifiableSet(removedDependencies);
		m_removedAttributes = Utils.createUnmodifiableSet(removedAttributes);
		m_alteredAttributes = Utils.createUnmodifiableMap(alteredAttributes);
		m_alteredDependencies = Utils.createUnmodifiableMap(alteredDependencies);
	}

	public void alterCSpec(CSpecBuilder cspecBuilder) throws CoreException
	{
		for(String removedDep : m_removedDependencies)
		{
			cspecBuilder.getRequiredDependency(removedDep, null);
			cspecBuilder.removeDependency(removedDep);
		}

		for(AlterDependency alterDep : m_alteredDependencies.values())
			alterDep.alterDependency(cspecBuilder.getRequiredDependency(alterDep.getName(), alterDep
					.getComponentTypeID()));

		Collection<? extends IComponentRequest> addedDeps = m_base.getDependencies();
		for(IComponentRequest addedDep : addedDeps)
			cspecBuilder.addDependency(addedDep);

		Map<String, ? extends IGenerator> addedGenerators = m_base.getGenerators();
		for(IGenerator addedGenerator : addedGenerators.values())
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
			alterAttr.alterAttribute((TopLevelAttributeBuilder)cspecBuilder.getRequiredAttribute(alterAttr.getName()));

		Map<String, ? extends IAttribute> addedAttrs = m_base.getAttributes();
		for(IAttribute addedAttr : addedAttrs.values())
		{
			AttributeBuilder attrBld;
			if(addedAttr instanceof IActionArtifact)
				attrBld = cspecBuilder.createActionArtifactBuilder();
			else if(addedAttr instanceof IAction)
				attrBld = cspecBuilder.createActionBuilder();
			else if(addedAttr instanceof IArtifact)
				attrBld = cspecBuilder.createArtifactBuilder();
			else
				attrBld = cspecBuilder.createGroupBuilder();
			attrBld.initFrom(addedAttr);
			cspecBuilder.addAttribute(attrBld);
		}

		// On the top element, we never override a value with NULL unless it is
		// explicitly set to the string "null"
		//
		cspecBuilder.setComponentTypeID(overrideCheckNull(cspecBuilder.getComponentTypeID(), m_base
				.getComponentTypeID()));
		cspecBuilder.setVersion(overrideCheckNull(cspecBuilder.getVersion(), m_base.getVersion()));

		Documentation origDoc = cspecBuilder.getDocumentation();
		Documentation baseDoc = m_base.getDocumentation();
		cspecBuilder.setDocumentation(origDoc == null
				? baseDoc
				: origDoc.merge(baseDoc));
	}

	/**
	 * Returns the CSPEC base that contains the top element attribute overrides and all pure additions
	 * 
	 * @return A Cspec that acts as the base for the extension
	 */
	public void alterTopElement(CSpecBuilder bld) throws CoreException
	{
		Version extVersion = m_base.getVersion();
		if(extVersion != null)
			bld.setVersion(extVersion);

		String ctype = m_base.getComponentTypeID();
		if(ctype != null)
			bld.setComponentTypeID(ctype);
	}
}
