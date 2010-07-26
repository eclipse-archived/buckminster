/*****************************************************************************
 * Copyright (c) 2008-2010, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.commands;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.core.commands.WorkspaceCommand;
import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.pde.PDEPlugin;
import org.eclipse.buckminster.pde.internal.PDETargetPlatform;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.equinox.p2.core.IProvisioningAgent;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.internal.core.target.DirectoryBundleContainer;
import org.eclipse.pde.internal.core.target.IUBundleContainer;
import org.eclipse.pde.internal.core.target.TargetDefinitionPersistenceHelper;
import org.eclipse.pde.internal.core.target.provisional.IBundleContainer;
import org.eclipse.pde.internal.core.target.provisional.ITargetDefinition;
import org.eclipse.pde.internal.core.target.provisional.ITargetHandle;
import org.eclipse.pde.internal.core.target.provisional.ITargetPlatformService;

@SuppressWarnings("restriction")
public class ImportTargetDefinition extends WorkspaceCommand {
	private String targetPath;

	private boolean importAsActive;

	static private final OptionDescriptor OPTION_ACTIVE = new OptionDescriptor('A', "active", OptionValueType.NONE); //$NON-NLS-1$

	public String getTargetPath() {
		return targetPath;
	}

	public boolean isImportAsActive() {
		return importAsActive;
	}

	public void setImportAsActive(boolean importAsActive) {
		this.importAsActive = importAsActive;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception {
		super.getOptionDescriptors(appendHere);
		appendHere.add(OPTION_ACTIVE);
	}

	@Override
	protected void handleOption(Option option) throws Exception {
		if (option.is(OPTION_ACTIVE))
			setImportAsActive(true);
		else
			super.handleOption(option);
	}

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception {
		// if (unparsed.length > 1)
		// throw new
		// SimpleErrorExitException(org.eclipse.buckminster.core.Messages.Too_many_arguments);
		// if (unparsed.length < 1)
		// throw new
		// SimpleErrorExitException(org.eclipse.buckminster.core.Messages.Too_few_arguments);
		setTargetPath(unparsed[0]);
	}

	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception {
		if (targetPath == null) {
			MonitorUtils.complete(monitor);
			return 0;
		}

		Buckminster bucky = Buckminster.getDefault();
		ITargetPlatformService service = bucky.getService(ITargetPlatformService.class);
		URI uri = URLUtils.normalizeToURI(targetPath, false);

		ITargetHandle handle = null;
		if ("file".equalsIgnoreCase(uri.getScheme())) { //$NON-NLS-1$
			IPath path = Path.fromPortableString(uri.getPath());
			if (path.segmentCount() > 1) {
				IContainer container = ResourcesPlugin.getWorkspace().getRoot().getContainerForLocation(path.removeLastSegments(1));
				if (container != null)
					handle = service.getTarget(container.getFile(new Path(path.lastSegment())));
			}
		}

		ITargetDefinition target;
		if (handle == null) {
			// The target is external to the workspace so import it into
			// a local target
			target = service.newTarget();
			InputStream input = null;
			try {
				input = DownloadManager.read(uri.toURL(), null);
				TargetDefinitionPersistenceHelper.initFromXML(target, input);
			} finally {
				IOUtils.close(input);
			}
			service.saveTargetDefinition(target);
		} else
			target = handle.getTargetDefinition();

		// Perform some sanity checks
		IBundleContainer[] containers = target.getBundleContainers();
		if (containers == null)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.target_0_has_no_containers, targetPath));

		SubMonitor mon = SubMonitor.convert(monitor, containers.length * 100 + (importAsActive ? 0 : 50));
		for (IBundleContainer container : containers) {
			SubMonitor child = mon.newChild(50);
			if (container instanceof IUBundleContainer)
				verifyIUBundleContainer(target, (IUBundleContainer) container, child);
			else if (container instanceof DirectoryBundleContainer)
				verifyDirectoryBundleContainer(target, (DirectoryBundleContainer) container, child);
			else
				verifyBundleContainer(target, container, child);
			MonitorUtils.testCancelStatus(mon);
		}

		if (importAsActive)
			PDETargetPlatform.setTargetActive(target, mon.newChild(50));
		MonitorUtils.done(monitor);
		return 0;
	}

	private void verifyBundleContainer(ITargetDefinition target, IBundleContainer container, SubMonitor monitor) throws CoreException {
		IStatus status = container.resolve(target, monitor);
		if (status.getSeverity() == IStatus.ERROR)
			throw new CoreException(status);
	}

	private void verifyDirectoryBundleContainer(ITargetDefinition target, DirectoryBundleContainer container, SubMonitor monitor)
			throws CoreException {
		String path = container.getLocation(true);
		if (path == null)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.dc_of_target_0_lacks_location, targetPath));

		File tpDir = new File(path);
		if (!tpDir.exists()) {
			PDEPlugin.getLogger().warning(NLS.bind(Messages.tpdir_0_does_not_exist, path));
			tpDir.mkdirs();
			if (!tpDir.isDirectory())
				throw BuckminsterException.fromMessage(NLS.bind(Messages.unable_to_create_tpdir_0, path));
		}
		verifyBundleContainer(target, container, monitor);
	}

	private void verifyIUBundleContainer(ITargetDefinition target, IUBundleContainer container, SubMonitor monitor) throws CoreException {
		URI[] repositories = container.getRepositories();
		if (repositories == null || repositories.length == 0)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.ssc_of_target_0_lacks_repository, targetPath));

		Buckminster bucky = Buckminster.getDefault();
		IProvisioningAgent agent = bucky.getService(IProvisioningAgent.class);
		IMetadataRepositoryManager repoManager = (IMetadataRepositoryManager) agent.getService(IMetadataRepositoryManager.SERVICE_NAME);
		int top = repositories.length;
		monitor.beginTask(null, 100 + top * 100);
		MultiStatus problems = new MultiStatus(PDEPlugin.getPluginId(), 0,
				NLS.bind(Messages.unable_to_load_all_units_for_ssc_of_target_0, targetPath), null);
		for (int idx = 0; idx < top; ++idx) {
			try {
				repoManager.loadRepository(repositories[idx], monitor.newChild(100));
			} catch (CoreException e) {
				problems.add(e.getStatus());
			}
		}
		try {
			verifyBundleContainer(target, container, monitor.newChild(100));
		} catch (CoreException e) {
			problems.add(e.getStatus());
		}
		if (problems.getSeverity() == IStatus.ERROR)
			throw new CoreException(problems);
		monitor.done();
	}
}
