/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.commands;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.SimpleErrorExitException;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.mspec.ConflictResolution;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public abstract class WorkspaceInitCommand extends WorkspaceCommand {
	static final OptionDescriptor CONTINUE_ON_ERROR = new OptionDescriptor('C', "continueonerror", OptionValueType.NONE); //$NON-NLS-1$

	static final OptionDescriptor MATERIALIZER = new OptionDescriptor('M', "materializer", OptionValueType.REQUIRED); //$NON-NLS-1$

	static final OptionDescriptor TEMPLATE = new OptionDescriptor('T', "template", OptionValueType.REQUIRED); //$NON-NLS-1$

	private static boolean isFolderEmpty(File folder) {
		String[] names = folder.list();
		if (names != null) {
			int idx = names.length;
			while (--idx >= 0)
				if (!isFolderEmpty(new File(folder, names[idx])))
					return false;
		}
		return !folder.isFile();
	}

	private boolean continueOnError;

	private String materializer;

	private URL template;

	public void setContinueOnError(boolean flag) {
		continueOnError = flag;
	}

	public void setMaterializer(String materializer) {
		this.materializer = materializer;
	}

	public void setTemplate(URL template) {
		this.template = template;
	}

	protected String getMaterializer() {
		return materializer;
	}

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception {
		super.getOptionDescriptors(appendHere);
		appendHere.add(CONTINUE_ON_ERROR);
		appendHere.add(MATERIALIZER);
		appendHere.add(TEMPLATE);
	}

	@Override
	protected void handleOption(Option option) throws Exception {
		if (option.is(CONTINUE_ON_ERROR))
			setContinueOnError(true);
		else if (option.is(TEMPLATE))
			setTemplate(URLUtils.normalizeToURL(option.getValue()));
		else if (option.is(MATERIALIZER))
			setMaterializer(option.getValue());
		else
			super.handleOption(option);
	}

	@Override
	protected void initWorkspace(IProgressMonitor monitor) throws Exception {
		if (template != null)
			initWorkspaceFromTemplate();
		super.initWorkspace(monitor);
	}

	/**
	 * Initialize the current workspace from a template workspace. This method
	 * must be called very early in the execution process. The current workspace
	 * must be empty. It may well exist but it cannot contain any files, just
	 * empty folders.
	 * 
	 * @param template
	 */
	protected void initWorkspaceFromTemplate() throws Exception {
		try {
			File wsRoot = FileUtils.getFile(FileLocator.toFileURL(Platform.getInstanceLocation().getURL()));
			if (!isFolderEmpty(wsRoot))
				throw new SimpleErrorExitException(NLS.bind(Messages.Workspace_at_0_is_not_empty, wsRoot));

			IProgressMonitor nullMon = new NullProgressMonitor();
			URL templateURL = FileLocator.toFileURL(template);
			String path = templateURL.getPath();
			File fileTemplate;
			if (path.endsWith(".zip") || path.endsWith(".jar")) //$NON-NLS-1$ //$NON-NLS-2$
			{
				File dest = FileUtils.createTempFolder("bmtpl", ".tmp"); //$NON-NLS-1$ //$NON-NLS-2$
				InputStream input = null;
				try {
					input = new BufferedInputStream(templateURL.openStream());
					FileUtils.unzip(input, null, dest, ConflictResolution.UPDATE, nullMon);
				} finally {
					IOUtils.close(input);
				}
				fileTemplate = dest;
			} else {
				fileTemplate = FileUtils.getFile(templateURL);
				if (fileTemplate == null)
					throw new SimpleErrorExitException(Messages.Only_zip_and_jar_files_allowed_for_remote_workspace_templates);

				if (!fileTemplate.isAbsolute())
					fileTemplate = fileTemplate.getAbsoluteFile();

				if (!fileTemplate.isDirectory())
					throw new SimpleErrorExitException(Messages.Only_folders_zip_and_jar_files_can_be_uses_as_workspace_template);
			}
			FileUtils.deepCopyUnchecked(fileTemplate, wsRoot, nullMon);
			ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, nullMon);
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		}
	}

	protected abstract int internalRun(boolean contOnError, IProgressMonitor monitor) throws Exception;

	@Override
	protected final int internalRun(IProgressMonitor monitor) throws Exception {
		return internalRun(continueOnError, monitor);
	}

	protected boolean isContinueOnError() {
		return continueOnError;
	}
}
