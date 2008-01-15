/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.pde.tasks;

import java.io.File;
import java.io.IOException;

import org.eclipse.buckminster.ant.tasks.VersionQualifierTask;

/**
 * @author Thomas Hallgren
 */
abstract class VersionConsolidator extends VersionQualifierTask
{
	private final File m_outputFile;

	VersionConsolidator(File outputFile, File propertiesFile, String qualifier) throws IOException
	{
		super(propertiesFile, qualifier);
		m_outputFile = outputFile;
	}

	File getOutputFile()
	{
		return m_outputFile;
	}
}
