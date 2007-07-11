/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.pde.tasks;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.Group;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.site.SaxableSite;
import org.eclipse.buckminster.core.site.SiteReader;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.buckminster.pde.internal.FeatureModelReader;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.update.core.Site;
import org.eclipse.update.core.SiteFeatureReferenceModel;
import org.eclipse.update.core.model.CategoryModel;
import org.eclipse.update.internal.core.ExtendedSite;
import org.eclipse.update.internal.core.UpdateSiteFeatureReference;
import org.xml.sax.SAXException;

/**
 * Scans a folder for jar files containing an OSGi manifest or an Eclipse feature.xml and
 * generates a JNLP version.xml file based on the information in them. The version.xml
 * file is output in the same folder.
 * 
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class UpdateSiteGenerator
{
	private final List<File> m_features;
	private final File m_outputFile;
	private final IActionContext m_actionContext;
	private final Site m_site;

	public UpdateSiteGenerator(List<File> features, File template, File outputFile) throws CoreException
	{
		m_outputFile = outputFile;
		m_features = features;
		m_actionContext = AbstractActor.getActiveContext();
		if(template != null)
			m_site = SiteReader.getSite(template, null);
		else
			m_site = new ExtendedSite();
	}

	public void run() throws CoreException
	{
		OutputStream output = null;
		try
		{
			output = new BufferedOutputStream(new FileOutputStream(m_outputFile));
			for(File file : m_features)
			{
				String leafName = file.getName();
				if(!leafName.endsWith(".jar"))
					continue;

				JarFile jarFile = null;
				try
				{
					jarFile = new JarFile(file);

					JarEntry entry = jarFile.getJarEntry(IPDEConstants.FEATURE_FILE);
					if(entry == null)
						continue;

					IFeatureModel model = FeatureModelReader.readFeatureModel(jarFile.getInputStream(entry));
					generateFromFeature(file, model.getFeature());
				}
				finally
				{
					if(jarFile != null)
						jarFile.close();
				}

			}
			SaxableSite saxableSite = new SaxableSite(m_site);
			Utils.serialize(saxableSite, output);
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		catch(SAXException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(output);
		}
	}

	private static boolean categoryExists(CategoryModel[] categories, String categoryName)
	{
		int idx = categories.length;
		while(--idx >= 0)
			if(categories[idx].getName().equals(categoryName))
				return true;
		return false;
	}

	private void generateFromFeature(File file, IFeature feature) throws CoreException
	{
		IVersionType osgiType = VersionFactory.OSGiType;
		String featureName = feature.getId();
		StringBuilder urlBuilder = new StringBuilder(IPDEConstants.FEATURES_FOLDER);
		urlBuilder.append('/');
		urlBuilder.append(file.getName());

		// --We look for a category for the feature in two ways.
		//
		// First we check if an entry that matches the feature by name and unqualified version
		// exists in the template. If it does, we use that entry
		//
		String verStr = feature.getVersion();
		if(verStr == null || verStr.length() == 0)
			verStr = "0.0.0";
		SiteFeatureReferenceModel model = null;
		for(SiteFeatureReferenceModel oldModel : m_site.getFeatureReferenceModels())
		{
			if(featureName.equals(oldModel.getFeatureIdentifier()))
			{
				IVersion oldVer = osgiType.fromString(oldModel.getFeatureVersion());
				IVersion ver = osgiType.fromString(verStr);
				if(ver.equalsUnqualified(oldVer))
				{
					model = oldModel;
					break;
				}
			}
		}

		// When no entry was found, we check the cspec to see if the feature is represented
		// in a group that maps to a category of the feature template
		//
		if(model == null)
		{
			CategoryModel[] categories = m_site.getCategoryModels();
			CSpec cspec = m_actionContext.getCSpec();
			Collection<Attribute> attributes = cspec.getAttributes().values();
	
			model = new UpdateSiteFeatureReference();
			model.setFeatureIdentifier(featureName);

			for(Attribute attr : attributes)
			{
				if(!(attr instanceof Group))
					continue;
	
				String categoryName = attr.getName();
				if(!categoryExists(categories, attr.getName()))
					continue;
	
				for(Prerequisite included : attr.getPrerequisites())
				{
					if(!featureName.equals(included.getComponentName()))
						continue;

					model.addCategoryName(categoryName);
					break;
				}
			}
			m_site.addFeatureReferenceModel(model);
		}
		model.setURLString(urlBuilder.toString());
		model.setFeatureVersion(verStr);
		model.setSiteModel(m_site);
	}
}
