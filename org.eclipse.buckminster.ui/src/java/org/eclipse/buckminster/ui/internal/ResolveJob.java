/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.internal;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.materializer.IMaterializer;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.materializer.MaterializerJob;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.core.resolver.MainResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.ui.wizards.QueryWizard;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPartSite;

/**
 * @author Karel Brezina
 *
 */
public class ResolveJob extends Job
{
	private final IResolver m_resolver;

	private final boolean m_materialize;
	
	private final IWorkbenchPartSite m_site;

	public ResolveJob(ComponentQuery query, boolean materialize, IWorkbenchPartSite site, boolean continueOnError) throws CoreException
	{
		super("Resolving query");
		m_resolver = new MainResolver(new ResolutionContext(query));
		m_resolver.getContext().setContinueOnError(continueOnError);
		m_materialize = materialize;
		m_site = site;
		setUser(true);
		setPriority(LONG);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor)
	{
		ResolutionContext ctx = m_resolver.getContext();
		ComponentQuery query = ctx.getComponentQuery();

		try
		{
			Display display = m_site.getShell().getDisplay();
			ComponentRequest rootRequest = query.getRootRequest();
			final BillOfMaterials bom = m_resolver.resolve(rootRequest, monitor);
			IStatus status = ctx.getStatus();
			if(status.getSeverity() == IStatus.ERROR && !ctx.isContinueOnError())
				return status;
			CorePlugin.logWarningsAndErrors(status);

			if(!m_materialize)
			{
				display.asyncExec(new Runnable()
				{
					public void run()
					{
						QueryWizard.openWizard(m_site, m_resolver.getContext(), bom);
					}
				});
				return Status.OK_STATUS;
			}

			try
			{
				if(bom.isFullyResolved() || ctx.isContinueOnError())
				{
					setName("Materializing");
					
					// Just create a default mspec that materializes to the current
					// workspace
					//
					MaterializationSpecBuilder mspecBuilder = new MaterializationSpecBuilder();
					mspecBuilder.setName(bom.getViewName());
					mspecBuilder.setMaterializer(IMaterializer.WORKSPACE);
					MaterializationContext matCtx = new MaterializationContext(bom, mspecBuilder.createMaterializationSpec());

					MaterializerJob.run(matCtx);
					status = ctx.getStatus();
					if(status.getSeverity() == IStatus.ERROR && !ctx.isContinueOnError())
						return status;
					CorePlugin.logWarningsAndErrors(status);
				}
				return status;
			}
			finally
			{
				monitor.done();
			}
		}
		catch(Exception e)
		{
			return BuckminsterException.wrap(e).getStatus();
		}
	}
}

