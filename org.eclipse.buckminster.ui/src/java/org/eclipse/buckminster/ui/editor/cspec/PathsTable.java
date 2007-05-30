/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.editor.cspec;

import java.util.List;

import org.eclipse.buckminster.ui.general.editor.IValidator;
import org.eclipse.buckminster.ui.general.editor.ValidatorException;
import org.eclipse.buckminster.ui.general.editor.simple.SimpleTable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * @author Karel Brezina
 *
 */
public class PathsTable extends SimpleTable<IPath>
{

	public PathsTable(List<IPath> data)
	{
		super(data);
	}

	public String[] getColumnHeaders()
	{
		return new String[] {"Path"};
	}

	public int[] getColumnWeights()
	{
		return new int[] {1};
	}

	public Object[] toRowArray(IPath t)
	{
		return new Object[] {t.toOSString()};
	}

	public IPath toRowClass(Object[] args) throws ValidatorException
	{
		String pathString = (String) args[0];
		return pathString == null ? null :(IPath) Path.fromOSString(pathString);
	}
	
	@Override
	public IValidator getFieldValidator(int idx)
	{
		switch(idx)
		{
		case 0:
			return SimpleTable.createNotEmptyStringValidator("Path cannot be empty");
		default:
			return SimpleTable.getEmptyValidator();
		}
	}
}
