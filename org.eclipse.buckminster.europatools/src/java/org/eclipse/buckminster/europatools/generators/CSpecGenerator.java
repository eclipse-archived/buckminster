/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.europatools.generators;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.europatools.model.Feature;
import org.eclipse.buckminster.europatools.model.SiteContribution;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 */
public class CSpecGenerator extends AbstractGenerator
{
	private final List<CSpec> m_cspecs = new ArrayList<CSpec>();
	private CSpecBuilder m_mainCSpec;

	public CSpecGenerator(String topComponent, File generatedFolder)
	{
		super(topComponent, generatedFolder);
	}

	@Override
	public void generate(SiteContribution siteContribution) throws CoreException
	{
		CSpecBuilder cspecBuilder = new CSpecBuilder();
		String name = siteContribution.getName();
		cspecBuilder.setName(name);
		for(Feature feature : siteContribution.getFeatures())
		{
			ComponentRequest request = new ComponentRequest(feature.getName(), null, feature.getVersionDesignator());
			cspecBuilder.removeDependency(request.getName());
			cspecBuilder.addDependency(request);
		}
		CSpec cspec = cspecBuilder.createCSpec();

		m_cspecs.add(cspec);

		cspecBuilder = getMainCSpec();
		cspecBuilder.removeDependency(name);
		cspecBuilder.addDependency(new ComponentRequest(name, null, null));
	}

	@Override
	protected File getArtifactFile()
	{
		return new File(getWorkingDir(), getTopProject() + ".cspec");
	}

	@Override
	protected ISaxable getGeneratedArtifact() throws CoreException
	{
		return getMainCSpec().createCSpec();
	}

	private CSpecBuilder getMainCSpec() throws CoreException
	{
		if(m_mainCSpec == null)
		{
			InputStream input = null;
			m_mainCSpec = new CSpecBuilder();
			try
			{
				File file = getArtifactFile();
				input = new BufferedInputStream(new FileInputStream(file));
				CSpec cspec = CorePlugin.getDefault().getParserFactory().getCSpecParser(true).parse(file.toString(), input);
				m_mainCSpec.initFrom(cspec);
			}
			catch(FileNotFoundException e)
			{
				m_mainCSpec.setName(getTopProject());
			}
			catch(Exception e)
			{
				throw BuckminsterException.wrap(e);
			}
			finally
			{
				IOUtils.close(input);
			}
		}
		return m_mainCSpec;
	}

	@Override
	public void save() throws CoreException
	{
		// Super saves the main cspec (top level)
		//
		super.save();
		
		// Save each of the contributed cspecs
		//
		for(CSpec cspec : m_cspecs)
		{
			OutputStream output = null;
			try
			{
				File cspecFile = new File(getWorkingDir(), cspec.getName() + ".cspec");
				output = new BufferedOutputStream(new FileOutputStream(cspecFile));
				Utils.serialize(cspec, output);
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
}
