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
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.metadata.model.WorkspaceBinding;
import org.eclipse.buckminster.core.parser.IParserFactory;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class StorageManager
{
	private static StorageManager s_defaultManager;

	private final ISaxableStorage<CSpec> m_cspecs;

	private final ISaxableStorage<WorkspaceBinding> m_wsBindings;

	private final ISaxableStorage<Resolution> m_resolutions;

	private final ISaxableStorage<Provider> m_providers;

	private final ISaxableStorage<Materialization> m_materializations;

	public StorageManager(File baseLocation) throws CoreException
	{
		CorePlugin plugin = CorePlugin.getDefault();
		IParserFactory pf = plugin.getParserFactory();

		// NOTE: The order in which these entries are created and cleared
		// in case of changes is important. It is in depencency order.
		//
		try
		{
			m_providers = new FileStorage<Provider>(new File(baseLocation, Provider.TAG),
				pf.getProviderParser(false), Provider.class, Provider.SEQUENCE_NUMBER);
	
			m_cspecs = new FileStorage<CSpec>(new File(baseLocation, CSpec.TAG), pf.getCSpecParser(false),
				CSpec.class, CSpec.SEQUENCE_NUMBER);
	
			m_resolutions = new FileStorage<Resolution>(new File(baseLocation, Resolution.TAG),
				pf.getResolutionParser(), Resolution.class, Resolution.SEQUENCE_NUMBER);
	
			m_materializations = new FileStorage<Materialization>(new File(baseLocation, Materialization.TAG),
				pf.getMaterializationParser(), Materialization.class, Materialization.SEQUENCE_NUMBER);
	
			m_wsBindings = new FileStorage<WorkspaceBinding>(new File(baseLocation, WorkspaceBinding.TAG),
				pf.getWorkspaceBindingParser(false), WorkspaceBinding.class, WorkspaceBinding.SEQUENCE_NUMBER);
		}
		catch(SAXException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public static synchronized StorageManager getDefault() throws CoreException
	{
		if(s_defaultManager == null)
		{
			s_defaultManager = new StorageManager(CorePlugin.getDefault().getStateLocation().toFile());
			s_defaultManager.initialize();
		}
		return s_defaultManager;
	}

	public ISaxableStorage<CSpec> getCSpecs() throws CoreException
	{
		return m_cspecs;
	}

	public ISaxableStorage<Materialization> getMaterializations() throws CoreException
	{
		return m_materializations;
	}

	public ISaxableStorage<Resolution> getResolutions() throws CoreException
	{
		return m_resolutions;
	}

	public ISaxableStorage<Provider> getProviders() throws CoreException
	{
		return m_providers;
	}

	public ISaxableStorage<WorkspaceBinding> getWorkspaceBindings() throws CoreException
	{
		return m_wsBindings;
	}

	private void initialize() throws CoreException
	{
		if(m_materializations.sequenceChanged()
		|| m_resolutions.sequenceChanged()
		|| m_cspecs.sequenceChanged()
		|| m_providers.sequenceChanged()
		|| m_wsBindings.sequenceChanged())
		{
			// Don't use another thread here. It will deadlock
			//
			WorkspaceInfo.forceRefreshOnAll(new NullProgressMonitor());
		}
	}
}
