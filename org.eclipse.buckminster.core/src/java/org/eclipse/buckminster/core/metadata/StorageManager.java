/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata;

import java.io.File;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.parser.IParserFactory;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class StorageManager
{
	private static final StorageManager s_defaultManager;

	private boolean m_initialized;

	static
	{
		try
		{
			s_defaultManager = new StorageManager(CorePlugin.getDefault().getStateLocation().toFile());
		}
		catch(Exception e)
		{
			throw new ExceptionInInitializerError(e);
		}
	}

	private final ISaxableStorage<CSpec> m_cspecs;

	private final ISaxableStorage<Resolution> m_resolutions;

	private final ISaxableStorage<Provider> m_providers;

	private final ISaxableStorage<ComponentQuery> m_queries;

	private final ISaxableStorage<DepNode> m_depNodes;

	private final ISaxableStorage<Materialization> m_materializations;

	public StorageManager(File baseLocation) throws CoreException, SAXException
	{
		CorePlugin plugin = CorePlugin.getDefault();
		IParserFactory pf = plugin.getParserFactory();

		// NOTE: The order in which these entries are created and cleared
		// in case of changes is important. It is in depencency order.
		//
		m_providers = new FileStorage<Provider>(new File(baseLocation, Provider.TAG),
			pf.getProviderParser(false), Provider.class, Provider.SEQUENCE_NUMBER);

		m_cspecs = new FileStorage<CSpec>(new File(baseLocation, CSpec.TAG), pf.getCSpecParser(false),
			CSpec.class, CSpec.SEQUENCE_NUMBER);

		m_resolutions = new FileStorage<Resolution>(new File(baseLocation, Resolution.TAG),
			pf.getResolutionParser(), Resolution.class, Resolution.SEQUENCE_NUMBER);

		m_materializations = new FileStorage<Materialization>(new File(baseLocation, Materialization.TAG),
			pf.getMaterializationParser(), Materialization.class, Materialization.SEQUENCE_NUMBER);

		m_depNodes = new FileStorage<DepNode>(new File(baseLocation, DepNode.TAG), pf.getDepNodeParser(),
			DepNode.class, DepNode.SEQUENCE_NUMBER);

		m_queries = new FileStorage<ComponentQuery>(new File(baseLocation, ComponentQuery.TAG),
			pf.getComponentQueryParser(false), ComponentQuery.class, ComponentQuery.SEQUENCE_NUMBER);
	}

	public static StorageManager getDefault()
	{
		return s_defaultManager;
	}

	public synchronized ISaxableStorage<CSpec> getCSpecs() throws CoreException
	{
		initialize();
		return m_cspecs;
	}

	public synchronized ISaxableStorage<Materialization> getMaterializations() throws CoreException
	{
		initialize();
		return m_materializations;
	}

	public synchronized ISaxableStorage<ComponentQuery> getQueries() throws CoreException
	{
		initialize();
		return m_queries;
	}

	public synchronized ISaxableStorage<Resolution> getResolutions() throws CoreException
	{
		initialize();
		return m_resolutions;
	}

	public synchronized ISaxableStorage<Provider> getProviders() throws CoreException
	{
		initialize();
		return m_providers;
	}

	public synchronized ISaxableStorage<DepNode> getDepNodes() throws CoreException
	{
		initialize();
		return m_depNodes;
	}

	class MetadataRefreshJob extends Job
	{
		public MetadataRefreshJob()
		{
			super("Meta-data refresh");
			setPriority(Job.BUILD);
		}

		@Override
		public boolean belongsTo(Object family)
		{
			return family == this;
		}

		@Override
		public IStatus run(IProgressMonitor monitor)
		{
			synchronized(StorageManager.this)
			{
				m_initialized = true;
				try
				{
					WorkspaceInfo.forceRefreshOnAll(monitor);
				}
				finally
				{
					StorageManager.this.notify();
				}
			}
			return Status.OK_STATUS;
		}
	}

	private void initialize() throws CoreException
	{
		if(!m_initialized)
		{
			if(m_materializations.sequenceChanged()
				|| m_resolutions.sequenceChanged() || m_cspecs.sequenceChanged()
				|| m_providers.sequenceChanged())
			{
				MetadataRefreshJob refreshJob = new MetadataRefreshJob();
				refreshJob.schedule();
				try
				{
					wait();
				}
				catch(InterruptedException e)
				{
					throw new OperationCanceledException();
				}
			}
		}
	}
}
