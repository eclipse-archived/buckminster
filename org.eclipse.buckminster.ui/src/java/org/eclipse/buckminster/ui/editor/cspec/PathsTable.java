/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.editor.cspec;

import java.util.List;

import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.general.editor.IValidator;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.buckminster.ui.general.editor.simple.SimpleTable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * @author Karel Brezina
 * 
 */
public class PathsTable extends SimpleTable<PathWrapper> {

	public PathsTable(List<PathWrapper> data, boolean readOnly) {
		super(data, readOnly);
	}

	@Override
	public PathWrapper createRowClass() {
		return new PathWrapper();
	}

	@Override
	public String[] getColumnHeaders() {
		return new String[] { Messages.path };
	}

	@Override
	public int[] getColumnWeights() {
		return new int[] { 1 };
	}

	@Override
	public IValidator getFieldValidator(int idx) {
		switch (idx) {
			case 0:
				return SimpleTable.createNotEmptyStringValidator(Messages.path_cannot_be_empty);
			default:
				return SimpleTable.getEmptyValidator();
		}
	}

	@Override
	public Object[] toRowArray(PathWrapper t) {
		return new Object[] { t.getPath().toOSString() };
	}

	@Override
	public void updateRowClass(PathWrapper path, Object[] args) throws ValidatorException {
		String pathString = (String) args[0];
		path.setPath(pathString == null ? null : (IPath) Path.fromOSString(pathString));
	}
}

class PathWrapper {
	private IPath path;

	public PathWrapper() {
	}

	public PathWrapper(IPath path) {
		this.path = path;
	}

	public IPath getPath() {
		return path;
	}

	public void setPath(IPath path) {
		this.path = path;
	}
}
