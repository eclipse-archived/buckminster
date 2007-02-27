/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.europatools.commands;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.cmdline.AbstractCommand;
import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.SimpleErrorExitException;
import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.europatools.EuropaTools;
import org.eclipse.buckminster.europatools.generators.AbstractGenerator;
import org.eclipse.buckminster.europatools.generators.CQueryGenerator;
import org.eclipse.buckminster.europatools.generators.CSpecGenerator;
import org.eclipse.buckminster.europatools.generators.RMapGenerator;
import org.eclipse.buckminster.europatools.generators.SiteGenerator;
import org.eclipse.buckminster.europatools.model.SiteContribution;
import org.eclipse.buckminster.europatools.parser.SiteContributionParser;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public class Generate extends AbstractCommand
{
	static private final OptionDescriptor TOP_PROJECT = new OptionDescriptor('T', "topproject",
			OptionValueType.REQUIRED);

	static private final OptionDescriptor OUTPUT_DIR = new OptionDescriptor('O', "outputdir",
			OptionValueType.REQUIRED);

	static private final OptionDescriptor CLEAN = new OptionDescriptor('C', "clean",
			OptionValueType.NONE);

	private final List<URL> m_siteContributions = new ArrayList<URL>();

	private String m_topProject;

	private File m_outputDir;

	private boolean m_clean;

	@SuppressWarnings("unchecked")
	@Override
	protected void getOptionDescriptors(List appendHere) throws Exception
	{
		appendHere.add(TOP_PROJECT);
		appendHere.add(OUTPUT_DIR);
		appendHere.add(CLEAN);
	}

	@Override
	protected void handleOption(Option option) throws Exception
	{
		if(option.is(TOP_PROJECT))
			m_topProject = option.getValue();
		else if(option.is(OUTPUT_DIR))
			m_outputDir = new File(option.getValue());
		else if(option.is(CLEAN))
			m_clean = true;
	}

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		for(String s : unparsed)
			m_siteContributions.add(URLUtils.normalizeToURL(s));
	}

	@Override
	protected int run(IProgressMonitor monitor) throws Exception
	{
		Logger logger = EuropaTools.getLogger();
		if(m_siteContributions.size() == 0)
			throw new UsageException("Must have at least one site contribution");

		if(m_topProject == null)
			throw new UsageException("Missing required option --topproject");

		if(m_outputDir == null)
			m_outputDir = new File(System.getProperty("user.dir"), m_topProject);

		if(!m_outputDir.isDirectory())
		{
			if(m_outputDir.exists())
				throw new SimpleErrorExitException(m_outputDir + " exists but it is not a directory");

			logger.info("Creating directory " + m_outputDir);
			if(!m_outputDir.mkdirs())
				throw new SimpleErrorExitException("Unable to create output directory " + m_outputDir);
		}
		else if(m_clean)
		{
			logger.info("Cleaning directory " + m_outputDir);
			FileUtils.prepareDestination(m_outputDir, true, new NullProgressMonitor());
		}

		AbstractGenerator[] generators = new AbstractGenerator[]
		{
			new CSpecGenerator(m_topProject, m_outputDir),
			new RMapGenerator(m_topProject, m_outputDir),
			new CQueryGenerator(m_topProject, m_outputDir),
			new SiteGenerator(m_topProject, m_outputDir)
		};

		monitor.beginTask(null, m_siteContributions.size() * 100 + 100);
		IParser<SiteContribution> parser = new SiteContributionParser();
		for(URL siteContribURL : m_siteContributions)
		{
			logger.info("Generating artifacts from " + siteContribURL);
			InputStream input = null;
			try
			{
				input = new BufferedInputStream(URLUtils.openStream(siteContribURL, MonitorUtils
						.subMonitor(monitor, 92)));
				SiteContribution siteContribution = parser.parse(siteContribURL.toExternalForm(), input);
				for(AbstractGenerator generator : generators)
				{
					generator.generate(siteContribution);
					MonitorUtils.worked(monitor, 2);
				}
			}
			finally
			{
				IOUtils.close(input);
			}
		}

		logger.info("Writing generated artifacts to " + m_outputDir);
		for(AbstractGenerator generator : generators)
		{
			generator.save();
			MonitorUtils.worked(monitor, 25);
		}
		logger.info("Generation complete");
		return 0;
	}
}
