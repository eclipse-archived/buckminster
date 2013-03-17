/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.materializer;

import java.util.List;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.IJobInfo;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public class MaterializerJob extends Job implements IJobInfo {
	private final IMaterializer materializer;

	private final MaterializationContext context;

	private final List<Resolution> resolutions;

	public MaterializerJob(String id, IMaterializer materializer, List<Resolution> resolutions, MaterializationContext context) {
		super(id + " materializer"); //$NON-NLS-1$
		if (resolutions.size() < 1)
			throw new IllegalArgumentException();

		this.materializer = materializer;
		this.context = context;
		this.resolutions = resolutions;

		// Report using the standard job reporter.
		//
		this.setSystem(false);
		this.setUser(false);
		this.setPriority(LONG);
	}

	@Override
	public boolean belongsTo(Object family) {
		return context == family;
	}

	@Override
	public String getOperationName() {
		Resolution lastResolution = resolutions.get(resolutions.size() - 1);
		return NLS.bind(Messages.Materialization_of_0, lastResolution.getComponentIdentifier().toString());
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		try {
			materializer.materialize(resolutions, context, monitor);
		} catch (CoreException e) {
			context.addRequestStatus(resolutions.get(resolutions.size() - 1).getRequest(), BuckminsterException.wrap(e).getStatus());
			if (!context.isContinueOnError())
				return e.getStatus();
		}
		return Status.OK_STATUS;
	}
}
