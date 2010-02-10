/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.resolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.IComponentIdentifier;
import org.eclipse.buckminster.core.cspec.IComponentRequest;
import org.eclipse.buckminster.core.cspec.IGenerator;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.UnmodifiableMapUnion;
import org.eclipse.buckminster.core.metadata.model.GeneratorNode;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.query.IAdvisorNode;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.IStatus;

/**
 * @author Thomas Hallgren
 */
public class ResolutionContext extends RMContext implements IResolverBackchannel {
	private final ComponentQuery componentQuery;

	private HashMap<IComponentIdentifier, GeneratorNode> generators;

	private final ResolutionContext parentContext;

	private final HashMap<ComponentRequest, List<ResolverDecision>> decisionLog = new HashMap<ComponentRequest, List<ResolverDecision>>();

	public ResolutionContext(ComponentQuery componentQuery) {
		this(componentQuery, null);
	}

	public ResolutionContext(ComponentQuery componentQuery, ResolutionContext parentContext) {
		super(parentContext == null ? componentQuery.getGlobalProperties() : new UnmodifiableMapUnion<String, Object>(componentQuery
				.getGlobalProperties(), parentContext));
		this.componentQuery = componentQuery;
		this.parentContext = parentContext;
		if (parentContext != null)
			setSilentStatus(parentContext.isSilentStatus());
	}

	public ResolutionContext(MaterializationSpec mspec, ComponentQuery componentQuery) {
		super(new UnmodifiableMapUnion<String, Object>(componentQuery.getGlobalProperties(), mspec.getProperties()));
		this.componentQuery = componentQuery;
		this.parentContext = null;
	}

	@Override
	public synchronized void addRequestStatus(IComponentRequest request, IStatus resolveStatus) {
		if (parentContext != null)
			parentContext.addRequestStatus(request, resolveStatus);
		else
			super.addRequestStatus(request, resolveStatus);
	}

	@Override
	public synchronized void clearStatus() {
		if (parentContext != null)
			parentContext.clearStatus();
		else
			super.clearStatus();
	}

	@Override
	public synchronized Map<String, String> getBindingProperties() {
		return (parentContext != null) ? parentContext.getBindingProperties() : super.getBindingProperties();
	}

	@Override
	public ComponentQuery getComponentQuery() {
		return componentQuery;
	}

	public synchronized List<ResolverDecision> getDecisionLog(IComponentRequest request) {
		if (parentContext != null)
			return parentContext.getDecisionLog(request);
		return Utils.createUnmodifiableList(decisionLog.get(request));
	}

	public GeneratorNode getGeneratorNode(ComponentRequest request) {
		if (generators != null) {
			for (GeneratorNode generator : generators.values()) {
				if (request.designates(generator.getGeneratesId()))
					return generator;
			}
		}
		return (parentContext == null) ? null : parentContext.getGeneratorNode(request);
	}

	@Override
	public Map<String, ? extends Object> getProperties(ComponentName cName) {
		IAdvisorNode parentNode = null;
		IAdvisorNode node = null;
		Map<String, ? extends Object> p = super.getProperties(cName);
		if (parentContext != null)
			parentNode = parentContext.getComponentQuery().getMatchingNode(cName, this);

		node = getComponentQuery().getMatchingNode(cName, this);
		if (parentNode == null && node == null)
			return p;

		if (parentNode != null)
			p = new UnmodifiableMapUnion<String, Object>(parentNode.getProperties(), p);

		if (node != null && node != parentNode)
			p = new UnmodifiableMapUnion<String, Object>(node.getProperties(), p);

		return p;
	}

	@Override
	public synchronized IStatus getStatus() {
		return (parentContext != null) ? parentContext.getStatus() : super.getStatus();
	}

	@Override
	public synchronized Map<UUID, Object> getUserCache() {
		return (parentContext != null) ? parentContext.getUserCache() : super.getUserCache();
	}

	@Override
	public synchronized boolean isContinueOnError() {
		return (parentContext != null) ? parentContext.isContinueOnError() : super.isContinueOnError();
	}

	@Override
	public synchronized ResolverDecision logDecision(ComponentRequest request, ResolverDecisionType decisionType, Object... args) {
		if (parentContext != null)
			return parentContext.logDecision(request, decisionType, args);

		List<ResolverDecision> decisions = decisionLog.get(request);
		if (decisions == null) {
			decisions = new ArrayList<ResolverDecision>();
			decisionLog.put(request, decisions);
		}

		ResolverDecision decision = new ResolverDecision(request, decisionType, args);
		decisions.add(decision);
		if (!isSilentStatus())
			CorePlugin.getLogger().debug("%s: %s", request, decision); //$NON-NLS-1$
		return decision;
	}

	@Override
	public ResolverDecision logDecision(ResolverDecisionType decisionType, Object... args) {
		return logDecision(getComponentQuery().getExpandedRootRequest(this), decisionType, args);
	}

	@Override
	public void setContinueOnError(boolean flag) {
		if (parentContext != null)
			parentContext.setContinueOnError(flag);
		else
			super.setContinueOnError(flag);
	}

	public void setGenerators(CSpec cspec, Collection<? extends IGenerator> generatorList) {
		for (IGenerator generator : generatorList) {
			if (generators == null)
				generators = new HashMap<IComponentIdentifier, GeneratorNode>();
			generators.put(generator.getGeneratedIdentifier(), new GeneratorNode(cspec, generator));
		}
	}
}
