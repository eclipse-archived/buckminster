/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public class StorageManager {
	private static StorageManager defaultManager;

	public static synchronized StorageManager getDefault() throws CoreException {
		if (defaultManager == null) {
			defaultManager = new StorageManager(CorePlugin.getDefault().getStateLocation().toFile());
			defaultManager.initialize();
		}
		return defaultManager;
	}

	private final ISaxableStorage<CSpec> cspecs;

	private final ISaxableStorage<WorkspaceBinding> wsBindings;

	private final ISaxableStorage<Resolution> resolutions;

	private final ISaxableStorage<Provider> providers;

	private final ISaxableStorage<Materialization> materializations;

	public StorageManager(File baseLocation) throws CoreException {
		CorePlugin plugin = CorePlugin.getDefault();
		IParserFactory pf = plugin.getParserFactory();

		// NOTE: The order in which these entries are created and cleared
		// in case of changes is important. It is in depencency order.
		//
		providers = new MemoryStorage<Provider>(Provider.class);

		cspecs = new MemoryStorage<CSpec>(CSpec.class);

		resolutions = new MemoryStorage<Resolution>(Resolution.class);

		materializations = new FileStorage<Materialization>(new File(baseLocation, Materialization.TAG), pf.getMaterializationParser(),
				Materialization.class, Materialization.SEQUENCE_NUMBER);

		wsBindings = new FileStorage<WorkspaceBinding>(new File(baseLocation, WorkspaceBinding.TAG), pf.getWorkspaceBindingParser(false),
				WorkspaceBinding.class, WorkspaceBinding.SEQUENCE_NUMBER);
	}

	public ISaxableStorage<CSpec> getCSpecs() throws CoreException {
		return cspecs;
	}

	public ISaxableStorage<Materialization> getMaterializations() throws CoreException {
		return materializations;
	}

	public ISaxableStorage<Provider> getProviders() throws CoreException {
		return providers;
	}

	public ISaxableStorage<Resolution> getResolutions() throws CoreException {
		return resolutions;
	}

	public ISaxableStorage<WorkspaceBinding> getWorkspaceBindings() throws CoreException {
		return wsBindings;
	}

	private void initialize() throws CoreException {
		if (materializations.sequenceChanged() || resolutions.sequenceChanged() || cspecs.sequenceChanged() || providers.sequenceChanged()
				|| wsBindings.sequenceChanged()) {
			// Don't use another thread here. It will deadlock
			//
			WorkspaceInfo.forceRefreshOnAll(new NullProgressMonitor());
		}
	}
}
