/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.ui.internal;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.PlatformUI;
import org.xml.sax.SAXException;

public abstract class SaxableEditorInput implements IStorageEditorInput
{
	public IStorage getStorage() throws CoreException
	{
		return new IStorage()
		{
			public InputStream getContents() throws CoreException
			{
				try
				{
					return Utils.getInputStream(SaxableEditorInput.this.getContent());
				}
				catch(SAXException e)
				{
					throw BuckminsterException.wrap(e);
				}
			}

			public IPath getFullPath()
			{
				return null;
			}

			public String getName()
			{
				return SaxableEditorInput.this.getName();
			}

			public boolean isReadOnly()
			{
				return true;
			}

			@SuppressWarnings("unchecked")
			public Object getAdapter(Class adapter)
			{
				return null;
			}
			
		};
	}

	public ImageDescriptor getImageDescriptor()
	{
		IContentDescription cd = this.getContentDescription();
		return PlatformUI.getWorkbench().getEditorRegistry().getImageDescriptor(this.getName(), cd.getContentType());
	}

	public IContentDescription getContentDescription()
	{
		InputStream contents = null;
		try
		{
			contents = this.getStorage().getContents();
			return Platform.getContentTypeManager().getDescriptionFor(
					contents,
					this.getName(),
					IContentDescription.ALL);
		}
		catch(CoreException e)
		{
			throw new RuntimeException(e);
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			IOUtils.close(contents);
		}
	}

	public IPersistableElement getPersistable()
	{
		return null;
	}

	@SuppressWarnings("unchecked")
	public Object getAdapter(Class adapter)
	{
		return null;
	}

	protected abstract ISaxable getContent() throws CoreException;
}
