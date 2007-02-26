/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.europatools.generators;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.rmap.model.Locator;
import org.eclipse.buckminster.core.rmap.model.Matcher;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.ResourceMap;
import org.eclipse.buckminster.core.rmap.model.SearchPath;
import org.eclipse.buckminster.europatools.model.Feature;
import org.eclipse.buckminster.europatools.model.SiteContribution;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class RMapGenerator extends AbstractGenerator
{
	private static final String DEFAULT_SEARCH_PATH = "default";

	private static SearchPath createDefaultSearchPath()
	{
		// This is the search path that finds the cspec files. It
		// maps component names such as org.eclipse.datatools.europa.features
		// to a file named features-datatools.cspec
		//
		SearchPath dflt = new SearchPath(DEFAULT_SEARCH_PATH);
		dflt.addProvider(new Provider(
				IReaderType.URL,
				IComponentType.BUCKMINSTER,
				null, null,
				new Format(GENERATED_FOLDER_REF + "/${" + KeyConstants.COMPONENT_NAME + "}.cspec"),
				true, true, null));
		return dflt;
	}

	private static SearchPath createSearchPath(SiteContribution sc)
	{
		SearchPath searchPath = new SearchPath(sc.getName());
		searchPath.addProvider(new Provider(
				IReaderType.ECLIPSE_SITE_FEATURE,
				IComponentType.ECLIPSE_SITE_FEATURE,
				null, null,
				new Format(sc.getSource()),
				true, true, null));
		return searchPath;
	}

	private ResourceMap m_resourceMap;

	public RMapGenerator(String topProjectName, File workingDirectory) throws CoreException
	{
		super(topProjectName, workingDirectory);
	}

	@Override
	public void generate(SiteContribution sc) throws CoreException
	{
		// This will effectively remove any present search path under the
		// same name
		//
		ResourceMap resourceMap = getResourceMap();
		resourceMap.addSearchPath(createSearchPath(sc));

		// Consolidate locators. Add new ones and remove extras that we don't have
		// any use for.
		//
		List<Feature> features = sc.getFeatures();
		int featureCount = features.size();

		String contribName = sc.getName();
		String cspecLocatorPattern = "^\\Q" + contribName + "\\E$";

		// Remove old matchers appointing our searchPath. Also, check that
		// no other matchers match our features.
		//
		List<Matcher> matchers = m_resourceMap.getMatchers();
		int idx = matchers.size();
		while(--idx >= 0)
		{
			Matcher matcher = matchers.get(idx);
			if(matcher instanceof Locator)
			{
				Locator locator = (Locator)matcher;
				String spName = locator.getSearchPathName();
				if(spName.equals(contribName)
						|| (spName.equals(DEFAULT_SEARCH_PATH) && locator.getPattern().pattern().equals(
								cspecLocatorPattern)))
				{
					matchers.remove(idx);
					continue;
				}
			}

			// It is an error if another matcher matches one of the
			// contributed features.
			//
			for(int featureIdx = 0; featureIdx < featureCount; ++featureIdx)
			{
				String featureName = features.get(featureIdx).getName();
				if(matcher.matches(featureName))
					throw new AmbigousMatchException(matcher.getPattern().pattern(), featureName, contribName);
			}
		}

		// Add one locator per feature. The all represent an exact match.
		//
		for(idx = 0; idx < featureCount; ++idx)
		{
			Feature feature = features.get(idx);
			String featureName = feature.getName();
			if(featureName.equals(contribName))
				throw BuckminsterException.fromMessage("A feature cannot have the same name as the contribution as a whole: " + contribName);
			resourceMap.addMatcher(new Locator(m_resourceMap, "^\\Q" + featureName + "\\E$", contribName));
		}

		// Add the locator that appoints the searchPath used when finding the contribution
		// cspec.
		//
		resourceMap.addMatcher(new Locator(m_resourceMap, cspecLocatorPattern, DEFAULT_SEARCH_PATH));
	}

	public void clear() throws CoreException
	{
		getResourceMap().clear();
	}

	@Override
	protected File getArtifactFile()
	{
		return getRMAPFile();
	}

	@Override
	protected ISaxable getGeneratedArtifact() throws CoreException
	{
		return getResourceMap();
	}

	private synchronized ResourceMap getResourceMap() throws CoreException
	{
		if(m_resourceMap == null)
		{
			InputStream input = null;
			ResourceMap resourceMap;
			try
			{
				File resourceMapFile = getRMAPFile();
				input = new BufferedInputStream(new FileInputStream(resourceMapFile));
				IParser<ResourceMap> parser = CorePlugin.getDefault().getParserFactory().getResourceMapParser(true);
				resourceMap = parser.parse(resourceMapFile.toString(), input);
			}
			catch(SAXException e)
			{
				throw BuckminsterException.wrap(e);
			}
			catch(FileNotFoundException e)
			{
				resourceMap = new ResourceMap();
				resourceMap.addSearchPath(createDefaultSearchPath());
			}
			finally
			{
				IOUtils.close(input);
			}
			m_resourceMap = resourceMap;
		}
		return m_resourceMap;
	}
}
