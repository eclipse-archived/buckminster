/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.europatools.generators;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Map;

import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.europatools.model.SiteContribution;
import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 */
public class CQueryGenerator extends AbstractGenerator
{
	public CQueryGenerator(String topComponent, File generatedFolder)
	{
		super(topComponent, generatedFolder);
	}

	@Override
	public void generate(SiteContribution siteContribution) throws CoreException
	{
	}

	@Override
	protected File getArtifactFile()
	{
		return new File(getWorkingDir(), getTopProject() + ".cquery");
	}

	@Override
	protected ISaxable getGeneratedArtifact() throws CoreException
	{
		ComponentQueryBuilder cqueryBuilder = new ComponentQueryBuilder();
		cqueryBuilder.setRootRequest(new ComponentRequest(getTopProject(), null, null));
		File workingDir = getWorkingDir();
		try
		{
			cqueryBuilder.setResourceMapURL(new File(workingDir, getTopProject() + ".rmap").toURI().toURL());
			Map<String,String> props = cqueryBuilder.getProperties();
			props.put("generated.folder.url", workingDir.toURI().toURL().toString());
			props.put("buckminster.materializer.name", "site.mirror");
			return cqueryBuilder.createComponentQuery();
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}
}
