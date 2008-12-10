/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.buckminster.ui.editor.ExternalFileEditorInput;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.FileEditorInput;

/**
 * A runnable capable of saving a query to a certain location. The location must be relative to the root of the local
 * file-system. Once saved, the resulting {@link org.eclipse.ui.IEditorInput IEditorInput} can be obtained through a
 * call to {@link #getSavedInput()}. That editor input will either be an
 * {@link org.eclipse.buckminster.ui.editor.ExternalFileEditorInput ExternalFileEditorInput} or a
 * {@link org.eclipse.ui.IFileEditorInput IFileEditorInput} depending on if the location could be mapped to a file in an
 * existing project or not.
 * 
 * @author Thomas Hallgren
 */
public class SaveRunnable implements IRunnableWithProgress
{
	private final ISaxable m_saxable;

	private final IPath m_location;

	private IEditorInput m_newInput;

	/**
	 * Creates a new instance that will save the <code>query</code> at the specificed <code>location</code>.
	 * 
	 * @param saxable
	 *            The SAX output enabled element to be saved.
	 * @param location
	 *            A location relative to the root of the local file-system.
	 */
	public SaveRunnable(ISaxable saxable, IPath location)
	{
		m_saxable = saxable;
		m_location = location;
	}

	public IEditorInput getSavedInput()
	{
		return m_newInput;
	}

	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
	{
		try
		{
			if(m_location.segmentCount() > 1)
			{
				IContainer container = ResourcesPlugin.getWorkspace().getRoot().getContainerForLocation(
						m_location.removeLastSegments(1));
				if(container != null)
				{
					// Workspace file.
					//
					InputStream stream = null;
					try
					{
						IFile file = container.getFile(new Path(m_location.lastSegment()));
						stream = Utils.getInputStream(m_saxable);
						if(file.exists())
							file.setContents(stream, IResource.KEEP_HISTORY, monitor);
						else
							file.create(stream, false, monitor);
						m_newInput = new FileEditorInput(file);
					}
					finally
					{
						IOUtils.close(stream);
					}
					return;
				}
			}

			// External file.
			//
			OutputStream stream = null;
			try
			{
				File file = m_location.toFile();
				stream = new FileOutputStream(file);
				Utils.serialize(m_saxable, stream);
				m_newInput = new ExternalFileEditorInput(file);
			}
			finally
			{
				IOUtils.close(stream);
			}
		}
		catch(OperationCanceledException e)
		{
			throw new InterruptedException();
		}
		catch(Throwable e)
		{
			throw new InvocationTargetException(e);
		}
	}
}
