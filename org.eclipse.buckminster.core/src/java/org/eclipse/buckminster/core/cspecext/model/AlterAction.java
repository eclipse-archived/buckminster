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

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.IAction;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.builder.TopLevelAttributeBuilder;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 */
public class AlterAction extends AlterAttribute<Action>
{
	public static final String ELEM_ALTER_PREREQUISITES = "alterPrerequisites"; //$NON-NLS-1$

	public static final String ELEM_ALTER_ACTOR_PROPERTIES = "alterActorProperties"; //$NON-NLS-1$

	public static final String ELEM_ALTER_PROPERTIES = "alterProperties"; //$NON-NLS-1$

	public static final String ELEM_ALTER_PRODUCTS = "alterProducts"; //$NON-NLS-1$

	public static final String ELEM_REMOVE_PRODUCT_PATH = "removeProductPath"; //$NON-NLS-1$

	public static final String ELEM_REMOVE_ATTRIBUTE = "removeAttribute"; //$NON-NLS-1$

	private final Map<String, String> m_alteredActorProperties;

	private final Set<String> m_removedActorProperties;

	private final Map<String, String> m_alteredProperties;

	private final Set<String> m_removedProperties;

	private final Set<IPath> m_removedPaths;

	private final Map<String, Prerequisite> m_alteredPrerequisites;

	private final Set<String> m_removedPrerequisites;

	public AlterAction(Action base, Set<String> removedHints, Map<String, String> alteredHints,
			Set<String> removedPrerequisites, Map<String, Prerequisite> alteredPrerequisites,
			Set<String> removedActorProperties, Map<String, String> alteredActorProperties,
			Set<String> removedProperties, Map<String, String> alteredProperties, Set<IPath> removedPaths)
	{
		super(base, removedHints, alteredHints);
		m_alteredPrerequisites = Utils.createUnmodifiableMap(alteredPrerequisites);
		m_removedPrerequisites = Utils.createUnmodifiableSet(removedPrerequisites);
		m_alteredActorProperties = ExpandingProperties.createUnmodifiableProperties(alteredActorProperties);
		m_removedActorProperties = Utils.createUnmodifiableSet(removedActorProperties);
		m_alteredProperties = ExpandingProperties.createUnmodifiableProperties(alteredProperties);
		m_removedProperties = Utils.createUnmodifiableSet(removedProperties);
		m_removedPaths = CSpec.createUnmodifiablePaths(removedPaths);
	}

	protected void alterActorProperties(ActionBuilder original) throws CoreException
	{
		performPropertyAlterations(original.getCSpecName(), original.getName(), "actorProperty", original //$NON-NLS-1$
				.getActorProperties(), m_alteredActorProperties, this.getBase().getActorProperties(),
				m_removedActorProperties);
	}

	@Override
	public void alterAttribute(TopLevelAttributeBuilder attrBld) throws CoreException
	{
		ActionBuilder actionBld = (ActionBuilder)attrBld;
		IAction base = getBase();

		GroupBuilder groupBld = actionBld.getPrerequisitesBuilder();
		AlterGroup ag = new AlterGroup(this.getBase().getPrerequisiteGroup(), null, null, m_removedPrerequisites,
				m_alteredPrerequisites);
		ag.alterAttribute(groupBld);

		alterProductPaths(actionBld);
		alterInstallerHints(actionBld);
		alterActorProperties(actionBld);
		alterProperties(actionBld);
		alterDocumentation(actionBld);

		actionBld
				.setProductAlias(CSpecExtension.overrideCheckNull(actionBld.getProductAlias(), base.getProductAlias()));
		actionBld.setProductBase(CSpecExtension.overrideCheckNull(actionBld.getProductBase(), base.getProductBase()));
	}

	protected void alterProductPaths(ActionBuilder original) throws CoreException
	{
		alterPaths(original.getCSpecName(), original.getName(), original.getProductPaths(),
				getBase().getProductPaths(), m_removedPaths);
	}

	protected void alterProperties(ActionBuilder original) throws CoreException
	{
		performPropertyAlterations(original.getCSpecName(), original.getName(), "property", original.getProperties(), //$NON-NLS-1$
				m_alteredProperties, this.getBase().getProperties(), m_removedProperties);
	}
}
