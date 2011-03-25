/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import java.util.HashSet;
import java.util.List;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.IAction;
import org.eclipse.buckminster.core.cspec.IAttribute;
import org.eclipse.buckminster.core.cspec.IPrerequisite;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.AttributeAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.model.Group;
import org.eclipse.buckminster.core.cspec.model.PrerequisiteAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.model.UpToDatePolicy;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 */
public class ActionBuilder extends TopLevelAttributeBuilder implements IAction {
	private String actorName;

	private final ExpandingProperties<String> actorProperties = new ExpandingProperties<String>();

	private boolean always = Action.ALWAYS_DEFAULT;

	private boolean assignConsoleSupport = Action.ASSIGN_CONSOLE_SUPPORT_DEFAULT;

	private final PrerequisitesBuilder prerequisitesBuilder;

	private String productAlias;

	private IPath productBase;

	private int productFileCount = -1;

	private final HashSet<IPath> productPaths = new HashSet<IPath>();

	private final ExpandingProperties<String> properties = new ExpandingProperties<String>();

	private UpToDatePolicy upToDatePolicy = UpToDatePolicy.DEFAULT;

	ActionBuilder(CSpecBuilder cspecBuilder) {
		super(cspecBuilder);
		prerequisitesBuilder = new PrerequisitesBuilder(cspecBuilder);
	}

	public void addActorProperty(String key, String propVal, boolean mutable) {
		actorProperties.put(key, propVal, mutable);
	}

	@Override
	public PrerequisiteBuilder addPrerequisite(PrerequisiteBuilder prerequisite) throws PrerequisiteAlreadyDefinedException {
		prerequisitesBuilder.addPrerequisite(prerequisite);
		return prerequisite;
	}

	public ActionArtifactBuilder addProductArtifact(String name, boolean publ, IPath output) throws AttributeAlreadyDefinedException {
		CSpecBuilder cspecBuilder = getCSpecBuilder();
		ActionArtifactBuilder bld = cspecBuilder.createActionArtifactBuilder();
		bld.setActionName(getName());
		bld.setName(name);
		bld.setPublic(publ);
		bld.setBase(output);
		cspecBuilder.addAttribute(bld);
		return bld;
	}

	public void addProductPath(IPath path) {
		productPaths.add(path);
	}

	public void addProperty(String key, String propVal, boolean mutable) {
		properties.put(key, propVal, mutable);
	}

	@Override
	public void clear() {
		super.clear();
		actorName = null;
		always = Action.ALWAYS_DEFAULT;
		assignConsoleSupport = Action.ASSIGN_CONSOLE_SUPPORT_DEFAULT;
		actorProperties.clear();
		prerequisitesBuilder.clear();
		productPaths.clear();
		properties.clear();
		productAlias = null;
		productBase = null;
		upToDatePolicy = UpToDatePolicy.DEFAULT;
		productFileCount = -1;
	}

	@Override
	public Action createAttribute() {
		return new Action(this);
	}

	@Override
	public String getActorName() {
		return actorName;
	}

	@Override
	public ExpandingProperties<String> getActorProperties() {
		return actorProperties;
	}

	@Override
	public AttributeBuilder getAttributeBuilder(CSpecBuilder specBuilder) {
		return specBuilder == getCSpecBuilder() ? this : new ActionBuilder(specBuilder);
	}

	public PrerequisiteBuilder getPrerequisite(String prerequisteName) {
		return prerequisitesBuilder.getPrerequisite(prerequisteName);
	}

	@Override
	public Group getPrerequisiteGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends IPrerequisite> getPrerequisites() {
		return getPrerequisitesBuilder().getPrerequisites();
	}

	public String getPrerequisitesAlias() {
		return prerequisitesBuilder.getName();
	}

	public PrerequisitesBuilder getPrerequisitesBuilder() {
		return prerequisitesBuilder;
	}

	@Override
	public String getProductAlias() {
		return productAlias;
	}

	public ArtifactBuilder getProductArtifact(String name) {
		ArtifactBuilder bld = getCSpecBuilder().getArtifactBuilder(name);
		return (bld instanceof ActionArtifactBuilder) ? bld : null;
	}

	@Override
	public IPath getProductBase() {
		return productBase;
	}

	@Override
	public int getProductFileCount() {
		return productFileCount;
	}

	@Override
	public HashSet<IPath> getProductPaths() {
		return productPaths;
	}

	@Override
	public ExpandingProperties<String> getProperties() {
		return properties;
	}

	@Override
	public UpToDatePolicy getUpToDatePolicy() {
		return upToDatePolicy;
	}

	@Override
	public void initFrom(IAttribute attribute) {
		IAction action = (IAction) attribute;
		super.initFrom(action);
		actorName = action.getActorName();
		actorProperties.putAll(action.getActorProperties(), true);
		always = action.isAlways();
		assignConsoleSupport = action.isAssignConsoleSupport();
		prerequisitesBuilder.initFrom(action.getPrerequisiteGroup());
		productAlias = action.getProductAlias();
		productBase = action.getProductBase();
		upToDatePolicy = action.getUpToDatePolicy();
		productFileCount = action.getProductFileCount();
		productPaths.addAll(action.getProductPaths());
		properties.putAll(action.getProperties(), true);
	}

	@Override
	public boolean isAlways() {
		return always;
	}

	@Override
	public boolean isAssignConsoleSupport() {
		return assignConsoleSupport;
	}

	@Override
	public boolean isInternal() {
		// An internal action is never "built".
		//
		return false;
	}

	@Override
	public void removePrerequisite(String prerequisteName) {
		prerequisitesBuilder.removePrerequisite(prerequisteName);
	}

	public void removeProductPath(IPath path) {
		productPaths.remove(path);
	}

	public void setActorName(String actorName) {
		this.actorName = actorName;
	}

	public void setAlways(boolean always) {
		this.always = always;
	}

	public void setAssignConsoleSupport(boolean assignConsoleSupport) {
		this.assignConsoleSupport = assignConsoleSupport;
	}

	public void setPrerequisites(Group prerequisites) {
		prerequisitesBuilder.initFrom(prerequisites);
	}

	public void setPrerequisitesAlias(String alias) {
		prerequisitesBuilder.setName(alias);
	}

	public void setPrerequisitesRebase(IPath rebase) {
		prerequisitesBuilder.setPrerequisiteRebase(rebase);
	}

	public void setProductAlias(String productAlias) {
		this.productAlias = productAlias;
	}

	public void setProductBase(IPath productBase) {
		this.productBase = productBase == null ? null : productBase.addTrailingSeparator();
	}

	public void setProductFileCount(int productFileCount) {
		this.productFileCount = productFileCount;
	}

	public void setUpToDatePolicy(UpToDatePolicy upToDatePolicy) {
		this.upToDatePolicy = upToDatePolicy;
	}
}
