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

import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.metadata.model.UUIDKeyed;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 */
public class AlterAction extends AlterAttribute<Action>
{
	public static final String ELEM_ALTER_PREREQUISITES = "alterPrerequisites";
	public static final String ELEM_ALTER_ACTOR_PROPERTIES = "alterActorProperties";
	public static final String ELEM_ALTER_PROPERTIES = "alterProperties";
	public static final String ELEM_ALTER_PRODUCTS = "alterProducts";
	public static final String ELEM_REMOVE_PRODUCT_PATH = "removeProductPath";
	public static final String ELEM_REMOVE_ATTRIBUTE = "removeAttribute";

	private final Map<String, String> m_alteredActorProperties;

	private final Set<String> m_removedActorProperties;

	private final Map<String, String> m_alteredProperties;

	private final Set<String> m_removedProperties;

	private final Set<IPath> m_removedPaths;

	private final Map<String,Prerequisite> m_alteredPrerequisites;
	private final Set<String> m_removedPrerequisites;

	public AlterAction(Action base, Set<String> removedHints, Map<String, String> alteredHints,
			Set<String> removedPrerequisites, Map<String,Prerequisite> alteredPrerequisites, 
			Set<String> removedActorProperties, Map<String, String> alteredActorProperties,
			Set<String> removedProperties, Map<String, String> alteredProperties, Set<IPath> removedPaths)
	{
		super(base, removedHints, alteredHints);
		m_alteredPrerequisites = UUIDKeyed.createUnmodifiableMap(alteredPrerequisites);
		m_removedPrerequisites = UUIDKeyed.createUnmodifiableSet(removedPrerequisites);
		m_alteredActorProperties = UUIDKeyed.createUnmodifiableProperties(alteredActorProperties);
		m_removedActorProperties = UUIDKeyed.createUnmodifiableSet(removedActorProperties);
		m_alteredProperties = UUIDKeyed.createUnmodifiableProperties(alteredProperties);
		m_removedProperties = UUIDKeyed.createUnmodifiableSet(removedProperties);
		m_removedPaths = UUIDKeyed.createUnmodifiablePaths(removedPaths);
	}

	@Override
	public void alterAttribute(AttributeBuilder attrBld) throws CoreException
	{
		ActionBuilder actionBld = (ActionBuilder)attrBld;
		Action base = getBase();

		GroupBuilder groupBld = actionBld.getPrerequisitesBuilder();
		AlterGroup ag = new AlterGroup(
								this.getBase().getPrerequisiteGroup(), null, null,
								m_removedPrerequisites, m_alteredPrerequisites);
		ag.alterAttribute(groupBld);

		alterProductPaths(actionBld);
		alterInstallerHints(actionBld);
		alterActorProperties(actionBld);
		alterProperties(actionBld);
		alterDocumentation(actionBld);

		actionBld.setProductAlias(CSpecExtension.overrideCheckNull(actionBld.getProductAlias(), base.getProductAlias()));
		actionBld.setProductBase(CSpecExtension.overrideCheckNull(actionBld.getProductBase(), base.getProductBase()));
	}

	protected void alterProductPaths(ActionBuilder original) throws CoreException
	{
		alterPaths(
				original.getCSpecName(), original.getName(),
				original.getProductPaths(), getBase().getProductPaths(), m_removedPaths);
	}

	protected void alterActorProperties(ActionBuilder original) throws CoreException
	{
		performPropertyAlterations(
				original.getCSpecName(), original.getName(),
				original.getActorProperties(), m_alteredActorProperties, this.getBase().getActorProperties(),
				m_removedActorProperties);
	}

	protected void alterProperties(ActionBuilder original) throws CoreException
	{
		performPropertyAlterations(
				original.getCSpecName(), original.getName(),
				original.getProperties(), m_alteredProperties, this.getBase().getProperties(),
				m_removedProperties);
	}
}
