/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.resolver;

import java.util.List;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.UnresolvedNode;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

/**
 * @author Thomas Hallgren
 */
public class MainResolver implements IResolver {
	private static final int MAX_ITERATIONS = 8; // TODO: Perhaps this should be
													// a preference setting

	private final ResolutionContext context;

	private boolean recursiveResolve = true;

	public MainResolver(ResolutionContext context) {
		this.context = context;
	}

	@Override
	public ResolutionContext getContext() {
		return context;
	}

	@Override
	public boolean isRecursiveResolve() {
		return recursiveResolve;
	}

	@Override
	public ResolverDecision logDecision(ComponentRequest request, ResolverDecisionType decisionType, Object... args) {
		return context.logDecision(request, decisionType, args);
	}

	@Override
	public ResolverDecision logDecision(ResolverDecisionType decisionType, Object... args) {
		return context.logDecision(decisionType, args);
	}

	@Override
	public BillOfMaterials resolve(ComponentRequest request, IProgressMonitor monitor) throws CoreException {
		NodeQuery query = context.getNodeQuery(request);
		BillOfMaterials bom = BillOfMaterials.create(new UnresolvedNode(query.getQualifiedDependency()), context.getComponentQuery());
		return resolveRemaining(bom, monitor);
	}

	@Override
	public BillOfMaterials resolve(IProgressMonitor monitor) throws CoreException {
		return resolve(context.getComponentQuery().getExpandedRootRequest(context), monitor);
	}

	@Override
	public BillOfMaterials resolveRemaining(BillOfMaterials bom, IProgressMonitor monitor) throws CoreException {
		if (bom.isFullyResolved(context))
			return bom;

		context.addTagInfo(bom.getRequest(), bom.getQuery().getTagInfo());
		IResolverFactory[] resolverFactories = ResolverFactoryMaintainer.getInstance().getActiveResolverFactories();
		int numFactories = resolverFactories.length;
		if (numFactories == 1) {
			// Only one factory? Just delegate to it.
			//
			IResolverFactory factory = resolverFactories[0];
			logDecision(ResolverDecisionType.USING_RESOLVER, factory.getId());
			bom = factory.createResolver(context).resolveRemaining(bom, monitor);
		} else {

			monitor.beginTask(null, numFactories * 100);

			// Since all factories but the last should continue on error
			// we must save our status here. The last factory should use
			// our status always since it must fail if we are setup to
			// fail.
			//
			boolean continueOnError = context.isContinueOnError();
			boolean silentStatus = context.isSilentStatus();
			context.setContinueOnError(true);
			context.setSilentStatus(true);
			try {
				IResolver[] resolvers = new IResolver[numFactories];
				for (int idx = 0; idx < numFactories; ++idx)
					resolvers[idx] = resolverFactories[idx].createResolver(context);

				for (int iteration = 0; iteration < MAX_ITERATIONS; ++iteration) {
					BillOfMaterials bomAtIterationStart = bom;
					for (int idx = 0; idx < numFactories; ++idx) {
						IResolver resolver = resolvers[idx];
						logDecision(ResolverDecisionType.USING_RESOLVER, resolverFactories[idx].getId());
						resolver.setRecursiveResolve(recursiveResolve);
						BillOfMaterials newBom = resolver.resolveRemaining(bom, MonitorUtils.subMonitor(monitor, 100));
						if (bom.contentEqual(newBom))
							continue;

						if (idx == 0)
							//
							// There is no reason to reiterate if the only
							// iteration that changed
							// the bom was the first.
							//
							bomAtIterationStart = bom;

						bom = newBom;
						if (!recursiveResolve || bom.isFullyResolved(context)) {
							// Something happened with the BOM so we consider
							// ourselves done here
							//
							iteration = MAX_ITERATIONS;
							break;
						}
					}

					if (bomAtIterationStart.equals(bom))
						//
						// A full iteration over all resolvers gave us nothing.
						// Then
						// it's safe to assume that the same thing would happen
						// again
						//
						break;
				}

				if (bom.isFullyResolved(context))
					context.clearStatus();
				else if (!continueOnError) {
					IStatus status = context.getStatus();
					if (status.getSeverity() == IStatus.ERROR) {
						context.clearStatus();
						List<ComponentRequest> unresolvedList = bom.getUnresolvedList();
						int top = unresolvedList.size();
						if (top == 0)
							throw new CoreException(status);

						StringBuilder bld = new StringBuilder();
						bld.append(Messages.Unable_to_resolve);

						for (int idx = 0; idx < top; ++idx) {
							if (idx > 0)
								bld.append(", "); //$NON-NLS-1$
							unresolvedList.get(idx).toString(bld);
						}
						throw BuckminsterException.fromMessage(bld.toString());
					}
				}
			} finally {
				context.setContinueOnError(continueOnError);
				context.setSilentStatus(silentStatus);
				monitor.done();
			}
		}
		return bom;
	}

	@Override
	public void setRecursiveResolve(boolean flag) {
		recursiveResolve = flag;
	}
}
