/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.core.runtime.IPath;

public class PathAlreadyDefinedException extends LocalizedException
{
	private static final long serialVersionUID = 1150541236134535409L;
	private final String m_name;
	private final String m_attribute;
	private final IPath m_path;

	public PathAlreadyDefinedException(String name, String attribute, IPath path)
	{
		super("CSpec {0}, attribute {1} already defines the path {2}");
		m_name = name;
		m_attribute = attribute;
		m_path = path;
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return new String[] { m_name, m_attribute, m_path.toPortableString() };
	}
}

