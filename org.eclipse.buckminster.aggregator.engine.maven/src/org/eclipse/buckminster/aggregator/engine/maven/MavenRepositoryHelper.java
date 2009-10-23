/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.aggregator.engine.maven;

/**
 * @author Filip Hrbek (filip.hrbek@cloudsmith.com)
 * 
 */
public class MavenRepositoryHelper
{
	private String[][] m_mappingRules;

	private InstallableUnitMapping m_top;

	public MavenRepositoryHelper(InstallableUnitMapping top, String[][] mappingRules)
	{
		m_top = top;
		m_mappingRules = mappingRules;
	}

	public String[][] getMappingRules()
	{
		return m_mappingRules;
	}

	public InstallableUnitMapping getTop()
	{
		return m_top;
	}
}
