/*****************************************************************************
 * (c) 2004-2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 ****************************************************************************/

package org.eclipse.buckminster.ui.editor;

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
 * An <code>IEditorInput</code> implementation for files external to the
 * workspace. Modelled after the Eclipse internal class
 * <code>JavaFileEditorInput</code>
 *
 * @author Thomas Hallgren
 */
public class ExternalFileEditorInput implements IPathEditorInput, ILocationProvider, IWorkbenchAdapter {
	private final String label;

	private final String tooltipText;

	private final File file;

	Dialog x;

	public ExternalFileEditorInput(File file) {
		this(file, file.getName(), file.getAbsolutePath());
	}

	public ExternalFileEditorInput(File file, String label, String tooltipText) {
		this.file = file;
		this.label = label;
		this.tooltipText = tooltipText;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (!(o instanceof ExternalFileEditorInput))
			return false;
		ExternalFileEditorInput that = (ExternalFileEditorInput) o;

		if (!this.getPath().equals(that.getPath()))
			return false;

		return true;
	}

	@Override
	public boolean exists() {
		return file.exists();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getAdapter(Class<T> adapter) {
		if (ILocationProvider.class.equals(adapter) || IWorkbenchAdapter.class.equals(adapter))
			return (T) this;
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

	protected IWorkbenchAdapter[] getChildren() {
		File[] childrenFiles = file.listFiles();
		int idx = childrenFiles.length;
		IWorkbenchAdapter[] children = new ExternalFileEditorInput[idx];
		while (--idx >= 0)
			children[idx] = new ExternalFileEditorInput(childrenFiles[idx]);
		return children;
	}

	@Override
	public Object[] getChildren(Object o) {
		return (o instanceof ExternalFileEditorInput) ? ((ExternalFileEditorInput) o).getChildren() : null;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	@Override
	public ImageDescriptor getImageDescriptor(Object o) {
		return (o instanceof IEditorInput) ? ((IEditorInput) o).getImageDescriptor() : null;
	}

	@Override
	public String getLabel(Object o) {
		return (o instanceof ExternalFileEditorInput) ? ((ExternalFileEditorInput) o).getName() : null;
	}

	@Override
	public String getName() {
		return label;
	}

	protected IWorkbenchAdapter getParent() {
		File parentFile = file.getParentFile();
		return parentFile == null ? null : new ExternalFileEditorInput(parentFile);
	}

	@Override
	public Object getParent(Object o) {
		return (o instanceof ExternalFileEditorInput) ? ((ExternalFileEditorInput) o).getParent() : null;
	}

	@Override
	public IPath getPath() {
		return Path.fromOSString(file.getAbsolutePath());
	}

	@Override
	public IPath getPath(Object o) {
		return (o instanceof IPathEditorInput) ? ((IPathEditorInput) o).getPath() : null;
	}

	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getToolTipText() {
		return tooltipText;
	}

	@Override
	public int hashCode() {
		return file.hashCode();
	}
}
