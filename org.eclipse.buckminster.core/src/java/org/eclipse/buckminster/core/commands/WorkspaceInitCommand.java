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

/**
 * @author Thomas Hallgren
 */
public abstract class WorkspaceInitCommand extends WorkspaceCommand
{
	static final OptionDescriptor CONTINUE_ON_ERROR = new OptionDescriptor('C', "continueonerror",
		OptionValueType.NONE);
	static final OptionDescriptor MATERIALIZER = new OptionDescriptor('M', "materializer",
			OptionValueType.REQUIRED);
	static final OptionDescriptor TEMPLATE = new OptionDescriptor('T', "template",
		OptionValueType.REQUIRED);

	private static boolean isFolderEmpty(File folder)
	{
		String[] names = folder.list();
		if(names != null)
		{
			int idx = names.length;
			while(--idx >= 0)
				if(!isFolderEmpty(new File(folder, names[idx])))
					return false;
		}
		return !folder.isFile();
	}

	private boolean m_continueOnError;

	private String m_materializer;

	private URL m_template;

	public void setContinueOnError(boolean flag)
	{
		m_continueOnError = flag;
	}

	public void setMaterializer(String materializer)
	{
		m_materializer = materializer;
	}

	public void setTemplate(URL template)
	{
		m_template = template;
	}

	protected String getMaterializer()
	{
		return m_materializer;
	}

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception
	{
		appendHere.add(CONTINUE_ON_ERROR);
		appendHere.add(MATERIALIZER);
		appendHere.add(TEMPLATE);
	}

	@Override
	protected void handleOption(Option option) throws Exception
	{
		if(option.is(CONTINUE_ON_ERROR))
			setContinueOnError(true);
		else if(option.is(TEMPLATE))
			setTemplate(URLUtils.normalizeToURL(option.getValue()));
		else if(option.is(MATERIALIZER))
			setMaterializer(option.getValue());
	}

	/**
	 * Initialize the current workspace from a template workspace. This
	 * method must be called very early in the execution process. The
	 * current workspace must be empty. It may well exist but it cannot
	 * contain any files, just empty folders.
	 *
	 * @param template
	 */
	protected void initWorkspaceFromTemplate() throws Exception
	{
		try
		{
			File wsRoot = FileUtils.getFile(FileLocator.toFileURL(Platform.getInstanceLocation().getURL()));
			if(!isFolderEmpty(wsRoot))
				throw new SimpleErrorExitException("Workspace at " + wsRoot + " is not empty");

			IProgressMonitor nullMon = new NullProgressMonitor();
			URL template = FileLocator.toFileURL(m_template);
			String path = template.getPath();
			File fileTemplate;
			if(path.endsWith(".zip") || path.endsWith(".jar"))
			{
				File dest = FileUtils.createTempFolder("bmtpl", ".tmp");
				InputStream input = null;
				try
				{
					input = new BufferedInputStream(template.openStream());
					FileUtils.unzip(input, null, dest, ConflictResolution.UPDATE, nullMon);
				}
				finally
				{
					IOUtils.close(input);
				}
				fileTemplate = dest;
			}
			else
			{
				fileTemplate = FileUtils.getFile(template);
				if(fileTemplate == null)
					throw new SimpleErrorExitException(
						"Only zip and jar files allowed for remote workspace templates");

				if(!fileTemplate.isAbsolute())
					fileTemplate = fileTemplate.getAbsoluteFile();

				if(!fileTemplate.isDirectory())
					throw new SimpleErrorExitException("Only folders, zip, and jar files can be uses as workspace template");
			}
			FileUtils.deepCopyUnchecked(fileTemplate, wsRoot, nullMon);
			ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, nullMon);
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	protected abstract int internalRun(boolean continueOnError, IProgressMonitor monitor) throws Exception;

	@Override
	protected void initWorkspace(IProgressMonitor monitor) throws Exception
	{
		if(m_template != null)
			initWorkspaceFromTemplate();
		super.initWorkspace(monitor);
	}

	@Override
	protected final int internalRun(IProgressMonitor monitor) throws Exception
	{
		return internalRun(m_continueOnError, monitor);
	}

	protected boolean isContinueOnError()
	{
		return m_continueOnError;
	}
}
