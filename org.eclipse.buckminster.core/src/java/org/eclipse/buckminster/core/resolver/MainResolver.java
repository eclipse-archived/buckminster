/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.resolver;


import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.UnresolvedNode;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

/**
 * @author Thomas Hallgren
 */
public class MainResolver implements IResolver
{
	private static final int MAX_ITERATIONS = 8;	// TODO: Perhaps this should be a preference setting

	private final ResolutionContext m_context;

	private boolean m_recursiveResolve = true;

	public MainResolver(ResolutionContext context)
	{
		m_context = context;
	}

	public ResolutionContext getContext()
	{
		return m_context;
	}

	public boolean isRecursiveResolve()
	{
		return m_recursiveResolve;
	}

	public BillOfMaterials resolve(ComponentRequest request, IProgressMonitor monitor) throws CoreException
	{
		NodeQuery query = m_context.getNodeQuery(request);
		BillOfMaterials bom = BillOfMaterials.create(new UnresolvedNode(query.getQualifiedDependency()), m_context.getComponentQuery());
		return resolveRemaining(bom, monitor);
	}

	public BillOfMaterials resolve(IProgressMonitor monitor) throws CoreException
	{
		return resolve(m_context.getComponentQuery().getRootRequest(), monitor);
	}

	public BillOfMaterials resolveRemaining(BillOfMaterials bom, IProgressMonitor monitor) throws CoreException
	{
		if(bom.isFullyResolved())
			return bom;

		m_context.addTagInfo(bom.getRequest(), bom.getQuery().getTagInfo());
		IResolverFactory[] resolverFactories = ResolverFactoryMaintainer.getInstance().getActiveResolverFactories();
		int numFactories = resolverFactories.length;
		if(numFactories == 1)
			//
			// Only one factory? Just delegate to it.
			//
			return resolverFactories[0].createResolver(m_context).resolveRemaining(bom, monitor);

		monitor.beginTask(null, numFactories * 100);

		// Since all factories but the last should continue on error
		// we must save our status here. The last factory should use
		// our status always since it must fail if we are setup to
		// fail.
		//
		boolean continueOnError = m_context.isContinueOnError();
		m_context.setContinueOnError(true);
		try
		{
			IResolver[] resolvers = new IResolver[numFactories];
			for(int idx = 0; idx < numFactories; ++idx)
				resolvers[idx] = resolverFactories[idx].createResolver(m_context);

			for(int iteration = 0; iteration < MAX_ITERATIONS; ++iteration)
			{
				BillOfMaterials bomAtIterationStart = bom;
				for(int idx = 0; idx < numFactories; ++idx)
				{
					IResolver resolver = resolvers[idx];
					resolver.setRecursiveResolve(m_recursiveResolve);
					BillOfMaterials newBom = resolver.resolveRemaining(bom, MonitorUtils.subMonitor(monitor, 100));
					if(bom.equals(newBom))
						continue;

					bom = newBom;
					if(!m_recursiveResolve || bom.isFullyResolved())
					{
						// Something happened with the BOM so we consider ourselves done here
						//
						iteration = MAX_ITERATIONS;
						break;
					}
				}

				if(bomAtIterationStart.equals(bom))
					//
					// A full iteration over all resolvers gave us nothing. Then
					// it's safe to assume that the same thing would happen again
					//
					break;
			}

			if(!continueOnError)
			{
				IStatus status = m_context.getStatus();
				if(status.getSeverity() == IStatus.ERROR)
				{
					m_context.clearStatus();
					throw new CoreException(status);
				}
			}
			return bom;
		}
		finally
		{
			m_context.setContinueOnError(continueOnError);
			monitor.done();
		}
	}

	public void setRecursiveResolve(boolean flag)
	{
		m_recursiveResolve = flag;
	}
}
