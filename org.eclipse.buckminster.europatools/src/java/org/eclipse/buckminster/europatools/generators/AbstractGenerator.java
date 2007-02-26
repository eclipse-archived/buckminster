/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.europatools.generators;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.europatools.model.SiteContribution;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 *
 */
public abstract class AbstractGenerator
{
	protected static final String GENERATED_FOLDER_REF = "${generated.folder}";
	private final String m_topProject;
	private final File m_workingDir;

	AbstractGenerator(String topProject, File workingDir)
	{
		m_topProject = topProject;
		m_workingDir = workingDir;
	}

	public abstract void generate(SiteContribution siteContribution) throws CoreException;

	public File getRMAPFile()
	{
		return new File(m_workingDir, m_topProject + ".rmap");
	}

	public String getTopProject()
	{
		return m_topProject;
	}

	public File getWorkingDir()
	{
		return m_workingDir;
	}

	protected abstract File getArtifactFile();

	protected abstract ISaxable getGeneratedArtifact() throws CoreException;

	public void save() throws CoreException
	{
		OutputStream output = null;
		try
		{
			File outputFile = getArtifactFile().getAbsoluteFile();
			File outputDir = outputFile.getParentFile();
			if(outputDir != null)
				if(!(outputDir.mkdirs() || outputDir.exists()))
					throw new IOException("Failed to create directory " + outputDir);

			output = new BufferedOutputStream(new FileOutputStream(getArtifactFile()));
			Utils.serialize(getGeneratedArtifact(), output);
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(output);
		}
	}
}
