/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.europatools.generators;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Collection;
import java.util.Map;

import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.reader.SiteReader;
import org.eclipse.buckminster.core.reader.URLReaderType;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.europatools.model.SiteContribution;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.update.core.Site;
import org.eclipse.update.core.SiteFeatureReferenceModel;
import org.eclipse.update.core.model.CategoryModel;
import org.eclipse.update.internal.core.ExtendedSite;
import org.eclipse.update.internal.core.UpdateSiteFeatureReference;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class SiteGenerator extends AbstractGenerator
{
	private final URL m_templateSite;
	private Site m_site;

	public SiteGenerator(String topProject, URL templateSite, File generatedDir)
	{
		super(topProject, generatedDir);
		m_templateSite = templateSite;
	}

	private static boolean categoryExists(CategoryModel[] categories, String categoryName)
	{
		int idx = categories.length;
		while(--idx >= 0)
			if(categories[idx].getName().equals(categoryName))
				return true;
		return false;
	}

	@Override
	public void generate(SiteContribution sc) throws CoreException
	{
		Site site = getSite();
		CategoryModel[] categories = site.getCategoryModels();

		CSpec cspec = sc.getCSpec();
		Map<String,ComponentRequest> features = cspec.getDependencies();
		for(SiteFeatureReferenceModel model : site.getFeatureReferenceModels())
			if(features.containsKey(model.getFeatureIdentifier()))
				site.removeFeatureReferenceModel(model);

		StringBuilder urlBuilder = new StringBuilder("features/");
		Collection<Attribute> attributes = cspec.getAttributes().values();

		for(ComponentRequest feature : features.values())
		{
			SiteFeatureReferenceModel model = new UpdateSiteFeatureReference();
			String name = feature.getName();
			urlBuilder.setLength(9);
			urlBuilder.append(name);
			model.setFeatureIdentifier(name);
			IVersionDesignator vd = feature.getVersionDesignator();
			if(vd != null)
			{
				String version = vd.getVersion().toString();
				urlBuilder.append('_');
				urlBuilder.append(version);
				model.setFeatureVersion(version);
			}

			urlBuilder.append(".jar");		
			for(Attribute attr : attributes)
			{
				for(Prerequisite included : attr.getPrerequisites())
				{
					if(!name.equals(included.getComponentName()))
						continue;

					String categoryName = attr.getName();
					if(!categoryExists(categories, categoryName))
					{
						if(m_templateSite != null)
							throw BuckminsterException.fromMessage("No such category: " + categoryName);

						CategoryModel category = new CategoryModel();
						category.setLabel(categoryName);
						category.setName(categoryName);
						site.addCategoryModel(category);
						categories = site.getCategoryModels();
					}
					model.addCategoryName(categoryName);
					break;
				}
			}
			model.setURLString(urlBuilder.toString());
			model.setSiteModel(site);
			site.addFeatureReferenceModel(model);
		}
	}

	@Override
	protected File getArtifactFile()
	{
		return new File(getWorkingDir(), getTopProject() + ".site");
	}

	@Override
	protected ISaxable getGeneratedArtifact() throws CoreException
	{
		return new SaxableSite(m_site);
	}

	private Site getSite() throws CoreException
	{
		if(m_site == null)
		{
			try
			{
				try
				{
					m_site = parseSite(getArtifactFile().toURI().toURL());
				}
				catch(CoreException e)
				{
					if(!(e.getCause() instanceof FileNotFoundException))
						throw e;

					if(m_templateSite != null)
						m_site = parseSite(m_templateSite);
					else
						m_site = new ExtendedSite();
				}
			}
			catch(Exception e)
			{
				throw BuckminsterException.wrap(e);
			}
		}
		return m_site;
	}

	private static Site parseSite(URL siteURL) throws CoreException
	{
		IProgressMonitor nullMonitor = new NullProgressMonitor();
		return SiteReader.getSite(URLReaderType.getReader(siteURL, nullMonitor), nullMonitor);
	}
}
