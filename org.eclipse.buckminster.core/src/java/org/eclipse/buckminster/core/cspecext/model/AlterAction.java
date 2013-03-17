/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
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
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 */
public class AlterAction extends AlterAttribute<Action> {
	public static final String ELEM_ALTER_PREREQUISITES = "alterPrerequisites"; //$NON-NLS-1$

	public static final String ELEM_ALTER_ACTOR_PROPERTIES = "alterActorProperties"; //$NON-NLS-1$

	public static final String ELEM_ALTER_PROPERTIES = "alterProperties"; //$NON-NLS-1$

	public static final String ELEM_ALTER_PRODUCTS = "alterProducts"; //$NON-NLS-1$

	public static final String ELEM_REMOVE_PRODUCT_PATH = "removeProductPath"; //$NON-NLS-1$

	public static final String ELEM_REMOVE_ATTRIBUTE = "removeAttribute"; //$NON-NLS-1$

	private final Map<String, String> alteredActorProperties;

	private final Set<String> removedActorProperties;

	private final Map<String, String> alteredProperties;

	private final Set<String> removedProperties;

	private final Set<IPath> removedPaths;

	private final Map<String, Prerequisite> alteredPrerequisites;

	private final Set<String> removedPrerequisites;

	public AlterAction(Action base, Set<String> removedHints, Map<String, String> alteredHints, Set<String> removedPrerequisites,
			Map<String, Prerequisite> alteredPrerequisites, Set<String> removedActorProperties, Map<String, String> alteredActorProperties,
			Set<String> removedProperties, Map<String, String> alteredProperties, Set<IPath> removedPaths) {
		super(base, removedHints, alteredHints);
		this.alteredPrerequisites = Utils.createUnmodifiableMap(alteredPrerequisites);
		this.removedPrerequisites = Utils.createUnmodifiableSet(removedPrerequisites);
		this.alteredActorProperties = ExpandingProperties.createUnmodifiableProperties(alteredActorProperties);
		this.removedActorProperties = Utils.createUnmodifiableSet(removedActorProperties);
		this.alteredProperties = ExpandingProperties.createUnmodifiableProperties(alteredProperties);
		this.removedProperties = Utils.createUnmodifiableSet(removedProperties);
		this.removedPaths = CSpec.createUnmodifiablePaths(removedPaths);
	}

	@Override
	public void alterAttribute(TopLevelAttributeBuilder attrBld) throws CoreException {
		if (!(attrBld instanceof ActionBuilder))
			throw BuckminsterException.fromMessage("%s is not an action", attrBld.getQualifiedName()); //$NON-NLS-1$

		ActionBuilder actionBld = (ActionBuilder) attrBld;
		IAction base = getBase();

		GroupBuilder groupBld = actionBld.getPrerequisitesBuilder();
		AlterGroup ag = new AlterGroup(this.getBase().getPrerequisiteGroup(), null, null, removedPrerequisites, alteredPrerequisites);
		ag.alterAttribute(groupBld);

		alterProductPaths(actionBld);
		alterActorProperties(actionBld);
		alterProperties(actionBld);
		alterDocumentation(actionBld);

		actionBld.setProductAlias(CSpecExtension.overrideCheckNull(actionBld.getProductAlias(), base.getProductAlias()));
		actionBld.setProductBase(CSpecExtension.overrideCheckNull(actionBld.getProductBase(), base.getProductBase()));
	}

	protected void alterActorProperties(ActionBuilder original) throws CoreException {
		performPropertyAlterations(original.getCSpecName(), original.getName(), "actorProperty", original //$NON-NLS-1$
				.getActorProperties(), alteredActorProperties, this.getBase().getActorProperties(), removedActorProperties);
	}

	protected void alterProductPaths(ActionBuilder original) throws CoreException {
		alterPaths(original.getCSpecName(), original.getName(), original.getProductPaths(), getBase().getProductPaths(), removedPaths);
	}

	protected void alterProperties(ActionBuilder original) throws CoreException {
		performPropertyAlterations(original.getCSpecName(), original.getName(), "property", original.getProperties(), //$NON-NLS-1$
				alteredProperties, this.getBase().getProperties(), removedProperties);
	}
}
