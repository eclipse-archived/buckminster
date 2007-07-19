/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.materializer;

import java.util.List;

import org.eclipse.buckminster.core.helpers.IJobInfo;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * @author Thomas Hallgren
 */
public class MaterializerJob extends Job implements IJobInfo
{
	private final IMaterializer m_materializer;
	private final MaterializationContext m_context;
	private final List<Resolution> m_resolutions;

	public MaterializerJob(String id, IMaterializer materializer, List<Resolution> resolutions, MaterializationContext context)
	{
		super(id + " materializer");
		m_materializer = materializer;
		m_context = context;
		m_resolutions = resolutions;

		// Report using the standard job reporter.
		//
		this.setSystem(false);
		this.setUser(true);
		this.setPriority(LONG);
	}

	@Override
	public boolean belongsTo(Object family)
	{
		return m_context == family;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor)
	{
		try
		{
			m_materializer.materialize(m_resolutions, m_context, monitor);
		}
		catch(Exception e)
		{
			m_context.addException(BuckminsterException.wrap(e).getStatus());
		}
		return Status.OK_STATUS;
	}

	public String getOperationName()
	{
		if(m_resolutions == null || m_resolutions.size() == 0)
		{
			return getName();
		}
		
		Resolution lastResolution = m_resolutions.get(m_resolutions.size() - 1);
		
		try
		{
			if(lastResolution == null || lastResolution.getComponentIdentifier() == null)
			{
				return getName();
			}
			return "Materialization of " + lastResolution.getComponentIdentifier().toString();
		}
		catch(CoreException e)
		{
			return getName();
		}
	}
}
