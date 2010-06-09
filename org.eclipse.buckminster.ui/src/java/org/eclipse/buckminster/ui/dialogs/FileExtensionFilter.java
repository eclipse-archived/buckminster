package org.eclipse.buckminster.ui.dialogs;

import java.util.Locale;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class FileExtensionFilter extends ViewerFilter {
	private String extension;

	public FileExtensionFilter(String extension) {
		this.extension = extension;
	}

	@Override
	public boolean select(Viewer viewer, Object parent, Object element) {
		if (element instanceof IFile)
			return ((IFile) element).getName().toLowerCase(Locale.ENGLISH).endsWith(extension);

		if (!(element instanceof IContainer))
			return false;

		if (element instanceof IProject && !((IProject) element).isOpen())
			return false;

		try {
			IResource[] resources = ((IContainer) element).members();
			for (int i = 0; i < resources.length; i++) {
				if (select(viewer, parent, resources[i]))
					return true;
			}
		} catch (CoreException e) {
		}
		return false;
	}

}
