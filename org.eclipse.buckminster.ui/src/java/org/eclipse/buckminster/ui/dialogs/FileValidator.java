package org.eclipse.buckminster.ui.dialogs;

import org.eclipse.buckminster.ui.UiPlugin;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

public class FileValidator implements ISelectionStatusValidator {
	public IStatus validate(Object[] selection) {
		int scode = (selection.length > 0 && selection[0] instanceof IFile) ? IStatus.OK : IStatus.ERROR;
		return new Status(scode, UiPlugin.getID(), scode, "", null); //$NON-NLS-1$
	}
}
