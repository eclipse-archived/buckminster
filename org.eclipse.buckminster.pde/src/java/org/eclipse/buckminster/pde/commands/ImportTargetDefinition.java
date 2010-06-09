/*****************************************************************************
 * Copyright (c) 2008-2010, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.commands;

import java.io.InputStream;
import java.net.URI;
import java.util.List;

import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.SimpleErrorExitException;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.commands.WorkspaceCommand;
import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.pde.internal.core.target.TargetDefinitionPersistenceHelper;
import org.eclipse.pde.internal.core.target.provisional.ITargetDefinition;
import org.eclipse.pde.internal.core.target.provisional.ITargetHandle;
import org.eclipse.pde.internal.core.target.provisional.ITargetPlatformService;
import org.eclipse.pde.internal.core.target.provisional.LoadTargetDefinitionJob;

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
		if (unparsed.length > 1)
			throw new SimpleErrorExitException(Messages.Too_many_arguments);
		if (unparsed.length < 1)
			throw new SimpleErrorExitException(Messages.Too_few_arguments);
		setTargetPath(unparsed[0]);
	}

	@Override
	protected int internalRun(IProgressMonitor monitor) throws Exception {
		if (targetPath == null)
			return 0;

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

		if (importAsActive) {
			LoadTargetDefinitionJob job = new LoadTargetDefinitionJob(target);
			IStatus status = job.run(monitor);
			if (status.getSeverity() == IStatus.ERROR)
				throw new CoreException(status);
		}
		return 0;
	}
}
