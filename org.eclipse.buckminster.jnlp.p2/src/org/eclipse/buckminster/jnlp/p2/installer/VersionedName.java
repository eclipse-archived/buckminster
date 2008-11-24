/*******************************************************************************
 * Copyright (c) 2008 Code 9 and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Code 9 - initial API and implementation
 *     Cloudsmith - ongoing development
 *******************************************************************************/
package org.eclipse.buckminster.jnlp.p2.installer;

import org.osgi.framework.Version;

public class VersionedName
{
	private String m_id;

	private Version m_version;

	public VersionedName(String id, String version)
	{
		this.m_id = id;
		this.m_version = new Version(version == null
				? "0.0.0"
				: version);
	}

	public VersionedName(String id, Version version)
	{
		this.m_id = id;
		this.m_version = version;
	}

	public String getId()
	{
		return m_id;
	}

	public Version getVersion()
	{
		return m_version;
	}

	@Override
	public String toString()
	{
		return m_id + "/" + (m_version == null
				? "0.0.0"
				: m_version.toString());
	}
}
