/*****************************************************************************
 * (c) 2004-2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 ****************************************************************************/

package org.eclipse.buckminster.ui;

/*****************************************************************************
 * (c) 2004-2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 ****************************************************************************/
import java.io.File;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.editors.text.ILocationProvider;
import org.eclipse.ui.model.IWorkbenchAdapter;

/**
 * An <code>IEditorInput</code> implementation for files external to the workspace. Modelled after the
 * Eclipse internal class <code>JavaFileEditorInput</code>
 *
 * @author Thomas Hallgren
 */
public class ExternalFileEditorInput implements IPathEditorInput, ILocationProvider, IWorkbenchAdapter
{
	private final String m_label;
	private final String m_tooltipText;
	private final File m_file;
Dialog x;
	public ExternalFileEditorInput(File file)
	{
		this(file, file.getName(), file.getAbsolutePath());
	}

	public ExternalFileEditorInput(File file, String label, String tooltipText)
	{
		m_file = file;
		m_label = label;
		m_tooltipText = tooltipText;
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == this)
			return true;

		if (!(o instanceof ExternalFileEditorInput))
			return false;
		ExternalFileEditorInput that = (ExternalFileEditorInput)o;

		if (!this.getPath().equals(that.getPath()))
			return false;

		return true;
	}

	public boolean exists()
	{
		return m_file.exists();
	}

	@SuppressWarnings("unchecked")
	public Object getAdapter(Class adapter)
	{
		if(ILocationProvider.class.equals(adapter)
		|| IWorkbenchAdapter.class.equals(adapter))
			return this;
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

	public Object[] getChildren(Object o)
	{
		return (o instanceof ExternalFileEditorInput) ? ((ExternalFileEditorInput)o).getChildren() : null;
	}

	public ImageDescriptor getImageDescriptor()
	{
		return null;
	}

	public ImageDescriptor getImageDescriptor(Object o)
	{
		return (o instanceof IEditorInput) ? ((IEditorInput)o).getImageDescriptor() : null;
	}

	public String getLabel(Object o)
	{
		return (o instanceof ExternalFileEditorInput) ? ((ExternalFileEditorInput)o).getName() : null;
	}

	public String getName()
	{
		return m_label;
	}

	public Object getParent(Object o)
	{
		return (o instanceof ExternalFileEditorInput) ? ((ExternalFileEditorInput)o).getParent() : null;
	}

	public IPath getPath()
	{
		return Path.fromOSString(m_file.getAbsolutePath());
	}

	public IPath getPath(Object o)
	{
		return (o instanceof IPathEditorInput) ? ((IPathEditorInput)o).getPath() : null;
	}

	public IPersistableElement getPersistable()
	{
		return null;
	}

	public String getToolTipText()
	{
		return m_tooltipText;
	}

	@Override
	public int hashCode()
	{
		return m_file.hashCode();
	}

	protected IWorkbenchAdapter[] getChildren()
	{
		File[] childrenFiles = m_file.listFiles();
		int idx = childrenFiles.length;
		IWorkbenchAdapter[] children = new ExternalFileEditorInput[idx];
		while(--idx >= 0)
			children[idx] = new ExternalFileEditorInput(childrenFiles[idx]);
		return children;
	}

	protected IWorkbenchAdapter getParent()
	{
		File parentFile = m_file.getParentFile();
		return parentFile == null ? null : new ExternalFileEditorInput(parentFile);
	}
}