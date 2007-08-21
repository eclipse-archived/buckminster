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
public class PathsTable extends SimpleTable<PathWrapper>
{

	public PathsTable(List<PathWrapper> data)
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

	public Object[] toRowArray(PathWrapper t)
	{
		return new Object[] {t.getPath().toOSString()};
	}

	public PathWrapper createRowClass()
	{
		return new PathWrapper();
	}

	public void updateRowClass(PathWrapper path, Object[] args) throws ValidatorException
	{
		String pathString = (String) args[0];
		path.setPath(pathString == null ? null :(IPath) Path.fromOSString(pathString));
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

class PathWrapper
{
	private IPath m_path;
	
	public PathWrapper()
	{
	}
	
	public PathWrapper(IPath path)
	{
		m_path = path;
	}

	public IPath getPath()
	{
		return m_path;
	}

	public void setPath(IPath path)
	{
		m_path = path;
	}
}
