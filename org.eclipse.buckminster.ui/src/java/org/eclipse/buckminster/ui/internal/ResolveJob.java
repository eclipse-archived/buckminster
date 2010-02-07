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
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.materializer.MaterializationJob;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.resolver.IResolver;
import org.eclipse.buckminster.core.resolver.MainResolver;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.wizards.QueryWizard;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPartSite;

/**
 * @author Karel Brezina
 * 
 */
public class ResolveJob extends Job {
	private final IResolver resolver;

	private final boolean materialize;

	private final IWorkbenchPartSite site;

	public ResolveJob(ComponentQuery query, boolean materialize, IWorkbenchPartSite site, boolean continueOnError) throws CoreException {
		super(Messages.resolving_qurey);
		this.resolver = new MainResolver(new ResolutionContext(query));
		resolver.getContext().setContinueOnError(continueOnError);
		this.materialize = materialize;
		this.site = site;
		setUser(true);
		setPriority(BUILD);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		ResolutionContext ctx = resolver.getContext();
		ComponentQuery query = ctx.getComponentQuery();

		try {
			IProgressMonitor resolutionMonitor;
			if (materialize) {
				monitor.beginTask(null, 1000);
				resolutionMonitor = MonitorUtils.subMonitor(monitor, 500);
			} else
				resolutionMonitor = monitor;

			Display display = site.getShell().getDisplay();
			ComponentRequest rootRequest = query.getExpandedRootRequest(ctx);
			BillOfMaterials bom = null;
			IStatus status;
			try {
				bom = resolver.resolve(rootRequest, resolutionMonitor);
				status = ctx.getStatus();
			} catch (OperationCanceledException e) {
				status = ctx.getStatus();
			} catch (CoreException e) {
				status = e.getStatus();
			}

			CorePlugin.logWarningsAndErrors(status);
			ctx.emitWarningAndErrorTags();
			if (bom == null || (status.getSeverity() == IStatus.ERROR && !ctx.isContinueOnError()))
				return Status.OK_STATUS;

			if (!materialize) {
				final BillOfMaterials finalBom = bom;
				display.asyncExec(new Runnable() {
					public void run() {
						QueryWizard.openWizard(site, resolver.getContext(), finalBom);
					}
				});
				return Status.OK_STATUS;
			}

			try {
				if (bom.isFullyResolved(ctx) || ctx.isContinueOnError()) {
					setName(Messages.materializing);

					// Just create a default mspec that materializes to the
					// current
					// workspace
					//
					MaterializationSpecBuilder mspecBuilder = new MaterializationSpecBuilder();
					mspecBuilder.setName(bom.getViewName());
					bom.addMaterializationNodes(mspecBuilder);
					MaterializationContext matCtx = new MaterializationContext(bom, mspecBuilder.createMaterializationSpec(), ctx);
					MaterializationJob.runDelegated(matCtx, MonitorUtils.subMonitor(monitor, 500));
				}
				return status;
			} finally {
				monitor.done();
			}
		} catch (Exception e) {
			return BuckminsterException.wrap(e).getStatus();
		}
	}
}
