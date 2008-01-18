/*****************************************************************************
* Copyright (c) 2006-2007, Cloudsmith Inc.
* The code, documentation and other materials contained herein have been
* licensed under the Eclipse Public License - v 1.0 by the copyright holder
* listed above, as the Initial Contributor under such license. The text of
* such license is available at www.eclipse.org.
*****************************************************************************/

package org.eclipse.buckminster.remote.resolver;

import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.UnresolvedNode;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
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

	public BillOfMaterials resolve(IProgressMonitor monitor) throws CoreException
	{
		return resolve(m_context.getComponentQuery().getRootRequest(), monitor);
	}

	public BillOfMaterials resolve(ComponentRequest request, IProgressMonitor monitor) throws CoreException
	{
		QualifiedDependency qDep = new QualifiedDependency(request, m_cQuery.getAttributes(request));

		BillOfMaterials unresolved = BillOfMaterials.create(new UnresolvedNode(qDep), m_cQuery);

		return resolveRemaining(unresolved, monitor);
	}

	public BillOfMaterials resolveRemaining(BillOfMaterials bom, IProgressMonitor monitor) throws CoreException
	{
		if (bom == null)
			throw new BuckminsterException("Null BOM resolution request");
		
		if(bom.isFullyResolved())
		{
			MonitorUtils.complete(monitor);
			return bom;
		}

		BillOfMaterials bomReturned = null;

		try
		{
			m_remoteService.reset();

			monitor.beginTask("Query resolution is in progress...", IProgressMonitor.UNKNOWN);
			int lastWorked = 0;

			Logger logger = CorePlugin.getLogger();

			try
			{
				monitor.subTask("Starting query resolution");

				logger.debug("Starting query...");
				m_remoteService.fireQueryResolution(bom);
				logger.debug("Query started, waiting for reply...");
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

				String message = m_remoteService.getProgressInfo().getMessage();
				int worked = m_remoteService.getProgressInfo().getWorked() * 100;

				monitor.subTask(message);
				monitor.worked(worked - lastWorked);
				
				lastWorked = worked;

				if (monitor.isCanceled() && !m_remoteService.isCancelSent())
				{
					logger.debug("Starting cancel service...");
					m_remoteService.cancel();
					logger.debug("Cancel service started...");
				}
			}

			Throwable finalException = null;

			try
			{
				logger.debug("Getting query result...");
				bomReturned = m_remoteService.getResolutionResult();
				
				if (bomReturned == null)
				{
					logger.debug("No resolution progress detected, using the original BOM...");
					bomReturned = bom;
				}
			}
			catch(Exception e)
			{
				finalException = e;
			}

			if(finalException != null)
			{
				if(monitor.isCanceled())
				{
					throw new OperationCanceledException();
				}

				throw BuckminsterException.wrap(finalException);
			}
		}
		finally
		{
			monitor.done();
			m_remoteService.releaseConnection();
		}

		return bom.contentEqual(bomReturned) ? bom : bomReturned;
	}

	public void setRecursiveResolve(boolean recursive)
	{
		m_recursive = recursive;
	}
}
