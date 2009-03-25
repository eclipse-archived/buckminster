/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.remote.resolver;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.UnresolvedNode;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.core.resolver.ResolverDecision;
import org.eclipse.buckminster.core.resolver.ResolverDecisionType;
import org.eclipse.buckminster.remote.IProgressInfo;
import org.eclipse.buckminster.remote.Messages;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;

/**
 * Resolver that resolves query on a distant server
 * 
 * @author Karel Brezina
 */
public class RemoteResolver implements IResolver
{
	private IResolutionServiceConnection m_remoteService;

	private ComponentQuery m_cQuery;

	private final ResolutionContext m_context;

	private boolean m_recursive = false;

	public RemoteResolver(IResolutionServiceConnection remoteService, ComponentQuery cQuery)
	{
		m_remoteService = remoteService;
		m_cQuery = cQuery;
		m_context = new ResolutionContext(m_cQuery);
	}

	public ResolutionContext getContext()
	{
		return m_context;
	}

	public boolean isRecursiveResolve()
	{
		return m_recursive;
	}

	public ResolverDecision logDecision(ComponentRequest request, ResolverDecisionType decisionType, Object... args)
	{
		return m_context.logDecision(request, decisionType, args);
	}

	public ResolverDecision logDecision(ResolverDecisionType decisionType, Object... args)
	{
		return m_context.logDecision(decisionType, args);
	}

	public BillOfMaterials resolve(ComponentRequest request, IProgressMonitor monitor) throws CoreException
	{
		QualifiedDependency qDep = new QualifiedDependency(request, m_cQuery.getAttributes(request));

		BillOfMaterials unresolved = BillOfMaterials.create(new UnresolvedNode(qDep), m_cQuery);

		return resolveRemaining(unresolved, monitor);
	}

	public BillOfMaterials resolve(IProgressMonitor monitor) throws CoreException
	{
		return resolve(m_context.getComponentQuery().getExpandedRootRequest(m_context), monitor);
	}

	public BillOfMaterials resolveRemaining(BillOfMaterials bom, IProgressMonitor monitor) throws CoreException
	{
		if(bom == null)
			throw BuckminsterException.fromMessage(Messages.null_BOM_resolution_request);

		if(bom.isFullyResolved())
		{
			MonitorUtils.complete(monitor);
			return bom;
		}

		if(!bom.getQuery().useResolutionService(bom.getRequest()))
			return bom;

		BillOfMaterials bomReturned = null;

		try
		{
			m_remoteService.reset();

			monitor.beginTask(Messages.query_resolution_in_progress, IProgressMonitor.UNKNOWN);
			int lastWorked = 0;

			Logger logger = CorePlugin.getLogger();

			try
			{
				monitor.subTask(Messages.starting_query_resolution);

				logger.debug("Starting query..."); //$NON-NLS-1$
				m_remoteService.fireQueryResolution(bom);
				logger.debug("Query started, waiting for reply..."); //$NON-NLS-1$
			}
			catch(Exception e)
			{
				throw BuckminsterException.wrap(e);
			}

			while(!m_remoteService.isDone())
			{
				try
				{
					Thread.sleep(500);
				}
				catch(InterruptedException i)
				{
				}

				IProgressInfo progressInfo = m_remoteService.getProgressInfo();
				String message = progressInfo.getMessage();
				int worked = progressInfo.getWorked() * 100;

				monitor.subTask(message);
				monitor.worked(worked - lastWorked);

				lastWorked = worked;

				if(monitor.isCanceled() && !m_remoteService.isCancelSent())
				{
					logger.debug("Starting cancel service..."); //$NON-NLS-1$
					m_remoteService.cancel();
					logger.debug("Cancel service started..."); //$NON-NLS-1$
					throw new OperationCanceledException();
				}
			}

			try
			{
				logger.debug("Getting query result..."); //$NON-NLS-1$
				bomReturned = m_remoteService.getResolutionResult();

				if(bomReturned == null)
				{
					logger.debug("No resolution progress detected, using the original BOM..."); //$NON-NLS-1$
					bomReturned = bom;
				}
			}
			catch(Exception e)
			{
				throw BuckminsterException.wrap(e);
			}
		}
		finally
		{
			monitor.done();
			m_remoteService.releaseConnection();
		}
		return bom.switchContent(bomReturned);
	}

	public void setRecursiveResolve(boolean recursive)
	{
		m_recursive = recursive;
	}
}
