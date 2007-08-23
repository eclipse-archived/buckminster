/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.editor;

/**
 * @author Karel Brezina
 *
 */
public enum ArtifactType
{
	CQUERY("bmqtmp-", ".cquery"), CSPEC("bmstmp-", ".cspec");
	
	private String m_tempPrefix;
	
	private String m_tempExtension;
	
	private ArtifactType(String tempPrefix, String tempExtension)
	{
		m_tempPrefix = tempPrefix;
		m_tempExtension = tempExtension;
	}
	
	public String getTempPrefix()
	{
		return m_tempPrefix;
	}
	
	public String getTempExtension()
	{
		return m_tempExtension;
	}
}

